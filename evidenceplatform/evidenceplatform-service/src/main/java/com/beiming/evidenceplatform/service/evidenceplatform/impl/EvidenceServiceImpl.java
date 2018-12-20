package com.beiming.evidenceplatform.service.evidenceplatform.impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.enums.evidenceplatform.StatusCode;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentCosService;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.EncryptionUtils;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.FileFormatUtils;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.FileUtils;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.ZIPutils;
import com.beiming.evidenceplatform.dao.EvidenceMapper;
import com.beiming.evidenceplatform.dao.MessageMapper;
import com.beiming.evidenceplatform.dao.UserDetailMapper;
import com.beiming.evidenceplatform.dao.UserMapper;
import com.beiming.evidenceplatform.domain.dto.UserDTO;
import com.beiming.evidenceplatform.domain.evidenceplatform.ClientType;
import com.beiming.evidenceplatform.domain.evidenceplatform.Evidence;
import com.beiming.evidenceplatform.domain.evidenceplatform.EvidenceType;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.EvidenceDTO;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.redis.enums.RedisKey;
import com.beiming.evidenceplatform.service.RedisService;
import com.beiming.evidenceplatform.service.evidenceplatform.ClientTypeService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceTypeService;
import com.beiming.evidenceplatform.service.evidenceplatform.ProofService;
import com.beiming.evidenceplatform.service.evidenceplatform.VoucherService;
import com.beiming.evidenceplatform.service.usermanage.BidderUserManage;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : chenpeng
 * @date : 2018-08-17 11:48 存证相关操作 腾讯云文件存储的key必须用'/'而不能用'\'
 */
@Service
public class EvidenceServiceImpl implements EvidenceService {
  private static final Logger LOGGER = LoggerFactory.getLogger(EvidenceServiceImpl.class);
  @Value("${file.storage.tmp.dir}")
  private String path;
  @Resource
  private EvidenceTypeService evidenceTypeService;
  @Resource
  private EvidenceMapper evidenceMapper;
  @Resource
  private UserDetailMapper userDetailMapper;
  @Resource
  private UserMapper userMapper;
  @Resource
  private MessageMapper messageMapper;
  @Resource
  private ClientTypeService clientTypeService;
  @Autowired
  private ProofService proofService;
  @Autowired
  private VoucherService voucherService;
  @Autowired
  private TencentCosService tencentCosService;
  @Autowired
  private RedisService redisService;
  @Autowired
  private BidderUserManage bidderUserManage;


  // 上传文件
  @Override
  public Result uploadFiles(List<MultipartFile> files, String remarks, String clientType) {
    LOGGER.info("开始进行文件上传");
    Long userId = Long.parseLong(ContextUtil.getCurrentUserId());
    int fileNumber = files.size();
    int fileUpNumber = 0;
    Long time = new Date().getTime();
    long totalSize = 0;
    for (MultipartFile file : files) {
      totalSize += file.getSize();
    }
    String usageAmount = userDetailMapper.getUsageAmountByUserId(userId);
    if (totalSize > Long.parseLong(usageAmount) * 1024) {
      return Result.failed(ErrorCode.FILE_SIZE_OVERRUN, "用户空间不足");
    }
    StringBuffer msg = new StringBuffer("尊敬的用户，您的证据文件");
    String msgFileName = "";
    LOGGER.info("循环判断用户文件是否满足上传条件");
    for (MultipartFile file : files) {
      String fileName = file.getOriginalFilename();
      msgFileName += "【" + StringUtils.substringBeforeLast(fileName, ".") + "】、";

      // 获取用户id,判断用户是否重复上传 单纯判断hash 不好判断
      Evidence evidenceFile = null;

      if (evidenceFile == null) {
        Long size = file.getSize();
        String fileId = UUID.randomUUID().toString();
        String url = File.separator + userId + File.separator + time;
        String key = "/" + userId + "/" + time;
        String filepath = path + url;
        File dest = new File(filepath);
        dest.mkdirs();
        filepath += File.separator + fileName;
        dest = new File(filepath);
        try {
          file.transferTo(dest);
          // 上传文件到服务器
          JSONObject object = tencentCosService.putObject(key + "/" + fileName, dest);
          if (object.get("file_status").equals(ErrorCode.SUCCESS)) {
            LOGGER.info("文件上传腾讯云成功");
          } else {
            LOGGER.error("文件上传腾讯云失败！");
          }
          Evidence evidence = new Evidence();
          // 获取文件 fileHash
          String hash = EncryptionUtils.fileHash(dest, evidence.getAlg());
          evidence.setClientType(clientType);
          evidence.setLength(size);
          evidence.setHash(hash);
          evidence.setUri(filepath);
          evidence.setCosUri(key + "/" + fileName);
          evidence.setCreateTime(time);
          evidence.setName(fileName.substring(0, fileName.lastIndexOf(".")));
          evidence.setFileId(fileId);
          String type = fileName.toLowerCase();
          evidence.setEvidenceType(FileFormatUtils.typeCode(type));
          evidence.setUserId(userId);
          evidence.setRemarks(remarks);

          evidenceMapper.saveEvidence(evidence);
          fileUpNumber++;
          LOGGER.info("文件上传成功!");
          proofService.generateTicket(fileId);
        } catch (Exception e) {
          LOGGER.error("上传文件保存失败,错误的异常是:", e);
          e.printStackTrace();
          return Result.failed(ErrorCode.FAIL_FILE, "上传文件意外终止,失败的文件是" + fileName);
        }
      }
    }
    LOGGER.info("文件上传成功,开始进行容量判断");
    msg.append(StringUtils.substringBeforeLast(msgFileName, "、"));
    if (fileNumber == fileUpNumber) {
      userDetailMapper.updateUsageAmountByUserId(ContextUtil.getCurrentUserId(),
          (Long.parseLong(usageAmount) * 1024 - totalSize) / 1024 + "");
      msg.append("已上传成功。");
      LOGGER.info("上传文件操作完成");
      messageMapper.insertMessageWithUserId(msg.toString(), userId);
      return Result.success("文件全部上传成功!");
    }
    msg.append("上传失败！");
    messageMapper.insertMessageWithUserId(msg.toString(), userId);
    return Result.failed(ErrorCode.UNEXCEPTED, "有文件上传失败,请误重复上传");
  }


  // 文件下载
  public Result downFile(HttpServletResponse response, HttpServletRequest request, String fileId,
      String phoneCode) throws IOException, NoSuchAlgorithmException {

    LOGGER.info("开始查询证据凭单文件");
    VoucherDTO proof = proofService.findByProofId(fileId);
    String getFromLoccal = null;
    if (proof != null) {
      LOGGER.info("从服务器获取提供下载");
      String proofName =
          proof.getProofUrl().substring(proof.getProofUrl().lastIndexOf(File.separator) + 1);
      getFromLoccal = FileUtils.getFileFromLocal(proofName, proof.getProofUrl(), response, request);
      if ("error".equals(getFromLoccal)) {
        LOGGER.info("从腾讯云下载存证函到服务器");
        tencentCosService.downFile(proof.getCosUri(), proof.getProofUrl());
        getFromLoccal =
            FileUtils.getFileFromLocal(proofName, proof.getProofUrl(), response, request);
      }
    }

    LOGGER.info("开始处理需要验证码下载的文件");
    if (!validate(phoneCode)) {
      return Result.failed(ErrorCode.PHONE_CODE_IS_WRONG, "验证码过期或者验证码有误,请查看后重试!");
    }
    Evidence evidenceFile = evidenceMapper.findFileId(fileId);
    VoucherDTO vourcher = voucherService.findByVourcher(fileId);
    if (evidenceFile == null && proof == null && vourcher == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "下载文件不存在");
    }

    LOGGER.info("开始查询证据文件");
    if (evidenceFile != null) {
      LOGGER.info("从服务器获取提供下载");
      getFromLoccal = FileUtils.getFileFromLocalhostToHash(evidenceFile.getAlg(),
          evidenceFile.getHash(), evidenceFile.getName(), evidenceFile.getUri(),
          evidenceFile.getCosUri(), response, request);
      if ("error".equals(getFromLoccal)) {
        LOGGER.info("从腾讯云下载证据文件到服务器");
        tencentCosService.downFile(evidenceFile.getCosUri(), evidenceFile.getUri());
        getFromLoccal = FileUtils.getFileFromLocalhostToHash(evidenceFile.getAlg(),
            evidenceFile.getHash(), evidenceFile.getName(), evidenceFile.getUri(),
            evidenceFile.getCosUri(), response, request);
      }
    }

    LOGGER.info("开始查询证据出征函文件");
    if (vourcher != null) {
      LOGGER.info("从服务器获取提供下载");
      String substring = vourcher.getVoucherUrl()
          .substring(vourcher.getVoucherUrl().lastIndexOf(File.separator) + 1);
      getFromLoccal = FileUtils.getFileFromLocalhostToHash(vourcher.getAlg(), vourcher.getHash(),
          substring, vourcher.getVoucherUrl(), null, response, request);
      if ("error".equals(getFromLoccal)) {
        LOGGER.info("从腾讯云下载凭证文件到服务器");
        tencentCosService.downFile(vourcher.getCosUri(), vourcher.getVoucherUrl());
        getFromLoccal = FileUtils.getFileFromLocalhostToHash(vourcher.getAlg(), vourcher.getHash(),
            substring, vourcher.getVoucherUrl(), evidenceFile.getCosUri(), response, request);
      }
    }

    LOGGER.info("查询结束,开始判定最终擦查询结果");

    if (StringUtils.isNotBlank(getFromLoccal) && "true".equals(getFromLoccal)) {
      return Result.success("下载成功！");
    } else if (StringUtils.isNotBlank(getFromLoccal) && "error".equals(getFromLoccal)) {
      return Result.failed(ErrorCode.FAIL_FILE, "文件出现问题，被修改了？");
    }
    return Result.failed(ErrorCode.FAIL_FILE, "下载出错");
  }

  // 证据列表复杂查询
  @Override
  public Result findEvidence(Integer pageNo, Integer pageSize, Long userId, String name,
      String evidenceType, String startTime, String endTimes, String status, String proofId)
      throws ParseException {
    LOGGER.info("处理证据列表查询");
    LOGGER.info("接收到的参数为:pageNo:" + pageNo + ",pageSize:" + pageSize + ",userId" + userId + ",name"
        + name + ",evidenceType" + evidenceType + ",startTime" + startTime + ",endTimes" + endTimes
        + ",status" + status + ",proofId" + proofId);

    if (pageNo == 0 || pageSize == 0) {
      Page page = new Page();
      pageNo = page.getPageNo();
      pageSize = page.getPageSize();
    }
    LOGGER.info("开始分页");
    PageHelper.startPage(pageNo, pageSize);

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
    Long createtime = 0L;
    Long endtime = 999999999999999999L;
    if (StringUtils.isNotEmpty(startTime)) {
      createtime = format.parse(startTime).getTime();
    }
    if (StringUtils.isNotEmpty(endTimes)) {
      endtime = format.parse(endTimes).getTime();
    }
    String clientType = null;
    if ("4".equals(evidenceType)) {
      evidenceType = null;
      clientType = "2";
    }

    LOGGER.info("查询输入满足条件后得到结果");
    List<EvidenceDTO> list = evidenceMapper.findEvidence(name, userId, evidenceType, status,
        createtime, endtime, proofId, clientType);
    List<EvidenceType> evidenceTypes = evidenceTypeService.findAll();
    List<ClientType> clientTypes = clientTypeService.findAll();
    Map<String, String> evidenceTypeMap = new HashMap<>();
    Map<String, String> clientTypeMap = new HashMap<>();
    for (EvidenceType type : evidenceTypes) {
      evidenceTypeMap.put(type.getId().toString(), type.getName());
    }

    LOGGER.info("增强封装开始");
    for (ClientType type : clientTypes) {
      clientTypeMap.put(type.getId().toString(), type.getName());
    }
    for (EvidenceDTO dto : list) {
      String type = dto.getUri().substring(dto.getUri().lastIndexOf("."));
      dto.setType(type);
      dto.setUri(null);
      if (StringUtils.isNotBlank(dto.getClientType())) {
        dto.setClientType(clientTypeMap.get(dto.getClientType()));
      }
      if (StringUtils.isNotBlank(dto.getEvidenceType())) {
        dto.setEvidenceType(evidenceTypeMap.get(dto.getEvidenceType()));
      }
      dto.setCreateTimes(new Date(dto.getCreateTime()));
      dto.setFileSize(FileUtils.getFileSize(dto.getLength()));
      if (dto.getVoucherStatus() == null || "0".equals(dto.getVoucherStatus())) {
        dto.setVoucherStatus("未申请");
      } else {
        dto.setVoucherStatus("已申请");
      }
    }

    PageInfo result = new PageInfo(list);
    if (list == null || list.size() == 0) {
      return Result.success(list);
    }
    if (result.getPages() < pageNo) {
      return Result.failed(ErrorCode.ILLEGAL_PARAMETER, "输入的页码过大");
    }

    LOGGER.info("查找成功数据长度为" + result.getList().size());
    return Result.success(result);
  }

  // 修改文件名称
  @Override
  public Result rename(String fileId, String name) {
    LOGGER.info("开始处理修改名称请求");
    LOGGER.info("请求参数为fileid:" + fileId + ",name" + name);
    Evidence evidence = null;
    evidence = evidenceMapper.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "修改的文件不存在!");
    }
    evidence.setName(name);
    evidenceMapper.updateData(evidence);
    String oldName = evidence.getName();
    LOGGER.info("修改文件名成功!");
    return Result.success("修改[" + oldName + "]文件名为[" + name + "]");
  }

  // 修改文件备注
  @Override
  public Result updateRemarks(String fileId, String remarks) {
    LOGGER.info("开始处理文件备注请求");
    LOGGER.info("请求参数为fileid:" + fileId + ",remarks" + remarks);
    Evidence evidence = null;
    evidence = evidenceMapper.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "修改的文件不存在!");
    }
    evidence.setRemarks(remarks);
    evidenceMapper.updateData(evidence);
    String oldName = evidence.getName();
    LOGGER.info("添加备注成功!");
    return Result.success("添加" + oldName + "添加备注成功");
  }

  @Override
  public Result getEvidenceTypes() {
    LOGGER.info("查询evidenceType 列表");
    return Result.success(evidenceTypeService.findAll());
  }

  @Override
  public Evidence findFileId(String uuid) {
    LOGGER.info("查询evidenceId");
    return evidenceMapper.findFileId(uuid);
  }

  @Override
  public void updateData(Evidence evidence) {
    LOGGER.info("开始更新evidencde表中字段信息");
    evidenceMapper.updateData(evidence);
  }

  // 逻辑删除文件,或者恢复文件状态
  @Override
  public Result updateStatus(String fileId) {
    LOGGER.info("处理逻辑删除文件,更改文件状态");
    LOGGER.info("参数为,fileId:" + fileId);
    Evidence evidence = null;
    evidence = evidenceMapper.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "修改的文件不存在!");
    } else {
      String oldName = evidence.getName();
      String status = StatusCode.RESTORE.getValue() + "";
      if (evidence.getStatus().equals(status)) {
        evidence.setStatus(StatusCode.DELETE.getValue() + "");
        evidenceMapper.updateData(evidence);
        LOGGER.info("逻辑删除文件成功!");
        return Result.success("删除" + oldName + "文件,如需恢复请查看回收站");
      }
      evidence.setStatus(StatusCode.RESTORE.getValue() + "");
      evidenceMapper.updateData(evidence);
      LOGGER.info("逻辑恢复文件成功!");
      return Result.success("恢复" + oldName + "文件");
    }
  }

  // 彻底删除,不可恢复
  @Override
  public Result deletePermanently(String fileId, String phoneCode) {
    LOGGER.info("文件彻底删除请求!");
    LOGGER.info("参数为,fileId:" + fileId + ",phoneCode" + phoneCode);
    if (!validate(phoneCode)) {
      return Result.failed(ErrorCode.PHONE_CODE_IS_WRONG, "验证码过期或者验证码有误,请查看后重试!");
    }
    Evidence evidence = null;
    evidence = evidenceMapper.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "彻底删除的文件不存在!");
    }

    Long userId = Long.parseLong(ContextUtil.getCurrentUserId());

    LOGGER.info("开始读取" + userId.toString() + "用户的" + evidence.getId() + "相关的数据信息,开始彻底删除");
    LOGGER.info("开始读取用户的本地文件路径地址信息");
    List<File> fileList = new ArrayList<>();
    File evidencePath = new File(evidence.getUri());
    File proofFile = new File(proofService.findByEvidenceId(evidence.getId()).getProofUrl());
    fileList.add(evidencePath);
    fileList.add(proofFile);

    LOGGER.info("开始读取用户的腾讯云上的地址信息");
    List<String> cosFileList = new ArrayList<>();
    String cosEvidencePath = evidence.getCosUri();
    String cosProofFile = proofService.findByEvidenceId(evidence.getId()).getCosUri();
    cosFileList.add(cosEvidencePath);
    cosFileList.add(cosProofFile);

    LOGGER.info("判断用户有没有生成存证函");
    VoucherDTO voucherDto = voucherService.findByEvidenceId(evidence.getId());
    if (voucherDto == null) {
      LOGGER.info("用户的没有生成存证函相关的数据信息,开始彻底删除");
    } else {
      File voucherFile =
          new File(voucherService.findByEvidenceId(evidence.getId()).getVoucherUrl());
      fileList.add(voucherFile);
      cosFileList.add(voucherDto.getCosUri());
    }

    for (File file : fileList) {
      if (file.isFile()) {
        LOGGER.info("开始从本地移除" + userId + "的" + file.getName() + "文件");
        file.delete();
        LOGGER.info("本地移除" + userId + "的" + file.getName() + "文件成功");
      }
    }

    for (String cosFilePath : cosFileList) {
      LOGGER.info("开始从腾讯云上移除" + userId + "的" + cosFilePath + "文件");
      // 删除腾讯云文件
      JSONObject object = tencentCosService.deleteObject(cosFilePath);
      // 删除腾讯云文件
      if (object.get("file_status").equals(ErrorCode.SUCCESS)) {
        LOGGER.info("文件从腾讯云删除成功");
      } else {
        LOGGER.error("文件从腾讯云删除失败！");
      }
    }

    LOGGER.info("开始从数据库中删除" + userId + "用户的证据信息");
    String name = evidence.getName();
    Long eid = evidence.getId();
    evidenceMapper.deleteEvidence(evidence.getFileId());
    proofService.delProof(eid);
    voucherService.delVoucher(eid);

    // 更新用户空间
    String usageAmount = userDetailMapper.getUsageAmountByUserId(userId);
    if (evidenceMapper.getEvidenceNumByUserId(userId) == 0) {
      UserDTO userDto = userMapper.queryBidderUserInfoById(userId);
      userDetailMapper.updateUsageAmountByUserId(userId + "", userDto.getCapacity());
    } else {
      userDetailMapper.updateUsageAmountByUserId(userId + "",
          (Long.parseLong(usageAmount) * 1024 + evidence.getLength()) / 1024 + "");
    }

    messageMapper.insertMessageWithUserId(
        "尊敬的用户，您的证据文件【" + StringUtils.substringBeforeLast(name, ".") + "】已删除成功。", userId);
    LOGGER.info("文件" + name + "彻底删除文件成功!");
    return Result.success("文件" + name + "已被彻底删除");
  }

  // 申请出证 流程可能不对 目前仅有 证据+存证函
  @Override
  public Result downloadEvidencePackage(HttpServletRequest request, HttpServletResponse response,
      String fileId, String phoneCode) throws Exception {
    LOGGER.info("出证请求!");
    LOGGER.info("参数为,fileId:" + fileId + ",phoneCode" + phoneCode);
   /* if (!validate(phoneCode)) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "验证码过期或者验证码有误,请查看后重试!");
    }*/

    Evidence evidence = null;
    evidence = evidenceMapper.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "需要出证的文件不存在!");
    }
    VoucherDTO voucher = voucherService.findByEvidenceId(evidence.getId());
    if (voucher == null) {
      return Result.failed(ErrorCode.NOTARY_BUSINESS_STATUS_INCORRECT, "前置条件不满足不允许出证，请先申请出证函！");
    }

    LOGGER.info("开始处理需要出证请求");
    // 证据主id ticket 与voucher 查询用
    Long evidenceId = evidence.getId();
    List<String> list = new ArrayList<>();
    File evidenceFile = new File(evidence.getUri());
    File voucherFile = new File(voucher.getVoucherUrl());
    if (!evidenceFile.isFile() || !voucherFile.isFile()) {
      LOGGER.info("从cos对象存储中拿");
      tencentCosService.downFile(evidence.getCosUri(), evidence.getUri());
      tencentCosService.downFile(voucher.getCosUri(), voucher.getVoucherUrl());
    }

    LOGGER.info("开始对比hash进行下载");
    String evidenceHash = EncryptionUtils.fileHash(evidenceFile, evidence.getAlg());
    String voucherHash =
        EncryptionUtils.fileHash(new File(voucher.getVoucherUrl()), evidence.getAlg());
    if (!evidenceHash.equals(evidence.getHash()) && !voucherHash.equals(voucher.getHash())) {
      return Result.failed(ErrorCode.FAIL_FILE, "文件被修改打包错误，请重新尝试打包！");
    }
    list.add(evidence.getUri());
    list.add(voucher.getVoucherUrl());

    // 定义路径
    String path = evidence.getUri().substring(0, evidence.getUri().lastIndexOf(File.separator))
        + File.separator;
    String zipPath = path + evidence.getName() + "出证包.zip";
    zipPath = ZIPutils.toZip(list, zipPath, true);

    if (StringUtils.isBlank(zipPath) || !new File(zipPath).isFile()) {
      return Result.failed(ErrorCode.FAIL_FILE, "文件打包错误，请重新打包！");
    }
    LOGGER.info("打包成功，开始提供下载");
    String name = zipPath.substring(zipPath.lastIndexOf(File.separator) + 1);
    String gram = FileUtils.getFileFromLocal(name, zipPath, response, request);
    if (!"true".equals(gram)) {
      Result.failed(ErrorCode.FAIL_FILE, "打包文件下载失败!!");
    }
    return Result.success("打包下载完成");
  }

  // 判断用户是否输入验证码正确
  public boolean validate(String code) {
    LOGGER.info("判断验证是否有效");
    String userId = ContextUtil.getCurrentUserId();
    LOGGER.info("获取到的用户id是" + userId);
    UserDTO bidderUser = bidderUserManage.selectBidderUserInfoById(userId);
    String smsCode = redisService.get(RedisKey.SMS_CODE_JUDSALE, bidderUser.getPhone());
    if (null == smsCode || !smsCode.equals(code) || code == null) {
      return false;
    }
    return true;
  }


  @Override
  public Integer getEvidenceNumByUserId(long userId) {
    return evidenceMapper.getEvidenceNumByUserId(userId);
  }

}

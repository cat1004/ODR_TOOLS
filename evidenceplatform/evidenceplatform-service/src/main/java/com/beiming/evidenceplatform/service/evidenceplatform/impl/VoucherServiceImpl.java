package com.beiming.evidenceplatform.service.evidenceplatform.impl;

import com.alibaba.fastjson.JSONObject;
import com.auth0.jwt.internal.org.apache.commons.lang3.StringUtils;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentCosService;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.EncryptionUtils;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.FileUtils;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.PDFLetterUtils;
import com.beiming.evidenceplatform.dao.VoucherMapper;
import com.beiming.evidenceplatform.domain.UserDetail;
import com.beiming.evidenceplatform.domain.evidenceplatform.ClientType;
import com.beiming.evidenceplatform.domain.evidenceplatform.Evidence;
import com.beiming.evidenceplatform.domain.evidenceplatform.EvidenceType;
import com.beiming.evidenceplatform.domain.evidenceplatform.Voucher;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.evidenceplatform.ClientTypeService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceTypeService;
import com.beiming.evidenceplatform.service.evidenceplatform.VoucherService;
import com.beiming.evidenceplatform.service.usermanage.BidderUserManage;
import com.beiming.evidenceplatform.utils.PdfUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

/** @Author: chen @Description: @Date: Created in 10:51 2018/8/27 @Modified By: */
@Service
public class VoucherServiceImpl implements VoucherService {

  private static final Logger LOGGER = LoggerFactory.getLogger(VoucherServiceImpl.class);

  @Value("${file.storage.tmp.dir}")
  private String path;

  @Autowired
  private VoucherMapper voucherMapper;

  @Resource
  private EvidenceTypeService evidenceTypeService;

  @Resource
  private ClientTypeService clientTypeService;

  @Resource
  private EvidenceService evidenceService;

  @Autowired
  private TencentCosService tencentCosService;

  @Autowired
  private BidderUserManage bidderUserManage;

  @Override
  public VoucherDTO findByVourcher(String voucherId) {
    return voucherMapper.findByVoucherId(voucherId);
  }

  @Override
  public Long saveVoucher(Voucher voucher) {
    return voucherMapper.saveVoucher(voucher.getVoucherUrl(), voucher.getVoucherId(),
        voucher.getEvidence().getId(), voucher.getCosUri(), voucher.getHash());
  }

  @Override
  public void updateData(Voucher voucher) {
    voucherMapper.updateData(voucher);
  }

  @Override
  public Voucher findById(Long id) {
    return voucherMapper.findById(id);
  }

  @Override
  public VoucherDTO findByEvidenceId(Long evidenceId) {
    return voucherMapper.findByEvidenceId(evidenceId);
  }

  @Override
  public void delVoucher(Long evidenceId) {
    voucherMapper.deleteVoucher(evidenceId);
  }

  @Override
  public Result findVouchers(Integer pageNo, Integer pageSize, Long userId, String name,
                             String evidenceType, String startTime, String endTimes, String status, String proofId)
      throws ParseException {
    LOGGER.info("开始处理出证函列表请求");
    LOGGER.info("接收到的参数为:pageNo:" + pageNo + ",pageSize:" + pageSize + ",userId" + userId + ",name" + name
        + ",evidenceType" + evidenceType + ",startTime" + startTime + ",endTimes" + endTimes + ",status" + status + ",proofId" + proofId);

    if (pageNo < 0 || pageSize < 0) {
      Page page = new Page();
      pageNo = page.getPageNo();
      pageSize = page.getPageSize();
    } else {
      PageHelper.startPage(pageNo, pageSize);
    }
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

    LOGGER.info("查询满足条件的信息");
    List<VoucherDTO> voucherList =
        voucherMapper.findData(userId, name, evidenceType, createtime, endtime, status, proofId, clientType);
    List<EvidenceType> evidenceTypes = evidenceTypeService.findAll();
    List<ClientType> clientTypes = clientTypeService.findAll();
    Map<String, String> evidenceTypeMap = new HashMap<>();
    Map<String, String> clientTypeMap = new HashMap<>();
    for (EvidenceType type : evidenceTypes) {
      evidenceTypeMap.put(type.getId().toString(), type.getName());
    }
    for (ClientType type : clientTypes) {
      clientTypeMap.put(type.getId().toString(), type.getName());
    }
    for (VoucherDTO dto : voucherList) {
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

    LOGGER.info("开始封装返回");
    PageInfo result = new PageInfo(voucherList);
    if (voucherList == null || voucherList.size() == 0) {
      return Result.success(voucherList);
    }
    if (result.getPages() < pageNo) {
      return Result.failed(ErrorCode.EMPTY_FILE, "输入的页码过大");
    }
    return Result.success(result);
  }

  // 申请存证函
  @Override
  public Result generateVoucher(String fileId) throws Exception {
    LOGGER.info("开始处理出证函请求,接收到fileId为" + fileId);
    Evidence evidence = null;
    evidence = evidenceService.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "申请存证函的文件不存在!");
    } else if (evidence != null && "1".equals(evidence.getVoucherStatus())) {
      return Result.failed(ErrorCode.NOTARY_BUSINESS_STATUS_INCORRECT, "请勿重复申请!");
    } else {

      LOGGER.info("开始生成出证函");
      Voucher voucher = new Voucher();

      String url = File.separator + evidence.getUserId() + File.separator + evidence.getCreateTime()
          + File.separator + evidence.getFileId() + ".pdf";
      String key = "/" + evidence.getUserId() + "/" + evidence.getCreateTime() + "/"
          + evidence.getFileId() + ".pdf";

      String newFilePath = path + url;
      SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
      String time = format.format(evidence.getCreateTime());

      String voucherUuid = UUID.randomUUID().toString();
      Long userId = Long.parseLong(ContextUtil.getCurrentUserId());
      UserDetail userDetail = bidderUserManage.findByuserId(userId);
      String voucherPath = PDFLetterUtils.generatePDF(newFilePath, evidence.getHash(), time,
          userDetail.getActualName(), voucherUuid, evidence.getName(), evidence.getFileId());

      LOGGER.info("生成完成,上传到腾讯");
      // 上传文件到腾讯云
      JSONObject object = tencentCosService.putObject(key, new File(voucherPath));
      if (object.get("file_status").equals(ErrorCode.SUCCESS)) {
        LOGGER.info("文件上传腾讯云成功");
      } else {
        LOGGER.error("文件上传腾讯云失败！");
      }
      evidence.setVoucherStatus("1");
      evidenceService.updateData(evidence);
      String hash = EncryptionUtils.fileHash(new File(voucherPath), evidence.getAlg());

      // 腾讯对象存储地址
      LOGGER.error("保存数据库！");
      Long aLong = voucherMapper.saveVoucher(voucherPath, voucherUuid, evidence.getId(), key, hash);
      if (aLong != 0L) {
        return Result.success(evidence.getName() + "文件已成功申请存证函");
      }
      return Result.failed(ErrorCode.FAIL_FILE, evidence.getName() + "存证函申请失败!");
    }
  }

  // 申请存证函-调用第三方接口
  @Override
  public Result voucherJd(String fileId) throws Exception {
    LOGGER.info("开始处理第三方出证函请求,接收到fileId为" + fileId);

    Evidence evidence = null;
    evidence = evidenceService.findFileId(fileId);
    if (evidence == null) {
      return Result.failed(ErrorCode.EMPTY_FILE, "申请存证函的文件不存在!");
    } else if (evidence != null && "1".equals(evidence.getVoucherStatus())) {
      return Result.failed(ErrorCode.NOTARY_BUSINESS_STATUS_INCORRECT, "请勿重复申请!");
    } else {
      Voucher voucher = new Voucher();
      String url = File.separator + evidence.getUserId() + File.separator + evidence.getCreateTime()
          + File.separator + evidence.getFileId() + "存证函.pdf";
      String key = "/" + evidence.getUserId() + "/" + evidence.getCreateTime() + "/"
          + evidence.getFileId() + "存证函.pdf";
      String newFilePath = path + url;
      SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHMMss");
      String time = format.format(Calendar.getInstance().getTime());

      String voucherUuid = UUID.randomUUID().toString();
      Long userId = Long.parseLong(ContextUtil.getCurrentUserId());
      UserDetail userDetail = bidderUserManage.findByuserId(userId);

      boolean status = PdfUtil.createPdf(newFilePath, voucherUuid, evidence.getFileId() + "存证函.pdf",
          evidence.getFileId(), userDetail.getActualName(), time, evidence.getHash());
      if (!status) {
        return Result.failed(ErrorCode.FAIL_FILE, "存证函申请失败!");
      }
      // 上传文件到腾讯云
      JSONObject object = tencentCosService.putObject(key, new File(newFilePath));
      if (object.get("file_status").equals(ErrorCode.SUCCESS)) {
        LOGGER.info("文件上传腾讯云成功");
      } else {
        LOGGER.error("文件上传腾讯云失败！");
      }
      evidence.setVoucherStatus("1");
      evidenceService.updateData(evidence);
      String hash = EncryptionUtils.fileHash(new File(newFilePath), evidence.getAlg());
      Long aLong = voucherMapper.saveVoucher(newFilePath, voucherUuid, evidence.getId(), key, hash);
      if (aLong != 0L) {
        return Result.success(evidence.getName() + "文件已成功申请存证函");
      }
      return Result.failed(ErrorCode.FAIL_FILE, evidence.getName() + "存证函申请失败!");
    }
  }
}

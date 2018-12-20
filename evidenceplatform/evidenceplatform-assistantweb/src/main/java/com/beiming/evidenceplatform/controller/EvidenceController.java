package com.beiming.evidenceplatform.controller;

import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.domin.dto.requestdto.MobilePhoneRequestDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.PersonnelService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceService;
import com.beiming.evidenceplatform.service.evidenceplatform.ProofService;
import com.beiming.evidenceplatform.service.evidenceplatform.VoucherService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : chenpeng
 * @date : 2018-08-17 11:51
 */
@Api(value = "证据管理层", tags = {"证据管理控制层"})
@RestController
@Slf4j
@RequestMapping("/evidence")
public class EvidenceController {

  @Autowired
  private EvidenceService evidenceService;

  @Autowired
  private VoucherService voucherService;

  @Autowired
  private PersonnelService personnelService;

  @Autowired
  private ProofService proofService;

  private static final Logger LOGGER = LoggerFactory.getLogger(EvidenceController.class);

  @PostMapping(value = "/")
  @ApiOperation(value = "上传证据", notes = "上传证据文件,文件参数fileName,文件备注说明remarks")
  public Result uploadFiles(MultipartHttpServletRequest request) throws IOException {
    List<MultipartFile> files = request.getFiles("fileName");
    String remarks = request.getParameter("remarks");
    if (files.isEmpty()) {
      return Result.failed(ErrorCode.UNEXCEPTED, "上传文件为空");
    }
    String clientType = "1";
    return evidenceService.uploadFiles(files, remarks, clientType);
  }

  @PostMapping(value = "uploadFilesFromOtherPlatform")
  @ApiOperation(value = "ODR平台上传证据", notes = "上传证据文件,文件参数fileName,文件备注说明remarks")
  public Result uploadFilesFromOtherPlatform(MultipartHttpServletRequest request)
      throws IOException {
    List<MultipartFile> files = request.getFiles("fileName");
    String remarks = request.getParameter("remarks");
    if (files.isEmpty()) {
      return Result.failed(ErrorCode.UNEXCEPTED, "上传文件为空");
    }
    String clientType = "2";
    return evidenceService.uploadFiles(files, remarks, clientType);
  }

  @GetMapping(value = "downloadFile")
  @ApiOperation(value = "下载证据", notes = "请输入文件对应的id进行下载,需要参数fileId")
  public Result downloadFile(HttpServletResponse resp, HttpServletRequest requ,
      @RequestParam("fileId") String fileId, String phoneCode)
      throws IOException, NoSuchAlgorithmException {
    return evidenceService.downFile(resp, requ, fileId, phoneCode);
  }

  @GetMapping(value = "list")
  @ApiOperation(value = "证据列表,回收站列表",
      notes = "必要参数pageNo第几页(从第一页开始),pageSize每页几条,status是否删除(回收站/证据列表),"
          + "</br>其他参数如:name:文件名,evidenceType文件类型(只接受类型的编号),createTime 起始时间,endTime终止时间(格式:2018-08-2901:15:22),"
          + "</br>proofId 存证凭证单编号")
  public Result findEvidence(@RequestParam Integer pageNo, @RequestParam Integer pageSize,
      String name, String evidenceType, String createTime, String endTime, String status,
      String proofId) throws IOException, ParseException {
    String userIdstr = ContextUtil.getCurrentUserId();
    Long userId = new Long(userIdstr);
    return evidenceService.findEvidence(pageNo, pageSize, userId, name, evidenceType, createTime,
        endTime, status, proofId);
  }

  @PostMapping(value = "name")
  @ApiOperation(value = "修改文件名称", notes = "需要参数文件对应id(fileId)与修改后的文件名称(name)")
  public Result rename(@RequestParam String fileId, @RequestParam String name) throws IOException {
    return evidenceService.rename(fileId, name);
  }

  @PostMapping(value = "status")
  @ApiOperation(value = "更新证据状态", notes = "需要参数evidence文件id的编号(fileId)")
  public Result updateStatus(@RequestParam String fileId) throws IOException {
    return evidenceService.updateStatus(fileId);
  }

  @PostMapping(value = "remarks")
  @ApiOperation(value = "更新备注信息", notes = "需要参数evidence文件id的编号(fileId)与备注信息(remarks)")
  public Result updateRemarks(@RequestParam String fileId, @RequestParam String remarks)
      throws IOException {
    return evidenceService.updateRemarks(fileId, remarks);
  }

  @PostMapping(value = "removePermanently")
  @ApiOperation(value = "彻底删除文件", notes = "需要参数evidence文件id的编号(fileId)")
  public Result deletePermanently(@RequestParam String fileId, @RequestParam String phoneCode)
      throws IOException {
    return evidenceService.deletePermanently(fileId, phoneCode);
  }

  // 存证函
  @PostMapping(value = "voucher")
  @ApiOperation(value = "生成存证函", notes = "需要参数evidence文件id的编号(fileId)")
  public Result generateVoucher(@RequestParam String fileId) throws Exception {
    return voucherService.generateVoucher(fileId);
  }

  // 调用吉大正元接口生成pdf存证函
  @PostMapping(value = "voucherJd")
  @ApiOperation(value = "调用吉大正元接口生成pdf存证函", notes = "需要参数evidence文件id的编号(fileId)")
  public Result voucherJd(@RequestParam String fileId) throws Exception {
    return voucherService.voucherJd(fileId);
  }

  // 存证凭证
  @PostMapping(value = "explain")
  @ApiOperation(value = "生成存证凭证", notes = "需要参数evidence文件id的编号(fileId)")
  public Result generateTicket(@RequestParam String fileId) throws Exception {
    return proofService.generateTicket(fileId);
  }

  @GetMapping(value = "evidenceType")
  @ApiOperation(value = "查询所有的evidenceType类型", notes = "不需要任何参数")
  public Result getEvidenceTypes() {
    return evidenceService.getEvidenceTypes();
  }

  @GetMapping(value = "certification")
  @ApiOperation(value = "出征打包下载", notes = "需要参数evidence文件id的编号(fileId)")
  public Result downloadEvidencePackage(HttpServletRequest request, @RequestParam String fileId,
      @RequestParam String phoneCode, HttpServletResponse response) throws Exception {
    return evidenceService.downloadEvidencePackage(request, response, fileId, phoneCode);

  }

  // 存证函列表
  @GetMapping(value = "voucherlist")
  @ApiOperation(value = "存证函列表",
      notes = "必要参数pageNo第几页(从第一页开始),pageSize每页几条,status是否删除(回收站/证据列表),name 文件名称,evidenceType文件类型,"
          + "createTime与endTime起始终止时间(格式:2018-08-2901:15:22),proofId存证凭单号")
  public Result findVouchers(@RequestParam Integer pageNo, @RequestParam Integer pageSize,
      String name, String evidenceType, String createTime, String endTime, String status,
      String proofId) throws IOException, ParseException {
    String userIdstr = ContextUtil.getCurrentUserId();
    Long userId = new Long(userIdstr);
    return voucherService.findVouchers(pageNo, pageSize, userId, name, evidenceType, createTime,
        endTime, status, proofId);
  }

  /**
   * 获取验证码
   */
  @ApiOperation(value = "获取手机验证码", notes = "请求中judJWTToken请填写任意值，后台不做校验")
  @PostMapping("/getPhoneCode")
  public Result getPhoneCode(@RequestBody MobilePhoneRequestDTO dto) {
    personnelService.getPhoneCode(dto);
    return Result.success();
  }
}

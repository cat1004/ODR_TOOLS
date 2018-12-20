package com.beiming.evidenceplatform.service.evidenceplatform.impl;

import com.alibaba.fastjson.JSONObject;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentCosService;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.FileUtils;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.PDFLetterUtils;
import com.beiming.evidenceplatform.dao.ProofMapper;
import com.beiming.evidenceplatform.domain.UserDetail;
import com.beiming.evidenceplatform.domain.evidenceplatform.Evidence;
import com.beiming.evidenceplatform.domain.evidenceplatform.Proof;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.evidenceplatform.ClientTypeService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceService;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceTypeService;
import com.beiming.evidenceplatform.service.evidenceplatform.ProofService;
import com.beiming.evidenceplatform.service.usermanage.BidderUserManage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.UUID;

import javax.annotation.Resource;

/**
 * @Author: chen @Description: @Date: Created in 10:46 2018/8/27 @Modified By:
 */
@Service
public class ProofServiceImpl implements ProofService {

  private static final Logger LOGGER = LoggerFactory.getLogger(ProofServiceImpl.class);

  @Value("${file.storage.tmp.dir}")
  private String path;

  @Resource
  private EvidenceTypeService evidenceTypeService;
  @Resource
  private EvidenceService evidenceService;

  @Resource
  private ClientTypeService clientTypeService;
  @Autowired
  private ProofService proofService;

  @Autowired
  private TencentCosService tencentCosService;

  @Autowired
  private BidderUserManage bidderUserManage;

  @Autowired
  private ProofMapper proofMapper;

  @Override
  public VoucherDTO findByProofId(String proofId) {
    return proofMapper.findByProofId(proofId);
  }

  @Override
  public Long saveProof(Proof proof) {
    return proofMapper.saveProof(
        proof.getProofUrl(), proof.getProofId(), proof.getEvidence().getId(), proof.getCosUri());
  }

  @Override
  public void updateData(Proof proof) {
    proofMapper.updateData(proof);
  }

  @Override
  public VoucherDTO findById(Long id) {
    return proofMapper.findById(id);
  }

  @Override
  public void delProof(Long evidenceId) {
    proofMapper.deleteProof(evidenceId);
  }

  @Override
  public VoucherDTO findByEvidenceId(Long evidenceId) {
    return proofMapper.findByEvidenceId(evidenceId);
  }

  // 存证凭据单
  public Result generateTicket(String evidenceId) throws Exception {
    LOGGER.info("接收到的参数为,evidenceId:" + evidenceId);

    LOGGER.info("开始生成存证凭单");
    Evidence evidence = null;
    evidence = evidenceService.findFileId(evidenceId);
    if (evidence == null) {
      return Result.success("开具存证凭证证明的文件不存在");
    }

    LOGGER.info("查询存证凭单是否存在");
    VoucherDTO proof = proofService.findByEvidenceId(evidence.getId());
    if (proof == null) {
      SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:MM:ss");
      String time = format.format(Calendar.getInstance().getTime());
      String url =
          File.separator
              + evidence.getUserId()
              + File.separator
              + evidence.getCreateTime()
              + File.separator
              + evidence.getFileId()
              + "存证凭单.pdf";
      String key =
          "/"
              + evidence.getUserId()
              + "/"
              + evidence.getCreateTime()
              + "/"
              + evidence.getFileId()
              + "存证凭单.pdf";
      String newFilePath = path + url;
      String type = "未申请";

      LOGGER.info("开始处理凭单相关数据");
      Long userId = Long.parseLong(ContextUtil.getCurrentUserId());
      UserDetail userDetail = bidderUserManage.findByuserId(userId);
      String fileNameType = evidence.getUri().substring(evidence.getUri().lastIndexOf("."));
      String fileName = evidence.getName();
      String proofUuid = UUID.randomUUID().toString();
      String evidenceType =
          evidenceTypeService.findByIdToEvidenceType(Long.valueOf(evidence.getEvidenceType()));
      String clientType =
          clientTypeService.findByIdToClientType(Long.valueOf(evidence.getClientType()));
      String fileSize = FileUtils.getFileSize(evidence.getLength());
      PDFLetterUtils.ticket(
          evidenceId,
          newFilePath,
          userDetail.getActualName(),
          time,
          fileName,
          fileNameType,
          fileSize,
          evidenceType,
          clientType,
          proofUuid,
          evidence.getHash(),
          type);

      LOGGER.info("处理凭单完成开始对凭单进行上传到腾讯处理");
      // 上传文件到腾讯云服务器
      JSONObject object = tencentCosService.putObject(key, new File(newFilePath));
      if (object.get("file_status").equals(ErrorCode.SUCCESS)) {
        LOGGER.info("文件上传腾讯云成功");
      } else {
        LOGGER.error("文件上传腾讯云失败！");
      }
      Proof proofs = new Proof();
      proofs.setProofUrl(newFilePath);
      proofs.setEvidence(evidence);
      proofs.setProofId(proofUuid);
      proofs.setCosUri(key);
      proofService.saveProof(proofs);
      VoucherDTO pf = proofService.findByProofId(proofUuid);

      LOGGER.info("凭单进度完成");
      return Result.success(pf);
    }
    return Result.failed(ErrorCode.FAIL_FILE, "请勿重复申请!");
  }
}

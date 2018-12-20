package com.beiming.evidenceplatform.service.evidenceplatform;

import com.beiming.evidenceplatform.domain.evidenceplatform.Evidence;
import com.beiming.evidenceplatform.helper.Result;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author : chenpeng
 * @date : 2018-08-17 11:49 存证接口
 */
public interface EvidenceService {
  Result uploadFiles(List<MultipartFile> file, String remarks, String clientType); // 上传文件

  Result findEvidence(Integer pageNo, Integer pageSize, Long userId, String name,
      String evidenceType, String createTime, String endTime, String status, String proofId)
      throws ParseException;

  Result rename(String uuid, String name);

  Result updateStatus(String uuid);

  Result deletePermanently(String uuid, String phoneCode);

  Result downFile(HttpServletResponse resp, HttpServletRequest requ, String uuid, String phoneCode)
      throws IOException, NoSuchAlgorithmException;

  Result downloadEvidencePackage(HttpServletRequest request, HttpServletResponse response,
      String uuid, String phoneCode) throws Exception;

  Result updateRemarks(String uuid, String remarks);

  Result getEvidenceTypes();

  Evidence findFileId(String uuid);

  void updateData(Evidence evidence);

  Integer getEvidenceNumByUserId(long userId);
}

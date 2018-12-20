package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.evidenceplatform.Evidence;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.EvidenceDTO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : chenpeng
 * @date : 2018-08-17 11:36
 */
public interface EvidenceMapper {

  void deleteEvidence(@Param("fileId") String fileId);

  Evidence findByUserIdAndName(@Param("name") String name, @Param("userId") Long userId);

  void updateData(Evidence evidence);

  List<EvidenceDTO> findEvidence(@Param("name") String name, @Param("userId") Long userId,
      @Param("evidenceType") String evidenceType, @Param("status") String status,
      @Param("startTime") Long startTime, @Param("endTime") Long endTime,
      @Param("proofId") String proofId, @Param("clientType") String clientType);

  Long saveEvidence(Evidence evidence);

  Evidence findFileId(@Param("fileId") String fileId);

  Integer getEvidenceNumByUserId(@Param("userId") long userId);

  String getEvidenceTotalSizeByUserId(@Param("userId") long userId);
}

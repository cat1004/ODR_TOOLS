package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.evidenceplatform.Proof;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;

import org.apache.ibatis.annotations.Param;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 11:26 2018/8/27
 * @Modified By:
 */
public interface ProofMapper {
  void deleteProof(@Param("evidenceId") Long evidenceId);

  Long saveProof(@Param("proofUrl") String proofUrl,
                 @Param("proofId") String proofId,
                 @Param("evidenceId") Long evidenceId,
                 @Param("cosUri") String cosUri);

  VoucherDTO findByProofId(@Param("proofId") String proofId);

  VoucherDTO findById(@Param("id") Long id);

  void updateData(Proof proof);

  VoucherDTO findByEvidenceId(@Param("evidenceId") Long evidenceId);
}

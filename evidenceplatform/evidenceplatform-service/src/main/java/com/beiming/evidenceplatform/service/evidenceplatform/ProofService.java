package com.beiming.evidenceplatform.service.evidenceplatform;

import com.beiming.evidenceplatform.domain.evidenceplatform.Proof;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;
import com.beiming.evidenceplatform.helper.Result;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 10:46 2018/8/27
 * @Modified By:
 */
public interface ProofService {

  VoucherDTO findByProofId(String proofId);

  Long saveProof(Proof proof);

  void updateData(Proof proof);

  VoucherDTO findById(Long id);


  void delProof(Long evidenceId);

  VoucherDTO findByEvidenceId(Long evidenceId);

  Result generateTicket(String uuid) throws Exception;
}

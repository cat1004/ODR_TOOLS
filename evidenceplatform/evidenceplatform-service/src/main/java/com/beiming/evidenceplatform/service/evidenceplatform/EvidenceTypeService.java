package com.beiming.evidenceplatform.service.evidenceplatform;

import com.beiming.evidenceplatform.domain.evidenceplatform.EvidenceType;

import java.util.List;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 13:58 2018/8/24
 * @Modified By:
 */


public interface EvidenceTypeService {

  List<EvidenceType> findAll();

  String findByIdToEvidenceType(Long id);

}

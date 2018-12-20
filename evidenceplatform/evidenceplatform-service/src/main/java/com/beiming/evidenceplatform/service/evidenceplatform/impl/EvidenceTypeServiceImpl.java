package com.beiming.evidenceplatform.service.evidenceplatform.impl;

import com.beiming.evidenceplatform.dao.EvidenceTypeMapper;
import com.beiming.evidenceplatform.domain.evidenceplatform.EvidenceType;
import com.beiming.evidenceplatform.service.evidenceplatform.EvidenceTypeService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 14:00 2018/8/24
 * @Modified By:
 */
@Service
public class EvidenceTypeServiceImpl implements EvidenceTypeService {

  @Resource
  EvidenceTypeMapper evidenceTypeMapper;

  @Override
  public List<EvidenceType> findAll() {
    return evidenceTypeMapper.findAll();
  }

  @Override
  public String findByIdToEvidenceType(Long id) {
    return evidenceTypeMapper.findByIdToEvidenceType(id);
  }
}

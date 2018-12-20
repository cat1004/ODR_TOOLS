package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.evidenceplatform.EvidenceType;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : chenpeng
 * @date : 2018-08-17 13:47
 */
public interface EvidenceTypeMapper {
  List<EvidenceType> findAll();

  String findByIdToEvidenceType(@Param("id") Long id);
}

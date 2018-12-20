package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.evidenceplatform.ClientType;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : chenpeng
 * @date : 2018-08-17 13:45
 */
public interface ClientTypeMapper {
  List<ClientType> findAll();

  String findByIdToClientType(@Param("id") Long id);
}

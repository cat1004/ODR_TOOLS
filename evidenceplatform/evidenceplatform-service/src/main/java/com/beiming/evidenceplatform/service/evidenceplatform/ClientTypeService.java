package com.beiming.evidenceplatform.service.evidenceplatform;

import com.beiming.evidenceplatform.domain.evidenceplatform.ClientType;

import java.util.List;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 23:14 2018/8/24
 * @Modified By:
 */
public interface ClientTypeService {
  List<ClientType> findAll();

  String findByIdToClientType(Long id);
}

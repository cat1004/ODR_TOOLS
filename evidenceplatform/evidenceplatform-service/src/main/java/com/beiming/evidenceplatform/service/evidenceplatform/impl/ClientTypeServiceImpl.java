package com.beiming.evidenceplatform.service.evidenceplatform.impl;

import com.beiming.evidenceplatform.dao.ClientTypeMapper;
import com.beiming.evidenceplatform.domain.evidenceplatform.ClientType;
import com.beiming.evidenceplatform.service.evidenceplatform.ClientTypeService;

import org.springframework.stereotype.Service;

import java.util.List;

import javax.annotation.Resource;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 23:15 2018/8/24
 * @Modified By:
 */
@Service
public class ClientTypeServiceImpl implements ClientTypeService {

  @Resource
  private ClientTypeMapper clientTypeMapper;

  @Override
  public List<ClientType> findAll() {
    return clientTypeMapper.findAll();
  }

  @Override
  public String findByIdToClientType(Long id) {
    return clientTypeMapper.findByIdToClientType(id);
  }
}

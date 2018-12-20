package com.beiming.evidenceplatform.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.TestMapper;
import com.beiming.evidenceplatform.domain.Test;
import com.beiming.evidenceplatform.service.TestService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TestServiceImpl implements TestService {

  @Autowired
  TestMapper testMapper;

  @Override
  public Test testservice(long id) {
    // TODO Auto-generated method stub
    Test test = new Test();
    test.setId(id);
    Test test2 = testMapper.selectOne(test);
    log.debug("TEST");
    return test2;
  }

}

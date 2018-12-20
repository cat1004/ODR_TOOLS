package com.beiming.evidenceplatform.serviceimpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beiming.evidenceplatform.dao.AssistanterMapper;
import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.dto.responsedto.AssistanterResponseDTO;
import com.beiming.evidenceplatform.service.AssistanterService;

/**
 * @author zhaomx
 * @date 2018年7月4日
 * @description 辅助人员ServiceImpl
 */
@Service
public class AssistanterServiceImpl implements AssistanterService {

  @Autowired
  private AssistanterMapper assistanterDao;


  @Override
  public List<AssistanterResponseDTO> getAllAssistanter() {
    // TODO Auto-generated method stub
    return assistanterDao.queryAllAssistanter();
  }

  @Override
  public Assistanter getAssistanterByPhone(String phone) {
    // TODO Auto-generated method stub
    return assistanterDao.queryAssistanterByPhone(phone);
  }

  @Override
  public AssistanterResponseDTO getAssistanterById(long id) {
    // TODO Auto-generated method stub
    return assistanterDao.queryAssistanterById(id);
  }

  @Override
  public Long addAssistanter(Assistanter assistanter) {
    // TODO Auto-generated method stub
    return assistanterDao.insertAssistanter(assistanter);
  }

  @Override
  public void modifyPhoneById(Assistanter assistanter) {
    // TODO Auto-generated method stub
    assistanterDao.updatePhoneById(assistanter);
  }

  @Override
  public void modifyPasswordById(Assistanter assistanter) {
    // TODO Auto-generated method stub
    assistanterDao.updatePasswordById(assistanter);
  }

  @Override
  public List<AssistanterResponseDTO> getAssistantersByName(String name) {
    // TODO Auto-generated method stub
    return assistanterDao.queryAssistanterByName(name);
  }

  @Override
  public void modifyStatus(Assistanter assistanter) {
    // TODO Auto-generated method stub
    assistanterDao.updateStatus(assistanter);
  }


}

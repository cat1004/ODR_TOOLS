package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.common.utils.CommonsUtils;
import com.beiming.evidenceplatform.dao.CorporeHouseMapper;
import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.dto.CorporeHouseDTO;
import com.beiming.evidenceplatform.service.CorporeHouseService;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author zhangfc
 * @ClassName: CorporeHouseServiceImpl
 * @Description: TODO
 * @date 2018年6月27日 上午9:14:53
 */
@Service
public class CorporeHouseServiceImpl implements CorporeHouseService {

  @Resource
  private CorporeHouseMapper corporeHouseMapper;

  @Override
  public CorporeHouse addCorporeHouse(CorporeHouseDTO corporeHouseDTO) {
    CorporeHouse corporeHouse = new CorporeHouse();
    corporeHouse.setAcreage(corporeHouseDTO.getAcreage() + CommonsUtils.HOUSE_AREA_UNIT);
    corporeHouse.setCorporeId(corporeHouseDTO.getCorporeId());
    corporeHouse.setHouseType(corporeHouseDTO.getHouseType());
    corporeHouse.setCreateTime(new Date());
    corporeHouse.setCreateUser(corporeHouseDTO.getAssistanterId().toString());
    corporeHouse.setUsering(corporeHouseDTO.getUsering());
    corporeHouse.setFloor(corporeHouseDTO.getFloor());
    corporeHouse.setYears(corporeHouseDTO.getYears());
    corporeHouse.setLandNature(corporeHouseDTO.getLandNature());
    corporeHouseMapper.addCorporeHouse(corporeHouse);
    return corporeHouse;
  }
  
  @Override
  public CorporeHouse getCorporeHouseByCorporeId(int corporeId) {
    return corporeHouseMapper.getCorporeHouseByCorporeId(corporeId);
  }

  @Override
  public void updateCorporeHouse(CorporeHouse corporeHouse) {
    corporeHouseMapper.updateByPrimaryKeySelective(corporeHouse);
  }

  @Override
  public Long getSurveyIdByCorporeHouseId(long corporeHouseId) {
    return corporeHouseMapper.getSurveyIdByCorporeHouseId(corporeHouseId);
  }

  @Override
  public String getCorporeName(long corporeHouseId) {
    return corporeHouseMapper.getCorporeName(corporeHouseId);
  }
}

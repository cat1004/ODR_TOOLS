package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.domain.Corpore;
import com.beiming.evidenceplatform.domain.CorporeCityDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeDetailDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeHouseDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeInfoDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestQueryDTO;

import java.util.List;

/**
 * @author yanlulu, fenghouzhi
 * @version 创建时间：2018年6月25日 上午11:51:11 标的物corpore、corpore_house相关事务
 */
public interface CorporeService {

  Corpore getCorporeById(int id);

  CorporeDTO getCorporeDTOById(int id);

  CorporeDetailDTO getCorporeDetailDTOById(Long id);

  int getReservationNumber(Long cphId);

  List<CorporeInfoDTO> getCorporeByType(String typeCode, int pageSize);

  List<CorporeInfoDTO> getCorporesByQueryDto(CorporeRequestQueryDTO corporeRequestQueryDto,
      Page page);

  List<CorporeCityDTO> getCorporeCountByCity();

  List<CorporeInfoDTO> findItemByPage(Page page, CorporeRequestDTO corporeRequestDto);

  /**
   * @Title: addAuction @Description: @param corpore @return @return int @throws
   */
  Long addCorpore(Corpore corpore);

  /**
   * @Title: updateCorpore @Description: @param corpore @return void @throws
   */
  void updateCorpore(CorporeHouseDTO corporeHouseDTO);

  String getCorporeDetail(Long id);

  List<CorporeInfoDTO> findItemMobileByPage(Page page, CorporeRequestDTO corporeRequestDto);

}


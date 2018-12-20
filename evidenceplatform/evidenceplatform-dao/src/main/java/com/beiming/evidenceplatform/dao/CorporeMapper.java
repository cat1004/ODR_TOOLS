package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.Corpore;
import com.beiming.evidenceplatform.domain.CorporeCityDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeDetailDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeInfoDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestQueryDTO;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface CorporeMapper extends Mapper<Corpore> {

  Corpore getCorporeById(int id);

  CorporeDTO getCorporeDTOById(@Param("id") int id);

  CorporeDetailDTO getCorporeDetailDTOById(@Param("id") Long id);

  String getCorporeContentByAdId(@Param("adId") Long adId,
      @Param("noticeType") String noticeType);

  int getReservationNumber(@Param("cphId") Long cphId);

  List<CorporeInfoDTO> getCorporeByType(@Param("typeId") String typeId,
      @Param("pageSize") int pageSize);

  List<CorporeInfoDTO> getCorporesByQueryDto(
      CorporeRequestQueryDTO corporeRequestQueryDto);

  List<String> getCorporePhotoUrlByCphId(@Param("cphId") Long cphId);

  int getCorporeCountOfCourt(@Param("organizationId") Long organizationId);

  List<CorporeCityDTO> getCorporeCountByCity();

  List<CorporeInfoDTO> selectByifPage(
      @Param("corporeRequestDTO") CorporeRequestDTO corporeRequestDto);

  /**
   * @Title: insertCorpore @Description: @param corpore @return @return int @throws
   */
  Long insertCorpore(Corpore corpore);

  List<CorporeInfoDTO> selectByMobileifPage(
      @Param("corporeRequestDTO") CorporeRequestDTO corporeRequestDto);
}

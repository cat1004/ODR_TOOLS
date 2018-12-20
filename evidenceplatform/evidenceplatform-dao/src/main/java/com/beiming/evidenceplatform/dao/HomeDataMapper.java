package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.BaseGroupByMonthDTO;
import com.beiming.evidenceplatform.domain.CorporeCountByMonth;
import com.beiming.evidenceplatform.domin.dto.requestdto.HomeDataRequestDTO;

import java.util.List;

/**
 * @Description: 首页数据总览Mapper
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/6
 */
public interface HomeDataMapper {

  String getEntrustmentSum(HomeDataRequestDTO homeDataRequestDto);

  String getTransactionAmountSum(HomeDataRequestDTO homeDataRequestDto);

  String getDealCorporeSum(HomeDataRequestDTO homeDataRequestDto);

  String getMonthOnMonthBasisForVolume(HomeDataRequestDTO homeDataRequestDto);

  String getSoonStartSum(HomeDataRequestDTO homeDataRequestDto);

  String getRacketSum(HomeDataRequestDTO homeDataRequestDto);

  String getPublicCorporeSum(HomeDataRequestDTO homeDataRequestDto);

  String getOneBeatCount(HomeDataRequestDTO homeDataRequestDto);

  String getSecondBeatCount(HomeDataRequestDTO homeDataRequestDto);

  String getPatCount(HomeDataRequestDTO homeDataRequestDto);

  String getMonthOnMonthBasisForCount(HomeDataRequestDTO homeDataRequestDto);

  String getMonthOnMonthBasisForPublic(HomeDataRequestDTO homeDataRequestDto);

  List<CorporeCountByMonth> getLastYearForMonthCount(HomeDataRequestDTO homeDataRequestDto);

  List<CorporeCountByMonth> getThisYearForMonthCount(HomeDataRequestDTO homeDataRequestDto);

  List<CorporeCountByMonth> getLastYearForMonthPublic(HomeDataRequestDTO homeDataRequestDto);

  List<CorporeCountByMonth> getThisYearForMonthPublic(HomeDataRequestDTO homeDataRequestDto);

  String getAveragePremiumRate(HomeDataRequestDTO homeDataRequestDto);

  List<BaseGroupByMonthDTO> getLastYearGroupByMonthEndPrice(HomeDataRequestDTO homeDataRequestDto);

  List<BaseGroupByMonthDTO> getThisYearGroupByMonthEndPrice(HomeDataRequestDTO homeDataRequestDto);

  List<BaseGroupByMonthDTO> getLastYearGroupByMonthCount(HomeDataRequestDTO homeDataRequestDto);

  List<BaseGroupByMonthDTO> getThisYearGroupByMonthCount(HomeDataRequestDTO homeDataRequestDto);

}

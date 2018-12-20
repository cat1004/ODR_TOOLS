package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.HomeChartDTO;
import com.beiming.evidenceplatform.domain.HomeDataDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.HomeDataRequestDTO;

import java.util.List;

/**
 * @Description: 监督端-数据总览Service
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/6
 */
public interface HomeDataService {

  HomeDataDTO buildHomeDataDTO(HomeDataRequestDTO homeDataRequestDto);

  List<HomeChartDTO> buildChartDataDTO(HomeDataRequestDTO homeDataRequestDto);

}

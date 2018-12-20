package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.common.utils.CommonsUtils;
import com.beiming.evidenceplatform.dao.HomeDataMapper;
import com.beiming.evidenceplatform.domain.BaseGroupByMonthDTO;
import com.beiming.evidenceplatform.domain.CorporeCountByMonth;
import com.beiming.evidenceplatform.domain.HomeChartDTO;
import com.beiming.evidenceplatform.domain.HomeDataDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.HomeDataRequestDTO;
import com.beiming.evidenceplatform.redis.enums.RedisKey;
import com.beiming.evidenceplatform.service.HomeDataService;
import com.beiming.evidenceplatform.service.RedisService;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Description: 监督端-数据总览Service 实现类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/6
 */
@Service
public class HomeDataServiceImpl implements HomeDataService {

  private static final Logger LOGGER = LoggerFactory.getLogger(HomeDataServiceImpl.class);

  private static List<String> monthList = null;

  /**
   * 初始化月份到集合中
   */
  static {
    monthList = new ArrayList<>();
    monthList.add("1");
    monthList.add("2");
    monthList.add("3");
    monthList.add("4");
    monthList.add("5");
    monthList.add("6");
    monthList.add("7");
    monthList.add("8");
    monthList.add("9");
    monthList.add("10");
    monthList.add("11");
    monthList.add("12");
  }

  @Resource
  private HomeDataMapper homeDataMapper;

  @Resource
  private RedisService redisService;

  /**
   * 组装首页数据总览需要的数据
   */
  @Override
  public HomeDataDTO buildHomeDataDTO(HomeDataRequestDTO homeDataRequestDto) {

    HomeDataDTO homeDataDto = null;

    homeDataDto = redisService.get(RedisKey.JUD_HOME_DATA_SHOW);

    if (homeDataDto != null) {
      LOGGER.info("查询Redis找到了:   " + homeDataRequestDto);
      return homeDataDto;
    } else {
      LOGGER.info("查询参数:   " + homeDataRequestDto);

      homeDataDto = new HomeDataDTO();
      LOGGER.info("组装首页总览数据===START");
      String transactionAmountSum = homeDataMapper.getTransactionAmountSum(homeDataRequestDto);
      String entrustmentSum = homeDataMapper.getEntrustmentSum(homeDataRequestDto);
      String dealCorporeSum = homeDataMapper.getDealCorporeSum(homeDataRequestDto);
      String soonStartSum = homeDataMapper.getSoonStartSum(homeDataRequestDto);
      String racketSum = homeDataMapper.getRacketSum(homeDataRequestDto);
      String publicCorporeSum = homeDataMapper.getPublicCorporeSum(homeDataRequestDto);
      String oneBeatCount = homeDataMapper.getOneBeatCount(homeDataRequestDto);
      String secondBeatCount = homeDataMapper.getSecondBeatCount(homeDataRequestDto);
      String patCount = homeDataMapper.getPatCount(homeDataRequestDto);

      String monthOnMonthBasisForVolume = homeDataMapper
          .getMonthOnMonthBasisForVolume(homeDataRequestDto);
      String monthOnMonthBasisForCount = homeDataMapper
          .getMonthOnMonthBasisForCount(homeDataRequestDto);
      String monthOnMonthBasisForPublic = homeDataMapper
          .getMonthOnMonthBasisForPublic(homeDataRequestDto);

      String turnoverRate = getTurnoverRate(dealCorporeSum, entrustmentSum);

      List<CorporeCountByMonth> lastYearCorporeList = homeDataMapper
          .getLastYearForMonthCount(homeDataRequestDto);
      List<CorporeCountByMonth> thisYearCorporeList = homeDataMapper
          .getThisYearForMonthCount(homeDataRequestDto);

      List<CorporeCountByMonth> corporeMonthOnYearDataList = getMonthOnYearData(lastYearCorporeList,
          thisYearCorporeList);

      List<CorporeCountByMonth> lastYearPublicCorporeList = homeDataMapper
          .getLastYearForMonthPublic(homeDataRequestDto);
      List<CorporeCountByMonth> thisYearPublicCorporeList = homeDataMapper
          .getThisYearForMonthPublic(homeDataRequestDto);

      List<CorporeCountByMonth> publicCorporeMonthOnYearDataList = getMonthOnYearData(
          lastYearPublicCorporeList,
          thisYearPublicCorporeList);

      String averagePremiumRate = homeDataMapper.getAveragePremiumRate(homeDataRequestDto);

      homeDataDto.setTransactionAmountSum(transactionAmountSum);
      homeDataDto.setEntrustmentSum(entrustmentSum);
      homeDataDto.setDealCorporeSum(dealCorporeSum);
      homeDataDto.setSoonStartSum(soonStartSum);
      homeDataDto.setRacketSum(racketSum);
      homeDataDto.setPublicCorporeSum(publicCorporeSum);
      homeDataDto.setOneBeatCount(oneBeatCount);
      homeDataDto.setSecondBeatCount(secondBeatCount);
      homeDataDto.setPatCount(patCount);

      homeDataDto.setMonthOnMonthBasisForVolume(monthOnMonthBasisForVolume);
      homeDataDto.setMonthOnMonthBasisForCount(monthOnMonthBasisForCount);
      homeDataDto.setMonthOnMonthBasisForPublic(monthOnMonthBasisForPublic);
      homeDataDto.setMonthOnYearForCount(corporeMonthOnYearDataList);
      homeDataDto.setMonthOnYearForPublic(publicCorporeMonthOnYearDataList);
      homeDataDto.setTurnoverRate(turnoverRate);
      homeDataDto.setAveragePremiumRate(averagePremiumRate);

      /**
       * 设置Redis数据保存的期限为5分钟
       */
      redisService.set(RedisKey.JUD_HOME_DATA_SHOW, homeDataDto, 5, TimeUnit.MINUTES);
      LOGGER.info("组装首页总览数据,保存至Redis===END");
      return homeDataDto;
    }

  }

  /**
   * 组装首页数据总览-图表需要的数据
   */
  @Override
  public List<HomeChartDTO> buildChartDataDTO(HomeDataRequestDTO homeDataRequestDto) {
    List<HomeChartDTO> homeChartDtoList = null;

    homeChartDtoList = redisService.get(RedisKey.JUD_HOME_CHART_DATA_SHOW);

    if (homeChartDtoList != null && !homeChartDtoList.isEmpty()) {
      LOGGER.info("Redis找到了   " + homeChartDtoList);
      return homeChartDtoList;
    } else {
      homeChartDtoList = new ArrayList<>();
      List<BaseGroupByMonthDTO> lastYearGroupByMonthEndPriceList = homeDataMapper
          .getLastYearGroupByMonthEndPrice(homeDataRequestDto);
      List<BaseGroupByMonthDTO> thisYearGroupByMonthEndPrice = homeDataMapper
          .getThisYearGroupByMonthEndPrice(homeDataRequestDto);

      HomeChartDTO priceHomeChartDto = formatChartDataMethod(lastYearGroupByMonthEndPriceList,
          thisYearGroupByMonthEndPrice);

      List<BaseGroupByMonthDTO> lastYearGroupByMonthCountList = homeDataMapper
          .getLastYearGroupByMonthCount(homeDataRequestDto);
      List<BaseGroupByMonthDTO> thisYearGroupByMonthCountList =
          homeDataMapper.getThisYearGroupByMonthCount(homeDataRequestDto);

      HomeChartDTO countHomeChartDto = formatChartDataMethod(lastYearGroupByMonthCountList,
          thisYearGroupByMonthCountList);

      homeChartDtoList.add(priceHomeChartDto);
      homeChartDtoList.add(countHomeChartDto);
      redisService.set(RedisKey.JUD_HOME_CHART_DATA_SHOW, homeChartDtoList, 5, TimeUnit.MINUTES);
      LOGGER.info("组装首页总览数据,保存至Redis===END");
      return homeChartDtoList;
    }
  }

  /**
   * 计算月同比的方法
   */
  private List<CorporeCountByMonth> getMonthOnYearData(List<CorporeCountByMonth> thisYearList,
      List<CorporeCountByMonth> lastYearList) {
    List<CorporeCountByMonth> resultList = new ArrayList<>();

    for (CorporeCountByMonth thisYearCorporeCountByMonth : thisYearList) {
      String monthKey = thisYearCorporeCountByMonth.getMonth();
      String thisYearMonthCount = thisYearCorporeCountByMonth.getCorporeCountByMonth();
      for (CorporeCountByMonth lastYearCorporeCountByMonth : lastYearList) {
        if (monthKey.equals(lastYearCorporeCountByMonth.getMonth())) {
          LOGGER.info("计算月同比==START");
          String lastYearMonthCount = lastYearCorporeCountByMonth.getCorporeCountByMonth();
          String monthOnYearNum = getPercentage(Integer.parseInt(thisYearMonthCount),
              Integer.parseInt(lastYearMonthCount));
          LOGGER.info("计算月同比==END");
          resultList.add(new CorporeCountByMonth(monthKey, monthOnYearNum + "%"));
        }
      }
    }
    LOGGER.info("计算之后的月同比集合长度：    " + resultList.size());
    return resultList;
  }

  /**
   * 计算成交率
   */
  private String getTurnoverRate(String dealCorporeSum, String entrustmentSum) {

    String turnoverRate = null;

    if (CommonsUtils.isNumeric(dealCorporeSum) && CommonsUtils.isNumeric(entrustmentSum)) {
      LOGGER.info("成交标的物总数量：  " + dealCorporeSum);
      LOGGER.info("委托标的物的  " + entrustmentSum);
      Integer dealCorporeSumInt = Integer.parseInt(dealCorporeSum);
      Integer entrustmentSumInt = Integer.parseInt(entrustmentSum);
      turnoverRate = getPercentage(dealCorporeSumInt, entrustmentSumInt);
    } else {
      turnoverRate = "传入的参数不合法，有些是非数字参数！请检查！！";
    }

    return turnoverRate;
  }

  /**
   * 计算百分比的方法
   */
  private String getPercentage(Integer numOne, Integer numTwo) {

    NumberFormat numberFormat = NumberFormat.getInstance();
    // 设置精确到小数点后2位
    numberFormat.setMaximumFractionDigits(2);
    String result = numberFormat.format((float) numOne / (float) numTwo * 100);
    System.out.println("numOne和numTwo的百分比为:" + result + "%");
    return result;

  }

  /**
   * 整理图表需要的数据
   */
  private HomeChartDTO formatChartDataMethod(List<BaseGroupByMonthDTO> lastYearGroupByMonthData,
      List<BaseGroupByMonthDTO> thisYearGroupByMonthData) {
    LOGGER.info("月份List:   " + monthList);

    HomeChartDTO homeChartDto = new HomeChartDTO();
    homeChartDto.setMonthList(monthList);

    List<String> lastYearDataList = new ArrayList<>();
    List<String> thisYearDataList = new ArrayList<>();

    for (int i = 0; i < monthList.size(); i++) {
      String month = monthList.get(i);
      for (BaseGroupByMonthDTO baseGroupByMonthDto : lastYearGroupByMonthData) {
        if (!month.equals(baseGroupByMonthDto.getMonth())) {
          lastYearDataList.add("");
        } else {
          lastYearDataList.add(baseGroupByMonthDto.getGroupByNum());
        }
      }
    }
    homeChartDto.setLastYearData(lastYearDataList);
    LOGGER.info("设置去年的数据完毕！！=====  " + homeChartDto.getLastYearData());

    for (int i = 0; i < monthList.size(); i++) {
      String month = monthList.get(i);
      for (BaseGroupByMonthDTO baseGroupByMonthDto : thisYearGroupByMonthData) {
        if (!month.equals(baseGroupByMonthDto.getMonth())) {
          thisYearDataList.add("");
        } else {
          thisYearDataList.add(baseGroupByMonthDto.getGroupByNum());
        }
      }
    }
    homeChartDto.setLastYearData(thisYearDataList);
    LOGGER.info("设置今年的数据完毕！！=====  " + homeChartDto.getThisYearData());

    return homeChartDto;
  }

}

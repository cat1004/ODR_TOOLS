package com.beiming.evidenceplatform.serviceimpl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.common.utils.CommonsUtils;
import com.beiming.evidenceplatform.dao.CorporeHouseMapper;
import com.beiming.evidenceplatform.dao.CorporeMapper;
import com.beiming.evidenceplatform.dao.PhotoFilesMapper;
import com.beiming.evidenceplatform.domain.Corpore;
import com.beiming.evidenceplatform.domain.CorporeCityDTO;
import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.PhotoFiles;
import com.beiming.evidenceplatform.domain.dto.CorporeDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeDetailDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeHouseDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeInfoDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestQueryDTO;
import com.beiming.evidenceplatform.service.CorporeService;
import com.github.pagehelper.PageHelper;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Description: 标的物Service实现类
 * @Param:
 * @return:
 * @Author: fenghouzhi
 * @Date: 2018/7/9
 */
@Service
public class CorporeServiceImpl implements CorporeService {

  private static final Logger LOGGER = LoggerFactory.getLogger(CorporeServiceImpl.class);

  private static String noticeType = "150000";

  /**
   * Tencent Cos 地址
   */
  @Value("${tencent.cos.url}")
  private String cos;

  @Resource
  private CorporeMapper corporeMapper;

  @Resource
  private PhotoFilesMapper photoFilesMapper;

  @Resource
  private CorporeHouseMapper corporeHouseMapper;

  @Override
  public Corpore getCorporeById(int id) {
    return corporeMapper.getCorporeById(id);
  }

  @Override
  public CorporeDTO getCorporeDTOById(int id) {
    return corporeMapper.getCorporeDTOById(id);
  }

  @Override
  public List<CorporeInfoDTO> findItemByPage(Page page, CorporeRequestDTO corporeRequestDto) {
    LOGGER.info("传入的参数为:   " + page);
    LOGGER.info("传入的参数为:   " + corporeRequestDto);
    PageHelper.startPage(page.getPageNo(), page.getPageSize());
    List<CorporeInfoDTO> list = corporeMapper.selectByifPage(corporeRequestDto);
    return list;
  }

  @Override
  public Long addCorpore(Corpore corpore) {
    return corporeMapper.insertCorpore(corpore);
  }

  @Override
  public void updateCorpore(CorporeHouseDTO corporeHouseDTO) {
    Corpore corpore = new Corpore();
    corpore.setId(corporeHouseDTO.getCorporeId());
    corpore.setOrgId(corporeHouseDTO.getOrgId());
    corpore.setServicePerId(corporeHouseDTO.getServicePerId());
    corpore.setUpdateTime(new Date());
    corpore.setUpdateUser(corporeHouseDTO.getAssistanterId().toString());
    corporeMapper.updateByPrimaryKeySelective(corpore);
  }

  @Override
  public String getCorporeDetail(Long id) {
    CorporeHouse ch = corporeHouseMapper.getCorporeHouseByCorporeId(Integer.parseInt(id + ""));
    CorporeDTO cd = corporeMapper.getCorporeDTOById(Integer.parseInt(id + ""));
    CorporeDetailDTO cdd = corporeMapper.getCorporeDetailDTOById(id);
    List<PhotoFiles> listPhotoFiles = photoFilesMapper
        .getPhotosList(ch.getId(), "corpore_house_id");
    JSONObject result = new JSONObject();
    result.put("CorporeDTO", cd);
    result.put("CorporeDetailDTO", cdd);
    result.put("listPhotoFiles", listPhotoFiles);
    return JSONObject.toJSONString(result, SerializerFeature.WriteMapNullValue);
  }

  @Override
  public List<CorporeInfoDTO> findItemMobileByPage(Page page, CorporeRequestDTO corporeRequestDto) {
    LOGGER.info("传入的page参数为:   " + page);
    LOGGER.info("传入的查询corporeRequestDao参数为:   " + corporeRequestDto);
    PageHelper.startPage(page.getPageNo(), page.getPageSize());
    List<CorporeInfoDTO> list = corporeMapper.selectByMobileifPage(corporeRequestDto);
    for (CorporeInfoDTO cor : list) {
      if (cor.getPhotoUrl() != null) {
        cor.setPhotoUrl(cos + "/" + cor.getPhotoUrl());
      }
    }
    return list;
  }

  /**
   * 根据标的物ID查看标的物详情数据
   */
  @Override
  public CorporeDetailDTO getCorporeDetailDTOById(Long id) {

    LOGGER.info("标的物ID：  " + id);

    CorporeDetailDTO corporeDetailDto = corporeMapper.getCorporeDetailDTOById(id);
    if (!"".equals(corporeDetailDto.getAuctionPrice())
        && corporeDetailDto.getAuctionPrice() != null && !""
        .equals(corporeDetailDto.getAcreage()) && corporeDetailDto.getAcreage() != null) {
      corporeDetailDto = getHourseAveragePrice(corporeDetailDto);
    }

    List<String> photoUrlList = corporeMapper
        .getCorporePhotoUrlByCphId(corporeDetailDto.getCphId());
    if (photoUrlList != null && photoUrlList.size() > 0) {
      for (int i = 0; i < photoUrlList.size(); i++) {
        photoUrlList.set(i, cos + "/" + photoUrlList.get(i));
      }
      corporeDetailDto.setPhotoUrlList(photoUrlList);
    } else {
      LOGGER.info("该标的物未找到图片!!");
    }
    String content = corporeMapper.getCorporeContentByAdId(corporeDetailDto.getAdId(), noticeType);

    corporeDetailDto.setContent(content);
    corporeDetailDto.setReservationNumber(getReservationNumber(corporeDetailDto.getCphId()));
    LOGGER.info("标的物详情：  " + corporeDetailDto);
    return corporeDetailDto;
  }

  /**
   * 根据cphId获取预约人数
   */
  @Override
  public int getReservationNumber(Long cphId) {
    if (cphId != null) {
      LOGGER.info("标的物cphId：  " + cphId);
      return corporeMapper.getReservationNumber(cphId);
    } else {
      LOGGER.info("标的物cphId为null");
      return 0;
    }
  }

  /**
   * 根据标的物类型查询标的物列表
   */
  @Override
  public List<CorporeInfoDTO> getCorporeByType(String typeCode, int pageSize) {
    LOGGER.info("传入的参数为:   " + typeCode + "  " + pageSize);
    List<CorporeInfoDTO> corporeList = corporeMapper.getCorporeByType(typeCode, pageSize);
    if (corporeList != null && corporeList.size() > 0) {
      getReservationNumberForList(corporeList);
      getPhotoUrlByCphId(corporeList);
    }
    LOGGER.info("标的物的个数:    " + corporeList.size());
    return corporeList;
  }

  /**
   * 根据传入的查询对象进行筛选获取标的物列表
   */
  @Override
  public List<CorporeInfoDTO> getCorporesByQueryDto(CorporeRequestQueryDTO corporeRequestQueryDto,
      Page page) {
    LOGGER.info("==== START ====");
    LOGGER.info("传入的查询参数： " + corporeRequestQueryDto + "   " + page);

    if (corporeRequestQueryDto.getCorporeName() != null
        && corporeRequestQueryDto.getCorporeName().length() != 0) {
      corporeRequestQueryDto.setCorporeName(corporeRequestQueryDto.getCorporeName().trim());
    }

    PageHelper.startPage(page.getPageNo(), page.getPageSize());
    List<CorporeInfoDTO> corporeInfoDtoList = corporeMapper
        .getCorporesByQueryDto(corporeRequestQueryDto);
    LOGGER.info("当前查询条件下标的物个数： " + corporeInfoDtoList.size());
    if (corporeInfoDtoList != null && corporeInfoDtoList.size() > 0) {
      getReservationNumberForList(corporeInfoDtoList);
      getPhotoUrlByCphId(corporeInfoDtoList);
    }

    LOGGER.info("==== END ====");
    return corporeInfoDtoList;
  }

  /**
   * 获取有标的物的地级市和拥有标的物的数量
   */
  @Override
  public List<CorporeCityDTO> getCorporeCountByCity() {
    List<CorporeCityDTO> corporeCityDtoList = corporeMapper.getCorporeCountByCity();
    return corporeCityDtoList;
  }

  /**
   * 获取标的物图片组中的第一张图提供列表页面展示
   */
  private List<CorporeInfoDTO> getPhotoUrlByCphId(List<CorporeInfoDTO> corporeInfoDtoList) {
    for (int i = 0; i < corporeInfoDtoList.size(); i++) {
      CorporeInfoDTO corporeInfoDto = corporeInfoDtoList.get(i);
      List<String> photoUrlList = corporeMapper
          .getCorporePhotoUrlByCphId(corporeInfoDto.getCphId());
      LOGGER.info("找到的图片数：  " + photoUrlList.size());
      if (photoUrlList != null && photoUrlList.size() > 0) {
        corporeInfoDto.setPhotoUrl(cos + "/" + photoUrlList.get(0));
      }
    }
    return corporeInfoDtoList;
  }

  /**
   * 计算房屋均价
   */
  private CorporeDetailDTO getHourseAveragePrice(CorporeDetailDTO corporeDetailDto) {
    LOGGER.info("房屋价格：    " + corporeDetailDto.getAuctionPrice());
    LOGGER.info("房屋面积：    " + corporeDetailDto.getAcreage());
    Integer auctionPrice = Integer
        .parseInt(corporeDetailDto.getAuctionPrice());
    Integer acreage = Integer
        .parseInt(corporeDetailDto.getAcreage().split(CommonsUtils.HOUSE_AREA_UNIT_SPLIT)[0]);
    String hourseAveragePrice = auctionPrice / acreage + "";
    corporeDetailDto.setHourseAveragePrice(hourseAveragePrice + CommonsUtils.HOUSE_AVG_PRICE_UNIT);
    return corporeDetailDto;
  }

  /**
   * 传入标的物列表获取每个标的的预约人数
   */
  private void getReservationNumberForList(List<CorporeInfoDTO> corporeList) {
    for (CorporeInfoDTO corporeInfoDto : corporeList) {
      corporeInfoDto
          .setReservationNumber(getReservationNumber(corporeInfoDto.getCphId()));
    }
  }

}


package com.beiming.evidenceplatform.controller;

import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.domain.Area;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.AreaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName AreaController
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Slf4j
@RestController
@Api(value = "地区相关接口", tags = "地区相关接口")
@RequestMapping("/api/pc/assistant")
public class AreaController {

  @Autowired
  private AreaService areaService;

  /**
   * 获取省份
   * @return
   */
  @GetMapping("/areas/province")
  @ApiOperation(value = "PC端-省份列表", notes = "PC端-省份列表")
  Result getProvinces() {
    List<Area> provinceList = areaService.getAllSnameList();
    if (provinceList == null || provinceList.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到省份列表");
    } else {
      log.info("省份个数:-----    " + provinceList.size());
      return Result.success(provinceList);
    }
  }

  /**
   * 获取省份下的城市
   * @param parentId
   * @return
   */
  @GetMapping("/areas/province/{parentId}")
  @ApiOperation(value = "PC端-城市列表", notes = "PC端-省份列表")
  Result getProvinces(@ApiParam("上级id") @PathVariable("parentId") String parentId) {
    List<Area> cityList = areaService.getLowerLevelArea(parentId);
    if (cityList == null || cityList.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到城市列表");
    } else {
      log.info("改省份下的城市个数:-----    " + cityList.size());
      return Result.success(cityList);
    }
  }
}

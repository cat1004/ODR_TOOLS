package com.beiming.evidenceplatform.controller;

import com.alibaba.fastjson.JSONObject;
import com.beiming.evidenceplatform.common.constants.OrgnazationTypeConst;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.Area;
import com.beiming.evidenceplatform.domain.Orgnazation;
import com.beiming.evidenceplatform.domain.dto.responsedto.OrgnazationResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.ServicePerResponseDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.ModifyOrgnazation;
import com.beiming.evidenceplatform.domin.dto.requestdto.OrgnazationListRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.OrgnazationRequestDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.AreaService;
import com.beiming.evidenceplatform.service.OrgnazationService;
import com.beiming.evidenceplatform.service.ServicePerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author zhaomx
 * @date 2018年7月2日
 * @description 法院管理员后台PC端接口
 */
@Slf4j
@RestController
@Api(value = "法院管理员后台PC端接口", tags = "法院管理员后台PC端接口")
@RequestMapping("/api/pc/assistant/orgnazation")
public class OrgnazationController {

  @Autowired
  private OrgnazationService orgService;

  @Autowired
  private AreaService areaService;

  @Autowired
  private ServicePerService servicePerService;

  @PostMapping("/provinces")
  @ApiOperation(value = "省份列表", notes = "省份列表")
  public Result provinces() {
    List<Area> areaList = areaService.getAllSnameList();
    if (areaList == null || areaList.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到省份列表");
    } else {
      return Result.success(areaList);
    }
  }

  @GetMapping("/citys/{provinceId}")
  @ApiOperation(value = "市级列表", notes = "通过省Id查询对应的市级列表")
  public Result citys(@PathVariable("provinceId") String provinceId) {
    List<Area> areaList = areaService.getAllPrefecturalCity(provinceId);
    if (areaList == null || areaList.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到市级列表");
    } else {
      return Result.success(areaList);
    }
  }

  @PostMapping("/getCourtsByAreaIdAndName")
  @ApiOperation(value = "法院列表", notes = "根据地区id及搜索条件查询所有法院")
  public Result getCourtsByAreaIdAndName(@RequestBody OrgnazationListRequestDTO object) {
    log.info("法院列表请求参数  地区id:{},搜索条件:{}", object.getAreaId(), object.getSearch());
    if (object.getAreaId() == null) {
      return Result.failed(ErrorCode.ILLEGAL_PARAMETER, "非法参数");
    } else {
      List<Orgnazation> orgnazationList = orgService.getOrgnazationByAreaIdAndTypeAndName(
          String.valueOf(object.getAreaId()), OrgnazationTypeConst.COURT_TYPE, object.getSearch());
      if (orgnazationList.size() == 0 || orgnazationList == null) {
        return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到该地区下的法院");
      } else {
        ArrayList<OrgnazationResponseDTO> list = new ArrayList<OrgnazationResponseDTO>();
        for (Orgnazation orgnazation : orgnazationList) {
          OrgnazationResponseDTO orgnazationResponseDTO = new OrgnazationResponseDTO();
          orgnazationResponseDTO.setId(orgnazation.getId());
          orgnazationResponseDTO.setAreaId(orgnazation.getAreaId());
          orgnazationResponseDTO.setOrgnazationName(orgnazation.getOrganizationName());
          orgnazationResponseDTO.setCreateTime(orgnazation.getCreateTime());
          Area area = areaService.getArea(Long.valueOf(orgnazation.getAreaId()));
          orgnazationResponseDTO.setCityName(getArea(area).getCityName());
          orgnazationResponseDTO.setProvinceName(getArea(area).getProvinceName());
          list.add(orgnazationResponseDTO);
        }
        log.info("法院列表:{}", JSONObject.toJSON(list));
        return Result.success(list);
      }
    }
  }

  public OrgnazationResponseDTO getArea(Area area) {
    OrgnazationResponseDTO orgnazationResponseDTO = new OrgnazationResponseDTO();
    if (area.getLevel() == 1) {
      orgnazationResponseDTO.setProvinceName(area.getSname());
      return orgnazationResponseDTO;
    } else {
      Area city = areaService.getArea(Long.valueOf(area.getParentId()));
      orgnazationResponseDTO.setCityName(area.getSname());
      orgnazationResponseDTO.setProvinceName(city.getSname());
      return orgnazationResponseDTO;
    }
  }

  @PostMapping("/addCourt")
  @ApiOperation(value = "添加法院", notes = "添加法院")
  public Result addCourt(@RequestBody OrgnazationRequestDTO object) {
    Orgnazation orgnazationByName = orgService.getOrgnazationByName(object.getOrgnaztionName(),
        OrgnazationTypeConst.COURT_TYPE);
    if (orgnazationByName != null) {
      return Result.failed(ErrorCode.ORGNAZATION_NAME_EXITS, "法院已存在");
    } else {
      String adminId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(adminId));
      Orgnazation orgnazation = new Orgnazation();
      orgnazation.setAreaId(String.valueOf(object.getAreaId()));
      orgnazation.setOrganizationName(object.getOrgnaztionName());
      Area area = areaService.getArea(object.getAreaId());
      orgnazation.setOrganizationAddress(area.getLname());
      orgnazation.setAreaLevel(String.valueOf(area.getLevel()));
      orgnazation.setType(OrgnazationTypeConst.COURT_TYPE);
      orgnazation.setCreateUser(admin.getPhone());
      orgService.addOrgnazation(orgnazation);
      log.info("添加法院    法院名称:{},法院地址:{}", object.getOrgnaztionName(), area.getLname());
      return Result.failed(ErrorCode.SUCCESS, "法院添加成功");
    }
  }

  @GetMapping("/getCourtById/{id}")
  @ApiOperation(value = "通过id获取法院", notes = "通过id获取法院")
  public Result getCourtById(@PathVariable("id") long id) {
    Orgnazation orgnazation = orgService.getOrgnazationById(id);
    if (orgnazation == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到法院");
    } else {
      OrgnazationResponseDTO orgnazationResponseDTO = new OrgnazationResponseDTO();
      orgnazationResponseDTO.setId(orgnazation.getId());
      orgnazationResponseDTO.setOrgnazationName(orgnazation.getOrganizationName());
      orgnazationResponseDTO.setAreaId(orgnazation.getAreaId());
      return Result.success(orgnazationResponseDTO);
    }
  }

  @GetMapping("/deleteCourtById/{id}")
  @ApiOperation(value = "删除法院", notes = "通过id删除法院")
  public Result deleteCourtById(@PathVariable("id") long id) {
    Orgnazation orgnazation = orgService.getOrgnazationById(id);
    if (orgnazation == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到法院");
    } else {
      orgService.deleteOrgnazation(id);
      log.info("删除法院:成功   删除法院名称为:{}", orgnazation.getOrganizationName());
      return Result.failed(ErrorCode.SUCCESS, "法院删除成功");
    }
  }

  @PostMapping("/modifyCourt")
  @ApiOperation(value = "修改法院", notes = "修改法院")
  public Result modifyCourt(@RequestBody ModifyOrgnazation object) {
    Orgnazation orgnazationById = orgService.getOrgnazationById(object.getOrgnazationId());
    if (orgnazationById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到法院");
    } else {
      Orgnazation orgnazationByName = orgService.getOrgnazationByName(object.getOrgnaztionName(),
          OrgnazationTypeConst.COURT_TYPE);
      if (orgnazationByName != null) {
        return Result.failed(ErrorCode.ORGNAZATION_NAME_EXITS, "法院已存在");
      } else {
        String adminId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(adminId));
        Orgnazation orgnazation = new Orgnazation();
        orgnazation.setId(object.getOrgnazationId());
        orgnazation.setOrganizationName(object.getOrgnaztionName());
        orgnazation.setAreaId(String.valueOf(object.getAreaId()));
        Area area = areaService.getArea(Long.valueOf(object.getAreaId()));
        orgnazation.setOrganizationAddress(area.getLname());
        orgnazation.setAreaLevel(String.valueOf(area.getLevel()));
        orgnazation.setUpdateUser(admin.getPhone());
        orgService.modifyOrgnazationName(orgnazation);
        log.info("修改法院   名称为:{},地址为:{}", object.getOrgnaztionName(), area.getLname());
        return Result.failed(ErrorCode.SUCCESS, "修改成功");
      }
    }
  }

  @GetMapping("/getOrgnazationList")
  @ApiOperation(value = "查找所有法院", notes = "没有参数")
  public Result getOrgnazationList() {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<Orgnazation> list = orgService.getAllCourt();
    return Result.success(list);
  }

  /**
   * 根据地区获取法院
   * @return
   */
  @GetMapping("/getOrgByAreaId/{areaId}")
  @ApiOperation(value = "根据地区获取法院", notes = "根据地区获取法院")
  public Result getOrgByAreaId(@ApiParam("地区id") @PathVariable("areaId") String areaId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<Orgnazation> list = orgService.getOrgByAreaId(areaId);
    return Result.success(list);
  }

  /**
   * 根据法院获取法官列表
   */
  @GetMapping("/getJudgesByOrg/{orgId}")
  @ApiOperation(value = "根据法院获取法官列表", notes = "根据法院获取法官列表")
  public Result<?> getJudgesByOrg(@ApiParam("法院id") @PathVariable("orgId") long orgId) {
    if (orgId <= 0) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    List<ServicePerResponseDTO> servicePers = servicePerService.getServicePerByOrgId(orgId);
    if (servicePers == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到该法院下的法官信息");
    }
    return Result.success(servicePers);
  }
}

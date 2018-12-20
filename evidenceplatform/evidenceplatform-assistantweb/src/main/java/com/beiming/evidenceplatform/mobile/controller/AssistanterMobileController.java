package com.beiming.evidenceplatform.mobile.controller;

import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentCosService;
import com.beiming.evidenceplatform.common.utils.FileUtil;
import com.beiming.evidenceplatform.common.utils.Java8DateUtil;
import com.beiming.evidenceplatform.common.utils.UploadUtils;
import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.OrderWatch;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;
import com.beiming.evidenceplatform.domain.Orgnazation;
import com.beiming.evidenceplatform.domain.PhotoFiles;
import com.beiming.evidenceplatform.domain.RealCondition;
import com.beiming.evidenceplatform.domain.Survey;
import com.beiming.evidenceplatform.domain.dto.CorporeInfoDTO;
import com.beiming.evidenceplatform.domain.dto.RealConditionDTO;
import com.beiming.evidenceplatform.domain.dto.SurveyDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.PhotoFilesDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.CorporeHouseService;
import com.beiming.evidenceplatform.service.CorporeService;
import com.beiming.evidenceplatform.service.OrderWatchPersonnelService;
import com.beiming.evidenceplatform.service.OrderWatchService;
import com.beiming.evidenceplatform.service.OrgnazationService;
import com.beiming.evidenceplatform.service.PhotoFilesService;
import com.beiming.evidenceplatform.service.RealConditionServiceI;
import com.beiming.evidenceplatform.service.SurveyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
 * @ClassName AssistanterMobileController
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Api(value = "辅助后台小程序端接口", tags = "辅助后台小程序端接口")
@RestController
@RequestMapping("/api/mobile/assistant")
@Slf4j
public class AssistanterMobileController {


  @Autowired
  private CorporeHouseService corporeHouseService;

  @Autowired
  private SurveyService surveyService;

  @Autowired
  private OrderWatchService orderWatchService;

  @Autowired
  private OrderWatchPersonnelService orderWatchPersonnelService;

  @Autowired
  private RealConditionServiceI realConditionServiceI;

  @Autowired
  private PhotoFilesService photoFilesService;

  @Autowired
  private TencentCosService tencentCosService;
  /**
   * 添加勘验记录
   */
  @PostMapping("/auction/addSurvey")
  @ApiOperation(value = "添加勘验记录", notes = "添加勘验记录")
  public Result<?> addSurvey(@RequestBody SurveyDTO surveyDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (surveyDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    Survey survey = new Survey();
    survey.setName(surveyDTO.getName());
    survey.setFinishTime(new Date());
    // survey.setCreateUser(currentUserId);
    survey.setCreateTime(new Date());
    survey.setElecCharges(surveyDTO.getElecCharges());
    survey.setWaterCharges(surveyDTO.getWaterCharges());
    survey.setOccupy(surveyDTO.getOccupy());
    survey.setPropertyFees(surveyDTO.getPropertyFees());
    survey.setTaxes(surveyDTO.getTaxes());
    survey.setRent(surveyDTO.getRent());
    surveyService.addSurvey(survey);
    CorporeHouse corporeHouse = new CorporeHouse();
    corporeHouse.setId(surveyDTO.getCorporeHouseId());
    corporeHouse.setSurveyId(survey.getId());
    corporeHouseService.updateCorporeHouse(corporeHouse);

    PhotoFiles photoFiles = new PhotoFiles();
    PhotoFiles photoFiles1 = null;
    if (surveyDTO.getUrl() != null && !"".equals(surveyDTO.getUrl())) {
      photoFiles.setUrl(surveyDTO.getUrl());
      photoFiles.setCorporeHouseId(survey.getId());
      photoFiles1 = savePhotoFiles(photoFiles);
    }
    Map<String, Object> map = new HashMap<>();
    if (photoFiles1 != null) {
      map.put("photoFiles", photoFiles1);
    }
    map.put("survey", survey);

    return Result.success(map);
  }

  /**
   * 更新勘验记录
   */
  @PostMapping(value = "/auction/updateSurvey")
  @ApiOperation(value = "更新勘验记录", notes = "更新勘验记录")
  public Result<?> updateSurvey(@RequestBody Survey survey) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (survey == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    surveyService.updateSurvey(survey);
    return Result.success();
  }

  /**
   * 根据房产id查询勘验记录
   */
  @GetMapping("/auction/getSurvey/{corporeHouseId}")
  @ApiOperation(value = "根据房产id查询勘验记录", notes = "根据房产id查询勘验记录")
  public Result<?> getSurvey(
      @ApiParam("房产id") @PathVariable("corporeHouseId") String corporeHouseId,
      HttpServletRequest request) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    Long surveyId = corporeHouseService.getSurveyIdByCorporeHouseId(Long.valueOf(corporeHouseId));
    if (surveyId == null) {
      return Result.success();
    }

    Map<String, Object> map  = surveyService.getSurveyDetail(surveyId);
    return Result.success(map);
  }

  /**
   * 获取预约看样人员列表
   */
  @GetMapping("/auction/getOrderWatchList/{corporeHouseId}")
  @ApiOperation(value = "获取预约看样列表", notes = "获取预约看样列表")
  public Result<?> getOrderWatchList(
      @ApiParam("房产id") @PathVariable("corporeHouseId") String corporeHouseId,
      HttpServletRequest request) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (corporeHouseId == null || "".equals(corporeHouseId)) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    List<OrderWatch> orderWatches = orderWatchService.getOrderWatchs(Long.valueOf(corporeHouseId));
    return Result.success(orderWatches);
  }

  /**
   * 获取预约看样人员列表
   * @param orderWatchId
   * @param request
   * @return
   */
  @GetMapping("/auction/getOrderWatchPersonnels/{orderWatchId}")
  @ApiOperation(value = "获取预约看样人员列表", notes = "获取预约看样人员列表")
  public Result<?> getOrderWatchPersonnels(
      @ApiParam("预约看样id") @PathVariable("orderWatchId") String orderWatchId,
      HttpServletRequest request) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (orderWatchId == null || "".equals(orderWatchId)) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    List<OrderWatchPersonnel> orderWatchPersonnels = orderWatchPersonnelService.getOrderWatchPersonnelsByOrderWatch(Long.valueOf(orderWatchId));
    return Result.success(orderWatchPersonnels);
  }

  /**
   * 人员签到
   */
  @PostMapping("/auction/signOrderWatch")
  @ApiOperation(value = "人员签到", notes = "人员签到")
  public Result<?> signOrderWatch(@RequestBody OrderWatchPersonnel orderWatchPersonnel,
      HttpServletRequest request) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (orderWatchPersonnel == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "未接收到参数");
    }
    log.info("小程序人员签到" + orderWatchPersonnel.getOrderName());
    orderWatchPersonnelService.signOrderWatch(orderWatchPersonnel);
    return Result.success();
  }

  /**
   * 获取看样情况记录
   */
  @GetMapping("/auction/getRealConditions/{corporeHouseId}")
  @ApiOperation(value = "获取看样情况记录", notes = "获取看样情况记录")
  public Result<?> getRealConditions(
      @ApiParam("房产id") @PathVariable("corporeHouseId") String corporeHouseId,
      HttpServletRequest request) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<RealCondition> realConditions = realConditionServiceI
        .getRealConditionByCorporeHouseId(Long.valueOf(corporeHouseId));

    return Result.success(realConditions);
  }

  @Autowired
  private CorporeService corporeService;
  /**
   * 条件查询  显示本机构(子机构)以及其他查询条件
   *
   * @param page
   * @param corporeRequestDTO 查询条件
   * @return
   */

  @ApiOperation(value = "小程序标的物信息控制层http get请求", notes = "注意参数")
  @GetMapping("/auction/corporePageBy")
  public Result itemsPage(@ApiParam("page的封装对象") Page page, @ApiParam("CorporeRequestDTO的封装对象") CorporeRequestDTO corporeRequestDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<CorporeInfoDTO> list = corporeService.findItemMobileByPage(page, corporeRequestDTO);
    if (corporeRequestDTO != null && list.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "当前查询条件下没有标的物信息");
    } else {
      return Result.success(list);
    }
  }


  /**
   * 新增或编辑看样现场情况记录
   */
  @PostMapping("/auction/saveRealCondition")
  @ApiOperation(value = "编辑看样现场情况记录", notes = "编辑看样现场情况记录")
  public Result<?> saveRealCondition(@RequestBody RealConditionDTO realConditionDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (realConditionDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "未接收到参数");
    }
    RealCondition realCondition = new RealCondition();
    realCondition.setProblems(realConditionDTO.getProblems());
    realCondition.setOrderTime(Java8DateUtil.getDate(realConditionDTO.getOrderTime()));

    if ("edit".equals(realConditionDTO.getStatus())) {
      realCondition.setId(realConditionDTO.getId());
      realConditionServiceI.updateRealCondition(realCondition);
    }

    realConditionServiceI.saveRealCondition(realCondition);

    PhotoFiles photoFiles = new PhotoFiles();
    PhotoFiles photoFiles1 = null;
    if (realConditionDTO.getUrl() != null && !"".equals(realConditionDTO.getUrl())) {
      photoFiles.setUrl(realConditionDTO.getUrl());
      photoFiles.setCorporeHouseId(realCondition.getId());
      photoFiles1 = savePhotoFiles(photoFiles);
    }
    Map<String, Object> map = new HashMap<>();
    if (photoFiles1 != null) {
      map.put("photoFiles", photoFiles1);
    }
    map.put("realCondition", realCondition);
    return Result.success(map);
  }

  /**
   * 删除文件
   */
  @GetMapping(value = "/auction/deletePhotoFiles/{url}")
  @ApiOperation(value = "删除文件", notes = "删除文件")
  public Result<?> deletePhotoFiles(@ApiParam(value = "key") @PathVariable("url") String url) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (url == null || "".equals(url)) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    PhotoFiles photoFiles = new PhotoFiles();
    photoFiles.setUrl(url);
    photoFiles.setDelFlag("0");
    photoFilesService.updatePhotoFiles(photoFiles);
    tencentCosService.deleteObject(url);
    return Result.success();
  }
  /**
   * 获取看样现场情况记录
   */
  @GetMapping("/auction/getRealCondition/{corporeHouseId}")
  @ApiOperation(value = "获取看样现场情况记录", notes = "获取看样现场情况记录")
  public Result<?> getRealCondition(@ApiParam("房产id") @PathVariable("corporeHouseId") String corporeHouseId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (corporeHouseId == null || "".equals(corporeHouseId)) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "未接受到参数");
    }
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");

    List<RealCondition> realConditions = realConditionServiceI.getRealConditionByCorporeHouseId(Long.valueOf(corporeHouseId));

    if (realConditions == null) {
      return Result.success(getOrderTimes(corporeHouseId));
    }

    List<RealConditionDTO> realConditionDTOS = new ArrayList<>();
    for (int i = 0; i < realConditions.size(); i++) {
      realConditionDTOS.get(i).setId(realConditions.get(i).getId());
      realConditionDTOS.get(i).setOrderTime(sdf.format(realConditions.get(i).getOrderTime()));
      realConditionDTOS.get(i).setProblems(realConditions.get(i).getProblems());
    }

    return Result.success(realConditionDTOS);
  }
  /**
   * 保存图片
   */
  public PhotoFiles savePhotoFiles(PhotoFiles photoFiles) {
    String name = photoFiles.getUrl();
    photoFiles.setName(name.substring(name.lastIndexOf("/") + 1));
    photoFiles.setType(name.substring(name.lastIndexOf(".")));
    photoFilesService.savePhotoFiles(photoFiles);
    return photoFiles;
  }
  /**
   * 上传文件
   *
   * @param
   * @return
   * @throws IOException
   */
  @PostMapping(value = "/auction/uploadFiles")
  @ApiOperation(value = "附件上传", notes = "附件上传")
  public Result<?> uploadFiles(MultipartHttpServletRequest multi, String kinds) throws IOException {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    String url = "";
    List<MultipartFile> files = multi.getFiles("file");
    Map<String, String[]> map = new HashMap<>();
    //表中对应字段url
    String keys[] = new String[files.size()];
    for (MultipartFile file : files) {
      if (file == null) {
        continue;
      }
      String fileName = file.getOriginalFilename();
      String suffix = UploadUtils.getSuffixName(fileName);
      fileName = UploadUtils.reName(fileName);
      if (!UploadUtils.verifyFileName(suffix)) {
        continue;
      }
      File file1 = FileUtil.convert(file);
      // 获取文件的字节数组
      byte[] content = UploadUtils.getContent(file1);
      if (content == null) {
        continue;
      }
      //拼接url
      url = "/" + kinds + "/" + fileName;
      //上传文件到服务器
      tencentCosService.putObject(url, file1);
      // 保存key
      for (int i = 0; i < files.size(); i++) {
        keys[i] = url;
      }
    }
    map.put("https://judstaticbucket-1255773429.cos-website.ap-shanghai.myqcloud.com", keys);
    return Result.success(map);
  }

  /**
   * 获取预约时间列表
   * @param id
   * @return
   */
  public  List<String> getOrderTimes(String id) {
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
    List<Date> orderTimes = orderWatchService.getOrderTimes(Long.valueOf(id));
    List<String> orderTimeList = new ArrayList<>();
    for (int i = 0; i < orderTimes.size(); i++) {
      orderTimeList.add(sdf.format(orderTimes.get(i)));
    }
    return orderTimeList;
  }

  /**
   * 获取图片
   */
  @PostMapping(value = "/auction/getPotoFileUrls")
  @ApiOperation(value = "获取图片", notes = "获取图片")
  public Result<?> getPotoFileUrls(@RequestBody PhotoFilesDTO photoFilesDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (photoFilesDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "未接收到参数");
    }
    PhotoFiles photoFiles = new PhotoFiles();
    String kinds = photoFilesDTO.getKinds();
    long id = Long.valueOf(photoFilesDTO.getId());
    if ("survey".equals(kinds)) {
      photoFiles.setSurveyId(id);
    }
    if ("realCondition".equals(kinds)) {
      photoFiles.setSurveyId(id);
    }
    Map<String, List<String>> map = new HashMap<>();
    List<String> urls = photoFilesService.getPotoFileUrls(photoFiles);
    if (urls == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到相关图片");
    }
    map.put("https://judstaticbucket-1255773429.cos-website.ap-shanghai.myqcloud.com", urls);
    return Result.success(map);
  }

  @Autowired
  private OrgnazationService orgService;

  @GetMapping("/getOrgnazationList")
  @ApiOperation(value = "查找所有法院", notes = "没有参数")
  public Result getOrgnazationList() {
    List<Orgnazation> list = orgService.getAllCourt();
    return Result.success(list);
  }
}

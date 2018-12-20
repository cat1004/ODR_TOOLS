package com.beiming.evidenceplatform.controller;

import com.beiming.evidenceplatform.common.constants.TencentSmsConst;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentCosService;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentSmsService;
import com.beiming.evidenceplatform.common.utils.FileUtil;
import com.beiming.evidenceplatform.common.utils.Java8DateUtil;
import com.beiming.evidenceplatform.common.utils.UploadUtils;
import com.beiming.evidenceplatform.domain.Corpore;
import com.beiming.evidenceplatform.domain.CorporeHouse;
import com.beiming.evidenceplatform.domain.DeliveryPd;
import com.beiming.evidenceplatform.domain.OrderWatch;
import com.beiming.evidenceplatform.domain.OrderWatchPersonnel;
import com.beiming.evidenceplatform.domain.PhotoFiles;
import com.beiming.evidenceplatform.domain.RealCondition;
import com.beiming.evidenceplatform.domain.Recordings;
import com.beiming.evidenceplatform.domain.Survey;
import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeHouseDTO;
import com.beiming.evidenceplatform.domain.dto.CorporeInfoDTO;
import com.beiming.evidenceplatform.domain.dto.OrderWatchDTO;
import com.beiming.evidenceplatform.domain.dto.RealConditionDTO;
import com.beiming.evidenceplatform.domain.dto.RecordingsDTO;
import com.beiming.evidenceplatform.domain.dto.SurveyDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.PhotoFilesDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CorporeRequestDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.AuctionDetailService;
import com.beiming.evidenceplatform.service.AuctionNoticeService;
import com.beiming.evidenceplatform.service.BankInfoService;
import com.beiming.evidenceplatform.service.CorporeHouseService;
import com.beiming.evidenceplatform.service.CorporeService;
import com.beiming.evidenceplatform.service.DeliveryPdServiceI;
import com.beiming.evidenceplatform.service.OrderWatchPersonnelService;
import com.beiming.evidenceplatform.service.OrderWatchService;
import com.beiming.evidenceplatform.service.PhotoFilesService;
import com.beiming.evidenceplatform.service.RealConditionServiceI;
import com.beiming.evidenceplatform.service.RecordingsService;
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
 * @author zhangfc
 */
@Slf4j
@RestController
@Api(value = "辅助后台pc端接口", tags = "辅助后台pc端接口")
@RequestMapping("/api/pc/assistant")
public class AssistanterPcController {

  @Autowired
  private CorporeService corporeService;
  @Autowired
  private CorporeHouseService corporeHouseService;
  @Autowired
  private SurveyService surveyService;
  @Autowired
  private RecordingsService recordingsService;
  @Autowired
  private OrderWatchService orderWatchService;
  @Autowired
  private OrderWatchPersonnelService orderWatchPersonnelService;
  @Autowired
  private RealConditionServiceI realConditionService;
  @Autowired
  private PhotoFilesService photoFilesService;
  @Autowired
  private TencentCosService tencentCosService;
  @Autowired
  private DeliveryPdServiceI deliveryPdServiceI;
  @Autowired
  private AuctionDetailService auctionDetailService;
  @Autowired
  private AuctionNoticeService auctionNoticeService;
  @Autowired
  private BankInfoService bankInfoService;
  @Autowired
  private TencentSmsService tencentSmsService;

  /**
   * 新增标的物
   * @return Result
   */
  @PostMapping("/auction/addCorpore")
  @ApiOperation(value = "新增标的物", notes = "新增标的物")
  public Result<?> addCorpore(@RequestBody Corpore corpore) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (corpore == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    corpore.setCreateTime(new Date());
    corpore.setAssistanterId(Long.valueOf(currentUserId));
    corpore.setCreateUser(currentUserId);
    corporeService.addCorpore(corpore);
    return Result.success(corpore);
  }


  @PostMapping(value = "/auction/addCorporeHouse")
  @ApiOperation(value = "新增标的物详情", notes = "新增标的物详情")
  public Result<?> addCorporeHouse(@RequestBody CorporeHouseDTO corporeHouseDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (corporeHouseDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    corporeService.updateCorpore(corporeHouseDTO);
    CorporeHouse corporeHouse = corporeHouseService.addCorporeHouse(corporeHouseDTO);
    PhotoFiles photoFiles = new PhotoFiles();
    PhotoFiles photoFiles1 = null;
    if (corporeHouseDTO.getUrl() != null) {
      photoFiles.setUrl(corporeHouseDTO.getUrl());
      photoFiles.setCorporeHouseId(corporeHouse.getId());
      photoFiles1 = savePhotoFiles(photoFiles);
    }
    Map<String, Object> map = new HashMap<>();
    if (photoFiles1 != null) {
      map.put("photoFiles", photoFiles1);
    }
    map.put("corporeHouse", corporeHouse);
    return Result.success(map);
  }

  /**
   * 添加勘验情况
   * @return Result<?>
   */
  @PostMapping(value = "/auction/addSurvey")
  @ApiOperation(value = "添加勘验情况", notes = "添加勘验情况")
  public Result<?> addSurvey(@RequestBody SurveyDTO surveydto) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (surveydto == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    Survey survey = new Survey();
    survey.setName(surveydto.getName());
    survey.setFinishTime(new Date());
   // survey.setCreateUser(currentUserId);
    survey.setCreateTime(new Date());
    survey.setElecCharges(surveydto.getElecCharges());
    survey.setWaterCharges(surveydto.getWaterCharges());
    survey.setOccupy(surveydto.getOccupy());
    survey.setPropertyFees(surveydto.getPropertyFees());
    survey.setTaxes(surveydto.getTaxes());
    survey.setRent(surveydto.getRent());
    surveyService.addSurvey(survey);
    CorporeHouse corporeHouse = new CorporeHouse();
    corporeHouse.setId(surveydto.getCorporeHouseId());
    corporeHouse.setSurveyId(survey.getId());
    corporeHouseService.updateCorporeHouse(corporeHouse);
    PhotoFiles photoFiles = new PhotoFiles();
    PhotoFiles photoFiles1 = null;
    if (surveydto.getUrl() != null && !"".equals(surveydto.getUrl())) {
      photoFiles.setUrl(surveydto.getUrl());
      photoFiles.setCorporeHouseId(corporeHouse.getId());
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
   /* String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }*/
    if (survey == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    surveyService.updateSurvey(survey);
    return Result.success();
  }

  /**
   * 添加咨询记录
   * @return Result
   */
  @PostMapping(value = "/auction/addRecording")
  @ApiOperation(value = "添加咨询记录", notes = "添加咨询记录")
  public Result<?> addRecording(@RequestBody RecordingsDTO recordingsDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (recordingsDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    recordingsDTO.setCreateUser(currentUserId);
    Recordings recordings = recordingsService.addRecording(recordingsDTO);
    PhotoFiles photoFiles = new PhotoFiles();
    PhotoFiles photoFiles1 = null;
    if (recordingsDTO.getUrl() != null && !"".equals(recordingsDTO.getUrl())) {
      photoFiles.setUrl(recordingsDTO.getUrl());
      photoFiles.setCorporeHouseId(recordings.getId());
      photoFiles1 = savePhotoFiles(photoFiles);
    }
    Map<String, Object> map = new HashMap<>();
    if (photoFiles1 != null) {
      map.put("photoFiles", photoFiles1);
    }
    map.put("recordings", recordings);
    return Result.success(map);
  }

  /**
   * 编辑咨询记录
   * @return Result
   */
  @PostMapping(value = "/auction/updateReording")
  @ApiOperation(value = "编辑咨询记录", notes = "编辑咨询记录")
  public Result<?> updateReording(@RequestBody Recordings recording) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (recording == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    recording.setUpdateTime(new Date());
    recording.setUpdateUser(currentUserId);
    recordingsService.updateReording(recording);
    return Result.success();
  }

  /**
   * 添加预约看样
   */
  @PostMapping(value = "/auction/orderWatch")
  @ApiOperation(value = "预约看样", notes = "预约看样")
  public Result<?> orderWatch(@RequestBody OrderWatchDTO orderWatchDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (orderWatchDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    orderWatchService.addOrderWatch(orderWatchDTO);
    List<OrderWatchPersonnel> orderWatchPersonnels = orderWatchDTO.getOrderWatchPersonnels();

    String corporeHouseName = corporeHouseService.getCorporeName(orderWatchDTO.getCorporeHouseId());
    for (OrderWatchPersonnel orderWatchPersonnel : orderWatchPersonnels) {
      orderWatchPersonnel.setCreateTime(new Date());
      String phone = orderWatchPersonnel.getOrderPhone();
      String name = orderWatchPersonnel.getOrderName();
      String[] params = new String[6];
      String[] date = orderWatchDTO.getOrderTime().toString().split(" ");
      params[0] = date[0].split("-")[0];
      params[1] = date[0].split("-")[1];
      params[2] = date[0].split("-")[2];
      params[3] = date[1].split(":")[0];
      params[4] = date[1].split(":")[1];
      params[5] = corporeHouseName;
      tencentSmsService
          .sendSms(phone, params, TencentSmsConst.SIGN, TencentSmsConst.ORDER_WATCH_SUCCESS);
      orderWatchPersonnelService.addOrderWatchPersonnel(orderWatchPersonnel);
    }
    return Result.success();
  }

  /**
   * 更新预约看样
   */
  @PostMapping(value = "/auction/updateOrderWatch")
  @ApiOperation(value = "更新预约看样", notes = "更新预约看样")
  public Result<?> updateOrderWatch(@RequestBody OrderWatch orderWatch) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (orderWatch == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    orderWatchService.updateOrderWatch(orderWatch);
    orderWatchPersonnelService.updateOrderWatchPersonnel(orderWatch.getOrderWatchPersonnellist());
    return Result.success();
  }

  /**
   * 删除预约看样人员
   */
  @GetMapping(value = "/auction/deleteOrderPerson/{orderWatchPersonnelId}")
  @ApiOperation(value = "删除预约看样人员", notes = "删除预约看样人员")
  public Result<?> deleteOrderPerson(@ApiParam(value = "预约人员id", required = true) @PathVariable(
      value = "orderWatchPersonnelId") String orderWatchPersonnelId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    orderWatchPersonnelService.deleteOrderPerson(Long.valueOf(orderWatchPersonnelId));
    return Result.success();
  }

  /**
   * 获取预约看样的人员
   */
  @GetMapping(value = "/auction/getOrderWatchPersons/{corporeHouseId}")
  @ApiOperation(value = "获取预约看样的人员", notes = "获取预约看样的人员")
  public Result<?> getOrderWatchPersons(@ApiParam(value = "房产id",
      required = true) @PathVariable(value = "corporeHouseId") String corporeHouseId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<OrderWatchPersonnel> list =
        orderWatchService.getOrderWatchPersonnels(Long.valueOf(corporeHouseId));
    return Result.success(list);
  }

  /**
   * 看样情况现场记录
   */
  @PostMapping(value = "/auction/saveRealCondition")
  @ApiOperation(value = "看样情况现场记录", notes = "看样情况现场记录")
  public Result<?> saveRealCondition(@RequestBody RealConditionDTO realConditionDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (realConditionDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    RealCondition realCondition = new RealCondition();
    Date orderTime = Java8DateUtil.getDate(realConditionDTO.getOrderTime());
    realCondition.setOrderTime(orderTime);
    realCondition.setConsultant(realConditionDTO.getName());
    realCondition.setProblems(realConditionDTO.getProblems());
    realCondition.setCreateTime(new Date());
    realConditionService.saveRealCondition(realCondition);
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
   * 获取预约时间列表
   */
  @GetMapping("/auction/getOrderTimes/{corporeHouseId}")
  @ApiOperation(value = "获取看样现场情况记录", notes = "获取看样现场情况记录")
  public Result<?> getOrderTimes(
      @ApiParam("房产id") @PathVariable("corporeHouseId") String corporeHouseId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-DD HH:MM");
    List<Date> orderTimes = orderWatchService.getOrderTimes(Long.valueOf(corporeHouseId));
    if (orderTimes == null) {
      return Result.failed(ErrorCode.UNEXCEPTED, "未查询到相关预约信息，请先添加预约看样");
    }
    List<String> orderTimeList = new ArrayList<>();
    for (int i = 0; i < orderTimes.size(); i++) {
      orderTimeList.add(sdf.format(orderTimes.get(i)));
    }
    return Result.success(orderTimeList);
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
    String [] keys = new String[files.size()];
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
   * 交割手续办理
   */
  @PostMapping(value = "/auction/saveDelivery")
  @ApiOperation(value = "交割手续办理", notes = "交割手续办理")
  public Result<?> saveDelivery(@RequestBody DeliveryPd deliveryPd) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (deliveryPd == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    deliveryPdServiceI.saveDelivery(deliveryPd);
    return Result.success();
  }

  /**
   * 拍卖详情
   */
  @PostMapping(value = "/auction/saveAuctionDetail")
  @ApiOperation(value = "拍卖详情", notes = "拍卖详情")
  public Result<?> saveAuctionDetail(@RequestBody AuctionDetailDTO auctionDetailDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (auctionDetailDTO == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    long auctionDetailId = auctionDetailService.saveAuctionDetail(auctionDetailDTO);
    auctionNoticeService.saveAuctionNotice(auctionDetailDTO, auctionDetailId);
    bankInfoService.saveBankInfo(auctionDetailDTO, auctionDetailId);
    return Result.success();
  }

  /**
   * 拍品列表 列表分页+筛选+查询
   */
  @GetMapping(value = "/corpore/listBypage")
  @ApiOperation(value = "查询所有的拍品列表", notes = "查询的参数有page封装信息与查询的封装信息CorporeRequestDTO")
  public Result<?> getCorporeList(@ApiParam(value = "page信息") Page page,
      @ApiParam(value = "CorporeRequestDTO信息") CorporeRequestDTO corporeRequestDTO) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<CorporeInfoDTO> list = corporeService.findItemByPage(page, corporeRequestDTO);
    if (corporeRequestDTO != null && list.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "当前查询条件下没有标的物信息");
    } else {
      return Result.success(list);
    }
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
    if ("corporeHouseId".equals(kinds)) {
      photoFiles.setCorporeHouseId(id);
    }
    if ("deliveryPd".equals(kinds)) {
      photoFiles.setDeliveryPdId(id);
    }
    if ("recordings".equals(kinds)) {
      photoFiles.setRecordingsId(id);
    }
    if ("auctionNotice".equals(kinds)) {
      photoFiles.setAuctionNoticeId(id);
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
}

package com.beiming.evidenceplatform.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.beiming.evidenceplatform.common.constants.DictCode;
import com.beiming.evidenceplatform.common.constants.PersonnelType;
import com.beiming.evidenceplatform.common.constants.StatusType;
import com.beiming.evidenceplatform.common.constants.TencentSmsConst;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentSmsService;
import com.beiming.evidenceplatform.common.utils.MD5Util;
import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.Orgnazation;
import com.beiming.evidenceplatform.domain.OrgnazationService;
import com.beiming.evidenceplatform.domain.Personnel;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domain.dto.responsedto.AssistanterResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.OrgnazationResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.ServicePerResponseDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AddAssistantRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AddServicePerRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.ModifyServicePerPassword;
import com.beiming.evidenceplatform.domin.dto.requestdto.ModifyServicePerPhone;
import com.beiming.evidenceplatform.domin.dto.requestdto.ServicePerListRequestDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.AssistanterService;
import com.beiming.evidenceplatform.service.OrgSerService;
import com.beiming.evidenceplatform.service.PersonnelService;
import com.beiming.evidenceplatform.service.ServicePerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaomx
 * @date 2018年6月28日
 * @description 系统管理员后台PC端接口
 */
@Slf4j
@RestController
@Api(value = "系统管理员后台PC端接口", tags = "系统管理员后台PC端接口")
@RequestMapping("/api/pc/assistant/userManagement")
public class UserManagementController {
  @Autowired
  private ServicePerService servicePerService;
  @Autowired
  private OrgSerService orgSerService;
  @Autowired
  private PersonnelService personnelService;
  @Autowired
  private AssistanterService assistanterService;
  @Autowired
  private com.beiming.evidenceplatform.service.OrgnazationService orgnazationService;
  @Autowired
  private TencentSmsService tencentSmsService;

  @PostMapping("/getCourts")
  @ApiOperation(value = "查询所有法院", notes = "查询所有法院")
  public Result getCourts() {
    List<Orgnazation> courts = orgnazationService.getAllCourt();
    ArrayList<OrgnazationResponseDTO> list = new ArrayList<OrgnazationResponseDTO>();
    if (courts.size() == 0 || courts == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有参数");
    } else {
      for (Orgnazation orgnazation : courts) {
        OrgnazationResponseDTO orgnazationResponseDTO = new OrgnazationResponseDTO();
        orgnazationResponseDTO.setId(orgnazation.getId());
        orgnazationResponseDTO.setOrgnazationName(orgnazation.getOrganizationName());
        orgnazationResponseDTO.setAreaId(orgnazation.getAreaId());
        list.add(orgnazationResponseDTO);
      }
      return Result.success(list);
    }
  }

  @PostMapping("/getJudges")
  @ApiOperation(value = "法官列表", notes = "通过法院id(orgnazationId)及搜索条件(search)查询某法院下的所有法官")
  public Result getJudgeByCourtIdAndName(@RequestBody ServicePerListRequestDTO object) {
    log.info("法官列表接收参数. \n法院id:{} \n搜索条件:{}", object.getOrgnazationId(), object.getSearch());
    if (object.getOrgnazationId() == null || "".equals(object.getOrgnazationId())) {
      return Result.failed(ErrorCode.ILLEGAL_PARAMETER, "没有参数");
    } else {
      List<ServicePerResponseDTO> judges = servicePerService.getServicesPerByIdAndCodeAndSearch(
          Long.valueOf(object.getOrgnazationId()), DictCode.JUDGE_CODE, object.getSearch());
      if (judges == null || judges.size() == 0) {
        return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到该法院下的法官信息");
      } else {
        log.info("返回结果:{}", JSONObject.toJSON(judges));
        return Result.success(judges);
      }
    }
  }

  @PostMapping("/addJudge")
  @ApiOperation(value = "添加法官", notes = "添加法官")
  public Result addJudge(@RequestBody AddServicePerRequestDTO param) {
    ServicePer judgeByPhone = servicePerService.getServicePerByPhone(param.getPhone());
    ServicePerResponseDTO servicesByPhone =
        servicePerService.getServicesByPhone(param.getPhone(), DictCode.JUDGE_CODE);
    if (judgeByPhone == null && servicesByPhone == null) {
      String currentUserId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
      ServicePer servicePer = new ServicePer();
      servicePer.setActualName(param.getName());
      servicePer.setIdCard(param.getIdCard());
      servicePer.setPhone(param.getPhone());
      servicePer.setPassword(MD5Util.string2MD5(StatusType.INITIAL_PASSWORD));
      servicePer.setCreateUser(admin.getPhone());
      Long id = servicePerService.addServicePer(servicePer);
      OrgnazationService orgService = new OrgnazationService();
      orgService.setSpmId(id);
      orgService.setOrgId(Long.valueOf(param.getOrgnazationId()));
      orgService.setServiceType(DictCode.JUDGE_CODE);
      orgService.setCreateUser(admin.getPhone());
      orgSerService.addOrgService(orgService);
      Personnel personnel = new Personnel();
      personnel.setRole(DictCode.JUDGE_CODE);
      personnel.setType(PersonnelType.PERSONAL);
      personnel.setUserDetailId(id);
      personnel.setCreateUser(admin.getPhone());
      personnelService.addPersonnel(personnel);
      String[] params = {param.getPhone(), StatusType.INITIAL_PASSWORD};
      JSONObject sendSms = tencentSmsService.sendSms(param.getPhone(), params, TencentSmsConst.SIGN,
          TencentSmsConst.ADD_ADMIN_TEMPL_ID);
      log.info("添加法官   发送短信的手机号码为:{},发送情况:{}", param.getPhone(), sendSms);
      return Result.failed(ErrorCode.SUCCESS, "添加法官成功");
    } else if (servicesByPhone != null) {
      return Result.failed(ErrorCode.MOBILE_EXITS, "法官已存在");
    } else {
      ServicePerResponseDTO servicesByPhone2 =
          servicePerService.getServicesByPhone(param.getPhone(), DictCode.COURT_ADMINISTRATOR_CODE);
      if (servicesByPhone2.getOrgId() == Long.valueOf(param.getOrgnazationId())
          || Long.valueOf(param.getOrgnazationId()).equals(servicesByPhone2.getOrgId())) {
        String currentUserId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
        ServicePer judge = servicePerService.getServicePerByPhone(param.getPhone());
        OrgnazationService orgService = new OrgnazationService();
        orgService.setSpmId(judge.getId());
        orgService.setOrgId(Long.valueOf(param.getOrgnazationId()));
        orgService.setServiceType(DictCode.JUDGE_CODE);
        orgService.setCreateUser(admin.getPhone());
        orgSerService.addOrgService(orgService);
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.JUDGE_CODE);
        personnel.setType(PersonnelType.PERSONAL);
        personnel.setUserDetailId(judge.getId());
        personnel.setUpdateUser(admin.getPhone());
        personnel.setCreateUser(admin.getPhone());
        personnelService.addPersonnel(personnel);
        String[] params = {param.getPhone()};
        JSONObject sendSms = tencentSmsService.sendSms(param.getPhone(), params,
            TencentSmsConst.SIGN, TencentSmsConst.ADD_JUDGE);
        log.info("添加法官   发送短信的手机号码为:{},发送情况:{}", param.getPhone(), sendSms);
        return Result.failed(ErrorCode.SUCCESS, "添加法官成功");
      } else {
        return Result.failed(ErrorCode.ORGNAZATION_NAME_MISMATCHING, "与法院管理员机构不一致");
      }
    }
  }

  @GetMapping(value = "/getJudgeById/{id}")
  @ApiOperation(value = "通过id查询法官", notes = "通过法官id查询法官")
  public Result getJudgeById(@PathVariable(value = "id") long id) {
    log.info("查询法官 接收id为:{}", id);
    ServicePerResponseDTO servicePer = servicePerService.getServicePerById(id, DictCode.JUDGE_CODE);
    if (servicePer != null) {
      return Result.success(servicePer);
    } else {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到该id下的法官");
    }
  };

  @PostMapping("/modifyJudgeOrCourtAdministratorPhone")
  @ApiOperation(value = "修改法官或法院管理员手机号码", notes = "修改法官或法院管理员手机号码")
  public Result modifyPhone(@RequestBody ModifyServicePerPhone object) {
    ServicePer servicePerById = servicePerService.getServicePerById(object.getId());
    if (servicePerById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到该id下的法官");
    } else {
      ServicePer servicePerByPhone = servicePerService.getServicePerByPhone(object.getPhone());
      if (servicePerByPhone != null) {
        return Result.failed(ErrorCode.MOBILE_EXITS, "手机号码已存在");
      } else {
        ServicePer servicePer = new ServicePer();
        servicePer.setId(object.getId());
        servicePer.setPassword(MD5Util.string2MD5(StatusType.INITIAL_PASSWORD));
        servicePer.setPhone(object.getPhone());
        String currentUserId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
        servicePer.setUpdateUser(admin.getPhone());
        servicePerService.updatePhoneById(servicePer);
        String[] params = {object.getPhone(), StatusType.INITIAL_PASSWORD};
        JSONObject sendSms = tencentSmsService.sendSms(object.getPhone(), params,
            TencentSmsConst.SIGN, TencentSmsConst.ADD_ADMIN_TEMPL_ID);
        log.info("修改的手机号/发送短信手机号为:{},短信发送情况:{}", object.getPhone(), sendSms);
        return Result.failed(ErrorCode.SUCCESS, "修改手机号成功");

      }
    }
  }

  @PostMapping("/modifyJudgeOrCourtAdministratorPassword")
  @ApiOperation(value = "修改法官或法院管理员密码", notes = "修改法官或法院管理员密码")
  public Result modifyJudgePassword(@RequestBody ModifyServicePerPassword object) {
    ServicePer servicePerById = servicePerService.getServicePerById(object.getId());
    if (servicePerById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到该id下的法官");
    } else {
      if (object.getPassword() == null || "".equals(object.getPassword())) {
        return Result.failed(ErrorCode.PASSWORD_EMPTY, "密码不能为空");
      } else {
        ServicePer servicePer = new ServicePer();
        servicePer.setPassword(MD5Util.string2MD5(object.getPassword()));
        servicePer.setId(object.getId());
        String currentUserId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
        servicePer.setUpdateUser(admin.getPhone());
        int modify = servicePerService.updatePasswordById(servicePer);
        if (modify == 1) {
          String[] params = {object.getPassword()};
          JSONObject sendSms = tencentSmsService.sendSms(servicePerById.getPhone(), params,
              TencentSmsConst.SIGN, TencentSmsConst.MODIFY_PASSWORD);
          log.info("修改法官或法院管理员的密码为:{},短信发送情况:{}", object.getPassword(), sendSms);
          return Result.failed(ErrorCode.SUCCESS, "修改密码成功");
        } else {
          return Result.failed(ErrorCode.UNEXCEPTED, "修改密码失败");
        }
      }
    }
  }

  @GetMapping("/modifyJudgeStatus/{id}")
  @ApiOperation(value = "修改法官状态", notes = "通过法官id修改法官状态")
  public Result modifyJudgeStatus(@PathVariable(value = "id") long id) {
    ServicePerResponseDTO servicePerById =
        servicePerService.getServicePerById(id, DictCode.JUDGE_CODE);
    if (servicePerById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到法官");
    } else {
      String currentUserId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
      if (servicePerById.getStatus() == StatusType.ON
          || StatusType.ON.equals(servicePerById.getStatus())) {
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.JUDGE_CODE);
        personnel.setStatus(StatusType.OFF);
        personnel.setUpdateUser(admin.getPhone());
        personnel.setUserDetailId(servicePerById.getId());
        personnelService.modifyPersonnel(personnel);
        log.info("当前法官状态为:{}", "禁用");
        return Result.failed(ErrorCode.STATUS_OFF, "禁用成功");
      } else {
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.JUDGE_CODE);
        personnel.setStatus(StatusType.ON);
        personnel.setUpdateUser(admin.getPhone());
        personnel.setUserDetailId(servicePerById.getId());
        personnelService.modifyPersonnel(personnel);
        log.info("当前法官状态为:{}", "开启");
        return Result.failed(ErrorCode.STATUS_ON, "开启成功");
      }
    }
  }

  @PostMapping("/getAssistanters")
  @ApiOperation(value = "辅助人员列表", notes = "查询所有辅助人员")
  public Result getAllAssistanters() {
    List<AssistanterResponseDTO> list = assistanterService.getAllAssistanter();
    if (list.size() == 0 || list == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到辅助人员");
    } else {
      return Result.success(list);
    }
  }

  @PostMapping("/getAssistantersByName")
  @ApiOperation(value = "搜索辅助人员", notes = "通过搜索条件查询辅助人员")
  public Result getAssistantersByName(@RequestParam("name") String name) {
    log.info("搜索辅助人员接收参数为:{}", name);
    if (name == null || "".equals(name)) {
      Result allAssistanter = getAllAssistanters();
      return allAssistanter;
    } else {
      List<AssistanterResponseDTO> list = assistanterService.getAssistantersByName(name);
      if (list == null || list.size() == 0) {
        return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到辅助人员");
      }
      log.info("搜索辅助人员返回结果:{}", JSONObject.toJSON(list));
      return Result.success(list);
    }
  }

  @GetMapping("/getAssistanter/{id}")
  @ApiOperation(value = "根据id查询辅助人员", notes = "根据id查询辅助人员")
  public Result getAssistanter(@PathVariable(value = "id") long id) {
    AssistanterResponseDTO assistanter = assistanterService.getAssistanterById(id);
    if (assistanter == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有该辅助人员");
    } else {
      return Result.success(assistanter);
    }
  }

  @PostMapping("/modifyAssistanterPhone")
  @ApiOperation(value = "修改辅助人员手机号码", notes = "修改辅助人员手机号码")
  public Result modifyAssistanterPhone(@RequestBody ModifyServicePerPhone object) {
    AssistanterResponseDTO assistanterById = assistanterService.getAssistanterById(object.getId());
    if (assistanterById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "辅助人员不存在");
    } else {
      Assistanter assistanterByPhone = assistanterService.getAssistanterByPhone(object.getPhone());
      if (assistanterByPhone != null) {
        return Result.failed(ErrorCode.MOBILE_EXITS, "手机号码已存在");
      } else {
        String currentUserId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
        Assistanter assistanter = new Assistanter();
        assistanter.setId(object.getId());
        assistanter.setPhone(object.getPhone());
        assistanter.setPassword(MD5Util.string2MD5(StatusType.INITIAL_PASSWORD));
        assistanter.setUpdateUser(admin.getPhone());
        assistanterService.modifyPhoneById(assistanter);
        String[] params = {object.getPhone(), StatusType.INITIAL_PASSWORD};
        JSONObject sendSms = tencentSmsService.sendSms(object.getPhone(), params,
            TencentSmsConst.SIGN, TencentSmsConst.ADD_ADMIN_TEMPL_ID);
        log.info("修改辅助人员的手机号码为:{},短信发送情况:{}", object.getPhone(), sendSms);
        return Result.failed(ErrorCode.SUCCESS, "修改手机号成功");
      }
    }
  }

  @PostMapping("/modifyAssistanterPassword")
  @ApiOperation(value = "修改辅助人员密码", notes = "修改辅助人员密码")
  public Result modifyAssistanterPassword(@RequestBody ModifyServicePerPassword object) {
    AssistanterResponseDTO assistanterById = assistanterService.getAssistanterById(object.getId());
    if (assistanterById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "辅助人员不存在");
    } else {
      if (object.getPassword() == null || "".equals(object.getPassword())) {
        return Result.failed(ErrorCode.PASSWORD_EMPTY, "密码不能为空");
      } else {
        String currentUserId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
        Assistanter assistanter = new Assistanter();
        assistanter.setPassword(MD5Util.string2MD5(object.getPassword()));
        assistanter.setId(object.getId());
        assistanter.setUpdateUser(admin.getPhone());
        assistanterService.modifyPasswordById(assistanter);
        String[] params = {object.getPassword()};
        JSONObject sendSms = tencentSmsService.sendSms(assistanterById.getPhone(), params,
            TencentSmsConst.SIGN, TencentSmsConst.MODIFY_PASSWORD);
        log.info("修改辅助人员密码为:{},短信发送情况:{}", object.getPassword(), sendSms);
        return Result.failed(ErrorCode.SUCCESS, "修改密码成功");
      }
    }
  }

  @GetMapping("/modifyAssistanterStatus/{id}")
  @ApiOperation(value = "修改辅助人员状态", notes = "通过辅助人员id修改状态")
  public Result modifyAssistanterStatus(@PathVariable(value = "id") long id) {
    AssistanterResponseDTO assistanterDTO = assistanterService.getAssistanterById(id);
    if (assistanterDTO == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "辅助人员不存在");
    } else {
      String currentUserId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
      if (assistanterDTO.getStatus() == StatusType.ON
          || StatusType.ON.equals(assistanterDTO.getStatus())) {
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.AUXILIARY_PERSON_CODE);
        personnel.setStatus(StatusType.OFF);
        personnel.setUpdateUser(admin.getPhone());
        personnel.setUserDetailId(assistanterDTO.getId());
        personnelService.modifyPersonnel(personnel);
        log.info("当前辅助人员状态为:{}", "禁用");
        return Result.failed(ErrorCode.STATUS_OFF, "禁用成功");
      } else {
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.AUXILIARY_PERSON_CODE);
        personnel.setStatus(StatusType.ON);
        personnel.setUpdateUser(admin.getPhone());
        personnel.setUserDetailId(assistanterDTO.getId());
        personnelService.modifyPersonnel(personnel);
        log.info("当前辅助人员状态为:{}", "开启");
        return Result.failed(ErrorCode.STATUS_ON, "开启成功");
      }
    }
  }

  @PostMapping("/addAssistanter")
  @ApiOperation(value = "添加辅助人员", notes = "添加辅助人员")
  public Result addAssistanter(@RequestBody AddAssistantRequestDTO object) {
    Assistanter assistanter1 = assistanterService.getAssistanterByPhone(object.getPhone());
    if (assistanter1 != null) {
      return Result.failed(ErrorCode.MOBILE_EXITS, "手机号码已存在");
    } else {
      String currentUserId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
      Assistanter assistanter = new Assistanter();
      assistanter.setActualName(object.getName());
      assistanter.setIdCard(object.getIdCard());
      assistanter.setPhone(object.getPhone());
      assistanter.setPassword(MD5Util.string2MD5(StatusType.INITIAL_PASSWORD));
      assistanter.setCreateUser(admin.getPhone());
      Long id = assistanterService.addAssistanter(assistanter);
      Personnel personnel = new Personnel();
      personnel.setRole(DictCode.AUXILIARY_PERSON_CODE);
      personnel.setType(PersonnelType.PERSONAL);
      personnel.setUserDetailId(id);
      personnel.setCreateUser(admin.getPhone());
      personnelService.addPersonnel(personnel);
      String[] params = {object.getPhone(), StatusType.INITIAL_PASSWORD};
      JSONObject sendSms = tencentSmsService.sendSms(object.getPhone(), params,
          TencentSmsConst.SIGN, TencentSmsConst.ADD_ADMIN_TEMPL_ID);
      log.info("添加辅助人员   发送短信的手机号码为:{},发送情况:{}", object.getPhone(), sendSms);
      return Result.failed(ErrorCode.SUCCESS, "添加辅助人员成功");
    }
  }

  /**
   * @description 通过手机号查询对应的辅助人员
   */
  public Assistanter getAssistanterByPhone(String phone) {
    Assistanter assistanter = assistanterService.getAssistanterByPhone(phone);
    return assistanter;
  }

  @PostMapping("/addCourtAdministrator")
  @ApiOperation(value = "添加法院管理员", notes = "添加法院管理员")
  public Result addCourtAdministrator(@RequestBody AddServicePerRequestDTO object) {
    ServicePerResponseDTO servicesByPhone =
        servicePerService.getServicesByPhone(object.getPhone(), DictCode.COURT_ADMINISTRATOR_CODE);
    ServicePer servicePer1 = servicePerService.getServicePerByPhone(object.getPhone());
    if (servicePer1 == null && servicesByPhone == null) {
      String currentUserId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
      ServicePer servicePer = new ServicePer();
      servicePer.setActualName(object.getName());
      servicePer.setIdCard(object.getIdCard());
      servicePer.setPhone(object.getPhone());
      servicePer.setPassword(MD5Util.string2MD5(StatusType.INITIAL_PASSWORD));
      servicePer.setCreateUser(admin.getPhone());
      Long id = servicePerService.addServicePer(servicePer);
      OrgnazationService orgService = new OrgnazationService();
      orgService.setSpmId(id);
      orgService.setOrgId(Long.valueOf(object.getOrgnazationId()));
      orgService.setServiceType(DictCode.COURT_ADMINISTRATOR_CODE);
      orgService.setCreateUser(admin.getPhone());
      orgSerService.addOrgService(orgService);
      Personnel personnel = new Personnel();
      personnel.setRole(DictCode.COURT_ADMINISTRATOR_CODE);
      personnel.setType(PersonnelType.PERSONAL);
      personnel.setUserDetailId(id);
      personnel.setCreateUser(admin.getPhone());
      personnelService.addPersonnel(personnel);
      String[] params = {object.getPhone(), StatusType.INITIAL_PASSWORD};
      JSONObject sendSms = tencentSmsService.sendSms(object.getPhone(), params,
          TencentSmsConst.SIGN, TencentSmsConst.ADD_ADMIN_TEMPL_ID);
      log.info("添加辅助人员   发送短信的手机号码为:{},发送情况:{}", object.getPhone(), sendSms);
      return Result.failed(ErrorCode.SUCCESS, "添加法院管理员成功");
    } else if (servicesByPhone != null) {
      return Result.failed(ErrorCode.MOBILE_EXITS, "法院管理员已存在");
    } else {
      ServicePerResponseDTO servicesByPhone2 =
          servicePerService.getServicesByPhone(servicePer1.getPhone(), DictCode.JUDGE_CODE);
      if (servicesByPhone2.getOrgId() == Long.valueOf(object.getOrgnazationId())
          || Long.valueOf(object.getOrgnazationId()).equals(servicesByPhone2.getOrgId())) {
        String currentUserId = ContextUtil.getCurrentUserId();
        Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
        ServicePer courtAdministrator = servicePerService.getServicePerByPhone(object.getPhone());
        OrgnazationService orgService = new OrgnazationService();
        orgService.setSpmId(courtAdministrator.getId());
        orgService.setOrgId(Long.valueOf(object.getOrgnazationId()));
        orgService.setServiceType(DictCode.COURT_ADMINISTRATOR_CODE);
        orgService.setCreateUser(admin.getPhone());
        orgSerService.addOrgService(orgService);
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.COURT_ADMINISTRATOR_CODE);
        personnel.setType(PersonnelType.PERSONAL);
        personnel.setUserDetailId(courtAdministrator.getId());
        personnel.setCreateUser(admin.getPhone());
        personnelService.addPersonnel(personnel);
        String[] params = {object.getPhone()};
        JSONObject sendSms = tencentSmsService.sendSms(object.getPhone(), params,
            TencentSmsConst.SIGN, TencentSmsConst.ADD_COURT_ADMINSTRATOR);
        log.info("添加辅助人员   发送短信的手机号码为:{},发送情况:{}", object.getPhone(), sendSms);
        return Result.failed(ErrorCode.SUCCESS, "添加法院管理员成功");
      } else {
        return Result.failed(ErrorCode.ORGNAZATION_NAME_MISMATCHING, "与法官机构不一致");
      }
    }
  }

  @PostMapping("/getCourtAdministrators")
  @ApiOperation(value = "法院管理员列表", notes = "通过法院id(orgnazationId)及搜索关键字(search)查询法院管理员")
  public Result getCourtAdministrators(@RequestBody ServicePerListRequestDTO object) {
    log.info("法院管理员列表接收参数. \n法院id:{} \n搜索条件:{}", object.getOrgnazationId(), object.getSearch());
    if (object.getOrgnazationId() == null || "".equals(object.getOrgnazationId())) {
      return Result.failed(ErrorCode.ILLEGAL_PARAMETER, "没有id参数");
    } else {
      List<ServicePerResponseDTO> judges = servicePerService.getServicesPerByIdAndCodeAndSearch(
          Long.valueOf(object.getOrgnazationId()), DictCode.COURT_ADMINISTRATOR_CODE,
          object.getSearch());
      if (judges == null || judges.size() == 0) {
        return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到该法院下的法官信息");
      } else {
        log.info("返回结果:{}", JSONObject.toJSON(judges));
        return Result.success(judges);
      }
    }
  }

  @GetMapping(value = "/getCourtAdministratorById/{id}")
  @ApiOperation(value = "查询一个法院管理员", notes = "通过id查询法院管理员")
  public Result getCourtAdministratorById(@PathVariable(value = "id") long id) {
    ServicePerResponseDTO servicePer =
        servicePerService.getServicePerById(id, DictCode.COURT_ADMINISTRATOR_CODE);
    if (servicePer != null) {
      return Result.success(servicePer);
    } else {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到该id下的法院管理员");
    }
  };

  @GetMapping("/modifyCourtAdministratorStatus/{id}")
  @ApiOperation(value = "修改法院管理员状态", notes = "通过法院管理员id修改状态")
  public Result modifyCourtAdministratorStatus(@PathVariable(value = "id") long id) {
    ServicePerResponseDTO servicePerById =
        servicePerService.getServicePerById(id, DictCode.COURT_ADMINISTRATOR_CODE);
    if (servicePerById == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "没有查询到法院管理员");
    } else {
      String currentUserId = ContextUtil.getCurrentUserId();
      Admintor admin = servicePerService.getAdmintorById(Long.valueOf(currentUserId));
      if (servicePerById.getStatus() == StatusType.ON
          || StatusType.ON.equals(servicePerById.getStatus())) {
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.COURT_ADMINISTRATOR_CODE);
        personnel.setStatus(StatusType.OFF);
        personnel.setUpdateUser(admin.getPhone());
        personnel.setUserDetailId(servicePerById.getId());
        personnelService.modifyPersonnel(personnel);
        log.info("当前法院管理员状态为:禁用");
        return Result.failed(ErrorCode.STATUS_OFF, "禁用成功");
      } else {
        Personnel personnel = new Personnel();
        personnel.setRole(DictCode.COURT_ADMINISTRATOR_CODE);
        personnel.setStatus(StatusType.ON);
        personnel.setUpdateUser(admin.getPhone());
        personnel.setUserDetailId(servicePerById.getId());
        personnelService.modifyPersonnel(personnel);
        log.info("当前法院管理员状态为:开启");
        return Result.failed(ErrorCode.STATUS_ON, "开启成功");
      }
    }
  }
}

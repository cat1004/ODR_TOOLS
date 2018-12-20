package com.beiming.evidenceplatform.controller;

import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.utils.MD5Util;
import com.beiming.evidenceplatform.domain.Admintor;
import com.beiming.evidenceplatform.domain.Assistanter;
import com.beiming.evidenceplatform.domain.ServicePer;
import com.beiming.evidenceplatform.domain.dto.responsedto.AssistanterUserDetailVO;
import com.beiming.evidenceplatform.domain.dto.responsedto.LoginInfoResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.LoginTokenResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.RefreshTokenResponseDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistUserLoginDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistantTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssistanterForgetPwdDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.AssisttanterChangePwdDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CourtManageTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.CourtManageUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.MobilePhoneRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.SystemTokenUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.SystemUserLoginDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.redis.enums.RedisKey;
import com.beiming.evidenceplatform.security.TokenGenerator;
import com.beiming.evidenceplatform.service.PersonnelService;
import com.beiming.evidenceplatform.service.RedisService;
import com.beiming.evidenceplatform.service.usermanage.AssitantUserManage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xiaoqiang on 16-5-30.
 *
 * @ProjectName:  jud 
 * @Package:    com.beiming.jud.controller  
 * @Description:  辅助用户权限管理接口    
 * @CreateUser:   anxiaoqiang   
 * @CreateDate:   2018/6/25 15:20    
 * @UpdateRemark: 说明本次修改内容  
 * @Version:      [v1.0] 
 */
@Api(value = "司法辅助竞拍者前端和小程序控制层", tags = {"司法辅助用户管理控制层"})
@RestController
@RequestMapping("auth/assistant/permission")
@Slf4j
public class AssistantPermisssionController {

  @Autowired
  private AssitantUserManage assitantUserManage;

  @Autowired
  private TokenGenerator tokenGenerator;

  @Autowired
  private PersonnelService personnelService;

  @Autowired
  private RedisService redisService;

  /**
   * 司法拍卖辅助后台辅助人员登录
   */
  @ApiOperation(value = "司法拍卖辅助后台辅助人员登录接口", notes = "post请求")
  @PostMapping("/assistLogin")
  public Result assistLogin(@RequestBody AssistUserLoginDTO assistUserLoginDTO) {

    if (null == assistUserLoginDTO) {
      return Result.failed(ErrorCode.MOBILE_PHONE_IS_EMPTY, "登录信息不完整");
    }
    String md5Password = MD5Util.string2MD5(assistUserLoginDTO.getPassword());

    try {
      log.info("辅助人员用户名(手机号)密码登录。\n手机号：{} \n密码：{}", assistUserLoginDTO.getPhone(), md5Password);
      AssistantTokenUserDTO assistantTokenUserDTO =
          assitantUserManage.assitantUserLogin(assistUserLoginDTO.getPhone(), md5Password);
      if (null == assistantTokenUserDTO) {
        return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户登录失败");
      }
      if (assistantTokenUserDTO.isStatusIsBanned()) {
        return Result.failed(ErrorCode.USER_IS_BANNED, "用户没有权限或已被管理员禁用，请联系管理员");
      }
      // 生成token传入参数对象
      LoginInfoResponseDTO loginInfoResponseDTO = tokenGenerator.convertUserDetail2TokenInfo(
          assistantTokenUserDTO.getUserId(), assistantTokenUserDTO.getActualName(),
          assistantTokenUserDTO.getSocialIdentify(), assistantTokenUserDTO.getTypeList());
      loginInfoResponseDTO.setHeadImageUrl(assistantTokenUserDTO.getHeadImageUrl());
      // 生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO =
          tokenGenerator.generateLoginToken(loginInfoResponseDTO);
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("辅助人员用户名(手机号)密码登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户登录失败");
    }
  }

  /**
   * 司法拍卖用户管理后台 系统管理员登录
   */
  @ApiOperation(value = "司法拍卖用户管理后台系统管理员登录", notes = "post请求")
  @PostMapping("/systemLogin")
  public Result systemLogin(@RequestBody SystemUserLoginDTO systemUserLoginDTO) {

    if (null == systemUserLoginDTO) {
      return Result.failed(ErrorCode.MOBILE_PHONE_IS_EMPTY, "登录信息不完整");
    }
    String md5Password = MD5Util.string2MD5(systemUserLoginDTO.getPassword());
    LoginTokenResponseDTO loginTokenResponseDTO = null;

    try {
      log.info("系统管理员用户名(手机号)密码登录。\n手机号：{} \n密码：{}", systemUserLoginDTO.getPhone(), md5Password);
//      SystemTokenUserDTO systemTokenUserDTO =
//          assitantUserManage.systemUserLogin(systemUserLoginDTO.getPhone(), md5Password);
//      
      
      SystemTokenUserDTO systemTokenUserDTO = new SystemTokenUserDTO();
      List<String> rowList = new ArrayList();
      systemTokenUserDTO.setUserId(1L);
      systemTokenUserDTO.setActualName("test");
      systemTokenUserDTO.setSocialIdentify(SecurityConst.ROLE_ROW_PERSONAL);
      rowList.add(SecurityConst.ROLE_SYSADMIN);
      systemTokenUserDTO.setTypeList(rowList);
        
      if (null == systemTokenUserDTO) {
        return Result.failed(ErrorCode.USER_NOT_EXISTS, "用户密码错误或用户不存在");
      }
      // 生成token传入参数对象
      LoginInfoResponseDTO loginInfoResponseDTO = tokenGenerator.convertUserDetail2TokenInfo(
          systemTokenUserDTO.getUserId(), systemTokenUserDTO.getActualName(),
          systemTokenUserDTO.getSocialIdentify(), systemTokenUserDTO.getTypeList());
      loginInfoResponseDTO.setHeadImageUrl(systemTokenUserDTO.getHeadImageUrl());
      // 生成token封装对象
      loginTokenResponseDTO = tokenGenerator.generateLoginToken(loginInfoResponseDTO);

      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("系统管理员用户名(手机号)密码登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户登录失败");
    }
  }

  /**
   * 司法拍卖用户管理后台 法院管理员登录
   */
  @ApiOperation(value = "司法拍卖用户管理后台系统管理员登录", notes = "post请求")
  @PostMapping("/courtManageLogin")
  public Result courtManageLogin(@RequestBody CourtManageUserDTO courtManageUserDTO) {

    if (null == courtManageUserDTO) {
      return Result.failed(ErrorCode.MOBILE_PHONE_IS_EMPTY, "登录信息不完整");
    }
    String md5Password = MD5Util.string2MD5(courtManageUserDTO.getPassword());

    try {
      log.info("系统管理员户名(手机号)密码登录。\n手机号：{} \n密码：{}", courtManageUserDTO.getPhone(), md5Password);
      CourtManageTokenUserDTO courtManageTokenUserDTO =
          assitantUserManage.courtManageUserLogin(courtManageUserDTO.getPhone(), md5Password);
      if (null == courtManageTokenUserDTO) {
        return Result.failed(ErrorCode.USER_NOT_EXISTS, "用户名密码错误或用户不存在");
      }
      if (courtManageTokenUserDTO.isStatusIsBanned()) {
        return Result.failed(ErrorCode.USER_IS_BANNED, "用户已被禁用，请联系管理员");
      }
      // 生成token传入参数对象
      LoginInfoResponseDTO loginInfoResponseDTO = tokenGenerator.convertUserDetail2TokenInfo(
          courtManageTokenUserDTO.getUserId(), courtManageTokenUserDTO.getActualName(),
          courtManageTokenUserDTO.getSocialIdentify(), courtManageTokenUserDTO.getTypeList());
      loginInfoResponseDTO.setHeadImageUrl(courtManageTokenUserDTO.getHeadImageUrl());
      // 生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO =
          tokenGenerator.generateLoginToken(loginInfoResponseDTO);
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("系统管理员用户名(手机号)密码登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户登录失败");
    }

  }

  /**
   * 辅助人员修改密码接口
   */
  @ApiOperation(value = "辅助人员修改密码接口", notes = "")
  @PostMapping("/assisterUser/ChangePwd")
  public Result assisterUserChangePwd(
      @RequestBody AssisttanterChangePwdDTO assisttanterChangePwdDTO) {

    log.info("辅助人员是否存在。\n修改密码用户ID：{}", ContextUtil.getCurrentUserId());
    Assistanter assistanter =
        assitantUserManage.selectAssistanterById(ContextUtil.getCurrentUserId());
    if (null == assistanter) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    if (!assistanter.getPassword()
        .equals(MD5Util.string2MD5(assisttanterChangePwdDTO.getOldPassword()))) {
      return Result.failed(ErrorCode.CHANGE_OLD_PASSWORD_ERROR, "原密码错误");
    }
    log.info("辅助人员修改用户密码。\n用户ID：{} \n用户新密码", ContextUtil.getCurrentUserId(),
        MD5Util.string2MD5(assisttanterChangePwdDTO.getPassword()));
    if (assitantUserManage.assistanterChangePwdByUserId(ContextUtil.getCurrentUserId(),
        MD5Util.string2MD5(assisttanterChangePwdDTO.getPassword()), null)) {
      return Result.success();
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改密码失败");
  }

  /**
   * 辅助人员忘记密码接口
   */
  @ApiOperation(value = "辅助人员忘记密码接口", notes = "")
  @PostMapping("/assistanterUserForgetPwd")
  public Result assistanterUserForgetPwd(
      @RequestBody AssistanterForgetPwdDTO assistanterForgetPwdDTO) {

    String smsCode =
        redisService.get(RedisKey.SMS_CODE_JUDSALE, assistanterForgetPwdDTO.getMobilePhone());
    log.info("查看竞拍者手机号是否存在。\n用户手机号：{}", assistanterForgetPwdDTO.getMobilePhone());
    if (assitantUserManage.assistantUserIsExit(null, assistanterForgetPwdDTO.getMobilePhone(),
        null)) {
      return Result.failed(ErrorCode.USER_NOT_EXISTS, "账号未注册或不存在");
    }
    if (null == smsCode || !smsCode.equals(assistanterForgetPwdDTO.getVerificationCode())) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "验证码错误或已过期，请重新获取");
    }
    log.info("通过旧密码和手机号修改竞拍者用户密码。\n用户手机号：{}\n用户新密码{}", assistanterForgetPwdDTO.getMobilePhone(),
        MD5Util.string2MD5(assistanterForgetPwdDTO.getPassword()));
    if (assitantUserManage.assistanterChangePwdByUserId(null,
        assistanterForgetPwdDTO.getMobilePhone(),
        MD5Util.string2MD5(assistanterForgetPwdDTO.getPassword()))) {
      return Result.success();
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改密码失败");
  }


  /**
   * 辅助人员个人信息接口
   */
  @ApiOperation(value = "辅助人员个人信息接口", notes = "")
  @PostMapping("/assistanterUser/Detail")
  public Result assistanterUserDetail() {

    log.info("辅助人员通过id查看个人信息，用户ID：{}", ContextUtil.getCurrentUserId());
    Assistanter assistanter =
        assitantUserManage.selectAssistanterById(ContextUtil.getCurrentUserId());
    if (null == assistanter) {
      return Result.failed(ErrorCode.USER_NOT_EXISTS, "账号未注册或不存在");
    }
    AssistanterUserDetailVO assistanterUserDetailVO = new AssistanterUserDetailVO();
    assistanterUserDetailVO.setName(assistanter.getActualName());
    assistanterUserDetailVO.setIdentityCard(assistanter.getIdCard());
    assistanterUserDetailVO.setPhone(assistanter.getPhone());
    if (null != assistanter.getPassword()) {
      assistanterUserDetailVO.setPassWordIsUp(true);
    } else {
      assistanterUserDetailVO.setPassWordIsUp(false);
    }
    return Result.success(assistanterUserDetailVO);
  }

  /**
   * 获取验证码
   */
  @ApiOperation(value = "获取手机验证码")
  @PostMapping("/getPhoneCode")
  public Result getPhoneCode(@RequestBody MobilePhoneRequestDTO dto) {
    personnelService.getPhoneCode(dto);
    return Result.success();
  }

  /**
   * 辅助人员个人退出登录接口
   */
  @ApiOperation(value = "辅助人员个人退出登录接口", notes = "")
  @PostMapping("/assistanterUserLogout")
  public Result assistanterUserLogout(HttpServletRequest request) {

    log.info("辅助人员是否存在。\n退出用户ID：{}", ContextUtil.getCurrentUserId());
    Assistanter assistanter =
        assitantUserManage.selectAssistanterById(ContextUtil.getCurrentUserId());
    if (null == assistanter) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    try {
      // 生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO = tokenGenerator
          .generateLogoutToken(request.getHeader(SecurityConst.JWT_AUTH_TOKEN_KEY_NAME));
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("辅助人员退出登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户退出登录失败");
    }
  }
  /**
   系统管理员退出登录接口
   */
  @ApiOperation(value = "系统管理员退出登录接口", notes = "")
  @PostMapping("/systemUserLogout")
  public Result systemUserLogout(HttpServletRequest request) {

    log.info("系统管理员是否存在。\n退出用户ID：{}", ContextUtil.getCurrentUserId());
    Admintor admintor = assitantUserManage
        .selectAdmintorById(ContextUtil.getCurrentUserId());
    if (null == admintor) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    try {
      //生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO = tokenGenerator
          .generateLogoutToken(request.getHeader(SecurityConst.JWT_AUTH_TOKEN_KEY_NAME));
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("系统管理员退出登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "系统管理员退出登录失败");
    }
  }
  /**
   法官管理员退出登录接口
   */
  @ApiOperation(value = "法官管理员退出登录接口", notes = "")
  @PostMapping("/courtManageLogout")
  public Result courtManagerLogout(HttpServletRequest request) {

    log.info("法官管理员是否存在。\n退出用户ID：{}", ContextUtil.getCurrentUserId());
    ServicePer servicePer = assitantUserManage
        .selectCountManageByID(ContextUtil.getCurrentUserId());
    if (null == servicePer) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    try {
      //生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO = tokenGenerator
          .generateLogoutToken(request.getHeader(SecurityConst.JWT_AUTH_TOKEN_KEY_NAME));
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("法官管理员退出登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "法官管理员退出登录失败");
    }
  }

  @ApiOperation(value = "刷新token", notes = "如果token过期则刷新token")
  @GetMapping("/refreshToken")
  public Result refreshToken(String refreshToken) {
    if (refreshToken == null && "".equals(refreshToken)) {
      return Result.failed(ErrorCode.UNEXCEPTED, "刷新token为空");
    }
    RefreshTokenResponseDTO dto = tokenGenerator.refreshToken(refreshToken);
    return Result.success(dto);
  }
}

package com.beiming.evidenceplatform.controller;

import com.alibaba.fastjson.JSON;
import com.beiming.evidenceplatform.common.constants.SecurityConst;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.utils.MD5Util;
import com.beiming.evidenceplatform.common.utils.evidenceplatform.AESenc;
import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.domain.UserDetail;
import com.beiming.evidenceplatform.domain.dto.UserDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.LoginInfoResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.LoginTokenResponseDTO;
import com.beiming.evidenceplatform.domain.dto.responsedto.RefreshTokenResponseDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderChangePwdUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderForgetPwdDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderLoginUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderRegisterUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderUserDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderUserImgDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BidderUserPhoneDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.MobilePhoneRequestDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.VerifiedRequestDTO;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.redis.enums.RedisKey;
import com.beiming.evidenceplatform.security.TokenGenerator;
import com.beiming.evidenceplatform.service.PersonnelService;
import com.beiming.evidenceplatform.service.RedisService;
import com.beiming.evidenceplatform.service.usermanage.BidderUserManage;
import com.beiming.evidenceplatform.utils.VerifiedUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description:  用户管理接口    
 */
@Api(value = "用户管理控制层", tags = {"用户管理控制层"})
@RestController
@RequestMapping("/account")
@Slf4j
public class AccountController {

  @Value("${sms.code.expireMinutes}")
  private int smsCodeExpireMinutes;

  @Value("${file.storage.tmp.dir}")
  private String path;

  @Autowired
  private TokenGenerator tokenGenerator;

  @Autowired
  private BidderUserManage bidderUserManage;

  @Autowired
  private PersonnelService personnelService;

  @Autowired
  private RedisService redisService;

  @Autowired
  DefaultKaptcha defaultKaptcha;

  /*
   * 用户登录接口
   */
  @ApiOperation(value = "登录接口", notes = "请求中judJWTToken请填写任意值，后台不做校验")
  @PostMapping("/login")
  public Result login(@RequestBody BidderLoginUserDTO bidderLoginUserDTO) {

    if (null == bidderLoginUserDTO) {
      return Result.failed(ErrorCode.LOGIN_INFO_INCOMPLETE, "登录信息不完整");
    }
    String md5Password = MD5Util.string2MD5(bidderLoginUserDTO.getPassword());
    try {
      log.info("用户名(手机号)密码登录。\n手机号：{} \n密码：{}", bidderLoginUserDTO.getPhone(), md5Password);
      BidderUserDTO bidderUserDTO =
          bidderUserManage.bidderUserLogin(bidderLoginUserDTO.getPhone(), md5Password);
      UserDetail userDetail = bidderUserManage.findByuserId(bidderUserDTO.getUserId());
      if (null == bidderUserDTO) {
        return Result.failed(ErrorCode.USER_NOT_EXISTS, "用户密码错误或用户不存在");
      }
      // 生成token传入参数对象
      LoginInfoResponseDTO loginInfoResponseDTO = tokenGenerator.convertUserDetail2TokenInfo(
          bidderUserDTO.getUserId(), bidderUserDTO.getActualName(),
          bidderUserDTO.getSocialIdentify(), bidderUserDTO.getTypeList());
      loginInfoResponseDTO.setHeadImageUrl(bidderUserDTO.getHeadImageUrl());
      // 生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO =
          tokenGenerator.generateLoginToken(loginInfoResponseDTO);
      // 判断该用户是否已经实名认证
      if (userDetail.getActualName() == null || userDetail.getIdCard() == null) {
        loginTokenResponseDTO.setIsCertification(0);
      } else {
        loginTokenResponseDTO.setIsCertification(1);
      }
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("用户名(手机号)密码登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户登录失败");
    }
  }

  /*
   * 修改密码接口
   */
  @ApiOperation(value = "修改密码接口", notes = "oldPassword为原密码，会对原密码做一个校验，password为新的密码")
  @PostMapping("/changePwd")
  public Result changePwd(@RequestBody BidderChangePwdUserDTO bidderChangePwdUserDTO) {

    log.info("查询用户是否存在。\n修改密码用户ID：{}", ContextUtil.getCurrentUserId());
    User bidderUser = bidderUserManage.selectBidderUserById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    if (!bidderUser.getPassword()
        .equals(MD5Util.string2MD5(bidderChangePwdUserDTO.getOldPassword()))) {
      return Result.failed(ErrorCode.CHANGE_OLD_PASSWORD_ERROR, "原密码错误");
    }
    log.info("修改用户密码。\n用户ID：{} \n用户新密码", ContextUtil.getCurrentUserId(),
        MD5Util.string2MD5(bidderChangePwdUserDTO.getPassword()));
    if (bidderUserManage.bidderUserModifyPassWord(ContextUtil.getCurrentUserId(),
        MD5Util.string2MD5(bidderChangePwdUserDTO.getPassword()), null)) {
      return Result.success("用户密码修改成功");
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改密码失败");
  }

  /*
   * 用户注册接口
   */
  @ApiOperation(value = "用户注册接口", notes = "请求中judJWTToken请填写任意值，后台不做校验")
  @PostMapping("/register")
  public Result register(@RequestBody BidderRegisterUserDTO bidderRegisterUserDTO) {

    log.info("查看用户是否已存在。\n用户手机号：{}", bidderRegisterUserDTO.getMobilePhone());
    if (bidderUserManage.isBidderExitByPhone(bidderRegisterUserDTO.getMobilePhone())) {
      return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "用户已存在");
    }
    String smsCode =
        redisService.get(RedisKey.SMS_CODE_JUDSALE, bidderRegisterUserDTO.getMobilePhone());
    if (null == smsCode || !smsCode.equals(bidderRegisterUserDTO.getVerificationCode())) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "验证码错误或已过期，请重新获取");
    }
    log.info("查看用户手机号密码注册。\n用户手机号：{}\n用户密码：{}", bidderRegisterUserDTO.getMobilePhone(),
        MD5Util.string2MD5(bidderRegisterUserDTO.getPassword()));
    if (bidderUserManage.bidderUserRegister(bidderRegisterUserDTO.getMobilePhone(),
        MD5Util.string2MD5(bidderRegisterUserDTO.getPassword()))) {
      return Result.success("用户注册成功");
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改密码失败");

  }

  /*
   * 忘记密码接口
   */
  @ApiOperation(value = "忘记密码接口", notes = "请求中judJWTToken请填写任意值，后台不做校验")
  @PostMapping("/forgetPwd")
  public Result forgetPwd(@RequestBody BidderForgetPwdDTO bidderForgetPwdDTO) {

    String smsCode =
        redisService.get(RedisKey.SMS_CODE_JUDSALE, bidderForgetPwdDTO.getMobilePhone());
    log.info("查看用户手机号是否存在。\n用户手机号：{}", bidderForgetPwdDTO.getMobilePhone());
    if (!bidderUserManage.isBidderExitByPhone(bidderForgetPwdDTO.getMobilePhone())) {
      return Result.failed(ErrorCode.USER_NOT_EXISTS, "账号未注册或不存在");
    }
    if (null == smsCode || !smsCode.equals(bidderForgetPwdDTO.getVerificationCode())) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "验证码错误或已过期，请重新获取");
    }
    log.info("通过手机号修改用户密码。\n用户手机号：{}\n用户新密码{}", bidderForgetPwdDTO.getMobilePhone(),
        MD5Util.string2MD5(bidderForgetPwdDTO.getPassword()));
    if (bidderUserManage.bidderUserModifyPassWord(null,
        MD5Util.string2MD5(bidderForgetPwdDTO.getPassword()),
        bidderForgetPwdDTO.getMobilePhone())) {
      return Result.success();
    }

    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改密码失败");
  }

  /**
   * 获取验证码
   */
  @ApiOperation(value = "获取手机验证码", notes = "请求中judJWTToken请填写任意值，后台不做校验")
  @PostMapping("/getPhoneCode")
  public Result getPhoneCode(@RequestBody MobilePhoneRequestDTO dto) {
    personnelService.getPhoneCode(dto);
    return Result.success("成功获取手机验证码");
  }

  /**
   * 退出登录接口
   */
  @ApiOperation(value = "退出登录接口")
  @PostMapping("/logout")
  public Result logout(HttpServletRequest request) {

    log.info("查询用户是否存在。\n用户ID：{}", ContextUtil.getCurrentUserId());
    User bidderUser = bidderUserManage.selectBidderUserById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    try {
      // 生成token封装对象
      LoginTokenResponseDTO loginTokenResponseDTO = tokenGenerator
          .generateLogoutToken(request.getHeader(SecurityConst.JWT_AUTH_TOKEN_KEY_NAME));
      return Result.success(loginTokenResponseDTO);
    } catch (Exception e) {
      log.error("竞拍人员退出登录异常{}", e);
      return Result.failed(ErrorCode.USER_NOT_LOGIN, "用户退出登录失败");
    }
  }

  @ApiOperation(value = "刷新token", notes = "如果token过期则刷新token")
  @PostMapping("/refreshToken")
  public Result refreshToken(String refreshToken) {
    if (refreshToken == null && "".equals(refreshToken)) {
      return Result.failed(ErrorCode.UNEXCEPTED, "刷新token为空");
    }
    RefreshTokenResponseDTO dto = tokenGenerator.refreshToken(refreshToken);
    return Result.success(dto);
  }

  /**
   * 实名认证
   * 
   * @param reqMap
   * @param request
   * @return
   */
  @RequestMapping(path = "/verified", method = RequestMethod.POST)
  @ResponseBody
  public Result verified(@RequestBody VerifiedRequestDTO verifiedRequestDTO,
      HttpServletRequest request) {
    log.info("查询用户是否存在。\n用户ID：{}", ContextUtil.getCurrentUserId());
    User bidderUser = bidderUserManage.selectBidderUserById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    // 之后将手机验证码保存到redis中
    String smsCode =
        redisService.get(RedisKey.SMS_CODE_JUDSALE, verifiedRequestDTO.getMobilePhone());
    if (null == smsCode || !verifiedRequestDTO.getVerificationCode().equals(smsCode)) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "验证码错误或已过期，请重新获取");
    }
    if (new VerifiedUtil().identityAuthentication(verifiedRequestDTO.getName(),
        verifiedRequestDTO.getIdCard())) {
      if (bidderUserManage.verified(bidderUser.getId() + "", verifiedRequestDTO.getName(),
          verifiedRequestDTO.getIdCard())) {
        return Result.success("验证通过");
      }
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "验证失败，身份证和姓名不匹配");
  }

  /*
   * 修改用户名接口
   */
  @ApiOperation(value = "修改用户名接口", notes = "传递json参数{'name':'test'}")
  @PostMapping("/userName")
  public Result userName(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
    log.info("查询用户是否存在。\n用户ID：{}", ContextUtil.getCurrentUserId());
    User bidderUser = bidderUserManage.selectBidderUserById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    String name = reqMap.get("name").toString();
    bidderUser.setName(name);
    if (bidderUserManage.bidderUserModifyName(bidderUser.getId() + "", bidderUser.getName())) {
      return Result.success("用户名修改成功");
    }

    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改用户名失败");
  }

  /*
   * 修改用户邮箱接口
   */
  @ApiOperation(value = "修改用户邮箱接口", notes = "传递json参数{'email':'test@qq.com'}")
  @PostMapping("/userEmail")
  public Result userEmail(@RequestBody Map<String, Object> reqMap, HttpServletRequest request) {
    log.info("查询用户是否存在。\n用户ID：{}", ContextUtil.getCurrentUserId());
    User bidderUser = bidderUserManage.selectBidderUserById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    String email = reqMap.get("email").toString();
    bidderUser.setEmail(email);
    if (bidderUserManage.bidderUserModifyEmail(bidderUser.getId() + "", bidderUser.getEmail())) {
      return Result.success("邮箱修改成功");
    }

    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改邮箱失败");
  }

  /*
   * 修改用户手机号码接口
   */
  @ApiOperation(value = "修改用户手机号码接口")
  @PostMapping("/userPhone")
  public Result userPhone(@RequestBody BidderUserPhoneDTO bidderUserPhoneDTO,
      HttpServletRequest request) {
    log.info("查询用户是否存在。\n用户ID：{}", ContextUtil.getCurrentUserId());
    User bidderUser = bidderUserManage.selectBidderUserById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    if (bidderUserManage.isBidderExitByPhone(bidderUserPhoneDTO.getNewMobilePhone())) {
      return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "该手机号已被注册");
    }
    // 之后将手机验证码保存到redis中
    String smsCode =
        redisService.get(RedisKey.SMS_CODE_JUDSALE, bidderUserPhoneDTO.getNewMobilePhone());
    if (null == smsCode || !bidderUserPhoneDTO.getVerificationCode().equals(smsCode)) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "验证码错误或已过期，请重新获取");
    }
    UserDTO userDTO = bidderUserManage.selectBidderUserInfoById(ContextUtil.getCurrentUserId());
    if (userDTO.getPhone().equals(bidderUserPhoneDTO.getNewMobilePhone())) {
      return Result.failed(ErrorCode.MOBILE_EXITS, "新的手机号码与原手机号码重复");
    }
    if (bidderUserManage.bidderUserModifyPhone(bidderUser.getId() + "",
        bidderUserPhoneDTO.getNewMobilePhone())) {
      return Result.success("手机号码修改成功");
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改手机号失败");
  }

  /*
   * 根据id查询用户信息接口
   */
  @ApiOperation(value = "根据id查询用户信息接口")
  @GetMapping("/userInfo")
  public Result userInfo(HttpServletRequest request) {
    log.info("查询用户是否存在。\n用户ID：{}", ContextUtil.getCurrentUserId());
    UserDTO bidderUser = bidderUserManage.selectBidderUserInfoById(ContextUtil.getCurrentUserId());
    if (null == bidderUser) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EXPIRE, "未登录或登陆已退出");
    }
    return Result.success(bidderUser);
  }


  /*
   * 生成图片验证码接口
   */
  @ApiOperation(value = "生成图片验证码接口")
  @GetMapping("/imgCode")
  public void defaultKaptcha(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws Exception {
    byte[] captchaChallengeAsJpeg = null;
    ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
    try {
      String createText = defaultKaptcha.createText();
      redisService.set(RedisKey.IMG_CODE_JUDSALE, httpServletRequest.getSession().getId(),
          createText, smsCodeExpireMinutes, TimeUnit.MINUTES);
      // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
      BufferedImage challenge = defaultKaptcha.createImage(createText);
      ImageIO.write(challenge, "jpg", jpegOutputStream);
    } catch (IllegalArgumentException e) {
      httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
      return;
    }
    // 定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
    captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
    httpServletResponse.setHeader("Cache-Control", "no-store");
    httpServletResponse.setHeader("Pragma", "no-cache");
    httpServletResponse.setDateHeader("Expires", 0);
    httpServletResponse.setContentType("image/jpeg");
    ServletOutputStream responseOutputStream = httpServletResponse.getOutputStream();
    responseOutputStream.write(captchaChallengeAsJpeg);
    responseOutputStream.flush();
    responseOutputStream.close();
  }

  /*
   * 生成图片验证码接口备用
   */
  @ApiOperation(value = "生成图片验证码接口图片保存到服务器（备用）")
  @GetMapping("/imgCodeNative")
  public Result imgCodeNative(HttpServletRequest httpServletRequest,
      HttpServletResponse httpServletResponse) throws Exception {
    ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
    FileOutputStream fos = null;
    try {
      String targetPath = path + File.separator + "imgCode" + File.separator;
      File dest = new File(targetPath);
      dest.mkdirs();
      fos = new FileOutputStream(
          new File(targetPath + httpServletRequest.getSession().getId() + ".jpg"));
      String createText = defaultKaptcha.createText();
      redisService.set(RedisKey.IMG_CODE_JUDSALE, httpServletRequest.getSession().getId(),
          createText, smsCodeExpireMinutes, TimeUnit.MINUTES);
      // 使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
      BufferedImage challenge = defaultKaptcha.createImage(createText);
      ImageIO.write(challenge, "jpg", jpegOutputStream);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(jpegOutputStream);
      objectOutputStream.flush();
      objectOutputStream.close();
      jpegOutputStream.writeTo(fos);
      jpegOutputStream.flush();
      jpegOutputStream.close();
      return Result.success(targetPath + httpServletRequest.getSession().getId() + ".jpg");
    } catch (IllegalArgumentException e) {
      httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
      return Result.failed(e);
    } finally {
      if (fos != null) {
        fos.close();
      }
    }
  }

  /*
   * 验证图片验证码接口
   */
  @ApiOperation(value = "验证图片验证码接口")
  @PostMapping("/imgCodeVerified")
  public Result imgvrifyControllerDefaultKaptcha(@RequestBody BidderUserImgDTO bidderUserImgDTO,
      HttpServletRequest request) {
    String captchaId = redisService.get(RedisKey.IMG_CODE_JUDSALE, request.getSession().getId());
    if (null == captchaId || !bidderUserImgDTO.getImgCode().equals(captchaId)) {
      return Result.failed(ErrorCode.SMS_CODE_INCORRECT, "图片验证码错误或已过期，请重新获取");
    }
    return Result.success("验证码通过验证");
  }

  /*
   * ODR等其他平台新建用户接口
   */
  @ApiOperation(value = "供ODR等其他平台新建用户接口",
      notes = "请求中judJWTToken请填写任意值，后台不做校验, encryptedData为AES加密后的数据，加密前的数据为（verificationCode填空即可） {\"mobilePhone\":\"123\", \"password\":\"password1\", \"verificationCode\":\"\"}")
  @PostMapping("/createUserFromOtherPlatform")
  public Result createUserFromOtherPlatform(@RequestParam String encryptedData) {

    BidderRegisterUserDTO bidderRegisterUserDTO;
    try {
      // yWJ9EXmaysmekV/RhUltDbzD+HCZeD5A7U1OKPrRgPMBJF038/WJfbyvc/OqAYXKsfuturGOzbfITU3Dl1/7XDJj235R/ejl6KKJym0JYGnSex2+eP+3UOg8D/vbaOK8
      // {"mobilePhone":"123", "password":"password1", "verificationCode":"verificationCode1"}
      String data = AESenc.decrypt(encryptedData);
      bidderRegisterUserDTO = JSON.parseObject(data, BidderRegisterUserDTO.class);
    } catch (Exception e) {
      e.printStackTrace();
      return Result.failed(ErrorCode.AES_DECRYPT_FAIL_OR_INVALID_JSON, "AES解密失败或者json参数解析失败");
    }

    log.info("查看用户是否已存在。\n用户手机号：{}", bidderRegisterUserDTO.getMobilePhone());
    if (bidderUserManage.isBidderExitByPhone(bidderRegisterUserDTO.getMobilePhone())) {
      return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "用户已存在");
    }
    log.info("查看用户手机号密码注册。\n用户手机号：{}\n用户密码：{}", bidderRegisterUserDTO.getMobilePhone(),
        MD5Util.string2MD5(bidderRegisterUserDTO.getPassword()));
    if (bidderUserManage.bidderUserRegister(bidderRegisterUserDTO.getMobilePhone(),
        MD5Util.string2MD5(bidderRegisterUserDTO.getPassword()))) {
      return Result.success();
    }
    return Result.failed(ErrorCode.CHANGE_PASSWORD_FAIL, "修改密码失败");

  }
}

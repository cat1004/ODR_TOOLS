package com.beiming.evidenceplatform.serviceimpl;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.beiming.evidenceplatform.common.AppException;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentSmsService;
import com.beiming.evidenceplatform.dao.PersonnelMapper;
import com.beiming.evidenceplatform.domain.Personnel;
import com.beiming.evidenceplatform.domin.dto.requestdto.MobilePhoneRequestDTO;
import com.beiming.evidenceplatform.redis.enums.RedisKey;
import com.beiming.evidenceplatform.service.PersonnelService;
import com.beiming.evidenceplatform.service.RedisService;
import com.beiming.evidenceplatform.utils.SMSTencent;
import com.google.gson.JsonObject;

@Service
public class PersonnelServiceImpl implements PersonnelService {
  private static final int RANDOM_MIN = 100000;
  private static final int RANDOM_MAX = 900000;

  @Value("${sms.code.expireMinutes}")
  private int smsCodeExpireMinutes;

  @Autowired
  private PersonnelMapper personnelDao;
  @Autowired
  private RedisService redisService;
  @Autowired
  private TencentSmsService smsService;

  @Override
  public void addPersonnel(Personnel personnel) {
    // TODO Auto-generated method stub
    personnelDao.addPersonnel(personnel);
  }

  @Override
  public String getPhoneCode(MobilePhoneRequestDTO dto) {
    String phoneNumber = dto.getMobilePhone();
    String smsCode = redisService.get(RedisKey.SMS_CODE_JUDSALE, phoneNumber);
    if (StringUtils.isBlank(smsCode)) {
      smsCode = generateSMSCode();
      redisService.set(RedisKey.SMS_CODE_JUDSALE, phoneNumber, smsCode, smsCodeExpireMinutes,
          TimeUnit.MINUTES);
    }
    // 发送短信
    String[] params = {smsCode};
    // JSONObject result = smsService.sendSms(phoneNumber, params, TencentSmsConst.SIGN,
    // TencentSmsConst.BIDDER_REGISTER);
    JsonObject result = new SMSTencent().send(phoneNumber, 147738, params);
    if (result.get("error_code").getAsInt() != 0) {
      throw new AppException(ErrorCode.PHONE_SEND_WRONG, "发送短信验证码失败");
    }
    return smsCode;
  }

  @Override
  public String getSmsCode(MobilePhoneRequestDTO dto) {
    // TODO Auto-generated method stub
    return null;
  }

  /**
   * 验证码规则：6位有效数字
   * 
   * @return
   */
  private String generateSMSCode() {
    Random r = new Random();
    int code = RANDOM_MIN + r.nextInt(RANDOM_MAX);
    return String.valueOf(code);
  }

  @Override
  public int modifyPersonnel(Personnel personnel) {
    // TODO Auto-generated method stub
    return personnelDao.updatePersonnel(personnel);
  }
}

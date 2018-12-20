package com.beiming.evidenceplatform.common.tencentcos.service;
/**
 * 
 * @author 腾讯sms短信
 *
 */

import com.alibaba.fastjson.JSONObject;

public interface TencentSmsService {
  /**
   * 根据模板单发短信
   * 
   * @param phoneNumber
   * @param params
   * @param sign
   * @param templateId
   * @return
   */
  public JSONObject sendSms(String phoneNumber, String[] params, String sign, int templateId);
}

package com.beiming.evidenceplatform.commin.tencentcos.service.impl;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentSmsService;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TencentSmsServiceImpl implements TencentSmsService {

  @Value("${tencent.sms.adkappid}")
  private int adkappid;

  @Value("${tencent.sms.appkey}")
  private String appkey;

  @Override
  public JSONObject sendSms(String phoneNumber, String[] params, String sign, int templateId) {
    // TODO Auto-generated method stub
    JSONObject obj = new JSONObject();
    try {
      SmsSingleSender ssender = new SmsSingleSender(adkappid, appkey);
      SmsSingleSenderResult result =
          ssender.sendWithParam("86", phoneNumber, templateId, params, sign, "", "");
      log.debug(result.toString());

      obj.put("smsStatus", ErrorCode.SUCCESS);
      return obj;
    } catch (HTTPException e) {
      // HTTP响应码错误
      e.printStackTrace();
    } catch (JSONException e) {
      // json解析错误
      e.printStackTrace();
    } catch (IOException e) {
      // 网络IO错误
      e.printStackTrace();
    }
    obj.put("smsStatus", ErrorCode.UNEXCEPTED);
    return obj;
  }

}

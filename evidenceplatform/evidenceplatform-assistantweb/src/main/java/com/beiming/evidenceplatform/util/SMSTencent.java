package com.beiming.evidenceplatform.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.alibaba.druid.util.StringUtils;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Configuration
public class SMSTencent {
  static Logger logger = LoggerFactory.getLogger(SMSTencent.class);
  // 短信应用SDK AppID
  static int appid; // 1400开头

  // 短信应用SDK AppKey
  static String appkey;

  // 需要发送短信的手机号码
  static String[] phoneNumbers = {"14790090866"};

  // 短信模板ID，需要在短信应用中申请
  static int templateId = 159083; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请

  static String smsSign = "石景山矛盾多元化解中心"; // NOTE:
                                         // 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`

  @Value("${tencent.sms.adkappid}")
  public void setAppid(int appid) {
    SMSTencent.appid = appid;
  }

  @Value("${tencent.sms.appkey}")
  public void setAppkey(String appkey) {
    SMSTencent.appkey = appkey;
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub
    try {
      String[] params = {"Herbert", "Soon", "Test"};
      SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
      SmsSingleSenderResult result =
          ssender.sendWithParam("86", phoneNumbers[0], templateId, params, smsSign, "", ""); // 签名参数未提供或者为空时，会使用默认签名发送短信
      System.out.print(result.toString());
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
  }

  public void testsend() {
    try {
      String[] params = {"5678"};
      SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
      SmsSingleSenderResult result =
          ssender.sendWithParam("86", phoneNumbers[0], templateId, params, smsSign, "", ""); // 签名参数未提供或者为空时，会使用默认签名发送短信
      System.out.print(result.toString());
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
  }

  /**
   * 发送消息
   * 
   * @param phone
   * @param templateId
   * @param params
   */
  public static JsonObject send(String phone, int templateId, String[] params) {
    JsonObject jsonObject = null;
    try {
      SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
      SmsSingleSenderResult result =
          ssender.sendWithParam("86", phone, templateId, params, smsSign, "", ""); // 签名参数未提供或者为空时，会使用默认签名发送短信
      jsonObject = (JsonObject) new Gson().toJsonTree(result);
      if (result.result == 0) {
        jsonObject.addProperty("error_code", 0);
        return jsonObject;
      }
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
    return jsonObject;
  }

  /**
   * 参数格式转换
   * 
   * @param keys
   * @param vals
   * @return
   */
  public static String[] paramFormat(String[] keys, String[] vals) {
    String[] urls = new String[] {"#url#", "#adminUrl#", "#orgUrl#", "#orgLoginUrl#", "#odrCamUrl#",
        "#odrUrl#"};
    List<String> keysList = new ArrayList<String>();
    for (String string : keys) {
      keysList.add(string.trim());
    }
    List<String> aslist = Arrays.asList(vals);
    List<String> valList = new ArrayList<String>(aslist);
    for (int i = 0; i < urls.length; i++) {
      if (keysList.contains(urls[i])) {
        int index = keysList.indexOf(urls[i]);
        valList.remove(index);
        return valList.toArray(vals);
      }
    }
    return vals;
  }

  public static String[] paramFormat(Map<String, Object> map, String str) {
    List<String> resultList = new ArrayList<String>();
    if (str == null || StringUtils.isEmpty(str.trim()) || map == null) {
      resultList.toArray(new String[resultList.size()]);
    }
    str = str.replace(" ", "");
    str = str.substring(str.indexOf("[") + 1, str.lastIndexOf("]"));
    String[] strs = str.split(",");
    String[] urls = new String[] {"#url#", "#adminUrl#", "#orgUrl#", "#orgLoginUrl#", "#odrCamUrl#",
        "#odrUrl#"};
    List<String> urlLists = Arrays.asList(urls);
    for (String string : strs) {
      if (urlLists.contains(string)) {
        continue;
      } else {
        if (map.containsKey(string) && map.get(string) != null)
          resultList.add(map.get(string).toString());
      }
    }
    return resultList.toArray(new String[resultList.size()]);
  }

}

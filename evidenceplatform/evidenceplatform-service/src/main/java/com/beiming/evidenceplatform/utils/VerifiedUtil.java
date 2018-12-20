package com.beiming.evidenceplatform.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.JSONObject;

/**
 * 中间件实名认证
 */
@Configuration
public class VerifiedUtil {

  private static String evaluateContext;

  private static String evaluateUserName;

  private static String evaluatePassword;

  @Value("${evaluate.context}")
  public void setEvaluateContext(String evaluateContext) {
    VerifiedUtil.evaluateContext = evaluateContext;
  }

  @Value("${evaluate.userName}")
  public void setEvaluateUserName(String evaluateUserName) {
    VerifiedUtil.evaluateUserName = evaluateUserName;
  }

  @Value("${evaluate.password}")
  public void setEvaluatePassword(String evaluatePassword) {
    VerifiedUtil.evaluatePassword = evaluatePassword;
  }

  /**
   * 中间件聚合函数接口
   * 
   * @param name
   * @param idcard
   * @return 请求成功示意 ： {"result":true,"code":200,"message":"成功"} //result：true匹配 false 不匹配 返回code码详情
   *         200 请求成功 400 参数有误 500 无效的token：连接中间件用户名密码有误 3 错误的请求；其中 message:account abnormal,errorno
   *         is:2 为账号欠费停服 4 签名为空 5 签名串错误 6 签名中的 appid/bucket 与操作目标不匹配 9 签名过期 10 appid 不存在 11
   *         secretid 不存在 12 appid 和 secretid 不匹配 13 重放攻击 14 签名校验失败 15 操作太频繁，触发频控 16 bucket 不存在 21
   *         无效参数 23 请求包体过大 24 没有权限 25 您购买的资源已用完 107 鉴权服务不可用 108 鉴权服务不可用 213 内部错误 -5803 身份证号码与姓名不匹配
   *         -5804 身份证号码无效 -5806 身份证号码或者姓名格式错误 -5807 查询身份证信息失败
   */
  public static boolean identityAuthentication(String name, String idcard) {
    String contextPath = evaluateContext;
    JSONObject loginResult = evaluateLogin(contextPath);
    if ("200".equals(loginResult.getString("code"))) {
      String token = loginResult.getJSONObject("result").getString("token");
      JSONObject user = new JSONObject();
      user.put("name", name);
      user.put("idcard", idcard);
      String resultUser =
          HttpClientUtils.sendHttpPost(contextPath + "/verified/user", user.toJSONString(), token);
      JSONObject resultDate = JSONObject.parseObject(resultUser);
      if ("200".equals(resultDate.getString("code"))) {
        return resultDate.getBooleanValue("result");
      }
    }
    return false;
  }

  /**
   * 中间件腾讯接口
   * 
   * @param name
   * @param idcard
   * @return 请求成功示意 ： {"result":true,"code":200,"message":"成功"} //result：true匹配 false 不匹配 返回code码详情
   *         200 请求成功 400 参数有误 500 无效的token：连接中间件用户名密码有误，或生成签名有误 3 错误的请求；其中 message:account
   *         abnormal,errorno is:2 为账号欠费停服 4 签名为空 5 签名串错误 6 签名中的 appid/bucket 与操作目标不匹配 9 签名过期 10
   *         appid 不存在 11 secretid 不存在 12 appid 和 secretid 不匹配 13 重放攻击 14 签名校验失败 15 操作太频繁，触发频控 16
   *         bucket 不存在 21 无效参数 23 请求包体过大 24 没有权限 25 您购买的资源已用完 107 鉴权服务不可用 108 鉴权服务不可用 213 内部错误
   *         -5803 身份证号码与姓名不匹配 -5804 身份证号码无效 -5806 身份证号码或者姓名格式错误 -5807 查询身份证信息失败
   * 
   */
  public static boolean identityAuthenticationTencent(String name, String idcard) {
    String contextPath = evaluateContext;
    JSONObject loginResult = evaluateLogin(contextPath);
    if ("200".equals(loginResult.getString("code"))) {
      String token = loginResult.getJSONObject("result").getString("token");
      JSONObject user = new JSONObject();
      user.put("name", name);
      user.put("idcard", idcard);
      String resultUser = HttpClientUtils.sendHttpPost(contextPath + "/verified/userIdcard",
          user.toJSONString(), token);
      JSONObject resultDate = JSONObject.parseObject(resultUser);
      if ("200".equals(resultDate.getString("code"))) {
        return resultDate.getBooleanValue("result");
      }
    }
    return false;
  }


  public static JSONObject evaluateLogin(String contextPath) {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("userName", evaluateUserName);
    jsonObject.put("password", evaluatePassword);
    String loginStr =
        HttpClientUtils.sendHttpPost(contextPath + "/login/signIn", jsonObject.toString());
    return JSONObject.parseObject(loginStr);
  }

}

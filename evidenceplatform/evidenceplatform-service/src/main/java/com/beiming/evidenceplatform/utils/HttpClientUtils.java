package com.beiming.evidenceplatform.utils;

//import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * httpclient工具类
 */
public class HttpClientUtils {

  private static String encoding = "UTF-8";
  private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);
  private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(60000)
      .setConnectTimeout(60000).setConnectionRequestTimeout(60000).build();

  /**
   * 发送 post请求
   * 
   * @param httpUrl 地址
   */
  public static String sendHttpPost(String httpUrl) {
    HttpPost httpPost = new HttpPost(httpUrl);
    return sendHttpPost(httpPost);
  }

  /**
   * 发送 post请求
   * 
   * @param httpUrl 地址
   * @param json json参数
   * @return
   */
  public static String sendHttpPost(String httpUrl, String json) {
    HttpPost httpPost = new HttpPost(httpUrl);
    httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    return sendHttpPost(httpPost);
  }

  /**
   * 发送 post请求
   * 
   * @param httpUrl 地址
   * @param json json参数
   * @return
   */
  public static String sendHttpPost(String httpUrl, String json, String token) {
    HttpPost httpPost = new HttpPost(httpUrl);
    httpPost.setHeader("token", token);
    httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    return sendHttpPost(httpPost);
  }

  public static String sendHttpPostAuthorization(String httpUrl, String json,
      String authorization) {
    HttpPost httpPost = new HttpPost(httpUrl);
    httpPost.setHeader("authorization", authorization);
    httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    return sendHttpPost(httpPost);
  }

  /**
   * 发送 post请求
   * 
   * @param httpUrl 地址
   * @param json json参数
   * @return
   */
  public static String sendHttpPost(String httpUrl, String json, String key, String token) {
    HttpPost httpPost = new HttpPost(httpUrl);
    httpPost.setHeader("key", key);
    httpPost.setHeader("token", token);
    httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    return sendHttpPost(httpPost);
  }

  /**
   * 发送 post请求
   * 
   * @param httpUrl 地址
   * @param json json参数
   * @return
   */
  public static String sendHttpPostByUserName(String httpUrl, String userName, String password) {
    HttpPost httpPost = new HttpPost(httpUrl);
    httpPost.setHeader("userName", userName);
    httpPost.setHeader("password", password);
    return sendHttpPost(httpPost);
  }

  /**
   * 发送 post请求
   * 
   * @param httpUrl 地址
   * @param maps 参数
   */
  public static String sendHttpPost(String httpUrl, Map<String, String> maps) {
    HttpPost httpPost = new HttpPost(httpUrl);
    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
    for (String key : maps.keySet()) {
      nameValuePairs.add(new BasicNameValuePair(key, maps.get(key)));
    }
    try {
      httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, encoding));
    } catch (Exception e) {
      logger.error("HttpClientUtils sendHttpPost error {}");
    }
    return sendHttpPost(httpPost);
  }


  /**
   * 发送Post请求
   * 
   * @param httpPost
   * @return
   */
  private static String sendHttpPost(HttpPost httpPost) {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    String responseContent = null;
    try {
      httpClient = HttpClients.createDefault();
      httpPost.setConfig(requestConfig);
      response = httpClient.execute(httpPost);
      entity = response.getEntity();
      responseContent = EntityUtils.toString(entity, encoding);
    } catch (Exception e) {
      logger.error("HttpClientUtils sendHttpPost error {}", e);
    } finally {
//      try {
//        if (response != null) {
//          response.close();
//        }
//        if (httpClient != null) {
//          httpClient.close();
//        }
//      } catch (IOException e) {
//        logger.error("HttpClientUtils sendHttpPost error {}", e);
//      }
    }
    return responseContent;
  }

  /**
   * 发送 get请求
   * 
   * @param httpUrl
   */
  public static String sendHttpGet(String httpUrl) {
    HttpGet httpGet = new HttpGet(httpUrl);
    return sendHttpGet(httpGet);
  }

  /**
   * 发送 get请求
   * 
   * @param httpUrl
   */
  public static String sendHttpsGet(String httpUrl) {
    HttpGet httpGet = new HttpGet(httpUrl);
    return sendHttpsGet(httpGet);
  }

  /**
   * 发生 get请求
   * 
   * @param httpUrl
   * @param header 头信息
   * @return
   */
  public static String sendHttpGet(String httpUrl, Map<String, String> header) {
    HttpGet httpGet = new HttpGet(httpUrl);
    if (header != null) {
      Set<String> keys = header.keySet();
      for (String key : keys) {
        httpGet.setHeader(key, header.get(key));
      }
    }
    return sendHttpGet(httpGet);
  }

  /**
   * 发生 get请求
   * 
   * @param httpUrl
   * @param header 头信息
   * @return
   */
  public static String sendHttpsGet(String httpUrl, Map<String, String> header) {
    HttpGet httpGet = new HttpGet(httpUrl);
    if (header != null) {
      Set<String> keys = header.keySet();
      for (String key : keys) {
        httpGet.setHeader(key, header.get(key));
      }
    }
    return sendHttpsGet(httpGet);
  }

  /**
   * 发送Get请求
   * 
   * @param httpPost
   * @return
   */
  private static String sendHttpsGet(HttpGet httpGet) {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    String responseContent = null;
    try {
      httpClient =
          HttpClients.custom().setSSLSocketFactory(SSLConnectionSocketFactory.getSocketFactory())
              .setDefaultRequestConfig(requestConfig).build();
      httpGet.setConfig(requestConfig);
      response = httpClient.execute(httpGet);
      entity = response.getEntity();
      responseContent = EntityUtils.toString(entity, encoding);
    } catch (Exception e) {
      logger.error("HttpClientUtils sendHttpsGet error {}", e);
    } finally {
//      try {
//        if (response != null) {
//          response.close();
//        }
//        if (httpClient != null) {
//          httpClient.close();
//        }
//      } catch (IOException e) {
//        logger.error("HttpClientUtils sendHttpsGet error {}", e);
//      }
    }
    return responseContent;
  }

  /**
   * 发送Get请求
   * 
   * @param httpPost
   * @return
   */
  private static String sendHttpGet(HttpGet httpGet) {
    CloseableHttpClient httpClient = null;
    CloseableHttpResponse response = null;
    HttpEntity entity = null;
    String responseContent = null;
    try {
      httpClient = HttpClients.createDefault();
      httpGet.setConfig(requestConfig);
      response = httpClient.execute(httpGet);
      entity = response.getEntity();
      responseContent = EntityUtils.toString(entity, encoding);
    } catch (Exception e) {
      logger.error("HttpClientUtils sendHttpsGet error {}", e);
    } finally {
//      try {
//        if (response != null) {
//          response.close();
//        }
//        if (httpClient != null) {
//          httpClient.close();
//        }
//      } catch (IOException e) {
//        logger.error("HttpClientUtils sendHttpsGet error {}", e);
//      }
    }
    return responseContent;
  }

  /**
   * 发送post请求语音转文字接口方
   * 
   * @param httpUrl 请求路径
   * @param json 请求体
   * @param head 头信息
   * @return
   */
  public static String sendHttpPostHead(String httpUrl, String json, JSONObject head) {
    HttpPost httpPost = new HttpPost(httpUrl);
    httpPost.addHeader("Cmd", head.getString("Cmd"));
    httpPost.setEntity(new StringEntity(json, ContentType.APPLICATION_JSON));
    return sendHttpPost(httpPost);
  }



  public static void main(String[] args) {
    String url = "rtmp://19089.liveplay.myqcloud.com/live/19089_8465164e3c";
    String prefix = "xieguan_iui";
    JSONObject obj = new JSONObject();
    JSONArray array = new JSONArray();
    obj.put("Url", url);
    obj.put("Prefix", prefix);
    array.add(obj);
    JSONObject head = new JSONObject();
    head.put("Cmd", "start");
    String result =
        sendHttpPostHead("http://x1846l5486.51mypc.cn:19000/trans", array.toJSONString(), head);
    System.out.println(result);
  }

}

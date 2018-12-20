package com.beiming.evidenceplatform.common.tencentcos.service;


import java.io.File;
import com.alibaba.fastjson.JSONObject;

/**
 * 腾讯云COS对象存储接口service
 * 
 * @author xieguangliu
 *
 */
public interface TencentCosService {

  /**
   * 将本地文件上传至COS
   * 
   * @param key
   * @param key
   * @param contentBuffer 本地文件地址
   * @return
   */
  public JSONObject putObject(String key, File file);



  /**
   * 删除文件
   * 
   * @param key
   * @param key
   * @return
   */
  public JSONObject deleteObject(String key);

  /**
   * 下载文件
   * 
   * @param key
   * @param key
   * @return
   */
  public JSONObject downFile(String key, String targerPath);


}

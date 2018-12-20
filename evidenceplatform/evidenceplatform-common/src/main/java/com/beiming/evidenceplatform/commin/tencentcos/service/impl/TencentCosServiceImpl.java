package com.beiming.evidenceplatform.commin.tencentcos.service.impl;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.alibaba.fastjson.JSONObject;
import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.tencentcos.service.TencentCosService;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

/**
 * 腾讯云对象存储COS 实现类
 * 
 * @author xieguangliu
 *
 */
@Service
public class TencentCosServiceImpl implements TencentCosService {
  /**
   * 个人账号测试
   */
  @Value("${tencent.cos.secrectId}")
  public String secrectId;
  @Value("${tencent.cos.secrectKey}")
  public String secrectKey;
  @Value("${tencent.cos.appId}")
  public long appId;
  @Value("${tencent.cos.area}")
  public String area;
  @Value("${tencent.cos.bucketName}")
  public String bucketName;

  /**
   * 初始化client
   * 
   * @return
   */
  private COSClient initClient() {
    COSCredentials cred = new BasicCOSCredentials(secrectId, secrectKey);
    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    ClientConfig clientConfig = new ClientConfig(new Region(area));
    // 3 生成cos客户端
    return new COSClient(cred, clientConfig);
  }

  /**
   * 关闭client,每次初始化后都需要关闭该client
   * 
   * @param client
   */
  private void closeClient(COSClient client) {
    client.shutdown();
  }

  @Override
  public JSONObject putObject(String key, File file) {
    // TODO Auto-generated method stub
    JSONObject json = new JSONObject();

    try {
      COSClient client = initClient();
      PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, file);
      PutObjectResult putObjectResult = client.putObject(putObjectRequest);
      closeClient(client);
      json.put("file_status", ErrorCode.SUCCESS);
    } catch (Exception e) {
      // TODO: handle exception
      json.put("file_status", ErrorCode.UNEXCEPTED);
    }
    return json;
  }


  @Override
  public JSONObject deleteObject(String key) {
    // TODO Auto-generated method stub
    JSONObject json = new JSONObject();
    try {
      COSClient client = initClient();
      client.deleteObject(bucketName, key);
      closeClient(client);
      json.put("file_status", ErrorCode.SUCCESS);
    } catch (Exception e) {
      // TODO: handle exception
      json.put("file_status", ErrorCode.UNEXCEPTED);
    }
    return json;
  }

  /**
   * 
   * @Title: downFile
   * @Description: 下载文件
   * @return
   */
  public JSONObject downFile(String key, String targerPath) {
    JSONObject json = new JSONObject();

    try {
      COSClient client = initClient();
      File downFile = new File(targerPath);
      // 指定要下载的文件所在的 bucket 和对象键
      GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
      ObjectMetadata downObjectMeta = client.getObject(getObjectRequest, downFile);
      closeClient(client);
      json.put("file_status", ErrorCode.SUCCESS);
    } catch (Exception e) {
      // TODO: handle exception
      json.put("file_status", ErrorCode.UNEXCEPTED);
    }
    return json;
  }


}

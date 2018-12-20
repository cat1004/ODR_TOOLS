package com.beiming.evidenceplatform.common.utils;
/*package com.beiming.jud.common.utils;

import java.io.File;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.model.COSObject;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;

public class TencentCosUtils {
  public static String SECRET_ID = "AKIDxkJitqZ1GG2av3llIadbPzMVR9g0x1hA";
  public static String SECRET_KEY = "OZYIn34pJYXGjwuc4FBrHRL0PYR4RYP0";
  public static String AREA = "ap-shanghai";
  public static String BUCKET_NAME = "judbucket-1255773429";

  public static COSClient initClient() {
    // 1 初始化用户身份信息(secretId, secretKey)
    COSCredentials cred = new BasicCOSCredentials("AKIDxkJitqZ1GG2av3llIadbPzMVR9g0x1hA", "OZYIn34pJYXGjwuc4FBrHRL0PYR4RYP0");
    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    ClientConfig clientConfig = new ClientConfig(new Region("ap-shanghai"));
    // 3 生成cos客户端
    COSClient cosclient = new COSClient(cred, clientConfig);
    // bucket的命名规则为{name}-{appid} ，此处填写的存储桶名称必须为此格式
    String bucketName = "mybucket-1251668577";
    return cosclient;
  }

  public static PutObjectResult upfile(String path, String key) {
    // 简单文件上传, 最大支持 5 GB, 适用于小文件上传, 建议 20M以下的文件使用该接口
    // 大文件上传请参照 API 文档高级 API 上传
    File localFile = new File(path);
    // 指定要上传到 COS 上对象键
    // 对象键（Key）是对象在存储桶中的唯一标识。例如，在对象的访问域名
    // `bucket1-1250000000.cos.ap-guangzhou.myqcloud.com/doc1/pic1.jpg` 中，对象键为 doc1/pic1.jpg, 详情参考
    // [对象键](https://cloud.tencent.com/document/product/436/13324)
    //String key2 = "upload_single_demo.txt";
    PutObjectRequest putObjectRequest = new PutObjectRequest(BUCKET_NAME, key, localFile);
    PutObjectResult putObjectResult = initClient().putObject(putObjectRequest); //com.qcloud.cos.model.PutObjectResult@44a664f2
    return putObjectResult;
  }
public static void main(String[] args) {
  //上传
  System.out.println(upfile("E:\\tt.txt", "t2est/tt1.txt"));
  //下载
 // System.out.println(downfile("test/tt.txt", "D://tt.txt"));
}
  public static COSObject downfile(String key,String downPath) {
    // 指定要下载到的本地路径
    File downFile = new File(downPath);
    // 指定要下载的文件所在的 bucket 和对象键
    COSCredentials cred = new BasicCOSCredentials(SECRET_ID, SECRET_KEY);
    // 2 设置bucket的区域, COS地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
    ClientConfig clientConfig = new ClientConfig(new Region(AREA));
    // 3 生成cos客户端
    COSClient cosclient = new COSClient(cred, clientConfig);
    GetObjectRequest getObjectRequest = new GetObjectRequest(BUCKET_NAME, key);
    COSObject downObjectMeta = cosclient.getObject(getObjectRequest);
    cosclient.shutdown();
    return downObjectMeta;
  }

  public static void deletefile(String key) {

    initClient().deleteObject(BUCKET_NAME, key);
  }

  public static void closeClient() {
    initClient().shutdown();
  }
}
*/
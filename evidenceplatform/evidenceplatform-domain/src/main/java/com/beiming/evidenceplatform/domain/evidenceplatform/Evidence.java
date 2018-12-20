package com.beiming.evidenceplatform.domain.evidenceplatform;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author : chenpeng
 * @date : 2018-08-17 11:35 上传文件信息
 */
@Entity
@Data
public class Evidence {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name; // 证据名称

  @Column(name = "user_id")
  private Long userId; // 关联用户

  private Long length; // 长度
  private String status = "1"; // 文件状态

  @Column(name = "create_time")
  private Long createTime = new Date().getTime(); // 创建时间

  @Column(name = "client_type")
  private String clientType; // client端类型

  @Column(name = "evidence_type")
  private String evidenceType = "1"; // 上传文件类型

  private String hash; // 文件的hash值
  private String alg = "SHA1"; // 文件hash计算方式
  private String statement; //
  private String uri; // 文件地址


  @Column(name = "file_id")
  private String fileId; // 文件编号

  @Column(name = "voucher_status")
  private String voucherStatus; // 存证函是否有

  @Column(name = "cos_uri")
  private String cosUri; //腾讯云cos 对象存储地址

  private String remarks; // 备注

}

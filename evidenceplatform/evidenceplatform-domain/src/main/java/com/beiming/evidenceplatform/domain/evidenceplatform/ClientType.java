package com.beiming.evidenceplatform.domain.evidenceplatform;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author : chenpeng
 * @date : 2018-08-17 13:42 Client 相关数据,记录client类型
 */
@Entity
@Data
public class ClientType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name = "本地上传"; // client类型名
}

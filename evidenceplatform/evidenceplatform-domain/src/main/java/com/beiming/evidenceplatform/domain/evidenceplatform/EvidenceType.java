package com.beiming.evidenceplatform.domain.evidenceplatform;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * @author : chenpeng
 * @date : 2018-08-17 13:42
 */
@Entity
@Data
public class EvidenceType {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String name = "文本文档"; // 文件分类名
}

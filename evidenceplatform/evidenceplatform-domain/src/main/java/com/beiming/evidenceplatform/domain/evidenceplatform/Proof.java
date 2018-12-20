package com.beiming.evidenceplatform.domain.evidenceplatform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.Data;

/**
 * @Author: chen
 * @Date: Created in 9:20 2018/8/27 存证凭据单
 */
@Data
@Entity
public class Proof { ///!!!修改名字,proof改为ticket

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "proof_url")
  private String proofUrl; // 存证函地址

  @Column(name = "proof_id")
  private String proofId; // 存证函编号

  @OneToOne
  @JoinColumn(name = "evidence_id")
  private Evidence evidence; //存证编号

  @Column(name = "cos_uri")
  private String cosUri; //腾讯云cos 对象存储地址

}

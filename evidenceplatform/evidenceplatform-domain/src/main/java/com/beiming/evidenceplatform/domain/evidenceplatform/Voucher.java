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
 * @Date: Created in 9:09 2018/8/27 存证函
 */
@Entity
@Data
public class Voucher {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "voucher_url")
  private String voucherUrl; // 存证函地址

  @Column(name = "voucher_id")
  private String voucherId; // 存证函编号

  @OneToOne
  @JoinColumn(name = "evidence_id")
  private Evidence evidence; //存证编号

  @Column(name = "cos_uri")
  private String cosUri; //腾讯云cos 对象存储地址

  private String hash; //存证函hash
}

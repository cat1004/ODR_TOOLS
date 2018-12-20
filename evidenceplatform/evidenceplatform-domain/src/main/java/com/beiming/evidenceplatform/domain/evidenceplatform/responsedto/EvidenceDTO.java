package com.beiming.evidenceplatform.domain.evidenceplatform.responsedto;

import java.util.Date;

import lombok.Data;

/**
 * @author : chenpeng
 * @date : 2018-08-21 12:16 存证列表显示页信息
 */
@Data
public class EvidenceDTO {
  private Long id;
  private String name; // 文件名称
  private Long userId; // 关联用户
  private Long length; // 长度
  private String status; // 文件状态
  private Long createTime; // 创建时间
  private String clientType; // client端类型
  private String evidenceType; // 上传文件类型
  private String hash; // 文件的hash值
  private String alg; // 文件hash计算方式
  private String statement; //
  private String uri; // 文件地址
  private String fileId; // 文件编号
  private String voucherUrl; // 存证函
  private String voucherId; // 存证函编号
  private String proofId; // 存证证明编号
  private String proofUrl; // 存证凭单地址
  private String voucherStatus; // 存证函是否有
  private String remarks; // 备注
  private Date createTimes; // 前端显示时间
  private String fileSize; // 前端显示文件大小用
  private String type; //前端显示文件用(带type)
}

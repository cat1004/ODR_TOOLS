package com.beiming.evidenceplatform.domain.dto;

import java.io.Serializable;
import java.util.List;

import com.beiming.evidenceplatform.domain.AuctionDetail;
import com.beiming.evidenceplatform.domain.AuctionNotice;
import com.beiming.evidenceplatform.domain.BankInfo;

import lombok.Data;

/**
 * @ClassName AuctionDetailDTO
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Data
public class AuctionDetailDTO implements Serializable {
  private AuctionDetail auctionDetail; //拍卖详情
  private AuctionNotice auctionNotice; //拍卖通知
  private List<BankInfo> bankInfo; //拍卖关联的银行信息
  private String assistanterId; //辅助后台人员的id或者名字
}

package com.beiming.evidenceplatform.service;

import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;

/**
 * @Auther zhangfucheng
 * @Date 2018/7/3 16:31
 * @Description:TODO
 */
public interface AuctionNoticeService {

  void saveAuctionNotice(AuctionDetailDTO auctionDetailDTO, long auctionDetailId);
}

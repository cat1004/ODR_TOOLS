package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.AuctionDetail;

import tk.mybatis.mapper.common.Mapper;

public interface AuctionDetailMapper extends Mapper<AuctionDetail> {
  long saveAuctionDetail(AuctionDetail auctionDetail);
}
package com.beiming.evidenceplatform.dao;

import java.util.List;

import com.beiming.evidenceplatform.domain.BankInfo;
import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BankTestRequestDTO;

import tk.mybatis.mapper.common.Mapper;

public interface BankInfoMapper extends Mapper<BankInfo> {
  List<BankInfo> selectbankattribute(String usestate);

  void saveBankInfo(AuctionDetailDTO auctionDetailDTO, long auctionDetailId);

  List<BankInfo> selectTest(BankTestRequestDTO dto);
}

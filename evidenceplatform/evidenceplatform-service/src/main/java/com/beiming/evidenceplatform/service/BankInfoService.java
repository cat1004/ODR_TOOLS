
package com.beiming.evidenceplatform.service;

import java.util.List;

import com.beiming.evidenceplatform.domain.BankInfo;
import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BankTestRequestDTO;

/**
 * @Auther zhangfucheng
 * @Date 2018/7/3 16:34
 * @Description:TODO
 */
public interface BankInfoService {

  List<BankInfo> selectbankattribute(String usestate);

  void saveBankInfo(AuctionDetailDTO auctionDetailDTO, long auctionDetailId);
  
  List<BankInfo> selectTest(BankTestRequestDTO dto);
}


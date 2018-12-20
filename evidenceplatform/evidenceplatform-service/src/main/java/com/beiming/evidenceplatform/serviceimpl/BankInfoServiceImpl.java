package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.BankInfoMapper;
import com.beiming.evidenceplatform.domain.BankInfo;
import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;
import com.beiming.evidenceplatform.domin.dto.requestdto.BankTestRequestDTO;
import com.beiming.evidenceplatform.service.BankInfoService;

import java.util.Date;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class BankInfoServiceImpl implements BankInfoService {

  @Resource
  BankInfoMapper bankInfoMapper;

  @Override
  public List<BankInfo> selectbankattribute(String usestate) {
    // TODO Auto-generated method stub

    return bankInfoMapper.selectbankattribute(usestate);
  }

  @Override
  public void saveBankInfo(AuctionDetailDTO auctionDetailDTO, long auctionDetailId) {
    List<BankInfo> bankInfos = auctionDetailDTO.getBankInfo();
    for (BankInfo bankInfo : bankInfos) {
      bankInfo.setAuctionDetailId(auctionDetailId);
      bankInfo.setCreateTime(new Date());
      bankInfo.setCreateUser(auctionDetailDTO.getAssistanterId());
      bankInfoMapper.insertSelective(bankInfo);
    }

  }

  @Override
  public List<BankInfo> selectTest(BankTestRequestDTO dto) {
    // TODO Auto-generated method stub
    return bankInfoMapper.selectTest(dto);

  }

}

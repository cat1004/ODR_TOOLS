package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.AuctionDetailMapper;
import com.beiming.evidenceplatform.domain.AuctionDetail;
import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;
import com.beiming.evidenceplatform.service.AuctionDetailService;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @ClassName AuctionDetailServiceImpl
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Service
public class AuctionDetailServiceImpl implements AuctionDetailService {

  @Resource
  private AuctionDetailMapper auctionDetailMapper;
  @Override
  public long saveAuctionDetail(AuctionDetailDTO auctionDetailDTO) {
    AuctionDetail auctionDetail = auctionDetailDTO.getAuctionDetail();
    auctionDetail.setCreateTime(new Date());
    auctionDetail.setAuctionPrice(auctionDetail.getAuctionPrice());
    auctionDetail.setCreateUser(auctionDetailDTO.getAssistanterId());
    auctionDetail.setAuctionAddPrice(auctionDetail.getAuctionAddPrice());
    auctionDetail.setReservePrice(auctionDetail.getReservePrice());
    return auctionDetailMapper.saveAuctionDetail(auctionDetail);
  }
}

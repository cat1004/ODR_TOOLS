package com.beiming.evidenceplatform.serviceimpl;

import com.beiming.evidenceplatform.dao.AuctionNoticeMapper;
import com.beiming.evidenceplatform.domain.AuctionNotice;
import com.beiming.evidenceplatform.domain.dto.AuctionDetailDTO;
import com.beiming.evidenceplatform.service.AuctionNoticeService;

import java.util.Date;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @ClassName AuctionNoticeServiceImpl
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Service
public class AuctionNoticeServiceImpl implements AuctionNoticeService {

  @Resource
  private AuctionNoticeMapper auctionNoticeMapper;
  @Override
  public void saveAuctionNotice(AuctionDetailDTO auctionDetailDTO, long auctionDetailId) {
    AuctionNotice auctionNotice = auctionDetailDTO.getAuctionNotice();
    auctionNotice.setAuctionDetailId(auctionDetailId);
    auctionNotice.setCreateTime(new Date());
    auctionNotice.setCreateUser(auctionDetailDTO.getAssistanterId());
    auctionNoticeMapper.insertSelective(auctionNotice);
  }
}

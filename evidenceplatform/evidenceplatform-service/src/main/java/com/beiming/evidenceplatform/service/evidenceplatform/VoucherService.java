package com.beiming.evidenceplatform.service.evidenceplatform;

import com.beiming.evidenceplatform.domain.evidenceplatform.Voucher;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;
import com.beiming.evidenceplatform.helper.Result;

import org.apache.ibatis.annotations.Param;

import java.text.ParseException;

/**
 * @Author: chen
 * @Description:
 * @Date: Created in 10:47 2018/8/27
 * @Modified By:
 */
public interface VoucherService {

  VoucherDTO findByVourcher(String evidenceId);

  Long saveVoucher(Voucher voucher);

  void updateData(Voucher voucher);

  Voucher findById(Long id);

  VoucherDTO findByEvidenceId(@Param("evidenceId") Long evidenceId);

  void delVoucher(Long evidenceId);

  Result findVouchers(Integer pageNo, Integer pageSize, Long userId, String name, String evidenceType, String createTime, String endTime, String status, String proofId) throws ParseException;

  Result generateVoucher(String uuid) throws Exception;

  Result voucherJd(String uuid) throws Exception;
}

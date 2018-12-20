package com.beiming.evidenceplatform.dao;

import com.beiming.evidenceplatform.domain.evidenceplatform.Voucher;
import com.beiming.evidenceplatform.domain.evidenceplatform.responsedto.VoucherDTO;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/** @Author: chen @Description: @Date: Created in 11:26 2018/8/27 @Modified By: */
public interface VoucherMapper {
  void deleteVoucher(@Param("evidenceId") Long evidenceId);

  Long saveVoucher(
      @Param("voucherUrl") String voucherUrl,
      @Param("voucherId") String voucherId,
      @Param("evidenceId") Long evidenceId,
      @Param("cosUri") String cosUri,
      @Param("hash") String hash);

  void updateData(Voucher proof);

  Voucher findById(@Param("id") Long id);

  VoucherDTO findByVoucherId(@Param("voucherId") String voucherId);


  List<VoucherDTO> findData(
      @Param("userId") Long userId,
      @Param("name") String name,
      @Param("evidenceType") String evidenceType,
      @Param("startTime") Long startTime,
      @Param("endTime") Long endTime,
      @Param("status") String status,
      @Param("proofId") String proofId,
      @Param("clientType") String clientType);


  VoucherDTO findByEvidenceId(@Param("evidenceId") Long evidenceId);
}

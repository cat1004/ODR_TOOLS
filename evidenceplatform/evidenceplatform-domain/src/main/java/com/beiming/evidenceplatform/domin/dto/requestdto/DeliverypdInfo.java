package com.beiming.evidenceplatform.domin.dto.requestdto;
/**
 * 交割手续办理信息展示
 * 
 * @author The King
 *
 */

import java.util.Date;
import lombok.Data;

@Data
public class DeliverypdInfo {

  private String deliverypdname; // 交割手续进度名称

  private Date deliverypdtime; // 交割时间


}

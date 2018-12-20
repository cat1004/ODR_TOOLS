/**
 * 
 */
package com.beiming.evidenceplatform.domain.dto;

import java.util.Date;
import lombok.Data;

/**
 * @author zhangfc
 * @ClassName: ServicePerDTO
 * @Description: TODO
 * @date 2018年6月26日 上午10:54:32
 */
@Data
public class ServicePerDTO {
  private Long id;
  private String phone;
  private String actualName;
  private String password;
  private String idCard;
  private String role;
  private Date createTime;
  private String createUser;
  private String updateUser;
  private Integer version;
  private String remark;
}

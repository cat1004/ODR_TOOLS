package com.beiming.evidenceplatform.domin.dto.requestdto;

import java.util.List;
import lombok.Data;

/**
 * Created by xiaoqiang on 2018/7/4.
 */
@Data
public class SystemTokenUserDTO {
  private Long userId; //用户标识id
  private String actualName; //用户真实姓名
  private List<String> typeList; //用户权限list
  private String socialIdentify; //服务人员类型，组织还是个人

  private String headImageUrl; //用户头像图片链接

}

package com.beiming.evidenceplatform.domain.dto.responsedto;

import java.io.Serializable;
import lombok.Data;

/**
 * @ClassName PhotoFilesDTO
 * @Description:TODO
 * @author:zhangfucheng
 * @date
 */
@Data
public class PhotoFilesDTO implements Serializable {

  private String id; //关联各个场景的外键
  private String kinds; //关联各个场景 （corporeHouse--房产，delivery--交割手续等）

}

package com.beiming.evidenceplatform.common.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum UserProxyEnum {

  LAWYER("0", "律师"), OTHERS("1", "亲属/推荐公民/公司员工/其他");

  private String code;
  private String name;

  UserProxyEnum(String code, String name) {
    this.code = code;
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public static List<Map<String, Object>> getProxyType() {
    List<Map<String, Object>> result = new ArrayList<>();
    for (UserProxyEnum e : UserProxyEnum.values()) {
      Map<String, Object> map = new LinkedHashMap<>();
      map.put("name", e.getName());
      map.put("code", e.getCode());
      result.add(map);
    }
    return result;
  }
}

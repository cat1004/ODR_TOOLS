package com.beiming.evidenceplatform.common.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author tp Date : 2018/6/5/005 11:21
 */
public enum CaseApplyEnum {
  FINANCIAL("金融类", 1, 1),
  NONFINANCIAL("非金融类", 2, 1),
  // 纠纷类型
  CREDITDISPUTE("信用卡纠纷", 201, 2),
  FINANCIALLENDINGDISPUTE("金融借贷合同纠纷", 202, 2),
  WARRANTYDISPUTE("保证合同纠纷", 203, 2),
  LENDINGDISPUTE("民间借贷纠纷", 204, 2),
  DOCUMENTARY_EVIDENCE("书证", 301, 3),
  PHYSICAL_EVIDENCE("物证", 302, 3),
  CONTRACT("签约合同", 402, 4);

  private String name;

  private Integer code;

  private Integer groupId;

  CaseApplyEnum(String name, Integer code, Integer groupId) {
    this.name = name;
    this.code = code;
    this.groupId = groupId;
  }

  /**
   * 通过code获取name
   */
  public static String getName(int index, int team) {
    try {
      for (CaseApplyEnum e : CaseApplyEnum.values()) {
        if (e.getCode() - index == 0 && e.getGroupId() == team) {
          return e.name;
        }
      }
    } catch (Exception e) {
    }
    return null;
  }

  public static String getName(Integer index) {
    try {
      for (CaseApplyEnum e : CaseApplyEnum.values()) {
        if (e.getCode() - index == 0) {
          return e.name;
        }
      }
    } catch (Exception e) {
    }
    return null;
  }

  /**
   * 通过name获取code
   */
  public static Integer getCodeByName(String name) {
    for (CaseApplyEnum e : CaseApplyEnum.values()) {
      if (name != null && name.equals(e.getName())) {
        return e.code;
      }
    }
    return null;
  }

  public static List<Map<String, Object>> getMapByGroup(Integer group) {
    List<Map<String, Object>> result = new ArrayList<>();
    for (CaseApplyEnum e : CaseApplyEnum.values()) {
      if (e.getGroupId().equals(group)) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("name", e.getName());
        map.put("code", e.getCode());
        result.add(map);
      }
    }
    return result;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }
}

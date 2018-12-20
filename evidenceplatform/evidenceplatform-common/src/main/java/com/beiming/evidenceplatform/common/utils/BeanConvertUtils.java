package com.beiming.evidenceplatform.common.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import org.springframework.beans.BeanUtils;
import org.springframework.util.CollectionUtils;

public class BeanConvertUtils {

  public static <T> T copyBean(Object sourceObj, Class<T> targetCls) {
    T targetObj = null;
    if (sourceObj != null) {
      try {
        targetObj = BeanUtils.instantiate(targetCls);
        BeanUtils.copyProperties(sourceObj, targetObj);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    return targetObj;
  }

  public static <T> List<T> copyList(Collection<?> list, Class<T> targetCls) {
    List<T> result = new ArrayList<T>();
    if (list == null || list.size() == 0) {
      return result;
    }

    for (Object obj : list) {
      result.add(copyBean(obj, targetCls));
    }

    return result;
  }

  /**
   * 将集合中的map转为对应的dto
   */
  public static <T> List<T> map2Java(List<Map<String, Object>> list, Class<T> targetCls) {
    if (CollectionUtils.isEmpty(list)) {
      return new ArrayList<T>();
    }
    List<T> resultList = new ArrayList<>();
    try {
      for (int i = 0; i < list.size(); i++) {
        Map map = list.get(i);
        Set<Entry> set = map.entrySet();
        T targetObject = targetCls.newInstance();
        for (Entry entry : set) {
          Field field = targetCls.getDeclaredField(entry.getKey().toString());
          field.setAccessible(true);
          field.set(targetObject, entry.getValue().toString());
        }
        resultList.add(targetObject);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return resultList;
  }

}

package com.beiming.evidenceplatform.redis.enums;

public enum RedisKey {

  /**
   * 首页数据DTO类KEY
   */
  JUD_HOME_DATA_SHOW(String.class),

  /**
   * 首页图表数据DTO类KEY
   */
  JUD_HOME_CHART_DATA_SHOW(String.class),

  /**
   * 法院列表KEY
   */
  JUD_COURT_LIST_SHOW(String.class),

  /**
   * 短信验证码
   */
  SMS_CODE_JUDSALE(String.class),
  
  /**
   * 图片验证码
   */
  IMG_CODE_JUDSALE(String.class),

  /**
   * 邮件验证码
   */
  EMAIL_CODE_JUDSALE(String.class),

  /**
   * 控制短信频率
   */
  SMS_FREQUENCY_JUDSALE(String.class),

  /**
   * 保存 用户身份证和姓名验证是否成功的key
   */
  IDCARDVALID_CODE_JUDSALE(Boolean.class),

  SHORT_URL_JUDSALE(String.class);

  private Class<?> clazz;

  private <T> RedisKey(Class<T> clazz) {
    this.clazz = clazz;
  }

  @SuppressWarnings("unchecked")
  public <T> Class<T> getClazz() {
    return (Class<T>) clazz;
  }
}

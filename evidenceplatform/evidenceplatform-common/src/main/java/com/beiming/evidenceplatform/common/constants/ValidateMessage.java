package com.beiming.evidenceplatform.common.constants;

/**
 * 校验异常信息
 * 
 * @author lb
 *
 */
public class ValidateMessage {

  /**
   * 可配合validationMessage.properties使用
   */
  public static final String MOBILE_PHONE_INCORRECT = "{mobilePhone.incorrect}";
  public static final String PLATFORM_NOT_NULL = "{platform.notNull}";
  public static final String SMSCODE_NOTBLANK = "{smsCode.notBlank}";
  public static final String REFRESH_TOKEN_NOT_BLANK = "{refreshToken.notBlank}";
  public static final String ID_NOT_BLANK = "{id.notBlank}";
  /** 备注不能为空 */
  public static final String REMARK_CANNOT_EMPTY = "{remark.cannotEmpty}";
  /** 没有文件上传 */
  public static final String NOT_FILE_UPLOAD = "{file.notFileUpload} ";
  /** 无效请求 */
  public static final String ILLEGAL_REQUEST = "{file.illegalRequest}";
  /** 未找到相应文件 */
  public static final String FILE_NOT_FOUND = "{file.fileNotFound}";
  /** 文件名为空 */
  public static final String FILE_NAME_IS_EMPTY = "{file.fileNameIsEmpty}";
  /** 文件大小为空 */
  public static final String FILE_SIZE_IS_EMPTY = "{file.fileSizeIsEmpty}";
  /**
   * 文件处理失败
   */
  public static final String FAIL_FILE = "{file.fail}";
  /**
   * 文件类型有误
   */
  public static final String FILE_TYPE_IS_WRONG = "{file.fileTypeIsWrong}";

  /** 传入参数为空 */
  public static final String PARAMETER_IS_NULL = "{common.parameterIsNull}";
  /** 未找到相应文件 */
  public static final String OTHER_ERROR = "{file.fileNotFound}";

  /**
   * 从数据库里没有找到相关数据
   */
  public static final String RESULT_EMPTY = "{database.result.empty}";

  /** 添加贷款人失败 */
  public static final String ADD_LOAN_USER_FAIL = "{add.loan.user.fail}";
  /** 添加公证失败 */
  public static final String ADD_NOTARY_BUSINESS_FAIL = "{add.notary.business.fail}";
  /** 更新公证失败 */
  public static final String UPDATE_NOTARY_BUSINESS_FAIL = "{update.notary.business.fail}";
  /** 填写的用户为公证员本人，请重新选择公证员 */
  public static final String LOAN_USER_NOT_NOTARY_USER = "{loan.user.not.notary.user}";
  /** 贷款人为您本人，请交由其他同事处理 */
  public static final String LOAN_USER_NOT_BANK_USER = "{loan.user.not.bank.user}";

  /** 名称不能为空 */
  public static final String NAME_NOT_BLANK = "{name.notBlank}";
  /** 身份证格式不对 */
  public static final String ID_CARD_NOT_INCORRECT = "{idCard.incorrect}";
  /** 角色id不能为空 */
  public static final String ROLE_ID_NOT_BLANK = "{roleId.notBlank}";
  /** code不能为空 */
  public static final String CODE_NOT_BLANK = "{code.notBlank}";
  /** value不能为空 */
  public static final String VALUE_NOT_BLANK = "{value.notBlank}";
  /** 驳回理由不能为空 */
  public static final String FAILREASON_NOT_NULL = "{fail.reason.notNull}";
  /** 菜单正在使用 */
  public static final String MENU_BEING_USED = "{menu.beingUsed}";
  /** id不存在 */
  public static final String ID_NOT_EXISTS = "{id.notExists}";
  /** 名称已经存在 */
  public static final String NAME_EXISTS = "{name.exists}";
  /** 菜单正在使用 */
  public static final String ROLE_BEING_USED = "{role.beingUsed}";
  /** 有一方未加入视频 */
  public static final String REAL_START_TIME_NOT_NULL = "{realStartTime.notNull}";
  
  //登录
  /** 用户名不能为空 */
  public static final String LOGIN_NAME_NOT_BLANK = "{loginName.notBlank}";
  /** 手机号不能为空 */
  public static final String MOBILE_PHONE_NOT_BLANK = "{mobilePhone.notBlank}";
  /** 用户类型不能为空 */
  public static final String USER_TYPE_NOT_BLANK = "{userType.notBlank}";
  /** 登录密码不能为空 */
  public static final String PASSWORD_NOT_BLANK = "{password.notBlank}";
  /** 手机验证码不能为空 */
  public static final String PHONE_CODE_NOT_BLANK = "{phoneCode.notBlank}";
  /**登录密码错误   */
  public static final String PASSWORD_IS_WRONG = "{password.isWrong}";
  //注册
  /**用户id不能为空*/
  public static final String USER_ID_NOT_BLANK = "{userId.notBlank}";
  /** 用户名不存在*/
  public static final String USER_NAME_NOT_EXISTS = "{userName.notExists}";
  /**邮箱不能为空 */
  public static final String EMAIL_NOT_BLANK = "{email.notBlank}";
  /**邮箱验证码不能为空 */
  public static final String EMAIL_CODE_NOT_BLANK = "{emailCode.notBlank}";
  /** 企业组织机构代码为空*/
  public static final String ORG_CODE_NOT_BLANK = "{orgCode.notBlank}";
  /** 企业组织机构名称为空*/
  public static final String ORG_NAME_NOT_BLANK = "{orgName.notBlank}";
  /** 身份证编码为空*/
  public static final String ID_CARD_NOT_BLANK = "{idCard.notBlank}";
  /** 姓名为空*/
  public static final String USER_NAME_NOT_BLANK = "{userName.notBlank}";
  
  public static final String EMAIL_INCORRECT = "{email.incorrect}";
  
  //字典表
  /**字典表类型不能为空*/
  public static final String DICT_TYPE_NOT_BLANK = "{dictType.notBlank}";

  //案件类型
  /**
   *  案件类型不能为空   */
  public static final String CASETYPE_NOT_BLANK = "{case.notBlank}";
  /**
   *  纠纷类型不能为空
   */
  public static final String DISPUTETYPE_NOT_BLANK = "{disputeType.notBlank}";

  /** 案件 **/
  public static final String CASE_USER_TYPE_NOT_BLANK = "{case.userType.notBlank}";

  /**
   *  案件关联人员
   */
  public static final String CASEUSER_RELATION_NOT_NULL = "{caseuser.relation.notNull}";

  /**
   *  证据
   */
  public static final String EVIDENCE_NOT_NULL = "{evidence.notNull}";
  /**
   * 纠纷业务要素
   */
  public static final String DISPUTE_NOT_BLANK = "{dispute.notBlank}";
  /**
   *  案件关联人员
   */
  public static final String CASEBASE_NOT_NULL = "{caseBase.notNull}";
}

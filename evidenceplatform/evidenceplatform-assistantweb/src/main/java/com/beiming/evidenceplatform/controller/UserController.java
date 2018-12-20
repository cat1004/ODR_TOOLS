package com.beiming.evidenceplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.domain.User;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.BankInfoService;
import com.beiming.evidenceplatform.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 项目名称：jud-biddersweb 类名称：CommonproblemController 类描述： 创建人：SLL 创建时间：2018年6月27日 下午5:29:56
 * 
 * @version
 */

@Api(value = "竞拍者端-PC平台-用户Controller")
@RestController
@RequestMapping(value = "/api/pc/bidders/user")
public class UserController {
  @Autowired
  UserService userService;
  @Autowired
  BankInfoService bankInfoService;

  @ApiOperation(value = "获取用户详细信息", notes = "根据url的id来获取用户信息")

  @RequestMapping(value = "/PrimaryKey", method = RequestMethod.GET)
  public Result<User> selectuseranddetailinfo() {

    Integer id = Integer.valueOf(ContextUtil.getCurrentUserId());
    if (id != null & id != 0) {
      User u = userService.selectuseranddetailinfo(id);
      if (u == null) {
        return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到该用户信息！");

      }
      return Result.success(u);
    }

    return Result.failed(ErrorCode.USER_ID_IS_EMPTY, "用户ID为空或未查询到该用户信息！");

  }

  /*
   * @ApiOperation(value = "获取银行使用结构", notes = "查看银行属性（贷款、配资）")
   * 
   * @RequestMapping(value = "selectuseranddetailinfo", method = RequestMethod.GET) public
   * Result<Map<String, List<BankInfo>>> selectuseranddetailinfo() { Map<String, List<BankInfo>> map
   * = new HashMap<>(); List<BankInfo> loanlist = bankInfoService.selectbankattribute("1");
   * List<BankInfo> allocationlist = bankInfoService.selectbankattribute("2"); map.put("loan",
   * loanlist); map.put("allocation", allocationlist);
   * 
   * return Result.success(map); }
   */
}

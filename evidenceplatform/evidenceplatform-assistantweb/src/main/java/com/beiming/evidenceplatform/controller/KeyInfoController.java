package com.beiming.evidenceplatform.controller;

import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.domain.KeyInfo;
import com.beiming.evidenceplatform.domain.KeyUse;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.KeyService;
import com.beiming.evidenceplatform.service.KeyUseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangfc
 */
@Slf4j
@RestController
@Api(value = "辅助后台pc端钥匙相关接口", tags = "辅助后台pc端钥匙相关接口")
@RequestMapping("/api/pc/assistant/key")
public class KeyInfoController {

  @Autowired
  private KeyService keyService;
  @Autowired
  private KeyUseService keyUseService;

  /**
   * 新增钥匙
   * @param keys
   * @return
   */
  @PostMapping(value = "/auction/addKey")
  @ApiOperation(value = "新增钥匙", notes = "新增钥匙")
  public Result<?> addKey(@RequestBody List<KeyInfo> keys) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (keys == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    int a = keyService.addKey(keys);
    if (a == -1) {
      return Result.failed(ErrorCode.ACCESS_DENIED, "输入数量有误，库存数量小于0");
    }
    return Result.success();
  }

  /**
   * 获取keys
   * @return Result<?>
   */
  @GetMapping(value = "/auction/getKeys/{corporeHouseId}")
  @ApiOperation(value = "获取keys", notes = "获取keys")
  public Result<?> getKeys(@ApiParam(value = "房产id",
      required = true) @PathVariable(value = "corporeHouseId") String corporeHouseId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (StringUtils.isEmpty(corporeHouseId)) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    List<KeyInfo> keys = keyService.getKeysByCorporeHouseId(Long.valueOf(corporeHouseId));
    return Result.success(keys);
  }

  /**
   * 添加钥匙使用记录
   * @return Result<?>
   */
  @PostMapping(value = "/auction/addKeyUsing")
  @ApiOperation(value = "添加钥匙使用记录", notes = "添加钥匙使用记录")
  public Result<?> addKeyUsing(@RequestBody KeyUse keyUse) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (keyUse == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    int result = keyUseService.addKeyUsing(keyUse);
    if (result == 1) {
      return Result.failed(ErrorCode.ACCESS_DENIED, "库存所剩无几");
    }
    if (result == -1) {
      return Result.failed(ErrorCode.ACCESS_DENIED, "未接受到参数");
    }
    return Result.success();
  }

  /**
   * 获取该房产下所有钥匙使用记录
   * @return Result<?>
   */
  @PostMapping(value = "/auction/{corporeHouseId}")
  @ApiOperation(value = "获取该房产下所有钥匙使用记录", notes = "获取该房产下所有钥匙使用记录")
  public Result<?> getKeyUses(@ApiParam(value = "标的物id",
      required = true) @PathVariable(value = "corporeHouseId") String corporeHouseId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    List<KeyUse> keyUses = keyUseService.getKeyUsesByCorporeHouseId(Long.valueOf(corporeHouseId));
    if (keyUses == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "未查询到相应数据");
    }
    return Result.success(keyUses);
  }

  /**
   * 更新钥匙使用记录
   *
   * @return Result<?>
   */
  @PostMapping(value = "/auction/updateKeyUse")
  @ApiOperation(value = "更新钥匙使用记录", notes = "更新钥匙使用记录")
  public Result<?> updateKeyUse(@RequestBody KeyUse keyUse) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (keyUse == null) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    int result = keyUseService.updateKeyUse(keyUse);
    if (result == 1) {
      return Result.failed(ErrorCode.NO_STOCKS, "库存所剩无几");
    }
    if (result == -1) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    return Result.success();
  }

  /**
   * 归还钥匙
   * @return Result<?>
   */
  @GetMapping(value = "/auction/returnKey/{keyUseId}")
  @ApiOperation(value = "归还钥匙", notes = "归还钥匙")
  public Result<?> returnKey(@ApiParam(value = "钥匙使用记录的id",
      required = true) @PathVariable(value = "keyUseId") String keyUseId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    if (StringUtils.isEmpty(keyUseId)) {
      return Result.failed(ErrorCode.EMPTY_PARAMS, "参数为空");
    }
    keyUseService.returnKey(keyUseId);
    return Result.success();
  }

}

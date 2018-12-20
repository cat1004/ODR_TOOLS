package com.beiming.evidenceplatform.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.beiming.evidenceplatform.common.enums.ErrorCode;
import com.beiming.evidenceplatform.common.page.Page;
import com.beiming.evidenceplatform.domain.Message;
import com.beiming.evidenceplatform.helper.ContextUtil;
import com.beiming.evidenceplatform.helper.Result;
import com.beiming.evidenceplatform.service.MessageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

/**
 * 系统消息处理接口
 */
@Api(value = "系统消息控制层", tags = {"系统消息控制层"})
@RestController
@RequestMapping("/message")
@Slf4j
public class MessageController {

  @Autowired
  private MessageService messageService;

  /**
   * 系统消息列表 列表分页+筛选+查询
   */
  @GetMapping(value = "/listBypage")
  @ApiOperation(value = "查询系统消息列表", notes = "查询的参数有page封装信息与查询的封装信息Message")
  public Result<?> getCorporeList(@ApiParam(value = "page信息") Page page,
      @ApiParam(value = "Message信息") Message message) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    Map<String, Object> map = messageService.findItemByPage(page, message, currentUserId);
    List<Message> list = (List<Message>) map.get("list");
    if (message != null && list.size() == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "当前查询条件下没有系统信息");
    } else {
      return Result.success(map);
    }
  }

  /**
   * 系统消息详情，并将该消息置位已读
   */
  @GetMapping(value = "/messageDetail/{msgId}")
  @ApiOperation(value = "查看消息详情", notes = "")
  public Result<?> messageDetail(@ApiParam("消息id") @PathVariable("msgId") String msgId) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    Message msg = messageService.getMessageDetail(Long.parseLong(msgId));
    if (msg == null) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "当前系统消息id下没有系统信息");
    } else {
      return Result.success(msg);
    }
  }

  /**
   * 删除系统消息 批量删除
   */
  @DeleteMapping(value = "/deleteMessages")
  @ApiOperation(value = "删除系统消息", notes = "msgIds批量删除时id之间用','号分割，例如：1,2,3")
  public Result<?> deleteMessages(
      @ApiParam("消息id列表") @RequestParam(value = "msgIds") String msgIds) {
    String currentUserId = ContextUtil.getCurrentUserId();
    if (currentUserId == null || "".equals(currentUserId)) {
      return Result.failed(ErrorCode.AUTH_TOKEN_EMPTY, "请先登录");
    }
    int effectRow = messageService.deleteMessages(msgIds);
    if (effectRow == 0) {
      return Result.failed(ErrorCode.RESULT_EMPTY, "删除系统信息失败");
    } else {
      return Result.success("系统消息删除成功");
    }
  }

}

package com.beiming.evidenceplatform.websocket;
//package com.beiming.libra.websocket;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.beiming.libra.common.Assert;
//import com.beiming.libra.common.enums.ErrorCode;
//import com.beiming.libra.common.enums.UserTypeEnum;
//import com.beiming.libra.common.utils.SpringContextUtil;
//import com.beiming.libra.dao.NotaryMeetingMapper;
//import com.beiming.libra.dao.UserMapper;
//import com.beiming.libra.domain.NotaryMeeting;
//import com.beiming.libra.domain.User;
//import com.beiming.libra.domain.dto.requestdto.CommonIdRequestDTO;
//import com.beiming.libra.domain.dto.responsedto.AccessVideoMeetingResponseDTO;
//import com.beiming.libra.domain.dto.responsedto.VideoUserInfoResponseDTO;
//import com.beiming.libra.security.TokenGenerator;
//import com.beiming.libra.service.NotaryBusinessService;
//import com.beiming.libra.service.VideoInfoService;
//import com.beiming.libra.service.impl.NotaryBusinessServiceImpl;
//import com.beiming.libra.service.impl.VideoInfoServiceImpl;
//import com.beiming.libra.video.util.LiveUtil;
//import com.google.common.base.Joiner;
//import com.google.common.collect.Maps;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//import java.util.Map.Entry;
//import java.util.concurrent.ConcurrentHashMap;
//import javax.websocket.OnClose;
//import javax.websocket.OnError;
//import javax.websocket.OnMessage;
//import javax.websocket.OnOpen;
//import javax.websocket.Session;
//import javax.websocket.server.PathParam;
//import javax.websocket.server.ServerEndpoint;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Controller;
//
///**
// * package: com.beiming.libra.controller describe: TODO create_user: xiet create_date: 2018/5/15
// * create_time: 下午6:58
// **/
//@Controller
//@Slf4j
//@ServerEndpoint("/accessMeeting/{param}")
//public class MeetingRoomService {
//
//  private static Map<String, MeetingRoomService> connections =
//      new ConcurrentHashMap<>();
//  private static Map<String, AccessVideoMeetingResponseDTO> homesByHomeId = Maps.newHashMap();
//
//  private Session session;
//  private String role;
//  private String homeId;
//  private String token;
//  private Long userId;
//  private String userType;
//  private String userName;
//
//  @OnOpen
//  public void start(@PathParam("param") String param, Session session) {
//    log.info("有新连接" + param);
//    String[] arrs = param.split(",");
//
//    this.session = session;
//    this.homeId = arrs[0];
//    this.token = arrs[1];
//    this.role = param;
//    TokenGenerator tokenGenerator = SpringContextUtil.getBean(TokenGenerator.class);
//    this.userId = tokenGenerator.generateAuthenticationToken(token).getUserId();
//    UserMapper userMapper = SpringContextUtil.getBean(UserMapper.class);
//    User userQuery = new User();
//    userQuery.setId(userId);
//    userQuery = userMapper.selectOne(userQuery);
//    Assert.isNotNull(userQuery, ErrorCode.USER_NOT_EXISTS);
//    this.userType = userQuery.getUserType();
//    this.userName = userQuery.getUserName();
//    connections.put(role, this);
//    AccessVideoMeetingResponseDTO accessVideoMeetingResponseDTO = createOrJoinRoom(homeId, userId,
//        this.userType, this.userName);
//    broadcast(
//        "{\"event\":\"getPush\",\"data\":" + JSON.toJSONString(accessVideoMeetingResponseDTO) + "}",
//        homeId);
//  }
//
//  @OnClose
//  public void end() {
//    log.info("有链接断开");
//    handlerCloseOrError();
//  }
//
//
//  @OnMessage
//  public void onMessage(String message) {
//    try {
//      log.info("房间号[" + homeId + "]的客户[" + userId + "]发来新消息" + message);
//      JSONObject msg = JSONObject.parseObject(message, JSONObject.class);
//      if (msg.containsKey("event")) {
//        if ("endVideo".equals(msg.getString("event"))) {
//          Assert.isEquals(UserTypeEnum.NOTARY.getCode(), this.userType,
//              ErrorCode.CAN_NOT_OPERATE_CURRENT_RECORD);
//          updateNotaryBusinessFinish(this.homeId);
//        }
//        if ("onShow".equals(msg.getString("event"))) {
//          AccessVideoMeetingResponseDTO accessVideoMeetingResponseDTO = homesByHomeId.get(homeId);
//          message =
//              "{\"event\":\"onShow\",\"data\":" + JSON.toJSONString(accessVideoMeetingResponseDTO)
//                  + "}";
//        }
//        broadcast(message, homeId);
//      }
//    } catch (Exception e) {
//      log.info("传输报异常：" + e);
//    }
//  }
//
//  private void updateNotaryBusinessFinish(String meetingRoomId) {
//    NotaryMeetingMapper notaryMeetingMapper = SpringContextUtil.getBean(NotaryMeetingMapper.class);
//    NotaryMeeting notaryMeetingQuery = new NotaryMeeting();
//    notaryMeetingQuery.setMeetingRoomId(meetingRoomId);
//    NotaryMeeting notaryMeeting = notaryMeetingMapper.selectOne(notaryMeetingQuery);
//    Assert.isNotNull(notaryMeeting, ErrorCode.NOTARY_BUSINESS_NOT_EXIST);
//    NotaryBusinessService notaryBusinessService = SpringContextUtil
//        .getBean(NotaryBusinessServiceImpl.class);
//    CommonIdRequestDTO commonIdRequestDTO = new CommonIdRequestDTO();
//    commonIdRequestDTO.setId(notaryMeeting.getBusinessId());
//    notaryBusinessService.notaryFinish(commonIdRequestDTO);
//  }
//
//  @OnError
//  public void onError(Throwable t) {
//    log.error("sokect异常", t);
//    handlerCloseOrError();
//  }
//
//  private synchronized void handlerCloseOrError() {
//    AccessVideoMeetingResponseDTO accessVideoMeetingResponseDTO = homesByHomeId.get(homeId);
//    if (accessVideoMeetingResponseDTO != null) {
//      connections.remove(role);
//      List<VideoUserInfoResponseDTO> videoUserInfoResponseDTOList = accessVideoMeetingResponseDTO
//          .getVideoUserInfoList();
//      for (VideoUserInfoResponseDTO videoUserInfoResponseDTO : videoUserInfoResponseDTOList) {
//        if (userId.equals(videoUserInfoResponseDTO.getUserId())) {
//          videoUserInfoResponseDTOList.remove(videoUserInfoResponseDTO);
//          break;
//        }
//      }
//      if (videoUserInfoResponseDTOList.size() == 0) {
//        homesByHomeId.remove(homeId);
//        log.info("房间已关闭");
//        return;
//      }
//      String msg = "{\"event\":\"breakVideo\",\"data\":\"" + userId + "\"}";
//      log.info(role + "退出了链接");
//      broadcast(msg, homeId);
//    }
//  }
//
//  private static synchronized AccessVideoMeetingResponseDTO createOrJoinRoom(String homeId,
//      Long userId, String userType, String userName) {
//    AccessVideoMeetingResponseDTO accessVideoMeetingResponseDTO = homesByHomeId.get(homeId);
//    //判断用户是否可以进入该房间
//    VideoInfoService videoInfoService = SpringContextUtil
//        .getBean(VideoInfoServiceImpl.class);
//    accessVideoMeetingResponseDTO = videoInfoService
//        .accessVideoMeeting(homeId, userId, userType, userName, accessVideoMeetingResponseDTO);
//
//    homesByHomeId.put(homeId, accessVideoMeetingResponseDTO);
//    log.info("createOrJoinRoom result is " + accessVideoMeetingResponseDTO.toString());
//    return accessVideoMeetingResponseDTO;
//
//  }
//
//  /**
//   * 加入房间返回拉流给各个终端
//   */
//  private static AccessVideoMeetingResponseDTO joinRoom(String homeId, Long userId) {
//
//    AccessVideoMeetingResponseDTO accessVideoMeetingResponseDTO = homesByHomeId.get(homeId);
//    List<VideoUserInfoResponseDTO> videoUserInfoResponseDTOList = accessVideoMeetingResponseDTO
//        .getVideoUserInfoList();
//
//    VideoUserInfoResponseDTO videoUserInfoResponseDTOTmp = null;
//    for (VideoUserInfoResponseDTO videoUserInfoResponseDTO : videoUserInfoResponseDTOList) {
//      if (userId.equals(videoUserInfoResponseDTO.getUserId()) && ""
//          .equals(videoUserInfoResponseDTO.getPushUrl())
//          && "".equals(videoUserInfoResponseDTO.getAcceleratePlayUrl())) {
//        String pushUrl = LiveUtil.genPushUrl(Joiner.on("_").join(userId, homeId));
//        String acceleratePlayUrl = LiveUtil
//            .genAcceleratePlayUrl(Joiner.on("_").join(userId, homeId));
//        videoUserInfoResponseDTO.setPushUrl(pushUrl);
//        videoUserInfoResponseDTO.setAcceleratePlayUrl(acceleratePlayUrl);
//        homesByHomeId.put(homeId, accessVideoMeetingResponseDTO);
//        break;
//      }
//    }
//    log.info("joinRoom result is " + accessVideoMeetingResponseDTO.toString());
//    return accessVideoMeetingResponseDTO;
//  }
//
//  private static void broadcast(String msg, String homeId) {
//    for (Entry<String, MeetingRoomService> client : connections.entrySet()) {
//      try {
//        synchronized (client) {
//          if (homeId.equals(client.getValue().homeId)) {
//            log.info("sendText to {}, msg is {}", client.getValue().userId, msg);
//            client.getValue().session.getBasicRemote().sendText(msg);
//          }
//        }
//      } catch (IOException e) {
//        closeClient(client, homeId);
//      }
//    }
//  }
//
//  private static void closeClient(Entry<String, MeetingRoomService> client, String homeId) {
//    try {
//      log.error("Chat Error: Failed to send message to client");
//      connections.remove(client);
//      client.getValue().session.close();
//    } catch (IOException e) {
//      // Ignore
//    }
//    String message = String.format("* %s %s",
//        client.getValue().role, " has been disconnected.");
//    broadcast(message, homeId);
//  }
//}

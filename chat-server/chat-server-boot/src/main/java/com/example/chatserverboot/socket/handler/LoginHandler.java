package com.example.chatserverboot.socket.handler;

import com.alibaba.fastjson.JSON;
import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.*;
import com.example.chatserverboot.infrastructure.common.Constants;
import com.example.chatserverboot.infrastructure.common.SocketChannelUtil;
import com.example.chatserverboot.socket.MyBizHandler;
import com.kzm.chat.protocal.login.LoginRequest;
import com.kzm.chat.protocal.login.LoginResponse;
import com.kzm.chat.protocal.login.dto.ChatRecordDto;
import com.kzm.chat.protocal.login.dto.ChatTalkDto;
import com.kzm.chat.protocal.login.dto.GroupsDto;
import com.kzm.chat.protocal.login.dto.UserFriendDto;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class LoginHandler extends MyBizHandler<LoginRequest> {

    public LoginHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void mychannelRead(Channel channel, LoginRequest msg) {
        log.info("登录请求处理:{}", JSON.toJSONString(msg));

        if (!userService.checkAuth(msg.getUserId(), msg.getUserPassword())) {
            //登录失败
            channel.writeAndFlush(new LoginResponse(false));
            return;
        }

        //登录成功绑定channel
        //1.绑定用户id
        SocketChannelUtil.addChannel(msg.getUserId(),channel);
        //todo 绑定群组

        //3反馈消息
        LoginResponse loginResponse=new LoginResponse();

        UserInfo userInfo = userService.getUserInfo(msg.getUserId());


        // 3.2 对话框
        List<TalkBoxInfo> talkBoxInfoList = userService.queryTalkBoxInfoList(msg.getUserId());
        for (TalkBoxInfo talkBoxInfo : talkBoxInfoList) {
            ChatTalkDto chatTalk=new ChatTalkDto();
            chatTalk.setTalkId(talkBoxInfo.getTalkId());
            chatTalk.setTalkType(talkBoxInfo.getTalkType());
            chatTalk.setTalkName(talkBoxInfo.getTalkName());
            chatTalk.setTalkHead(talkBoxInfo.getTalkHead());
            chatTalk.setTalkSketch(talkBoxInfo.getTalkSketch());
            chatTalk.setTalkDate(talkBoxInfo.getTalkDate());
            loginResponse.getChatTalkList().add(chatTalk);

            // 好友；聊天消息
            if (Constants.TalkType.Friend.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Friend.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    // 自己发的消息
                    if (msgType) {
                        chatRecordDto.setUserId(chatRecordInfo.getUserId());
                        chatRecordDto.setMsgUserType(0); // 消息类型[0自己/1好友]
                    }
                    // 好友发的消息
                    else {
                        chatRecordDto.setUserId(chatRecordInfo.getFriendId());
                        chatRecordDto.setMsgUserType(1); // 消息类型[0自己/1好友]
                    }
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    chatRecordDtoList.add(chatRecordDto);
                }
                chatTalk.setChatRecordList(chatRecordDtoList);
            }
            // 群组；聊天消息
            else if (Constants.TalkType.Group.getCode().equals(talkBoxInfo.getTalkType())) {
                List<ChatRecordDto> chatRecordDtoList = new ArrayList<>();
                List<ChatRecordInfo> chatRecordInfoList = userService.queryChatRecordInfoList(talkBoxInfo.getTalkId(), msg.getUserId(), Constants.TalkType.Group.getCode());
                for (ChatRecordInfo chatRecordInfo : chatRecordInfoList) {
                    UserInfo memberInfo = userService.getUserInfo(chatRecordInfo.getUserId());
                    ChatRecordDto chatRecordDto = new ChatRecordDto();
                    chatRecordDto.setTalkId(talkBoxInfo.getTalkId());
                    chatRecordDto.setUserId(memberInfo.getUserId());
                    chatRecordDto.setUserNickName(memberInfo.getUserNickName());
                    chatRecordDto.setUserHead(memberInfo.getUserHead());
                    chatRecordDto.setMsgContent(chatRecordInfo.getMsgContent());
                    chatRecordDto.setMsgDate(chatRecordInfo.getMsgDate());
                    boolean msgType = msg.getUserId().equals(chatRecordInfo.getUserId());
                    chatRecordDto.setMsgUserType(msgType ? 0 : 1); // 消息类型[0自己/1好友]
                    chatRecordDto.setMsgType(chatRecordInfo.getMsgType());
                    chatRecordDtoList.add(chatRecordDto);
                }
                chatTalk.setChatRecordList(chatRecordDtoList);
            }


        }

        //群组
        List<GroupsInfo> groupsINfoList=userService.queryUserGroupInfoList(msg.getUserId());
        for (GroupsInfo groupsInfo : groupsINfoList) {
            GroupsDto groups=new GroupsDto();
            groups.setGroupId(groupsInfo.getGroupId());
            groups.setGroupName(groupsInfo.getGroupName());
            groups.setGroupHead(groupsInfo.getGroupHead());
            loginResponse.getGroupsLIst().add(groups);
        }
        //好友
        List<UserFriendInfo> userFriendInfosList=userService.queryUserFriendInfoList(msg.getUserId());
        for (UserFriendInfo userFriendInfo : userFriendInfosList) {
            UserFriendDto userFriendDto=new UserFriendDto();
            userFriendDto.setFriendId(userFriendInfo.getFriendId());
            userFriendDto.setFriendHead(userFriendInfo.getFriendHead());
            userFriendDto.setFriendName(userFriendInfo.getFriendName());
            loginResponse.getUserFirendList().add(userFriendDto);
        }
        loginResponse.setSuccess(true);
        loginResponse.setUserHead(userInfo.getUserHead());
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickName());
        channel.writeAndFlush(loginResponse);


    }
}

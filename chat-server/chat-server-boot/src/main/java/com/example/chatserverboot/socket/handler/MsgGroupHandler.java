package com.example.chatserverboot.socket.handler;

import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.ChatRecordInfo;
import com.example.chatserverboot.domain.user.model.UserInfo;
import com.example.chatserverboot.infrastructure.common.Constants;
import com.example.chatserverboot.infrastructure.common.SocketChannelUtil;
import com.example.chatserverboot.socket.MyBizHandler;
import com.kzm.chat.protocal.msg.MsgGroupRequest;
import com.kzm.chat.protocal.msg.MsgGroupResponse;
import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgGroupHandler extends MyBizHandler<MsgGroupRequest> {

    public MsgGroupHandler(UserService userService){
        super(userService);
    }

    @Override
    public void mychannelRead(Channel channel, MsgGroupRequest msg) {
        //获取群组通信管道
        ChannelGroup channelGroup= SocketChannelUtil.getChannelGroup(msg.getTalkId());

        if (channelGroup==null){
            channelGroup=SocketChannelUtil.addChannelGroup(msg.getTalkId(),channel);
        }
        //异步写库
       userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(),msg.getTalkId(),msg.getMsgText(),
               msg.getMsgType(),msg.getMsgDate(), Constants.TalkType.Group.getCode()));

        //群发消息
        UserInfo userInfo = userService.getUserInfo(msg.getUserId());
        MsgGroupResponse msgGroupResponse=new MsgGroupResponse();
        msgGroupResponse.setTalkId(msg.getTalkId());
        msgGroupResponse.setUserId(msg.getUserId());
        msgGroupResponse.setUserNickName(userInfo.getUserNickName());
        msgGroupResponse.setUserHead(userInfo.getUserHead());
        msgGroupResponse.setMsg(msg.getMsgText());
        msgGroupResponse.setMsgType(msg.getMsgType());
        msgGroupResponse.setMsgDate(msg.getMsgDate());
        channelGroup.writeAndFlush(msgGroupResponse);

    }
}

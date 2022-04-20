package com.example.chatserverboot.socket.handler;

import com.alibaba.fastjson.JSON;
import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.ChatRecordInfo;
import com.example.chatserverboot.infrastructure.common.Constants;
import com.example.chatserverboot.infrastructure.common.SocketChannelUtil;
import com.example.chatserverboot.socket.MyBizHandler;
import com.kzm.chat.protocal.msg.MsgRequest;
import com.kzm.chat.protocal.msg.MsgResponse;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MsgHandler extends MyBizHandler<MsgRequest> {

    public MsgHandler(UserService userService) {
        super(userService);
    }

    @Override
    public void mychannelRead(Channel channel, MsgRequest msg) {
        log.info("消息处理器,{}", JSON.toJSONString(msg));
        //异步写库
        userService.asyncAppendChatRecord(new ChatRecordInfo(msg.getUserId(), msg.getFriendId(), msg.getMsgText(),
                msg.getMsgType(), msg.getMsgDate()));
        //添加对话框[如果对方没有你的对话框则添加]
        userService.addTalkBoxInfo(msg.getFriendId(), msg.getUserId(), Constants.TalkType.Friend.getCode());
        
        //获取好友通信信道
        Channel firendChannel = SocketChannelUtil.getChannel(msg.getFriendId());
        if (firendChannel==null){
            log.info("用户:{}未登录",msg.getFriendId());
            return;
        }
        //发送消息
        firendChannel.writeAndFlush(new MsgResponse(msg.getUserId(), msg.getMsgText(), msg.getMsgType(),msg.getMsgDate()));
    }
}

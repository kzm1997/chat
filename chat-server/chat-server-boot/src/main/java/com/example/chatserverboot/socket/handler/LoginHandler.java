package com.example.chatserverboot.socket.handler;

import com.alibaba.fastjson.JSON;
import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.UserInfo;
import com.example.chatserverboot.infrastructure.common.SocketChannelUtil;
import com.example.chatserverboot.socket.MyBizHandler;
import com.kzm.chat.protocal.login.LoginRequest;
import com.kzm.chat.protocal.login.LoginResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import lombok.extern.slf4j.Slf4j;

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

        loginResponse.setSuccess(true);
        loginResponse.setUserHead(userInfo.getUserHead());
        loginResponse.setUserId(userInfo.getUserId());
        loginResponse.setUserNickName(userInfo.getUserNickName());

        channel.writeAndFlush(loginResponse);


    }
}

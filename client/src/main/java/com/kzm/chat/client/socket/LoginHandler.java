package com.kzm.chat.client.socket;

import com.kzm.chat.client.application.UIService;
import com.kzm.chat.protocal.login.LoginResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {

    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private UIService uiService;

    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }

    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        logger.info("消息内容:"+msg);

        if (!msg.isSuccess()) {
            logger.info("登录失败");
            return;
        }
        Platform.runLater(() -> {
             uiService.getLoginMethod().doLoginSuccess(); //跳转登录框
             //设置用户信息
             uiService.getChat().setUserInfo(msg.getUserId(),msg.getUserNickName(),msg.getUserHead());

        });
    }
}

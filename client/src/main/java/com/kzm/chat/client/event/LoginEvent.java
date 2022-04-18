package com.kzm.chat.client.event;

import com.kzm.chat.client.infrastructure.util.BeanUtil;
import com.kzm.chat.protocal.login.LoginRequest;
import com.kzm.chat.ui.view.login.ILoginEvent;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEvent implements ILoginEvent {
    
    private Logger logger= LoggerFactory.getLogger(LoginEvent.class);
    
    public void doLoginCheck(String userId, String userPassword) {
           //获取channel
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new LoginRequest(userId,userPassword));

    }
}

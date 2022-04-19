package com.kzm.chat.client.event;

import com.kzm.chat.client.infrastructure.util.BeanUtil;
import com.kzm.chat.protocal.msg.MsgRequest;
import com.kzm.chat.ui.view.chat.IchatEvent;
import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.util.Date;

public class ChatEvent implements IchatEvent {
    
    private Logger logger= LoggerFactory.getLogger(ChatEvent.class);
    
    @Override
    public void doQuit() {
        logger.info("退出登录");
        
    }

    @Override
    public void doSendMsg(String userId, String talkId, Integer talkType, String msg, Integer msgType, Date msgDate) {
        Channel channel= BeanUtil.getBean("channel",Channel.class);

        //好友
        if (0==talkType){
            channel.writeAndFlush(new MsgRequest(userId,talkId,msg,msgType,msgDate));
        }
        //todo 群聊

    }
}

package com.kzm.chat.client.event;

import com.kzm.chat.ui.view.chat.IchatEvent;
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

    }
}

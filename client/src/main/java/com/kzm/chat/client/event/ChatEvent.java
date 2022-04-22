package com.kzm.chat.client.event;

import com.kzm.chat.client.infrastructure.util.BeanUtil;
import com.kzm.chat.protocal.friend.SearchFriendRequest;
import com.kzm.chat.protocal.msg.MsgGroupRequest;
import com.kzm.chat.protocal.msg.MsgRequest;
import com.kzm.chat.protocal.talk.DeTalkRequest;
import com.kzm.chat.ui.view.chat.IchatEvent;
import io.netty.channel.Channel;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;
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
        if (1==talkType){
            channel.writeAndFlush(new MsgGroupRequest(talkId,userId,msg,msgType,msgDate));
        }

    }

    @Override
    public void doEventDelTalkUser(String userId, String talkId) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new DeTalkRequest(userId,talkId));
    }

    @Override
    public void addFriendLuck(String userId, ListView<Pane> listView) {
        Channel channel=BeanUtil.getBean("channel",Channel.class);
        channel.writeAndFlush(new SearchFriendRequest(userId,""));
    }

    @Override
    public void doFriendLuckSearch(String userId, String text) {
        Channel channel = BeanUtil.getBean("channel", Channel.class);
        channel.writeAndFlush(new SearchFriendRequest(userId, text));
    }
}

package com.kzm.chat.client.socket;

import com.kzm.chat.client.application.UIService;
import com.kzm.chat.protocal.msg.MsgGroupResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

public class MsgGroupHandler extends SimpleChannelInboundHandler<MsgGroupResponse> {

    private UIService uiService;

    public MsgGroupHandler(UIService uiService){
        this.uiService=uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgGroupResponse msg) throws Exception {
        Platform.runLater(()->{
            uiService.getChat().addTalkMsgGroupLeft(msg.getTalkId(),msg.getUserId(),
                    msg.getUserNickName(),msg.getUserHead(),msg.getMsg(),msg.getMsgType(),msg.getMsgDate(),true,false,true);
        });
    }
}

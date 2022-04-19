package com.kzm.chat.client.socket;

import com.kzm.chat.client.application.UIService;
import com.kzm.chat.protocal.msg.MsgResponse;
import com.kzm.chat.ui.view.chat.IchatMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;

public class MsgHandler extends SimpleChannelInboundHandler<MsgResponse> {

    private UIService uiService;

    public MsgHandler(UIService uiService) {
        this.uiService = uiService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MsgResponse msg) throws Exception {
        IchatMethod ichatMethod=uiService.getChat();
        Platform.runLater(()->{
            ichatMethod.addTalkMsgUserLeft(msg.getFriendId(),msg.getMsgText(),msg.getMsgType(),msg.getMsgDate(),true,false,true);
        });
    }
}

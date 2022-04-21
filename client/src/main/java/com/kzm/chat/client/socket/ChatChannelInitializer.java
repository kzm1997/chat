package com.kzm.chat.client.socket;

import com.kzm.chat.client.application.UIService;
import com.kzm.chat.codec.ObjDecoder;
import com.kzm.chat.codec.ObjEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.serialization.ObjectDecoder;

public class ChatChannelInitializer extends ChannelInitializer<SocketChannel> {

    private UIService uiService;

    public ChatChannelInitializer(UIService uiService) {
        this.uiService = uiService;
    }

    protected void initChannel(SocketChannel socketChannel) throws Exception {

        //对象传输处理器
        socketChannel.pipeline().addLast(new ObjDecoder());
        socketChannel.pipeline().addLast(new LoginHandler(uiService));
        socketChannel.pipeline().addLast(new MsgHandler(uiService));
        socketChannel.pipeline().addLast(new MsgGroupHandler(uiService));
        socketChannel.pipeline().addLast(new ObjEncoder());
    }
}

package com.example.chatserverboot.socket;

import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.socket.handler.LoginHandler;
import com.example.chatserverboot.socket.handler.MsgHandler;
import com.kzm.chat.codec.ObjDecoder;
import com.kzm.chat.codec.ObjEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;

import javax.annotation.Resource;

public class ChatChannelInitializer extends ChannelInitializer<SocketChannel> {

    private UserService userService;

    public ChatChannelInitializer(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println(2222);
        //对象传输处理器
        socketChannel.pipeline().addLast(new ObjDecoder());
        socketChannel.pipeline().addLast(new LoginHandler(userService));
        socketChannel.pipeline().addLast(new MsgHandler(userService));

        socketChannel.pipeline().addLast(new ObjEncoder());
    }
}

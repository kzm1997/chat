package com.example.chatserverboot.socket;

import com.example.chatserverboot.application.UserService;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class MyBizHandler<T> extends SimpleChannelInboundHandler<T> {


    protected UserService userService;

    public MyBizHandler(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, T t) throws Exception {
        mychannelRead(channelHandlerContext.channel(),t);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        log.info("客户端连接通知:{}",ctx.channel());
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        super.channelInactive(ctx);
        log.info("连接断开了");

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        System.out.println("关闭" + ctx.channel().id());
    }

    public abstract void mychannelRead(Channel channel, T msg);
}

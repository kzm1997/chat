package com.example.chatserverboot.socket;


import com.example.chatserverboot.application.UserService;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LoggingHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

@Service
@Slf4j
public class NettyServer implements Callable<Channel> {

    @Resource
    private UserService userService;

    private final EventLoopGroup parentGroup = new NioEventLoopGroup(2);
    private final EventLoopGroup childGroup = new NioEventLoopGroup();
    private Channel channel;

    @Override
    public Channel call() throws Exception {
        ChannelFuture channelFuture = null;
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(parentGroup, childGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new LoggingHandler())
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChatChannelInitializer(userService));
            channelFuture = b.bind(new InetSocketAddress(8000)).syncUninterruptibly();
            this.channel=channelFuture.channel();
            channel.closeFuture().sync();
        } catch (Exception e) {
            e.printStackTrace();
            log.error("socket server start error:"+e.getMessage());
        } finally {
            if (channelFuture!=null&& channelFuture.isSuccess()){
                log.info("socket server start done.");
            }else {
                log.error("socket server start error");
            }
        }
        return channel;
    }

    public void destroy() {
        if (null == channel) return;
        channel.close();
        parentGroup.shutdownGracefully();
        childGroup.shutdownGracefully();
    }

    public Channel channel() {
        return channel;
    }

}

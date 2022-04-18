package com.example.chatserverboot;

import com.example.chatserverboot.socket.NettyServer;
import io.netty.channel.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
@SpringBootApplication
public class ChatServerBootApplication implements CommandLineRunner {

    @Resource
    private NettyServer nettyServer;


    public static void main(String[] args) {
        SpringApplication.run(ChatServerBootApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
     log.info("NettyServer启动");

        Future<Channel> future = Executors.newFixedThreadPool(2).submit(nettyServer);
        Channel channel = future.get();
        if (channel==null){
            throw new RuntimeException("netty server start error");
        }
        while (!channel.isActive()){
            log.info("nettyServer 启动服务");
            Thread.sleep(500);
        }
        log.info("NettyServer启动服务完成 {}", channel.localAddress());

    }
}

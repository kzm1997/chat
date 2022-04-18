package com.kzm.chat.client;

import com.kzm.chat.client.application.UIService;
import com.kzm.chat.client.event.ChatEvent;
import com.kzm.chat.client.event.LoginEvent;
import com.kzm.chat.client.socket.NettyClient;
import com.kzm.chat.ui.view.chat.ChatController;
import com.kzm.chat.ui.view.chat.IchatMethod;
import com.kzm.chat.ui.view.login.ILoginMethod;
import com.kzm.chat.ui.view.login.LoginController;
import io.netty.channel.Channel;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.event.ChangeEvent;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Application extends javafx.application.Application {

    private Logger logger = LoggerFactory.getLogger(Application.class);

    private static ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void start(Stage primaryStage) throws Exception {

        IchatMethod chat = new ChatController(new ChatEvent());

        //登录组件
        ILoginMethod login = new LoginController(new LoginEvent(), chat);
        login.doShow();

        UIService uiService = new UIService();
        uiService.setChat(chat);
        uiService.setLoginMethod(login);

        logger.info("netty客户端连接服务启动");
        NettyClient nettyClient = new NettyClient(uiService);
        Future<Channel> future = executorService.submit(nettyClient);
        Channel channel = future.get();


        if (null == channel) throw new RuntimeException("netty client start error channel is null");


        while (!nettyClient.isActive()) {
            logger.info("NettyClient启动服务..");
            Thread.sleep(500);
        }
        logger.info("NettyClient连接服务完成 {}", channel.localAddress());


    }
}

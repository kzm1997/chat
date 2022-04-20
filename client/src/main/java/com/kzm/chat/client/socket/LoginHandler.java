package com.kzm.chat.client.socket;

import com.kzm.chat.client.application.UIService;
import com.kzm.chat.protocal.login.LoginResponse;
import com.kzm.chat.protocal.login.dto.ChatRecordDto;
import com.kzm.chat.protocal.login.dto.ChatTalkDto;
import com.kzm.chat.ui.view.chat.IchatMethod;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import javafx.application.Platform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

public class LoginHandler extends SimpleChannelInboundHandler<LoginResponse> {

    private static Logger logger = LoggerFactory.getLogger(LoginHandler.class);

    private UIService uiService;

    public LoginHandler(UIService uiService) {
        this.uiService = uiService;
    }

    protected void channelRead0(ChannelHandlerContext ctx, LoginResponse msg) throws Exception {
        logger.info("消息内容:"+msg);

        if (!msg.isSuccess()) {
            logger.info("登录失败");
            uiService.getLoginMethod().doLoginError();//登录失败逻辑
            return;
        }
        Platform.runLater(() -> {
             uiService.getLoginMethod().doLoginSuccess(); //跳转登录框
            IchatMethod chat = uiService.getChat();
            //设置用户信息
             uiService.getChat().setUserInfo(msg.getUserId(),msg.getUserNickName(),msg.getUserHead());

             //对话框
            List<ChatTalkDto> chatTalkList = msg.getChatTalkList();
            if (chatTalkList!=null){
                 chatTalkList.forEach(talk->{
                     uiService.getChat().addTalkBox(0, talk.getTalkType(), talk.getTalkId(), talk.getTalkName(), talk.getTalkHead(), talk.getTalkSketch(), talk.getTalkDate(), true);
                     switch (talk.getTalkType()){
                       //好友
                         case 0:
                             List<ChatRecordDto> userRecordList=talk.getChatRecordList();
                             if (null==userRecordList||userRecordList.isEmpty())return;
                             for (int i=userRecordList.size()-1;i>=0;i--){
                                 ChatRecordDto chatRecordDto = userRecordList.get(i);
                                 //自己的消息
                                 if (0==chatRecordDto.getMsgType()){
                                     uiService.getChat().addTalkMsgRight(chatRecordDto.getTalkId(), chatRecordDto.getMsgContent(), chatRecordDto.getMsgType(),
                                             chatRecordDto.getMsgDate(), true, false, false);
                                     continue;
                                 }
                                 //他人的消息
                                 if (1==chatRecordDto.getMsgType()){ 
                                     uiService.getChat().addTalkMsgUserLeft(chatRecordDto.getTalkId(), chatRecordDto.getMsgContent(),
                                             chatRecordDto.getMsgType(), chatRecordDto.getMsgDate(), true, false, false);
                                 }
                             }
                     }
                 });
            }



            
        });
    }
}

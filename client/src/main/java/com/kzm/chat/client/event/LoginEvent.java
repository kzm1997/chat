package com.kzm.chat.client.event;

import com.kzm.chat.ui.view.login.ILoginEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoginEvent implements ILoginEvent {
    
    private Logger logger= LoggerFactory.getLogger(LoginEvent.class);
    
    public void doLoginCheck(String userId, String userPassword) {
           //获取channel
    }
}

package com.kzm.chat.ui.view.chat;

import com.kzm.chat.ui.view.UIObject;
import javafx.scene.control.TextArea;

public abstract class ChatInit extends UIObject {
    
    private static final String VIEW_NAME="/fxml/chat/chat.fxml";
    
    public String userId;
    
    public String userNickName;
    
    public String userNead;
    
    public IchatEvent chatEvent;
    
    public TextArea txt_input; //输入框
    
    
    
    
}

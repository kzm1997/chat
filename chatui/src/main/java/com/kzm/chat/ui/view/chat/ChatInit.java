package com.kzm.chat.ui.view.chat;

import com.kzm.chat.ui.view.UIObject;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import javax.xml.soap.Text;
import java.io.IOException;

public abstract class ChatInit extends UIObject {
    
    private static final String VIEW_NAME="/fxml/chat/chat.fxml";
    
    public String userId;
    
    public String userNickName;
    
    public String userNead;
    
    public IchatEvent chatEvent;
    
    public TextArea txt_input; //输入框
    
    ChatInit(IchatEvent chatEvent){
        this.chatEvent=chatEvent;

        try {
            root= FXMLLoader.load(getClass().getResource(VIEW_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);//#000000
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/chat/img/head/logo.png"));
        obtain();//绑定按钮
        initView();
        initEventDefine();
    }
    
    public void obtain(){
        txt_input=$("txt_input",TextArea.class);
    }
    
    public Parent root(){
        return super.root;
    }
    
    
    
    
    
    
    
}

package com.kzm.chat.ui.view.login;

import com.kzm.chat.ui.view.UIObject;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

import java.io.IOException;

public abstract class LoginInit extends UIObject {
    
    private static final String VIEW_NAME="/fxml/login/login.fxml";
    
    protected ILoginEvent loginEvent;
    
    public Button login_min_btn; //登录窗口最小化

    public Button login_close_btn; //登录窗口退出
 
    public Button login_btn; //登录按钮
   
    public TextField userNameFiled; //登录名文本框
 
    public PasswordField passwordField; //密码文本

    public Label errorLabel; //密码错误
    
    LoginInit(ILoginEvent loginEvent){
        this.loginEvent=loginEvent;

        try {
            root= FXMLLoader.load(getClass().getResource(VIEW_NAME));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene=new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        setScene(scene);
        initStyle(StageStyle.TRANSPARENT);
        setResizable(false);
        this.getIcons().add(new Image("/fxml/chat/img/head/logo.png"));
        obtain();
        initView();
        initEventDefine();
    }
    
    private void obtain(){
        login_min_btn=$("login_min",Button.class);
        login_close_btn=$("login_close",Button.class);
        login_btn=$("login_button",Button.class);
        userNameFiled=$("userId",TextField.class);
        passwordField=$("userPassword",PasswordField.class);
        errorLabel=$("errorLabel",Label.class);

    }
    
    
}


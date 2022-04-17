package com.kzm.chat.ui.view;

import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.stage.Stage;

/**
 * 窗口抽象类
 */
public abstract class UIObject extends Stage {

    protected Parent root;

    private double xOffset;
    private double yOffset;
    
    
    public <T> T $(String id,Class<T>clazz){
        return (T) root.lookup("#"+id);
    }

    public void move() {
        root.setOnMousePressed(event -> {
            xOffset = getX() - event.getScreenX();
            yOffset = getY() - event.getScreenY();
            root.setCursor(Cursor.CLOSED_HAND);
        });
        root.setOnMouseDragged(event -> {
            setX(event.getScreenX() + xOffset);
            setY(event.getScreenY() + yOffset);
        });
        root.setOnMouseReleased(event -> {
            root.setCursor(Cursor.DEFAULT);
        });
    }

    public double x() {
        return getX();
    }

    public double y() {
        return getY();
    }

    public double width() {
        return getWidth();
    }
    
    public double height(){
        return getHeight();
    }
    
    //初始化页面
    public abstract void  initView();
    
    //初始化事件定义
    public abstract void initEventDefine();
    

}

package com.kzm.chat.ui.view.chat.element.group_bar_chat;

import com.kzm.chat.ui.util.DateUtil;
import com.kzm.chat.ui.util.Ids;
import com.kzm.chat.ui.view.chat.data.RemindCount;
import com.kzm.chat.ui.view.chat.data.TalkBoxData;
import com.kzm.chat.ui.view.chat.data.TalkData;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import javax.xml.crypto.Data;
import java.util.Date;

public class ElemntTalk {

    private Pane pane;

    private Label head;

    private Label nickName; //昵称
    private Label msgSketch; //信息简述
    private Label msgData; //信息时间
    private Label msgRemind; //消息提醒
    private Button delete; //删除对话框按钮

    private ListView<Pane> infoBoxList; //初始化填充消息对话框

    public ElemntTalk(String talkId, Integer talkType, String talkName, String talkHead, String talkSketch, Date talkDate){
        pane=new Pane();
        pane.setId(Ids.ELementTalkId.createTalkPaneId(talkId));
        pane.setUserData(new TalkBoxData(talkId,talkType,talkName,talkHead));
        pane.setPrefSize(270,80);
        pane.getStyleClass().add("talkElement");
        ObservableList<Node> children=pane.getChildren();

        //头像
        head=new Label();
        head.setPrefSize(50,50);
        head.setLayoutX(15);
        head.setLayoutY(15);
        head.getStyleClass().add("element_head");
        head.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", talkHead));
        children.add(head);

        //昵称区域
        nickName=new Label();
        nickName.setPrefSize(140,25);
        nickName.setLayoutX(80);
        nickName.setLayoutY(15);
        nickName.setText(talkName);
        nickName.getStyleClass().add("element_nickName");
        children.add(nickName);

        //信息简述
        msgSketch=new Label();
        msgSketch.setId(Ids.ELementTalkId.createTalkPaneId(talkId));
        msgSketch.setPrefSize(200,25);
        msgSketch.setLayoutX(80);
        msgSketch.setLayoutY(40);
        msgSketch.getStyleClass().add("element_nickName");
        children.add(msgSketch);

        //信息时间
        msgData=new Label();
        msgData.setId(Ids.ELementTalkId.createTalkPaneId(talkId));
        msgData.setPrefSize(60,25);
        msgData.setLayoutX(220);
        msgData.setLayoutY(15);
        msgData.getStyleClass().add("element_msgData");
        children.add(msgData);

        // 填充；信息简述 & 信息时间
        fillMsgSketch(talkSketch, talkDate);

        // 消息提醒
        msgRemind = new Label();
        msgRemind.setPrefSize(15, 15);
        msgRemind.setLayoutX(60);
        msgRemind.setLayoutY(5);
        msgRemind.setUserData(new RemindCount());
        msgRemind.setText("");
        msgRemind.setVisible(false);
        msgRemind.getStyleClass().add("element_msgRemind");
        children.add(msgRemind);

        // 删除对话框按钮
        delete = new Button();
        delete.setVisible(false);
        delete.setPrefSize(4, 4);
        delete.setLayoutY(26);
        delete.setLayoutX(-8);
        delete.getStyleClass().add("element_delete");
        children.add(delete);

        // 消息框[初始化，未装载]，承载对话信息内容，点击按钮时候填充
        infoBoxList = new ListView<>();
        infoBoxList.setId(Ids.ELementTalkId.createInfoBoxListId(talkId));
        infoBoxList.setUserData(new TalkData(talkName, talkHead));
        infoBoxList.setPrefSize(850, 560);
        infoBoxList.getStyleClass().add("infoBoxStyle");

    }

    public Pane pane() {
        return pane;
    }

    public ListView<Pane> infoBoxList() {
        return infoBoxList;
    }

    public Button delete() {
        return delete;
    }

    public void  fillMsgSketch(String talkSketch, Date talkDate){
       if (null!=talkSketch){
           if (talkSketch.length()>30){
               talkSketch=talkSketch.substring(0,30);
               msgSketch.setText(talkSketch);
           }
       }
       if (null==talkDate){
           talkDate=new Date();
       }
       //信息格式化
        String talkSimpleDate = DateUtil.simpleDate(talkDate);
        msgData.setText(talkSimpleDate);
    }

    public void clearMsgSketch() {
        msgSketch.setText("");
    }

    public Label msgRemind() {
        return msgRemind;
    }
}

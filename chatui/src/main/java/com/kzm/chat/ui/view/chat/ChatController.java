package com.kzm.chat.ui.view.chat;


import com.kzm.chat.ui.util.CacheUtil;
import com.kzm.chat.ui.util.Ids;
import com.kzm.chat.ui.view.chat.data.RemindCount;
import com.kzm.chat.ui.view.chat.data.TalkData;
import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElementInfoBox;
import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElemntTalk;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;

public class ChatController extends ChatInit implements IchatMethod {


    private ChatView chatView;
    private ChatEventDefine chatEventDefine;


    public ChatController(IchatEvent ichatEvent) {
        super(ichatEvent);
    }

    @Override
    public void initView() {
        chatView = new ChatView(this, chatEvent);
    }

    @Override
    public void initEventDefine() {
        chatEventDefine = new ChatEventDefine(this, chatEvent, this);
    }

    @Override
    public void doShow() {
        super.show();
    }

    @Override
    public void setUserInfo(String userId, String userNickName, String userHead) {
        super.userId = userId;
        super.userNickName = userNickName;
        super.userNead = userHead;
        Button button = $("bar_portrait", Button.class);
        button.setStyle(String.format("-fx-background-image: url('/fxml/chat/img/head/%s.png')", userHead));
    }


    @Override
    public void addTalkMsgUserLeft(String talkId, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElemntTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        TalkData talkUserData = (TalkData) listView.getUserData();
        Pane left = new ElementInfoBox().Left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg, msgType);
        // 消息填充
        listView.getItems().add(left);
        // 滚动条
        listView.scrollTo(left);
        talkElement.fillMsgSketch(0 == msgType ? msg : "[表情]", msgDate);
        // 设置位置&选中
        chatView.updateTalkListIdxANdSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

    }

    /**
     * 私有方法
     * 填充对话框消息内容
     *
     * @param talkElement 对话框元素
     * @param talkName    对话框名称
     */
    private void fillInfoBox(ElemntTalk talkElement, String talkName) {
        String talkId = talkElement.pane().getUserData().toString();
        // 填充对话列表
        Pane info_pane_box = $("info_pane_box", Pane.class);
        String boxUserId = (String) info_pane_box.getUserData();
        // 判断是否已经填充[talkId]，当前已填充则返回
        if (talkId.equals(boxUserId)) return;
        ListView<Pane> listView = talkElement.infoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(listView);
        // 对话框名称
        Label info_name = $("info_name", Label.class);
        info_name.setText(talkName);
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {
        //填充到对话框
        ListView<Pane> talkList = $("talkList", ListView.class);
        //判断对话框是否有该对象
        ElemntTalk elemntTalk = CacheUtil.talkMap.get(talkId);

        if (null != elemntTalk) {
            Node talkNode = talkList.lookup("#" + Ids.ELementTalkId.createTalkPaneId(talkId));
            if (null == talkNode) {
                talkList.getItems().add(talkIdx, elemntTalk.pane());
                //填充对话框消息栏
                fillInfoBox(elemntTalk, talkName);
            }
            if (selected) {
                //设置选中
                talkList.getSelectionModel().select(elemntTalk.pane());
            }
            //填充对话框消息栏
            fillInfoBox(elemntTalk, talkName);
            return;
        }
        ElemntTalk talkElement = new ElemntTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        CacheUtil.talkMap.put(talkId, talkElement);
        //填充到对话框
        ObservableList<Pane> items = talkList.getItems();
        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // 添加到第一个位置
        } else {
            items.add(talkElementPane);           // 顺序添加
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        // 对话框元素点击事件
        talkElementPane.setOnMousePressed(event -> {
            // 填充消息栏
            fillInfoBox(talkElement, talkName);
            // 清除消息提醒
            Label msgRemind = talkElement.msgRemind();
            msgRemind.setUserData(new RemindCount(0));
            msgRemind.setVisible(false);
        });
        // 鼠标事件[移入/移出]
        talkElementPane.setOnMouseEntered(event -> {
            talkElement.delete().setVisible(true);
        });
        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        // 填充对话框消息栏
        fillInfoBox(talkElement, talkName);
        // 从对话框中删除
        talkElement.delete().setOnMouseClicked(event -> {
            talkList.getItems().remove(talkElementPane);
            $("info_pane_box", Pane.class).getChildren().clear();
            $("info_pane_box", Pane.class).setUserData(null);
            $("info_name", Label.class).setText("");
            talkElement.infoBoxList().getItems().clear();
            talkElement.clearMsgSketch();
            chatEvent.doEventDelTalkUser(super.userId, talkId);
        });

    }

    @Override
    public void addTalkMsgRight(String talkId, String msg, Integer msgType, Date msgData, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        ElemntTalk talkElement = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = talkElement.infoBoxList();
        Pane right = new ElementInfoBox().right(userNickName, userNead, msg, msgType);
        //消息填充
        listView.getItems().add(right);
        listView.scrollTo(right);
        talkElement.fillMsgSketch(0==msgType?msg:"[表情]",msgData);
        chatView.updateTalkListIdxANdSelected(0,talkElement.pane(),talkElement.msgRemind(),idxFirst,selected,isRemind);
    }
}

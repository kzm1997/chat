package com.kzm.chat.ui.view.chat;

import com.kzm.chat.ui.view.chat.data.RemindCount;
import com.kzm.chat.ui.view.chat.data.TalkBoxData;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * 聊天窗口展示和操作
 */
public class ChatView {

    private ChatInit chatInit;
    private IchatEvent ichatEvent;

    public ChatView(ChatInit chatInit, IchatEvent chatEvent) {
        this.chatInit = chatInit;
        this.ichatEvent = chatEvent;

        //1.好友列表添加工具方法('新朋友')

    }


    void updateTalkListIdxANdSelected(int talkType, Pane talkElementPane, Label msgRemindLabel, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        //对话框ID,好友ID
        TalkBoxData talkBoxData = (TalkBoxData) talkElementPane.getUserData();
        //填充到对话框
        ListView<Pane> talkList = chatInit.$("talkList", ListView.class);
        //如果对话为空,初始化[置顶,选中,提醒]
        if (talkList.getItems().isEmpty()) {
            if (idxFirst) {
                talkList.getItems().add(0, talkElementPane);
            }
            if (selected) {
                talkList.getSelectionModel().select(talkElementPane);
            }
            isRemind(msgRemindLabel,talkType,isRemind);
            return;
        }
        //对话不为空
        Pane firstPane=talkList.getItems().get(0);
        //判断元素是否是首位,如果是首位可返回不需要重新设置首位
        if (talkBoxData.getTalkId().equals(((TalkBoxData)firstPane.getUserData()).getTalkId())){
            Pane selectedItem=talkList.getSelectionModel().getSelectedItem();
            //选中判断:如果第一个元素已经选中,那么清空消息提醒
            if (null==selectedItem){
                isRemind(msgRemindLabel,talkType,isRemind);
                return;
            }
             TalkBoxData selectedItemUserData = (TalkBoxData) selectedItem.getUserData();
            if (null!=selectedItemUserData&&talkBoxData.getTalkId().equals(selectedItemUserData.getTalkId())){
                clearRemind(msgRemindLabel);
            }else {
                isRemind(msgRemindLabel, talkType, isRemind);
            }
            return;
        }
        if (idxFirst){
            talkList.getItems().remove(talkElementPane);
            talkList.getItems().add(0,talkElementPane);
        }
        if (selected){
            //设置对话框
            talkList.getSelectionModel().select(talkElementPane);
        }
        isRemind(msgRemindLabel, talkType, isRemind);
    }
    
    private void clearRemind(Label msgRemindLabel){
        msgRemindLabel.setVisible(false);
        msgRemindLabel.setUserData(new RemindCount(0));
    }

    /**
     * 消息提醒
     *
     * @param msgRemindLabel
     * @param talkType
     * @param isRemind
     */
    private void isRemind(Label msgRemindLabel, int talkType, Boolean isRemind) {
        if (!isRemind) return;
        msgRemindLabel.setVisible(true);
        //群组直接展示小红点
        RemindCount remindCount = (RemindCount) msgRemindLabel.getUserData();
        //超过99展示省略号
        if (remindCount.getCount() > 99) {
            msgRemindLabel.setText("..");
            return;
        }
        int count = remindCount.getCount() + 1;
        msgRemindLabel.setUserData(new RemindCount(count));
        msgRemindLabel.setText(String.valueOf(count));
    }
}

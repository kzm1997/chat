package com.kzm.chat.ui.view.chat;


import com.kzm.chat.ui.util.CacheUtil;
import com.kzm.chat.ui.view.chat.data.TalkData;
import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElementInfoBox;
import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElemntTalk;
import javafx.scene.control.Button;
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
        ElemntTalk elemntTalk = CacheUtil.talkMap.get(talkId);
        ListView<Pane> listView = elemntTalk.infoBoxList();
        TalkData talkUserData = (TalkData) listView.getUserData();
        Pane left = new ElementInfoBox().Left(talkUserData.getTalkName(), talkUserData.getTalkHead(), msg, msgType);

        //消息填充
        listView.getItems().add(left);
        //滚动条
        listView.scrollTo(left);
        elemntTalk.fillMsgSketch(0 == msgType ? msg : "[表情]", msgDate);
        //设置位置&选中
        chatView.updateTalkListIdxAndSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

    }
}

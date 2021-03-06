package com.kzm.chat.ui.view.chat;


import com.kzm.chat.ui.util.CacheUtil;
import com.kzm.chat.ui.util.Ids;
import com.kzm.chat.ui.view.chat.data.RemindCount;
import com.kzm.chat.ui.view.chat.data.TalkData;
import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElementInfoBox;
import com.kzm.chat.ui.view.chat.element.group_bar_chat.ElemntTalk;
import com.kzm.chat.ui.view.chat.element.group_bar_friend.ElementFriendGroup;
import com.kzm.chat.ui.view.chat.element.group_bar_friend.ElementFriendUser;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Pane;

import java.util.Date;
import java.util.List;

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
        // ????????????
        listView.getItems().add(left);
        // ?????????
        listView.scrollTo(left);
        talkElement.fillMsgSketch(0 == msgType ? msg : "[??????]", msgDate);
        // ????????????&??????
        chatView.updateTalkListIdxANdSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);

    }

    /**
     * ????????????
     * ???????????????????????????
     *
     * @param talkElement ???????????????
     * @param talkName    ???????????????
     */
    private void fillInfoBox(ElemntTalk talkElement, String talkName) {
        String talkId = talkElement.pane().getUserData().toString();
        // ??????????????????
        Pane info_pane_box = $("info_pane_box", Pane.class);
        String boxUserId = (String) info_pane_box.getUserData();
        // ????????????????????????[talkId]???????????????????????????
        if (talkId.equals(boxUserId)) return;
        ListView<Pane> listView = talkElement.infoBoxList();
        info_pane_box.setUserData(talkId);
        info_pane_box.getChildren().clear();
        info_pane_box.getChildren().add(listView);
        // ???????????????
        Label info_name = $("info_name", Label.class);
        info_name.setText(talkName);
    }

    @Override
    public void addTalkBox(int talkIdx, Integer talkType, String talkId, String talkName, String talkHead, String talkSketch, Date talkDate, Boolean selected) {
        //??????????????????
        ListView<Pane> talkList = $("talkList", ListView.class);
        //?????????????????????????????????
        ElemntTalk elemntTalk = CacheUtil.talkMap.get(talkId);

        if (null != elemntTalk) {
            Node talkNode = talkList.lookup("#" + Ids.ELementTalkId.createTalkPaneId(talkId));
            if (null == talkNode) {
                talkList.getItems().add(talkIdx, elemntTalk.pane());
                //????????????????????????
                fillInfoBox(elemntTalk, talkName);
            }
            if (selected) {
                //????????????
                talkList.getSelectionModel().select(elemntTalk.pane());
            }
            //????????????????????????
            fillInfoBox(elemntTalk, talkName);
            return;
        }
        ElemntTalk talkElement = new ElemntTalk(talkId, talkType, talkName, talkHead, talkSketch, talkDate);
        CacheUtil.talkMap.put(talkId, talkElement);
        //??????????????????
        ObservableList<Pane> items = talkList.getItems();
        Pane talkElementPane = talkElement.pane();
        if (talkIdx >= 0) {
            items.add(talkIdx, talkElementPane);  // ????????????????????????
        } else {
            items.add(talkElementPane);           // ????????????
        }
        if (selected) {
            talkList.getSelectionModel().select(talkElementPane);
        }
        // ???????????????????????????
        talkElementPane.setOnMousePressed(event -> {
            // ???????????????
            fillInfoBox(talkElement, talkName);
            // ??????????????????
            Label msgRemind = talkElement.msgRemind();
            msgRemind.setUserData(new RemindCount(0));
            msgRemind.setVisible(false);
        });
        // ????????????[??????/??????]
        talkElementPane.setOnMouseEntered(event -> {
            talkElement.delete().setVisible(true);
        });
        talkElementPane.setOnMouseExited(event -> {
            talkElement.delete().setVisible(false);
        });
        // ????????????????????????
        fillInfoBox(talkElement, talkName);
        // ?????????????????????
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
        //????????????
        listView.getItems().add(right);
        listView.scrollTo(right);
        talkElement.fillMsgSketch(0 == msgType ? msg : "[??????]", msgData);
        chatView.updateTalkListIdxANdSelected(0, talkElement.pane(), talkElement.msgRemind(), idxFirst, selected, isRemind);
    }


    @Override
    public void addTalkMsgGroupLeft(String talkId, String userId, String userNickName, String userHead, String msg, Integer msgType, Date msgDate, Boolean idxFirst, Boolean selected, Boolean isRemind) {
        if (super.userId.equals(userId)) return;
        ElemntTalk elemntTalk = CacheUtil.talkMap.get(talkId);
        if (elemntTalk==null){

        }
        ListView<Pane> listView = elemntTalk.infoBoxList();
        Pane left=new ElementInfoBox().Left(userNickName,userHead,msg,msgType);
        //????????????
        listView.getItems().add(left);
        //??????
        listView.scrollTo(left);

        elemntTalk.fillMsgSketch(0==msgType?userNickName+":"+msg:userNickName+":[??????]",msgDate);
        //????????????&??????
        chatView.updateTalkListIdxANdSelected(1,elemntTalk.pane(),elemntTalk.msgRemind(),idxFirst,selected,isRemind);

    }

    @Override
    public void addFriendGroup(String groupId, String groupName, String groupHead) {
        ElementFriendGroup elementFriendGroup=new ElementFriendGroup(groupId,groupName,groupHead);
        Pane pane= elementFriendGroup.pane();
        //?????????????????????
        ListView<Pane> groupListView=$("groupListView",ListView.class);
        ObservableList<Pane> items = groupListView.getItems();
        items.add(pane);
        groupListView.setPrefHeight(80*items.size());
        $("friendGroupList",Pane.class).setPrefHeight(80*items.size());

        // ??????????????????[?????????????????????]??????????????????????????????????????????????????????
        Pane detailContent = new Pane();
        detailContent.setPrefSize(850, 560);
        detailContent.getStyleClass().add("friendGroupDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(groupId);
        sendMsgButton.getStyleClass().add("friendGroupSendMsgButton");
        sendMsgButton.setPrefSize(176, 50);
        sendMsgButton.setLayoutX(337);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("????????????");
        chatEventDefine.doEventOpenFriendUserSendMsg(sendMsgButton, groupId, groupName, groupHead);
        children.add(sendMsgButton);

        //??????????????????
        pane.setOnMousePressed(event->{
            clearListViewSelectAll($("friendList", ListView.class), $("userListView", ListView.class));
            chatView.setContentPaneBox(groupId, groupName, detailContent);
        });
        chatView.setContentPaneBox(groupId,groupName,detailContent);
    }

    @Override
    public void addFriendUser(boolean selected, String userFriendId, String userFriendNickName, String userFriendHead) {
        ElementFriendUser friendUser = new ElementFriendUser(userFriendId, userFriendNickName, userFriendHead);
        Pane pane = friendUser.pane();
        // ?????????????????????
        ListView<Pane> userListView = $("userListView", ListView.class);
        ObservableList<Pane> items = userListView.getItems();
        items.add(pane);
        userListView.setPrefHeight(80 * items.size());
        $("friendUserList", Pane.class).setPrefHeight(80 * items.size());
        // ??????
        if (selected) {
            userListView.getSelectionModel().select(pane);
        }

        // ??????????????????[?????????????????????]??????????????????????????????????????????????????????
        Pane detailContent = new Pane();
        detailContent.setPrefSize(850, 560);
        detailContent.getStyleClass().add("friendUserDetailContent");
        ObservableList<Node> children = detailContent.getChildren();

        Button sendMsgButton = new Button();
        sendMsgButton.setId(userFriendId);
        sendMsgButton.getStyleClass().add("friendUserSendMsgButton");
        sendMsgButton.setPrefSize(176, 50);
        sendMsgButton.setLayoutX(337);
        sendMsgButton.setLayoutY(450);
        sendMsgButton.setText("????????????");
        chatEventDefine.doEventOpenFriendUserSendMsg(sendMsgButton, userFriendId, userFriendNickName, userFriendHead);
        children.add(sendMsgButton);
        // ??????????????????
        pane.setOnMousePressed(event -> {
            clearListViewSelectAll($("friendList", ListView.class), $("groupListView", ListView.class));
            chatView.setContentPaneBox(userFriendId, userFriendNickName, detailContent);
        });
        chatView.setContentPaneBox(userFriendId, userFriendNickName, detailContent);
    }
}

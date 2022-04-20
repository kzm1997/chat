package com.example.chatserverboot.infrastructure.repository;

import com.example.chatserverboot.domain.user.model.ChatRecordInfo;
import com.example.chatserverboot.domain.user.model.TalkBoxInfo;
import com.example.chatserverboot.domain.user.model.UserInfo;
import com.example.chatserverboot.domain.user.repository.IUserRepository;
import com.example.chatserverboot.infrastructure.dao.IChatRecordDao;
import com.example.chatserverboot.infrastructure.dao.IGroupsDao;
import com.example.chatserverboot.infrastructure.dao.ITalkBoxDao;
import com.example.chatserverboot.infrastructure.dao.IUserDao;
import com.example.chatserverboot.infrastructure.po.ChatRecord;
import com.example.chatserverboot.infrastructure.po.Groups;
import com.example.chatserverboot.infrastructure.po.TalkBox;
import com.example.chatserverboot.infrastructure.po.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static com.example.chatserverboot.infrastructure.common.Constants.TalkType.Friend;
import static com.example.chatserverboot.infrastructure.common.Constants.TalkType.Group;


@Repository("userRepository")
public class UserRepository implements IUserRepository {

    @Resource
    IUserDao userDao;

    @Resource
    ITalkBoxDao talkBoxDao;

    @Resource
    IGroupsDao groupsDao;

    @Resource
    IChatRecordDao chatRecordDao;



    @Override
    public String queryUserPassword(String userId) {
        String password = userDao.queryUserPassword(userId);
        return password;
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        User user = userDao.queryUserById(userId);
        return new UserInfo(user.getUserId(), user.getUserNickName(), user.getUserHead());
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        List<TalkBoxInfo> talkBoxInfoList = new ArrayList<>();
        List<TalkBox> talkBoxList = talkBoxDao.queryTalkBoxList(userId);
        for (TalkBox talkBox : talkBoxList) {
            TalkBoxInfo talkBoxInfo = new TalkBoxInfo();
            if (Friend.getCode().equals(talkBox.getTalkType())) {
                User user = userDao.queryUserById(talkBox.getTalkId());
                talkBoxInfo.setTalkType(Friend.getCode());
                talkBoxInfo.setTalkId(user.getUserId());
                talkBoxInfo.setTalkName(user.getUserNickName());
                talkBoxInfo.setTalkHead(user.getUserHead());
            } else if (Group.getCode().equals(talkBox.getTalkType())) {
                Groups groups = groupsDao.queryGroupsById(talkBox.getTalkId());
                talkBoxInfo.setTalkType(Group.getCode());
                talkBoxInfo.setTalkId(groups.getGroupId());
                talkBoxInfo.setTalkName(groups.getGroupName());
                talkBoxInfo.setTalkHead(groups.getGroupHead());
            }
            talkBoxInfoList.add(talkBoxInfo);
        }
        return talkBoxInfoList;
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        List<ChatRecordInfo> chatRecordInfoList = new ArrayList<>();
        List<ChatRecord> list = new ArrayList<>();
        if (Friend.getCode().equals(talkType)){
            list = chatRecordDao.queryUserChatRecordList(talkId, userId);
        } else if (Group.getCode().equals(talkType)){
            list =  chatRecordDao.queryGroupsChatRecordList(talkId, userId);
        }
        for (ChatRecord chatRecord : list) {
            ChatRecordInfo chatRecordInfo = new ChatRecordInfo();
            chatRecordInfo.setUserId(chatRecord.getUserId());
            chatRecordInfo.setFriendId(chatRecord.getFriendId());
            chatRecordInfo.setMsgContent(chatRecord.getMsgContent());
            chatRecordInfo.setMsgType(chatRecord.getMsgType());
            chatRecordInfo.setMsgDate(chatRecord.getMsgDate());
            chatRecordInfoList.add(chatRecordInfo);
        }
        return chatRecordInfoList;
    }
}

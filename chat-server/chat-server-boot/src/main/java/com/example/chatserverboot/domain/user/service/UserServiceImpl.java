package com.example.chatserverboot.domain.user.service;

import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.*;
import com.example.chatserverboot.domain.user.repository.IUserRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IUserRepository userRepository;



    @Override
    public boolean checkAuth(String userId, String userPassword) {

        if (StringUtils.isEmpty(userPassword)) {
            return false;
        }
        String password = userRepository.queryUserPassword(userId);
        if (!userPassword.equals(password)) {
            return false;
        }
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return userRepository.queryUserInfo(userId);
    }

    @Override
    public List<TalkBoxInfo> queryTalkBoxInfoList(String userId) {
        return userRepository.queryTalkBoxInfoList(userId);
    }

    @Override
    public List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType) {
        return userRepository.queryChatRecordInfoList(talkId, userId, talkType);
    }

    @Override
    public void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo) {
        userRepository.appendChatRecord(chatRecordInfo);
    }

    @Override
    public void addTalkBoxInfo(String userId, String talkId, Integer talkType) {
        userRepository.addTalkBoxInfo(userId, talkId, talkType);
    }

    @Override
    public List<GroupsInfo> queryUserGroupInfoList(String userId) {
        return userRepository.queryUserGroupInfoList(userId);
    }

    @Override
    public List<UserFriendInfo> queryUserFriendInfoList(String userId) {
        return userRepository.queryUserFriendInfoList(userId);
    }
}

package com.example.chatserverboot.application;

import com.example.chatserverboot.domain.user.model.ChatRecordInfo;
import com.example.chatserverboot.domain.user.model.TalkBoxInfo;
import com.example.chatserverboot.domain.user.model.UserInfo;

import java.util.List;

public interface UserService {

    /**
     * 登录校验
     *
     * @param userId
     * @param userPassword
     * @return
     */
    boolean checkAuth(String userId, String userPassword);


    /**
     * 获取用户信息
     *
     * @param userId
     * @return
     */
    UserInfo getUserInfo(String userId);

    /**
     * 查询个人用户对话框列表
     *
     * @param userId 个人用户ID
     * @return 对话框列表
     */
    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    /**
     * 查询聊天记录
     *
     * @param talkId   对话框ID
     * @param userId   好友ID
     * @param talkType 对话类型；0好友、1群组
     * @return 聊天记录(10条)
     */
    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType);
}

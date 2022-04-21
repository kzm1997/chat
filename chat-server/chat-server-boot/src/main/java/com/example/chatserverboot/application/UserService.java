package com.example.chatserverboot.application;

import com.example.chatserverboot.domain.user.model.*;

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

    /**
     * 异步添加聊天记录
     *
     * @param chatRecordInfo 聊天记录信息
     */
    void asyncAppendChatRecord(ChatRecordInfo chatRecordInfo);
    /**
     * 添加对话框
     *
     * @param userId   用户ID
     * @param talkId   好友ID
     * @param talkType 对话框类型[0好友、1群组]
     */
    void addTalkBoxInfo(String userId, String talkId, Integer talkType);

    /**
     * 查询个人用户群组列表
     * @param userId
     * @return
     */
    List<GroupsInfo> queryUserGroupInfoList(String userId);

    /**
     * 查询用户好友列表
     * @param userId
     * @return
     */
    List<UserFriendInfo> queryUserFriendInfoList(String userId);

}

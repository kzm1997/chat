package com.example.chatserverboot.domain.user.repository;

import com.example.chatserverboot.domain.user.model.*;

import java.util.List;

public interface IUserRepository {

    String queryUserPassword(String userId);

    UserInfo queryUserInfo(String userId);

    List<TalkBoxInfo> queryTalkBoxInfoList(String userId);

    /**
     * 查询聊天记录
     *
     * @param talkId   对话框ID
     * @param userId   好友ID
     * @param talkType 对话框类型；0好友、1群组
     * @return         聊天记录(10条)
     */
    List<ChatRecordInfo> queryChatRecordInfoList(String talkId, String userId, Integer talkType);

    /**
     * 添加聊天记录
     *
     * @param chatRecordInfo 聊天记录信息
     */
    void appendChatRecord(ChatRecordInfo chatRecordInfo);

    /**
     * 添加对话框
     *
     * @param userId   用户ID
     * @param talkId   好友ID
     * @param talkType 对话框类型[0好友、1群组]
     */
    void addTalkBoxInfo(String userId, String talkId, Integer talkType);

    /**
     * 查询用户群组列表
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

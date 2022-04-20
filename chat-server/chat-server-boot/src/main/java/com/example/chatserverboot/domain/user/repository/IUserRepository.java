package com.example.chatserverboot.domain.user.repository;

import com.example.chatserverboot.domain.user.model.ChatRecordInfo;
import com.example.chatserverboot.domain.user.model.TalkBoxInfo;
import com.example.chatserverboot.domain.user.model.UserInfo;

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
}

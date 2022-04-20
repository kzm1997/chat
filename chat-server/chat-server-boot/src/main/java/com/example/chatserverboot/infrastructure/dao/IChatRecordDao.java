package com.example.chatserverboot.infrastructure.dao;

import com.example.chatserverboot.infrastructure.po.ChatRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IChatRecordDao {

    List<ChatRecord> queryUserChatRecordList(String talkId, String userId);

    List<ChatRecord> queryGroupsChatRecordList(String talkId, String userId);
}

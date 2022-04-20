package com.example.chatserverboot.infrastructure.dao;

import com.example.chatserverboot.infrastructure.po.TalkBox;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ITalkBoxDao {

    List<TalkBox> queryTalkBoxList(@Param("userId") String userId);

    int addTalkBox(TalkBox talkBox);

    TalkBox queryTalkBox(String userId, String talkId);
    
    
    
}

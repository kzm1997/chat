package com.example.chatserverboot.infrastructure.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface IUserFriendDao {

    List<String> queryUserFriendIdList(String userId);
}

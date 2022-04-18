package com.example.chatserverboot.infrastructure.dao;

import com.example.chatserverboot.infrastructure.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserDao {

    String queryUserPassword(@Param("userId") String userId);

    User queryUserById(@Param("userId") String userId);

}

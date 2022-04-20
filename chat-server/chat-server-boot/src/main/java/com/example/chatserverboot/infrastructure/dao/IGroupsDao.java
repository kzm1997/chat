package com.example.chatserverboot.infrastructure.dao;

import com.example.chatserverboot.infrastructure.po.Groups;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(@Param("groupId") String groupsId);

}

package com.example.chatserverboot.infrastructure.dao;

import com.example.chatserverboot.infrastructure.po.Groups;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface IGroupsDao {

    Groups queryGroupsById(String groupsId);

}

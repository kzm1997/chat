package com.example.chatserverboot.domain.user.repository;

import com.example.chatserverboot.domain.user.model.UserInfo;

public interface IUserRepository {

    String queryUserPassword(String userId);

    UserInfo queryUserInfo(String userId);
}

package com.example.chatserverboot.application;

import com.example.chatserverboot.domain.user.model.UserInfo;

public interface UserService {

    /**
     * 登录校验
     * @param userId
     * @param userPassword
     * @return
     */
    boolean checkAuth(String userId,String userPassword);


    /**
     * 获取用户信息
     * @param userId
     * @return
     */
    UserInfo getUserInfo(String userId);

}

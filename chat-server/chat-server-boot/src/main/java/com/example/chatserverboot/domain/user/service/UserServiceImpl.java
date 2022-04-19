package com.example.chatserverboot.domain.user.service;

import com.example.chatserverboot.application.UserService;
import com.example.chatserverboot.domain.user.model.UserInfo;
import com.example.chatserverboot.domain.user.repository.IUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.concurrent.ThreadPoolExecutor;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private IUserRepository userRepository;



    @Override
    public boolean checkAuth(String userId, String userPassword) {

        if (StringUtils.isEmpty(userPassword)) {
            return false;
        }
        String password = userRepository.queryUserPassword(userId);
        if (!userPassword.equals(password)) {
            return false;
        }
        return true;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return userRepository.queryUserInfo(userId);
    }
}
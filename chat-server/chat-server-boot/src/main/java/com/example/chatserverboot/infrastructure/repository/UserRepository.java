package com.example.chatserverboot.infrastructure.repository;

import com.example.chatserverboot.domain.user.model.UserInfo;
import com.example.chatserverboot.domain.user.repository.IUserRepository;
import com.example.chatserverboot.infrastructure.dao.IUserDao;
import com.example.chatserverboot.infrastructure.po.User;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;


@Repository("userRepository")
public class UserRepository implements IUserRepository {

    @Resource
    IUserDao userDao;

    @Override
    public String queryUserPassword(String userId) {
        String password = userDao.queryUserPassword(userId);
        return password;
    }

    @Override
    public UserInfo queryUserInfo(String userId) {
        User user = userDao.queryUserById(userId);
        return new UserInfo(user.getUserId(), user.getUserNickName(), user.getUserHead());
    }
}

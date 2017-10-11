package com.happymmall.service.impl;

import com.happymmall.common.ServerResponse;
import com.happymmall.dao.UserMapper;
import com.happymmall.pojo.User;
import com.happymmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String username, String password) {
        int countResult = userMapper.checkUsername(username);
        if (countResult == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //todo MD5密码登陆

        return null;
    }
}

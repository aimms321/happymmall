package com.happymmall.service.impl;

import com.happymmall.common.ServerResponse;
import com.happymmall.dao.UserMapper;
import com.happymmall.pojo.User;
import com.happymmall.service.IUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("iUserService")
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    public ServerResponse<User> login(String username, String password) {
        int countResult = userMapper.checkUsername(username);
        if (countResult == 0) {
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        //todo MD5密码登陆
        User user = userMapper.selectLogin(username, password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);
    }


}

package com.happymmall.service.impl;

import com.happymmall.common.Const;
import com.happymmall.common.ServerResponse;
import com.happymmall.dao.UserMapper;
import com.happymmall.pojo.User;
import com.happymmall.service.IUserService;
import com.happymmall.util.MD5Util;
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
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        //todo MD5密码登陆
        User user = userMapper.selectLogin(username, md5Password);
        if (user == null) {
            return ServerResponse.createByErrorMessage("密码错误");
        }
        user.setPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登陆成功", user);
    }

    public ServerResponse<String> register(User user) {
        ServerResponse responseVaild = checkVaild(user.getUsername(), Const.USERNAME);
        if (!responseVaild.isSuccess()) {
            return ServerResponse.createByErrorMessage("用户名已存在");
        }
        responseVaild = checkVaild(user.getEmail(), Const.EMAIL);
        if (!responseVaild.isSuccess()) {
            return ServerResponse.createByErrorMessage("Email已存在");
        }
        user.setRole(Const.Role.ROLE_CUSTOMER);
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));
        int countResult = userMapper.insert(user);
        if (countResult == 0) {
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    public ServerResponse<String> checkVaild(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if (Const.USERNAME.equals(type)) {
                int resultCount = userMapper.checkUsername(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)) {
                int resultCount = userMapper.checkEmail(str);
                if (resultCount > 0) {
                    return ServerResponse.createByErrorMessage("EMAIL已存在");
                }
            }
            return ServerResponse.createBySuccessMessage("校验成功");
        } else {
            return ServerResponse.createByErrorMessage("类型参数错误");
        }
    }
}
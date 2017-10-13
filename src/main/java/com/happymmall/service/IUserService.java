package com.happymmall.service;

import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);
    ServerResponse<String> register(User user);
    ServerResponse<String> checkVaild(String str, String type);
}

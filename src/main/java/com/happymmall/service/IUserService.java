package com.happymmall.service;

import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.User;

public interface IUserService {
    ServerResponse<User> login(String username, String password);
}

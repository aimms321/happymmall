package com.happymmall.controller.backend;

import com.happymmall.common.Const;
import com.happymmall.common.ResponseCode;
import com.happymmall.common.ServerResponse;
import com.happymmall.pojo.Product;
import com.happymmall.pojo.User;
import com.happymmall.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by user on 2017-10-18.
 */

@Controller
@RequestMapping("/manage/product")
public class ProductManageController {

    @Autowired
    private IUserService iUserService;


    public ServerResponse productSave(HttpSession session, Product product) {
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null) {
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(), "未登录");
        }
        if (iUserService.checkAdminRole(user).isSuccess()) {
            //todo
        } else {
            return ServerResponse.createByErrorMessage("无权限操作，需要管理员");
        }
    }
}

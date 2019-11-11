package com.ning.service;

import com.ning.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface UserService {
    //校验用户登录 用户名 密码 返回查询到的用户信息
    User checkUserLoginService(String uname,String pwd);
    //修改用户密码
    int userChangeService(String newPwd, int uid);
    //获取所有的用户信息
    List<User> userShowService(HttpServletRequest request, HttpServletResponse response);
    //用户注册
    int userRegService(User user);
}


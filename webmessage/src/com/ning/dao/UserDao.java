package com.ning.dao;

import com.ning.pojo.User;

import java.util.List;

public interface UserDao {
    //根据用户名和密码查询用户信息 用户名 密码 返回查询到的用户信息
    User checkUserLoginDao(String uname,String pwd);
    //根据用户id修改用户密码
    int userChangePwdDao(String newPwd, int uid);
    //获取所有的用户信息
    List<User> userShowDao();
    //用户注册
    int userRegDao(User user);
}

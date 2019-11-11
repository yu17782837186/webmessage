package com.ning.service.impl;

import com.ning.dao.UserDao;
import com.ning.dao.impl.UserDaoImpl;
import com.ning.pojo.User;
import com.ning.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class UserServiceImpl implements UserService {

    //声明dao层对象
    UserDao ud = new UserDaoImpl();
    //用户登录
    @Override
    public User checkUserLoginService(String uname, String pwd) {
        System.out.println(uname+"发起登录请求");
        User user = ud.checkUserLoginDao(uname,pwd);
        if (user != null) {
            System.out.println(uname+"登录成功");
        }else {
            System.out.println(uname+"登录失败");
        }
        return user;
    }
    //修改用户密码
    @Override
    public int userChangeService(String newPwd, int uid) {
        System.out.println(uid+"发起修改密码请求");
        int index = ud.userChangePwdDao(newPwd,uid);
        if (index > 0) {
            System.out.println(uid+":密码修改成功");
        }else {
            System.out.println(uid+":密码修改失败");
        }
        return index;
    }
    //获取所有的用户信息
    @Override
    public List<User> userShowService(HttpServletRequest request, HttpServletResponse response) {
        List<User> list = ud.userShowDao();
        System.out.println("显示所有用户信息："+list);
        return list;
    }
    //用户注册
    @Override
    public int userRegService(User user) {
        return ud.userRegDao(user);
    }
}

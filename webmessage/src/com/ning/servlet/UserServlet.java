package com.ning.servlet;

import com.ning.pojo.User;
import com.ning.service.UserService;
import com.ning.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class UserServlet extends HttpServlet {
    //获取service层对象
    UserService us = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置请求编码格式
        request.setCharacterEncoding("utf-8");
        //设置响应编码格式
        response.setContentType("text/html;charset=utf-8");
        //获取操作符
        String oper = request.getParameter("oper");
        if ("login".equals(oper)){
            //调用登录处理方法
            checkUserLogin(request,response);
        }else if ("out".equals(oper)){
            //调用退出功能
            userOut(request,response);
        }else if ("pwd".equals(oper)){
            //调用密码修改功能
            userPwd(request,response);
        }else if ("show".equals(oper)){
            //显示所有用户功能
            userShow(request,response);
        }else if ("reg".equals(oper)){
            //调用注册功能
            userReg(request,response);
        }else {
            System.out.println("没有找到对应的操作符："+oper);
        }
    }

    //注册用户
    private void userReg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取请求信息
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
        String sex = request.getParameter("sex");
        int age = request.getParameter("age") != "" ?Integer.parseInt(request.getParameter("age")):0;
        String birth = request.getParameter("birth");
        String[] str = null;
        if (birth != "") {
            str = birth.split("/");
            birth = str[2]+"-"+str[0]+"-"+str[1];
        }
        System.out.println(uname+":"+pwd+":"+sex+":"+age+":"+birth);
        User user = new User(0,uname,pwd,sex,age,birth);
        //处理请求信息
        //调用业务层处理
        int index = us.userRegService(user);
//        System.out.println(index);
        if (index > 0) {
            //获取session
            HttpSession hs = request.getSession();
            hs.setAttribute("reg","true");
            response.sendRedirect("/webmessage/index.jsp");
        }
        //响应处理结果
    }

    //显示所有的用户信息
    private void userShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理请求
        //调用service
        List<User> list = us.userShowService(request,response);
        if (list != null) {
            //将查询的用户数据存储到request对象
            request.setAttribute("list",list);
            //请求转发
            request.getRequestDispatcher("/showuser.jsp").forward(request,response);
            return;
        }else {

        }
    }

    //用户修改密码
    private void userPwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取新密码数据
        String newPwd = request.getParameter("newPwd");
        //从session中获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        int uid = user.getUid();
        //处理请求
        //调用service处理
        int index = us.userChangeService(newPwd,uid);
        if (index > 0) {
            //获取session对象
            HttpSession hs = request.getSession();
            hs.setAttribute("pwd","true");
            //重定向到登录页面
            response.sendRedirect("/webmessage/index.jsp");
        }else {

        }
    }

    //用户退出
    private void userOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //获取session对象
        HttpSession hs = request.getSession();
        //强制销毁session
        hs.invalidate();
        //重定向到登录页面
        response.sendRedirect("/webmessage/index.jsp");
    }

    //处理登录
    private void checkUserLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //获取请求信息
        String uname = request.getParameter("uname");
        String pwd = request.getParameter("pwd");
//        System.out.println(uname+":"+pwd);
        //处理请求信息

        //校验
        User user = us.checkUserLoginService(uname,pwd);
        if (user != null) {
            //获取session对象
            HttpSession hs = request.getSession();
            //将用户数据存储到session中
            hs.setAttribute("user",user);
            //使用相对路径和绝对路径，如果servlet的别名中包含目录，就会造成重定向资源失败
            //建议使用绝对路径 第一个/表示服务器根目录 重定向
            response.sendRedirect("/webmessage/main/main.jsp");
            return;
        }else {
            //添加标识符到request中
            request.setAttribute("str",0);
            //使用请求转发 /表示项目根目录
            request.getRequestDispatcher("/index.jsp").forward(request,response);
            return;
        }
//        System.out.println(user);
        //响应处理结果
        //直接响应
        //请求转发
    }
}


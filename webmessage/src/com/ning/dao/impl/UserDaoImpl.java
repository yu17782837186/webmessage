package com.ning.dao.impl;

import com.ning.dao.UserDao;
import com.ning.pojo.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    //根据用户名和密码查询用户信息
    @Override
    public User checkUserLoginDao(String uname, String pwd) {
        //声明jdbc对象
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        User user = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","123456");
            //创建sql命令
            String sql = "select * from t_user2 where uname = ? and pwd = ?";
            //创建sql命令对象
            ps = coon.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,uname);
            ps.setString(2,pwd);
            //执行sql
            rs = ps.executeQuery();
            //遍历结果
            while (rs.next()) {
                //给变量赋值
                user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setPwd(rs.getString("pwd"));
                user.setSex(rs.getString("sex"));
                user.setAge(rs.getInt("age"));
                user.setBirth(rs.getString("birth"));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (coon != null){
                    coon.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回结果
        return user;
    }
    //根据用户id修改用户密码
    @Override
    public int userChangePwdDao(String newPwd, int uid) {
        //声明jsbc对象
        Connection coon = null;
        PreparedStatement ps = null;
        //创建变量
        int index = -1;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","123456");
            //创建sql命令
            String sql = "update t_user2 set pwd = ? where uid = ?";
            //创建sql命令对象
            ps = coon.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,newPwd);
            ps.setInt(2,uid);
            //执行
            index = ps.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }finally { //关闭资源
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (coon != null) {
                    coon.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回结果
        return index;
    }

    @Override
    public List<User> userShowDao() {
        //声明jdbc对象
        Connection coon = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        //声明变量
        List<User> list = null;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","123456");
            //创建sql命令
            String sql = "select * from t_user2 ";
            //创建sql命令对象
            ps = coon.prepareStatement(sql);
            //给占位符赋值

            //执行sql
            rs = ps.executeQuery();
            //给集合赋值
            list = new ArrayList<>();
            //遍历结果
            while (rs.next()) {
                //给变量赋值
                User user = new User();
                user.setUid(rs.getInt("uid"));
                user.setUname(rs.getString("uname"));
                user.setPwd(rs.getString("pwd"));
                user.setSex(rs.getString("sex"));
                user.setAge(rs.getInt("age"));
                user.setBirth(rs.getString("birth"));
                //将对象存储到list集合中
                list.add(user);
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            //关闭资源
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (ps != null){
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (coon != null){
                    coon.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回结果
        return list;
    }
    //用户注册
    @Override
    public int userRegDao(User user) {
        //声明jdbc对象
        Connection coon = null;
        PreparedStatement ps = null;
        //声明变量
        int index = -1;
        try {
            //加载驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取连接
            coon = DriverManager.getConnection("jdbc:mysql://localhost:3306/web","root","123456");
            //创建sql命令
            String sql = "insert into t_user2 values(default,?,?,?,?,?)";
            //创建sql命令对象
            ps = coon.prepareStatement(sql);
            //给占位符赋值
            ps.setString(1,user.getUname());
            ps.setString(2,user.getPwd());
            ps.setString(3,user.getSex());
            ps.setInt(4,user.getAge());
            ps.setString(5,user.getBirth());
            //执行
            index = ps.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }finally {//关闭资源
            try {
                if (ps != null) {
                    ps.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (coon != null) {
                    coon.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //返回结果
        return index;
    }
}

<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/11
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <title>欢迎登录信息管理系统界面</title>
  <link href="css/style.css" rel="stylesheet" type="text/css" />
  <script language="JavaScript" src="js/jquery.js"></script>
  <script src="js/cloud.js" type="text/javascript"></script>

  <script language="javascript">
      $(function(){
          $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
          $(window).resize(function(){
              $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
          })
      });
  </script>

</head>

<body style="background-color:#df7611; background-image:url(images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
  <span>欢迎登录信息管理系统界面</span>
</div>

<div class="loginbody">
<div>
  <span class="systemlogo"></span>

  <br>
  <%
  //声明java代码块进行逻辑校验 判断是否是错误过来的 0
  Object obj=request.getAttribute("str");
  if (obj != null) {
%>
  <div style="text-align: center">
    <span style="font-size: 15px;color: darkred;font-weight: bold">用户名或密码错误</span>
  </div>
  <%}%>

  <%
    //声明java代码块进行密码修改提示语 判断是否是错误过来的 0
    Object pwd = session.getAttribute("pwd");
    if (pwd != null) {
  %>
  <div style="text-align: center">
    <span style="font-size: 15px;color: darkred;font-weight: bold">密码修改成功</span>
  </div>
  <%}
      session.removeAttribute("pwd");
  %>

  <%
    //声明java代码块进行注册提示语 判断是否是错误过来的 0
    Object reg= session.getAttribute("reg");
    if (reg != null) {
  %>
  <div style="text-align: center">
    <span style="font-size: 15px;color: darkred;font-weight: bold">注册成功</span>
  </div>
  <%}
    session.removeAttribute("reg");
  %>

  <div class="loginbox loginbox2">
    <form action="user" method="post">
      <input type="hidden" name="oper" value="login"/>
      <ul>
        <li><input name="uname" type="text" placeholder="用户名" class="loginuser" /></li>
        <li><input name="pwd" type="password" placeholder="密码" class="loginpwd" /></li>
        <li class="yzm">
          <span><input name="" type="text" value="验证码" onclick="JavaScript:this.value=''"/></span><cite>X3D5S</cite>
        </li>
        <li><input name="" type="submit" class="loginbtn" value="登录"  onclick="javascript:window.location='main.html'"  /><label><a
                href="/webmessage/user/reg.jsp">注册</a></label><label><a href="#">忘记密码？</a></label></li>
      </ul>
    </form>
  </div>
</div>

</body>
</html>

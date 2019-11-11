<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/11
  Time: 13:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" import="com.ning.pojo.*" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <script language="JavaScript" src="../js/jquery.js"></script>
    <script type="text/javascript">
        $(function(){
            //顶部导航切换
            $(".nav li a").click(function(){
                $(".nav li a.selected").removeClass("selected")
                $(this).addClass("selected");
            })
            //退出功能
            $("#out").click(function(){
                var flag = window.confirm("你确定要退出吗？");
                if (flag) {
                    //修该地址栏信息并发起请求
                    window.top.location.href ="/webmessage/user?oper=out";
                }
            })
        })

    </script>


</head>

<body style="background:url(../images/topbg.gif) repeat-x;">

<div class="topleft">
    <a href="main.html" target="_parent"><img src="../images/logo.png" title="系统首页" /></a>
</div>



<div class="topright">
    <ul>
        <li><a href="javascript:void(0)" id="out" >退出</a></li>
    </ul>
    <div class="user">
        <span><%=((User)session.getAttribute("user")).getUname() %></span>
    </div>
</div>

</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: HASEE
  Date: 2019/11/11
  Time: 16:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="../css/style.css" rel="stylesheet" type="text/css" />
    <!--引入jQuery文件  -->
    <script type="text/javascript" src="../js/jquery.js"></script>
    <!--声明jQuery代码域  -->
    <script type="text/javascript">
        $(function(){
            //校验密码修改
            $("#fm").submit(function(){
                if($("#newPwd").val()==""){//校验新密码
                    alert("新密码不能为空");
                    return false;
                }else if($("#cfPwd").val()==""){//校验确认密码
                    alert("确认密码不能为空");
                    return false;
                }else if($("#newPwd").val()!=$("#cfPwd").val()){//校验新密码和确认密码是否一致
                    alert("两次密码 不一致");
                    return false;
                }else{
                    return true;
                }
            })
        })

    </script>
</head>

<body>

<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">首页</a></li>
        <li><a href="#">个人信息</a></li>
        <li><a href="#">修改密码</a></li>
    </ul>
</div>

<div class="formbody">

    <div class="formtitle"><span>修改密码信息</span></div>
    <form action="/webmessage/user" method="post" id="fm" target="_top">
        <input  type="hidden" name="oper" value="pwd" />
        <ul class="forminfo">
            <li><label>新密码</label><input name="newPwd" id="newPwd" type="text" class="dfinput" /><i></i></li>
            <li><label>确认密码</label><input name="" id="cfPwd" type="text" class="dfinput" /><i></i></li>
            <li><label>&nbsp;</label><input name="" type="submit"   value="确认保存"/></li>
        </ul>
    </form>

</div>


</body>

</html>
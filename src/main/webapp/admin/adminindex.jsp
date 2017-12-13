<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
 	<title>Title</title>
    <link rel="stylesheet" href="css/admincommon.css">
    <link rel="stylesheet" href="css/adminindex.css">

</head>
<body>
<div id="login">
    <div class="headerdiv">
        <div class="login_l">
            欢迎您登录系统，<a href="">${session_admin.username }</a>
        </div>
            <div class="login_r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/admin/close.png" alt="">&nbsp;<a href="adminindexlogin.jsp">退出</a></div>
    </div>
</div>
<div id="logo">
    <div>
        <div class="logo_l">

        </div>
        <div class="logo_r">
            <span>服务热线&nbsp;:&nbsp;</span><span>400-9968-888</span>
        </div>
    </div>
</div>
<div id="content">
    <div id="content_l">
        <%@include file="/WEB-INF/jspf/adminHeader.jsp" %>
    </div>
    <div id="content_r">
        <div class="content_rt">
            <div><img src="images/admin/lock.png" alt=""></div>
            <div class="time">
                <div>当前时间&nbsp;:&nbsp;<span id="timer"></span></div>
                <div><h3>${session_admin.username }</h3></div>
                <div>欢迎进入网站后台管理中心</div>
            </div>
        </div>
        <div class="message">您的相关信息</div>
        <div class="xinxi">
            <ul>
                <li>登录帐号&nbsp;:&nbsp;<span>${session_admin.username }</span></li>
                <li>真实姓名&nbsp;:&nbsp;<span>${session_admin.username }</span></li>
                <li>用户角色&nbsp;:&nbsp;<span>管理员</span></li>
                <li>上次登录&nbsp;:&nbsp;<span>2017-01-10&nbsp;&nbsp;17&nbsp;:&nbsp;00</span></li>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
    <p>版权所有碟派科技有限公司</p>
    <p>备案号:京-3032152541</p>
</div>
</body>
<script src="js/adminindex.js">
</script>
</html>

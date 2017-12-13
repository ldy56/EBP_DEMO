<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div>
        <div>Hi TOMCAT8082 欢迎访问跌派网！&nbsp;&nbsp;<a href="myshopping.jsp">[我的订单]</a></div>
        <div class="login_r">
        <c:if test="${empty session_user}">
        <a href="login.jsp">登录</a>丨<a href="zhuce.jsp">注册</a>
         &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty session_user}">
        	${session_user.name }
        </c:if>
       <img src="images/admin/close.png" alt="">&nbsp;<a href="user/logout">退出</a></div>
    </div>

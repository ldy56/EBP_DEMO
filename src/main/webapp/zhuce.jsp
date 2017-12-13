<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Title</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/zhuce.css">
    <script type="text/javascript" src="js/jquery-1.12.1.min.js"></script>
    <style>

    </style>
</head>
<body>

<div id="login">
    <%@include file="WEB-INF/jspf/login.jsp" %>
</div>
<div id="navgin">
	<%@include file="WEB-INF/jspf/navgin.jsp" %>    
</div>

<div id="adv">
    <div>
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="#">注册</a>
    </div>
</div>
<div id="content">
    <div style="margin: 0px auto; width: 600px;">
        <form action="user/registerUser" method="post" onsubmit="return check()">
                <strong>您的电子邮箱不会被公布出来，但是必须填写.</strong><br>
                <span>在您注册之前请先认真阅读服务条款</span><br>
                <table class="tbs1" cellpadding="5">
                    <tr>
                        <td height="60" align="right">用&nbsp;户&nbsp;&nbsp;名:</td>
                        <td class="inpt"><input type="text" maxlength="30" name="username" 
                         autofocus placeholder="请输入用户名！" required  id="username"> *&nbsp;&nbsp;(最多输入30个字符)</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">电子邮箱:</td>
                        <td class="inpt"><input type="email"  id="eml"
                        required   placeholder="请填写邮箱！" name="email"> *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机:</td>
                        <td class="inpt"><input type="text" id="telno"
                        required pattern="^((13[0-9])|(15[0-9])|(18[0, 5-9]))\d{8}$" maxlength="11" name="telno" > *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">真实姓名:</td>
                        <td class="inpt"><input type="text" id="name" name="name" pattern="^[\u4e00-\u9af5]{2,4}$" required> *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">身份证号:</td>
                        <td class="inpt"><input type="text" id="idCard" name="idCard" maxlength="18" required> *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址:</td>
                        <td class="inpt"><input type="text" id="address" name="address" required> *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄:</td>
                        <td class="inpt"><input type="number" id="age" name="age" required min="1"> *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别:</td>
                        <td >&nbsp;&nbsp;&nbsp;<input type="radio"  name="sex" value="1">男
                        	&nbsp;&nbsp;&nbsp;&nbsp;
                        	<input type="radio"  name="sex" value="0" checked>女
                        </td>
                    </tr>
                    <tr>
                        <td height="60" align="right">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码:</td>
                        <td class="inpt"><input type="password"  name="password" id="password" required> *</td>
                    </tr>
                    <tr>
                        <td height="60" align="right">重复密码:</td>
                        <td class="inpt"><input type="password" name="passwordC" id="passwordC" required> *</td>
                    </tr>
                    <tr>
                        <td height="60" colspan="2">同意服务条款<input type="checkbox" id="bixuT" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;先看看条款？<span class="inpt">*</span></td>
                    </tr>
                </table>
                <div class="btnbox"><input class="input1" type="submit" value="提交" >&nbsp;<input class="input1" type="reset"></div><br>
                <strong>*在提交您的注册信息时，我们认为您已经同意了我们的服务条款.</strong><br>
                <strong>*这些条款可能在未经您同意的时候进行修改.</strong>
        </form>
    </div>
<script type="text/javascript">
	function check(){
		 var ps=document.getElementById("password");
		 var ps2=document.getElementById("passwordC");
		 
		 if(ps.value!=ps2.value){
			alert("两次密码不一致！");
		 	return false;
		 }
		 
		if(!$("#bixuT").is(":checked")){
			alert("请同意条款！");
			return false;
		}
		return true;
	}

</script>
</div>

<div id="footer">
    <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
</html>
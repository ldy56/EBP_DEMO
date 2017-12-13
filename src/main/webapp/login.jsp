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
    <link rel="stylesheet" href="css/login.css">
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">登录</a>
    </div>
</div>
<div id="content">
    <div class="idmessage">
        <div class="id_left">
            <div class="id_left_bj">
                <div class="pic">
                    <img src="images/other/head_portrait.png" alt="">
                </div>
                <div class="passwd">
                   <form action="user/login" method="post">
                   
                    <table>
                        <tr align="right">
                            <td>帐&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号&nbsp;:&nbsp;</td>
                            <td class="inpt">&nbsp;<input type="text" name="username" placeholder="${errors }"></td>
                        </tr>
                        <tr align="right">
                            <td height="60">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;:&nbsp;</td>
                            <td class="inpt">&nbsp;<input type="password" name="password" placeholder="${errors }"></td>
                        </tr>
                        <tr>
                            <td height="40" class="bt" colspan="2" align="center"><input class="input1" type="submit"
                                                                                         value="登&nbsp;录"><input
                                    type="reset" id="forget" class="input1" value="注&nbsp;册" onclick="window.location='zhuce.jsp'"></td>
                        </tr>
                    </table>
                    </form> 
                </div>
            </div>
        </div>

    </div>

</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
</html>s
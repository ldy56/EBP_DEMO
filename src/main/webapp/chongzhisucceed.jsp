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
    <link rel="stylesheet" href="css/chongzhisucceed.css">

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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="chongzhi.jsp">账户充值<span>&nbsp;>&nbsp;</span></a>充值成功
    </div>
</div>
<div id="content">
    <div class="look_dd">
        <div>充值成功！</div>
        <div id="tbss">
            <a href="chongzhi.jsp" id="block" class="input1">返回</a>
        </div>
    </div>
    <div class="chongzhi">
        <h3>本次充值信息如下&nbsp;:&nbsp;</h3>
        <div class="msg">
            <img src="images/other/chongzhi.jpg" >
            <ul class="ul2">
                <li>用&nbsp;&nbsp;户&nbsp;&nbsp;名&nbsp;:&nbsp;<span>${session_user.username }</span></li>
                <li>充值方式&nbsp;:&nbsp;<span>${type }</span></li>
                <li>充值金额&nbsp;:&nbsp;<span>${money }元</span></li>
                <li>账户余额&nbsp;:&nbsp;<span>${session_user.balance }元</span></li>
                <li><input type="button" class="input1" value="打印账单">
                </li>
            </ul>

        </div>

    </div>
</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
<script src="js/chongzhi.js"></script>
</html>
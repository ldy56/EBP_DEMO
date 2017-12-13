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
    <link rel="stylesheet" href="css/chongzhi.css">

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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">账户充值</a>
    </div>
</div>
<div id="content">
    <div class="look_dd">
        <div>近期订单</div>
        <div id="tbss">
            <ul>
                <li>查看帮助</li>
                <li>账户明细</li>
                <li>我的钱包</li>
                <li class="clk">充值</li>
            </ul>

        </div>
    </div>
    <div class="chongzhi">
    	<form action="user/topUpMoney" method="post">
        <table border="0">
            <tr>
                <td rowspan="5"><img src="images/other/chongzhi.jpg" alt=""></td>
                <td class="width_r" height="30" align="right">用&nbsp;&nbsp;户&nbsp;&nbsp;名&nbsp;:&nbsp;</td>
                <td>${session_user.username}</td>
            </tr>
            <tr>
                <td height="40" align="right">账户余额&nbsp;:&nbsp;</td>
                <td>${session_user.balance }元</td>
            </tr>
            <tr>
                <td height="40" align="right">充值方式&nbsp;:&nbsp;</td>
                <td class="lie">
                    <select class="all1" name="type">
                        <option value="空中充值">空中充值</option>
                        <option value="支付宝支付">支付宝支付</option>
                        <option value="微信支付">微信支付</option>
                    </select>
                </td>

            </tr>
            <tr>
                <td height="40" align="right">充值金额&nbsp;:&nbsp;</td>
                <td class="money"><input type="number" name="money" min="1" value="2000"></td>
            </tr>
            <tr>
                <td height="60" align="right"></td>
                <td><input class="input1" type="submit" value="充&nbsp;值"></td>
            </tr>
        </table>
		</form>
    </div>
</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
<script src="js/chongzhi.js"></script>
</html>
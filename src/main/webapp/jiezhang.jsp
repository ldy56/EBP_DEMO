<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Title</title>
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/jiezhang.css">
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">交易成功</a>
    </div>

</div>
<div id="content">
    <div class="text">
        <p>您的预定已成功！</p>
        <p><span>订单号&nbsp;:&nbsp;</span><span>${order.oid }</span><span>订单生成时间&nbsp;:&nbsp;</span><span>${order.commitTime }</span><span>成交金额&nbsp;:&nbsp;</span><span>${order.amount }元</span></p>
        <p>以下是该订单详细信息&nbsp;:&nbsp;</p>

    </div>
    <div class="tabs">
        <table border="0" cellpadding="0" cellspacing="0" id="t">
            <tr bgcolor="#E80F3C" class="trs">
                <th width="150" height="40">序&nbsp;&nbsp;&nbsp;号</th>
                <th width="440">描述</th>
                <th width="250">单价(元)</th>
                <th width="150">购票数</th>
                <th width="150">总金额(元)</th>
            </tr>
            <c:forEach items="${shopping_cart }" var="tk">
	            <tr id="tr0" class="tfont" align="center">
	                <td height="60">${tk.key }</td>
	                <td>${tk.value.descs }</td>
	                <td>${tk.value.price }</td>
	                <td>${tk.value.number }</td>
	                <td>${tk.value.number*tk.value.price }</td>
	            </tr>
          </c:forEach>  
          
            <tr id="tr2" class="tfont" align="center">
                <th height="60">总计</td>
                <th></th>
                <th></th>
                <th id="zong">${sumTicket }</th>
                <th id="he">${sumMoney}</th>
            </tr>

        </table>
        <p>该订单以出票，两日内讲通过快递送达，请注意查看。</p>
    </div>
</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>

</html>
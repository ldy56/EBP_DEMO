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
    <link rel="stylesheet" href="css/gouwuche.css">
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">购物车</a>
    </div>

</div>
<div id="content">
    <div class="tabs">
        <table border="0" cellpadding="0" cellspacing="0" id="t">
            <tr bgcolor="#E80F3C" class="trs">
                <th width="150" height="40">序&nbsp;&nbsp;&nbsp;号</th>
                <th width="440">描述</th>
                <th width="250">单价(元)</th>
                <th width="150">购票数</th>
                <th width="150">总金额(元)</th>
                <th width="150"></th>
                </tr>
           <c:forEach items="${shopping_cart }" var="tk">
	            <tr id="tr0" class="tfont" align="center">
	                <td height="60">${tk.key }</td>
	                <td>${tk.value.descs }</td>
	                <td>${tk.value.price }</td>
	                <td>${tk.value.number }</td>
	                <td>${tk.value.number*tk.value.price }</td>
	                <td><input type="button" value="删除" class="input1" onclick="if(confirm('是否删除该数据?')){window.location='user/deleteCart?key=${tk.key}'}"></td>
	            </tr>
          </c:forEach>  
          
            <tr id="tr2" class="tfont" align="center">
                <th height="60">总计</td>
                <th></th>
                <th></th>
                <th id="zong">${sumTicket }</th>
                <th id="he">${sumMoney}</th>
                <th></th>
            </tr>
            <tr>
                <td align="center" colspan="6"><input type="button" value="继续订票" class="input2" onclick="window.location='user/queryTicketsBy6'"><input type="button" value="结账" class="input1" onclick="window.location='user/summaryMoney?money=${sumMoney}'"></td>
            </tr>
        </table>
        
        <div class="butn">
            <ul class="btn_ul">
                <li><input type="button" value="上一页"></li>
                <li class="next_color"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><input type="button" value="下一页"></li>
                <li><input type="text"></li>
                <li><a href="#">GO</a></li>
            </ul>
        </div>
    </div>
</div>
<div id="footer">
    <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
<script>

</script>
</html>
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
    <link rel="stylesheet" href="css/myshopping.css">
	<script type="text/javascript" src="js/jquery-1.12.1.min.js"></script>
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">我的订单</a>
    </div>
</div>
<div id="content">
    <div class="mydd">
        <input class="mysop btnbgr" type="button" value="我的订单"><input class="duihuan" type="button" value="积分兑换"><input class="hezuo" type="button" value="合作商户订单">
        <div class="block">
            <div class="srh">

                <select class="all1">
                        <option value="yi">全部订单</option>
                </select>
                <select class="all2">
                    <option value="yi">近一个月订单</option>
                    <option value="er">近两个月订单</option>
                    <option value="san">近三个月订单</option>
                </select>
               <input type="text" class="all3" value="订单编号" onfocus="this.value=''"><input type="button" class="all4" value="订单查询">

            </div>
            <div class="tabs">
                <table border="0" cellpadding="0" cellspacing="0" id="t">
                   <tr bgcolor="#E80F3C" class="trs">
                       <th width="150" height="40">序&nbsp;&nbsp;&nbsp;号</th>
                       <th width="480">订单号</th>
                       <th width="300">生成时间</th>
                       <th width="220">金额（元）</th>

                   </tr>
	                <c:forEach items="${orderList }" var="order" varStatus="st">
	                    <tr id="tr${st.index}" class="tfont" align="center">
	                        <td height="60"><a id="jian${st.index}" class="tbsbtn" type="button" onclick="tianjia(this,${st.index},${order.oid})">+</a>&nbsp;&nbsp;&nbsp;&nbsp;${st.count }</td>
	                        <td>${order.oid }</td>
	                        <td>${order.commitTime }</td>
	                        <td>${order.amount }</td>
	                    </tr>
	                    <tr  bgcolor="#F5F5F5"  >
	                    <td id="td${st.index}" colspan="4"></td>
	                    </tr>
	                </c:forEach>
                </table>

            </div>
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
        <div class="none1">
            积分兑换
        </div>
        <div class="none1">
        合作商户订单
        </div>
    </div>
</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
<script src="js/myshopping.js"></script>
<script>

</script>
</html>
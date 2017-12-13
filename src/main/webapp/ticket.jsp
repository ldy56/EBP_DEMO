<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="css/ticket.css">
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">ticket</a>
    </div>
</div>
<div id="shopping">
    <div class="data">
        <span>请选择起始日期（查询7天以内票项）：</span>
        <input type="date" id="endDate" value="${end }">
        <a onclick="queryByDate()">查&nbsp;询</a>
    </div>
    <script type="text/javascript">
    	function queryByDate(){
    		var date=$("#endDate").val();
    		window.location="user/queryTicketsBy6?currentPage=1&endDate="+date;
    	}
    </script>
    
</div>
<div class="content">
    <ul>
    <c:forEach begin="0" end="3" var="index">
        <li>
            <div class="content_left">
                <img src="getPhotoByTid?tid=${Tickets[index].tid }" width="180" height="250" alt="">
            </div>

            <div class="content_right" >
                <h2>${Tickets[index].descs }</h2>
                <br>
                <span>预售/预定</span><span id="color_red">售票中</span><span>已停售</span>
                <br>
                <div class="content_right_img">
                    <img src="images/other/ticket/icon2.png" alt=""><div class="line_red line_rht"></div><img src="images/other/ticket/icon1.png" alt=""><div class="line_hui line_cnt"></div><img src="images/other/ticket/icon3.png" alt=""><div class="line_hui line_lft"></div>
                </div>
                <div class="palytime">
                    <h3>上映时间：${Tickets[index].startTime }</h3>
                </div>
                <div class="price">
                    <span id="price_size">${Tickets[index].price }</span><span class="price_color">元</span><span>/张</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>共${Tickets[index].amount }张</span>
                </div>
                <div class="some">
                    <input type="number" id="number${Tickets[index].tid }" min="0" required><input type="button" onclick="shopping(${Tickets[index].tid})" value="加购物车">
                </div>
                <div class="save">
                    库存${Tickets[index].balance }张
                </div>
            </div>
        </li>
    </c:forEach>    
        
    </ul>
</div>

<script type="text/javascript">
	function shopping(id){
		var num=$("#number"+id).val();
		$.ajax({
			url:"user/addShoppingCart",
			data:"tid="+id+"&number="+num,
			dataType:"text",
			success:function(data){
				if(data=="ok"){
					alert("该票据添加成功！");
				}
			}
		});
	}
</script>

<div class="content" style="text-align: center;">
	<a href="user/queryTicketsBy6?currentPage=1&endDate=${end }"><input type="button" value="首页"/></a>
	<a href="user/queryTicketsBy6?currentPage=${currentPage-1}&endDate=${end }"><input type="button" value="上一页"/></a>
	${currentPage }/${pageCount }
	<a href="user/queryTicketsBy6?currentPage=${currentPage+1}&endDate=${end }"><input type="button" value="下一页"/></a>
	<a href="user/queryTicketsBy6?currentPage=${pageCount }&endDate=${end }"><input type="button" value="尾页"/></a>
</div>

<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
</html>
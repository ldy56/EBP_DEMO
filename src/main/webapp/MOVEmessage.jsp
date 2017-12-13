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
    <link rel="stylesheet" href="css/MOVEmessage.css">
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">影片详情</a>
    </div>
</div>
<div id="content">
    <div class="idmessage">
        <div class="id_left">
            <div class="pic">
                <img src="getPhotoByTid?tid=${ticket.tid }" width="200" height="250" alt="">
                <div class="adminm">
                    <p>&nbsp;&nbsp;....</p>
                 </div>
            </div>
            <div class="passwd">
                <table>
                    <tr align="center">
                        <td>${ticket.descs }</td>
                    </tr>
                    <tr align="center">
                        <td height="20">${ticket.descs }经典再现</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="id-hr"></div>
        <div class="id_right">
            <div class="content_right">
                <h2>${ticket.descs }</h2>
                <br>
                <span>预售/预定</span><span id="color_red">售票中</span><span>已停售</span>
                <br>
                <div class="content_right_img">
                    <img src="images/other/ticket/icon2.png" alt=""><div class="line_red line_rht"></div><img src="images/other/ticket/icon1.png" alt=""><div class="line_hui line_cnt"></div><img src="images/other/ticket/icon3.png" alt=""><div class="line_hui line_lft"></div>
                </div>
                <div class="palytime">
                    <h3>上映时间：${ticket.startTime }</h3>
                </div>
                <div class="price">
                    <span id="price_size">${ticket.price }</span><span class="price_color">元</span><span>/张</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>共${ticket.amount }张</span>
                </div>
                <div class="some">
                    <input type="number" min="0" required><input type="button" value="加购物车" onclick="window.location='gouwuche.jsp'">
                </div>
                <div class="save">
                    库存${ticket.balance }张
                </div>
            </div>
        </div>
    </div>
</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
</html>
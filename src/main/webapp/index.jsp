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
    <link rel="stylesheet" href="css/index.css">
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
    <div class="bnner">
        <ul>
            <li><a href="#"><span class="ion_1"></span>演唱会</a></li>
            <li><a href="#"><span class="ion_2"></span>音乐会</a></li>
            <li><a href="#"><span class="ion_3"></span>话剧歌剧</a></li>
            <li><a href="#"><span class="ion_4"></span>舞蹈芭蕾</a></li>
            <li><a href="#"><span class="ion_5"></span>曲苑杂坛</a></li>
            <li><a href="#"><span class="ion_6"></span>体育比赛</a></li>
            <li><a href="#"><span class="ion_7"></span>度假休闲</a></li>
            <li><a href="#"><span class="ion_8"></span>儿童亲子</a></li>
            <li><a href="#"><span class="ion_9"></span>展会展览</a></li>
        </ul>
    </div>
    <div class="hot">
        <div class="hotbj"></div>
        <div class="hotlb">
            <div class="hotlb_cont">
                <span></span>
                <a href="">热门标签</a>
                <a href="">全部电影</a>
                <ul>
                    <li><a href="">华语</a></li>
                    <li><a href="">喜剧</a></li>
                    <li><a href="">爱情</a></li>
                    <li><a href="">动作</a></li>
                    <li><a href="">惊悚</a></li>
                    <li><a href="">犯罪</a></li>
                    <li><a href="">美国</a></li>
                    <li><a href="">悬疑</a></li>
                    <li><a href="">枪战</a></li>
                    <li><a href="">青春</a></li>
                    <li><a href="">奇幻</a></li>
                </ul>
            </div>
            <div class="viplb">
                <img src="images/index/vip.png" alt="">
            </div>
        </div>
    </div>
</div>
<div class="watch">
    <div class="watch_top">
        <div class="looked"><a href=""><img src="images/index/recent_watch.png" alt=""></a></div>
        <div class="hotplayer">
            <div>
                最近热播
            </div>
            <div><a href="#">VIP电影榜</a>&nbsp;丨&nbsp;<a href="">免费电影榜</a></div>
            <div></div>
        </div>
    </div>
    <div class="image">
        <ul>
        <c:forEach begin="0" end="4" var="index">
            <li class="list1"><a href="queryTicketById?tid=${Tickets[index].tid }"><img class="fkwxr" src="getPhotoByTid?tid=${Tickets[index].tid }" alt="" width="195px" height="245"></a><br>
                <a href="queryTicketById?tid=${Tickets[index].tid }">&nbsp;&nbsp;${Tickets[index].descs }</a><br><a href=""><img src="images/index/heart.png" alt=""><img src="images/index/heart.png" alt=""><img src="images/index/heart.png" alt=""><img src="images/index/heart.png" alt=""><img src="images/index/heart.png" alt="">&nbsp;&nbsp;&nbsp;<span>超赞</span></a></li>
        </c:forEach>
        </ul>
    </div>
</div>
<div class="watch2">
    <div class="watch_top2">
        <!--<div class="looked"><a href=""><img src="images/index/recent_watch.png" alt=""></a></div>-->
        <div class="hotplayer2">
            <div>
                全城热播
            </div>
            <div><a href="#">大家都在看</a>&nbsp;丨&nbsp;<a href="">即将上映</a></div>
            <div></div>
        </div>
    </div>
    <div class="image2">
        <ul>
		<c:forEach begin="0" end="4" var="index">
            <li class="list12"><a href="queryTicketById?tid=${Tickets[index].tid }"><img src="getPhotoByTid?tid=${Tickets[index].tid }" alt="" width="195px" height="245"></a>
                <a class="font_a" href="queryTicketById?tid=${Tickets[index].tid }">&nbsp;&nbsp;${Tickets[index].descs }</a><span class="sp1">7</span><span class="sp6">.</span><span class="sp7">8</span><span class="sp12">分</span><br><a class="font_c" href="queryTicketById?tid=${Tickets[index].tid }">&nbsp;&nbsp;&nbsp;${Tickets[index].descs }经典再现</a></li>
		</c:forEach>
        </ul>
    </div>
    <div class="image2">
        <ul>
        <c:forEach begin="5" end="9" var="index">
            <li class="list12"><a href="queryTicketById?tid=${Tickets[index].tid }"><img src="getPhotoByTid?tid=${Tickets[index].tid }" width="195px" height="245" alt=""></a>
                <a class="font_a" href="queryTicketById?tid=${Tickets[index].tid }">&nbsp;&nbsp;${Tickets[index].descs }</a><span class="sp1">7</span><span class="sp6">.</span><span class="sp7">8</span><span class="sp12">分</span><br><a class="font_c" href="queryTicketById?tid=${Tickets[index].tid }">&nbsp;&nbsp;&nbsp;${Tickets[index].descs }经典再现</a></li>
       	</c:forEach>
        </ul>
    </div>
</div>
<div id="footer">
     <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
</html>
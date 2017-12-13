<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

		<div class="l_top">
            <div>
                <img src="images/admin/head_portrait.png" alt=""></div>
            <div>
                <span>用户&nbsp;&nbsp;:&nbsp;${session_admin.username }</span><br>
                <span>角色&nbsp;&nbsp;:&nbsp;管理员</span>
            </div>
        </div>
        <div class="l_bottom" id="l_bottom">
            <ul>
                <li class="active"><img class="img_b" src="images/admin/icon1.png" alt=""><span><a href="adminindex.jsp">首&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;页</a></span></li>
                <li><img class="img_b" src="images/admin/icon02.png" alt=""><span><a href="../admin/queryTickets">票项管理</a></span></li>
                <li><img class="img_b" src="images/admin/icon03.png" alt=""><span><a href="adminUser.jsp">用户管理</a></span></li>
                <li><img class="img_b" src="images/admin/icon04.png" alt=""><span><a href="adminOder.jsp">订单管理</a></span></li>
                <li><img class="img_b" src="images/admin/icon05.png" alt=""><span><a href="logout">退&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;出</a></span></li>
            </ul>
        </div>

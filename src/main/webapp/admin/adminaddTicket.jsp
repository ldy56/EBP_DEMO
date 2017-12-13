<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Title</title>
    <link rel="stylesheet" href="css/admincommon.css">
    <link rel="stylesheet" href="css/adminaddTicket.css">
    <style>

    </style>
</head>
<body>
<div id="login">
    <div class="headerdiv">
        <div class="login_l">
            欢迎您登录系统，<a href="">zhangsan</a>
        </div>
        <div class="login_r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/admin/close.png" alt="">&nbsp;<a
                href="adminindexlogin.html">退出</a></div>
    </div>
</div>
<div id="logo">
    <div>
        <div class="logo_l">

        </div>
        <div class="logo_r">
            <span>服务热线&nbsp;:&nbsp;</span><span>400-9968-888</span>
        </div>
    </div>
</div>
<div id="content">
    <div id="content_l">
       <%@include file="/WEB-INF/jspf/adminHeader.jsp" %>
    </div>
    <div id="content_r">
        <div class="tianjia">
            <h1>增加票项</h1>
            <div class="tbes">
                <table  cellspacing="0" cellpadding="0" id="t">
                    <tr class="tr1">
                        <td>电影名称</td>
                        <td width="300"><input type="text"></td>

                    </tr>
                    <tr class="tr1">
                        <td>总&nbsp;&nbsp;票&nbsp;&nbsp;数</td>
                        <td width="300"><input type="text"></td>
                    </tr>
                    <tr class="tr1">
                        <td>单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</td>
                        <td width="300"><input type="text"></td>
                    </tr>
                    <tr class="tr1">
                        <td>上映时间</td>
                        <td width="300"><input type="text"></td>
                    </tr>
                    <tr class="tr2">
                        <td>上传图片</td>
                        <td width="300"><input type="file"></td>
                    </tr>
                    <tr class="tr1">
                        <td valign="top">电影描述</td>
                        <td width="300"><textarea cols="50" rows="10"></textarea></td>
                    </tr>
                    <tr>
                        <td>是否停售</td>
                        <td><input type="radio" name="tick1" id="shou2"><label for="shou2">已停售</label><input
                                type="radio" name="tick1" id="shou1"><label for="shou1">售票中</label></td>
                    </tr>
                </table>
                <div class="btnbox">
                    <input type="button" class="btn1" value="一键添加" onclick="window.location='adminTicket.html'">
                </div>

            </div>
				
        </div>
		
    </div>
</div>

</body>

<script src="js/admincommon.js"></script>

</html>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>Title</title>
    <link rel="stylesheet" href="css/admincommon.css">
    <link rel="stylesheet" href="css/adiminUser.css">
</head>
<body>
<div id="login">
    <div class="headerdiv">
        <div class="login_l">
            欢迎您登录系统，<a href="">zhangsan</a>
        </div>
            <div class="login_r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/admin/close.png" alt="">&nbsp;<a href="adminindexlogin.jsp">退出</a></div>
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
            <span>起始日期&nbsp;:&nbsp;</span><input type="text" value="2017-01-10">
            <span>终止日期&nbsp;:&nbsp;</span><input type="text" value="2017-01-19">
            <input type="button" class="btnc" value="查&nbsp;询">
        </div>
        <div class="tianjia2">
            <span>姓名/用户名&nbsp;:&nbsp;</span><input type="text" value="">
            <span>身份证号&nbsp;:&nbsp;</span><input type="text" value="">
            <span>联系电话&nbsp;:&nbsp;</span><input type="text" value="">
            <input type="button" class="btnc" value="查&nbsp;询">
        </div>
        <div class="tbes">
            <table border="1" cellspacing="0" cellpadding="0" id="t">
                <tr bgcolor="#808080" id="trt">
                    <td width="35" height="38">编号</td>
                    <td width="50">姓名</td>
                    <td width="58">用户名</td>
                    <td width="40">性别</td>
                    <td width="80">身份证号</td>
                    <td width="40">年龄</td>
                    <td width="90">通讯地址</td>
                    <td width="90">联系电话</td>
                    <td width="75">注册时间</td>
                    <td width="65">账户余额</td>
                    <td width="45">状态</td>
                    <td width="60"></td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>张三三</td>
                    <td>zhang</td>
                    <td>男</td>
                    <td>130984119207285728</td>
                    <td>24</td>
                    <td>北京西城区南里士路66号</td>
                    <td>18210608547</td>
                    <td>2017-1-12</td>
                    <td class="td_font">1000</td>
                    <td>活动</td>
                    <td class="trdd"><input class="btnc" type="button" value="禁用" onclick=""></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>李四四</td>
                    <td>lisi</td>
                    <td>女</td>
                    <td>130984119306105551</td>
                    <td>23</td>
                    <td>北京西城区海泰大厦202</td>
                    <td>18210608547</td>
                    <td>2017-1-12</td>
                    <td class="td_font">800</td>
                    <td>活动</td>
                    <td class="trdd"><input class="btnc" type="button" value="禁用" onclick=""></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                    <td></td>
                </tr>
            </table>
        </div>
        <div class="butn">
            <ul class="btn_ul">
                <li><input class="firstbtn" type="button" value="首&nbsp;页"></li>
                <li class="next_color"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><input class="firstbtn" type="button" value="末&nbsp;页"></li>
                <li><input class="intext" type="text"></li>
                <li><a href="#">GO</a></li>
            </ul>
        </div>
    </div>
</div>

<div id="footer">
    <p>版权所有碟派科技有限公司</p>
    <p>备案号:京-3032152541</p>
</div>
</body>
<script src="js/admincommon.js">

</script>
</html>
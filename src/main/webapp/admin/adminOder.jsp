<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
   <title>Title</title>
    <link rel="stylesheet" href="css/admincommon.css">
    <link rel="stylesheet" href="css/adminOder.css">
    <style>

    </style>
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
            <span>订&nbsp;&nbsp;单&nbsp;&nbsp;号&nbsp;:&nbsp;</span><input type="text" value="">
            <span>姓名/用户名&nbsp;:&nbsp;</span><input type="text" value="">
            <span>身份证号&nbsp;:&nbsp;</span><input type="text" value="">
            <input type="button" class="btnc" value="查&nbsp;询">
        </div>
        <div class="tbes">
            <table border="1" cellspacing="0" cellpadding="0" id="t">
                <tr bgcolor="#808080" id="trt">
                    <td width="57" height="38">编号</td>
                    <td width="91">订单号</td>
                    <td width="150">订单内容</td>
                    <td width="85">成交时间</td>
                    <td width="74">金额(元)</td>
                    <td width="63">姓名</td>
                    <td width="76">用户名</td>
                    <td width="236">身份证号</td>
                </tr>
                <tr>
                    <td>1</td>
                    <td>100365</td>
                    <td>《总有刁民想害朕》</td>
                    <td>2017-01-19</td>
                    <td>100.00</td>
                    <td>张三三</td>
                    <td>zhangsan</td>
                    <td>110101198708230019</td>
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

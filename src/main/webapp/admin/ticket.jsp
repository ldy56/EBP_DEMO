<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML>
<html>
  <head>
    <title>Title</title>
    <link rel="stylesheet" href="css/admincommon.css">
    <link rel="stylesheet" href="css/adminTicket.css">
    <style>

    </style>
</head>
<body>
<div id="login">
    <div class="headerdiv">
        <div class="login_l">
            欢迎您登录系统，<a href="">zhangsan</a>
        </div>
            <div class="login_r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/admin/close.png" alt="">&nbsp;<a href="adminindexlogin.html">退出</a></div>
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
            <span>起始日期&nbsp;:&nbsp;</span><input type="date" id="begin" value="${begin }">
            <span>终止日期&nbsp;:&nbsp;</span><input type="date" id="end" value="${end }">
            <input type="submit" class="btnc" value="查&nbsp;询" onclick="queryByDate()">
            
            <input type="button" class="btnc" value="增加票项" onclick="tianjia()"></div>
        <div class="tbes">
            <table border="1" cellspacing="0" cellpadding="0" id="t">
                <tr bgcolor="#808080" id="trt">
                    <td width="58" height="38">编号</td>
                    <td width="145">描述</td>
                    <td width="148">时间</td>
                    <td width="83">总张数（张）</td>
                    <td width="95">剩余票数（张）</td>
                    <td width="68">单价(元)</td>
                    <td width="67">状态</td>
                    <td width="67">封面</td>
                    <td width="177"></td>
                </tr>
                <c:forEach items="${Tickets }" var="tk" varStatus="st">
                <tr id="tr${st.index }">
                    <td height="38">${tk.tid }</td>
                    <td>${tk.descs }</td>
                    <td>${tk.startTime }</td>
                    <td>${tk.amount }</td>
                    <td>${tk.balance }</td>
                    <td>${tk.price }</td>
                    <td>
	                    <a id="stat_tick${tk.tid }" onclick="return upTicketStatus(${tk.tid })">
	                    <c:if test="${tk.status ==1 }">售票中</c:if>
	                    <c:if test="${tk.status ==0 }"><font color="red">已停售</font></c:if>	
	                    </a>	
                    </td>
                    <td>
                    
                    <img id="photo${tk.tid }" src="../getPhotoByTid?tid=${tk.tid }" width="65" height="65"
                     onclick="document.getElementById('fileToUpload${tk.tid }').click();"/>
                     
                    <div style="display:none;">
						 <form id="form_face${tk.tid }" method="post" action="uploadPhoto" enctype="multipart/form-data">
						  <input type="file"  name="file" id="fileToUpload${tk.tid }" 
						  onchange="fileSelected(${tk.tid });" style="display:none;">
						 </form>
					 </div>
                    
                    </td>
                    <td><input class="btnc" type="button" value="删除" onclick="return shanchu(${tk.tid })">
                    <input class="btnc" type="button" value="修改" onclick="xiugai(${st.index },${tk.tid })"></td>
                </tr>
               </c:forEach>

            </table>
            <div class="butn">
                <ul class="btn_ul">
                	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                    <li><input class="firstbtn" type="button" value="首&nbsp;页" 
                    onclick="window.location='queryTickets?begin=${begin}&end=${end}&currentPage=1'"></li>
                    <li><input class="firstbtn" type="button" value="上一页" 
                    onclick="window.location='queryTickets?begin=${begin}&end=${end}&currentPage=${currentPage-1 }'"></li>
                    <li>${currentPage }</li>
                    <li>/</li>
                    <li>${pageCount }</li>
                    <li><input class="firstbtn" type="button" value="下一页" 
                    onclick="window.location='queryTickets?begin=${begin}&end=${end}&currentPage=${currentPage+1 }'"></li>
                    <li><input class="firstbtn" type="button" value="末&nbsp;页"
                    onclick="window.location='queryTickets?begin=${begin}&end=${end}&currentPage=${pageCount }'"></li>
                    
                </ul>
            </div>
        </div>
    </div>
</div>
<div id="footer">
    <p>版权所有碟派科技有限公司</p>
    <p>备案号:京-3032152541</p>
</div>
</body>
<script src="js/jquery-2.1.3.min.js"></script>
<script src="js/admincommon.js"></script>
<script src="js/adminTicket.js"></script>

</html>
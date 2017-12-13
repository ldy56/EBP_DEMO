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
    <link rel="stylesheet" href="css/VIPmessage.css">
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
        <a href="index.jsp">碟派网</a><span>&nbsp;>&nbsp;</span><a href="">会员信息</a>
    </div>
</div>
<div id="content">
    <div class="idmessage">
        <div class="id_left">
            <div class="pic">
					<img  src="getFileByUid?id=${session_user.uid}" 
					width="125" height="125" onclick="fileSelect();">
					 
					 <div style="display:none;">
						 <form id="form_face" method="post" action="uploadPhoto" enctype="multipart/form-data">
						  <input type="file" name="file" id="fileToUpload" onchange="fileSelected();" style="display:none;">
						 </form>
					 </div>
					 
                 <div class="adminm">
                    <p>用户名：${session_user.username}</p>
                    <p>账户余额：${session_user.balance}元</p>
                    <p>积分：<span>0</span></p>
                    <div><input type="button" onclick="window.location='chongzhi.jsp';" value="充&nbsp;&nbsp;值"></div>
                </div>
            </div>
            <div class="passwd">
            <form action="user/updateUserById?uid=${session_user.uid}" method="post" onsubmit="return check()">
                <table>
                    <tr align="right">
                        <td>密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码&nbsp;:&nbsp;</td>
                        <td class="inpt">&nbsp;<input type="password" name="password" id="password"></td>
                    </tr>
                    <tr align="right">
                        <td height="60">确认密码&nbsp;:&nbsp;</td>
                        <td class="inpt">&nbsp;<input type="password" id="passwordC"></td>
                    </tr>
                    <tr>
                        <td height="40" class="bt" colspan="2" align="center"><input class="input1" type="submit" value="确&nbsp;认"></td>
                    </tr>
                </table>
                </form>
            </div>
        </div>
        <div class="id-hr"></div>
        <div class="id_right">
        <form action="user/updateUserById?uid=${session_user.uid}" method="post">
            <table>
                <tr>
                    <td align="right">真实姓名&nbsp;:&nbsp;</td>
                    <td class="inpt">&nbsp;<input type="text" name="name" value="${session_user.name}"></td>
                </tr>
                <tr valign="bottom">
                    <td height="33" align="right">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别&nbsp;:&nbsp;</td>
                    <td>&nbsp;<input type="radio" name="gender" value="1" id="boy" <c:if test="${session_user.gender ==1}">checked</c:if>  >&nbsp;<label for="boy">男</label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="radio" name="gender" id="girl" value="0" <c:if test="${session_user.gender ==0}">checked</c:if>>&nbsp;<label for="girl">女</label></td>
                </tr>
                <tr>
                    <td height="50" align="right">身份证号&nbsp;:&nbsp;</td>
                    <td class="inpt">&nbsp;<input type="text" name="idCard" value="${session_user.idCard}"></td>
                </tr>
                <tr>
                    <td height="30" align="right">年&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;龄&nbsp;:&nbsp;</td>
                    <td>&nbsp;<input type="text" name="age" value="${session_user.age}"></td>
                </tr>
                <tr>
                    <td height="50" align="right">通讯地址&nbsp;:&nbsp;</td>
                    <td class="inpt">&nbsp;<input type="text" name="address" value="${session_user.address}"></td>
                </tr>
                <tr>
                    <td height="50" align="right">联系电话&nbsp;:&nbsp;</td>
                    <td class="inpt">&nbsp;<input type="text" name="telno" value="${session_user.telno}"></td>
                </tr>
            </table>
            <div class="btndiv">
                <input type="submit" class="input1 input2" value="修&nbsp;改"><input type="reset" class="input1 input3" value="重&nbsp;置">
            </div>
            </form>
        </div>
    </div>
    <div class="look_dd">
        <div>近期订单</div><div><a href="">查看所有订单&nbsp;>></a></div>
        <div class="tabs">
            <table class="table2" border="0" cellpadding="0" cellspacing="0">
                <tr bgcolor="#F5F5F5" class="trs2">
                    <th width="150" height="36">序&nbsp;&nbsp;号</th>
                    <th width="150">描述</th>
                    <th width="300">订单号</th>
                    <th width="36">单价</th>
                    <th width="265">购票张数</th>
                    <th width="58">金额(元)</th>
                    <th width="260">生成时间</th>
                </tr>
                <tr class="trs2">
                    <td height="40">1</td>
                    <td>《总有刁民想害朕》</td>
                    <td>2389403840003</td>
                    <td>50元</td>
                    <td>6</td>
                    <td>300</td>
                    <td>2017-01-17</td>

                </tr>
                <tr class="trs2">
                    <td height="40">2</td>
                    <td>《28岁未成年》</td>
                    <td>2389403840004</td>
                    <td>90元</td>
                    <td>4</td>
                    <td>360</td>
                    <td>2017-01-16</td>
                </tr>
                </table>
        </div>
    </div>

<script type="text/javascript">
function fileSelect() {
    document.getElementById("fileToUpload").click();
}
function fileSelected() {
  $("#form_face").submit();
}
function check(){
	if($("#password").val()==""){
		return false;
	}
	if($("#password").val()==$("#passwordC").val()){
		return true;
	}
	alert("两次密码不一致！");
	return false;
}

</script>

</div>
<div id="footer">
    <%@ include file="WEB-INF/jspf/footer.jsp" %>
</div>
</body>
</html>
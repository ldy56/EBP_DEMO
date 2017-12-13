<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE HTML>
<html>
  <head>
    
    <title>Title</title>
    <link rel="stylesheet" href="css/admincommon.css">
    <link rel="stylesheet" href="css/adminindexlogin.css">
    <style>

    </style>

</head>
<body>
<div id="login">
    <div class="headerdiv">
        <div class="login_l">
            欢迎您登录系统
        </div>
            <!--<div class="login_r">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<img src="images/admin/close.png" alt="">&nbsp;<a href="#">退出</a></div>-->
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
    <div class="denglu">
    <div class="right_top">
        <!--<img src="images/pic2.png" align="center">-->
        <h2>碟派后台登录系统</h2></div>
    <div class="right_bottom">
        <div class="right_l"><img src="images/other/head_portrait.png" alt=""></div>
        <div class="right_r">
            <form action="login" method="post">
                <table>
                    <tr>
                        <td align="right" height="40">用户名：</td>
                        <td><input class="ipt" type="text" name="username" required size="13" placeholder="${errors}"></td>
                    </tr>
                    <tr>
                        <td align="right" height="40">密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                        <td><input class="ipt" type="password" name="password" required size="13" placeholder="${errors}"></td>
                    </tr>
                    <tr>
                        <td height="40"></td>
                        <td class="forget" align="left"><input type="checkbox" id="jizhu">
                            <label for="jizhu">记住密码</label><br></td>
                    </tr>
                </table>
                <div><input type="submit" value="登录" class="btn1" >&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="reset" value="取消" class="btn2"></div>
            </form>
        </div>
    </div>
    </div>
</div>
<div id="footer">
    <p>版权所有碟派科技有限公司</p>
    <p>备案号:京-3032152541</p>
</div>
</body>
<script src="js/adminindex.js">
</script>
</html>
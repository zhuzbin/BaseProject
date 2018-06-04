<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@page isELIgnored="false" %>
<html>
<head>
    <title>登录</title>
    <link href="${pageContext.request.contextPath}/static/login/css/style_log.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/css/style.css">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/css/jquery.ui.all.css">
    <style>.errors{color:red;}</style>
</head>
<body class="login" mycollectionplug="bind">
<div class="errors">${error}</div>
<div class="login_m">
    <div class="login_logo"><img src="${pageContext.request.contextPath}/static/login/image//logo.png" width="196" height="46"></div>
    <div class="login_boder">
        <form action="" method="post">
        <div class="login_padding" id="login_model">
            <div class="login_margin">
                <span class="login_span">用户名：</span>
                <label>
                    <input type="text" name="username" class="txt_input txt_input2" value="<shiro:principal/>">
                </label>
            </div>
            <div class="login_margin">
                <span class="login_span">密码：</span>
                <label>
                    <input type="password" name="password" class="txt_input" value="">
                </label>
            </div>
            <div class="login_margin">
                <label>
                    <input type="text" name="randomcode"  class="txt_inputCode" value="">
                </label>
                <img src="${pageContext.request.contextPath}/imageCode">
            </div>
            <div class="rem_sub">
                <div class="rem_sub_l">
                    <input type="checkbox" name="rememberMe" value="true">
                    <label for="checkbox">记住我</label>
                </div>
                <label>
                    <input type="submit" class="sub_button" name="button" id="button" style="opacity: 0.7;" value="登录">
                </label>
            </div>
        </div>
        </form>
    </div>
</div>
</body>
</html>
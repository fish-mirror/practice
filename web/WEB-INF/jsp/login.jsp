<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>

<c:set var="assets_domain" value="http://opvyx1uc9.bkt.clouddn.com" scope="request"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <meta name="copyright" content="YUJING&WANGQI@浙江传媒学院"/>
    <title>登录</title>
    <link rel="stylesheet" href="${assets_domain}/practice/css/login.css">
</head>
<body>
<div class="wrap fadeInDown">
    <h1>实习管理系统</h1>
    <h2>用实践，探索更大的世界</h2>
    <div id="loginForm">
        <div class="form-group" id="account">
            <input type="text" name="account" placeholder="学号">
            <div class="check"></div>
        </div>
        <div class="form-group" id="password">
            <input type="password" name="password" placeholder="密码">
            <div class="check"></div>
        </div>
        <div class="form-group">
            <button class="btn btn-submit" id="loginBtn">
                登录
            </button>
        </div>
        <div class="tip" id="tip"></div>
    </div>
    <footer>推荐使用chrome48以上版本访问<br>Copyright © 2017 aqi&amp;ayu. All Rights Reserved.</footer>
</div>
</body>
<script src="${assets_domain}/base/js/jquery.min.js"></script>
<script src="${assets_domain}/practice/js/login.js"></script>
</html>


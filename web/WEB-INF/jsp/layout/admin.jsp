<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp"%>
<!DOCTYPE HTML>
<html>
<c:set var="assets_domain" value="http://localhost" scope="request"/>

<%@ include file="/WEB-INF/jsp/include/admin_head.jsp" %>
<body>
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container-fluid">
        <div class="navbar-header">
            <a href="/" class="navbar-brand">实习系统管理后台</a>
        </div>
        <div class="navbar-collapse collapse" id="navbar">
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a>${thisUser.username}</a>
                </li>
                <li>
                    <a href="/logout">退出</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <tiles:insertAttribute name="menu" />
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <c:if test="${success}">
                <div class="message">操作成功</div>
            </c:if>
            <tiles:insertAttribute name="body" />
        </div>
    </div>
</div>
</body>
</html>



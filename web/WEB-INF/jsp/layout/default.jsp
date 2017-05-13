<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>
<!DOCTYPE HTML>
<html>
<c:set var="assets_domain" value="http://opvyx1uc9.bkt.clouddn.com" scope="request"/>

<%@ include file="/WEB-INF/jsp/include/head.jsp" %>
<body>
<div id="wrapper" class="clearfix">
    <%-- 一级导航--%>
    <tiles:insertAttribute name="header"/>
    <div class="container">
        <div class="container-fluid">
            <div class="row">
                <%-- 二级导航--%>
                <tiles:insertAttribute name="nav"/>
                <%-- 主体 --%>
                <tiles:insertAttribute name="main"/>

            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jsp/include/footer.jsp" %>
<%@ include file="/WEB-INF/jsp/include/foot_js.jsp" %>
</body>
</html>

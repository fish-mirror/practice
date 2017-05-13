<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>

<%@ include file="/WEB-INF/jsp/include/account_head.jsp" %>
<body>
<c:set var="assets_domain" value="http://opvyx1uc9.bkt.clouddn.com" scope="request"/>

<tiles:insertAttribute name="main" />
<script src="${assets_domain}/practice/js/jquery.min.js"></script>
<script src="${assets_domain}/practice/js/bootstrap.min.js"></script>
<script src="${assets_domain}/practice/js/bootstrapValidator.min.js"></script>
<script src="${assets_domain}js/data.js"></script>
</body>
</html>
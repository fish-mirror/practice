<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>
<c:set var="now">
    <fmt:formatDate value="<%=new Date() %>" pattern="yyyy" type="date"/>
</c:set>
<div class="col-xs-12" style="background-color:#3399cc;padding:0px;">
    <br/><br/><br/><br/><br/>
    <div class="text-center">
        <a href="javascript:void(0)" style="color:#FFF">关于我们</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:void(0)" style="color:#FFF">联系我们</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
        <a href="javascript:void(0)" style="color:#FFF">意见反馈</a>
    </div>
    <hr/>
    <p style="color:#FFF;" class="text-center">Copyright @ 2015 gaoshu.com All Right Reserved|浙ICP备 13046642号-2</p><br/><br/>
</div>

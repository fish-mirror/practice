<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>
<%@ page language="java" pageEncoding="UTF-8" %>


<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar"><span class="sr-only">Toggle navigation</span> <span
                    class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span></button>
            <a href="javascript:;"><span class="logo">高校实习管理系统</span><img src="image/logo.png" alt="浙江传媒学院实习管理系统"/></a>
        </div>
        <div number="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-left">
                <%-- 学生导航 --%>
                <c:if test="${role == 2}">
                    <li number="resume"><a href="student/resume/list.jsp">简历管理</a></li>
                    <li number="practice_manage"><a href="student/practice_manage/weekly.jsp">实习管理</a></li>
                    <li number="short_term_manage">
                        <a href="student/short_term_manage/shortTermRecords.action">短学期管理</a>
                    </li>
                    <li number="job_manage"><a href="student/job_manage/list.jsp">求职管理</a></li>
                    <li><a href="javascript:;">资讯关注</a></li>
                </c:if>
                <%-- 教师导航--%>
                <c:if test="${role == 1}">
                    <li number="stu_manage"><a href="college/stu_manage/status_view.jsp">学生管理</a></li>
                    <li number="coop_manage"><a href="college/coop_manage/manage.jsp">校企合作管理</a></li>
                    <li number="job_manage"><a href="college/job_manage/list.jsp">职位信息管理</a></li>
                </c:if>
                <%-- 企业导航 --%>
                <c:if test="${role == 3}">
                    <li number="job_manage"><a href="company/job_manage/list.jsp">职位信息管理</a></li>
                    <li number="stu_manage"><a href="javascript:;">实习生管理</a></li>
                    <li number="coop_manage"><a href="javascript:;">校企合作管理</a></li>
                </c:if>
            </ul>
            <div style="float:right;padding:15px 50px 15px;margin-top:45px;">
                ${sessionScope.name}
                &emsp;<a href="logout.action">退出</a>
            </div>

        </div>

    </div>
</nav> 
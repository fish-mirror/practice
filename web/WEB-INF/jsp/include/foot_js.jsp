<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>

<c:set var="assets_domain" value="http://localhost" scope="request"/>

<script type=text/javascript src="${assets_domain}/practice/js/html5shiv.js"></script>
<script type=text/javascript src="${assets_domain}/practice/js/respond.min.js"></script>
<![endif]-->
<script>
    window.onload = initPage;

    //初始化界面
    function initPage() {
        navStyle();

        getStuStatus(null, null, null, null, 1);
        getClassList();

        getJobRecriutList(null, 1);

        var pid = "${param.pid }";
        if (pid == null || pid == "" || pid == "false") {
            pid = 1;
        }
        getComList(pid);

        // 合作意向管理页
        var institute = document.getElementById("institute");
        if(institute!=null){
            loadIntentionList(institute.value);
        }

        loadCoopList();

        // 发布合作意向
        var intention_id = document.getElementById("intention_id");
        if(intention_id.value != null && intention_id.value != ""){
            loadIntentionData(intention_id .value);
        }

        // 查看合作意向
        var intention_id = document.getElementById("intention_id");
        loadIntentionData2(intention_id.value);

        // 查看企业
        var com_id = document.getElementById("com_id");
        loadCompanyData(com_id.value);

        // 查看短学期项目
        var com_id = document.getElementById("com_id");
        loadCompanyData(com_id.value);

    }
</script>
<script src="${assets_domain}/practice/js/data.js"></script>

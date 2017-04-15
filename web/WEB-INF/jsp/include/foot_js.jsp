<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>

<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery -->
<script src="js/jquery.min.js"></script>
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　-->
<script src="js/bootstrap.min.js"></script>
<!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 -->
<!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 -->
<!--[if lt IE 9]>
<script type=text/javascript src="http://asserts.ayuya.me/js/html5shiv.js"></script>
<script type=text/javascript src="http://asserts.ayuya.me/js/respond.min.js"></script>
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
<script src="http://asserts.ayuya.me/js/data.js"></script>

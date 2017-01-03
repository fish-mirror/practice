<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>发布合作意向-实习管理系统</title>
	<!-- Bootstrap --> 
    <link href="css/bootstrap.min.css" rel="stylesheet" /> 
    <!--你自己的样式文件 --> 
    <link href="css/index.css" rel="stylesheet" /> 
    <!-- HTML5 Shim 和 Respond.js 用于让 IE8 支持 HTML5元素和媒体查询 --> 
    <!-- 注意： 如果通过 file://  引入 Respond.js 文件，则该文件无法起效果 --> 
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]--> 
</head>
<body>
<%@ include file="/college/nav.jspf" %>
<div class="container"> 
	<div class="container-fluid"> 
		<div class="row"> 
			<%@ include file="/college/c_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        		<form class="form-horizontal" method="post" action="college/coop_manage/saveIntention.action">
            		<div class="form-group">
            			<label for="tittle" class="col-sm-2 control-label">标题</label>
			            <div class="col-sm-10">
			            	<input type="text" class="form-control" id="tittle" name="tittle">
			            </div>
            		</div>
		            <div class="form-group">
		            	<label for="content" class="col-sm-2 control-label">内容</label>
		                <div class="col-sm-10">
			                <textarea class="form-control" rows="8" id="content" name="content"></textarea>
						</div>
					</div>
					<input type="hidden" id="intention_id" name="id" value="${param.intention_id }" />
					<input type="hidden" id="col_id" name="col_id" value="${sessionScope.user.id }" />
		            <input type="hidden" id="institute" name="institute" value="${sessionScope.institute }" />
        			<div style="color: red;">${info} </div>
	                <div class="form-group">
	                	<div class="col-sm-offset-2 col-sm-9">
	                    	<button type="submit" class="btn btn-default">发&emsp;布</button>
		                </div>
					</div>
				</form>
			</div> 
		</div>
	</div> 
</div>  
<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery --> 
<script src="js/jquery.min.js"></script> 
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　--> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/data.js"></script> 
<script type="text/javascript">
window.onload = initPage;

//初始化界面
function initPage(){
	navStyle();
	var intention_id = document.getElementById("intention_id");
	if(intention_id.value != null && intention_id.value != ""){
		loadIntentionData(intention_id .value);
	}
}

</script>
</body>
</html>
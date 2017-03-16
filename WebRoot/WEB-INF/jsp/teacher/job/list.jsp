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
	<title>进行中的招聘-实习管理系统</title>
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
			<%@ include file="/college/j_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
		       
         		<div class="table-responsive"> 
          			<table class="table table-striped"> 
		           	<thead> 
			            <tr> 
				            <th width="40%">职位名称</th> 
				            <th>发布方</th> 
				            <th>工作地点</th> 
				            <th>职位状态</th> 
			            </tr> 
					</thead>
					<tbody number="job-records">
						
						
					</tbody> 
				</table>
			</div> 
         	<div class="table-bottom"> 
		        <ul number="pageDiv" class="pagination">
		        </ul> 
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
	getJobRecriutList(null,1);
}
</script> 
</body>
</html>
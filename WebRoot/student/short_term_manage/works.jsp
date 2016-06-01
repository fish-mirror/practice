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
	<title>实习作品-实习管理系统</title>
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
<%@ include file="/student/nav.jspf" %>
<div class="container"> 
	<div class="container-fluid"> 
		<div class="row"> 
			<%@ include file="/student/p_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
		        <ul class="nav nav-pills"> 
		        	<li class="active"><a href="#">上传作品</a></li> 
		        </ul> 
         		<div class="table-responsive"> 
          			<table class="table table-striped"> 
				        <thead> 
					        <tr> 
						        <th>时间</th> 
						        <th>实习地点</th> 
						        <th>实习职位</th> 
						        <th>实习作品管理</th> 
					        </tr> 
				        </thead> 
           				<tbody> 
				            <tr> 
					            <td>2015.07.01-2015.07.08</td> 
					            <td>杭州-新航线公司</td> 
					            <td>产品开发</td> 
					            <td>
						            <a href="#">下载</a>
						            <a href="#">删除</a>
					            </td> 
				            </tr>
				            <tr> 
					            <td>2015.07.01-2015.07.08</td> 
					            <td>杭州-新航线公司</td> 
					            <td>产品开发</td> 
					            <td>
						            <a href="#">下载</a>
						            <a href="#">删除</a>
					            </td> 
				            </tr> 
				            <tr> 
					            <td>2015.07.01-2015.07.08</td> 
					            <td>杭州-新航线公司</td> 
					            <td>产品开发</td> 
					            <td>
						            <a href="#">下载</a>
						            <a href="#">删除</a>
					            </td> 
				            </tr>
				       	</tbody> 
					</table> 
				</div> 
         		<div class="table-bottom"> 
					<ul class="pagination"> 
						<li><a href="#">&laquo;</a></li> 
						<li class="active"><a href="#">1</a></li> 
						<li class="#"><a href="#">2</a></li> 
						<li><a href="#">3</a></li> 
						<li><a href="#">4</a></li> 
						<li><a href="#">5</a></li> 
						<li><a href="#">&raquo;</a></li> 
					</ul> 
				</div> 
        	</div> 
		</div> 
	</div> 
</div>  
<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery --> 
<script src="js/jquery.min.js"></script> 
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　--> 
<script src="js/bootstrap.min.js"></script> 
<script type="text/javascript">
window.onload = initPage;

//初始化界面
function initPage(){
	navStyle();

	
}
</script> 
</body>
</html>
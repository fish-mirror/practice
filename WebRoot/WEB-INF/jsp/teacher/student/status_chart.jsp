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
	<title>学生管理-实习管理系统</title>
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
    		<%@ include file="/college/s_m-nav.jspf" %>
        	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
         		<ul number="select_list" class="nav nav-pills">
          			<li number="institute" class="active"><a href="javascript:;">全部</a></li>
			        <li number="graduate" ><a href="javascript:;">毕业班学生</a></li>
			        <li number="non_graduate" ><a href="javascript:;" > 非毕业班学生    </a></li>
         		</ul> 
         		<div number="pie" style="min-width:500px;height:400px"></div>
         	</div>
        </div> 
        
	</div> 
</div> 

<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery --> 
<script src="js/jquery.min.js"></script> 
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　--> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/data.js"></script> 
<!--<script type="text/javascript" src="http://cdn.hcharts.cn/highcharts/exporting.js"></script> -->
<script type="text/javascript" src="js/highcharts.js"></script>
<script src="js/pie.js"></script> 
<script>
window.onload = initPage;

//初始化界面
function initPage(){
	
	navStyle();
}

</script> 
<script src="js/data.js"></script>  
</body>
</html>
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
	<title>实习周记-实习管理系统</title>
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
	    		
				<!--   <h2>实习周记</h2>-->
				<div class="week-record">
				    <div><a href="#">120708127 张三</a>·12网络工程1班</div>
				    <div class="record-content">今天写了好多页面写各种浏览器兼容好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦哦。</div>
				    <div class="record-inform">
				        <div class="record-place"><span class="glyphicon glyphicon-map-marker"></span> 位于杭州市·新航线公司</div> 
				        <div class="record-time">2015/11/28 20：30</div>
				    </div>
				</div>
                <div class="week-record">
                    <div><a href="#">120708127 张三</a>·12网络工程1班</div>
                    <div class="record-content">今天写了好多页面写各种浏览器兼容好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦哦。</div>
                    <div class="record-inform">
                        <div class="record-place"><span class="glyphicon glyphicon-map-marker"></span> 位于杭州市·新航线公司</div> 
                        <div class="record-time">2015/11/28 20：30</div>
                    </div>
                </div>
                <div class="week-record">
                    <div><a href="#">120708127 张三</a>·12网络工程1班</div>
                    <div class="record-content">今天写了好多页面写各种浏览器兼容好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦哦。</div>
                    <div class="record-inform">
                        <div class="record-place"><span class="glyphicon glyphicon-map-marker"></span> 位于杭州市·新航线公司</div> 
                        <div class="record-time">2015/11/28 20：30</div>
                    </div>
                </div>
                <div class="week-record">
                    <div><a href="#">120708127 张三</a>·12网络工程1班</div>
                    <div class="record-content">今天写了好多页面写各种浏览器兼容好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦好烦哦。</div>
                    <div class="record-inform">
                        <div class="record-place"><span class="glyphicon glyphicon-map-marker"></span> 位于杭州市·新航线公司</div> 
                        <div class="record-time">2015/11/28 20：30</div>
                    </div>
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
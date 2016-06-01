<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>短学期记录-实习管理系统</title>
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
			<%@ include file="/student/s_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
			   
				<div class="table-responsive"> 
					<s:if test="strs!=null">
						<s:iterator value="strs">
							 <div class="col-sm-10 solit-border">
					          	<div class="col-sm-12"><h4><a href="<s:property value="shortTermProject.fileUrl"/>" title="下载附件以得到详细项目介绍"><s:property value="shortTermProject.name"/></a>&emsp;<small><s:property value="shortTermProject.company.name"/></small></h4></div>
					          	<div class="col-sm-12"><p class="text-muted"><s:property value="shortTermProject.purpose"/></p></div>
					          	<div class="col-sm-6"><p>实践学期：<s:property value="shortTermProject.term"/></p></div><div class="col-sm-6"><p>实践时间：<s:property value="shortTermProject.time"/></p></div>
					          	<div class="col-sm-6"><p>实践地点：<s:property value="shortTermProject.place"/></p></div>
					          	<!-- <div class="col-sm-6"><p>实践报告：未上传&emsp;<button type="button" class="btn btn-default btn-xs">上传报告</button></p></div> -->
					          	<div class="col-sm-6">
					          		<p>实践报告：
					          			<s:if test="url!=null">
						          			已上传
						          			<a href="<s:property value="url"/>">下载</a>
						          			&emsp;
						          			<button type="button" class="btn btn-default btn-xs">删除</button>
					          			</s:if>
					          			<s:else>
					          				未上传
						          			&emsp;
						          			<button type="button" class="btn btn-default btn-xs">上传</button> 
						          		</s:else>
						          	</div>
					          	<div class="col-sm-6">
					          		<p>实践得分：
										<s:if test="shortTermComments.size()>0">
											<s:property value="getgetAverageGrade()"/>
					          			</s:if>
					          			<s:else>
					          				未评分
						          		</s:else>
						          	</p>
						        </div>
					         </div>
				         </s:iterator>
				    </s:if>
				    <s:else>
	         			<div id="body">还没有参与短学期实习项目！</div>
	         		</s:else>
				</div> 
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
	var institute = document.getElementById("institute");
	if(institute!=null){
	loadIntentionList(institute.value);
	}
	
}
</script>  
</body>
</html>
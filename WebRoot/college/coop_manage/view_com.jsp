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
	<title>查看企业信息-实习管理系统</title>
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
<div class="container"> 
	<div class="container-fluid"> 
		<div class="row"> 
			
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
		        
		        	
        			<div class="table-responsive"> 
         				<table class="table table-striped"> 
		         				<div class="panel-group" id="accordion">
								   <div class="panel panel-default">
								      <div class="panel-heading">
								         <h4 class="panel-title"> 查看企业信息</h4>
								      </div>   	
								      <div>
         								 <div class="panel-body">
         									<div class="col-sm-6">
        										<p id="name"></p>
        										<p id="type"></p>
        										<p id="linkman"></p>
        										<p id="tel"></p>
        										<p id="location"></p>
        										<p id="address"></p>
         									</div>
								      	</div>
								    </div>
								  </div>
								  
								  
								</div>
								
							   <input type="hidden" id="com_id" name="com_id" value="${param.company_id }"/>
							   <div class="form-group">
							   
							   </div>
							</form>
							
				    		
					</table> 
					
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
<script>
window.onload = initPage;

//初始化界面
function initPage(){
	
	var com_id = document.getElementById("com_id");
	loadCompanyData(com_id.value);
}


</script> 
</body>
</html>
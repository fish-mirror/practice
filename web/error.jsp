<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<base href="<%=basePath%>">
 	<link href="css/bootstrap.min.css" rel="stylesheet" />
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <style>
		.bg{
		position:absolute;
		z-index:-1;
		}
		h4,label{color:#434343;}
		.login{
			padding:10px 20px 0px;
			border-color:#bfbfbf;
			border-style:solid;
			border-width:thin;
			border-radius:5px
		}
	</style>
</head>

<body>

<div class="container" style="min-width:800px">
	<br /><br /><br />

	<!-- 学校LOGO -->
	<div class="col-xs-6">
    	<br /><br />
    	<img src="image/logo_school.png" />
    </div>
    <!-- 主体 -->
	<div class="col-xs-12">
		<br /><br /><br />
		
		<div class="bg">
			<img src="image/login_bg.png" />
			
		</div>
		<div class="col-xs-8"></div>
		<!-- 登录框 -->
		<div class="col-xs-4 login">
	      	<h4 class="text-center"><b>实 习 管 理 系 统</b></h4><hr />
	        <form action="login.action" role="form" method="post">
	        	<div class="form-group">
	            	<label>用户名：</label>
	            		<input type="text" name="name" class="form-control" placeholder="请在此输入您的用户名" required="required">
	            </div>
	            <div class="form-group">
	            	<label>密　码：</label>
	                <input type="password" name="pwd" class="form-control" placeholder="请在此输入您的密码" required="required">
	            </div>
				<div style="color: red;">${info}${param.info}</div>
				
	            <div class="text-center" style="padding-top:15px">
	            	<button type="submit" class="btn btn-primary"> &nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button>
	            </div>
	        </form>
	        <br />
     	</div>
	</div>
   </div>
</div>
</body>
</html>
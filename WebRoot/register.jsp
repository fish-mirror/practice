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
<title>企业用户注册</title>
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
    	<!-- <img src="image/logo_school.png" /> -->
    </div>
    <!-- 主体 -->
	<div class="col-xs-12">
		<br /><br /><br />
		
		<div class="bg">
			<img src="image/login_bg.png" />
			
		</div>
		<div class="col-xs-6"></div>
		<!-- 登录框 -->
		<div class="col-xs-6 login">
	      	<h4 class="text-center"><b>实 习 管 理 系 统</b></h4><hr />
	        <form id="defaultForm" class="form-horizontal" method="post" action="registerCompany.action">
            		<div class="form-group">
            			<label for="id" class="col-sm-2 control-label">用户名&emsp;</label>
			            <div class="col-sm-10">
			            	<input type="text" class="form-control" id="id" name="id">
			            </div>
            		</div>
		            <div class="form-group">
            			<label for="name" class="col-sm-2 control-label">企业名称</label>
			            <div class="col-sm-10">
			            	<input type="text" class="form-control" id="name" name="name">
			            </div>
            		</div>
            		<div class="form-group">
            			<label for="pwd" class="col-sm-2 control-label">密&emsp;&emsp;码</label>
			            <div class="col-sm-10">
			            	<input type="password" class="form-control" id="pwd" name="pwd">
			            </div>
            		</div>
            		<div class="form-group">
            			<label for="repwd" class="col-sm-2 control-label">确认密码</label>
			            <div class="col-sm-10">
			            	<input type="password" class="form-control" id="repwd" name="repwd">
			            </div>
            		</div>
		            <div style="color: red;">${info} </div>
	                <div class="form-group">
	                	<div class="col-sm-offset-2 col-sm-4">
	                    	<button type="submit" class="btn btn-primary" onclick="createCom()">注册企业账号</button>
		                </div>
		                <div class="col-sm-4">
	                    	<button type="submit" class="btn btn-default" onclick="toLogin()">登录</button>
		                </div>
					</div>
				</form>
	        <br />
     	</div>
	</div>
   </div>
</div>
<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery --> 
<script src="js/jquery.min.js"></script> 
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　--> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/bootstrapValidator.min.js"></script> 
<script src="js/data.js"></script> 
<script type="text/javascript">
window.onload = initPage;

//初始化界面
function initPage(){
	navStyle();
	
}
$(document).ready(function() {
    $('#defaultForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            id: {
                message: '无效用户名',
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 9,
                        message: '用户名长度必须在3-9个字符之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '用户名只能由字母、数字和下划线组成'
                    }
                }
            },
            name: {
                validators: {
                    notEmpty: {
                        message: '企业名不能为空'
                    }
                }
                
            },
            pwd: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    identical: {
                        field: 'repwd',
                        message: '两次密码不一致'
                    }
                }
            },
            repwd: {
                validators: {
                    notEmpty: {
                        message: '确认密码不能为空'
                    },
                    identical: {
                        field: 'pwd',
                        message: '两次密码不一致'
                    }
                }
            },
        }
    });
});
function toLogin(){
	window.location.href="login.jsp";
}
</script>
</body>
</html>
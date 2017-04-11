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
	<title>编辑个人信息-实习管理系统</title>
	<!-- Bootstrap --> 
    <link href="css/bootstrap.min.css" rel="stylesheet" /> 
    <!-- fileinput上传样式 -->
    <link href="css/fileinput.min.css" rel="stylesheet" /> 
    
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
			<%@ include file="/student/r_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
		        <h2>编辑个人基本信息</h2> 
		        	
        			<div class="table-responsive"> 
         				<table class="table table-striped"> 
	         				<form class="form-horizontal" role="form" action="student/resume/updateStuInfo.action" method="post">
	         					<div class="col-sm-12"><br /></div>
	         					
						      	<!-- 表单 ,js自动填写已有数据 -->
	         					<div style="color: red;">${info} </div>
							    <div class="col-sm-7">
								    <div class="form-group col-sm-12">
								    	<div class="form-group col-sm-6">
									    	<label  class="col-sm-4 control-label">姓 &emsp;&emsp;名</label>
									    	<div class="col-sm-8">
									         	<input type="text" class="form-control" number="name"  disabled>
									      	</div>
									    </div>
									    <div class="form-group col-sm-6">
									    	<label  class="col-sm-4 control-label">性 &emsp;&emsp;别</label>
									    	<div class="col-sm-8">
									         	<input type="text" class="form-control" number="sex"  disabled>
									      	</div>
									    </div>
								    </div>
									<div class="form-group col-sm-12">
								    	<label  class="col-sm-4 control-label">毕业院校</label>
								    	<div class="col-sm-8">
								         	<input type="text" class="form-control" number="school"  value="霍格沃茨学院" disabled>
								      	</div>
								    </div>
									<div class="form-group col-sm-12">
								    	<label  class="col-sm-4 control-label">学 &emsp;&emsp;院</label>
								    	<div class="col-sm-8">
								         	<input type="text" class="form-control" number="institute"  disabled>
								      	</div>
								    </div>
									<div class="form-group col-sm-12">
								    	<label  class="col-sm-4 control-label">专 &emsp;&emsp;业</label>
								    	<div class="col-sm-8">
								         	<input type="text" class="form-control" number="major"  disabled>
								      	</div>
								   	</div>
							    </div>
								<div class="col-sm-5">
									<a href="javascript:;"  data-toggle="modal"  data-target="#myModal">
							        	<img number="head" style="width:150px"
							        	src=""  
							        	class="img-thumbnail" 
							        	alt="点击上传/更改照片" />
							    	</a>
							    </div>
							   	
							   	
							   	<div class="col-sm-12"><hr /><br /></div>
							   
							   
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">出生日期</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="birth" name="birth"
							            	placeholder="请输入yyyy-mm-dd格式">
							      	</div>
								</div>
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">民&emsp;&emsp;族</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="nation" name="nation"
							            	placeholder="请输入民族">
							      	</div>
								</div>
								
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">身&emsp;&emsp;高</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="height" name="height"
							            	placeholder="请输入身高（例163.5）">
							      	</div>
								</div>
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">体&emsp;&emsp;重</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="weight" name="weight"
							            	placeholder="请输入体重（例50）">
							      	</div>
								</div>
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">电&emsp;&emsp;话</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="tel" name="tel"
							            	placeholder="请输入联系电话">
							      	</div>
								</div>
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">邮&emsp;&emsp;箱</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="email" name="email"
							            	placeholder="请输入联系邮箱">
							      	</div>
								</div>
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">政治面貌</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="politics" name="politics">
							      	</div>
								</div>
								<div class="form-group col-sm-6">
							    	<label  class="col-sm-4 control-label">住&emsp;&emsp;址</label>
							    	<div class="col-sm-8">
							         	<input type="text" class="form-control" number="address" name="address" >
							      	</div>
								</div>
								<input type="hidden" number="number"  name="number" value="${sessionScope.user.number} "/>
								
								<div class="form-group">
							      <div class="col-sm-offset-2 col-sm-10">
							      	<button type="submit" class="btn btn-primary">保存信息</button>
							      </div>
							   	</div>
							   	
							</form>
				    		
					</table> 
				</div> 
         		<div class="modal fade" number="myModal" tabindex="-1" role="dialog"
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" 
				               data-dismiss="modal" aria-hidden="true">
				                  &times;
				            </button>
				            <h4 class="modal-title" number="myModalLabel">选择照片</h4>
				         </div>
				         <div class="modal-body">
			            	<form enctype="multipart/form-data">
					         	<label>
					         		请选择不大于100KB的图片，支持jpg,png,gif格式
					         	</label>
					         	<span>注意：上传会覆盖之前已存在的图片！</span>
							    <input number="img" name="img" type="file" class="file">
							</form>
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">关闭
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				</div><!-- /.modal -->
        	</div> 
		</div> 
	</div> 
</div>  
<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery --> 
<script src="js/jquery.min.js"></script> 
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　--> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/data.js"></script> 

<script src="js/fileinput.min.js"></script> 
<script src="js/fileinput_locale_zh.js"></script> 
<script>
window.onload = initPage;
$("#img").fileinput({
    language: 'zh', //设置语言
    'allowedFileExtensions' : ['jpg', 'png','gif'],
    maxFileSize: 100,
    uploadAsync:true,
    uploadUrl:"student/uploadHeadImg.action",
});
$("#img").on("fileuploaded", function (event, data) {
	var data = data.response;
	document.getElementById("head").setAttribute("src",data);
});

//初始化界面
function initPage(){
	navStyle();
	getStuInfo(document.getElementById("number").value);

}
</script>
</body>
</html>
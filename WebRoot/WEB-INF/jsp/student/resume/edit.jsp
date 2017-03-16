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
	<title>添加/编辑简历-实习管理系统</title>
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
			<%@ include file="/student/r_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
		        <h2>添加/编辑简历</h2> 
		        	
        			<div class="table-responsive"> 
         				<table class="table table-striped"> 
	         				<form class="form-horizontal" role="form" method="post" action="student/resume/saveResume.action"> 
	         					<!-- 基本信息折叠样式 -->
		         				<div class="panel-group" number="accordion">
								   <div class="panel panel-default">
								      <div class="panel-heading">
								         <h4 class="panel-title">
								            <a data-toggle="collapse" data-parent="#accordion" 
								               href="#collapseOne">
								         	 基本信息
								         	 
								         	 
								        </a>
								        <span  style="float:right;font-size:10pt">
								        	<a href="javascript:;" onclick="skipToPersonal()">编辑个人基本信息</a>
								        </span>
								        
								      </h4>
								    </div>
								    <div calss="col-sm-12"><div style="color: red;">${info} </div></div>
								    <div number="collapseOne" class="panel-collapse collapse">
         								<div class="panel-body">
         									<div class="col-sm-12">
	         									<div class="col-sm-8">
	         										<div class="col-sm-6"><p number="name"></p></div>
	         										<div class="col-sm-6"><p number="sex"></p></div>
	         										<div class="col-sm-6"><p>毕业院校：霍格沃茨学院</p></div>
	         										<div class="col-sm-6"><p number="graduate_time"></p></div>
	         										<div class="col-sm-6"><p number="institute"></p></div>
	         										<div class="col-sm-6"><p number="major"></p></div>
	         										<div class="col-sm-6"><p number="birth"></p></div>
	         										<div class="col-sm-6"><p number="nation"></p></div>
	         										<div class="col-sm-6"><p number="height"></p></div>
	         										<div class="col-sm-6"><p number="weight"></p></div>
	         										<div class="col-sm-6"><p number="tel"></p></div>
	         										<div class="col-sm-6"><p number="email"></p></div>
	         										<div class="col-sm-6"><p number="politics"></p></div>
	         										<div class="col-sm-6"><p number="address"></p></div>
	         									
	         									</div>
	         									<div class="col-sm-4">
	         										<img number="head" style="width:150px"
											        	src="/practice/image/default-head.png"  
											        	class="img-thumbnail" />
	         									</div>
         									</div>
								      	</div>
								    </div>
								  </div>
								  <div class="panel panel-default">
								    <div class="panel-heading">
								      <h4 class="panel-title">
								        <a>
								          	主修课程
								        </a>
								      </h4>
								    </div>
								    <div number="collapseTwo">
								      <div class="panel-body">
								        <textarea class="form-control" rows="8" number="major_class" name="major_class"></textarea>
								      </div>
								    </div>
								  </div>
								  <div class="panel panel-default">
								    <div class="panel-heading">
								      <h4 class="panel-title">
								        <a>
								          	校园经历
								        </a>
								      </h4>
								    </div>
								    <div number="collapseThree" >
								      <div class="panel-body">
								       <textarea class="form-control" rows="8" number="school_exp" name="school_exp"></textarea>
								      </div>
								    </div>
								  </div>
								</div>
								
								<div class="panel panel-default">
								    <div class="panel-heading">
								      <h4 class="panel-title">
								        <a>
								          	实习经历
								        </a>
								      </h4>
								    </div>
								    <div number="collapseFour" >
								      <div class="panel-body">
								        <textarea class="form-control" rows="8" number="practice_exp" name="practice_exp"></textarea>
								      </div>
								    </div>
								  </div>
								</div>
								<div class="panel panel-default">
								    <div class="panel-heading">
								      <h4 class="panel-title">
								        <a>
								          	获奖以及证书
								        </a>
								      </h4>
								    </div>
								    <div number="collapseFive" >
								      <div class="panel-body">
								        <textarea class="form-control" rows="8" number="certificate" name="certificate"></textarea>
								      </div>
								    </div>
								  </div>
								</div>
								<div class="panel panel-default">
								    <div class="panel-heading">
								      <h4 class="panel-title">
								        <a >
								          	自我评价
								        </a>
								      </h4>
								    </div>
								    <div number="collapseSix">
								      <div class="panel-body">
								       <textarea class="form-control" rows="8" number="self_comment" name="self_comment"></textarea>
								      </div>
								    </div>
								  </div>
								</div>
								<div class="panel panel-default">
								    <div class="panel-heading">
								      <h4 class="panel-title">
								        <a >
								          	简历名（不要为空，否则会提交失败）
								        </a>
								      </h4>
								    </div>
								    <div number="collapseSeven">
								      <div class="panel-body">
								       <input type="text" class="form-control" number="tittle" name="tittle" />
								      </div>
								    </div>
								  </div>
								</div>
								
							   <input type="hidden" number="number"  name="number" value="${sessionScope.user.number} "/>
							   <input type="hidden" number="resume_id" name="resume_id" value="${param.resume_id }"/>
							   <div class="form-group">
							      <div class="col-sm-10">
							         <input type="submit" class="btn btn-primary" value="保&emsp;存" />
							      </div>
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
	navStyle();
	getStuInfo2(document.getElementById("number").value);
	var resume_id = document.getElementById("resume_id");
	if(resume_id.value != null && resume_id.value != ""){
		loadResumeData(resume_id.value);
	}
}
function skipToPersonal(){
	var result = confirm("离开后页面信息将丢失，确定离开？");
	if(result){
		window.location.href="personal_info.jsp";
	}
	
}


 $(function () { $('#collapseOne').collapse('toggle')});
</script> 
</body>
</html>
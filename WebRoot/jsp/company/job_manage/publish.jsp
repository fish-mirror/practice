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
	<title>发布新职位-实习管理系统</title>
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
<%@ include file="/company/nav.jspf" %>
<div class="container"> 
	<div class="container-fluid"> 
		<div class="row"> 
			<%@ include file="/company/j_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
          		<form id="jobFrom" class="form-horizontal" method="post" action="company/job_manage/addJob.action">
		            <div class="form-group">
		              	<label for="name" class="col-sm-2 control-label">职位名称</label>
		              	<div class="col-sm-9">
		              		<input type="text" class="form-control" id="name" name="name">
		              	</div>
		            </div>
		           <div class="form-group">
		              	<label for="desc" class="col-sm-2 control-label">职位描述</label>
		              	<div class="col-sm-9">
				        	<textarea class="form-control" rows="3" id="desc" name="desc"></textarea>
				        </div>
		            </div>
		            <div class="form-group">
				        <label for="need" class="col-sm-2 control-label">职位要求</label>
				        <div class="col-sm-9">
				        	<textarea class="form-control" rows="3" id="need" name="need"></textarea>
				        </div>
			        </div>
		            <div class="form-group">
		                <label for="time" class="col-sm-2 control-label">时间要求</label>
		                <div class="col-sm-9">
				        	<textarea class="form-control" rows="3" id="time" name="time"></textarea>
				        </div>
		           	</div>
		           	<div class="form-group">
		                <label for="time" class="col-sm-2 control-label">招聘人数</label>
		                <div class="col-sm-9">
		                	<input type="text" class="form-control" id="need_num" name="need_num">
		                </div>
		           	</div>
			        <div class="form-group">
				        <label for="" class="col-sm-2 control-label">工作地点</label>
				        <div class="col-sm-9">
				        	<div class="form-group col-sm-3">
	            				<select id="selProvince" name="province" class="form-control" > 
							        <option value="">--省份--</option> 
							    </select> 
						    </div>
						    <div class="form-group col-sm-3">
							    <select id="selCity" name="city" class="form-control col-sm-4"> 
							        <option value="">--城市--</option> 
							    </select> 
							</div>
						    <div class="form-group col-sm-6">
							    <input type="text" class="form-control" id="place" name="place">
		                
						    </div>
						    
				        </div>
			        </div>
		            <div class="form-group">
			            <div class="col-sm-offset-2 col-sm-9">
			            	<button type="submit" class="btn btn-primary btn-block">发布</button>
			            </div>
		            </div>
        		</form>
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
<script src="json/CityJson.js" type="text/javascript"></script> 
<script src="json/ProJson.js" type="text/javascript"></script>  
<script type="text/javascript">
window.onload = initPage;

//初始化界面
function initPage(){
	navStyle();
	loadWeeklyData(null,1);
}
$(function () { 
 
     $.each(province, function (k, p) {  
         var option = "<option value='" +  p.ProName  + "' id='"+p.ProID+"'>" + p.ProName + "</option>"; 
         $("#selProvince").append(option); 
     }); 
       
     $("#selProvince").change(function () { 
         var selID = $("#selProvince option:selected")[0].index;
         $("#selCity option:gt(0)").remove(); 
           
         $.each(city, function (k, p) {  
             if (p.ProID == selID) { 
                 var option = "<option value='" + p.CityName + "'>" + p.CityName + "</option>"; 
                 $("#selCity").append(option); 
             } 
         }); 
           
     }); 
       
    
 }); 
 $(document).ready(function() {
    $('#jobFrom').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '职位名称不能为空'
                    }
                }
            },
            desc: {
                validators: {
                    notEmpty: {
                        message: '职位描述不能为空'
                    }
                }
            },
            need: {
                validators: {
                    notEmpty: {
                        message: '职位要求不能为空'
                    }
                }
            },
            time: {
                validators: {
                    notEmpty: {
                        message: '时间要求不能为空'
                    }
                }
            },
            need_num: {
                validators: {
                    notEmpty: {
                        message: '需要人数不能为空，不限则填“不限”'
                    }
                }
            },
            province: {
                validators: {
                    notEmpty: {
                        message: '省份不能为空'
                    }
                }
                
            },
            city: {
                validators: {
                    notEmpty: {
                        message: '城市不能为空'
                    }
                }
            },
            
            place: {
                validators: {
                    notEmpty: {
                        message: '工作地点不能为空'
                    }
                }
            },
        }
    });
});
</script> 
</body>
</html>
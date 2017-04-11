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
	    	<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main background">
	    		<div class="col-sm-12 form-group">
					<input type="button" class="btn btn-primary"  style="float:right"
					    data-toggle="modal" data-target="#myModal"
					    value="发表周记">
			    </div>
			    <div  number="weekly-records" >
					
					
               </div>
            </div> 
            <div class="table-bottom"> 
		    	<ul number="pageDiv" class="pagination">
				</ul>
	        </div> 
			
        </div>
	</div>
</div>        
<div class="modal fade" number="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" number="myModalLabel">发表周记</h4>
				</div>
				<div class="modal-body">
           			<form number="weekly-form" role="form" method="post" action="student/practice_manage/addPracticeData.action">
        				
            			<div class="form-group">
            				<label>内容：</label>
							<textarea number="content" name="content" class="form-control" rows="8"></textarea>
            			</div>
            			<div class="form-group" >
            				<label class="col-sm-2 control-label" >位置</label>
            				<div class="form-group">
	            				<select number="selProvince" name="province" class="form-control" >
							        <option value="">--请选择省份--</option> 
							    </select> 
						    </div>
						    <div class="form-group">
							    <select number="selCity" name="city" class="form-control col-sm-4">
							        <option value="">--请选择城市--</option> 
							    </select> 
						    </div>
            			</div>
            			<div class="col-sm-12" ><br></div>
                        <div class="modal-footer" >
            				<input type="submit" class="btn btn-primary" value="提交"/>
            				<input type="button" class="btn btn-default" value="取消" data-dismiss="modal"/>
            			</div>
        			</form>
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
         var option = "<option value='" +  p.ProName  + "' number='"+p.ProID+"'>" + p.ProName + "</option>";
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
    $('#weekly-form').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            content: {
                validators: {
                    notEmpty: {
                        message: '实习周记内容不能为空'
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
        }
    });
});
</script> 
</body>
</html>
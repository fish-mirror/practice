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
	<title>短学期项目管理-实习管理系统</title>
	<!-- Bootstrap --> 
    <link href="css/bootstrap.min.css" rel="stylesheet" /> 
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
<%@ include file="/college/nav.jspf" %>
<div class="container"> 
	<div class="container-fluid"> 
		<div class="row"> 
			<%@ include file="/college/c_m-nav.jspf" %>
       		<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main"> 
			    <input type="button" class="btn btn-primary" 
			    	onclick="location.href='/practice/college/coop_manage/add_short_term.jsp'"
			    	value="添加短学期项目">
		    	<input type="button" class="btn" value="导出选课表">
		    	<input type="button" class="btn"   data-toggle="modal"  data-target="#myModal1" value="导入选课表">
				<div class="table-responsive"> 
					<table id="shortTermList" class="table table-striped"> 
		         		<thead> 
					        <tr> 
					        	<th>学期</th> 
						        <th>短学期项目名称</th>
						        <th>已选人数</th>  
						        <th>状态</th> 
						        <th>更改状态为</th> 
						        <th>操作</th> 
						    </tr>
				        </thead> 
           				<tbody> 
           					<s:iterator value="stps">
					            <tr> 
						            <td><s:property value="term" /></td> 
						            <td><s:property value="name" /></td> 
						            <td><s:property value="selectedNum" /></td>
						            <s:if test="status == 0">
						            	<td id="<s:property value="id" />">关闭</td>
						            	
						            	<td>	
						            		<a href="javascript:updateStatus(<s:property value="id" />,1);">发布信息</a>
						            		<br>
						            		<a href="javascript:updateStatus(<s:property value="id" />,2);">可上传</a>
						            		<br>
						            		<a href="javascript:updateStatus(<s:property value="id" />,3);">可评分</a> 
						            		<br>
						            	</td>
						            </s:if>
						            <s:elseif test="status == 1"> 
										<td id="<s:property value="id" />">已发布</td>
										<td>
						            		<a href="javascript:updateStatus(<s:property value="id" />,0);">关闭</a>
										</td>
									</s:elseif>
									<s:elseif test="status == 2"> 
										<td id="<s:property value="id" />">可上传</td>
										<td>
						            		<a href="javascript:updateStatus(<s:property value="id" />,0);">关闭</a>
										</td>
									</s:elseif>
									<s:elseif test="status == 4"> 
										<td id="<s:property value="id" />">可评分</td>
										<td>
						            		<a href="javascript:updateStatus(<s:property value="id" />,0);">关闭</a>
										</td>
									</s:elseif>
						           	<td>
					            		<a target="blank" href="college/coop_manage/viewShortTermProject.action?id=<s:property value="id" />">查看</a>
					            		<br>
					            		<a href="college/coop_manage/add_short_term.jsp?pid=<s:property value="id" />">编辑</a>
					            	</td>
					            </tr>
				            </s:iterator>
				        </tbody>
					</table> 
					<div class="modal fade" id="myModal1" tabindex="-1" role="dialog" 
					   aria-labelledby="myModalLabel" aria-hidden="true">
					   <div class="modal-dialog">
					      <div class="modal-content">
					         <div class="modal-header">
					            <button type="button" class="close" 
					               data-dismiss="modal" aria-hidden="true">
					                  &times;
					            </button>
					            <h4 class="modal-title" id="myModalLabel">导入选课Excel</h4>
					         </div>
					         <div class="modal-body">
				            	<form enctype="multipart/form-data" method="post">
						         	<label>
						         		请上传Excel文件，支持.xls,.xlsx
						         	</label>
								    <input id="xls" name="xls" type="file" class="file">
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
</div>  
<!-- 如果要使用Bootstrap的js插件，必须先调入jQuery --> 
<script src="js/jquery.min.js"></script> 
<!-- 包括所有bootstrap的js插件或者可以根据需要使用的js插件调用　--> 
<script src="js/bootstrap.min.js"></script> 
<script src="js/data.js"></script> 
<script src="js/fileinput.min.js"></script> 
<script src="js/fileinput_locale_zh.js"></script> 
<script type="text/javascript">
window.onload = initPage;

//初始化界面
function initPage(){
	navStyle();
}
$("#xls").fileinput({
    language: 'zh', //设置语言
    'allowedFileExtensions' : ['xls','xlsx'],
    maxFileSize: 1048576,
    uploadAsync:true,
    uploadUrl:"college/importShortTermSelected.action",
});
$("#xls").on("fileuploaded", function (event, data) {
	var data = data.response;
	alert(data);
	window.location.reload();
	
});

function updateStatus(id,status){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "college/updateStatus.action?id=" + id + "&status="+status;
		request.open("GET", url + "&" + Math.random(), true);
		request.onreadystatechange=function(){
			if(request.readyState == 4){
				if(request.status == 200){
					var data= eval("(" + request.responseText + ")");				
					var statusDiv = document.getElementById(id);
					//填充数据
					var newStatus = data.status;
					switch(newStatus){
						case 0:
							statusDiv.innerHTML = "关闭";
							var node = statusDiv.nextSibling;
							while(node.nodeType != 1){
								node = node.nextSibling;
							}
							node.innerHTML = "<a href='javascript:updateStatus("+id+",1);'>发布 </a>"
							            		+ "<br><a href='javascript:updateStatus("+id+",2);'>可上传 </a>"
							            		+ "<br><a href='javascript:updateStatus("+id+",3);'>可评分 </a> ";
							break;
						case 1:
							statusDiv.innerHTML = "已发布";
							var node = statusDiv.nextSibling;
							while(node.nodeType != 1){
								node = node.nextSibling;
							}
							node.innerHTML = "<a href='javascript:updateStatus("+id+",0);'>关闭</a>";
							break;
						
						case 2:
							statusDiv.innerHTML = "可上传";
							var node = statusDiv.nextSibling;
							while(node.nodeType != 1){
								node = node.nextSibling;
							}
							node.innerHTML = "<a href='javascript:updateStatus("+id+",0);'>关闭</a>";
							break;
						case 3:
							statusDiv.innerHTML = "可评分";
							var node = statusDiv.nextSibling;
							while(node.nodeType != 1){
								node = node.nextSibling;
							}
							node.innerHTML = "<a href='javascript:updateStatus("+id+",0);'>关闭</a>";
							break;
					}
					
				}
			}
		};
	}
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}
</script>  
</body>
</html>
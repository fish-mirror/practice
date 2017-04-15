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
	<title>短学期项目选择-实习管理系统</title>
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
					<s:if test="stps!=null">
						<table number="shortTermList" class="table table-striped">
			         		<thead> 
						        <tr>  
							        <th>短学期项目名称</th> 
							        <th>合作公司</th> 
							        <th>年级要求</th>
							        <th>专业要求</th>
							        <th>非专业名额</th>  
							        <th>已选/总数</th> 
							        <th>操作</th> 
							    </tr>
					        </thead> 
	           				<tbody> 
	           					<s:iterator value="stps">
		           					<tr number="<s:property value="number" />">
			           					<td><a href="student/short_term_manage/details.jsp?number=<s:property value="number" />"><s:property value="name" /></a></td>
			           					<td><s:property value="company.name" /></td>
			           					<td><s:property value="gradeNeed" /></td>
			           					<td><s:property value="majorNeed" /></td>
			           					<td>
			           						<span><s:property value="unmajorSelected" /></span>/<span><s:property value="unmajorNum" /></span>
			           						
			           					</td>
			           					<td>
				           					<span><s:property value="selectedNum" /></span>/<span><s:property value="topNum" /></span>
			           					</td>
			           					<td><a href="javascript:validation('<s:property value="number" />');">选择</a></td>
		           					</tr>
	           					</s:iterator>
	           					
					        </tbody>
						</table> 
					</s:if>
					<s:else>
						<div>没有安排可选择的短学期项目！</div>
					</s:else>
					
				</div> 
				<div  style="height:100px"></div>
				<div class="table-responsive">
					<table number="stp_selected" class="table table-striped">
						<s:if test="selected!=null">
							<thead>
								<tr>
									<th>学期</th>
									<th>已选短学期项目</th>
									<th>合作公司</th>
									<th>地点</th>
									<th>操作</th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td><s:property value="selected.shortTermProject.term" /></td>
									<td><s:property value="selected.shortTermProject.name" /></td>
									<td><s:property value="selected.shortTermProject.company.name" /></td>
									<td><s:property value="selected.shortTermProject.place" /></td>
									<td><a href="javascript:cancelShortTermProject(<s:property value="selected.number" />)">取消</a></td>
								</tr>
							</tbody>
						</s:if>
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
<script type="text/javascript">
window.onload = initPage;

//初始化界面
function initPage(){
	navStyle();
}
function validation(pid){
	var projectRow = document.getElementById(pid);
	 if(projectRow)
      {
        var gradeNeed = projectRow.cells[2].innerHTML;
        var majorNeed = projectRow.cells[3].innerHTML;
        var unmajorSelected = projectRow.cells[4].childNodes[1].innerHTML;
      	var unmajorNum = projectRow.cells[4].childNodes[3].innerHTML;
      	var selectedNum = projectRow.cells[5].childNodes[1].innerHTML;
      	var topNum = projectRow.cells[5].childNodes[3].innerHTML;

		if(eval(selectedNum) <eval(topNum)){
			if(gradeNeed == "all" || "${sessionScope.ID}".substr(0, 2) == gradeNeed){
				if(majorNeed == "all" || majorNeed == "${sessionScope.major}" ){
					selectShortTermProject(pid);
				}else if(unmajorSelected < unmajorNum){
					selectShortTermProject(pid);
				}else{
					alert("专业限制，名额已满！");
				}
			}else{
				alert("年级不符！");
			}
		}else{
			alert("该项目人数已满！");
		}
      
      }
}
</script>  
</body>
</html>
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
<title>高校实习管理系统-首页</title>
<base href="<%=basePath%>">
	
 	<link href="css/style.css" rel="stylesheet" />
 	
 	<link href="css/bootstrap.min.css" rel="stylesheet" />
 	<link href="css/index.css" rel="stylesheet" />
 	
</head>

<body>
<nav class="navbar navbar-default navbar-fixed-top"> 
	<div class="container"> 
    	<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">    <span class="sr-only">Toggle navigation</span>    <span class="icon-bar"></span>    <span class="icon-bar"></span>    <span class="icon-bar"></span>    </button> 
        	<a href="javascript:;"><span class="logo">高校实习管理系统</span><img src="image/logo.png" alt="实习管理系统" /></a>
    	</div> 
    	<div id="navbar" class="navbar-collapse collapse"> 
        	<ul class="nav navbar-nav navbar-left"> 
         		<li id="resume" class="active"><a href="index.jsp">首页</a></li> 
         		<li><a href="">合作意向</a></li> 
         		<li><a href="">合作企业介绍</a></li> 
         		<li><a href="">实习职位</a></li> 
        	</ul> 
        	<div id="info" style="float:right;padding:15px 50px 15px;margin-top:45px;">
         		<a href="login.jsp" target="_blank">登录</a>&emsp;<a href="register.jsp" target="_blank">企业用户注册</a>
         	</div>
       </div> 
	</div> 
</nav> 
<!-- 轮播 -->
<div id="h-banner" class="slider">
    <ul id="show_pic">
		<li style="opacity: 1; z-index: 10;">
			<a href1="">
				<img src="image/01.jpg">
			</a>
			<span style="width: 378px; opacity: 0.5;" class="titlebg"></span>
			<a href1="" class="title">理工科招聘会</a>
		</li>
		<li style="opacity: 0; z-index: 9;">
			<a href1="">
				<img src="image/02.jpg">
			</a>
			<span style="width: 196px; opacity: 0.5;" class="titlebg"></span>
			<a href1="" class="title">校企“零距离”，倾听企业声音</a>
		</li>
		<li style="opacity: 0; z-index: 9;">
			<a href1="">
				<img src="image/03.jpg">
			</a>
			<span style="width: 490px; opacity: 0.5;" class="titlebg"></span>
			<a href1="" class="title">访问游戏公司</a>
		</li>
		<li style="opacity: 0; z-index: 9;">
			<a href1="">
				<img src="image/04.jpg">
			</a>
			<span style="width: 420px; opacity: 0.5;" class="titlebg"></span>
			<a href1="" class="title">多媒体作品讨论会</a>
		</li>
		<li style="opacity: 0; z-index: 9;">
			<a href1="">
				<img src="image/05.jpg">
			</a>
			<span style="width: 354px; opacity: 0.5;" class="titlebg"></span>
			<a href1="" class="title">移动互联网技术与传播高峰论坛在我校召开</a>
		</li>
		<li style="opacity: 0; z-index: 9;">
			<a href1="">
				<img src="image/06.jpg">
			</a>
			<span style="width: 224px; opacity: 0.5;" class="titlebg"></span>
			<a href1="" class="title">第一届“海象杯”手机APP设计大赛成功举办</a>
		</li>
	
	</ul>
	<div style="z-index: 11;" class="img_pagebox">
		<div class="img_page">
			<div id="icon_num" class="pageBox">
				<a class="active" href="javascript:void(0);">1</a>
				<a href="javascript:void(0);">2</a>
				<a href="javascript:void(0);">3</a>
				<a href="javascript:void(0);">4</a>
				<a href="javascript:void(0);">5</a>
				<a href="javascript:void(0);">6</a>
			</div>
		</div>
	</div>
</div>

<!-- 合作企业介绍 -->
<div class="h-body clear">
	<div class="h-left">
    	<div class="h-notice">
        	<div class="hd">
	        	<a><h4>合作企业介绍</h4></a>
        	</div>
            <div class="bd">
			<img src="image/company/danei.jpg" style="height:94px;width:190px;">
            	                	
                        <ul class="h-notice-list">
                            <li><a href="" target="_blank">达内时代科技集团有限公司</a></li>
                        </ul>   
                        <ul class="h-notice-list">
                            <li><a href="" target="_blank">汇文教育</a></li>
                        </ul>   
				                	
                        <ul class="h-notice-list">
                            <li><a href="" target="_blank">爱迪斯通科技有限公司</a></li>
                        </ul>   
                        <ul class="h-notice-list">
                            <li><a href="" target="_blank">青麦学院</a></li>
                        </ul>   
                        <ul class="h-notice-list">
                            <li><a href="" target="_blank">杭州益赛信息系统工程有限公司</a></li>
                        </ul> 
                        <ul class="h-notice-list">
                            <li><a href="" target="_blank">腾科IT教育集团</a></li>
                        </ul>   
								<div class="more"><a href="t" target="_blank" title="点击查看更多合作企业">MORE INFO &gt;&gt;</a></div>
			</div>
        </div>
    </div>
    <div class="h-center">
    	<div class="h-center-c">
        	<div class="h-news">
        		<div class="hd clear">              	
                	<a><h4>合作意向列表</h4></a>
				</div>
            	<div class="bd">
					<div class="h-news-box">
						<ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">【IT行业】7月初短学期项目寻求合作</a>
                             </li>
                         </ul>	
						<ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">移动互联网技术高峰论坛</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">IT项目学院培训项目</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">电子商务数据分析培训合作</a>
                             </li>
                         </ul>		
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">就业指导课企业讲座合作</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">电视节目实习招聘企业合作</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">新闻报道出版小学期项目</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-25</span>
	                             <a href="" target="_blank">电子科技跨校合作大赛</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-22</span>
	                             <a href="" target="_blank">学院技术讲座长期合作</a>
                             </li>
                         </ul>	
                         <ul class="h-news-list">
                             <li>
	                             <span>2016-03-18</span>
	                             <a href="" target="_blank">招聘宣讲会安排</a>
                             </li>
                         </ul>	
                         
                    </div>
					
					<div class="more"><a href="" target="_blank" title="点击查看更多合作意向" style="color:#FC5D00;">&gt;&gt; 更多 合作意向 &gt;&gt;</a></div>
				</div>
       	 	</div>
        </div>
    </div>
    <div class="h-right">
    	<div class="h-video">
        	<div class="hd">
        	<a><h4>职位列表</h4></a>
        	</div>
            <div class="bd">
				<ul class="h-video-list">
                    <li><span>1</span><a href="" target="_blank" class="tittle">软件测试-新加坡科技园</a></li>

                    <li><span>2</span><a href="" target="_blank" class="title">前端实习生-金沙湖</a></li>

                    <li><span>3</span><a href="" target="_blank" class="title">移动开发实习生-海象科技</a></li>

                    <li><span>4</span><a href="" target="_blank" class="title">UI设计师</a></li>

                    <li><span>5</span><a href="" target="_blank" class="title">微信运营管理</a></li>
                    
                    <li><span>6</span><a href="" target="_blank" class="title">活动策划</a></li>
                    
                    <li><span>7</span><a href="" target="_blank" class="title">电子商务用户数据分析</a></li>
                    
                    <li><span>8</span><a href="" target="_blank" class="title">市场调研</a></li>
                    
                    <li><span>9</span><a href="" target="_blank" class="title">JAVA开发实习生</a></li>
                    
                    <li><span>10</span><a href="" target="_blank" class="title">综合布线实习</a></li>
       		
       				<li><span>11</span><a href="" target="_blank" class="title">视频剪辑员</a></li>
       				
       				<li><span>12</span><a href="" target="_blank" class="title">3D建模设计师</a></li>
   				</ul>
				<div class="more"><a href="" target="_blank" title="点击查看更多职位">MORE JOB &gt;&gt;</a></div>
			</div>
        </div>
       
    </div>
</div>

<script charset="utf-8" src="js/jquery.min.js"></script>
<script charset="utf-8" src="js/easySlider.js"></script>
<script>
var info = document.getElementById("info");
var login = "${sessionScope.name}";
if(info && login){
	info.innerHTML = "${sessionScope.name}&emsp;<a href='logout.action'>退出</a>";
}

$(function() {
		var sWidth = $(".h-activity-slider").width(); 
		var len = $(".h-activity-slider ul li").length;
		var index = 0;
		var picTimer;
	
		var btn = "<div class='btn'>";
		for(var i=0; i < len; i++) {
			btn += "<span></span>";
		}
		btn += "</div>";
		$(".h-activity-slider").append(btn);
		$(".h-activity-slider .btn span").css("opacity",0.4).mouseenter(function() {
			index = $(".h-activity-slider .btn span").index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");

		$(".h-activity-slider ul").css("width",sWidth * (len));
		$(".h-activity-slider").hover(function() {
			clearInterval(picTimer);
		},function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if(index == len) {index = 0;}
			},4000); 
		}).trigger("mouseleave");
		
		function showPics(index) { 
			var nowLeft = -index*sWidth; 
			$(".h-activity-slider ul").stop(true,false).animate({"left":nowLeft},300); 
			$(".h-activity-slider .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); 
		}
	});
	$(function() {
		var sWidth = $(".h-wechat-slider").width(); 
		var len = $(".h-wechat-slider ul li").length;
		var index = 0;
		var picTimer;
	
		var btn = "<div class='btn'>";
		for(var i=0; i < len; i++) {
			btn += "<span></span>";
		}
		btn += "</div>";
		$(".h-wechat-slider").append(btn);
		$(".h-wechat-slider .btn span").css("opacity",0.4).mouseenter(function() {
			index = $(".h-wechat-slider .btn span").index(this);
			showPics(index);
		}).eq(0).trigger("mouseenter");

		$(".h-wechat-slider ul").css("width",sWidth * (len));
		$(".h-wechat-slider").hover(function() {
			clearInterval(picTimer);
		},function() {
			picTimer = setInterval(function() {
				showPics(index);
				index++;
				if(index == len) {index = 0;}
			},4000); 
		}).trigger("mouseleave");
		
		function showPics(index) { 
			var nowLeft = -index*sWidth; 
			$(".h-wechat-slider ul").stop(true,false).animate({"left":nowLeft},300); 
			$(".h-wechat-slider .btn span").stop(true,false).animate({"opacity":"0.4"},300).eq(index).stop(true,false).animate({"opacity":"1"},300); 
		}
	});	
</script>

</body>
</html>
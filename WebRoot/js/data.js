
//创建请求对象
function createRequest(){
	try{
		request = new XMLHttpRequest();
	}catch (tryMS){
		try{
			requesr = new ActiveXObject("Msxml2.XMLHTTP");		
		}catch(otherMS){
			try{
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(failed){
				request = null;
			}
		}
	}
	return request;
}
//请求学生状态信息
function getStuStatus(graduate,classname,num,pageSize,page){
	request1 = createRequest();
	if(request1 == null){
		alert("Unable to create request");
	}else{
		var url = "college/statusInfo.action?page="+page;
		if(graduate!=null){
			url=url+"&graduate="+graduate;	
		}
		if(classname!=null){
			url=url+"&"+ encodeURI("classname="+classname);
		}
		if(num!=null){
			url=url+"&num="+num;
		}
		if(pageSize!=null){
			url=url+"&pageSize="+pageSize;
		}
		updateStyle(graduate,classname,num);
		request1.open("GET", url + "&" + Math.random(), true);
		request1.onreadystatechange=function(){
			if(request1.readyState == 4){
				if(request1.status == 200){
					var stuStatusDiv = document.getElementById("stu_status_tbl");
					stuStatusDiv.innerHTML= "";
					var pageDiv = document.getElementById("pageDiv");
					pageDiv.innerHTML = "";
					var page = eval("(" + request1.responseText + ")");
					var data = page.list;
					var i;
					var len = data.length;
					//如果字符串不为null则需要加上转义\"
					var classname_str;
					if(classname==null){
						classname_str = null;
					}else{
						classname_str = "\""+classname+"\"";
					}

					//填充数据
					for(i=0; i<len; i++){
						var item = data[i];
						var line = document.createElement("tr");
						var box1 = document.createElement("td");
						var box2 = document.createElement("td");
						var box3 = document.createElement("td");
						var box4 = document.createElement("td");
						var box5 = document.createElement("td");
						box1.innerHTML = item.id;
						line.appendChild(box1);
						box2.innerHTML = item.name;
						line.appendChild(box2);
						box3.innerHTML = item.classname;
						line.appendChild(box3);
						if(item.graduate==1){
							box4.innerHTML = "是";
						}else{
							box4.innerHTML = "否";
						}
						line.appendChild(box4);
						switch(item.status){
						case 0:
							box5.innerHTML = "未实习";break;
						case 1:
							box5.innerHTML = "正在实习";break;
						case 2:
							box5.innerHTML = "实习完待评分";break;
						case 3:
							box5.innerHTML = "完成实习";break;
						
						}
						line.appendChild(box5);
						stuStatusDiv.appendChild(line);
					}
					//更改页码
					var li_omit = document.createElement("li");
					li_omit.innerHTML = "<a>...</a>";
					var li_first =  document.createElement("li");
					li_first.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+"1)'>首页</a>";
					var li_tail =  document.createElement("li");
					li_tail.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+page.totalPage+")'>尾页</a>";
					pageDiv.appendChild(li_first);
					//向前翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+(page.currentPage-1)+")'>&laquo;</a>";
					}
					//中间页码
					if(page!=null){
						//页数>6的输出
						if(page.totalPage>6){
							if(page.currentPage<6){
								for(i = 1; i < 7; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}else if(page.currentPage >(page.totalPage-6)){
								pageDiv.appendChild(li_omit);
								for(i = page.totalPage-6; i <= page.totalPage; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
							}else{
								pageDiv.appendChild(li_omit);
								for(i = page.currentPage-2; i < page.currentPage+4; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}
						}else{
							for(i = 1; i <= page.totalPage; i++){
								var li_page = document.createElement("li");
								if(i == page.currentPage){
									li_page.className="active";
								}
								li_page.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+i+")'>"+ i +"</a>";
								pageDiv.appendChild(li_page);
							}
						}
					}else{
						var li_page = document.createElement("li");
						li_page.className="active";
						li_page.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+"1)'>1</a>";
						pageDiv.appendChild(li_page);
					}
					//向后翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getStuStatus("+graduate+","+classname_str+","+num+","+pageSize+","+(page.currentPage+1)+")'>&raquo;</a>";
					}
					pageDiv.appendChild(li_tail);
				}
			}
		};
		request1.setRequestHeader("Cache-Control","no-cache")
		request1.send(null);
	}
}


function updateStyle(graduate,classname,num){
	var all_stu = document.getElementById("all_stu");
	var graduated = document.getElementById("graduated");
	var non_graduated = document.getElementById("non_graduated");
	var class_item = document.getElementById("class_item");
	all_stu.className = "";
	graduated.className = "";
	non_graduated.className = "";
	class_item.innerHTML = "";
	if(graduate==1){
		graduated.className = "active";
	}else if(graduate == 0){
		non_graduated.className = "active";
	}else if(classname!=null){
		class_item.innerHTML = classname;
	}else if(num!=null){
		class_item.innerHTML = num;
	}else{
		all_stu.className = "active";
	}
}
function navStyle(){
	var url = window.location.href;
	var strs = url.split("/");
	var firstID = strs[5];
	var subID = strs[6].substring(0, strs[6].indexOf("."));
	document.getElementById(firstID).className = "active";
	document.getElementById(subID).className = "active";
}


//请求班级列表
function getClassList(){
	request2 = createRequest();
	if(request2 == null){
		alert("Unable to create request");
	}else{
		var url = "college/getClassList.action";
	};
	request2.open("GET", url + "?" + Math.random(), true);
	request2.onreadystatechange=function(){
		if(request2.readyState == 4){
			if(request2.status == 200){
				var class_menu = document.getElementById("class-menu");
				var data = eval("(" + request2.responseText + ")");
				var i,item;
				var len = data.length;
				//填充数据
				for(i=0; i<len; i++){
					var item = data[i];
					var line = document.createElement("li");
					line.innerHTML="<a href='javascript:;' onclick='getStuStatus(null,\""+item+"\",null,null,1)'>"+item+"</a>"
					
					class_menu.appendChild(line);
				}
			}
		}
	};
	request2.setRequestHeader("Cache-Control","no-cache")
	request2.send(null);

}

//跳转网页
function gotoEdit(){
	window.location.href="edit.jsp"; 
}
//填充学生信息
function getStuInfo(id){
	var request3 = createRequest();
	if(request3 == null){
		alert("Unable to create request");
	}else{
		var url = "student/getPersonalInfo.action?id=" + id;
	};
	request3.open("POST", url + "&" + Math.random(), true);
	request3.onreadystatechange=function(){
		if(request3.readyState == 4){
			if(request3.status == 200){
				var class_menu = document.getElementById("class-menu");
				var data = eval("(" + request3.responseText + ")");
				var i,item;
				//填充数据
				document.getElementById("name").value = data.name;
				document.getElementById("sex").value = data.sex;
				document.getElementById("institute").value = data.institute;
				document.getElementById("major").value = data.major;
				document.getElementById("nation").value = data.nation;
				var date = data.birth.toString();
				if(data!=null){
					document.getElementById("birth").value = date.substring(0,10);
				}
				
				document.getElementById("height").value = data.height;
				document.getElementById("weight").value = data.weight;
				document.getElementById("tel").value = data.tel;
				document.getElementById("email").value = data.email;
				document.getElementById("politics").value = data.politics;
				document.getElementById("address").value = data.address;
				var img_head = document.getElementById("head");
				img_head.setAttribute("src", data.imgUrl);
				
			}
		}
	};
	request3.setRequestHeader("Cache-Control","no-cache")
	request3.send(null);
}
function getStuInfo2(id){
	var request3 = createRequest();
	if(request3 == null){
		alert("Unable to create request");
	}else{
		var url = "student/getPersonalInfo.action?id=" + id;
	};
	request3.open("POST", url + "&" + Math.random(), true);
	request3.onreadystatechange=function(){
		if(request3.readyState == 4){
			if(request3.status == 200){
				var class_menu = document.getElementById("class-menu");
				var data = eval("(" + request3.responseText + ")");
				var i,item;
				//填充数据
				document.getElementById("name").innerHTML = "姓&emsp;&emsp;名：" + data.name;
				document.getElementById("sex").innerHTML = "性&emsp;&emsp;别：" + data.sex;
				document.getElementById("institute").innerHTML = "学&emsp;&emsp;院：" + data.institute;
				document.getElementById("major").innerHTML = "专&emsp;&emsp;业：" + data.major;
				document.getElementById("nation").innerHTML = "民&emsp;&emsp;族：" + data.nation;
				var grade = eval(data.id.toString().substr(0, 2));
				document.getElementById("graduate_time").innerHTML = "毕业时间：20" + (grade+4) + "年6月";
				var date = data.birth.toString();
				if(data!=null){
					document.getElementById("birth").innerHTML = "出生年月：" + date.substring(0,10);
				}
				
				document.getElementById("height").innerHTML = "身&emsp;&emsp;高：" + data.height + "cm";
				document.getElementById("weight").innerHTML = "体&emsp;&emsp;重：" + data.weight + "kg";
				document.getElementById("tel").innerHTML = "联系电话：" + data.tel;
				document.getElementById("email").innerHTML = "电子邮箱：" + data.email;
				document.getElementById("politics").innerHTML = "政治面貌：" + data.politics;
				document.getElementById("address").innerHTML = "联系地址：" + data.address;
				var img_head = document.getElementById("head");
				img_head.setAttribute("src", data.imgUrl);
				
			}
		}
	};
	request3.setRequestHeader("Cache-Control","no-cache")
	request3.send(null);
}


//填充简历编辑的信息
function loadResumeData(resume_id){
	var request1 = createRequest();
	if(request1 == null){
		alert("Unable to create request");
	}else{
		var url = "student/getResumeData.action?id="+ resume_id;
	};
	request1.open("GET", url + "&" + Math.random(), true);
	request1.onreadystatechange=function(){
		if(request1.readyState == 4){
			if(request1.status == 200){
				var stuData = eval("(" + request1.responseText + ")");
				document.getElementById("major_class").value = (stuData.majorClass ==null) ? "" : stuData.majorClass;
				document.getElementById("school_exp").value = (stuData.schoolExp ==null) ? "" : stuData.schoolExp;
				document.getElementById("practice_exp").value = (stuData.practiceExp ==null) ? "" : stuData.practiceExp;
				document.getElementById("certificate").value = (stuData.certificate ==null) ? "" : stuData.certificate;
				document.getElementById("self_comment").value = (stuData.selfComment ==null) ? "" : stuData.selfComment;
				document.getElementById("tittle").value = stuData.tittle;
				
			}
		}
	};
	request1.setRequestHeader("Cache-Control","no-cache")
	request1.send(null);
	if(id.value!=null){
	}
}

function loadResumeList(stu_id){
	var request1 = createRequest();
	if(request1 == null){
		alert("Unable to create request");
	}else{
		var url = "student/getResumeList.action?stuId="+ stu_id;
	};
	request1.open("GET", url + "&" + Math.random(), true);
	request1.onreadystatechange=function(){
		if(request1.readyState == 4){
			if(request1.status == 200){
				var resumes = eval("(" + request1.responseText + ")");
				//清除原有内容
				var resumesDiv = document.getElementById("resumeList");
				var temp = document.getElementById("body");
				if(temp!=null){
					temp.parentNode.removeChild(temp);
				}
				
				//填充数据
				var head = document.createElement("thead");
				var body = document.createElement("tbody");
				head.innerHTML = "<tr><th>标题</th><th>操作</th></tr> ";
				resumesDiv.appendChild(head);
				var len = resumes.length;
				var i;
				for(i = 0; i<len; i++){
					var line = document.createElement("tr");
					line.innerHTML = "<td width='70%'>" + resumes[i].tittle + "</td>" 
									+ "<td>" + "<span><a target='_blank' href='student/resume/view.jsp?resume_id="+resumes[i].id+"'>查看</a>&emsp;</span>"
									+ "<span><a href='student/resume/edit.jsp?resume_id="+resumes[i].id+"'>编辑</a>&emsp;</span>"
									 + "<span><a href='javascript:;' onclick='deleteResume("+resumes[i].id+",\""+resumes[i].stuId+"\")'>删除</a></span>"+ "</td>";
					body.appendChild(line);
				
				}
				resumesDiv.appendChild(body);
			}
		}
	};
	request1.setRequestHeader("Cache-Control","no-cache")
	request1.send(null);
}

//填充简历编辑的信息
function loadResumeData2(resume_id){
	var request1 = createRequest();
	if(request1 == null){
		alert("Unable to create request");
	}else{
		var url = "student/getResumeData.action?id="+ resume_id;
	};
	request1.open("GET", url + "&" + Math.random(), true);
	request1.onreadystatechange=function(){
		if(request1.readyState == 4){
			if(request1.status == 200){
				var stuData = eval("(" + request1.responseText + ")");
				if(document.getElementById("id").value == null || document.getElementById("id").value == ""){
					document.getElementById("id").value = stuData.stuId;
				}
				
				var majorClass = document.getElementById("major_class");
				if(stuData.majorClass!=null && stuData.majorClass!=""){
					majorClass.getElementsByTagName("p")[0].innerHTML = stuData.majorClass;
				}else{
					majorClass.parentNode.removeChild(majorClass);
				}
				var schoolExp = document.getElementById("school_exp");
				if(stuData.schoolExp!=null && stuData.schoolExp !=""){
					schoolExp.getElementsByTagName("p")[0].innerHTML = stuData.schoolExp;
				}else{
					schoolExp.parentNode.removeChild(schoolExp);
				}
				var practiceExp = document.getElementById("practice_exp");
				if(stuData.practiceExp!=null && stuData.practiceExp !=""){
					practiceExp.getElementsByTagName("p")[0].innerHTML = stuData.practiceExp;
				}else{
					practiceExp.parentNode.removeChild(practiceExp);
				}
				var certificate = document.getElementById("certificate");
				if(stuData.certificat!=null && stuData.certificate !=""){
					certificate.getElementsByTagName("p")[0].innerHTML = stuData.certificate;
				}else{
					certificate.parentNode.removeChild(certificate);
				}
				var selfComment = document.getElementById("self_comment");
				if(stuData.selfComment!=null && stuData.selfComment !=""){
					selfComment.getElementsByTagName("p")[0].innerHTML = stuData.selfComment;
				}else{
					selfComment.parentNode.removeChild(selfComment);
				}
			}
		}
	};
	request1.setRequestHeader("Cache-Control","no-cache")
	request1.send(null);
	
}



function deleteResume(id,stu_id){
	var flag = confirm("是否确认删除该简历？");
	if(flag){
		var request1 = createRequest();
		if(request1 == null){
			alert("Unable to create request");
		}else{
			var url = "student/deleteResume.action?resume_id="+id;
		};
		request1.open("GET", url + "&" + Math.random(), true);
		request1.onreadystatechange=function(){
			if(request1.readyState == 4){
				if(request1.status == 200){
					var result = eval("(" + request1.responseText + ")");
					if(result.result = "success"){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				}
			}
		};
		request1.setRequestHeader("Cache-Control","no-cache")
		request1.send(null);
	}
	
}

//加载合作意向列表信息
function loadIntentionList(institute){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getIntentionList.action?"+ encodeURI("institute="+institute);
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var intentions = eval("(" + request.responseText + ")");
				//清除原有内容
				var intentionsDiv = document.getElementById("intentionList");
				var temp = document.getElementById("body");
				if(temp!=null){
					temp.parentNode.removeChild(temp);
				}
				
				//填充数据
				var head = document.createElement("thead");
				var body = document.createElement("tbody");
				head.innerHTML = "<th width='60%'>名称</th> <th width='20%'>日期</th><th width='20%'>操作</th>";
				intentionsDiv.appendChild(head);
				var len = intentions.length;
				var i;
				for(i = 0; i<len; i++){
					var line = document.createElement("tr");
					line.innerHTML = "<td>" + intentions[i].tittle + "</td>" 
									+ "<td>" + intentions[i].date.toString().substring(0,10) + "</td>" 
									+ "<td>" + "<span><a target='_blank' href='college/coop_manage/view.jsp?intention_id="+intentions[i].id+"'>查看</a>&emsp;</span>"
									+ "<span><a href='college/coop_manage/publish.jsp?intention_id="+intentions[i].id+"'>编辑</a>&emsp;</span>"
									 + "<span><a href='javascript:;' onclick='deleteIntention("+intentions[i].id+",\""+intentions[i].institute+"\")'>删除</a></span>"+ "</td>";
					body.appendChild(line);
				
				}
				intentionsDiv.appendChild(body);
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

function loadIntrntionList2(institute){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getIntentionList.action?"+ encodeURI("institute="+institute);
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var intentions = eval("(" + request.responseText + ")");
				
				
				//填充数据
				
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

//填充合作意向编辑的信息
function loadIntentionData(intention_id){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getIntentionData.action?id="+ intention_id;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var intention = eval("(" + request.responseText + ")");
				
				document.getElementById("tittle").value = intention.tittle;
				document.getElementById("content").value = intention.content;
				
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
	
}

function loadIntentionData2(intention_id){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getIntentionData.action?id="+ intention_id;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var intention = eval("(" + request.responseText + ")");
				
				document.getElementById("tittle").innerHTML = intention.tittle;
				document.getElementById("content").innerHTML = intention.content;
				
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
	
}

function deleteIntention(id,institute){
	var flag = confirm("是否确认删除该合作意向？");
	if(flag){
		var request = createRequest();
		if(request == null){
			alert("Unable to create request");
		}else{
			var url = "college/deleteIntention.action?intention_id="+id;
		};
		request.open("GET", url + "&" + Math.random(), true);
		request.onreadystatechange=function(){
			if(request.readyState == 4){
				if(request.status == 200){
					var result = eval("(" + request.responseText + ")");
					if(result.result = "success"){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				}
			}
		};
		request.setRequestHeader("Cache-Control","no-cache")
		request.send(null);
	}
	
}

//加载企业列表信息
function loadCoopList(flag){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var coop = document.getElementById("coop");
		var all = document.getElementById("all");
		if(flag!=null){
			var url = "common/getCompanyList.action?1=1";
			coop.className = "";
			all.className = "active";
		}else{
			var url = "common/getCompanyList.action?flag="+flag;
			coop.className = "active";
			all.className = "";
			
		}
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var companies = eval("(" + request.responseText + ")");
				//清除原有内容
				var companyDiv = document.getElementById("companyList");
				companyDiv.innerHTML = "";
				var temp = document.getElementById("body");
				if(temp!=null){
					temp.parentNode.removeChild(temp);
				}
				
				//填充数据
				var head = document.createElement("thead");
				var body = document.createElement("tbody");
				head.innerHTML = "<th width='60%'>企业名称</th> <th width='20%'>类型</th><th width='20%'>操作</th>";
				companyDiv.appendChild(head);
				var len = companies.length;
				var i;
				for(i = 0; i<len; i++){
					var line = document.createElement("tr");
					if(flag == null){
						line.innerHTML = "<td>" + companies[i].name + "</td>" 
						+ "<td>" + companies[i].type + "</td>" 
						+ "<td>" + "<span><a target='_blank' href='college/coop_manage/view_com.jsp?company_id="+companies[i].id+"'>查看</a>&emsp;</span>"
						 + "<span><a href='javascript:;' onclick='deleteCoop(\""+companies[i].id+"\")'>解除</a></span>"+ "</td>";
					}else{
						line.innerHTML = "<td>" + companies[i].name + "</td>" 
						+ "<td>" + companies[i].type + "</td>" 
						+ "<td>" + "<span><a target='_blank' href='college/coop_manage/view_com.jsp?company_id="+companies[i].id+"'>查看</a>&emsp;</span>"
						 + "<span><a href='javascript:;' onclick='addCoop(\""+companies[i].id+"\")'>添加</a></span>"+ "</td>";
					}
					
					body.appendChild(line);
				
				}
				companyDiv.appendChild(body);
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

//首页加载企业列表信息
function loadCoopList2(){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var coop = document.getElementById("coop");
		var all = document.getElementById("all");
		if(institute!=null){
			var url = "common/getCompanyList.action?"+ encodeURI("institute="+institute);
			coop.className = "active";
			all.className = "";
		}else{
			var url = "common/getCompanyList.action?1=1";
			coop.className = "";
			all.className = "active";
		}
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var companies = eval("(" + request.responseText + ")");
				//清除原有内容
				var companyDiv = document.getElementById("companyList");
				companyDiv.innerHTML = "";
				var temp = document.getElementById("body");
				if(temp!=null){
					temp.parentNode.removeChild(temp);
				}
				
				//填充数据
				var head = document.createElement("thead");
				var body = document.createElement("tbody");
				head.innerHTML = "<th width='60%'>企业名称</th> <th width='20%'>类型</th><th width='20%'>操作</th>";
				companyDiv.appendChild(head);
				var len = companies.length;
				var i;
				for(i = 0; i<len; i++){
					var line = document.createElement("tr");
					if(institute!=null){
						line.innerHTML = "<td>" + companies[i].name + "</td>" 
						+ "<td>" + companies[i].type + "</td>" 
						+ "<td>" + "<span><a target='_blank' href='college/coop_manage/view_com.jsp?company_id="+companies[i].id+"'>查看</a>&emsp;</span>"
						 + "<span><a href='javascript:;' onclick='deleteCoop(\""+companies[i].id+"\")'>解除</a></span>"+ "</td>";
					}else{
						line.innerHTML = "<td>" + companies[i].name + "</td>" 
						+ "<td>" + companies[i].type + "</td>" 
						+ "<td>" + "<span><a target='_blank' href='college/coop_manage/view_com.jsp?company_id="+companies[i].id+"'>查看</a>&emsp;</span>"
						 + "<span><a href='javascript:;' onclick='addCoop(\""+companies[i].id+"\")'>添加</a></span>"+ "</td>";
					}
					
					body.appendChild(line);
				
				}
				companyDiv.appendChild(body);
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

//得到合作企业列表
function getComList(pid){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getCompanyList.action";
	};
	request.open("GET", url + "?" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var com_menu = document.getElementById("com-menu");
				var data = eval("(" + request.responseText + ")");
				var i,item;
				var len = data.length;
				//填充数据
				for(i=0; i<len; i++){
					var item = data[i];
					var line = document.createElement("option");
					line.id = item.id;
					line.value = item.id;
					line.innerHTML = item.name
					
					com_menu.appendChild(line);
				}
				getMajorList(pid);
				
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);

}


function deleteCoop(com_id,institute){
	var institute = document.getElementById("institute").value;
	var flag = confirm("是否确认解除该合作关系？");
	if(flag){
		var request1 = createRequest();
		if(request1 == null){
			alert("Unable to create request");
		}else{
			var url = "college/deleteCoop.action?com_id=" + com_id +"&"+ encodeURI("institute="+institute);
		}
		request1.open("GET", url + "&" + Math.random(), true);
		request1.onreadystatechange=function(){
			if(request1.readyState == 4){
				if(request1.status == 200){
					var result = eval("(" + request1.responseText + ")");
					if(result == "success"){
						alert("删除成功！");
						window.location.reload();
					}else{
						alert("删除失败！");
					}
				}
			}
		};
		request1.setRequestHeader("Cache-Control","no-cache")
		request1.send(null);
	}
}

function addCoop(com_id){
	var institute = document.getElementById("institute").value;
	var flag = confirm("是否确认添加该合作关系？");
	if(flag){
		var request1 = createRequest();
		if(request1 == null){
			alert("Unable to create request");
		}else{
			var url = "college/addCoop.action?com_id=" + com_id +"&"+ encodeURI("institute="+institute);
		}
		request1.open("GET", url + "&" + Math.random(), true);
		request1.onreadystatechange=function(){
			if(request1.readyState == 4){
				if(request1.status == 200){
					var result = eval("(" + request1.responseText + ")");
					if(result == "success"){
						alert("添加成功！");
						window.location.reload();
					}else{
						alert("添加失败！");
					}
				}
			}
		};
		request1.setRequestHeader("Cache-Control","no-cache")
		request1.send(null);
	}
}

function loadCompanyData(com_id){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getCompanyData.action?id="+ com_id;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var company = eval("(" + request.responseText + ")");
				
				document.getElementById("name").innerHTML = "企业名称：" + company.name;
				document.getElementById("type").innerHTML = "行业类型：" + company.type;
				document.getElementById("location").innerHTML = "所在地区：" + company.location;
				document.getElementById("address").innerHTML = "具体地址：" + company.address;
				document.getElementById("tel").innerHTML = "联系电话：" + company.tel;
				document.getElementById("linkman").innerHTML = "联系人：" + company.linkman;
				
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
	
}

//加载短学期项目内容
function loadShortContent(pid){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "college/viewShortTermProject.action?id="+ pid;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var data = eval("(" + request.responseText + ")");
				
				document.getElementById("term").value = data.term;
				document.getElementById("name").value = data.name;
				if(data.company!=null){
					var select = document.getElementById(data.company.id);
					select.setAttribute("selected", true);
				}
				if(data.gradeNeed!=null && data.gradeNeed!="" && data.gradeNeed!="all"){
					document.getElementById("allGrade").removeAttribute("checked")
					var grade = document.getElementById(data.gradeNeed);
					grade.checked = true;
				}
				if(data.majorNeed!=null && data.majorNeed!="" && data.majorNeed!="all"){
					document.getElementById("allMajor").removeAttribute("checked")
					var majors = document.getElementsByName("majorNeed");
					for(var i=0;i<majors.length;i++){
						if(majors[i].value == data.majorNeed){
							majors[i].checked = true;
							break;
						}
					}
				}
				document.getElementById("purpose").value = data.purpose;
				document.getElementById("content").value =  data.content;
				document.getElementById("time").value =  data.time;
				document.getElementById("topNum").value =  data.topNum;
				document.getElementById("unmajorNum").value =  data.unmajorNum;
				document.getElementById("place").value =  data.place;
				if(data.fileUrl!=null && fileUrl != ""){
					var fileLine = document.getElementById("fileUrl");
					fileLine.innerHTML = "<h4><a href=\""+data.fileUrl+"\">下载附件</a></h4>";
					
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
	
}
//加载专业列表&年级列表
function getMajorList(pid){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "college/getMajorList.action";
	};
	request.open("GET", url + "?" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var class_menu = document.getElementById("class-menu");
				var data = eval("(" + request.responseText + ")");
				var i,item;
				var majorDiv = document.getElementById("majorDiv");
				var len = data.length;
				//填充数据
				for(i=0; i<len; i++){
					var item = data[i];
					var label = document.createElement("label");
					label.className="checkbox-inline";
					label.innerHTML = "<input type='radio' name='majorNeed' value=\""+item+"\">"+item;
					majorDiv.appendChild(label);
				}
				var gradeDiv = document.getElementById("gradeDiv")
				var myDate = new Date();
				var year = myDate.getYear().toString().substring(1);
				for(i=1; i<4; i++){
					year -= 1;
					label = document.createElement("label");
					label.className="checkbox-inline";
					label.innerHTML = "<input type='radio' name='gradeNeed' id=\""+year+"\" value=\""+year+"\">"+year+"级";
					gradeDiv.appendChild(label);
				}
				
				if(pid!=null && pid != "" && pid != "false" &&pid != 1){
					loadShortContent(pid);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);

}

//加载选择的短学期项目
function selectShortTermProject(pid){
	
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "student/getSelectedShortTermProject.action";
	};
	request.open("GET", url + "?" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var data = eval("(" + request.responseText + ")");
				if(data.result == "success"){
					var selectedDiv = document.getElementById("stp_selected")
					//填充数据
					var head = document.createElement("thead");
					var body = document.createElement("tbody");
					head.innerHTML = "<thead><tr><th>学期</th><th>已选短学期项目</th><th>合作公司</th><th>地点</th><th>操作</th> </tr></thead> ";
					selectedDiv.appendChild(head);
					var line = document.createElement("tr");
					line.innerHTML = "<td>" + data.shortTerm.term + "</td>" 
									+ "<td>" + data.shortTerm.name + "</td>" 
									+ "<td>" + data.shortTerm.company.name + "</td>"
									+ "<td>" + data.shortTerm.place + "</td>"
									+ "<td><a href=''>取消</a></td>";
					body.appendChild(line);
					selectedDiv.appendChild(body);
				}else{
					alert(data.result);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);

}

//选择短学期项目
function selectShortTermProject(pid){
	
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "student/selectShortTermProject.action?pid="+pid;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var data = eval("(" + request.responseText + ")");
				if(data.result == "success"){
					alert("选课成功！");
					window.location.reload();
				}else{
					alert(data.result);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);

}

//取消选择短学期项目
function cancelShortTermProject(rid){
	
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "student/cancelShortTermProject.action?rid="+rid;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var data = eval("(" + request.responseText + ")");
				if(data == "success"){
					alert("取消成功！");
					window.location.reload();
				}else{
					alert("取消失败！");
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);

}

function loadWeeklyData(pageSize,page){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getPracticeDataList.action?page="+page;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var page = eval("(" + request.responseText + ")");
				var data = page.list;
				if(data != null){
					var main = document.getElementById("weekly-records");
					main.innerHTML = "";
					for(var i=0;i<data.length;i++){
						var record = document.createElement("div");
						record.className = "week-record";
						record = "<div class='week-record col-sm-12'><div><a>"+data[i].student.id+" "+data[i].student.name+"</a><span class='classname'>"+data[i].student.classname+"</span></div>"
							+"<div class='content'>"+data[i].content+"</div>"
							+"<div class='record-info'>"
							+"<div class='place'>"
							+"<span class='glyphicon glyphicon-map-marker'></span>"
							+"<span>"+data[i].province+"-"+data[i].city+"</span>"
							+"<span class='time'>"+data[i].date+"</span>"
							+"</div></div>";
						main.innerHTML = main.innerHTML+record;
						
					}
					//更改页码
					var pageDiv = document.getElementById("pageDiv");
					pageDiv.innerHTML = "";
					var li_omit = document.createElement("li");
					li_omit.innerHTML = "<a>...</a>";
					var li_first =  document.createElement("li");
					li_first.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+"1)'>首页</a>";
					var li_tail =  document.createElement("li");
					li_tail.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+page.totalPage+")'>尾页</a>";
					pageDiv.appendChild(li_first);
					//向前翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+(page.currentPage-1)+")'>&laquo;</a>";
					}
					//中间页码
					if(page!=null){
						//页数>6的输出
						if(page.totalPage>6){
							if(page.currentPage<6){
								for(i = 1; i < 7; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}else if(page.currentPage >(page.totalPage-6)){
								pageDiv.appendChild(li_omit);
								for(i = page.totalPage-6; i <= page.totalPage; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
							}else{
								pageDiv.appendChild(li_omit);
								for(i = page.currentPage-2; i < page.currentPage+4; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}
						}else{
							for(i = 1; i <= page.totalPage; i++){
								var li_page = document.createElement("li");
								if(i == page.currentPage){
									li_page.className="active";
								}
								li_page.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+i+")'>"+ i +"</a>";
								pageDiv.appendChild(li_page);
							}
						}
					}else{
						var li_page = document.createElement("li");
						li_page.className="active";
						li_page.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+"1)'>1</a>";
						pageDiv.appendChild(li_page);
					}
					//向后翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='loadWeeklyData("+pageSize+","+(page.currentPage+1)+")'>&raquo;</a>";
					}
					pageDiv.appendChild(li_tail);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

//加载职位数据
function getJobList(pageSize,page){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getJobList.action?page="+page;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var page = eval("(" + request.responseText + ")");
				var data = page.list;
				if(data != null){
					var main = document.getElementById("job-records");
					main.innerHTML = "";
					for(var i=0;i<data.length;i++){
						var record = document.createElement("tr");
						record = "<td>"+data[i].name+"</td>"
							+"<td>"+data[i].company.name+"</td>"
							+"<td>"+data[i].province+" "+data[i].city+"</td>";
						if(data[i].status == 0){
							record = record + "<td>已关闭/已招满</td>";
						}else{
							record =record + "<td>"+data[i].haveNum+"/"+data[i].needNum+"</td>";
						}
						
						main.innerHTML = main.innerHTML+record;
						
					}
					//更改页码
					var pageDiv = document.getElementById("pageDiv");
					pageDiv.innerHTML = "";
					var li_omit = document.createElement("li");
					li_omit.innerHTML = "<a>...</a>";
					var li_first =  document.createElement("li");
					li_first.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+"1)'>首页</a>";
					var li_tail =  document.createElement("li");
					li_tail.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+page.totalPage+")'>尾页</a>";
					pageDiv.appendChild(li_first);
					//向前翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+(page.currentPage-1)+")'>&laquo;</a>";
					}
					//中间页码
					if(page!=null){
						//页数>6的输出
						if(page.totalPage>6){
							if(page.currentPage<6){
								for(i = 1; i < 7; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}else if(page.currentPage >(page.totalPage-6)){
								pageDiv.appendChild(li_omit);
								for(i = page.totalPage-6; i <= page.totalPage; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
							}else{
								pageDiv.appendChild(li_omit);
								for(i = page.currentPage-2; i < page.currentPage+4; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}
						}else{
							for(i = 1; i <= page.totalPage; i++){
								var li_page = document.createElement("li");
								if(i == page.currentPage){
									li_page.className="active";
								}
								li_page.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+i+")'>"+ i +"</a>";
								pageDiv.appendChild(li_page);
							}
						}
					}else{
						var li_page = document.createElement("li");
						li_page.className="active";
						li_page.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+"1)'>1</a>";
						pageDiv.appendChild(li_page);
					}
					//向后翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getJobList("+pageSize+","+(page.currentPage+1)+")'>&raquo;</a>";
					}
					pageDiv.appendChild(li_tail);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

//加载招聘中的职位数据
function getJobRecriutList(pageSize,page){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getJobRecruitList.action?page="+page;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var page = eval("(" + request.responseText + ")");
				var data = page.list;
				if(data != null){
					var main = document.getElementById("job-records");
					main.innerHTML = "";
					for(var i=0;i<data.length;i++){
						var record = document.createElement("tr");
						record = "<td>"+data[i].name+"</td>"
							+"<td>"+data[i].company.name+"</td>"
							+"<td>"+data[i].province+" "+data[i].city+"</td>";
						if(data[i].status == 0){
							record = record + "<td>已关闭/已招满</td>";
						}else{
							record =record + "<td>"+data[i].haveNum+"/"+data[i].needNum+"</td>";
						}
						
						main.innerHTML = main.innerHTML+record;
						
					}
					//更改页码
					var pageDiv = document.getElementById("pageDiv");
					pageDiv.innerHTML = "";
					var li_omit = document.createElement("li");
					li_omit.innerHTML = "<a>...</a>";
					var li_first =  document.createElement("li");
					li_first.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+"1)'>首页</a>";
					var li_tail =  document.createElement("li");
					li_tail.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+page.totalPage+")'>尾页</a>";
					pageDiv.appendChild(li_first);
					//向前翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+(page.currentPage-1)+")'>&laquo;</a>";
					}
					//中间页码
					if(page!=null){
						//页数>6的输出
						if(page.totalPage>6){
							if(page.currentPage<6){
								for(i = 1; i < 7; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}else if(page.currentPage >(page.totalPage-6)){
								pageDiv.appendChild(li_omit);
								for(i = page.totalPage-6; i <= page.totalPage; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
							}else{
								pageDiv.appendChild(li_omit);
								for(i = page.currentPage-2; i < page.currentPage+4; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}
						}else{
							for(i = 1; i <= page.totalPage; i++){
								var li_page = document.createElement("li");
								if(i == page.currentPage){
									li_page.className="active";
								}
								li_page.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+i+")'>"+ i +"</a>";
								pageDiv.appendChild(li_page);
							}
						}
					}else{
						var li_page = document.createElement("li");
						li_page.className="active";
						li_page.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+"1)'>1</a>";
						pageDiv.appendChild(li_page);
					}
					//向后翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getJobRecruitList("+pageSize+","+(page.currentPage+1)+")'>&raquo;</a>";
					}
					pageDiv.appendChild(li_tail);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

//加载企业发布的职位数据
function getJobByComList(pageSize,page){
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "common/getJobByComList.action?page="+page;
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				var page = eval("(" + request.responseText + ")");
				var data = page.list;
				if(data != null){
					var main = document.getElementById("job-records");
					main.innerHTML = "";
					for(var i=0;i<data.length;i++){
						var record = document.createElement("tr");
						record = "<td>"+data[i].name+"</td>";
						if(data[i].status == 0){
							record = record + "<td>已关闭/已招满</td>"
									+"<td><a>编辑</a><a>继续招聘</a></td>";
						}else{
							record =record + "<td>"+data[i].haveNum+"/"+data[i].needNum+"</td>";
						}
						record =record +"<td><a>编辑</a>&emsp;<a>关闭</a></td>";
						main.innerHTML = main.innerHTML+record;
						
					}
					//更改页码
					var pageDiv = document.getElementById("pageDiv");
					pageDiv.innerHTML = "";
					var li_omit = document.createElement("li");
					li_omit.innerHTML = "<a>...</a>";
					var li_first =  document.createElement("li");
					li_first.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+"1)'>首页</a>";
					var li_tail =  document.createElement("li");
					li_tail.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+page.totalPage+")'>尾页</a>";
					pageDiv.appendChild(li_first);
					//向前翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+(page.currentPage-1)+")'>&laquo;</a>";
					}
					//中间页码
					if(page!=null){
						//页数>6的输出
						if(page.totalPage>6){
							if(page.currentPage<6){
								for(i = 1; i < 7; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}else if(page.currentPage >(page.totalPage-6)){
								pageDiv.appendChild(li_omit);
								for(i = page.totalPage-6; i <= page.totalPage; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
							}else{
								pageDiv.appendChild(li_omit);
								for(i = page.currentPage-2; i < page.currentPage+4; i++){
									var li_page = document.createElement("li");
									if(i == page.currentPage){
										li_page.className="active";
									}
									li_page.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+i+")'>"+ i +"</a>";
									pageDiv.appendChild(li_page);
								}
								pageDiv.appendChild(li_omit);
							}
						}else{
							for(i = 1; i <= page.totalPage; i++){
								var li_page = document.createElement("li");
								if(i == page.currentPage){
									li_page.className="active";
								}
								li_page.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+i+")'>"+ i +"</a>";
								pageDiv.appendChild(li_page);
							}
						}
					}else{
						var li_page = document.createElement("li");
						li_page.className="active";
						li_page.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+"1)'>1</a>";
						pageDiv.appendChild(li_page);
					}
					//向后翻页
					if(page.currentPage!=1){
						var li_last =  document.createElement("li");
						li_last.innerHTML = "<a href='javascript:;' onclick='getJobByComList("+pageSize+","+(page.currentPage+1)+")'>&raquo;</a>";
					}
					pageDiv.appendChild(li_tail);
				}
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
}

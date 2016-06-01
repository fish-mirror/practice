
$(function () {
	var bj;
	/* $.ajax({
	        url:'college/getStatusDistri.action?institute=%E6%96%B0%E5%AA%92%E4%BD%93%E5%AD%A6%E9%99%A2',
	        type: "post",
	        dataType: "json",
	        success: function (data) {
	            bj = data;
	        },
	        error: function (XMLHttpRequest, textStatus, errorThrown) {
	           //alert(errorThrown);
	        }
	    });*/
	var request = createRequest();
	if(request == null){
		alert("Unable to create request");
	}else{
		var url = "college/getStatusDistri.action?"+encodeURI("institute=格林芬多学院");
	};
	request.open("GET", url + "&" + Math.random(), true);
	request.onreadystatechange=function(){
		if(request.readyState == 4){
			if(request.status == 200){
				bj = eval("(" + request.responseText + ")");
				function pie(stu){
					   
				    $('#pie').highcharts({
				        chart: {
				            plotBackgroundColor: null,
				            plotBorderWidth: null,
				            plotShadow: false
				        },
				        title: {
				            text: '格林芬多学院学生实习情况'
				        },
				        credits:{
				            enabled:false
				        },
				        tooltip:{
				          valueSuffix: '人'
				          // footerFormat:''
				        },
				        plotOptions: {
				            pie: {
				                allowPointSelect: true,
				                cursor: 'pointer',
				                dataLabels: {
				                    enabled: true,
				                    color: '#000000',
				                    connectorColor: '#000000',
				                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'
				                }
				            }
				        },
				        series: [{
				            type: 'pie',
				            name: '有',
				            data: [
				                ['未实习的学生',stu.no_practice],
				                ['结束实习的学生',stu.practiced],
				                ['完成实习已评分的学生',stu.practice_graded],
				                {
				                    name: '正在实习的学生',
				                    y:stu.practicing,
				                    sliced: true,
				                    selected: true
				                }
				            ]
				        }]
				    });
			    }
			    pie(bj.institute);
			    function changeCss(a){
			        $(".nav-pills li").removeClass("active");
			        a.addClass("active");
			    }

			    $(".nav-pills li").click(function(){
			        var a=$(this).not(".dropdown").children("a")[0].innerHTML;
			        changeCss($(this));
			        var x="bj."+$(this).attr("id");
			        if(x=="bj.undefined"){
			            x="bj.\""+a+"\"";
			        }
			        x=eval("("+x+")");
			        pie(x);
			    });
			}
		}
	};
	request.setRequestHeader("Cache-Control","no-cache")
	request.send(null);
   
    
});
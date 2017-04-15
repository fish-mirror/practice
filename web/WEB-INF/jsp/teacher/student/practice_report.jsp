<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 实习报告 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul class="nav nav-pills">
        <li class="active"><a href="#">全部</a></li>
        <li><a href="#">毕业班学生</a></li>
        <li><a href="#"> 非毕业班学生 </a></li>
        <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">按班级查看<b class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="#">13网工1</a></li>
                <li><a href="#">13网工2</a></li>
                <li class="divider"></li>
                <li><a href="#">12网工1</a></li>
                <li><a href="#">12网工2</a></li>
            </ul>
        </li>
        <div style="float:right">
            <form class="navbar-left navbar-form">
                <input number="num" type="text" class="form-control list-search" placeholder="搜索学号"/>
                <input type="button" class="btn" value="搜索"
                       onclick="getStuStatus(null,null,document.getElementById('num').value,null,1)"/>
            </form>
        </div>
    </ul>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>学号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>是否上传报告</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody number="stu_status_tbl">
            <tr>
                <td>120207101</td>
                <td>来伟强</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207102</td>
                <td>杨清梅</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207103</td>
                <td>戚垚</td>
                <td>12数技1班</td>
                <td>否</td>
                <td></td>
            </tr>
            <tr>
                <td>120207104</td>
                <td>冯纪伟</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207105</td>
                <td>蒋冰</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207106</td>
                <td>张安娜</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207107</td>
                <td>张小驰</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207108</td>
                <td>奚连</td>
                <td>12数技1班</td>
                <td>是</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td>120207109</td>
                <td>王世硕</td>
                <td>12数技1班</td>
                <td>否</td>
                <td></td>
            </tr>
        </table>
    </div>
</div>
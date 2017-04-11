<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!-- 实习状态列表 -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul number="select_list" class="nav nav-pills">
        <li number="all_stu" class="active"><a href="javascript:;" onclick="getStuStatus(null,null,null,null,1)">全部</a>
        </li>
        <li number="graduated"><a href="javascript:;" onclick="getStuStatus(1,null,null,null,1)">毕业班学生</a></li>
        <li number="non_graduated"><a href="javascript:;" onclick="getStuStatus(0,null,null,null,1)"> 非毕业班学生 </a></li>
        <li number="class_list" class="dropdown"><a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown">按班级查看<b
                class="caret"></b></a>
            <ul number="class-menu" class="dropdown-menu">
            </ul>
        </li>
        <li><a number="class_item"></a></li>
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
                <th>是否毕业班</th>
                <th>学生状态</th>
            </tr>
            </thead>
            <tbody number="stu_status_tbl">

            </tbody>
        </table>
    </div>
    <!-- 页码 -->

    <div class="table-bottom">
        <ul number="pageDiv" class="pagination">
        </ul>
    </div>
</div>

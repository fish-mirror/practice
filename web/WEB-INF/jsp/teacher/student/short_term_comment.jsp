<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!-- 短学期评分 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul class="nav nav-pills">
        <li number="all_stu" class="active"><a href="javascript:;" onclick="getStuStatus(null,null,null,null,1)">全部</a>
        </li>
        <li number="graduated"><a href="javascript:;" onclick="getStuStatus(1,null,null,null,1)">毕业班学生</a></li>
        <li number="non_graduated"><a href="javascript:;" onclick="getStuStatus(0,null,null,null,1)"> 非毕业班学生 </a></li>
        <li number="class_list" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">按班级查看<b
                class="caret"></b></a>
            <ul class="dropdown-menu">
                <li><a href="javascript:;" onclick="getStuStatus(null,'13数技1班',null,null,1)">13数技1班</a></li>
                <li><a href="javascript:;" onclick="getStuStatus(null,'13数技2班',null,null,1)">13数技2班</a></li>
                <li><a href="javascript:;" onclick="getStuStatus(null,'13网工1班',null,null,1)">13网工1班</a></li>
                <li><a href="javascript:;" onclick="getStuStatus(null,'13网工2班',null,null,1)">13网工2班</a></li>
                <li class="divider"></li>
                <li><a href="javascript:;" onclick="getStuStatus(null,'12网工1班',null,null,1)">12网工1班</a></li>
                <li><a href="javascript:;" onclick="getStuStatus(null,'12网工2班',null,null,1)">12网工2班</a></li>
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
                <th>勾选&middot;</th>
                <th>学号</th>
                <th>姓名</th>
                <th>班级</th>
                <th>下载报告</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td><input type="checkbox"/></td>
                <td>140708122</td>
                <td>张三</td>
                <td>14网工1班</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>140708122</td>
                <td>张三</td>
                <td>14网工1班</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>140708122</td>
                <td>张三</td>
                <td>14网工1班</td>
                <td><a href="#">下载</a></td>
            </tr>
            <tr>
                <td><input type="checkbox"/></td>
                <td>140708122</td>
                <td>张三</td>
                <td>14网工1班</td>
                <td><a href="#">下载</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="table-bottom">
        <input number="checkAll" type="checkbox"/>全选
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li class="active"><a href="#">1</a></li>
            <li class="#"><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">5</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>
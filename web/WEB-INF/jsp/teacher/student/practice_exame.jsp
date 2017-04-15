<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 实习检查 -->
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
        <form class="navbar-left navbar-form">
            <input type="text" class="form-control list-search" placeholder="搜索学号"/>
        </form>
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
            <tbody>
            <tr>
                <td>120708122</td>
                <td>张三</td>
                <td>12网工1班</td>
                <td>是</td>
                <td>正在实习 <a href="#">查看详细</a></td>
            </tr>
            <tr>
                <td>120708122</td>
                <td>张三</td>
                <td>12网工1班</td>
                <td>是</td>
                <td>正在实习 <a href="#">查看详细</a></td>
            </tr>
            <tr>
                <td>120708122</td>
                <td>张三</td>
                <td>12网工1班</td>
                <td>是</td>
                <td>正在实习 <a href="#">查看详细</a></td>
            </tr>
            <tr>
                <td>120708122</td>
                <td>张三</td>
                <td>12网工1班</td>
                <td>是</td>
                <td>正在实习 <a href="#">查看详细</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    <div class="table-bottom">
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

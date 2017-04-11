<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<title>资讯列表-实习管理系统</title>
<!-- 资讯列表 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul class="nav nav-pills">
        <li class="active"><a href="#">全部</a></li>
        <form class="navbar-form navbar-left">
            <input type="text" class="form-control list-search" placeholder="资讯关键词"/>
            <label for="name">资讯类型</label>
            <select class="form-control">
                <option></option>
                <option>宣讲会</option>
                <option>招聘会</option>
                <option>招聘信息</option>
                <option>就业形势</option>
            </select>
        </form>
    </ul>
    <div class="table-responsive">
        <table class="table table-striped">
            <thead>
            <tr>
                <th>资讯名称</th>
                <th>资讯类型</th>
                <th>资讯内容</th>
                <th>资讯管理</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>天气预报</td>
                <td>就业形势</td>
                <td>今天天气真好呀，多云转…</td>
                <td>
                    <a href="#">编辑</a>
                    <a href="#">删除</a>
                </td>
            </tr>
            <tr>
                <td>天气预报</td>
                <td>就业形势</td>
                <td>今天天气真好呀，多云转…</td>
                <td>
                    <a href="#">编辑</a>
                    <a href="#">删除</a>
                </td>
            </tr>
            <tr>
                <td>天气预报</td>
                <td>就业形势</td>
                <td>今天天气真好呀，多云转…</td>
                <td>
                    <a href="#">编辑</a>
                    <a href="#">删除</a>
                </td>
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

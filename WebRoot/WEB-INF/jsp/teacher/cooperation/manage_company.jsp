<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 合作企业管理 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul number="select_list" class="nav nav-pills">
        <li number="coop" class="active"><a href="javascript:;" onclick="loadCoopList()">合作企业</a></li>
        <li number="all"><a href="javascript:;" onclick="loadCoopList('all')">全部</a></li>

        <div style="float:right">
            <form class="navbar-left navbar-form">
                <input number="num" type="text" class="form-control list-search" placeholder="搜索企业"/>
                <input type="button" class="btn" value="搜索"/>
            </form>
        </div>
    </ul>
    <div class="table-responsive">
        <table number="companyList" class="table table-striped">
            <div number="body">
                还没有合作企业，赶紧查看全部企业吧


            </div>
        </table>
    </div>
</div>
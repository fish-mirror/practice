<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 状态统计 -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <ul number="select_list" class="nav nav-pills">
        <li number="institute" class="active"><a href="javascript:;">全部</a></li>
        <li number="graduate"><a href="javascript:;">毕业班学生</a></li>
        <li number="non_graduate"><a href="javascript:;"> 非毕业班学生 </a></li>
    </ul>
    <div number="pie" style="min-width:500px;height:400px"></div>
</div>

<script type="text/javascript" src="js/highcharts.js"></script>
<script src="js/pie.js"></script> 

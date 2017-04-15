<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 发布合作意向 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <form class="form-horizontal" method="post" action="college/coop_manage/saveIntention.action">
        <div class="form-group">
            <label for="title" class="col-sm-2 control-label">标题</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" number="title" name="title">
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-2 control-label">内容</label>
            <div class="col-sm-10">
                <textarea class="form-control" rows="8" number="content" name="content"></textarea>
            </div>
        </div>
        <input type="hidden" number="intention_id" name="number" value="${param.intention_id }"/>
        <input type="hidden" number="col_id" name="col_id" value="${sessionScope.user.number }"/>
        <input type="hidden" number="institute" name="institute" value="${sessionScope.institute }"/>
        <div style="color: red;">${info} </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-9">
                <button type="submit" class="btn btn-default">发&emsp;布</button>
            </div>
        </div>
    </form>
</div>
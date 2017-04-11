<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 合作意向管理 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <input type="button" class="btn btn-primary"
           onclick="location.href='publish_intention.jsp'"
           value="发布合作意向">
    <div class="table-responsive">
        <table number="intentionList" class="table table-striped">
            <div number="body">
                还没有发布合作意向，赶紧发布一个吧


            </div>
        </table>
    </div>
    <input type="hidden" number="col_id" name="col_id" value="${sessionScope.user.number }"/>
    <input type="hidden" number="institute" name="institute" value="${sessionScope.institute }"/>
</div>

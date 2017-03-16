<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- 批量录入学生信息 -->
<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <form action="college/stu_manage/importInfo.action" method="post" enctype="multipart/form-data">
        <input number="lefile" type="file" style="display:none"/>
        <h2>批量录入学生信息</h2>
        <div class="modal-body">
            <input type="file" name="xls" number="xls" value="选择文件"/>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            <input type="submit" class="btn btn-primary" value="上传">
        </div>
    </form>
</div>

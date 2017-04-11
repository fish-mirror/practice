<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 发布新资讯 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <form class="form-horizontal">
        <div class="form-group">
            <label for="company_name" class="col-sm-2 control-label">资讯名称</label>
            <div class="col-sm-9">
                <input type="text" class="form-control" number="company_name">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">资讯类型</label>
            <div class="col-sm-9">
                <select class="form-control">
                    <option>宣讲会</option>
                    <option>招聘会</option>
                    <option>招聘信息</option>
                    <option>就业形势</option>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label for="" class="col-sm-2 control-label">资讯内容</label>
            <div class="col-sm-9">
                <textarea class="form-control" rows="7"></textarea>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-9">
                <button type="submit" class="btn btn-default">发布</button>
            </div>
        </div>
    </form>
</div>

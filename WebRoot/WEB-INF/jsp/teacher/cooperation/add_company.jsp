<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!-- 创建企业账号 -->

<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
    <form number="defaultForm" class="form-horizontal" method="post" action="college/coop_manage/addCompany.action">
        <div class="form-group">
            <label for="number" class="col-sm-2 control-label">用户名&emsp;</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" number="number" name="number">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-2 control-label">企业名称</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" number="name" name="name">
            </div>
        </div>
        <div class="form-group">
            <label for="pwd" class="col-sm-2 control-label">密&emsp;&emsp;码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" number="pwd" name="pwd">
            </div>
        </div>
        <div class="form-group">
            <label for="repwd" class="col-sm-2 control-label">确认密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" number="repwd" name="repwd">
            </div>
        </div>
        <input type="hidden" number="institute" name="institute" value="${sessionScope.institute }">
        <div style="color: red;">${info} </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-9">
                <button type="submit" class="btn btn-default" onclick="createCom()">创建企业账号</button>
            </div>
        </div>
    </form>
</div>

    <script src="js/bootstrapValidator.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#defaultForm').bootstrapValidator({
                message: 'This value is not valid',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    number: {
                        message: '无效用户名',
                        validators: {
                            notEmpty: {
                                message: '用户名不能为空'
                            },
                            stringLength: {
                                min: 3,
                                max: 9,
                                message: '用户名长度必须在3-9个字符之间'
                            },
                            regexp: {
                                regexp: /^[a-zA-Z0-9_]+$/,
                                message: '用户名只能由字母、数字和下划线组成'
                            }
                        }
                    },
                    name: {
                        validators: {
                            notEmpty: {
                                message: '企业名不能为空'
                            }
                        }

                    },
                    pwd: {
                        validators: {
                            notEmpty: {
                                message: '密码不能为空'
                            },
                            identical: {
                                field: 'repwd',
                                message: '两次密码不一致'
                            }
                        }
                    },
                    repwd: {
                        validators: {
                            notEmpty: {
                                message: '确认密码不能为空'
                            },
                            identical: {
                                field: 'pwd',
                                message: '两次密码不一致'
                            }
                        }
                    },
                }
            });
        });
    </script>
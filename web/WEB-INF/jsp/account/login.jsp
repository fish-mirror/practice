<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<c:set var="assets_domain" value="http://opvyx1uc9.bkt.clouddn.com" scope="request"/>

<div class="container" style="min-width:800px">
    <br/><br/><br/>

    <!-- 学校LOGO -->
    <div class="col-xs-6">
        <br/><br/>
        <img src="${assets_domain}/practice/image/logo_school.png"/>

    </div>
    <!-- 主体 -->
    <div class="col-xs-12">
        <br/><br/><br/>

        <div class="bg">
            <img src="${assets_domain}/practice/image/login_bg.png"/>

        </div>
        <div class="col-xs-8"></div>
        <!-- 登录框 -->
        <div class="col-xs-4 login">
            <h4 class="text-center"><b>实 习 管 理 系 统</b></h4>
            <hr/>
            <form action="login.action" role="form" method="post">
                <div class="form-group">
                    <label>用户名：</label>
                    <input type="text" name="name" class="form-control" placeholder="请在此输入您的用户名" required="required">
                </div>
                <div class="form-group">
                    <label>密　码：</label>
                    <input type="password" name="pwd" class="form-control" placeholder="请在此输入您的密码" required="required">
                </div>
                <div style="color: red;">${info} </div>

                <div class="col-sm-offset-2 col-sm-4">
                    <button type="submit" class="btn btn-primary"> &nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;</button>
                </div>
                <div class=" col-sm-4">
                    <button type="button" class="btn btn-default" onclick="toRegister()">企业用户注册</button>
                </div>
            </form>
            <br/>
        </div>
    </div>
</div>
</div>
<script type="text/javascript">
    function toRegister() {
        window.location.href = "/register";
    }
</script>

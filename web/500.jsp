<%@ page contentType="text/html;charset=GBK" isErrorPage="true" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>

<c:set var="assets_domain" value="http://opvyx1uc9.bkt.clouddn.com" scope="request"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="ThemeBucket">
    <link rel="shortcut icon" href="#" type="image/png">

    <title>500 出错了</title>

    <link href="${assets_domain}/base/css/style.css" rel="stylesheet">
    <link href="${assets_domain}/base/css/style-responsive.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="${assets_domain}/base/js/html5shiv.js"></script>
    <script src="${assets_domain}/base/js/respond.min.js"></script>
    <![endif]-->
</head>

<body class="error-page">

<section>
    <div class="container ">

        <section class="error-wrapper text-center">
            <h1><img alt="" src="${assets_domain}/base/images/500-error.png"></h1>
            <h2>哎呀 ！！</h2>
            <h3>呃...服务器可能出错了</h3>
            <p class="nrml-txt">尝试刷新，或者回到主页。如果问题一直存在，你可以 <a href="#">联系我们</a></p>
            <a class="back-btn" href="/home"> 返回主页</a>
        </section>

    </div>
</section>

<!-- Placed js at the end of the document so the pages load faster -->
<script src="${assets_domain}/base/js/jquery-1.10.2.min.js"></script>
<script src="${assets_domain}/base/js/jquery-migrate-1.2.1.min.js"></script>
<script src="${assets_domain}/base/js/bootstrap.min.js"></script>
<script src="${assets_domain}/base/js/modernizr.min.js"></script>


<!--common scripts for all pages-->
<!--<script src="js/scripts.js"></script>-->

</body>
</html>

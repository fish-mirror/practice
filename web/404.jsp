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

    <title>404 该资源不存在</title>

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
            <h1><img alt="" src="${assets_domain}/base/images/404-error.png"></h1>
            <h2>page not found</h2>
            <h3>呃...您访问的页面可能不存在</h3>
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

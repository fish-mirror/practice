<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/jsp/include/taglibs.jsp" %>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title></title>
    <c:set var="assets_domain" value="http://opvyx1uc9.bkt.clouddn.com" scope="request"/>

    <link href="${assets_domain}/practice/css/bootstrap.min.css" rel="stylesheet" />

    <style>
        .bg{
            position:absolute;
            z-index:-1;
        }
        h4,label{color:#434343;}
        .login{
            padding:20px;
            border-color:#bfbfbf;
            border-style:solid;
            border-width:thin;
            border-radius:5px
        }
    </style>
</head>
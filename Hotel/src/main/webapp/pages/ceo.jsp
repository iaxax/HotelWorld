<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
        <%@include file="/pages/common/meta.html" %>
<title>旅馆</title>
</head>
<body>
        <div class="container">
                <div class="row">
                        <div class="col-md-2 col-md-offset-1 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="旅店信息" onclick="insert('#form', '/Hotel/pages/member/activate_form.jsp');">
                                <p><a class="text-center"
                                         onclick="insert('#form', '/Hotel/pages/member/activate_form.jsp');"
                                 >旅店信息</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="统计信息" onclick="insert('#form', '/Hotel/pages/member/cancel_form.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/member/cancel_form.jsp');"
                                >统计信息</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="通知" onclick="insert('#form', '/Hotel/pages/member/cancel_form.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/member/cancel_form.jsp');"
                                >通知</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="退出" onclick=";">
                                <p><a class="text-center" onclick="window.location= '/Hotel/logout.action';">退出</a></p>
                        </div>
                </div>
        </div>
</body>
</html>
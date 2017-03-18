<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
        <%@include file="/pages/common/meta.html" %>
        <link rel="stylesheet" href="/Hotel/css/bootstrap-datepicker.min.css">
<title>旅馆</title>
</head>
<body>
        <div class="container">
                <div class="row">
                        <div class="col-md-2 col-md-offset-1 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="发布计划" onclick="insert('#form', '/Hotel/pages/hotel/plan.jsp');">
                                <p><a class="text-center"
                                         onclick="insert('#form', '/Hotel/pages/hotel/plan.jsp');"
                                 >发布计划</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="开店申请" onclick="insert('#form', '/Hotel/pages/hotel/new_branch.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/hotel/new_branch.jsp');"
                                >开店申请</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="旅店信息" onclick="insert('#form', '/Hotel/pages/hotel/hotel_info.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/hotel/hotel_info.jsp');"
                                >旅店信息</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="通知" onclick="insert('#form', '/Hotel/pages/hotel/hotel_msg.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/hotel/hotel_msg.jsp');"
                                >通知</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="退出" onclick="window.location= '/Hotel/logout.action';">
                                <p><a class="text-center" onclick="window.location= '/Hotel/logout.action';">退出</a></p>
                        </div>
                </div>
        </div>
        
         <div class="container block-gap">
                <div id="form"></div>
        </div>
        
        <script src="/Hotel/js/jquery-3.1.1.min.js"></script>
        <script src="/Hotel/js/bootstrap.min.js"></script>
        <script src="/Hotel/js/bootstrap-datepicker.min.js"></script>
        <script src="/Hotel/js/echarts.min.js"></script>
        <script src="/Hotel/js/util.js"></script>
        <script src="/Hotel/js/plan.js"></script>
        <script src="/Hotel/js/branch.js"></script>
        <script src="/Hotel/js/hotel_info.js"></script>
        <script src="/Hotel/js/hotel_msg.js"></script>
</body>
</html>
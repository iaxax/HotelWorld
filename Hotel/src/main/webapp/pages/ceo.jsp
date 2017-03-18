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
                        <div class="col-md-2 col-md-offset-3 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="消息通知" onclick="insert('#form', '/Hotel/pages/hotel/ceo_msg.jsp');">
                                <p><a class="text-center"
                                         onclick="insert('#form', '/Hotel/pages/hotel/ceo_msg.jsp');"
                                 >消息通知</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="统计信息" onclick="insert('#form', '/Hotel/pages/hotel/ceo_info.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/hotel/ceo_info.jsp');"
                                >统计信息</a></p>
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
        <script src="/Hotel/js/echarts.min.js"></script>
        <script src="/Hotel/js/util.js"></script>
        <script src="/Hotel/js/ceo_msg.js"></script>
        <script src="/Hotel/js/hotel_info.js"></script>
</body>
</html>
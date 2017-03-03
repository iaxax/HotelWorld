<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="/pages/common/meta.html" %>
<title>主页</title>
<style>    
        .button {
                width: 60%;
                margin-top: 20px;
                margin-bottom: 10px;
                margin-left: 40%;
        }
        
        #title {
                margin-top: 240px;
                margin-bottom: 50px;
        }
</style>
</head>
<body>
        <div class="container">
                <div class="row">
                        <div class="col-md-4">
                                <img src="/Hotel/img/hotel.jpg">
                                <div>
                                        <button class="form-control btn btn-primary button text-center"
                                                onclick="insert('#intro', '/Hotel/pages/login.jsp');"
                                        >登录</button>
                                </div>
                                <div>
                                        <button class="form-control btn btn-primary button text-center"
                                                 onclick="insert('#intro', '/Hotel/pages/member/register_form.jsp');"
                                        >会员创建 </button>
                                </div>
                        </div>
                        
                        <div class="col-md-8" id="intro">
                                <h2 class="text-center" id="title">Hotel World</h2>
                                <h4 class="text-center">
                                        希望您享受本店之旅，在这里找到家的感觉
                                </h4>
                        </div>
                </div>
        </div>
        
        <script src="/Hotel/js/jquery-3.1.1.min.js"></script>
        <script src="/Hotel/js/util.js"></script>
        <script src="/Hotel/js/register.js"></script>
        <script src="/Hotel/js/login.js"></script>
</body>
</html>
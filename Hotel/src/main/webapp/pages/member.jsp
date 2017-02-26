<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/pages/common/meta.html" %>
<title>会员</title>
<style>
        p>a {
                color: green;
        }
</style>
</head>
<body>
        <%@include file="/pages/common/nav.jsp" %>
        
        <div class="container">
                <div class="row">
                        <div class="col-md-2 col-md-offset-1 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="注册会员" onclick="showRegisterForm('#form');">
                                <p><a class="text-center" onclick="showRegisterForm('#form');">注册会员</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="激活会员" onclick="showActivateForm('#form');">
                                <p><a class="text-center" onclick="showActivateForm('#form');">激活会员</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="取消会员" onclick="showCancelForm('#form');">
                                <p><a class="text-center" onclick="showCancelForm('#form');">取消会员</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="会员充值" onclick="showRechargeForm('#form');">
                                <p><a class="text-center" onclick="showRechargeForm('#form');">会员充值</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="会员信息" onclick="showInfo('#form');">
                                <p><a class="text-center" onclick="showInfo('#form');">会员信息</a></p>
                        </div>       
                </div>
        </div>
        
        <div class="container block-gap">
                <div id="form"></div>
        </div>
        
        <script src="/Hotel/js/nav.js"></script>
        <script src="/Hotel/js/jquery-3.1.1.min.js"></script>
        <script src="/Hotel/js/jquery.tmpl.min.js"></script>
        <script src="/Hotel/js/member.js"></script>
</body>
</html>
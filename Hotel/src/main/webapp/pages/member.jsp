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
        <div class="container">
                <div class="row">
                        <div class="col-md-2 col-md-offset-1 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="激活会员" onclick="insert('#form', '/Hotel/pages/member/activate_form.jsp');">
                                <p><a class="text-center"
                                         onclick="insert('#form', '/Hotel/pages/member/activate_form.jsp');"
                                 >激活会员</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="取消会员" onclick="insert('#form', '/Hotel/pages/member/cancel_form.jsp');">
                                <p><a class="text-center" 
                                        onclick="insert('#form', '/Hotel/pages/member/cancel_form.jsp');">取消会员</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="会员充值" onclick="insert('#form', '/Hotel/pages/member/recharge_form.jsp');">
                                <p><a class="text-center" onclick="insert('#form', '/Hotel/pages/member/recharge_form.jsp');">会员充值</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="会员信息" onclick="insert('#form', '/Hotel/pages/member/info.jsp');">
                                <p><a class="text-center"
                                         onclick="insert('#form', '/Hotel/pages/member/info.jsp');">会员信息</a></p>
                        </div>
                        <div class="col-md-2 text-center">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="退出" onclick=";">
                                <p><a class="text-center" onclick=";">退出</a></p>
                        </div>
                </div>
        </div>
        
        <div class="container block-gap">
                <div id="form"></div>
        </div>
        
        <script src="/Hotel/js/jquery-3.1.1.min.js"></script>
	<script src="/Hotel/js/jquery.tmpl.min.js"></script>
	<script src="/Hotel/js/bootstrap.min.js"></script>
	<script src="/Hotel/js/util.js"></script>
        <script src="/Hotel/js/register.js"></script>
</body>
</html>
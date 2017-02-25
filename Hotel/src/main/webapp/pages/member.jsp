<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<%@include file="/pages/common/meta.html" %>
<title>会员</title>
</head>
<body>
        <%@include file="/pages/common/nav.jsp" %>
        
        <div class="container">
                <div class="row">
                        <div class="col-md-3 col-md-offset-1">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="注册会员" onclick="register();">
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="激活会员">
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="取消会员">
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="会员充值">
                        </div>
                        <div class="col-md-3 col-md-offset-1">
                                <img alt="图标" src="/Hotel/img/home-icon.png" class="btn-icon"
                                        title="会员信息">
                        </div>       
                </div>
        </div>
        
        <%@include file="/pages/common/footer.html" %>
        <script>
        function register() {
            $.ajax({
                url: "/Hotel/register.action",
                method:'post',
                success:function(result) {
                    alert(result);
                }
            });
        }
        </script>
</body>
</html>
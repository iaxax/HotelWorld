<%@page pageEncoding="utf-8" %>

<div class="text-center" id="registerForm" style="margin-top: 200px;">
        <h4 class="text-center input-gap">会员登录</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <input type="text" class="form-control input-gap"  placeholder="账号" id="id">
                <input type="password" class="form-control input-gap"  placeholder="密码" id="pw">
                <button class="form-control input-gap btn btn-success"
                         onclick="memberLogin();">登录</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="insert('#intro', '/Hotel/pages/intro.jsp');">取消</button>
        </div>
</div>

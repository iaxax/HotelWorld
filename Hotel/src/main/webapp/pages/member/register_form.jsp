<%@page pageEncoding="utf-8" %>

<div class="text-center" id="registerForm" style="margin-top: 80px;">
        <h4 class="text-center input-gap">会员办理</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <input type="text" class="form-control input-gap"  placeholder="姓名" id="name">
                <input type="text" class="form-control input-gap"  placeholder="身份证号" id="idNum">
                <input type="text" class="form-control input-gap"  placeholder="手机" id="phone">
                <input type="password" class="form-control input-gap"  placeholder="密码" id="pw">
                <input type="password" class="form-control input-gap"  placeholder="密码确认" id="pwEnsure">
                <button class="form-control input-gap btn btn-success"
                         onclick="registerValidate();">提交</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="insert('#intro', '/Hotel/pages/intro.jsp');">取消</button>
        </div>
</div>

<%@page pageEncoding="utf-8" %>

<div class="text-center" id="activateForm">
        <h4 class="text-center input-gap">会员激活</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <input type="text" class="form-control input-gap"  placeholder="充值金额(至少1000)" id="money">
                <input type="text" class="form-control input-gap"  placeholder="银行卡号" id="id">
                <input type="password" class="form-control input-gap"  placeholder="银行密码" id="pw">
                <button class="form-control input-gap btn btn-success"
                         onclick=";">激活</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="window.location = '/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

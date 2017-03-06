<%@page pageEncoding="utf-8" %>

<div class="text-center" id="rechargeForm">
        <h4 class="text-center input-gap">会员充值</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <input type="text" class="form-control input-gap"  placeholder="充值金额(至少50)" id="money">
                <input type="text" class="form-control input-gap"  placeholder="银行卡号" id="id">
                <input type="password" class="form-control input-gap"  placeholder="银行卡密码" id="pw">
                <button class="form-control input-gap btn btn-success"
                         onclick="rechargeValidate();">确定</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="window.location = '/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

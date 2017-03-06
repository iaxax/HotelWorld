<%@page pageEncoding="utf-8" %>

<div class="text-center" id="cacelForm">
        <h4 class="text-center input-gap">取消会员</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <input type="text" class="form-control input-gap"  placeholder="银行卡号,会员卡余额将退还到卡上" id="id">
                <button class="form-control input-gap btn btn-success"
                         onclick="cancelValidate();">确定</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="window.location = '/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

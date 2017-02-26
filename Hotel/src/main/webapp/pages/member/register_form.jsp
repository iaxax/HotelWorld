<%@page pageEncoding="utf-8" %>

<div class="text-center">
        <h4 class="text-center input-gap">会员办理</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <input type="text" class="form-control input-gap"  placeholder="姓名" id="name">
                <input type="text" class="form-control input-gap"  placeholder="身份证号" id="idNum">
                <input type="text" class="form-control input-gap"  placeholder="手机" id="phone">
                <button class="form-control input-gap btn btn-success" onclick="registerValidate();">提交</button>
                <button class="form-control input-gap btn btn-warning">取消</button>
        </div>
</div>
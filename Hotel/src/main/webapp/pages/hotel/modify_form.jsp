<%@ page pageEncoding="utf-8" %>

<input type="text" class="form-control input-gap" placeholder="旅店地址" id="address">
<button class="form-control input-gap btn btn-primary"
        onclick="modifyForm();">确定</button>
<button class="form-control input-gap btn btn-warning"
        onclick="insert('#basicInfo', '/Hotel/pages/hotel/hotel_info.jsp');">取消</button>
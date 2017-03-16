<%@page pageEncoding="utf-8" %>

<h4 class="input-gap text-center" id="tip"></h4>
<input type="text" class="form-control input-gap"  placeholder="姓名" id="name">
<input type="text" class="form-control input-gap"  placeholder="电话" id="phone">
<button class="form-control input-gap btn btn-success"
         onclick="modifyInfo();" id='book'>确认</button>
<button class="form-control input-gap btn btn-warning"
         onclick="insert('#basicInfo', '/Hotel/pages/member/info.jsp');">取消</button>

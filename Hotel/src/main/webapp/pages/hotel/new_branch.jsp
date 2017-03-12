<%@page pageEncoding="utf-8" %>

<div class="text-center" id="branch">
        <h4 class="text-center input-gap">开店申请</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div  class="input-gap"></div>
        
        <div class="input" id = "branchForm">
                 <input type="text" class="form-control input-gap"
                       placeholder="旅馆名称" id="name">
                 <input type="text" class="form-control input-gap"
                       placeholder="旅馆地点" id="address">
                  <input type="text" class="form-control input-gap"
                       placeholder="开张时间" id="openTime" >
                  <button class="form-control input-gap btn btn-success"
                         onclick="submitBranchApply();">确定</button>
                  <button class="form-control input-gap btn btn-warning"
                         onclick="window.location='/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

<script>
$('#openTime').datepicker({format: "yyyy-mm-dd"});
</script>
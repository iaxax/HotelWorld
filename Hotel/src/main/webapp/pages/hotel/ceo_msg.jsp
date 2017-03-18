<%@page pageEncoding="utf-8" %>

<div class="container input-gap">
        <h4 class="text-center input-gap">
                待审批开店申请<span style="color:green; display: none;" id="branchTitle">(没有待审批请求)</span>
        </h4>
        <h4 class="input-gap text-center" id="branchTip"></h4>
        <div class="row" id="branchReq"></div>
</div>

<div class="container input-gap">
        <h4 class="text-center input-gap">
                待审批计划发布申请<span style="color:green; display: none;" id="planTitle">(没有待审批请求)</span>
        </h4>
        <h4 class="input-gap text-center" id="planTip"></h4>
        <div class="row" id="planReq"></div>
</div>

<div class="container input-gap">
        <h4 class="text-center input-gap">
                待审批信息修改申请<span style="color:green; display: none;" id="infoTitle">(没有待审批请求)</span>
        </h4>
        <h4 class="input-gap text-center" id="infoTip"></h4>
        <div class="row" id="infoReq"></div>
</div>

<script>
getRequest();
</script>
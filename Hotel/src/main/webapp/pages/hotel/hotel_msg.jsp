<%@ page pageEncoding="utf-8" %>

<div class="container input-gap">
        <h4 class="text-center input-gap">
                开店申请<span style="color: green; display: none" id="branchTitle">(当前没有记录)</span>
        </h4>
        <div class="row" id="branchApply"></div>        
</div>

<div class="container input-gap">
        <h4 class="text-center input-gap">
                计划发布申请<span style="color: green; display: none" id="planTitle">(当前没有记录)</span>
        </h4>
        <div class="row" id="planApply"></div>        
</div>

<div class="container input-gap">
        <h4 class="text-center input-gap">
                信息修改申请<span style="color: green; display: none" id="infoTitle">(当前没有记录)</span>
        </h4>
        <div class="row" id="infoApply"></div>        
</div>

<script>
getBranchInform();
getPlanInform();
getInfoInform();
</script>
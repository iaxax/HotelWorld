<%@page pageEncoding="utf-8" %>

<div class="text-center" id="plan">
        <h4 class="text-center input-gap">发布计划</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div  class="input-gap"></div>
        
        <div class="input" id = "planForm">
                 <select class="form-control input-gap" id="roomNum"></select>
                 <input type="text" class="form-control input-gap"
                       placeholder="房间价格" id="roomPrice">
                  <button class="form-control input-gap btn btn-success"
                         onclick="submitPlan();">确定</button>
                  <button class="form-control input-gap btn btn-warning"
                         onclick="window.location='/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

<script>
getAllRooms();
</script>
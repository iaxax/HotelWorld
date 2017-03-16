<%@page pageEncoding="utf-8" %>

<div class="text-center" id="roomBook" style="margin-top: 80px;">
        <h4 class="text-center input-gap">房间预订</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <select class="form-control input-gap" id="hotel" onchange="showRooms($('#room'), $('#hotel').val());"></select>
                <select class="form-control input-gap" id="room"></select>
                <input type="text" class="form-control input-gap"  placeholder="入住时间" id="resideDate">
                <input type="text" class="form-control input-gap"  placeholder="预订天数" id="days">
                <button class="form-control input-gap btn btn-success"
                         onclick="roomBookValidate();" id='book'>预订</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="window.location = '/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

<script>
getInfo();
$('#resideDate').datepicker({format: "yyyy-mm-dd"});
</script>
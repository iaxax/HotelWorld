<%@page pageEncoding="utf-8" %>

<div class="text-center" id="roomBook" style="margin-top: 80px;">
        <h4 class="text-center input-gap">房间退订</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div class="input">
                <select class="form-control input-gap" id="bookRooms"></select>
                <button class="form-control input-gap btn btn-success"
                         onclick="submitRoomCancel();" id='book'>确定</button>
                <button class="form-control input-gap btn btn-warning"
                         onclick="window.location = '/Hotel/pages/member.jsp';">取消</button>
        </div>
</div>

<script>
getBookRooms();
</script>
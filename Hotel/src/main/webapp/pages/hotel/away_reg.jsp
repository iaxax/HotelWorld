<%@page pageEncoding="utf-8" %>

<div class="text-center" id="reside">
        <h4 class="text-center input-gap">离店登记</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <div  class="input-gap"></div>
        
        <div class="input" id = "awayForm">
                <input type="text" class="form-control input-gap" 
                      placeholder="身份证号" id="id" onkeyup="onIdKeyup(this.value);">
                <select class="form-control input-gap" id="room"></select>
                 <button class="form-control input-gap btn btn-success disabled"
                        onclick="awayRegister();" id="submit">确定</button>
                 <button class="form-control input-gap btn btn-warning" 
                        onclick="window.location = \'/Hotel/pages/member.jsp\';">取消</button>
        </div>
</div>

<script>

</script>
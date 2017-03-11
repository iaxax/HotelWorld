<%@page pageEncoding="utf-8" %>

<div class="text-center" id="reside">
        <h4 class="text-center input-gap">入住登记</h4>
        <h4 class="input-gap text-center" id="tip"></h4>
        <h4 class="input-gap text-center" id="price"></h4>
        <div  class="input-gap"></div>
        
        <div class="btn-group" data-toggle="buttons">
	  <label class="btn btn-default disabled">
	    是否提前预订了房间
	  </label>
	  <label class="btn btn-success" style="margin-left:30px;" onclick='showForm(true);'>
	    <input type="checkbox" autocomplete="off">是
	  </label>
	  <label class="btn btn-warning" style="margin-left:30px;" onclick='showForm(false);'>
	    <input type="checkbox" autocomplete="off"> 否
	  </label>
	</div>
	
        <div class="input" id = "resideForm">
                <div id="bookForm">
                        <input type="text" class="form-control input-gap" onkeyup="onKeyup(this.value);"
                              placeholder="会员卡号" id="memId">
                         <button class="form-control input-gap btn btn-success disabled"
                                onclick="submitReside();" id="submit">确定</button>
                         <button class="form-control input-gap btn btn-warning"
                                onclick="window.location = \'/Hotel/pages/member.jsp\';">取消</button>
                </div>
                
        </div>
</div>

<script>
getAvailableRooms();
</script>
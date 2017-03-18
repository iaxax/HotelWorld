<%@ page pageEncoding="utf-8" %>

<div class="container">
	<div class="col-md-4 col-md-offset-4">
	        <h4 class="text-center input-gap">基本信息</h4>
	        <h4 class="text-center input-gap" id="tip"></h4>
	        <div class="info-card" id="basicInfo"></div>
		<div class="input-gap"></div>
		<div class="input-gap"></div>
		<div class="input-gap"></div>
	</div>
	
	<div class="col-md-6 col-md-offset-3 input-gap" style="height: 500px;" id="recordChart"></div>
	<div class="input-gap"></div>
        <div class="col-md-offset-3 col-md-6 input-gap" id="costChart" style="height: 500px;"></div>
</div>



<script>
getHotelBasicInfo();
getStatInfo('/Hotel/getHotelStat.action');
</script>
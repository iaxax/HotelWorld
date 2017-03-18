<%@ page pageEncoding="utf-8" %>

<div class="container">
        <div class="col-md-6 col-md-offset-3 input-gap" style="height: 500px;" id="recordChart"></div>
        <div class="input-gap"></div>
        <div class="col-md-offset-3 col-md-6 input-gap" style="height: 500px;" id="costChart" ></div>
</div>

<script>
getStatInfo('/Hotel/getHotelGeneralStat.action');
</script>
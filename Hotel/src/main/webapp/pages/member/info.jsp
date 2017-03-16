<%@page pageEncoding="utf-8" %>

<div>
        <div class="row">
                <div class="col-md-4 col-md-offset-1">
                        <h4 class="text-center input-gap">基本信息</h4>
                        <div class="info-card" id="basicInfo"></div>
                </div>
                <div class="col-md-6  input-gap chart" id="memChart"> </div>
        </div>
        
        <div class="input-gap"></div>
        <div class="row">
                <div class="input-gap col-md-6 col-md-offset-3 " style="height: 500px" id="cosumeChart"> </div>
        </div>
        
        <div class="input-gap"></div>
        <div class="row">
                <h4 class="input-gap text-center">
                        入住记录<span style="display:none; color:green;" id="subReside">(当前没有入住记录)</span>
                </h4>
                <div id="resideRecord"></div>
        </div>
        
        <div class="input-gap"></div>
        <div class="row">
                <h4 class="input-gap text-center">
                        预订记录<span style="display:none; color:green;" id="subBook">(当前没有预订记录)</span>
                </h4>
                <div id="bookRecord"></div>
        </div>
        
        <div class="input-gap"></div>
        <div class="row">
                <h4 class="input-gap text-center">
                        退订记录<span style="display:none; color:green;" id="subCancel">(当前没有退订记录)</span>
                </h4>
                <div id="cancelRecord"></div>
        </div>
</div>
<script>
getBasicInfo();
showBookingChart();
showCosumeChart();
getResideRecords();
getBookRecords();
getCancelRecords();
</script>

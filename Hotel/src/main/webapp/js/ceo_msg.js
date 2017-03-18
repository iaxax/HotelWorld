function getId(empId, datetime) {
	var temp1 = datetime.split(' ');
	var temp2 = temp1[1].split(':');
	return empId + temp1[0] + temp2[0] + temp2[1] + temp2[2];
}

function checkBranchApply(isSuccess, empId, applyTime) {
	$.ajax({
		url: '/Hotel/checkBranchRequest.action',
		method: 'post',
		data: {
			"success": isSuccess,
			"empId": empId,
			"applyTime": applyTime
		},
		success: function(result) {
			var tip = $('#branchTip');
			tip.css('color', 'green');
			tip.html(result.msg);
			setTimeout(function() {
				tip.html('');
			}, 6000);
			$('#' + getId(empId, applyTime)).hide();
		}
	});
}

function checkPlanApply(isSuccess, hotel, room, applyTime, id) {
	$.ajax({
		url: '/Hotel/checkPlanRequest.action',
		method: 'post',
		data: {
			"success": isSuccess,
			"hotelName": hotel,
			"roomNum": room,
			"applyTime": applyTime
		},
		success: function(result) {
			var tip = $('#planTip');
			tip.css('color', 'green');
			tip.html(result.msg);
			setTimeout(function() {
				tip.html('');
			}, 6000);
			$('#' + id).hide();
		}
	});
}

function checkInfoApply(isSuccess, empId, applyTime) {
	$.ajax({
		url: '/Hotel/checkInfoRequest.action',
		method: 'post',
		data: {
			"success": isSuccess,
			"empId": empId,
			"applyTime": applyTime
		},
		success: function(result) {
			var tip = $('#infoTip');
			tip.css('color', 'green');
			tip.html(result.msg);
			setTimeout(function() {
				tip.html('');
			}, 6000);
			
			var id = getId(empId, applyTime);
			$('#' + id).hide();
		}
	});
}

function getBranchRequest() {
	$.ajax({
		url: '/Hotel/getBranchRequest.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			if (len == 0) {
				$('#branchTitle').show();
				return;
			}
			
			$('#branchTitle').hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var branch = result[i];
				var id =  getId(branch.empId, branch.requestDate);
				doc += '<div class="col-md-offset-1 col-md-4 card" id="' + id  + '">'
	                + '<p>员工工号： ' + branch.empId + '</p>'
	                + '<p>申请时间： ' + branch.requestDate + '</p>'
	                + '<p>新店名称： ' + branch.hotelName + '</p>'
	                + '<p>新店地址： ' + branch.hotelAddr + '</p>'
	                + '<p>开店日期： ' + branch.openDate + '</p>'
	                + '<br/>'
	                + '<button class="btn btn-success btn-gap"'
	                + 'onclick="checkBranchApply(true, \'' + branch.empId + '\', \'' + branch.requestDate + '\');">通过</button>'
	                + '<button class=" btn btn-warning btn-gap"'
	                + 'onclick="checkBranchApply(false, \'' + branch.empId + '\', \'' + branch.requestDate + '\');">拒绝</button></div>';
			}
			$('#branchReq').html(doc);
		}
	});
}

function getPlanRequest() {
		$.ajax({
			url: '/Hotel/getPlanRequest.action',
			method: 'get',
			success: function(result) {
				var len = result.length;
				if (len == 0) {
					$('#planTitle').show();
					return;
				}
				
				$('#planTitle').hide();
				var doc = '';
				for (var i = 0; i < len; ++i) {
					var plan = result[i];
					var empId = plan.empId;
					var date = plan.requestDate;
					var id = getId(empId, date) ;
					var hotel = plan.hotelName;
					var room = plan.roomNum;
					doc += '<div class="col-md-offset-1 col-md-4 card" id ="' + id + '">'
		                + '<p>员工工号： ' + empId + '</p>'
		                + '<p>申请时间： ' + date + '</p>'
		                + '<p>旅店名称： ' +  hotel+ '</p>'
		                + '<p>房间号码： ' +  room + '</p>'
		                + '<p>房间价格： ' + plan.roomPrice + '</p>'
		                + '<br/>'
		                + '<button class="btn btn-success btn-gap"'
		                + 'onclick="checkPlanApply(true, \'' + hotel + '\',\'' + room+'\',\''+ date + '\',\'' +  id + '\');">通过</button>'
		                + '<button class=" btn btn-warning btn-gap"'
		                + 'onclick="checkPlanApply(true, \'' + hotel + '\',\'' + room+'\',\''+ date + '\',\'' +  id + '\');">拒绝</button></div>';
				}
				
				$('#planReq').html(doc);
			}
		});
}

function getInfoRequest() {
	$.ajax({
		url: '/Hotel/getInfoRequest.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			if (len == 0) {
				$('#infoTitle').show();
				return;
			}
			
			$('#infoTitle').hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var info = result[i];
				var empId = info.empId;
				var time = info.applyTime;
				var id = getId(empId, time) ;
				var address = info.address;
				doc += '<div class="col-md-offset-1 col-md-4 card" id ="' + id + '">'
	                + '<p>员工工号： ' + empId + '</p>'
	                + '<p>申请时间： ' + time + '</p>'
	                + '<p>旅店地址： ' +  address + '</p>'
	                + '<br/>'
	                + '<button class="btn btn-success btn-gap"'
	                + 'onclick="checkInfoApply(true, \''  + empId + '\',\'' + time + '\');">通过</button>'
	                + '<button class=" btn btn-warning btn-gap"'
	                + 'onclick="checkInfoApply(false, \''  + empId + '\',\'' + time + '\');">拒绝</button></div>';
			}
			
			$('#infoReq').html(doc);
		}
	});
}

function getRequest() {
	getBranchRequest();
	getPlanRequest();
	getInfoRequest();
}
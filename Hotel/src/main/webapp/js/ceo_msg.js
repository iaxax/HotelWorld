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
			var tip = $('#tip');
			tip.css('color', 'blue');
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
			var tip = $('#tip');
			tip.css('color', 'blue');
			tip.html(result.msg);
			setTimeout(function() {
				tip.html('');
			}, 6000);
			$('#' + id).hide();
		}
	});
}

function getRequest() {
	$.ajax({
		url: '/Hotel/getBranchRequest.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var branch = result[i];
				var id =  getId(branch.empId, branch.requestDate);
				doc += '<div class="col-md-offset-1 col-md-4 card" id="' + id  + '">'
	                + '<p>申请类型： 开店申请</p>'
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
			
			$.ajax({
				url: '/Hotel/getPlanRequest.action',
				method: 'get',
				success: function(result) {
					for (var i = 0; i < result.length; ++i) {
						var plan = result[i];
						var empId = plan.empId;
						var date = plan.requestDate;
						var id = getId(empId, date) ;
						var hotel = plan.hotelName;
						var room = plan.roomNum;
						doc += '<div class="col-md-offset-1 col-md-4 card" id ="' + id + '">'
			                + '<p>申请类型： 计划发布申请</p>'
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
					
					$('#ceoMsg').html(doc);
				}
			});
			
			
		}
	});
}
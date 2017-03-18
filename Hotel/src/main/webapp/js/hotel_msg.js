function getBranchInform() {
	$.ajax({
		url: '/Hotel/getBranchInform.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			var title = $('#branchTitle');
			if (len == 0) {
				title.show();
				return;
			}
			
			title.hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var b = result[i];
				var res = '';
				if (b.state == '通过') {
					res = '<span style="color: green;">通过</span>';
				}
				else if (b.state == '拒绝') {
					res = '<span style="color: red;">拒绝</span>';
				}
				else if (b.state == '未读') {
					res = '<span style="color: orange;">未读</span>';
				}
				
				doc +=
					'<div class="col-md-4 record-card">'
					+ '<p>旅店名称： ' + b.hotelName + '</p>'
					+ '<p>旅店地址： ' + b.hotelAddr + '</p>'
					+ '<p>开店日期： ' + b.openDate + '</p>'
					+ '<p>申请时间： ' + b.requestDate + '</p>'
					+ '<p>审批结果： ' + res + '</p>'
					+ '</div>';
			}
			
			$('#branchApply').html(doc);
		}
	});
}

function getPlanInform() {
	$.ajax({
		url: '/Hotel/getPlanInform.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			var title = $('#planTitle');
			if (len == 0) {
				title.show();
				return;
			}
			
			title.hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var b = result[i];
				var res = '';
				if (b.state == '通过') {
					res = '<span style="color: green;">通过</span>';
				}
				else if (b.state == '拒绝') {
					res = '<span style="color: red;">拒绝</span>';
				}
				else if (b.state == '未读') {
					res = '<span style="color: orange;">未读</span>';
				}
				
				doc +=
					'<div class="col-md-4 record-card">'
					+ '<p>旅店名称： ' + b.hotelName + '</p>'
					+ '<p>房间号码： ' + b.roomNum + '</p>'
					+ '<p>房间价格： ' + b.roomPrice + '</p>'
					+ '<p>申请时间： ' + b.requestDate + '</p>'
					+ '<p>审批结果： ' + res + '</p>'
					+ '</div>';
			}
			
			$('#planApply').html(doc);
		}
	});	
}

function getInfoInform() {
	$.ajax({
		url: '/Hotel/getInfoInform.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			var title = $('#infoTitle');
			if (len == 0) {
				title.show();
				return;
			}
			
			title.hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var b = result[i];
				var res = '';
				if (b.state == '通过') {
					res = '<span style="color: green;">通过</span>';
				}
				else if (b.state == '拒绝') {
					res = '<span style="color: red;">拒绝</span>';
				}
				else if (b.state == '未读') {
					res = '<span style="color: orange;">未读</span>';
				}
				
				doc +=
					'<div class="col-md-4 record-card">'
					+ '<p>旅店名称： ' + b.name + '</p>'
					+ '<p>旅店地址： ' + b.address + '</p>'
					+ '<p>申请时间： ' + b.applyTime + '</p>'
					+ '<p>审批结果： ' + res + '</p>'
					+ '</div>';
			}
			
			$('#infoApply').html(doc);
		}
	});	
}
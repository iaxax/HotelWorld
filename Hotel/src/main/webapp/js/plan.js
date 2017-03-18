function submitPlan() {
	var tip = $('#tip');
	
	var num = $('#roomNum').val();
	if (num.length == 0) {
		showError(tip, '请填写房间号');
		return;
	}
	
	var price = $('#roomPrice').val();
	var ppattern = /^[1-9]\d{0,}$/;
	if (ppattern.test(price) == false) {
		showError(tip, '房间价格应该为正整数');
		return;
	}
	
	$.ajax({
		url: '/Hotel/publishPlan.action',
		method: 'post',
		data: {
			"roomNum": num,
			"roomPrice": price
		},
		success: function(result) {
			var tip = $('#tip');
			if (result.success) {
				showSuccess(tip, result.msg);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function getAllRooms() {
	$.ajax({
		url: '/Hotel/getAllRooms.action',
		method: 'get',
		success: function(result) {
			var doc = '';
			for (var i = 0; i < result.length; ++i) {
				doc += '<option>' + result[i] + '</option>';
			}
			
			$('#roomNum').html(doc);
		}
	});
}
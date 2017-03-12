function submitBranchApply() {
	var tip = $('#tip');
	
	var name = $('#name').val();
	if (name.length == 0) {
		showError(tip, '旅馆名称不能为空');
		return;
	}
	
	var addr = $('#address').val();
	if (addr.length == 0) {
		showError(tip, '旅馆地址不能为空');
		return;
	}
	
	var date = $('#openTime').val();
	var dpattern = /^\d{4}-\d{2}-\d{2}$/;
	if (dpattern.test(date) == false) {
		showError(tip, '请输入正确日期');
		return;
	}
	
	$.ajax({
		url: '/Hotel/branchApply.action',
		method: 'post',
		data: {
			"hotelName": name,
			"hotelAddr": addr,
			"openDate": date
		},
		success: function(result) {
			if (result.success) {
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function(){
					window.location = '/Hotel/pages/manager.jsp';
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}
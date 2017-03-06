function submitCancel(tip, vo) {
	$.ajax({
		url: '/Hotel/cancel.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				window.location = '/Hotel/logout.action';
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function cancelValidate() {
	var tip = $('#tip');
	
	var account = $('#id').val();
	if (account.length == 0) {
		showError(tip, "银行账号不能为空");
		return;
	}
	
	var vo = {
			"accountId": account
	};
	submitCancel(tip, vo);
}
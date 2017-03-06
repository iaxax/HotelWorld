function submitRecharge(tip, vo) {
	$.ajax({
		url: '/Hotel/recharge.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				showSuccess(tip, result.msg);
				$('#money').val('');
				$('#id').val('');
				$('#password').val('');
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function rechargeValidate() {
	var tip = $('#tip');
	
	var money = $('#money').val();
	var mnyPattern = /^[1-9]\d{0,}$/;
	if (mnyPattern.test(money) == false) {
		showError(tip, '充值金额应该为数字');
		return;
	}
	if (money < 50) {
		showError(tip, '充值金额至少应该为50');
		return;
	}
	
	var account = $('#id').val();
	if (account.length == 0) {
		showError(tip, '银行账户不能为空');
		return;
	}
	
	var password = $('#pw').val();
	if (password.length == 0) {
		showError(tip, '银行密码不能为空');
		return;
	}
	
	var vo = {
			"money": money,
			"accountId": account,
			"password": password
	};
	
	submitRecharge(tip, vo);
}
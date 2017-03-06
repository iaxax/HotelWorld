function submitActivate(tip, vo) {
	$.ajax({
		url: '/Hotel/activate.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				showSuccess(tip, '账号已经成功激活');
				$('#money').val('');
				$('#id').val('');
				$('#pw').val('');
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function activateValidate() {
	var tip = $('#tip');
	
	var money = $('#money').val();
	var mnyPattern = /^[1-9]\d{0,}$/;
	if (mnyPattern.test(money) == false){
		showError(tip, "充值金额应该为数字");
		return;
	}
	if (money < 1000) {
		showError(tip, "充值金额至少为1000");
		return;
	}
	
	var account = $('#id').val();
	if (account.length == 0) {
		showError(tip, "银行账号不能为空");
		return;
	}
	
	var password = $('#pw').val();
	if (password.length == 0) {
		showError(tip, "银行密码不能为空");
		return;
	}
	
	var vo = {
			"money": money,
			"accountId": account,
			"accountPw": password
	};
	submitActivate(tip, vo);
}
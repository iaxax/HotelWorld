
function submitRegister(tip, vo) {
	$.ajax({
		url: '/Hotel/register.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				showSuccess(tip, result.msg);
				$('#name').val('');
				$('#idNum').val('');
				$('#phone').val('');
				$('#pw').val('');
				$('#pwEnsure').val('');
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function registerValidate() {
	var tip = $('#tip');
	
	var name = $('#name').val();
	var len = name.length;
	if (len == 0) {
		showError(tip, '姓名不能为空');
		return;
	}
	if (len > 10) {
		showError(tip, '姓名长度不能大于10个字符');
		return;
	}
	
	var idNum = $('#idNum').val();
	var idRegex = /^(\d{17}[Xx\d])$/;
	if (idRegex.test(idNum) == false) {
		showError(tip, '请填写正确的身份证号');
		return;
	}
	
	var phone = $('#phone').val();
	var pRegex = /^\d{11}$/;
	if (pRegex.test(phone) == false) {
		showError(tip, '请填写正确的手机号');
		return;
	}
	
	var pw = $('#pw').val();
	if (pw.length == 0) {
		showError(tip, '密码不能为空');
		return;
	}
	
	var pwEnsure = $('#pwEnsure').val();
	if (pwEnsure.length == 0) {
		showError(tip, '密码确认不能为空');
		return;
	}
	
	if (pw !== pwEnsure) {
		showError(tip, '密码与密码确认不一致');
		return;
	}
	
	var vo = {
			"name": name,
			"idCard": idNum,
			"phone": phone,
			"password": pw
	}
	
	submitRegister(tip, vo);
}
function showRegisterForm(id) {
	$.get('/Hotel/pages/member/register_form.jsp', function(result) {
		$(id).html(result);
	});
}

function showActivateForm(id) {
	$.get('/Hotel/pages/member/activate_form.jsp', function(result) {
		$(id).html(result);
	});
}

function showRechargeForm(id) {
	$.get('/Hotel/pages/member/recharge_form.jsp', function(result) {
		$(id).html(result);
	});
}

function showCancelForm(id) {
	$.get('/Hotel/pages/member/cancel_form.jsp', function(result) {
		$(id).html(result);
	});
}

function showInfo(id) {
	$.get('/Hotel/pages/member/info.jsp', function(result) {
		$(id).html(result);
	});
}

function showError(tag, tip) {
	tag.css('color', 'red');
	tag.html(tip);
	setTimeout(function() {
		tag.html('');
	}, 2000);
}

function showSuccess(tag, tip) {
	tag.css('color', 'green');
	tag.html(tip);
	setTimeout(function() {
		tag.html('');
	}, 2000);
}

function submitRegister(tip) {
	var vo = {
			"name": $('#name').val(),
			"idCard": $('#idNum').val(),
			"phone": $('#phone').val()
	}
	
	$.ajax({
		url: '/Hotel/register.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				showSuccess(tip, result.msg);
				$('#name').html('');
				$('#idNum').html('');
				$('#phone').html('');
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
	
	submitRegister(tip);
}

function submitLogin(tip, id, pw, action) {
	var vo = {
			"id": id,
			"pw": pw
	};
	
	$.ajax({
		url: action,
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					tip.html('');
					window.location = result.url;
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function inputValidate(tip, id, pw) {
	if (id.length == 0) {
		showError(tip, '账号不能为空');
		return false;
	}
	
	if (pw.length == 0) {
		showError(tip, '密码不能为空');
		return false;
	}
	
	return true;
}

function emplLogin() {
	var tip = $('#tip');
	var id = $('#id').val();
	var pw = $('#pw').val();
	
	if (inputValidate(tip, id, pw) == false) {
		return;
	}
	
	submitLogin(tip, id, pw, '/Hotel/emplLogin.action');
}

function memberLogin() {
	var tip = $('#tip');
	var id = $('#id').val();
	var pw = $('#pw').val();
	
	if (inputValidate(tip, id, pw) == false) {
		return;
	}
	
	submitLogin(tip, id, pw, '/Hotel/memberLogin.action');
}

function submitLogin(tip, id, pw) {
	var vo = {
			"id": id,
			"pw": pw
	};
	
	$.ajax({
		url: '/Hotel/login.action',
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

function loginValidate(tip) {
	var id = $('#id').val();
	if (id.length == 0) {
		showError(tip, '账号不能为空');
		return;
	}
	
	var pw = $('#pw').val();
	if (pw.length == 0) {
		showError(tip, '密码不能为空');
		return;
	}
	
	submitLogin(tip, id, pw)
}
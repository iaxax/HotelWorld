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
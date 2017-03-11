function awayRegister() {
	$.ajax({
		url: '/Hotel/away.action',
		method: 'post',
		data: {
			"roomNum": $('#room').val(),
			"idNum": $('#id').val()
		},
		success: function(result) {
			var tip = $('#tip');
			if (result.success) {
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					window.location = '/Hotel/pages/staff.jsp';
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function onIdKeyup(id) {
	var idpattern = /^\d{17}[\dxX]$/;
	if (idpattern.test(id)) {
		$.ajax({
			url: '/Hotel/getResideRooms.action',
			method: 'post',
			data: {
				"idNum": id
			},
			success: function(result) {
				var tip = $('#tip');
				var len = result.length;
				if (len == 0) {
					showError(tip, '该客户没有在此店的入店登记记录');
					$('#submit').addClass('disabled');
					return;
				}
				
				var doc = '';
				for (var i = 0; i < len; ++i) {
					doc += '<option>' + result[i] + '</option>';
				}
				
				$('#room').html(doc);
				$('#submit').removeClass('disabled');
			}
		});
	}
	else {
		$('#submit').addClass('disabled');
		$('#room').html('');
	}
}
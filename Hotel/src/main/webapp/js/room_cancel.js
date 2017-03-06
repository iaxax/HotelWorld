function submitRoomCancel() {
	var info = $('#bookRooms').val();
	var temp = info.split('-');
	$.ajax({
		url: '/Hotel/roomCancel.action',
		method: 'post',
		data: {
			"hotelName": temp[0],
			"room": temp[1]
		},
		success: function (result) {
			var tip = $('#tip');
			if (result.success) {
				$('#' + info).remove();
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					window.location = '/Hotel/pages/member.jsp'
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function getBookRooms() {
	$.ajax({
		url: '/Hotel/getBookRooms.action',
		method: 'get',
		success: function(result) {
			var doc = '';
			for (var i = 0; i < result.length; ++i) {
				doc += '<option id = "' + result[i] + '">' + result[i] + '</option>';
			}
			$('#bookRooms').html(doc);
		}
	});
}
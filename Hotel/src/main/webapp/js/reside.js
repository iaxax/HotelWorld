function showRoomPrice(roomNum) {
	$.ajax({
		url: '/Hotel/getPrice.action',
		method: 'post',
		data: {
			"roomNum": roomNum
		},
		success: function(result) {
			var price = $('#price');
			price.css('color', 'green');
			price.html('房间价格为' + result + '元/日');
		}
	});
}

function showForm(isBookRoom) {
	var form = $('#bookForm');
	if (isBookRoom) {
		form.html(
				'<input type="text" class="form-control input-gap" onkeyup="onKeyup(this.value);"'
                + ' placeholder="会员卡号" id="memId">'
                + '<button class="form-control input-gap btn btn-success disabled"'
                + 'onclick="submitMemberReside();" id="submit">确定</button>'
                + '<button class="form-control input-gap btn btn-warning"'
                + 'onclick="window.location = \'/Hotel/pages/member.jsp\';">取消</button>'
		);
		$('#price').html('');
	}
	else {
		form.html(
				'<input type="text" class="form-control input-gap"  placeholder="身份证" id="idCard">'
                + '<select class="form-control input-gap" id="room" onchange="showRoomPrice(this.value);"></select>'
                + '<input type="text" class="form-control input-gap"  placeholder="预订天数" id="days">'
                + '<button class="form-control input-gap btn btn-success disabled"'
                + 'onclick="submitNormalReside();" id="submit">确定</button>'
                + '<button class="form-control input-gap btn btn-warning"'
                + 'onclick="window.location = \'/Hotel/pages/member.jsp\';">取消</button>'
		);
		getAvailableRooms();
		showRoomPrice($('#room').first().val());
		$('#submit').removeClass('disabled');
	}
}

var rooms = null;
$.ajax({
	url: '/Hotel/getRooms.action',
	method: 'get',
	sync: true,
	success: function(result) {
		rooms = result;
	}
});

function getAvailableRooms() {
	var doc = '';
	for (var i = 0; i < rooms.length; ++i) {
		doc += '<option>' + rooms[i] + '</option>'
	}
	$('#room').html(doc);
}

function showBookInfo(id) {
	$.ajax({
		url: '/Hotel/getBookRoomsAtReside.action',
		method: 'get',
		data: {"memberId": id},
		success: function(result) {
			if (result.length == 0) {
				showError($('#tip'), '该会员没有预订房间');
				$('#submit').addClass('disabled');
				return;
			}
			
			var room = $('#bookForm');
			doc = 
				'<input type="text" class="form-control input-gap" onkeyup="onKeyup(this.value);"'
                + 'value = "' + id + '" placeholder="会员卡号" id="memId">'
				+ '<select class="form-control input-gap" id="room">';
			for (var i = 0; i < result.length; ++i) {
				doc += '<option onchange="showRoomPrice(this.value.split(\'-\')[1]);">' + result[i] + '</option>';
			}
			doc += '</select>';
			doc += '<button class="form-control input-gap btn btn-success disabled"'
		            + 'onclick="submitMemberReside();" id="submit">确定</button>'
		            + '<button class="form-control input-gap btn btn-warning"'
		            + 'onclick="window.location = \'/Hotel/pages/member.jsp\';">取消</button>';
			room.html(doc);
			$('#submit').removeClass('disabled');
			showRoomPrice(result[0].split('-')[1]);
		}
	});
}

function onKeyup(val) {
	if (val.length == 7) {
		var pat = /^\d{7}$/;
		if (pat.test(val) == false) {
			showError($('#tip'), "会员卡不存在");
			return;
		}
		
		showBookInfo(val);
	}
	else {
		var room = $('#room');
		if (room != null) {
			room.html('');
		}
		$('#submit').addClass('disabled');
	}
}

function submitReside(vo) {
	$.ajax({
		url: '/Hotel/reside.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				var tip = $('#tip');
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					window.location = '/Hotel/pages/staff.jsp';
				}, 2000);
			}
			else {
				showError($('#tip'), result.msg);
			}
		}
	});
}

function submitMemberReside() {
	var temp = $('#room').val().split('-');
	var vo = {
			"memberId": $('memId').val(),
			"roomNum": temp[1],
	};

	submitReside(vo);
}

function submitNormalReside() {
	var tip = $('#tip');
	var id = $('#idCard').val();
	var idPattern = /^\d{17}[\dXx]$/;
	if (idPattern.test(id) == false) {
		showError(tip, '请输入正确的身份证号');
		return;
	}
	
	var room = $('#room').val();
	if (room.length == 0) {
		showError(tip, '已经没有空余房间了');
		return;
	}
	
	var days = $('#days').val();
	var dpattern = /^[1-9]\d{0,}$/;
	if (dpattern.test(room) == false) {
		showError(tip, '预订天数应该为正整数');
		return;
	}
	
	var vo = {
			"registrant": id,
			"roomNum": room,
			"days": days
	}
	submitReside(vo);
}
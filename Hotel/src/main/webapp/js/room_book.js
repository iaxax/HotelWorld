var info = null;
$.ajax({
        url: '/Hotel/getInfo.action',
        method: 'get',
        sync: true,
        success: function(result) {
                info = result;
        }
});

function showRooms(element, hotelName) {
    var rooms = info[hotelName];
    var doc = '';
    for (var i = 0; i < rooms.length; ++i) {
            doc += '<option>' + rooms[i] + '</option>';
    }
    element.html(doc);
}

function submitRoomBook(tip, vo) {
	$.ajax({
		url: '/Hotel/roomBook.action',
		method: 'post',
		data: vo,
		success: function(result) {
			if (result.success) {
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					window.location = '/Hotel/pages/member.jsp';
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function roomBookValidate() {
	var tip = $('#tip');
	
	var days = $('#days').val();
	var dayPattern = /^[1-9]\d{0,}$/;
	if (days.length == 0) {
		showError(tip, '请输入预订天数');
		return;
	}
	if (dayPattern.test(days) == false) {
		showError(tip, '预订天数应该为正整数');
		return;
	}
	
	var date = $('#resideDate').val();
	var datePattern = /\d{4}-\d{2}-\d{2}/;
	if (datePattern.test(date) == false) {
		showError(tip, '请输入有效日期');
		return;
	}
	var today = new Date();
	today.setHours(0, 0, 0, 0);
	var temp = date.split('-');
	var d = new Date(temp[0], temp[1] - 1, temp[2]);
	d.setHours(0, 0, 0);
	if (d < today) {
		showError(tip, '入住日期不能小于今天');
		return;
	}
	
	var vo = {
			"hotelName": $('#hotel').val(),
			"room": $('#room').val(),
			"resideDate": date,
			"days": days
	};
	
	submitRoomBook(tip, vo);
}

function getInfo() {
            var len = info.length;
            var hotel = $('#hotel');
            var room = $('#room');
            var book = $('#book');
            if (len == 0) {
                   hotel.html('<option>无可预订旅馆</option>');
                   room.html('<option>无可预订房间</option>');
                   book.addClass('disabled');
            }
            else {
                    var hotelDoc = '';
                    var hotels = Object.keys(info);
                    for (var i = 0; i < hotels.length; ++i) {
                            hotelDoc += '<option onchange="showRooms($(\'#room\'), \'' + hotels[i] +' \');">' + hotels[i] + '</option>';
                    }
                    hotel.html(hotelDoc);

                	showRooms(room, hotels[0]);
            }
}
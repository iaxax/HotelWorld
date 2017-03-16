function showBookingChart() {
	$.ajax({
		url: '/Hotel/getMemberData.action',
		method: 'get',
		success: function(result) {
			var resideData = result[0];
			var bookData = result[1];
			var cancelData = result[2];
			var year = Object.keys(resideData);
			
			var reside = [];
			for (var y in resideData) {
				reside.push(resideData[y]);
			}
			
			var book = [];
			for (var y in bookData) {
				book.push(bookData[y]);
			}
			
			var cancel = [];
			for (var y in cancelData) {
				cancel.push(cancelData[y]);
			}
			
			var chart = echarts.init(document.getElementById('memChart'));
			var option = {
				    title: {
				        text: '住房统计',
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['入住次数', '预订次数', '退订次数']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {show: true, type: ['stack', 'tiled']},
				            saveAsImage: {show: true}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: year
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [{
				        name: '入住次数',
				        type: 'line',
				        smooth: true,
				        data: reside
				    },
				    {
				        name: '预订次数',
				        type: 'line',
				        smooth: true,
				        data: book
				    },
				    {
				        name: '退订次数',
				        type: 'line',
				        smooth: true,
				        data: cancel
				    }
			    ]
			};
			chart.setOption(option);
		}
	});
}

function showCosumeChart() {
	$.ajax({
		url: '/Hotel/getMemberFinanceStat.action',
		method: 'get',
		success: function(result) {
			var year = Object.keys(result);
			var data = [];
			for (var i in result) {
				data.push(result[i]);
			}
			
			var chart = echarts.init(document.getElementById('cosumeChart'));
			var option = {
				    title: {
				        text: '消费统计',
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['消费']
				    },
				    toolbox: {
				        show: true,
				        feature: {
				            magicType: {show: true, type: ['stack', 'tiled']},
				            saveAsImage: {show: true}
				        }
				    },
				    xAxis: {
				        type: 'category',
				        boundaryGap: false,
				        data: year
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [{
				        name: '消费',
				        type: 'line',
				        smooth: true,
				        data: data
				    }]
			};
			chart.setOption(option);
		}
	});
}

function showModForm() {
	insert('#basicInfo', '/Hotel/pages/member/modify_form.jsp');
}

function modifyInfo() {
	$.ajax({
		url: '/Hotel/modifyInfo.action',
		method: 'post',
		data: {
			"name": $('#name').val(),
			"phone": $('#phone').val()
		},
		success: function(result) {
			if (result.success) {
				var tip = $('#tip');
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					insert('#basicInfo', '/Hotel/pages/member/info.jsp');
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function getBasicInfo() {
	$.ajax({
		url: '/Hotel/getBasicInfo.action',
		method: 'get',
		success: function(result) {
			doc = 
		            '<p>会员ID   ：' + result.memberId + '</p>'
		            + '<p>会员姓名：' + result.name + '</p>'
		            + '<p>身份证号：' + result.idNum + '</p>'
		            + '<p>会员电话：' + result.phone + '</p>'
		            + '<p>会员余额：' + result.balance + '</p>'
		            + '<p>会员消费：' + result.consumption + '</p>'
		            + '<p>会员积分：' + result.points + '</p>'
		            + '<p>会员等级：' + result.rank + '</p>'
		            + '<p>会员状态：' + result.state + '</p>'
		            + '<div class="row input-gap">'
		                    + '<button class="btn btn-primary form-control" onclick="showModForm();">修改</button>'
		           +  '</div>';
			
			$('#basicInfo').html(doc);
		}
	});
}

function getResideRecords() {
	$.ajax({
		url: '/Hotel/getMemberResideRecord.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			if (len == 0) {
				$('#subReside').show();
				return;
			}
			
			$('#subReside').hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var record = result[i];
				doc +=
					'<div class="col-md-4 record-card">'
		                + '<p>旅店名称：' + record.hotel + '</p>'
		                + '<p>房间号码：' + record.room + '</p>'
		                + '<p>预订时间：' + record.bookTime + '</p>'
		                + '<p>到达时间：' + record.arriveTime + '</p>'
		                + '<p>居住天数：' + record.days + '</p>'
		                + '<p>所花费用：' + record.cost + '</p>'
		        + '</div>';
			}
			
			$('#resideRecord').html(doc);
		}
	});
}

function getBookRecords() {
	$.ajax({
		url: '/Hotel/getMemberBookRecord.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			if (len == 0) {
				$('#subBook').show();
				return;
			}
			
			$('#subBook').hide();
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var record = result[i];
				doc +=
					'<div class="col-md-4 record-card">'
		                + '<p>旅店名称：' + record.hotel + '</p>'
		                + '<p>房间号码：' + record.room + '</p>'
		                + '<p>预订时间：' + record.bookTime + '</p>'
		                + '<p>居住日期：' + record.resideDate + '</p>'
		                + '<p>居住天数：' + record.days + '</p>'
		                + '<p>预订状态：' + record.state + '</p>'
		        + '</div>';
			}
			
			$('#bookRecord').html(doc);
		}
	});
}

function getCancelRecords() {
	$.ajax({
		url: '/Hotel/getBookCancelRecord.action',
		method: 'get',
		success: function(result) {
			var len = result.length;
			if (len == 0) {
				$('#subCancel').show();
				return;
			}
			
			$('#subCancel').hide();
			
			var doc = '';
			for (var i = 0; i < len; ++i) {
				var record = result[i];
				doc +=
					'<div class="col-md-4 record-card">'
		                + '<p>旅店名称：' + record.hotel + '</p>'
		                + '<p>房间号码：' + record.room + '</p>'
		                + '<p>预订时间：' + record.bookTime + '</p>'
		                + '<p>取消时间：' + record.cancelTime + '</p>'
		        + '</div>';
			}
			
			$('#cancelRecord').html(doc);
		}
	});
}
function getHotelBasicInfo() {
	$.ajax({
		url: '/Hotel/getHotelBasicInfo.action',
		method: 'get',
		success: function(result) {
			var doc =
				'<p>旅店名称： ' + result.name + '</p>'
				+ '<p>旅店地址： ' + result.address + '</p>'
				+ '<p>开店日期： ' + result.openDate + '</p>'
				+ '<button class="btn btn-primary form-control input-gap" '
				+ 'onclick="showModForm();">修改</button>';
			$('#basicInfo').html(doc);
		}
	});
}

function showModForm() {
	insert('#basicInfo', '/Hotel/pages/hotel/modify_form.jsp');
}

function modifyForm() {
	var tip = $('#tip');
	
	var addr = $('#address').val();
	if (addr.length == 0) {
		showError(tip, '请输入旅馆地址');
		return;
	}
	
	$.ajax({
		url: '/Hotel/modifyHotelInfo.action',
		method: 'post',
		data: {
			"address": addr
		},
		success: function(result) {
			if (result.success) {
				tip.css('color', 'green');
				tip.html(result.msg);
				setTimeout(function() {
					insert('#basicInfo', '/Hotel/pages/hotel/hotel_info.jsp');
					tip.html('');
				}, 2000);
			}
			else {
				showError(tip, result.msg);
			}
		}
	});
}

function getStatInfo(url) {
	$.ajax({
		url: url,
		method: 'get',
		success: function(result) {
			var resideData = result[0];
			var bookData = result[1];
			var years = Object.keys(resideData);
			
			var reside = [];
			for(var i in resideData) {
				reside.push(resideData[i]);
			}
			
			var book = [];
			for (var i in bookData) {
				book.push(bookData[i]);
			}
			
			var recordChart = echarts.init(document.getElementById('recordChart'));
			var recOption = {
				    title: {
				        text: '旅店统计',
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['入住次数', '预订次数']
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
				        data: years
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
				    }
			    ]
			};
			recordChart.setOption(recOption);
			
			var costData = result[2];
			var cost = [];
			for(var i in costData) {
				cost.push(costData[i]);
			}
			
			var costChart = echarts.init(document.getElementById('costChart'));
			var costOption = {
				    title: {
				        text: '收入统计',
				    },
				    tooltip: {
				        trigger: 'axis'
				    },
				    legend: {
				        data:['金额']
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
				        data: years
				    },
				    yAxis: {
				        type: 'value'
				    },
				    series: [{
				        name: '金额',
				        type: 'line',
				        smooth: true,
				        data: cost
				    }]
			};
			costChart.setOption(costOption);
		}
	});
}
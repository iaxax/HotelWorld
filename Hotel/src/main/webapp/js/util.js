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

function insert(id, url) {
	$.get(url, function(result) {
		$(id).html(result);
	});
}

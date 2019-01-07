function showDialog(_id, sucess, _dat, url) {
	$.get(url + '?' + _dat, function(data) {
		sucess.call(null, JSON.parse(data));
		$('#' + _id).modal('show');
	});
}

function showDialogNotRequest(_id) {
	$('#' + _id).modal('show');
}

function showDialogAprove(_id, accept, deny) {
	$('#' + _id).modal({
		closable : false,
		onDeny : function() {
			if(deny != undefined) {
				deny.call(null);
			}
			return true;
		},
		onApprove : function() {
			accept.call(null);
			return false;
		}
	}).modal('show');
}

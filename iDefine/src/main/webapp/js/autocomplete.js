$(function() {
	var keywords = [];
	$.ajax({
		url : '/getKeyWords',
		type : 'post',
		dataType : 'json',
		async : false,
		success : function(data) {
			$.each(data, function(key, val) {
				keywords.push(val);
			});
		}
	});
	$('#autocomplete').autocomplete({
		lookup : keywords,
		onSelect : function(suggestion) {
			getDefinition(suggestion.value);
			
		}
	});
	$(document).keypress(function(e) {
		if (e.which == 13) {
			var keyword = $('#autocomplete').val();
			$('#outputcontent').html("");
			if(keyword != ""){
				if(!(keywords.indexOf(keyword) > -1)){
					alert("new word");
				}else{
					getDefinition(keyword);
				}
			}
		}
	});
	function getDefinition(keyword) {
		var thehtml = '<strong>'+keyword+'</strong><br><br><strong>Definition:</strong>';
		var definitionCount = 1;
		$.ajax({
			url : '/getDefinition',
			type : 'post',
			data: JSON.stringify({"keyword": keyword}),
			dataType : 'json',
			contentType: "application/json; charset=utf-8",
			async : false,
			success : function(data) {
				$.each(data, function(key, val) {
					thehtml = thehtml + '<br><br>'+definitionCount+'. '+val.definition;
					definitionCount++;
				});
			}
		});
		$('#outputcontent').html(thehtml);
	}
});
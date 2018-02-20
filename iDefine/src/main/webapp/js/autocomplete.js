$(function() {
	var keywords = [];
	getKeyWords();
	function getKeyWords(){
		keywords = [];
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
	}
	$('#autocomplete').keypress(function(e) {
		if (e.which == 13) {
			var keyword = $('#autocomplete').val();
			$('#outputcontent').html("");
			if (keyword != "") {
				if (!(keywords.indexOf(keyword) > -1)) {
					$('#addDefinitionDiv').css('display', 'block');
				} else {
					getDefinition(keyword);
				}
			}
		}
	});
	function getDefinition(keyword) {
		$('#addDefinitionDiv').css('display', 'none');
		var thehtml = '<center><strong>' + keyword + '</strong></center>';
		var definitionCount = 1;
		$('#addDefinitionDiv').css('display', 'none');
		$.ajax({
			url : '/getDefinition',
			type : 'post',
			data : JSON.stringify({
				"keyword" : keyword
			}),
			dataType : 'json',
			contentType : "application/json; charset=utf-8",
			async : false,
			success : function(data) {
				$.each(data, function(key, val) {
					thehtml = thehtml + '<br>' + definitionCount + '. ' + val.definition;
					definitionCount++;
				});
			}
		});
		$('#outputcontent').html(thehtml);
	}
	
	$('#autocomplete').focusout(function(e) {
		if (keywords.indexOf($('#autocomplete').val()) > -1) {
			$('#addDefinitionDiv').css('display', 'none');
			getDefinition($('#autocomplete').val());
		}
	});

	$('#addDefinition').click(function() {
		if($('#definitionText').val().trim()==""){
			$('#definitionText').focus();
			$('#message').html("<font color=red>Enter definition</font>");
		}
		else if($('#autocomplete').val().trim()==""){
			$('#autocomplete').focus();
			$('#message').html("<font color=red>Enter keyword</font>");
		}else{
			$('#message').html("");
			$.ajax({
				url : '/addDefinition',
				type : 'post',
				data : JSON.stringify({"keyword" : $('#autocomplete').val(), "definition" : $('#definitionText').val()}),
				dataType : 'text',
				contentType : "application/json; charset=utf-8",
				async : false,
				success : function(data) {
					if(data=="true"){
						getKeyWords();
						alert(keywords);
						$('#definitionText').val("");
						$('#addDefinitionDiv').css('display', 'none');
						$('#message').html("<font color=green>Successfully Added</font>");
						$("#message").fadeOut(3000);
						getDefinition($('#autocomplete').val());
					}else{
						$('#message').html("<font color=red>Error in Insertion. Contact Admin.</font>");
					}
				}
			});
		}
	});

});
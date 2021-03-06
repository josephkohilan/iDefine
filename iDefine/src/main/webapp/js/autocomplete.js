var keywords = [];
$(function() {
	getKeyWords();
	$('#autocomplete').keypress(function(e) {
		if (e.which == 13) {
			var keyword = $('#autocomplete').val();
			$('#outputCardsTable').html("");
			if (keyword != "") {
				if (!(keywords.indexOf(keyword) > -1)) {
					$('#addDefinitionDiv').css('display', 'block');
				} else {
					getDefinition(keyword);
				}
			}
		}
	});
	$('#addDefinition').click(function() {
		var autocompleteVal = $('#autocomplete').val().trim();
		if($('#definitionText').val().trim()==""){
			$('#definitionText').focus();
			$('#message').html("<font color=red>Enter definition</font>");
		}else if(autocompleteVal=="" || autocompleteVal==":" || autocompleteVal.indexOf(":") == autocompleteVal.length -1){
			$('#autocomplete').focus();
			$('#message').html("<font color=red>Enter valid keyword</font>");
		}else{
			$('#message').html("");
			$.ajax({
				url : '/definition',
				type : 'post',
				data : JSON.stringify({"keyword" : $('#autocomplete').val(), "definition" : $('#definitionText').val()}),
				dataType : 'text',
				contentType : "application/json; charset=utf-8",
				async : false,
				success : function(data) {
					if(data=="true"){
						getKeyWords();
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
	$('#autocomplete').keyup(function(e) {
		this.value=this.value.toLowerCase();
	});
});
var definitionIdTemp = '';
function getKeyWords(){
	keywords = [];
	$.ajax({
		url : '/keyWords',
		type : 'get',
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
function getDefinition(keyword) {
	$('#addDefinitionDiv').css('display', 'none');
	var flashCards = "";
	var elementCount = 0;
	$('#addDefinitionDiv').css('display', 'none');
	$.ajax({
		url : '/definition/'+keyword,
		type : 'get',
		dataType : 'json',
		async : false,
		success : function(data) {
			$.each(data, function(key, val) {
				if(elementCount%4==0){
					flashCards = flashCards + '<tr>';
				}
				elementCount++;
				if(elementCount%3==0){
					flashCards = flashCards + '<td class="flashCard" style="background-color: #ABEBC6;">';
				}else if(elementCount%3==1){
					flashCards = flashCards + '<td class="flashCard" style="background-color: #F7DC6F;">';
				}else if(elementCount%3==2){
					flashCards = flashCards + '<td class="flashCard" style="background-color: #D6EAF8;">';
				}
				flashCards = flashCards + '<table class="votingTable"><tr><td>';
				flashCards = flashCards + '<center><b>' + keyword.toUpperCase();
				if(val.approvalStatus != true){
					flashCards = flashCards + '<img src = "../img/warning.png" width = 15px title="Yet to be reviewed">';
				}
				flashCards = flashCards + '</b></center></td></tr><tr><td><br></td></tr><tr><td class="definitionData">';
				flashCards = flashCards + val.definition.replace(/\n/g, "<br>") + '</td></tr>' + 
				'<tr><td><br></td></tr>' +
				'<tr>'+
					'<td>'+
						'<table class="votingTable">'+
							'<tr>' +
								'<td class="dateRow">Created date: ' + val.createdDate + '</td>' +
								'<td>'+
									'<img src = "../img/edit.png" width = 20px onclick="openForm('+val.definitionId+',\''+val.definition.replace(/\n/g, "<br>")+'\',\'updateForm\')">' +
								'</td>' +
								'<td>'+
									'<img src = "../img/delete.png" width = 20px onclick="openForm('+val.definitionId+',\'\',\'deleteForm\')">'+
								'</td>' + 
							'</tr>'+
						'</table>'+
					'</td>'+
				'</tr>' + 
				'<tr>' + 
					'<td class="buttonsData"><br>' + 
						'<table class="votingTable">'+
							'<tr>' + 
								'<td>'+
									'<img src="../img/happy.png" width="15px" clicked="F" id="plusVote_'+val.definitionId+'" onclick="vote(plusVote_'+val.definitionId+')">'+
									'<span id="plusVote_'+val.definitionId+'_count"">(' + val.upVotes + ')</span><input type="hidden" value="F" id="plusVote_'+val.definitionId+'_click">' +
								'</td>' +
								'<td>'+
									'<img src="../img/sad.png" width="15px" clicked="F" id="minusVote_'+val.definitionId+'" onclick="vote(minusVote_'+val.definitionId+')">'+
									'<span id="minusVote_'+val.definitionId+'_count">(' + val.downVotes + ')</span><input type="hidden" value="F" id="minusVote_'+val.definitionId+'_click">' +
								'</td>' +
							'</tr>' +
						'</table>' + 
					'</td>' +
				'</tr></table></td>';
				if(elementCount%4==0){
					flashCards = flashCards + '</tr>';
				}
			});
			if(elementCount%4!=0){
				for(i = 0; i < 4-elementCount%4; i++){
					flashCards = flashCards + '<td class="flashCard" style="box-shadow: 0px 0px 0px 0px rgba(0,0,0,0);"></td>';
				}
				flashCards = flashCards + '</tr>';
			}
		}
	});
	$('#outputCardsTable').html(flashCards);
}

function vote(idName) {
	var voteButtonId = idName.id;
	var votedButton = voteButtonId.split("_")[0];
	var voteId = voteButtonId.split("_")[1];
	var voteCountStr = document.getElementById(voteButtonId+"_count").innerHTML;
	var voteCount = parseInt(voteCountStr.substring(1, voteCountStr.length-1));
	var oppositeButton;
	var clickedButtonImg;
	var oppositeButtonImg;
	if(votedButton=="plusVote") {
		oppositeButton = "minusVote";
		clickedButtonImg = "happy";
		oppositeButtonImg = "sad";
	} else if (votedButton=="minusVote") {
		oppositeButton = "plusVote";
		clickedButtonImg = "sad";
		oppositeButtonImg = "happy";
	}
	var oppVoteCountStr = document.getElementById(oppositeButton+"_"+voteId+"_count").innerHTML;
	var oppVoteCount = parseInt(oppVoteCountStr.substring(1, oppVoteCountStr.length-1));
	if(document.getElementById(voteButtonId+"_click").value=="F"){
		voteCount++;
		if(document.getElementById(oppositeButton+"_"+voteId+"_click").value=="T"){
			oppVoteCount--;
		}
		idName.src = "../img/"+clickedButtonImg+"Click.png";
		document.getElementById(voteButtonId+"_click").value="T";
		document.getElementById(oppositeButton+"_"+voteId).src = "../img/"+oppositeButtonImg+".png";
		document.getElementById(oppositeButton+"_"+voteId+"_count").innerHTML = "("+oppVoteCount+")";
		document.getElementById(oppositeButton+"_"+voteId+"_click").value="F";
		updateVote(voteId,voteCount,oppVoteCount);
	} else if (document.getElementById(voteButtonId+"_click").value=="T") {
		voteCount--;
		idName.src = "../img/"+clickedButtonImg+".png";
		document.getElementById(voteButtonId+"_count").innerHTML = "("+voteCount+")";
		document.getElementById(voteButtonId+"_click").value="F";
	}
	document.getElementById(voteButtonId+"_count").innerHTML = "("+voteCount+")";
	if(votedButton=="plusVote") {
		updateVote(voteId,voteCount,oppVoteCount);
	} else if (votedButton=="minusVote") {
		updateVote(voteId,oppVoteCount,voteCount);
	}
}
function updateVote(definitionID, upVoteCount, downVoteCount){
	$.ajax({
		url : '/vote',
		type : 'put',
		data : JSON.stringify({
			"definitionId" : definitionID,
			"upVote": upVoteCount,
			"downVote": downVoteCount,		
		}),
		dataType : 'json',
		contentType : "application/json; charset=utf-8",
		async : false,
		success : function(data) {
			
		}
	});
}
function deleteDefinition(){
	$.ajax({
		url : '/definition/'+definitionIdTemp,
		type : 'delete',
		dataType : 'json',
		async : false,
		success : function(data) {
			closeForm('deleteForm');
			getKeyWords();
			getDefinition($('#autocomplete').val());
		}
	});
}
function updateDefinition(){
	$.ajax({
		url : '/definition',
		type : 'put',
		data : JSON.stringify({"definitionId" : definitionIdTemp, "definition" : $('#newDefinitionText').val()}),
		async : false,
		dataType : 'json',
		contentType : "application/json; charset=utf-8",
		success : function(data) {
			closeForm('updateForm');
			getKeyWords();
			getDefinition($('#autocomplete').val());
		}
	});
}
function openForm(defId, def, formName) {
	definitionIdTemp = defId;
	if("deleteForm" == formName){
		closeForm('updateForm');
	} else if ("updateForm" == formName){
		$('#newDefinitionText').val(def);
		closeForm('deleteForm');
	}
    document.getElementById(formName).style.display = "block";
}

function closeForm(formName) {
	if ("updateForm" == formName){
		$('#newDefinitionText').val('');
	}
    document.getElementById(formName).style.display = "none";
}
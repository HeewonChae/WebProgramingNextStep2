String.prototype.format = function() {
	$(".answerWrite input[type=submit]").click(addAnswer);
	
  var args = arguments;
  return this.replace(/{(\d+)}/g, function(match, number) {
    return typeof args[number] != 'undefined'
        ? args[number]
        : match
        ;
  });
};

function addAnswer(e){
	e.preventDefault();
	var queryString = $("form[name=answer]").serialize();
	
	$.ajax({
		type : 'post' ,
		url : '/api/qna/addAnswer' ,
		data : queryString,
		dataType : 'json' ,
		error : onError,
		success : onSuccess,
	});
}

function onSuccess(json, status){
	var answerTeplate = $("#answerTemplate").html();
	var template = answerTemplate.format(json.writer, new Date(json, createdDate), json.contents, json.answerId);
	$(".qna-comment-slipp-articles").prepend(template);
}
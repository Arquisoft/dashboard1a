var eventSource = new EventSource("/dashboardAdmin/updates");
		
eventSource.addEventListener("newSuggestion", function(event) {
	var obj = JSON.parse(event.data);
  	var row = document.getElementById("sugerencias").insertRow(-1);
	row.insertCell(0).outerHTML = "<th>"+obj.suggestion+"</th>";
	row.insertCell(1).innerHTML = 0;
	row.insertCell(2).innerHTML = 0;
});

eventSource.addEventListener("alertSuggestion", function(event) {
	var obj = JSON.parse(event.data);
	
	$('th').filter(function() {
		return $(this).text() === obj.suggestion;
	}).closest('tr').css("color", "red");
	alert("La sugerencia " + obj.suggestion + " ha sido aprobada");
});
    	
eventSource.addEventListener("voteSuggestion", function(event) {
	var obj = JSON.parse(event.data);
  	var value = parseInt($('th').filter(function() {
					return $(this).text() === obj.suggestion;
				}).closest('tr').children('td').eq(0).text());

	$('th').filter(function() {
		return $(this).text() === obj.suggestion;
	}).closest('tr').children('td').eq(0).html(value+1);
});

eventSource.addEventListener("newComment", function(event) {
	var obj = JSON.parse(event.data);
  	var value = parseInt($('th').filter(function() {
					return $(this).text() === obj.suggestion;
				}).closest('tr').children('td').eq(1).text());

	$('th').filter(function() {
		return $(this).text() === obj.suggestion;
	}).closest('tr').children('td').eq(1).html(value+1);
});
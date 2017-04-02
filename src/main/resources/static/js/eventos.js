var eventSource = new EventSource("/dashboardAdmin/updates");
		
eventSource.addEventListener("newSuggestion", function(event) {
  	var row = document.getElementById("sugerencias").insertRow(-1);
	row.insertCell(0).outerHTML = "<th>"+event.data+"</th>";
	row.insertCell(1).innerHTML = 0;
	row.insertCell(2).innerHTML = 0;
});
    	
eventSource.addEventListener("voteSuggestion", function(event) {
  	var value = parseInt($('td').filter(function() {
					return $(this).text() === event.data;
				}).closest('tr').children('td').eq(1).text());

	$('td').filter(function() {
		return $(this).text() === event.data;
	}).closest('tr').children('td').eq(1).html(value+1);
});

eventSource.addEventListener("newComment", function(event) {
  	var value = parseInt($('td').filter(function() {
					return $(this).text() === event.data;
				}).closest('tr').children('td').eq(2).text());

	$('td').filter(function() {
		return $(this).text() === event.data;
	}).closest('tr').children('td').eq(2).html(value+1);
});
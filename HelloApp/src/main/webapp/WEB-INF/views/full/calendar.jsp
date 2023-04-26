<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset='utf-8' />
<script src='/fullcal/dist/index.global.js'></script>
<script>

  document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');
	
    // allEvents 초기화
    
    let allEvents=[];
    
    fetch('eventList.do')
    .then(function(resolve){
    	return resolve.json();
    })
    .then(function(result){
    	allEvents = result;
    	/* result.forEach(function(event){
    		let newEvent={title:event.title,start:event.start,end:event.end};
    		allEvents.push(newEvent);
    	}); */
    	var calendar = new FullCalendar.Calendar(calendarEl, {
    	      headerToolbar: {
    	        left: 'prev,next today',
    	        center: 'title',
    	        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    	      },
    	      initialDate: new Date(),
    	      navLinks: true, // can click day/week names to navigate views
    	      selectable: true,
    	      selectMirror: true,
    	      select: function(arg) {
    	        var title = prompt('이벤트 등록 :');
    	        
    	        if (title) {
    	        	//Ajax 호출
    	        	fetch('addEvent.do',{
    	     			method:'POST',
    	        		headers:{
    	        		  'Content-Type':'application/x-www-form-urlencoded'
    	        		},
    	          		body : 'title='+title+'&startDate='+arg.startStr+'&endDate='+arg.endStr
    	          	})
    	        	.then(resolve=>resolve.json())
    	        	.then(result => {
    	        		if(result.retCode=="Success"){
    	        			calendar.addEvent({
    	        	            title: title,
    	        	            start: arg.start,
    	        	            end: arg.end,
    	        	            allDay: arg.allDay
    	        	          })
    	        			alert('성공');
    	        		}
    	        		else if(result.retCode="Fail"){
    	        			alert('실패');
    	        		}
    	        	})
    	        	.catch(err => console.log(err));
    	        }
    	        calendar.unselect()
    	      },
    	      eventClick: function(arg) {
    	    	  console.log(arg.event._def.title);
    	        if (confirm('Are you sure you want to delete this event?')) {
    	        	fetch('removeEvent.do',{
    	     			method:'POST',
    	        		headers:{
    	        		  'Content-Type':'application/x-www-form-urlencoded'
    	        		},
    	          		body : 'title='+arg.event._def.title
    	          	})
    	        	.then(resolve=>resolve.json())
    	        	.then(result => {
    	        		if(result.retCode=="Success"){
		    	          arg.event.remove()
    	   
    	        			alert('성공');
    	        		}
    	        		else if(result.retCode="Fail"){
    	        			alert('실패');
    	        		}
    	        	})
    	        	.catch(err => console.log(err));
    	        }
    	      },
    	      editable: true,
    	      dayMaxEvents: true, // allow "more" link when too many events
    	      events: allEvents
    	    });

    	    calendar.render();
    })
    .catch(function(err){
    	console.log(err);
    });
    
    
    
  });

</script>
<style>
body {
	margin: 40px 10px;
	padding: 0;
	font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	font-size: 14px;
}

#calendar {
	max-width: 1100px;
	margin: 0 auto;
}
</style>
</head>
<body>

	<div id='calendar'></div>

</body>
</html>

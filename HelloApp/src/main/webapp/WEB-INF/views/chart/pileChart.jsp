<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'corechart' ]
	});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {
		var result = [ [ 'dept', 'cnt' ] ];
		let xhtp = new XMLHttpRequest();
		xhtp.open('get', 'chartData.do');
		xhtp.send();
		xhtp.onload = function() {
			let chartData = JSON.parse(xhtp.response);
			for(let dept in chartData){
				console.log(dept,chartData[dept]);
				result.push([dept,chartData[dept]]);
			}
			console.log(result);
			var data = google.visualization.arrayToDataTable(result);

			var options = {
				title : '부서별 인원 현황',
				is3D : true
			};

			var chart = new google.visualization.PieChart(document
				.getElementById('piechart'));

			chart.draw(data, options);
		}
		
	}
</script>
</head>
<body>
	<p>views/chart/pileChart.jsp</p>
	<div id="piechart" style="width: 900px; height: 500px;"></div>
</body>
</html>

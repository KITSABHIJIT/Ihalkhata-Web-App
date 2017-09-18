//Ext.chart.Chart.CHART_URL = 'ext3/resources/charts.swf';

Ext.onReady(function(){
	 Ext.QuickTips.init();


	 
//var dataArray1=[19.9, 51.5, 106.4, 129.2, 144.0, 176.0, 135.6, 148.5, 216.4, 194.1];
//var dataArray2=[29.9, 41.5, 76.4, 45.2, 45.0, 66.0, 25.6, 56.5, 45.4, 43.1];
////var dataArray3=[39.9, 31.5, 46.4, 65.2, 14.0, 46.0, 35.6, 38.5, 43.4, 12.1];
////var dataArray4=[49.9, 21.5, 16.4, 76.2, 54.0, 86.0, 85.6, 54.5, 75.4, 54.1];
//var scatterArray1=[ ['09:55:05',49.9] , ['09:55:02', 21.5] , ['09:55:03', 16.4] , ['09:55:07',76.2] , ['09:55:05', 54.0] , ['09:55:06',86.0] ];
//var scatterArray2=[ ['09:55:02',49.9] , ['09:55:05', 21.5] , ['09:55:04', 16.4] , ['09:55:01',76.2] , ['09:55:03', 54.0] , ['09:55:03',86.0] ];
//var scatterArray3=[ ['09:55:07',49.9] , ['09:55:01', 21.5] , ['09:55:09', 16.4] , ['09:55:01',76.2] , ['09:55:09', 54.0] , ['09:55:01',86.0] ];
//var catagoryArray=['09:55:01','09:55:02','09:55:03','09:55:04','09:55:05','09:55:06','09:55:07','09:55:08','09:55:09','09:56:00'];
	 
var graphData= '[{ }]';
var graphDataDecoded = Ext.util.JSON.decode(graphData);
	    

graphData =ajaxFunction('POST','../GenerateChartServlet',false,data);

//var finalGraphData=graphData.substring(1, graphData.length-2);
var finalGraphDataArray=graphData.split("|");
var seriesColumn="";
var userArray=user.split(",");
for(var i=0;i<userArray.length;i++){

if(seriesColumn=="")
{seriesColumn ="{name: '"+userArray[i]+"',type :'spline' ,data :"+finalGraphDataArray[i]+"}";}
else
{seriesColumn =seriesColumn+",{name: '"+userArray[i]+"',type :'spline' ,data :"+finalGraphDataArray[i]+"}";}
}
var seriesColumn1='['+seriesColumn+']';
var finalSeriesColumn = Ext.util.JSON.decode(seriesColumn1);

var chartExample = new Highcharts.Chart({
	chart: {
	renderTo: 'container',
	zoomType: 'xy'
},
title: {
	text: 'Expense Tracker Graph'
},
subtitle: {
	text: document.ontouchstart === undefined ?
		'Click and drag in the plot area to zoom in' :
		'Drag your finger over the plot to zoom in'
},

xAxis: [{
	//type: 'time',
	//categories:catagoryArray,
	labels: {
	//rotation: -45,
	style: {
		color: '#FFFFFF'
	},
	formatter: function() {
		//return this.value +'°C';
		return Highcharts.dateFormat('%e. %b', this.value) ;
	},
	type: 'datetime'/*,
	
	align: 'right',
	style: {
		 font: 'normal 8px Verdana, sans-serif'
	}*/
},
title: {
	//text: 'Security Trade Price',
	text: 'Days',
	style: {
	color: '#FFFFFF'
	}
}

	//categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun','Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
}],
yAxis: [{ // Primary yAxis
	labels: {
		formatter: function() {
			//return this.value +'°C';
	return this.value ;
		},
		style: {
			color: '#FFFFFF'
		}
	},
	title: {
		//text: 'Security Trade Price',
		text: 'Expense',
		style: {
		color: '#FFFFFF'
		}
	}
}],
tooltip: {
	formatter: function() {
		//return ''+	Highcharts.dateFormat('%e. %b %Y, %H:%M:%S', this.x) +': '+ this.y +(this.series.name == 'Rainfall' ? ' mm' : '°C');
	//return ''+this.x +' : '+ this.y +(this.series.name == 'Rainfall' ? ' mm' : '°C');
	if(Highcharts.dateFormat('%e', this.x)==='1' || Highcharts.dateFormat('%e', this.x)==='21' || Highcharts.dateFormat('%e', this.x)==='31')
		return  'User:<b>'+this.series.name+'</b><br>Expense: <b>Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%est %b %Y', this.x)+'</b>';
	else if(Highcharts.dateFormat('%e', this.x)==='2' || Highcharts.dateFormat('%e', this.x)==='22')
		return  'User:<b>'+this.series.name+'</b><br>Expense: Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%end %b %Y', this.x)+'</b>';
	else if(Highcharts.dateFormat('%e', this.x)==='3'  || Highcharts.dateFormat('%e', this.x)==='23')
		return  'User:<b>'+this.series.name+'</b><br>Expense: <b>Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%erd %b %Y', this.x)+'</b>';
	else
		return  'User:<b>'+this.series.name+'</b><br>Expense: <b>Rs.'+parseFloat(Math.round(this.y * 100) / 100).toFixed(2)+'</b><br>on <b>'+Highcharts.dateFormat('%eth %b %Y', this.x)+'</b>';
	}
},
plotOptions: {
	spline: {
		lineWidth: 4,
		states: {
			hover: {
				lineWidth: 5
			}
		},
		marker: {
			enabled: false,
			states: {
				hover: {
					enabled: true,
					symbol: 'circle',
					radius: 5,
					lineWidth: 1
				}
			}	
		}/*,
		pointInterval: 3600000, // one hour
		pointStart: Date.UTC(2009, 9, 6, 0, 0, 0)*/
	}
},
/*legend: {
	layout: 'vertical',
	align: 'right',
	verticalAlign: 'top',
	x: -10,
	y: 100,
	borderWidth: 0
},*/

series: finalSeriesColumn

});


});

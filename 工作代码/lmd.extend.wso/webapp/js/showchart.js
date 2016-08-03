$(function () {                                                                     
$(document).ready(function() {
	Highcharts.setOptions({                                                     
		global: {                                                               
			useUTC: false                                                       
		}                                                                       
	});          
	var avgtime=parseInt($("#pjxysj").val());
	if($("#pjxysj").val()==""){
	avgtime=0;
	}
	function getData(){
		 $.ajax({   
	        type: "post",
	       // url: "xqdata.jsp",
	        url: "mbavg.do",
	        data: {"acnum":$("#zybhId").val()},   
	        dataType: "text",
	        success: function (data){
	        	avgtime = parseInt(data);
	        },
	        error:function(data){
	        	avgtime=0;
	        }
	    }); 
		return avgtime;
	}		
	var chart;                                                                  
	$('#container').highcharts({ 
		credits: {
          enabled:false
		},//去掉右下角heights.com链接
		chart: {                                                                
			type: 'spline',                                                     
			animation: Highcharts.svg, // don't animate in old IE               
			marginRight: 10,                                                    
			events: {                                                           
				load: function() {                                              
																				
					// set up the updating of the chart each second             
					var series = this.series[0];                                
					setInterval(function() {                                    
						var x = (new Date()).getTime(), // current time         
//							y = Math.random();     
							y = getData();
						series.addPoint([x, y], true, true);                    
					}, 1000);                                                   
				}                                                               
			}                                                                   
		},                                                                      
		title: {                                                                
			text: '服务动态响应图'                                            
		},                                                                      
		xAxis: {                                                                
			type: 'datetime',                                                   
			tickPixelInterval: 100                                              
		},                                                                      
		yAxis: {                                                                
			title: {                                                            
				text: '服务动态响应图--毫秒'                                                   
			},                                                                  
			plotLines: [{                                                       
				value: 10000000,                                                       
				width: 1,                                                       
				color: '#808080'                                                
			}]                                                                  
		},                                                                      
		tooltip: {                                                              
			formatter: function() {                                             
					return '<b>'+ this.series.name +'</b><br/>'+                
					Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) +'<br/>'+
					Highcharts.numberFormat(this.y, 2);                         
			}                                                                   
		},                                                                      
		legend: {                                                               
			enabled: false                                                      
		},                                                                      
		exporting: {                                                            
			enabled: false                                                      
		},                                                                      
		series: [{                                                              
			name: '平均响应时间',                                                
			data: (function() {                                                 
				// generate an array of random data                             
				var data = [],                                                  
					time = (new Date()).getTime(),                              
					i;                                                          
																				
				for (i = -19; i <= 0; i++) {                                    
					data.push({                                                 
						x: time + i * 1000,                                     
						y: avgtime                                       
					});                                                         
				}                                                               
				return data;                                              
			})()                                                                
		}]                                                                      
	});                                                                         
});                                                                             
																				
});  



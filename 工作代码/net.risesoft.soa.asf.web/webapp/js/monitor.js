$(document).ready(function() {
	Highcharts.setOptions({
				global : {
					useUTC : false
				},
				credits : {
					enabled : false
				}
			});

	var jolokia = new Jolokia({
				url : "/jmx-agent",
				username : "jmxagent",
				password : "jmxagent",
				timeout : 5000
			});

	var availableProcessors = jolokia.getAttribute("java.lang:type=OperatingSystem", "AvailableProcessors");

	var maxHeapMemory = jolokia.getAttribute("java.lang:type=Memory", "HeapMemoryUsage", "max");

	// console.log('cpus:=' + availableProcessors);
	var updateDelay = 15 * 1000;// 15秒

	var memoryChart = new Highcharts.Chart({
				chart : {
					renderTo : 'memory-chart-container',
					type : 'spline',
					marginRight : 10
				},
				title : {
					text : 'JVM 堆内存 ( MAX = ' + Highcharts.numberFormat(maxHeapMemory / (1024 * 1024), 2) + ' MB )'
				},
				xAxis : {
					type : 'datetime',
					tickPixelInterval : 150
				},
				yAxis : {
					title : {
						text : '堆内存使用 (MB)'
					},
					min : 0,
					plotLines : [{
								value : 0,
								width : 1,
								color : '#808080'
							}]
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>'
								+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>'
								+ Highcharts.numberFormat(this.y / (1024 * 1024), 2) + ' MB';
					}
				},
				legend : {
					enabled : false
				},
				exporting : {
					enabled : false
				},
				series : [{
							name : '堆内存使用 (MB)',
							data : (function() {
								// 填充当前时间点之前的 Y 轴
								var data = [], time = (new Date()).getTime(), i;

								for (i = -19; i <= 0; i++) {
									data.push({
												x : time + i * updateDelay,
												y : 0
											});
								}
								return data;
							})()
						}]
			});

	var cpuChart = new Highcharts.Chart({
				chart : {
					renderTo : 'cpu-chart-container',
					type : 'spline',
					marginRight : 10
				},
				title : {
					text : 'CPU ( * ' + availableProcessors + ' ) 监控'
				},
				xAxis : {
					type : 'datetime',
					tickPixelInterval : 150
				},
				yAxis : {
					title : {
						text : 'CPU ( * ' + availableProcessors + ' ) 使用率'
					},
					min : 0,
					max : 100,
					plotLines : [{
								value : 0,
								width : 1,
								color : '#808080'
							}]
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>'
								+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>'
								+ Highcharts.numberFormat(this.y, 2) + ' %';
					}
				},
				legend : {
					enabled : false
				},
				exporting : {
					enabled : false
				},
				series : (function() {
					var series = [];
					for (var i = 0; i < 1/* availableProcessors */; i++) {
						series.push({
									name : 'CPU ( * ' + availableProcessors + ' ) 使用率',
									data : (function() {
										// 填充当前时间点之前的 Y 轴
										var data = [], time = (new Date()).getTime(), i;

										for (i = -19; i <= 0; i++) {
											data.push({
														x : time + i * updateDelay,
														y : 0
													});
										}
										return data;
									})()
								});
					}
					return series;
				})()
			});

	var dspoolChart = new Highcharts.Chart({
				chart : {
					renderTo : 'dspool-chart-container',
					type : 'spline',
					marginRight : 10
				},
				title : {
					text : '数据库连接池监控'
				},
				xAxis : {
					type : 'datetime',
					tickPixelInterval : 150
				},
				yAxis : {
					title : {
						text : '活动/空闲连接数'
					},
					min : 0,
					max : 100,
					plotLines : [{
								value : 0,
								width : 1,
								color : '#808080'
							}]
				},
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>'
								+ Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>'
								+ Highcharts.numberFormat(this.y, 0);
					}
				},
				legend : {
					enabled : true
				},
				exporting : {
					enabled : false
				},
				series : (function() {
					var series = [{
								name : '活动连接数',
								data : (function() {
									// 填充当前时间点之前的 Y 轴
									var data = [], time = (new Date()).getTime(), i;

									for (i = -19; i <= 0; i++) {
										data.push({
													x : time + i * updateDelay,
													y : 0
												});
									}
									return data;
								})()
							}, {
								name : '空闲连接数',
								data : (function() {
									// 填充当前时间点之前的 Y 轴
									var data = [], time = (new Date()).getTime(), i;

									for (i = -19; i <= 0; i++) {
										data.push({
													x : time + i * updateDelay,
													y : 0
												});
									}
									return data;
								})()
							}];
					return series;
				})()
			});

	var handles = [];

	// memory
	handles.push(jolokia.register(function(memory) {
				var memorySeries = memoryChart.series[0];
				var x = new Date().getTime();
				var y = memory.value; // memory
				memorySeries.addPoint([x, y], true, true);
			}, {
				type : "READ",
				mbean : "java.lang:type=Memory",
				attribute : "HeapMemoryUsage",
				path : "used"
			}));

	// cpus
	for (var i = 0; i < 1/* availableProcessors */; i++) {
		handles.push(jolokia.register(function(cpu) {
					var cpu = cpu.value;
					var cpuSeries = cpuChart.series[cpu.CpuIndex];
					var idle = cpu.Idle;
					var combined = cpu.Combined;
					var x = new Date().getTime();
					var y = combined * 100;
					cpuSeries.addPoint([x, y], true, true);
				}, {
					type : "READ",
					mbean : "sigar:type=CpuPerc,cpuIndex=" + i
				}));
	}

	// dspool
	handles.push(jolokia.register(function(pool) {
				var pool = pool.value;
				var numIdle = pool.NumIdle;
				var numActive = pool.NumActive;
				var activeSeries = dspoolChart.series[0];
				var idleSeries = dspoolChart.series[1];
				var x = new Date().getTime();
				activeSeries.addPoint([x, numActive], true, true);
				idleSeries.addPoint([x, numIdle], true, true);
			}, {
				type : "READ",
				mbean : "org.apache.commons.dbcp:ManagedBasicDataSource=ManagedBasicDataSource"
			}));

	jolokia.start(updateDelay);

	$('#update-delay-setup').click(function() {
				// console.log('new delay:' +
				// $(this).children('option:selected').val());
				var newDelay = $(this).children('option:selected').val();
				newDelay = parseInt(newDelay);
				if (newDelay && (newDelay * 1000 != updateDelay)) {
					updateDelay = newDelay * 1000;
					jolokia.start(updateDelay);
				}
			});

	var updateDelayDivPosition = function(container) {
		var newWidth = ($(container).width() - 300) + 'px';
		$('#update-delay-div').css("padding-left", newWidth);
	};

	updateDelayDivPosition(document);

	$(window).resize(function() {
				updateDelayDivPosition(window);
			});
		/*
		 * $('#force-gc-button').click(function() {
		 * jolokia.execute("java.lang:type=Memory", "gc", { success :
		 * function(value) { console.log(JSON.stringify(value)); } }); });
		 */

});

Ext.ns('App.Service');

App.Service.Log = function() {

	var serviceId = Ext.ux.util.getQueryValue(location.search, 'serviceId');
	var methodList = Ext.ux.util.getQueryValue(location.search, 'methodList');

	var store = new Ext.data.Store({
				url : App.Context['RootPath'] + '/services/log.json',
				reader : new Ext.data.JsonReader({
							root : 'logs',
							id : 'id',
							totalProperty : 'totalCount'
						}, [{
									name : 'id',
									mapping : 'id'
								}, {
									name : 'object',
									mapping : 'object'
								}, {
									name : 'method',
									mapping : 'method'
								}, {
									name : 'invokeTime',
									mapping : 'invokeTime'
								}, {
									name : 'processTime',
									mapping : 'processTime'
								}, {
									name : 'success',
									mapping : 'success'
								}, {
									name : 'errMsg',
									mapping : 'errMsg'
								}, {
									name : 'action',
									mapping : 'action'
								}]),
				baseParams : {
					serviceId : serviceId,
					methodList : methodList
				},
				remoteSort: true
			});

	window.updateContent = function(params) {
      store.load(params);
	};

	// var sm = new Ext.grid.CheckboxSelectionModel({
	// width : 25
	// });

	var column = new Ext.grid.ColumnModel([/* sm, */{
				id : 'id',
				header : '编号',
				dataIndex : 'id',
				width : 200,
				hidden : true
			}, {
				header : '对象',
				dataIndex : 'object',
				width : 200
			}, {
				header : '方法',
				dataIndex : 'method'
			}, {
				header : '调用时间',
				dataIndex : 'invokeTime',
				renderer : function(v, p, r) {
					return new Date(v).toLocaleString();
				},
				sortable : true,
				remoteSort: true
			}, {
				header : '执行用时',
				dataIndex : 'processTime',
				renderer : function(v, p, r) {
					return v + ' 微秒';
				}
			}, {
				header : '成功?',
				dataIndex : 'success',
				width : 30,
				renderer : function(v, p, r) {
					return v === true ? '<font color="green">是</font>' : '<font color="red">否</font>'
				}
			}, {
				header : '错误信息',
				dataIndex : 'errMsg'
			}/*, {
				header : '操作',
				dataIndex : 'action'
			}*/]);
	//column.defaultSortable = true;

	var wrongOnly = new Ext.form.Checkbox({
		boxLabel : '只显示失败的调用'
	});
	
	var cmpDropdown = new Ext.form.ComboBox({
				width:  300,
				store : new Ext.data.JsonStore({
							fields : [{
										name : 'name'
									}, {
										name : 'value'
									}],
							url : App.Context['RootPath'] + '/services/log-cmp-names.json'
						}),
				displayField : 'name',
				valueField : 'value',
				typeAhead : true,
				editable : false,
				triggerAction : 'all',
				//value : ''/*('all' == App.Context['currentCmp'] ? '全部显示' : App.Context['currentCmp'])*/
				emptyText: '请选择...'
			});

	cmpDropdown.on('select', function(combo, record, index) {
				var cmp = record.data['value'];
				Ext.apply(store.baseParams, {
							'serviceName' : cmp,
							'wrongOnly' : wrongOnly.getValue()
						});
				store.load();
			});
	
	wrongOnly.on('check', function(){
		Ext.apply(store.baseParams, {
					'serviceName' : cmpDropdown.getValue(),
					'wrongOnly' : wrongOnly.getValue()
				});
		store.load();	
	});

	var monitorStore = new Ext.data.JsonStore({
			url : App.Context['RootPath'] + '/services/log-monitor.json',
			fields: [{name:'time', mapping:'time'},
					 {name:'count', mapping:'count'}],
			baseParams : {
				realtime : true
			}
		});
		
	var logChart = new Ext.ux.HighChart({
		animShift : true,
		chartConfig : {
			chart : {
				type : 'spline',
				marginRight : 10
			},
			title : {
				text : '服务调用监控'
			},
			xAxis : {
				type : 'datetime',
				tickPixelInterval : 150
			},
			yAxis : {
				title : {
					text : '调用次数'
				},
				min : 0,
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			},
			tooltip : {
				formatter : function() {
					return '<b>' + this.series.name + '</b><br/>' + Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x)
							+ '<br/>' + this.y + ' 次.';
				}
			},
			legend : {
				enabled : false
			},
			exporting : {
				enabled : false
			},
			series : [ {
				name : '调用次数',
				data : (function() {
					// 填充当前时间点之前的 Y 轴
					var data = [], time = (new Date()).getTime(), i;

					for (i = -19; i <= 0; i++) {
						data.push({
							x : time + i * (5 * 1000),
							y : 0
						});
					}
					return data;
				})()
			} ]
		}
	});
	
	monitorStore.on('load', function(store, records, options) {
		var countSeries = logChart.chart.series[0];
		if(countSeries){
			for(var i=0; i< records.length; i++){
				var data = records[i].data;
				//console.log(data['time']);
				countSeries.addPoint([data['time'], data['count']], true, true);
			}
		}
	});
		
	var monitorWindow = new Ext.Window({
		el : 'monitor-win-div',
		layout : 'fit',
		modal : true,
		title : '服务调用监控',
		width : 800,
		height : 600,
		closeAction : 'hide',
		//plain : true,
		tbar : [{text: '历史数据'}, {text: '实时数据'}],
		items : [ logChart ]
	});		
	
    var monitorTask = {
      run: function() {
	  	 Ext.apply(monitorStore.baseParams, {
	  		 currentTime : new Date().getTime(),
			 realtime : true,
			 samplingInterval : 5000
		  });
    	  monitorStore.load();
      },
      interval: 5000
    };
    
    monitorWindow.on('hide', function(){
    	Ext.TaskMgr.stop(monitorTask);
    });
    
    monitorWindow.on('show', function(){
    	var chart = logChart.chart;
    	var me = monitorWindow;
    	if(chart){
			var countSeries = logChart.chart.series[0];
			if(countSeries){
				countSeries.setData((function() {
					// 填充当前时间点之前的 Y 轴
					var data = [], time = (new Date()).getTime(), i;
	
					for (i = -19; i <= 0; i++) {
						data.push({
							x : time + i * (5 * 1000),
							y : 0
						});
					}
					return data;
				})());
			    Ext.TaskMgr.start(monitorTask);
			}
	    }else{
	    	setTimeout(function(){
	    		me.fireEvent('show', me)
	    	}, 3000);
	    }

	});
    
	var openMonitor = {
			text: '查看图表', 
			iconCls: 'asf-monitor-node',
			handler: function(){
				monitorWindow.show();
			}};

	var grid = new Ext.grid.GridPanel({
				store : store,
				cm : column,
				// sm : sm,
				viewConfig : {
					forceFit : true
				},
				layoutConfig : {
					autoWidth : true,
					autoHeight : true
				},
				loadMask: true,
				stripeRows : true,
				el : 'logs-grid-div',
				title : '服务调用日志',
				tbar : ['服务组件: ', ' ', cmpDropdown, '-', wrongOnly, '->', openMonitor],
				bbar : new Ext.PagingToolbar({
							pageSize : 20,
							store : store,
							displayInfo : true,
							displayMsg : '第{0} 到 {1} 条数据,  共 {2} 条',
							emptyMsg : "没有数据",
							lastText : '末页',
							firstText : '首页',
							nextText : '后一页',
							prevText : '前一页',
							refreshText : '刷新'
						})
			});

	var exWin = new Ext.Window({
				closeAction : 'hide',
				modal : true,
				resizable : false,
				autoHeight : false,
				border : false,
				layout : 'fit',
				title : '异常堆栈',
				width : 800,
				height : 600
			});

	return {
		init : function() {
			grid.render();
			store.load({
						start : 0,
						limit : 20
					});
			var vp = new Ext.Viewport({
						layout : 'fit',
						items : grid
					});
			//highcharts global config
			Highcharts.setOptions({
				global : {
					useUTC : false
				},
				credits : {
					enabled : false
				}
			});

		},

		showException : function(/* String */logId) {
			exWin.show();
			exWin.center();
			exWin.load({
						url : 'showEx.do',
						// scripts : true,
						params : {
							logId : logId
						}
					});
		}
	};
}();

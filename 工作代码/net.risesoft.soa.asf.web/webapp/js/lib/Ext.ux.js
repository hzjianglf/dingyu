Ext.namespace('Ext.ux');
Ext.ux.util = function() {
	return {
		/**
		 * 返回 &name=value;
		 * 
		 * @param search
		 *            URL的?及?以后的部分(location.search)
		 * @param name
		 *            &name=value中的name
		 */
		getQuery : function(search, name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = search.substr(1).match(reg);
			if (r != null)
				return unescape(r[0]);
			return null;
		},
		/**
		 * 返回 value;
		 * 
		 * @param search
		 *            URL的?及?以后的部分(location.search)
		 * @param name
		 *            &name=value中的name
		 */
		getQueryValue : function(search, name) {
			var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
			var r = search.substr(1).match(reg);
			if (r != null)
				return unescape(r[2]);
			return null;
		}
	};
}();

Ext.ux.log = function() {
	if (window.navigator.userAgent.indexOf("Firefox") < 0) {
		window.console = {
			info : function() {
			},
			warn : function() {
			},
			debug : function() {
			},
			error : function() {
			},
			trace : function() {
			}
		}
	}
	return {
		info : function(msg) {
			if (console)
				console.info(msg);
		},
		warn : function(msg) {
			if (console)
				console.warn(msg);
		},
		debug : function(msg) {
			if (console)
				console.debug(msg);
		},
		error : function(msg) {
			if (console)
				console.error(msg);
		},
		trace : function(msg) {
			if (console)
				console.trace(msg);
		}
	};
}();

Ext.namespace('Ext.ux.form');
Ext.ux.form.ReadOnlyField = Ext.extend(Ext.form.TextField, {
			initComponent : function() {
				Ext.form.TextField.superclass.initComponent.call(this);
				this.selectOnFocus = true;
				this.readOnly = true;
			}
		});

Ext.Msg.warn = function(msg) {
	Ext.Msg.show({
				title : '警告',
				msg : msg,
				buttons : Ext.Msg.OK,
				icon : Ext.MessageBox.WARNING
			});
};

Ext.namespace('Ext.ux.grid');
Ext.ux.grid.EditorGridPanel = function(c) {
	Ext.ux.grid.EditorGridPanel.superclass.constructor.call(this, Ext.apply(c, {
						viewConfig : {
							forceFit : true
						},
						layoutConfig : {
							autoWidth : true,
							autoHeight : true
						},
						stripeRows : true,
						clicksToEdit : 1,
						renderTo : document.body,
						monitorResize : true,
						doLayout : function() {
							this.setSize(Ext.get(this.getEl().dom.parentNode).getSize(true));
							Ext.grid.EditorGridPanel.prototype.doLayout.call(this);
						}
					}));
};
Ext.extend(Ext.ux.grid.EditorGridPanel, Ext.grid.EditorGridPanel);

if (Ext.ux.grid.RowExpander) {
	Ext.apply(Ext.ux.grid.RowExpander.prototype, {
				getDataFn : null,
				onMouseDown : function(e, t) {

					if (t.className == 'x-grid3-row-expander') {
						e.stopEvent();
						var row = e.getTarget('.x-grid3-row');
						var viewRow = row;
						if (typeof viewRow == 'number') {
							viewRow = this.grid.view.getRow(viewRow);
						}
                  
						var record = this.grid.store.getAt(viewRow.rowIndex);
						if (!record.data.detailDataLoaded) {

							var mark = new Ext.LoadMask(Ext.getBody(), {
										msg : '加载中...',
										removeMask : true
									});
							mark.show();
							this.getDataFn(record, this, function(expander) {
										// 展开该行
										expander.toggleRow(viewRow.rowIndex);
										mark.hide();
                  
                              // 使 RowExpander 的内容可以选中.
										var tr = Ext.DomQuery.selectNode('tr:nth(2)', viewRow);
										if (tr) {
											var fly = Ext.fly(tr);
											if (!fly.hasClass('x-selectable')) {
												fly.addClass('x-selectable');
											}
										}
                                                
									});

							return;
						}
						this.toggleRow(row);
					}
				}
			});
}

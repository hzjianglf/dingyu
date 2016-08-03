Ext.ns('App.Service');

App.Service.Manager = function() {
   var repo = App.Service.Repo;
   var module = App.Service.Module;
   var comp = App.Service.Comp;
   var operate = App.Service.Operate;

	var mainPanel = new Ext.Panel({
				id : 'mainPanel',
				border : false,
				layout : 'accordion',
				layoutConfig : {
					animate : false
				},
				items : [repo.mainPanel, module.mainPanel, comp.mainPanel, operate.mainPanel]
			});

	var vp = new Ext.Viewport({
				layout : 'fit',
				items : mainPanel
			});

	return {
		init : function() {
		},

		switchPanel : function(/* Panel Id */id) {
			var mainPanel = Ext.getCmp('mainPanel');
			var layout = mainPanel.getLayout();

			$.each([repo.mainPanel, module.mainPanel, comp.mainPanel, operate.mainPanel], function(i, panel) {
						if (panel.id == id) {
							panel.show();
							layout.setActiveItem(panel);
						} else {
							panel.hide();
						}
					});
			mainPanel.doLayout();
		}
	}

}();

Ext.onReady(function() {
			App.Service.Manager.init();
		});

window.updateContent = function(params) {
	if (params['_TargetType'] == 'repo') {
		App.Service.Repo.store.load({
					params : params
				});
		App.Service.Manager.switchPanel('repo-main-panel');
	} else if (params['_TargetType'] == 'module') {
		App.Service.Module.store.load({
					params : params
				});
		App.Service.Manager.switchPanel('module-main-panel');
	} else if (params['_TargetType'] == 'comp') {
		App.Service.Comp.store.load({
					params : params
				});
		App.Service.Manager.switchPanel('comp-main-panel');
	} else if (params["_TargetType"] == 'operate') {
		App.Service.Operate.store.load({
					params : params
				});
		App.Service.Manager.switchPanel('operate-main-panel');
	}
};

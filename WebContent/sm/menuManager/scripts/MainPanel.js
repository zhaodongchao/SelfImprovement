Ext.namespace("MenuManager")
MenuManager.MainPanel = Ext.extend(Ext.Panel,{
   title:'菜单管理',
   layout:'fit',
   frame:true,
   collapsible : false, //不允许窗口折叠
   initComponent : function(){
     
   	 this.menuManager_store = new Ext.data.JsonStore({//使用JsonStore，不定义root节点，可以直接解析从后台传入JsonArray数据
   	 	                    url:BasePath+'menuManager/list',
   	 	                    fields:[
						            {name:'id',mapping:'id'},
						            {name: 'menu_name'},
						            {name: 'level'},
						            {name:'isleaf'},
						            {name:'menu_url'},
						            {name:'parentId'},
						            {name:'created_time'}],
					       autoLoad:true
					    });
					    
	 var editor = new Ext.ux.grid.RowEditor({
        saveText: 'Update'
        
      });				    
					    
   	 this.menuManager_grid = new Ext.grid.GridPanel({
   	 	                    id:'MenuManager.MainPanel.menuManager_grid',
					   	    store: this.menuManager_store,
					   	   /* view: new Ext.grid.GroupingView({
												            markDirty: false
												        }),*/
					   	    colModel:new Ext.grid.ColumnModel( [new Ext.grid.RowNumberer(),
					        {
										id : 'id',
										header : "ID",
										hidden : true,
										locked : true,
										dataIndex : 'id'
									}, {
										header : "菜单名称",
										width : 150,
										sortable : true,
										dataIndex : 'menu_name',
										 editor: {
								                  xtype: 'textfield',
								                   allowBlank: false
								              }
									}, {
										header : "级别",
										width : 80,
										sortable : true,
										dataIndex : 'level',
										editor: {
								                xtype: 'numberfield',
								                allowBlank: false,
								                minValue: 1,
								                maxValue: 10
								             }
									}, {
										header : "是否是叶子",
										width : 100,
										sortable : true,
										dataIndex : 'isleaf',
										trueText: 'Yes',
							            falseText: 'No',
							            editor: {
							                xtype: 'checkbox'
							            }
									}, {
										header : "菜单链接",
										width : 200,
										sortable : true,
										dataIndex : 'menu_url',
										editor: {
								                  xtype: 'textfield',
								                   allowBlank: false
								              }
									}, {
										header : "上级菜单",
										width : 100,
										sortable : true,
										dataIndex : 'parentId',
										 editor: {
								                  xtype: 'combo',
								                   allowBlank: false
								              }
									},{
									    header:'创建时间',
									    width:120,
									    dataIndex:'created_time',
									    renderer:date_render,
									    editor: {
								                xtype: 'datefield',
								                allowBlank: false,
								                minValue: '2015-11-11',
								                minText: 'Can\'t have a start date before the company existed!',
								                maxValue: (new Date()).myFormat('yyyy-MM-dd hh:mm:ss')
								            }
									}]),
						//    view:new Ext.ux.grid.LockingGridView(),
						    sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
						    frame:true,
						    plugins: [editor],//添加行编辑器插件
					        iconCls:'icon-grid',
					        // config options for stateful behavior
					        stateful: true
                           });
 this.tbar = [
				    {
				       text:'过滤',
				       iconCls:"filter",
				       handler:handler_query,
				       hidden:true
				    },
				    '-',
				    {
				        text:'新增',
				        iconCls:"page_add",
				        handler:handler_add
					},
					{
				        text:'删除',
				        iconCls:"page_delete",
				        handler:handler_delete
					},'-',
					{
				        text:'刷新',
				        iconCls:"page_refresh",
				        handler:handler_refresh
					},'-',
					{
						text:'导出',
				        iconCls:"page_excel",
				        handler:handler_gridToExcel
					}
			   ];

	function date_render(v){
		return new Date(v).myFormat('yyyy-MM-dd hh:mm:ss');
	}		   
			   
	 function handler_query(){}	
	 function handler_add(){}
	 function handler_delete(){}
	 function handler_refresh(){}
	 function handler_gridToExcel(){}
	 
   	 this.items=[this.menuManager_grid];
   	 MenuManager.MainPanel.superclass.initComponent.call(this);
   }

});


// 对js中Date的方法扩展，将 Date 转化为指定格式的String
// 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
// 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
// 例子： 
// (new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
// (new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.myFormat = function (fmt) {  
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
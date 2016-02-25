Ext.namespace("UserManager")
UserManager.MainPanel = Ext.extend(Ext.Panel,{
   title:'用户管理',
   layout:'fit',
   frame:true,
   collapsible : false, //不允许窗口折叠
   initComponent : function(){
     
   	 this.userManager_store = new Ext.data.Store({
					        reader: new Ext.data.JsonReader({
					        	      
										        idProperty: 'userId',
										        root: 'querydatas',
										        totalProperty: 'totals',
										        fields: [
										            {name:'user_id',mapping:'user_id'},
										            {name: 'username'},
										            {name: 'password', mapping: 'password'},
										            {name:'nickname',mapping:'nickname'},
										            {name:'age',mapping:'age'},
										            {name:'sex',mapping:'sex'},
										            {name:'status',mapping:'status'},
										            {name:'login_err_count'},
										            {name:'birthday'},
										            {name:'telPhone'},
										            {name:'company'},
										            {name:'address'},
										            {name:'mail'},
										            {name:'register_time'},
										            {name:'last_login_time'},
										            {name:'parentId'}
	
										        ],
										        sortInfo: {field: 'age', direction:'ASC'} // used by store to set its sortInfo
										    

					              }),
					        proxy:new Ext.data.HttpProxy({
					           url:BasePath+'userManager/list/users'
					       }),
					       autoLoad:true
					    })
					    
					      // row expander
						    var expander = new Ext.ux.grid.RowExpander({
						        tpl : new Ext.Template(
						            '<p><b>姓名:</b> {username}</p><br>',
						            '<p><b>公司:</b> {company}</p>',
						            '<p><b>性别:</b> {sex}</p>',
						            '<p><b>tel:</b> {telPhone}</p>',
						            '<p><b>住址:</b> {address}</p>',
						            '<p><b>电子邮箱:</b> {mail}</p>'
						        )
						    });
   	 this.userManager_grid = new Ext.grid.GridPanel({
   	 	                    id:'UserManager.MainPanel.userManager_grid',
					   	    store: this.userManager_store,
					      // 使用colModel，在里面放入锁定列的模型,和公共属性
					   	     colModel:new Ext.ux.grid.LockingColumnModel({
					   	     defaults: {
                                         width: 20,
                                         sortable: true },
					   	     columns:[  expander,
								   	    new Ext.grid.RowNumberer({ locked:true}),//编号，从1开始
					   	                new Ext.grid.CheckboxSelectionModel({locked:true}),//单选框
								        {id:'user_id', header: "ID",hidden: true, locked:true,dataIndex: 'user_id'},
								        {header: "姓名", width: 80 ,locked:true,dataIndex: 'username'},
								        {header: "密码", width: 120,renderer:function(v){return '******';}, dataIndex: 'password'},
								        {header: "昵称", width: 80, dataIndex: 'nickname'},
								        {header: "年龄", width: 35, dataIndex: 'age'},
								        {header: "性别", width: 60, renderer:render_sex, dataIndex: 'sex'},
			                            {header: "状态", width: 50, renderer:render_status, dataIndex: 'status'},
			                            {header: "出生日期", width: 100, renderer:function(v){return new Date(v).format('Y-m-d');}, dataIndex: 'birthday'},
			                            {header: "手机号码", width: 100,  dataIndex: 'telPhone'},
			                            {header: "公司名称", width: 100,  dataIndex: 'company'},
			                            {header: "家庭住址", width: 150,  dataIndex: 'address'},
			                            {header: "电子邮箱", width: 150,  dataIndex: 'mail'},
			                            {header: "注册时间", width: 150, renderer:function(v){return new Date(v).myFormat('yyyy-MM-dd hh:mm:ss');}, dataIndex: 'register_time'},
			                            {header: "最后一次登陆",width:200 ,renderer:function(v){return new Date(v).myFormat('yyyy-MM-dd hh:mm:ss');}, dataIndex: 'last_login_time'}]
                                        }),
						    view:new Ext.ux.grid.LockingGridView(),
						    sm: new Ext.grid.RowSelectionModel({singleSelect:true}),
						    width:600,
						    height:300,
						    frame:true,
					        iconCls:'icon-grid',
					        plugins: expander,
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
					},'-',
					{
				        text:'修改',
				        iconCls:"page_edit",
				        handler:handler_edit
					},'-',
					{
				        text:'删除',
				        iconCls:"page_delete",
				        handler:handler_deleteUser
					},'-',
					{
				        text:'解锁/重置密码',
				        iconCls:"lock_open",
				        handler:handler_unLock
					},'-',
					{
				        text:'锁定',
				        iconCls:"lock",
				        handler:handler_lock
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
			   
	 function render_sex(value){
		   if (value) {
		    return "<img src='"+BasePath+"images/famicons/user_suit.png' />";
		   } else {
		    return "<img src='"+BasePath+"images/famicons/user_female.png' />";
		   }
	 }
	  function render_status(value){
		   if (value) {
		    return "<img src='"+BasePath+"images/hmenu-unlock.gif' />";
		   } else {
		    return "<img src='"+BasePath+"images/hmenu-lock.gif' />";
		   }
	 }	
	 function handler_query(){}	
	 function handler_add(){
	 var addPanel = new UserManager.adduser();
	 addPanel.show();
	 }
	 function handler_edit(){}
	 function handler_deleteUser(){}
	 function handler_unLock(){}
	 function handler_lock(){}
	 function handler_refresh(){}
	 function handler_gridToExcel(){}
	 
   	 this.items=[this.userManager_grid];
   	 UserManager.MainPanel.superclass.initComponent.call(this);
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
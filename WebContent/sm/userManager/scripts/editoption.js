Ext.namespace("UserManager");
UserManager.adduser = Ext.extend(Ext.Window,{
   title:'用户添加',
   layout:'fit',
   width:500,
   height:300,
   modal:true,
   frame:true,
   collapsible : false, //不允许窗口折叠
   initComponent : function(){
   	 var chooseuser = new Ext.form.TwinTriggerField({
   	                    fieldLabel:'用户组',
   	                    trigger1Class :'x-form-search-trigger',
						trigger2Class :'x-form-clear-trigger',
						onTrigger1Click : function(){
						 alert(1);
						},
						onTrigger2Click : function(){
						alert(2);}
   	                    });
    this.mainform = new Ext.form.FormPanel({
        items:[chooseuser]
    });
    
    this.items = [this.mainform];
   	UserManager.adduser.superclass.initComponent.call(this);
   	
   }
});
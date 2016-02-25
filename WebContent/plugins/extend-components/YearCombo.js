
		YearCombo = function(config) {
			Ext.applyIf(this, config);
			this.initUIComponents();
			YearCombo.superclass.constructor.call(this);
		};
Ext.extend(YearCombo, Ext.form.ComboBox, {
initUIComponents : function() {
		var yearArray = [];
		var currYear = new Date().getFullYear();
		for(var i = 2008;i <= currYear; i ++)
		{
			yearArray.push([i.toString(), i.toString()]);
		}
		var store1 = new Ext.data.SimpleStore({
		    fields: [
		    	{name:'index',type: 'string'},
		    	{name:'year',type: 'string'}],
		    data:yearArray
		});
		
		Ext.apply(this, {
		    fieldLabel: this.fieldLabel || '查询年份',
		    store: store1,
		    displayField:'year',
		    valueField:'index',
		    mode: 'local',
		    forceSelection : true, 
		    triggerAction:'all',
		    value:this.value||currYear,
		    relValue :this.relValue||currYear,
		    width :this.width || 150,
		    editable:false,
		    additionFu:this.additionFu||null,
		    //readOnly:true,
		    listeners:{
			    'select':function(v){
			    	this.relValue = v.getValue();
			    	
			    	if(this.additionFu){
			    		this.additionFu();
			    	}
			    }
		    }
		});
	}
});

Ext.reg('yearcombo', YearCombo); 

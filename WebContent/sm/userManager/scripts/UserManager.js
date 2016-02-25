var viewport;
var mainPanel;

var BasePath = "";
var userId;

var today;


Ext.onReady(
	function()
    {
    	//~global variable
    	BasePath = document.getElementById("BasePath").value;
       // userId = document.getElementById('userId').value;

        Ext.QuickTips.init();
        var tempDate = new Date();
        tempDate.setTime(tempDate.getTime());
        today = tempDate
        

        Ext.lib.Ajax.defaultPostHeader += '; charset=utf-8';
        Ext.BLANK_IMAGE_URL =BasePath+'images/s.gif';
        show();
        Ext.get("loading").remove();
    }
);

function show(){
	
	mainPanel = new UserManager.MainPanel();

		viewport = new Ext.Viewport({
			layout:'fit',
			border:false,
		    items:[mainPanel]
	    });
	
   // mainPanel.refresh();
}


	
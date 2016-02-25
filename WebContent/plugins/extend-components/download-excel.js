/**
 * 构造函数
 * @param 1:序号
 * @param 2:列名
 * @param 3:列宽,默认100
 * @param 4:列对齐方式
 * @param 5:是否隐藏列
 */
function getColumnInfo(gird)
{
	var columnModel = gird.getColumnModel();

	var store = gird.getStore();

	if(columnModel == null||store == null||store.fields==null)
	{
		return false;
	}

	var columnInfoArray = [];

	var index = 0;
	for(var i=0 ;i<=columnModel.getColumnCount();i++)
	{
		var c = columnModel.getColumnById(i);
		if(c == null||c==undefined)
		{
			continue;
		}
		var column = {};
		column.index = index;
		column.colName = c.header!=null?c.header:"";
		column.colMapping = store.fields.map[c.dataIndex].mapping;
		column.colWidth = c.width!=null?c.width:150;
		
		if(c.align == null)
		{
			column.colAlign = 1;
		}
		else
		{
			if(c.align.toUpperCase() == "LEFT")
			{
				column.colAlign = 1;
			}
			else if(c.align.toUpperCase() == "CENTER")
			{
				column.colAlign = 2;
			}
			else if(c.align.toUpperCase() == "RIGHT")
			{
				column.colAlign = 3;
			}
		}
		column.colHidden = c.hidden!=null?c.hidden:false;
		if(column.colHidden == true)
		{
			continue;
		}
		column.colDataType = c.exportType!=null?c.exportType:'';
		
		columnInfoArray.push(column);

		index++;
	}
	if(columnInfoArray.length ==0)
	{
		return false;
	}

	return columnInfoArray;
}

function downloadExcel(url,gird)
{
	var columnInfo = getColumnInfo(gird);
	downloadExcelRun(url,columnInfo,store.baseParams)
}

function downloadExcelRun(url,columnInfo,params,filename)
{
	if(columnInfo == false)
	{
		return false;
	}
	var input ={
			"jsonColumns":$.toJSON( columnInfo ),
			"jsonParams":$.toJSON( params )
	}

	$.blockUI({
		message : '<h3> 正在导出Excel文档,请稍候...</h3>',
		css : {
			border : 'none',
			padding : '15px',
			backgroundColor : '#000',
			'-webkit-border-radius' : '10px',
			'-moz-border-radius' : '10px',
			opacity : .5,
			color : '#fff'
		}
	});
	
	$.ajax({
	      url: url,
	      type: "POST",  
	      data:input,
	      success: function(msg){
	    	  var fileAddress  = eval("("+msg+")");
	    	  
	    	  window.open( document.getElementById('basePath').value+"downloadExcel.action?inputPath="+(fileAddress.fileAddress),'_self');
	    	  $.unblockUI();
		   },
		   failure: function(msg) {
		   }
	});
	
}
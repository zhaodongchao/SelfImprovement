Ext.namespace('MoneyToBig');
MoneyToBig.MoneyToBig = function(num)
{
	while(num.indexOf(",")>0)
		num = num.replace(",","");
	while(num.indexOf(" ")>0)	
		num = num.replace(" ","");
	while(num.indexOf("￥")>0)
		num = num.replace("￥","");
	
	if(isNaN(num)) 
      { 
          alert('请检查小写金额是否正确!'); 
          return; 
      } 
	else
	{
      	if(num<0)
      	{
      		num = num*(-1);
      	}
         //保留两位有效数字
        num = Math.round(num*Math.pow(10,2))/Math.pow(10,2);
    }
     var NumStr = String(num);
     var NumChar = '零壹贰叁肆伍陆柒捌玖'; 
     var UnitChar1 = ' 拾佰仟 拾佰仟 拾佰仟 拾佰仟'; 
     var UnitChar2 = ' 万亿兆'; 
     var Len; 
     var LeftLen,LeftStr; 
     var RightLen,RightStr; 
     var BigStr = ''; 
     Len = NumStr.length; 
     //根据小数点分割字符串
     if(NumStr.indexOf('.')==-1)//没有小数 
     { 
         LeftStr = NumStr; 
         LeftLen = Len; 
         RightStr = ''; 
         RightLen = 0; 
     } 
     else
    { 
        LeftStr = (NumStr.split('.'))[0]; 
         LeftLen = LeftStr.length; 
         RightStr = (NumStr.split('.'))[1]; 
         RightLen = RightStr.length; 
    } 
     //转换整数部分
     for(var i=0;i<LeftLen;i++)
     {
         //按位取小写数字
         var LeftTempNum = parseInt(LeftStr.substring(i,i+1));
         //转换成大写
         var LeftTempStr = NumChar.substring(LeftTempNum,LeftTempNum+1);
         //数字位为零且不是一位数
         if(LeftTempStr=='零' && LeftLen!=1)
         {
             //下一位数字为零或者为万(个)位
             if(LeftStr.substring(i+1,i+2)=='0' || (LeftLen-i)%4==1)
             {
                 LeftTempStr = '';
             }
         }
         else
         {
             //加上读的单位，拾佰仟之类的
             LeftTempStr += UnitChar1.substring(LeftLen-i-1,LeftLen-i).replace(' ','');
         }
         //万位或个位
         if((LeftLen-i)%4==1)
         {
         LeftTempStr += UnitChar2.substring(parseInt((LeftLen-i)/4),parseInt((LeftLen-i)/4)+1);
             if(i>2)
             {
                 if(LeftStr.substring(i-3,i+1)=='0000')
                 {
                     LeftTempStr = LeftTempStr.substring(0,LeftTempStr.length-1);
                 }
             }
         }
         BigStr += LeftTempStr.replace(' ','');
     }
     //处理小数部分
     if(RightLen==0) 
     { 
         BigStr += '元整'; 
     }
     else
     {
         BigStr += '元';
         for(var i=0;i<RightLen;i++)
         {
             //按位取小写数字
             var RightTempNum = parseInt(RightStr.substring(i,i+1));
             //转换成大写
             var RightTempStr = NumChar.substring(RightTempNum,RightTempNum+1);
             if (i==0)
             {
                 RightTempStr+="角";
             }
             if (i==1)
             {
                 RightTempStr+="分";
             }
             BigStr += RightTempStr;
         }
     }
     return BigStr;
}
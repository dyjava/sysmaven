/**
 * 	举例：formatter :function (v) {
                return Common.DateTimeFormatter(v);
            }
 */
 $(function(){
        $.extend($.fn.validatebox.defaults.rules,{
        phone : {// 验证电话号码 
	        validator : function(value) { 
	            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
	        }, 
	        message : '格式不正确,请使用下面格式:020-88888888' 
        },
        idcard : {// 验证身份证 
	        validator : function(value) { 
	            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value); 
	        }, 
	        message : '身份证号码格式不正确' 
        },
       mobile : {// 验证手机号码 
	        validator : function(value) { 
	            return /^(13|15|18|17)\d{9}$/i.test(value); 
	        }, 
	        message : '手机号码格式不正确' 
       }, 
       intOrFloat : {// 验证整数或小数 
	        validator : function(value) { 
	            return /^\d+(\.\d+)?$/i.test(value); 
	        }, 
	        message : '请输入数字，并确保格式正确' 
       }, 
       currency : {// 验证货币 
	        validator : function(value) { 
	            return /^\d+(\.\d+)?$/i.test(value); 
	        }, 
	        message : '货币格式不正确' 
       }, 
       qq : {// 验证QQ,从10000开始 
	        validator : function(value) { 
	            return /^[1-9]\d{4,9}$/i.test(value); 
	        }, 
	        message : 'QQ号码格式不正确' 
       }, 
       integer : {// 验证整数 
	        validator : function(value) { 
	            return /^[+]?[-]?[1-9]+\d*$/i.test(value); 
	        }, 
	        message : '请输入整数' 
       }, 
       age : {// 验证年龄
	        validator : function(value) { 
	            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value); 
	        }, 
	        message : '年龄必须是0到120之间的整数' 
       },
	   chinese : {// 验证中文 
	        validator : function(value) { 
	            return /^[\Α-\￥]+$/i.test(value); 
	        }, 
	        message : '请输入中文' 
	   }, 
	   english : {// 验证英语 
	        validator : function(value) { 
	            return /^[A-Za-z]+$/i.test(value); 
	        }, 
	        message : '请输入英文' 
	   }, 
	   unnormal : {// 验证是否包含空格和非法字符 
	       validator:function(value){
	          return /^[a-zA-Z0-9\u4E00-\u9FA5]+$/.test(value);
	       }, 
	       message : '输入值不能为空和包含其他非法字符' 
	   }, 
	   username : {// 验证用户名 
	        validator : function(value) { 
	            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value); 
	        }, 
	        message : '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）' 
	   }, 
	   faxno : {// 验证传真 
		   validator : function(value) { 
//            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value); 
			   return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value); 
		   }, 
		   message : '传真号码不正确' 
	   }, 
	   zip : {// 验证邮政编码 
	        validator : function(value) { 
	            return /^[1-9]\d{5}$/i.test(value); 
	        }, 
	        message : '邮政编码格式不正确' 
	   }, 
	   ip : {// 验证IP地址 
	        validator : function(value) { 
	            return /^(?:(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.){3}(?:\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])$/i.test(value); 
	        }, 
	        message : 'IP地址格式不正确' 
	   }, 
	   name : {// 验证姓名，可以是中文或英文 
            validator : function(value) { 
                return /^[\Α-\￥]+$/i.test(value)|/^\w+[\w\s]+\w+$/i.test(value); 
            }, 
            message : '请输入姓名' 
	   },
		    isNull : {// 验证选中是否为空 
	        validator : function(value) { 
	            return /^[ ]+$/i.test(value); 
	        }, 
	        message : '下拉列表不能为空！' 
	   },
	   onSelect : {// 日期比较
	        validator : function(begin,end) { 
	        	var ed= new Date(begin);
	        	var s =$(end[0]).datebox('getValue');
	        	if(s!=null){
	        		var bg =new Date(s);
	            	if(bg<ed){
	            		return true;
	            	}
	        	}else{
	        		return true;
	        	}
	            return false; 
	        }, 
	        message : '后面日期必须要大于前面日期' 
	    },
	    date : { 
	        validator : function(value) { 
	         //格式yyyy-MM-dd或yyyy-M-d
	          //  return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value); 
	        	  var sDate=DateStr.replace(/(^\s+|\s+$)/g,'');//去两边空格; 
	        	    if(sDate==''){ 
	        	        return true; 
	        	    } 
	        	    //如果格式满足YYYY-(/)MM-(/)DD或YYYY-(/)M-(/)DD或YYYY-(/)M-(/)D或YYYY-(/)MM-(/)D就替换为'' 
	        	    //数据库中，合法日期可以是:YYYY-MM/DD(2003-3/21),数据库会自动转换为YYYY-MM-DD格式 
	        	    var s=sDate.replace(/[\d]{ 4,4 }[\-/]{1}[\d]{1,2}[\-/]{1}[\d]{1,2}/g,''); 
	        	    if(s==''){//说明格式满足YYYY-MM-DD或YYYY-M-DD或YYYY-M-D或YYYY-MM-D 
	        	        var t=new Date(sDate.replace(/\-/g,'/')); 
	        	        var ar=sDate.split(/[-/:]/); 
	        	        if(ar[0]!=t.getYear()||ar[1]!=t.getMonth()+1||ar[2]!=t.getDate()){//alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。'); 
	        	            return false; 
	        	        } 
	        	    }else{//alert('错误的日期格式！格式为：YYYY-MM-DD或YYYY/MM/DD。注意闰年。'); 
	        	        return false; 
	        	    } 
	        	    return true; 
	        },
	        message : '清输入正确的日期格式'
	    },
	    //| 日期时间有效性检查   格式为：YYYY-MM-DD HH:MM
	   dateTimeHHMMSS:{ 
		   validator : function(value) { 
	        var reg=/^(\d+)-(\d{ 1,2})-(\d{ 1,2})(\d{ 1,2}):(\d{1,2}):(\d{1,2})$/; 
	        var r=str.match(reg); 
	        if(r==null) return false; 
	        r[2]=r[2]-1; 
	        var d= new Date(r[1],r[2],r[3],r[4],r[5],r[6]); 
	        if(d.getFullYear()!=r[1]) return false; 
	        if(d.getMonth()!=r[2]) return false; 
	        if(d.getDate()!=r[3]) return false; 
	        if(d.getHours()!=r[4]) return false; 
	        if(d.getMinutes()!=r[5]) return false; 
	        if(d.getSeconds()!=r[6]) return false; 
	        return true; 
		   },
	       message : '清输入正确的时间格式'
	    } ,
	  //| 日期时间有效性检查   格式为：YYYY-MM-DD HH:MM
	    dateTimeHHMM:{ 
	 	   validator : function(value) { 
	 		   alert(value);
	         var reg=/^(\d+)-(\d{ 1,2})-(\d{ 1,2})(\d{ 1,2}):(\d{1,2})$/; 
	         var r=str.match(reg); 
	         alert(r);
	         if(r==null) return false; 
	         r[2]=r[2]-1; 
	         var d= new Date(r[1],r[2],r[3],r[4],r[5]); 
	         if(d.getFullYear()!=r[1]) return false; 
	         if(d.getMonth()!=r[2]) return false; 
	         if(d.getDate()!=r[3]) return false; 
	         if(d.getHours()!=r[4]) return false; 
	         if(d.getMinutes()!=r[5]) return false; 
	         return true; 
	 	   },
	       message : '清输入正确的时间格式'
     } ,
     msn:{ 
        validator : function(value){ 
        	return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value); 
	    }, 
	    message : '请输入有效的msn账号(例：abc@hotnail(msn/live).com)' 
    },
    same:{ 
         validator:function(value,param){
            return $(param[0]).val() == value;
        },
        message:'字段不匹配'  
    },
    //出行天数、价格比较
    numberTo:{ 
        validator:function(value,param){
           return parseInt($(param[0]).val()) <= parseInt(value);
       },
       message:'前面天数必须大于等于后面的天数'  
   },
    //验证汉字  
	validCode: {  
      validator: function (value) {  
      	v=/^[A-Z][A-Z0-9]{3,7}$/;
          return v.test(value);  
      },  
      message: '请输入4-8位首字母大写的大写字母或数字！'  
  },
  validName: {  
      validator: function (value) {  
      	var reg =/^[a-zA-Z0-9\u4E00-\u9FA5_-]{1,16}$/;
          return reg.test(value);  
      },  
      message: '请输入1-16位字母数字汉字下划线中线！'  
  },
  validCatalogCode : {// 字典编码
	validator : function(value) {
		var reg =/^[A-Z][a-zA-Z0-9_-]{3,5}$/;
		return reg.test(value);
	},
	message : '字典编码不合法（大写字母开头，允许4-6字节，允许字母数字下划线和中线）'
  },
  validAirportCode : {// 机场三字代码
	  validator : function(value) {
		  var reg =/^[a-zA-Z]{3}$/;
		  return reg.test(value);
	  },
	  message : '机场三字代码不合法（请输入三个英文字母）'
  },
  validFlightNumber : {// 航班号
	  validator : function(value) {
		  var reg =/^[a-zA-Z]{2}[0-9]{3,4}$/;
		  return reg.test(value);
	  },
	  message : '航班号不合法（请输入前两位英文，后跟3位或4位数字）'
  },
  maxLength : { //长度范围
	  validator : function(value, param){
		  return value.length <= param[0];
	  },
	  message : '最大长度不能超过{0}位'
  }
})
})

Date.prototype.pattern = function (fmt) {
	  var o = {
	    "M+": this.getMonth() + 1, //月份		
	    "d+": this.getDate(), //日		
	    "h+": this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, //小时		
	    "H+": this.getHours(), //小时		
	    "m+": this.getMinutes(), //分		
	    "s+": this.getSeconds(), //秒		
	    "q+": Math.floor((this.getMonth() + 3) / 3), //季度		
	    "S": this.getMilliseconds() //毫秒		
	  };
	  var week = {
	    "0": "/u65e5",
	    "1": "/u4e00",
	    "2": "/u4e8c",
	    "3": "/u4e09",
	    "4": "/u56db",
	    "5": "/u4e94",
	    "6": "/u516d"
	  };
	  if (/(y+)/.test(fmt)) {
	    fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
	  }
	  if (/(E+)/.test(fmt)) {
	    fmt = fmt.replace(RegExp.$1, ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "") + week[this.getDay() + ""]);
	  }
	  for (var k in o) {
	    if (new RegExp("(" + k + ")").test(fmt)) {
	      fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
	    }
	  }
	  return fmt;
	}

//日期控件格式化方法
function dateFormatter(date){
	var y = date.getFullYear();
	var m = date.getMonth()+1;
	var d = date.getDate();
	return y+'-'+(m<10?('0'+m):m)+'-'+(d<10?('0'+d):d);
}
function dateParser(s){
	if (!s) return new Date();
	var ss = (s.split('-'));
	var y = parseInt(ss[0],10);
	var m = parseInt(ss[1],10);
	var d = parseInt(ss[2],10);
	if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
		return new Date(y,m-1,d);
	} else {
		return new Date();
	}
}
function showProcess(isShow, title, msg) {
    if (!isShow) {
         $.messager.progress('close');
         return;
     }
     var win = $.messager.progress({
         title: title,
         msg: msg
     });
}

function showMsg(title, msg, isAlert) {
     if (isAlert !== undefined && isAlert) {
         $.messager.alert(title, msg);
     } else {
         $.messager.show({
             title: title,
             msg: msg,
             showType: 'show'
         });
    }
}
//----------------------------------------------------------------------
//<summary>
//限制只能输入数字和字母
//</summary>
//----------------------------------------------------------------------
$.fn.onlyNumAlpha = function () {
$(this).keypress(function (event) {
   var eventObj = event || e;
   var keyCode = eventObj.keyCode || eventObj.which;
   if ((keyCode >= 48 && keyCode <= 57) || (keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122) || keyCode==8 ||keyCode==9 ||keyCode==46)
       return true;
   else
       return false;
}).focus(function () {
   this.style.imeMode = 'disabled';
}).bind("paste", function () {
   var clipboard = window.clipboardData.getData("Text");
   if (/^(\d|[a-zA-Z])+$/.test(clipboard))
       return true;
   else
       return false;
});
};

//----------------------------------------------------------------------
//<summary>
//限制只能输入字母
//</summary>
//----------------------------------------------------------------------
$.fn.onlyAlpha = function () {
 $(this).keypress(function (event) {
     var eventObj = event || e;
     var keyCode = eventObj.keyCode || eventObj.which;
     if ((keyCode >= 65 && keyCode <= 90) || (keyCode >= 97 && keyCode <= 122))
         return true;
     else
         return false;
 }).focus(function () {
     this.style.imeMode = 'disabled';
 }).bind("paste", function () {
     var clipboard = window.clipboardData.getData("Text");
     if (/^[a-zA-Z]+$/.test(clipboard))
         return true;
     else
         return false;
 });
};
//----------------------------------------------------------------------
//<summary>
//限制只能输入数字
//</summary>
//----------------------------------------------------------------------
$.fn.onlyNum = function () {
 $(this).keypress(function (event) {
     var eventObj = event || e;
     var keyCode = eventObj.keyCode || eventObj.which;
     if ((keyCode >= 48 && keyCode <= 57))
         return true;
     else
         return false;
 }).focus(function () {
 //禁用输入法
     this.style.imeMode = 'disabled';
 }).bind("paste", function () {
 //获取剪切板的内容
     var clipboard = window.clipboardData.getData("Text");
     if (/^\d+$/.test(clipboard))
         return true;
     else
         return false;
 });
};

   /*
 * 日期时间合法性验证函数，合法的格式包括(yyyy-mm-dd hh:mm:ss)
 * param str String 待验证的字符串
 * return 如果合法,返回true
 */
function checkDateTimeS(str){                 
    var reg = /^(\d+)-(\d{1,2})-(\d{1,2}) (\d{1,2}):(\d{1,2}):(\d{1,2})$/; 
    var r = str.match(reg); 
    if(r==null)return false; 
    r[2]=r[2]-1; 
    var d= new Date(r[1], r[2],r[3], r[4],r[5], r[6]); 
    if(d.getFullYear()!=r[1]) return false; 
    if(d.getMonth()!=r[2]) return false; 
    if(d.getDate()!=r[3]) return false; 
    if(d.getHours()!=r[4]) return false; 
    if(d.getMinutes()!=r[5]) return false; 
    if(d.getSeconds()!=r[6])return false; 
    return true;
}
//获取URL地址里的参数
function GetRequest() {
	   var url = location.search; //获取url中"?"符后的字串
	   var theRequest = new Object();
	   if (url.indexOf("?") != -1) {
	      var str = url.substr(1);
	      strs = str.split("&");
	      for(var i = 0; i < strs.length; i ++) {
	         theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]);
	      }
	   }
	   return theRequest;
	}

//esayui messager 按钮显示中文
$.messager.defaults={ok:"确定",cancel:"取消"};
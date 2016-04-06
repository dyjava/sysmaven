/**
 * 扩展JQuery表单数据搜集 将一个表单的数据返回成JSON对象
 */
$.fn.serializeObject = function() {
	var o = {};
	var a = this.serializeArray();
	$.each(a, function() {
				if (o[this.name]) {
					if (!o[this.name].push) {
						o[this.name] = [o[this.name]];
					}
					o[this.name]
							.push(this.value === 0 ? 0 : (this.value || ''));
				} else {
					o[this.name] = this.value === 0 ? 0 : (this.value || '');
				}
			});
	return o;
};

/**
 * 获取URL中的参数
 * @param name
 * @returns
 */
function GetQueryString(name){
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null)return  unescape(r[2]); return null;
}

/**
 * 弹框
 * @param url
 * @param title
 */
function dialogOpen(url,title){
	dialogOpen(url,title,"85%","90%") ;
}
function dialogOpen(url,title,width,weight){
	$("#dialog").dialog({
        autoOpen:false,   
        modal:true,      
        draggable:true,  
        closeOnEscape:false,       
        title:title,  
        width:width,  
        height:weight,  
        position:"center",  
        resizable:true, 
        href:url,
        });   
     $("#dialog").dialog("open");
}
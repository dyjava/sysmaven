//针对jQuery发出的ajax进行全局设置
var _basePath = "/caissauic"; 
$.ajaxSetup({
	headers:{"ajax":"ajax"},
	contentType: "application/x-www-form-urlencoded;charset=UTF-8",
	beforeSend:function(){
		//由于全部为ajax请求，对于页面加载过程中通过URL传递的search参数不能正常使用，在此处占用hash位进行处理
		if(this.url.indexOf(".do") != -1 || this.url.indexOf(".html") != -1){
			var params = this.url.substring(this.url.indexOf("?")+1 , this.url.length);
			if(this.data){
				params +="&"+this.data;
			}
			if(params && "" != $.trim(params)){
				window.location.hash = $.trim(params);
			}
		}
	},
	error:function(request, status, error){
		//alertMsg.error("与服务器交互发生错误！");
		alert("与服务器交互发生错误！");
	}
});

var Caissa = {
		//使用ajax的方式加载远程数据
		loadScript:function(argUrl,reqDatas,callback){
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT;
			reqDatas+="&_dt=script&_cb="+callback;
			jQuery.ajax({ url:url, type:"POST", async:true, dataType:"script",data:reqDatas});
		},
		loadJson:function(argUrl,reqDatas,succCallBack,errorCallback){
			//Caissa.loadData(argUrl,reqDatas,"json",null);
			radomT="?_v="+(new Date()).getTime();
			var url=_basePath+argUrl+radomT;
			reqDatas+="&_dt=json";
			jQuery.ajax({ url:url, type:"POST", async:true, dataType:"json",data:reqDatas,success:succCallBack,error:errorCallback});
		},
		loadModal:function(){ 
			 jQuery("<img src='"+_basePath+"/images/logo.jpg'>").modal({
                escClose:false,  
                close:true,
                overlayCss: {backgroundColor:"dimgrey"},
                zIndex:"9999999"
            });
        },
        closeModal:function(){
        	jQuery.modal.close();
        },
        //处理数据，加载jstemplate模板
		processTemplateData: function(show,templateId,data){
            jQuery(show).setTemplateElement(templateId).processTemplate(data); 
		},
		displayPager:function(continer,currPageNo,pageCount,clickHandler){
			$(continer).pager({ pagenumber: currPageNo, pagecount: pageCount, buttonClickCallback: clickHandler });
		},
		//获取url参数中指定名称的取值
        getUrlParam:function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.search.substr(1).match(reg);
			if (r!=null) return unescape(r[2]); return null;
		},
		getHashParam : function(name){
			var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
			var r = window.location.hash.substr(1).match(reg);
			if (r!=null) return unescape(r[2]); return null;
		},
		//将key=val&key=val&...转为json对象
		params2Json : function(data){
			var ret = {};
		    data.substr(1).replace(/([\w.]+)=([\w.]+)/ig, function(a, b, c){ret[b] = unescape(c);console.log("a="+a+";b="+b+";c="+c)});  
		    return ret; 
		},
		//将url中的参数转为json对象
		getUrlParamsJson : function(){
			return Caissa.params2Json(window.location.search);
		},
		getHashParamsJson : function(){
			return Caissa.params2Json(window.location.hash);
		},
		/**
		 * 使用正则表达式验证是否匹配规则
		 * @param regexp ： 正则表达式
		 * @param data ： 待验证的数据
		 * @return true / false
		 */
		regexpTest : function(regexp , data){
			return regexp.test(data);
		},
		regexp : {
			mobile : /1[3|5|7|8|][0-9]{9}/ , 
			email : /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/
		}
};

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
					o[this.name] = this.value === 0 ? 0 : (this.value || '')
				}
			});
	return o;
};
//分页
//container 容器，count 总页数 pageindex 当前页数
function setPage(container, count, pageindex) {
	var container = container;
	var count = count;
	var pageindex = pageindex;
	var li = [];
	//总页数少于10 全部显示,大于10 显示前3 后3 中间3 其余....
	if (pageindex == 1) {
		li[li.length] = "<li href=\"#\" class=\"prev unclick\"><<上一页</li>";
	} else {
		li[li.length] = "<li href=\"#\" class=\"prev\"><<上一页</li>";
	}
	function setPageList() {
		if (pageindex == i) {
			li[li.length] = "<li href=\"#\" class=\"active\">" + i + "</li>";
		} else {
			li[li.length] = "<li href=\"#\">" + i + "</li>";
		}
	}
		  //总页数小于10
		  if (count <= 10) {
			for (var i = 1; i <= count; i++) {
			  setPageList();
			}
		  }
		  //总页数大于10页
		  else {
			if (pageindex <= 4) {
			  for (var i = 1; i <= 5; i++) {
				setPageList();
			  }
			  li[li.length] = "<span>...</span><li href=\"#\">" + count + "</li>";
			} else if (pageindex >= count - 3) {
			  li[li.length] = "<li href=\"#\">1</li><span>...</span>";
			  for (var i = count - 4; i <= count; i++) {
				setPageList();
			  }
			}
			else { //当前页在中间部分
			  li[li.length] = "<li href=\"#\">1</li><span>...</span>";
			  for (var i = pageindex - 2; i <= pageindex + 2; i++) {
				setPageList();
			  }
			  li[li.length] = "<span>...</span><li href=\"#\">" + count + "</li>";
			}
		  }
		  if (pageindex == count) {
			li[li.length] = "<li href=\"#\" class=\"next unclick\">下一页>></li>";
		  } else {
			li[li.length] = "<li href=\"#\" class=\"next\">下一页>></li>";
		  }
		  container.innerHTML = li.join("");
		  //事件点击
		  var pageClick = function() {
			var oAlink = container.getElementsByTagName("li");
			var inx = pageindex; //初始的页码
			oAlink[0].onclick = function() { //点击上一页
			  if (inx == 1) {
				return false;
			  }
			  inx--;
			  setPage(container, count, inx);
			  return false;
			}
			for (var i = 1; i < oAlink.length - 1; i++) { //点击页码
			  oAlink[i].onclick = function() {
				inx = parseInt(this.innerHTML);
				setPage(container, count, inx);
				return false;
			  }
			}
			oAlink[oAlink.length - 1].onclick = function() { //点击下一页
			  if (inx == count) {
				return false;
			  }
			  inx++;
			  setPage(container, count, inx);
			  return false;
			}
		  } ()
		}
// 页面初始化加载
$(document).ready(function() {
	$(".rightmenulink a").click(function(e){
		$(".rightmenu").addClass("rightmenushow");
		var v_id = $(e.target).attr('id');
		$("a.top").animate({"right":"200px"},300);
	})
	$(".topmenu a").click(function(){
		$(".topmenu a").removeClass("show");
		$(this).addClass("show");
	})
	$("frame").load(function () {
        var mainheight = $(this).contents().find("body").height();
        $(this).height(mainheight);
        $(this).contents().click(function () {
            $(parent.document).trigger('click');
        });
    });
	$(document).bind("click",function(e){ 
		var target = $(e.target); 
		if(target.closest(".rightmenu").length == 0){ 
			$(".rightmenu").removeClass("rightmenushow");
			$("a.top").animate({"right":"0px"},300);
		} 
	})
})


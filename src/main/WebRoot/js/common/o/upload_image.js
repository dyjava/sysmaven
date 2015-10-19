function change(t) {
    var pic = $(t).closest("dl").find("img")[0];
    var file = $(t)[0];
    var ext = file.value.substring(file.value.lastIndexOf(".") + 1).toLowerCase();
    // gif在IE浏览器暂时无法显示
    if (ext != 'png' && ext != 'jpg' && ext != 'jpeg') {
        alert("文件必须为图片！");
        return;
    }
    // IE浏览器
    if (document.all) {

        file.select();
        var reallocalpath = document.selection.createRange().text;
        var ie6 = /msie 6/i.test(navigator.userAgent);
        // IE6浏览器设置img的src为本地路径可以直接显示图片
        if (ie6){ 
        	uploadFile(file);
        	pic.src = reallocalpath;
        }else {
        	uploadFile(file);
            // 非IE6版本的IE由于安全问题直接设置img的src无法显示本地图片，但是可以通过滤镜来实现
            pic.style.filter = "progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod='image',src=\"" + reallocalpath + "\")";
            // 设置img的src为base64编码的透明图片 取消显示浏览器默认图片
            pic.src = 'data:image/gif;base64,R0lGODlhAQABAIAAAP///wAAACH5BAEAAAAALAAAAAABAAEAAAICRAEAOw==';
        }
    } else {
   		uploadFile(file);
        html5Reader(file,pic);
    }
}

function html5Reader(file,pic) {
    var file = file.files[0];
    var reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = function(e) {
        pic.src = this.result;
    }
}

 function uploadFile(file) {
    var fd = new FormData();
    fd.append("pic", file.files[0]);
    var xhr = createXHR();
    
    //请求完成后执行的操作
    xhr.addEventListener("load", function(evt){
        var message = evt.target.responseText;
        var result = JSON.parse(JSON.parse(message));   
        console.log(result);
        if(result.status == 1){
        	//上传成功后的imageId
           file.nextSibling.value = result.result[0].imageId;
           
        }else{
			alert("图片上传失败");
        }
        
    }, false);

	//请求error
    xhr.addEventListener("error", uploadFailed, false);
    //请求中断
    xhr.addEventListener("abort", uploadCanceled, false);
    
    //发送请求
  	xhr.open("POST", "./image/upload_image_common");
    xhr.send(fd);
}

function uploadFailed(evt) {
    alert("上传出错.");
}

function uploadCanceled(evt) {
    alert("上传已由用户或浏览器取消删除连接.");
}

function createXHR(){
	var xmlHttp ;  
    try{  
        //FF,Opera,Safari  
        xmlHttp = new XMLHttpRequest();  
    }catch(e){  
        try{  
            //支持IE6.0+  
            xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");  
        }catch(e){  
            try{  
                //支持IE5.5+  
                xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");  
            }catch(e){  
                alert("提示: 您的浏览器暂时不支持Ajax请求!");  
                return false;  
            }  
        }  
    }  
    return xmlHttp;  
}

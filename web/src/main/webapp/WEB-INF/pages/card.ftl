<!DOCTYPE html>
<html>
<head>
<title>礼包分享</title>
<meta name="Keywords" content="" />
<meta name="Description" content="" />
<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
<meta content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">        
        <!-- Mobile Devices Support @begin -->
            <meta content="no-cache,must-revalidate" http-equiv="Cache-Control">
            <meta content="no-cache" http-equiv="pragma">
            <meta content="0" http-equiv="expires">
            <meta content="telephone=no, address=no" name="format-detection">
            <meta name="apple-mobile-web-app-capable" content="yes" /> <!-- apple devices fullscreen -->
            <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent" />
        <!-- Mobile Devices Support @end -->
        <style>
        	
        	*{margin:0 ;padding:0;}
        	body {font-size:62.5%;background:#f2f2f2;color:#333}
        	ul,li {list-style:none}	
        	li img,img {display:block;}
                .con .cons:active {background: #f0f0f0}
                .clear {clear:both}
                .tit {height: 55px;line-height: 55px;border-bottom: 1px solid #d8d8d8;background: #fff;}
                .tit span {border-left: 1px solid #d8d8d8;font-size:20px;font-weight: bold; text-indent:27px;}
                .tit img ,.tit span {float:left;}
                .tit img {margin:0 23px;}
                .top {width:100%;height:62px;background: #383838;}
                .con {padding:16px;}
                .cons {padding:16px;border: 1px solid #d8d8d8;background: #fff;border-radius:5px;margin-bottom:16px;}
                .cc_in img {width:21.1%;float:left;}
                .cc_in .ic_info {width: 73%; float:left;margin-left:16px;}
                .cc_in .ic_info h2 {display: block; width: 99.8%;height:22px;overflow:hidden;text-overflow:ellipsis;white-space: nowrap; font-size:20px;line-height:22px;}
                .ic_info .jdt {height:25px;line-height: 25px;}
                .ic_info .jdt .sy_num,.ic_info .jdt .jdts {display: block;float:left;}
                .ic_info .jdt .sy_num {width:25.5%;}
                .ic_info .jdt .sy_num font {color:#f5cb54}
                .ic_info .jdt .jdts {width:73%;height: 12px;margin-top:5px;border: 1px solid #f5cb54;border-radius:6px;}
                .ic_info .jdt .jdts span {display: block;width:<#if card.total gt 0>${((card.total-card.assignTotal)/card.total)*100}%<#else>0%</#if>;height: 12px;background: #f5cb54;border-radius:6px;}
                .ic_info .tips {height:25px;line-height: 25px; background:url(../../img/tip.png) no-repeat 3% center #feeeef;background-size:8%;text-indent:15%}
                .cons h3 {font-size: 20px;color:#333;padding-bottom:8px}
                .cons p {line-height: 150%}
                .foot .tj_img,.foot .tj_info,.foot .tj_btn {display: block;}
                .foot {padding:16px;background: #fff;border-top: 1px solid #d8d8d8;}
                .foot .tj_img {width:20%;float:left;}
                .foot .tj_info {width:40%;float:left;margin-left:16px;}
                .foot .tj_info p {font-size: 20px;line-height: 150%;color:#333;}
                .foot .tj_info span {line-height: 150%;color:#666;}
                .foot .tj_btn {width:30%;float:right}
                .foot .tj_btn a {display: block;width:100%;height: 40px;line-height: 40px;text-align:center;font-size:20px;border: 1px solid #ff6325;border-radius:5px;color:#fff;background: #ff6325;outline:none}
                .foot .tj_btn a:link{text-decoration:none;}
                /*.foot .tj_btn a:hover{text-decoration:none;color:#fff;background: #ff6325}*/
                .foot .tj_btn a:active{text-decoration:none;color:#333;background: none}
        </style>
    <script type="text/javascript">
        var iosDownloadUrl = "itms-apps://itunes.apple.com/app/${ios}";
        var androidDownloadUrl = "${android}";

        function ajaxRequest(platform)
        {
            var xmlHttpRequest = null;
            if(window.ActiveXObject) // IE浏览器
            {
                xmlHttpRequest = new ActiveXObject("Microsoft.XMLHTTP");
            }
            else if(window.XMLHttpRequest) // 除IE以外的其他浏览器
            {
                xmlHttpRequest = new XMLHttpRequest();
            }
            if(null != xmlHttpRequest)
            {
                // 准备向服务器发出一个请求
                 xmlHttpRequest.open("GET", "/1_0/page/download?platform=" + platform);
                // 向服务器发出一个请求
                xmlHttpRequest.send(null);
            }
        }
    </script>
    <script type="text/javascript">
        function isIOS(){
            var sUserAgent = navigator.userAgent.toLowerCase();
            var bIsIpad = sUserAgent.match(/ipad/i) == "ipad";
            var bIsIphoneOs = sUserAgent.match(/iphone os/i) == "iphone os";

            return bIsIpad || bIsIphoneOs;
        }

        function download() {
            if (isIOS()) {
                ajaxRequest("iOS");
                location.href = iosDownloadUrl;
            }
            else {
                ajaxRequest("android");
                location.href = androidDownloadUrl;
            }
        }
    </script>
</head>
<body>
<!--<div class="top"></div>-->
<div class="tit">
        <img src="../../img/t_logo.png" height="55" />
        <span>礼包大全</span>
</div>
<div class="con">
        <div class="cons cc_in">
                <img src="${card.icon!'http://kk.7k7kimg.cn/icon.jpg'}" width="21.1%" />
                <div class="ic_info">
                        <h2>《自由之战》礼包大全福利卡</h2>
                        <div class="jdt"><span class="sy_num">剩余: <font>${((card.total-card.assignTotal)/card.total)*100}%</font></span><div class="jdts"><span></span></div></div>
                        <div class="tips">本卡只能在礼包大全中领取！</div>
                </div>
                <div class="clear"></div>
        </div>
        <div class="cons">
                <h3>礼包内容</h3>
                <P>${card.description}</P>
        </div>
        <div class="cons">
                <h3>礼包内容</h3>
                <P>${card.flow}</P>
        </div>
</div>
<div class="foot">
        <div class="tj_img"><img src="../../img/lbdq.png" width="100%" /></div>
        <div class="tj_info">
                <p style="padding-top:10px">下载礼包大全</p>
                <span style="display:block;text-indent:8px">用手机随时领取礼包</span>
        </div>
        <div class="tj_btn" style="padding-top:15px">
                <a href="javascript:download()">领取礼包</a>
        </div>
        <div class="clear"></div>
</div>
</body>
</html>



<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>礼包大全管理系统</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>
    <script type="text/javascript" src="js/libs/modernizr.min.js"></script>
    <script type="text/javascript">
        function openUrl(url) {
            window.parent.main.frameElement.src=url;
        }
    </script>
</head>
<body>
<div class="sidebar-wrap">
    <div class="sidebar-title">
        <h1>菜单</h1>
    </div>
    <div class="sidebar-content">
        <ul class="sidebar-list">
            <li>
                <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                <ul class="sub-menu">
                    <li><a href="javascript:openUrl('/game/all')"><i class="icon-font">&#xe008;</i>游戏管理</a></li>
                    <li><a href="javascript:openUrl('/card/all')"><i class="icon-font">&#xe005;</i>礼包管理</a></li>
                    <li><a href="javascript:openUrl('/code/all')"><i class="icon-font">&#xe007;</i>激活码管理</a></li>
                    <li><a href="javascript:openUrl('/focus/all')"><i class="icon-font">&#xe006;</i>焦点图管理</a></li>
                    <li><a href="javascript:openUrl('/game/ranking')"><i class="icon-font">&#xe004;</i>排行榜管理</a></li>
                    <li><a href="javascript:openUrl('/user/all')"><i class="icon-font">&#xe012;</i>用户管理</a></li>
                    <li><a href="javascript:openUrl('/hot/all')"><i class="icon-font">&#xe052;</i>热词管理</a></li>
                    <li><a href="javascript:openUrl('/h5/all')"><i class="icon-font">&#xe052;</i>H5管理</a></li>
                    <li><a href="javascript:openUrl('/feedback/all')"><i class="icon-font">&#xe052;</i>用户反馈</a></li>
                    <li><a href="javascript:openUrl('/data/')"><i class="icon-font">&#xe052;</i>数据统计</a></li>
                    <li><a href="javascript:openUrl('/subscribe/all')"><i class="icon-font">&#xe052;</i>订阅统计</a></li>
                <#if admin?? && admin.top>
                    <li><a href="javascript:openUrl('/push/view')"><i class="icon-font">&#xe052;</i>推送管理</a></li>
                    <li><a href="javascript:openUrl('/admin/all')"><i class="icon-font">&#xe052;</i>操作员管理</a></li>
                    </#if>
                    </ul>
            </li>
            <li>
                <a href="#"><i class="icon-font">&#xe018;</i>系统管理</a>
                <ul class="sub-menu">
                    <li><a href="javascript:openUrl('/system/all')"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
</body>
</html>
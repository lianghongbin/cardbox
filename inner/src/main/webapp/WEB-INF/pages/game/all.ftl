<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i><a href="index.html">首页</a><span class="crumb-step">&gt;</span><span class="crumb-name">游戏管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">游戏平台:</th>
                            <td>
                                <select name="platform" id="">
                                    <option value="">全部</option>
                                    <option value="android">android</option><option value="iOS">iOS</option>
                                </select>
                            </td>
                            <th width="70">游戏状态:</th>
                            <td>
                                <select name="closed" id="">
                                    <option value="">全部</option>
                                    <option value="false">上线</option><option value="true">未上线</option>
                                </select>
                            </td>
                            <th width="120">游戏名称:</th>
                            <td><input class="common-text" placeholder="游戏名称" name="name" value="" id="" type="text"></td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="insert.html"><i class="icon-font"></i>新增作品</a>
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th class="tc" width="2%"><input class="allChoose" name="" type="checkbox"></th>
                            <th>排序</th>
                            <th>名称</th>
                            <th>状态</th>
                            <th>平台</th>
                            <th>发布时间</th>
                            <th>礼包数</th>
                            <th>已领取</th>
                            <th>评分</th>
                            <th>推荐</th>
                            <th>操作</th>
                        </tr>
                    <#list games as game>

                        <tr>
                            <td class="tc"><input name="id[]" value="${game.id}" type="checkbox"></td>
                            <td>
                                <input name="ids[]" value="${game.id}" type="hidden">
                                <input class="common-input sort-input" name="sort[]" value="1" type="text">
                            </td>
                            <td>59</td>
                            <td title="${game.name}"><a target="_blank" href="#" title="发哥经典">发哥经典</a> …
                            </td>
                            <td>0</td>
                            <td>2</td>
                            <td>admin</td>
                            <td>2014-03-15 21:11:01</td>
                            <td></td>
                            <td>
                                <a class="link-update" href="#">修改</a>
                                <a class="link-del" href="#">删除</a>
                            </td>
                        </tr>
                    </#list>
                    </table>
                    <div class="list-page"> 2 条 1/1 页</div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
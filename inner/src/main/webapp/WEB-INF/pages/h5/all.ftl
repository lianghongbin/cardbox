<#import "/spring.ftl" as spring />
<#import "/macros/pagination.ftl" as pagination />
<#setting url_escaping_charset='utf-8'>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <title>后台管理</title>
    <link rel="stylesheet" type="text/css" href="../css/common.css"/>
    <link rel="stylesheet" type="text/css" href="../css/main.css"/>
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript">
        function recommend(id)
        {
            if(!confirm("你确定要对该游戏进行相关推荐/取消推荐操作？")) {
                return false;
            }

            $.ajax({
                url: '/h5/recommend',// 跳转到 action
                data: {
                    aid: id
                },
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("修改成功");
                        window.location.reload();
                    } else {
                        alert("修改失败");
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }

        function saveSort(id, sort) {
            $.ajax({
                url: '/h5/sort',// 跳转到 action
                data: {
                    aid: id,
                    sort: sort
                },
                type: 'post',
                dataType: 'text',
                success: function (data) {
                    if (data == "1") {
                        alert("排序修改成功");
                        window.location.reload();
                    } else {
                        alert("排序修改失败");
                    }
                },
                error: function () {
                    alert("异常！");
                }
            });
        }

        function refresh() {
            if (!confirm("在线游戏更新时间受网络和更新数量的影响,可能需要几十秒至几分钟,更新完成会刷新游戏列表,你确定要刷新吗?")) {
                return false;
            }

            $.ajax({
                url: '/h5/refresh',// 跳转到 action
                type: 'get',
                dataType: 'text',
                success: function (data) {
                    alert("游戏在线更新完成");
                    window.location.reload();
                },
                error: function () {
                    alert("异常！");
                }
            });
        }
    </script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">H5管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="./all" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">游戏ID:</th>
                            <td>
                                <input type="text" name="aid" value="${aid}" placeholder="游戏ID">
                            </td>
                            <th width="120">游戏名称:</th>
                            <td>
                                <input type="text" name="title" value="${title}" placeholder="游戏名称">
                            </td>
                            <th width="120">游戏平台:</th>
                            <td>
                                <select name="platform">
                                    <option value="ALL">全部</option>
                                    <option value="android" <#if platform=="android">selected</#if> >android</option>
                                    <option value="ios" <#if platform=="ios">selected</#if> >ios</option>
                                </select>
                            </td>
                            <th width="70">游戏状态:</th>
                            <td>
                                <select name="recommend">
                                    <option value="2">全部</option>
                                    <option value="0" <#if recommend==0>selected</#if> >未推荐</option>
                                    <option value="1" <#if recommend==1>selected</#if> >推荐</option>
                                </select>
                            </td>
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
                        <a href="javascript:refresh()"><i class="icon-font"></i>即时更新H5游戏列表</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="50">游戏ID</th>
                            <th width="50">排序</th>
                            <th width="300">游戏名称</th>
                            <th>游戏描述</th>
                            <th width="60">是否推荐</th>
                            <th width="60">平台</th>
                            <th width="150">添加时间</th>
                            <th width="150">更新时间</th>
                            <th width="65">操作</th>
                        </tr>
                    <#list paginationData.pageItems as game>
                        <tr>
                            <td>
                                ${game.aid}
                            </td>
                            <td>
                                <input size="3" name="sort" value="${game.sort!100}"  type="text" onblur="javascript:saveSort(${game.aid}, this.value.trim())">
                            </td>
                            <td title="${game.title!}">
                                ${game.title}
                            </td>
                            <td>
                                ${game.gameIntro!}
                            </td>
                            <td><#if game.recommend><font color="red">推荐</font><#else>未推荐</#if> </td>
                            <td>${game.platform!"ALL"}</td>
                            <td>${game.createTime?number_to_datetime}</td>
                            <td><#if game.updateTime??> ${game.updateTime?number_to_datetime}</#if></td>
                            <td align="center">
                                &nbsp; <a class="link-update" href="javascript:recommend(${game.aid})"><#if game.recommend><font color="red">取消推荐</font><#else>推荐</#if></a>
                            </td>
                        </tr>
                    </#list>
                    </table>
                    <div class="list-page">
                        <nav style="float:right;">
                        <@pagination.counter /> &nbsp;&nbsp;&nbsp;
                        <@pagination.first />
                        <@pagination.previous />
                        <@pagination.numbers />
                        <@pagination.next />
                        <@pagination.last />
                        </nav>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
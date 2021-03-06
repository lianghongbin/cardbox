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
        function operate(gameId,closed)
        {
            if(!confirm("你确定要对该游戏进行上线/下线操作？")){
                return false;
            }

            if(closed == null) {
                closed = false;
            }

            $.ajax({
                url: '/game/openorclose',// 跳转到 action
                data: {
                    id: gameId,
                    operate: !closed
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
                url: '/game/update',// 跳转到 action
                data: {
                    id: id,
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
    </script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">游戏排行榜</span></div>
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
                                    <option value="ALL">ALL</option>
                                    <option value="android">android</option>
                                    <option value="iOS">iOS</option>
                                </select>
                            </td>

                            <th width="120">游戏名称:</th>
                            <td><input class="common-text" placeholder="游戏名称" name="name" value="" id="" type="text">
                            </td>
                            <td><input class="btn btn-primary btn2" name="sub" value="查询" type="submit"></td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="30"><font color="red">排序</font> </th>
                            <th>名称</th>
                            <th>状态</th>
                            <th>平台</th>
                            <th>礼包数</th>
                            <th width="30">评分</th>
                            <th width="30">推荐</th>
                            <th width="150">发布时间</th>
                            <th width="80" style="display: none">操作</th>
                        </tr>
                    <#list games as game>
                        <tr>
                            <td>
                                <input class="common-input sort-input" name="sort" value="${game.sort}" type="text" onblur="javascript:saveSort(${game.id}, this.value)">
                            </td>
                            <td title="${game.name}"><a href="./view?id=${game.id}" title="${game.name}">${game.name}</a>
                            </td>
                            <td><#if game.closed>下线<#else><font color="red">上线</font></#if> </td>
                            <td>${game.platform}</td>
                            <td>${game.total}</td>
                            <td>${game.score}</td>
                            <td><#if game.recommend><font color="red">推荐</font><#else>正常</#if></td>
                            <td>${game.createTime?number_to_datetime}</td>
                            <td align="center" style="display: none">
                                <a class="link-update" href="javascript:saveSort(${game.id}, ${game_index})">保存排序</a>
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
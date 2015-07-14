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
        function operate(id,gameId,closed)
        {
            if(!confirm("你确定要对该礼包进行上线/下线操作？")) {
                return false;
            }

            if(closed == null) {
                closed = false;
            }

            $.ajax({
                url: '/card/openorclose',// 跳转到 action
                data: {
                    id: id,
                    gameId: gameId,
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
                url: '/card/updatesort',// 跳转到 action
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
                    class="crumb-name">礼包管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="./all" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="120">游戏名称:</th>
                            <td>
                                <select name="gameId" id="">
                                    <option value="">全部</option>
                                    <#list games as game>
                                    <option value="${game.id}" <#if game.id=gameId>selected</#if> >${game.name}</option>
                                    </#list>
                                </select>
                            </td>
                            <th width="70">游戏状态:</th>
                            <td>
                                <select name="closed" id="">
                                    <option value="2">全部</option>
                                    <option value="0" <#if closed==0>selected</#if> >上线</option>
                                    <option value="1" <#if closed==1>selected</#if> >未上线</option>
                                </select>
                            </td>
                            <th width="120">礼包名称:</th>
                            <td><input class="common-text" placeholder="礼包名称" name="name" value="${name}" id="" type="text">
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
                        <a href="./add"><i class="icon-font"></i>新增礼包</a>
                        <a href="./expire?gameId=${gameId}&closed=${closed}&name=${name}"><i class="icon-font"></i>过期礼包</a>
                        <a href="./schedule?gameId=${gameId}&closed=${closed}&name=${name}"><i class="icon-font"></i>定时礼包</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="50">礼包ID</th>
                            <th width="50">排序</th>
                            <th>游戏名称</th>
                            <th>礼包名称</th>
                            <th>平台</th>
                            <th>状态</th>
                            <th width="50">礼包数</th>
                            <th width="50">库存量</th>
                            <th width="60">类别</th>
                            <th width="30">推荐</th>
                            <th width="150">发布时间</th>
                            <th width="150">截止时间</th>
                            <th width="100">激活码</th>
                            <th width="100">操作</th>
                        </tr>
                    <#list paginationData.pageItems as card>
                        <tr>
                            <td>
                                ${card.id}
                            </td>
                            <td>
                                <input size="3" name="sort" value="${card.sort!2}"  type="text" onblur="javascript:saveSort(${card.id}, this.value.trim())">
                            </td>
                            <td title="${card.gameName!}">
                                <a href="../game/view?id=${card.gameId}" title="${card.gameName}">${card.gameName}</a>
                            </td>
                            <td title="${card.name!}">
                                <a href="./view?id=${card.id}" title="${card.name!}">${card.name!}</a>
                            </td>
                            <td>${card.platform!}</td>
                            <td><#if card.closed>下线<#else><font color="red">上线</font></#if> </td>
                            <td>${card.total!}</td>
                            <td>${card.total-card.assignTotal}</td>
                            <td>${card.type!}</td>
                            <td><#if card.recommend><font color="red">推荐</font><#else>正常</#if></td>
                            <td>${card.openTime?number_to_datetime}</td>
                            <td>${card.expireTime?number_to_datetime}</td>
                            <td align="center">
                                &nbsp; <a class="link-update" href="../code/input?cardId=${card.id}">添加</a>
                                &nbsp; <a class="link-update" href="../code/findbycard?cardId=${card.id}">查看</a>
                            </td>
                            <td align="center">
                                <input type="hidden" name="gameId" value="${gameId}">
                                <input type="hidden" name="closed" value="${closed}">
                                <input type="hidden" name="name" value="${name}">
                                <#if card.closed><a class="link-update" href="javascript:operate(${card.id},${card.gameId},'${card.closed!false}')">上线</a><#else><a class="link-update" href="javascript:operate(${card.id},${card.gameId},'${card.closed!false}')">下线</a></#if>
                                &nbsp; <a class="link-update" href="./modify?id=${card.id}">修改</a>
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
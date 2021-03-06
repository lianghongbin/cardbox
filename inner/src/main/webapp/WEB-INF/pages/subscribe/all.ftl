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
    <script type="text/javascript" src="../laydate/laydate.js"></script>
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
                    class="crumb-name">订阅管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="./all" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="90">手机:</th>
                            <td>
                                <input type="text" size="15" name="phone" value="${phone}" placeholder="手机号">
                            </td>
                            <th width="90">游戏ID:</th>
                            <td>
                                <input type="text" size="10" name="gameId" value="${gameId}" placeholder="游戏ID">
                            </td>
                            <th width="120">开始时间:</th>
                            <td>
                                <input class="laydate-icon" name="start" value="${start}" id="start" style="width:200px;">
                            </td>
                            <th width="120">结束时间:</th>
                            <td>
                                <input class="laydate-icon" name="end" value="${end}" id="end" style="width:200px;">
                            </td>

                        </tr>
                        <tr>
                            <th width="90">游戏类别:</th>
                            <td colspan="6">
                                <#list typesList as t>
                                    <input type="checkbox" name="types" <#if types?? && types?seq_contains(t.name)>checked</#if> value="${t.name}">${t.name} &nbsp;&nbsp;
                                </#list>
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
                            <th width="50">手机号</th>
                            <th width="300">订阅游戏</th>
                            <th width="60">游戏类别</th>
                            <th width="150">订阅时间</th>
                        </tr>
                    <#list paginationData.pageItems as subscribe>
                        <tr>
                            <td>
                                ${subscribe.phone}
                            </td>
                            <td>
                                ${subscribe.gameName}
                            </td>
                            <td>
                                ${subscribe.types}
                            </td>
                            <td>${subscribe.createTime?number_to_datetime}</td>
                        </tr>
                    </#list>
                    </table>
                    <div class="list-page">
                        <nav style="float:right;">
                            ${count} 条订阅 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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


<script>
    var start = {
        elem: '#start',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2015-07-01 00:00:00', //设定最小日期为当前日期
        max: '2099-06-16 23:59:59', //最大日期
        istime: true,
        istoday: false,
        choose: function (datas) {
            end.min = datas; //开始日选好后，重置结束日的最小日期
            end.start = datas //将结束日的初始值设定为开始日
        }
    };
    var end = {
        elem: '#end',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2015-07-01 00:00:00',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(start);
    laydate(end);

</script>
</body>
</html>
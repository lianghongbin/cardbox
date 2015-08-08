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
        function push()
        {
            if(!confirm("你确定要给这些用户推送消息？推送时长依据推送数量，推送结果请查看推送服务商后台！")) {
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
    </script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">订阅查询</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="./view" method="post">
                    <table class="search-tab">
                        <tr>
                            <th width="90">游戏类别:</th>
                            <td>
                                <select name="type">
                                    <option value="">全部</option>
                                    <#list typesList as t>
                                    <option value="${t.name}" <#if t.name==type>selected</#if> >${t.name}</option>
                                    </#list>
                                </select>
                            </td>
                            <th>游戏ID:</th>
                            <td>
                                <input type="text" size="10" name="gameId" value="${gameId}" placeholder="游戏ID">
                            </td>
                            <th>开始时间:</th>
                            <td>
                                <input class="laydate-icon" value="${start}" name="start" id="start" style="width:200px;">
                            </td>
                            <th>结束时间:</th>
                            <td>
                                <input class="laydate-icon" name="end" value="${end}" id="end" style="width:200px;">
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
                                ${subscribe.types}</a>
                            </td>
                            <td>${subscribe.createTime?number_to_datetime}</td>
                        </tr>
                    </#list>
                        <tr>
                            <td colspan="4" align="center">
                                <textarea name="content" cols="50" rows="4" placeholder="推送内容"></textarea><br>
                            <input class="btn btn-primary" name="p" value="向当前 ${count} 位用户推送消息" onclick="push()" type="button">
                            </td>
                        </tr>
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
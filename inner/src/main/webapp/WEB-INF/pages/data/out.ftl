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
    <script type="text/javascript" src="../laydate/laydate.js"></script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">数据管理</span></div>
        </div>
        <div class="search-content">
            <form action="./user" method="post">
                <table class="search-tab">
                    <tr>
                        <th colspan="5" align="center"><b>数据导出</b></th>
                    </tr>
                    <form action="./user" method="post">
                        <tr>
                            <th width="120">开始时间:</th>
                            <td>
                                <input class="laydate-icon" name="start" id="start" style="width:200px;">
                            </td>
                            <th width="120">结束时间:</th>
                            <td>
                                <input class="laydate-icon" name="end" id="end" style="width:200px;">
                            </td>

                            <td><input class="btn btn-primary btn2" name="sub" value="导出注册用户" type="submit"></td>
                        </tr>
                    </form>

                    <form action="./download" method="post">
                        <tr>
                            <th colspan="5" align="center"><input class="btn btn-primary btn2" name="sub" value="导出下载数据"
                                                                  type="submit"></th>
                        </tr>
                    </form>

                    <form action="./card" method="post">
                        <tr>
                            <th width="120">开始时间:</th>
                            <td>
                                <input class="laydate-icon" name="start" id="startTime" style="width:200px;">
                            </td>
                            <th width="120">结束时间:</th>
                            <td>
                                <input class="laydate-icon" name="end" id="endTime" style="width:200px;">
                            </td>

                            <td><input class="btn btn-primary btn2" name="sub" value="导出礼包数据" type="submit"></td>
                        </tr>
                    </form>
                </table>
        </div>
    </div>
</div>
<!--/main-->


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

    var startTime = {
        elem: '#startTime',
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
    var endTime = {
        elem: '#endTime',
        format: 'YYYY-MM-DD hh:mm:ss',
        min: '2015-07-01 00:00:00',
        max: '2099-06-16 23:59:59',
        istime: true,
        istoday: false,
        choose: function (datas) {
            start.max = datas; //结束日选好后，重置开始日的最大日期
        }
    };
    laydate(startTime);
    laydate(endTime);
</script>
</div>
</body>
</html>
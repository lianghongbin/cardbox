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
    <link rel="stylesheet" type="text/css" href="../tcal/tcal.css" />
    <script type="text/javascript" src="../js/libs/modernizr.min.js"></script>
    <script type="text/javascript" src="../js/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="../tcal/tcal.js"></script>
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
    </script>
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">用户管理</span></div>
        </div>
        <div class="search-wrap">
            <div class="search-content">
                <form action="#" method="post">
                    <table class="search-tab">
                        <tr>
                            <th>开始时间：</th>
                            <td><input class="tcal" name="startDate" id="startDate" size="12" type="text"></td>

                            <th>结束时间：</th>
                            <td><input class="tcal" name="endDate" id="endDate" size="12" type="text"></td>

                            <th width="120">注册手机:</th>
                            <td><input class="common-text" placeholder="注册手机" name="phone" maxlength="11" size="15" value="" id="" type="text">
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
                            <th>手机</th>
                            <th>用户名</th>
                            <th>积分</th>
                            <th>设备类型</th>
                            <th>设备号</th>
                            <th width="150">注册时间</th>
                            <th width="150">最后登录</th>
                        </tr>
                    <#list paginationData.pageItems as user>
                        <tr>
                            <td>${user.phone}
                            </td>
                            <td>${user.username}</td>
                            <td>${user.score}</td>
                            <td>${user.type}</td>
                            <td>${user.device}</td>
                            <td>${user.createTime?number_to_datetime}</td>
                            <td>${user.lastTime?number_to_datetime}</td>
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
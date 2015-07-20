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
</head>
<body>
<div class="container clearfix">
    <!--/sidebar-->
    <div class="main-wrap">

        <div class="crumb-wrap">
            <div class="crumb-list"><i class="icon-font"></i>首页<span class="crumb-step">&gt;</span><span
                    class="crumb-name">系统配置管理</span></div>
        </div>
        <div class="result-wrap">
            <form name="myform" id="myform" method="post">
                <div class="result-title">
                    <div class="result-list">
                        <a href="./add"><i class="icon-font"></i>新增系统配置</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th width="60">版本</th>
                            <th width="60">每日登录</th>
                            <th width="60">注册</th>
                            <th width="60">微信分享</th>
                            <th width="60">QQ分享</th>
                            <th width="60">下载游戏</th>
                            <th>android下载</th>
                            <th>iOS ID</th>
                            <th>关于我们</th>
                            <th>启动公告</th>
                            <th width="150">添加时间</th>
                            <th width="50">操作</th>
                        </tr>
                    <#list settings as setting>
                        <tr>
                            <td class="tc">${setting.v}</td>
                            <td>
                            ${setting.daily}
                            </td>
                            <td>
                            ${setting.registry}
                            </td>
                            <td>
                            ${setting.weixin}
                            </td>
                            <td>
                            ${setting.qq}
                            </td>
                            <td>
                            ${setting.download}
                            </td>
                            <td>
                            ${setting.android}
                            </td>
                            <td>
                            ${setting.ios}
                            </td>
                            <td>
                            ${setting.us}
                            </td>
                            <td>
                            ${setting.announce}
                            </td>
                            <td>${setting.createTime?number_to_datetime} </td>
                            <td><a class="link-update" href="./modify?v=${setting.v}">修改</a></td>
                        </tr>
                    </#list>
                    </table>
                </div>
            </form>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>
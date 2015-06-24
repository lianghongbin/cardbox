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
                        <a id="batchDel" href="javascript:void(0)"><i class="icon-font"></i>批量删除</a>
                        <a id="updateOrd" href="javascript:void(0)"><i class="icon-font"></i>更新排序</a>
                    </div>
                </div>
                <div class="result-content">
                    <table class="result-tab" width="100%">
                        <tr>
                            <th>版本</th>
                            <th>每日登录得分</th>
                            <th>注册得分</th>
                            <th>微信分享得分</th>
                            <th>QQ分享得分</th>
                            <th>下载游戏得分</th>
                            <th width="150">添加时间</th>
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
                            <td>${setting.createTime?number_to_datetime} </td>
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
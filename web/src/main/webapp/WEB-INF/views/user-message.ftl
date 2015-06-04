<!--用户中心右侧头部开始 -->
<div class="user_list_top">
    <ul class="list_tab">
        <li class="list_tab_cur" id="user_comment_b"><a href="javascript:void(0)">评论</a></li>
        <li id="user_letter_b"><a href="javascript:void(0)">私信</a></li>
        <li id="sys_message_b"><a href="javascript:void(0)">系统消息</a></li>
    </ul>
    <div class="search ms01">
        <span class="search_title"><a href="javascript:;">收到的评论</a> | <a href="javascript:;">发出的评论</a></span>

        <div class="search_box">
            <form method="post" action="#"><input type="text" value="搜索评论" class="search_user"/><input type="submit"
                                                                                                       class="zoom"
                                                                                                       value=""
                                                                                                       style="width:20px"/>
            </form>
        </div>
    </div>
    <div class="search ms02" style="display:none">
        <span class="search_title"><a href="javascript:;">全部设为已读</a> | <a href="javascript:;">清空所有私信</a></span>

        <div class="search_box">
            <form method="post" action="#"><input type="text" value="搜索私信" class="search_user"/><input type="submit"
                                                                                                       class="zoom"
                                                                                                       value=""
                                                                                                       style="width:20px"/>
            </form>
        </div>
    </div>
    <div class="search ms03" style="display:none">
        <span class="search_title"><a href="javascript:;">全部设为已读</a> | <a href="javascript:;">清空所有系统消息</a></span>

        <div class="search_box">
            <form method="post" action="#"><input type="text" value="搜索系统消息" class="search_user"/><input type="submit"
                                                                                                         class="zoom"
                                                                                                         value=""
                                                                                                         style="width:20px"/>
            </form>
        </div>
    </div>
</div>
<!--用户中心右侧头部结束 -->
<!--我收到/发出的评论列表开始 -->
<div class="message_list" id="user_comment_list">
</div>
<!--我收到/发出的评论列表结束 -->
<!--我的私信列表开始 -->
<div class="message_list" id="user_letter_list">
</div>
<!--我的私信列表结束 -->
<!--我收到的系统消息列表开始-->
<div class="message_list" id="sys_message_list">
</div>
<!--我收到的系统消息列表结束 -->
<script src="js/user_center_message_main.js"></script>
<script src="js/focus-blur.js"></script>
<!--用户中心右侧头部开始 -->
<div class="user_list_top">
    <ul class="list_tab">
        <li class="list_tab_cur" id="follow_b"><a href="javascript:void(0)">关注</a></li>
        <li id="fans_b"><a href="javascript:void(0)">粉丝</a></li>
        <li id="black_b"><a href="javascript:void(0)">黑名单</a></li>
    </ul>
    <div class="search fs01">
        <span class="search_title">已经关注<span>288</span>人</span>

        <div class="search_box">
            <form method="post" action="#"><input type="text" value="搜索关注的人" class="search_user"/><input type="submit"
                                                                                                         class="zoom"
                                                                                                         value=""
                                                                                                         style="width:20px"/>
            </form>
        </div>
    </div>
    <div class="search fs02" style="display:none">
        <span class="search_title">共有<span>288</span>个粉丝</span>

        <div class="search_box">
            <form method="post" action="#"><input type="text" value="搜索粉丝" class="search_user"/><input type="submit"
                                                                                                       class="zoom"
                                                                                                       value=""
                                                                                                       style="width:20px"/>
            </form>
        </div>
    </div>
    <div class="search fs03" style="display:none">
        <span class="search_title">黑名单有<span>288</span>个人</span>

        <div class="search_box">
            <form method="post" action="#"><input type="text" value="搜索黑名单" class="search_user"/><input type="submit"
                                                                                                        class="zoom"
                                                                                                        value=""
                                                                                                        style="width:20px"/>
            </form>
        </div>
    </div>
</div>
<!--用户中心右侧头部结束 -->
<!--关注用户列表开始 -->
<div class="user_list" id="follow_list">
</div>
<!--关注用户列表结束 -->
<!--粉丝用户列表开始 -->
<div class="user_list" id="fans_list">
</div>
<!--粉丝用户列表结束 -->
<!--黑名单列表开始 -->
<div class="user_list" id="black_list">
</div>
<!--黑名单列表结束 -->
<script src="js/user_center_friend_main.js"></script>
<script src="js/focus-blur.js"></script>
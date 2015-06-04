<link href="css/user_center_setup.css" rel="stylesheet" type="text/css"/>
<div class="attention">
    <span class="arrow"><em>1</em><i></i></span>

    <div class="attention_box">
        如果需要取消邮箱绑定，请先绑定手机。
    </div>
</div>
<!--手机绑定开始 -->
<div class="small_box">
    <h3>绑定手机</h3>

    <div class="tel_bundling">
        <form>
            <dl>
                <dt>手机号：</dt>
                <dd><input type="text" class="tel_input" value="请输入手机号码"/></dd>
            </dl>
            <dl>
                <dt>验证码：</dt>
                <dd><input type="text" class="CAPTCHA_input" value="请输入验证码"/><a href="javascript:;" class="get_CAPTCHA">获取验证码</a><span
                        class="timeCount">60秒后重新获取</span></dd>
            </dl>
            <div class="agree_clause" onselectstart="javascript:return false;"><input type="checkbox"
                                                                                      id="agree_clause"/><label
                    for="agree_clause">同意<a href="#">《XXXXX bbs手机增值服务条款》</a></label></div>
            <input type="button" class="user_setup_big_button bundling" style="border:none" value="绑定手机">
        </form>
    </div>
    <!--更改绑定暂时隐藏 -->
    <div class="tel_unbundling" hidden="true">
        已绑定手机：<span>13*******67</span>，可使用手机号登录当前论坛
        <div class="button_box"><input type="button" class="user_setup_big_button change_bundling" style="border:none"
                                       value="更改手机号"><input type="button" class="user_setup_big_button change_bundling"
                                                            style="margin-left:10px">取消绑定</a></div>
    </div>
</div>
<!--手机绑定结束 -->
<!--邮箱绑定开始 -->
<div class="small_box" style="margin-left:8px">
    <h3>绑定邮箱</h3>

    <div class="tel_bundling">
        <form>
            <dl>
                <dt>邮&nbsp;&nbsp;箱：</dt>
                <dd><input type="text" class="tel_input" value="请输入邮箱地址"/></dd>
            </dl>
            <input type="button" class="user_setup_big_button bundling" style="margin-top:60px; border:none"
                   value="绑定邮箱">
        </form>
    </div>
    <!--更改绑定暂时隐藏 -->
    <div class="tel_unbundling" hidden="true">
        已绑定邮箱：<span>Zh*******com</span>，可使用该邮箱地址登录当前论坛
        <div class="button_box"><input type="button" class="user_setup_big_button change_bundling" style="border:none"
                                       value="更改账号"><input type="button" class="user_setup_big_button change_bundling"
                                                           style="margin-left:10px;border:none" value="取消绑定"><a
                href="javascript:;" type="button" class="user_setup_big_button change_bundling gray_button"
                style="margin-left:10px;">取消绑定</a></div>
    </div>
</div>
<!--邮箱绑定结束 -->
<div class="clear"></div>
<div class="big_box">
    <h3>头像</h3>

    <div class="headpic_box">
        <a href="javascript:;" class="button_small_blue upload_button">本地上传</a>

        <div class="upload_intro" onselectstart="javascript:return false;">
            仅支持jpg、gif、png格式，文件小于5M。（使用高质量图片，可生成高清头像）<br/><input type="checkbox" checked="checked"
                                                                 id="original_headpic_select"/><label
                for="original_headpic_select">上传原始图片，生成高清头像</label></div>
        <!--头像切割开始 -->
        <iframe frameborder="0" width="705" height="315" scrolling="no" src="/user/head/cut/"
                style="margin-top:10px"></iframe>
        <!--头像切割结束 -->
    </div>
</div>
<div class="big_box">
    <h3>昵称</h3>
    <dl>
        <dt>现昵称：</dt>
        <dd>郑岩_card</dd>
    </dl>
    <dl>
        <dt>新昵称：</dt>
        <dd><input type="text" class="tel_input"/></dd>
    </dl>
    <dl>
        <dt></dt>
        <dd><input type="button" value="保存" class="user_setup_big_button save_button"/></dd>
    </dl>
</div>
<div class="big_box">
    <h3>个人签名</h3>
    <dl>
        <dt>现签名：</dt>
        <dd>我是我 执着得笨拙 不少也不多 宁愿错 不错过 我还是我。</dd>
    </dl>
    <dl>
        <dt>新签名：</dt>
        <dd><input type="text" class="tel_input"/></dd>
    </dl>
    <dl>
        <dt></dt>
        <dd><input type="button" value="保存" class="user_setup_big_button save_button"/></dd>
    </dl>
</div>
<div class="big_box">
    <h3>所在城市</h3>
    <dl>
        <dt>所在地：</dt>
        <dd id="city_prov">
            <div class="select_province">
                <div class="province">
                    <span>请选择</span>
                    <a href="javascript:;"></a>
                </div>
                <ul>
                    <li><a href="javascript:;">北京市</a></li>
                    <li><a href="javascript:;">天津市</a></li>
                    <li><a href="javascript:;">上海市</a></li>
                    <li><a href="javascript:;">重庆市</a></li>
                    <li><a href="javascript:;">河北省</a></li>
                    <li><a href="javascript:;">山西省</a></li>
                    <li><a href="javascript:;">内蒙古</a></li>
                    <li><a href="javascript:;">辽宁省</a></li>
                    <li><a href="javascript:;">吉林省</a></li>
                    <li><a href="javascript:;">黑龙江省</a></li>
                    <li><a href="javascript:;">江苏省</a></li>
                    <li><a href="javascript:;">浙江省</a></li>
                    <li><a href="javascript:;">安徽省</a></li>
                    <li><a href="javascript:;">福建省</a></li>
                    <li><a href="javascript:;">江西省</a></li>
                    <li><a href="javascript:;">山东省</a></li>
                    <li><a href="javascript:;">河南省</a></li>
                    <li><a href="javascript:;">湖北省</a></li>
                    <li><a href="javascript:;">湖南省</a></li>
                    <li><a href="javascript:;">广东省</a></li>
                    <li><a href="javascript:;">广西</a></li>
                    <li><a href="javascript:;">海南省</a></li>
                    <li><a href="javascript:;">四川省</a></li>
                    <li><a href="javascript:;">贵州省</a></li>
                    <li><a href="javascript:;">云南省</a></li>
                    <li><a href="javascript:;">西藏</a></li>
                    <li><a href="javascript:;">陕西省</a></li>
                    <li><a href="javascript:;">甘肃省</a></li>
                    <li><a href="javascript:;">青海省</a></li>
                    <li><a href="javascript:;">宁夏</a></li>
                    <li><a href="javascript:;">新疆</a></li>
                    <li><a href="javascript:;">香港</a></li>
                    <li><a href="javascript:;">澳门</a></li>
                    <li><a href="javascript:;">台湾省</a></li>
                </ul>
            </div>
            <div class="select_city">
                <div class="city">
                    <span>请选择</span>
                    <a href="javascript:;"></a>
                </div>
                <ul class="city_list">
                    <li>请选择</li>
                </ul>
            </div>
        </dd>
    </dl>

</div>
<div class="big_box">
    <h3>生日</h3>
    <dl>
        <dt>生日：</dt>
        <dd>
            <div class="select_year">
                <div class="year">
                    <span>年</span>
                    <a href="javascript:;"></a>
                </div>
                <ul class="year_list">
                    <li>年</li>
                </ul>
            </div>
            <div class="select_month">
                <div class="month">
                    <span>月</span>
                    <a href="javascript:;"></a>
                </div>
                <ul class="month_list">
                    <li>月</li>
                </ul>
            </div>
            <div class="select_day">
                <div class="day">
                    <span>日</span>
                    <a href="javascript:;"></a>
                </div>
                <ul class="day_list">
                    <li>日</li>
                </ul>
            </div>
        </dd>
    </dl>
</div>
<div class="big_box">
    <h3>性别</h3>
</div>
<div class="big_box">
    <h3>关注兴趣</h3>
</div>
<script src="js/user_center_setup.js"></script>
<script src="js/city.js"></script>
<script src="js/date.js"></script>
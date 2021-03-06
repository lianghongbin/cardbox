package com.gamesky.card.core;

/**
 * Created on 6/10/15.
 *
 * @Author lianghongbin
 */
public interface Constants {

    int LOGIN_EXPIRE_LONG = 24*60*60*365;
    int REGISTRY_SCORE = 300;
    int DAILY_SCORE = 100;
    int SHARE_WEIXIN = 10;
    int DOWNLOAD_SCORE = 100;
    int SHARE_QQ = 10;
    int TAO_AFTER_TIME = 120;   //多长时间之后进行淘号状态(分钟)

    String CHECK_CODE_KEY_PREFIX = "check_code_key-";
    String USER_LOGIN_KEY_PREFIX = "user_login_key-";
    String TOKEN_KEY    = "token";

    String UPLOAD_DIR = "/upload/";
    String PHOTO_URL_PREFIX = "http://kk.7k7kimg.cn";

    String DEFAULT_ICON = "http://kk.7k7kimg.cn/icon.jpg";

    String INNER_LOGIN_SESSION_KEY = "inner_login_session_key_lhb";
}

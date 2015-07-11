package com.gamesky.card.core;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public enum ReturnCode {

    SUCCESS(0, "success"),
    GENERAL(-1, "普通错误"),
    ILLEGAL_ARGUMENT(-2, "参数错误"),
    EXCEPTION(-3, "系统异常"),
    ERROR(-4, "系统错误"),
    SCORE_NOT_ENOUGH(-5, "积分不足"),
    DATA_EMPTY(-6, "数据为空"),
    ILLEGAL_OPERATE(-7, "非法操作"),
    NOT_LOGIN(-8, "未登录操作"),
    CHECKCODE_INVALID(-9, "无效验证码"),
    CHECKCODE_WRONG(10, "验证码错误"),
    USER_NOT_EXIST(-11, "用户不存在"),
    SMS_SEND_ERROR(-12, "短信发送失败"),
    MARSHAL_ERROR(-13, "数据存储/读取异常"),
    SERVER_ERROR_500(-500, "服务器异常"),
    SERVER_ERROR_504(-504, "请求方式不支持"),
    BAD_REQUEST(-400, "错误的请求格式"),
    PAGE_NOT_FOUND(-404, "访问的接口不存在"),
    UNKNOWN_ERROR(-1000, "未知错误");

    private int code;
    private String desc;

    private ReturnCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public int getCode() {
        return code;
    }

    public String getDesc() {return desc;}

    public static ReturnCode fromCode(int code) {
        for(ReturnCode returnCode : values()) {
            if (returnCode.getCode() == code) {
                return returnCode;
            }
        }

        return UNKNOWN_ERROR;
    }
}

package com.gamesky.card.core;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class Result {

    private Status status;
    private Page page;
    private Object data;

    public Result() {
        this.status = new Status();
    }

    public Result(Object data) {
        this.status = new Status();
        this.data = data;
    }

    public Result(Status status) {
        this.status = status;
    }

    public Result(Status status, Object data) {
        this.status = status;
        this.data = data;
    }

    public Result(Page page, Object data) {
        this.status = new Status();
        this.page = page;
        this.data = data;
    }

    public Result(Status status, Page page, Object data) {
        this.status = status;
        this.page = page;
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public Page getPage() {
        return page;
    }

    public Object getData() {
        return data;
    }
}

class Status {
    private int code;
    private String message;

    public Status() {
        this.code = 0;
        this.message = "success";
    }

    public Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
package com.gamesky.card.core;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class ResultGenerator {

    public static String generateError(String message) {
        return generateError(-1, message);
    }

    public static String generateError(int code, String message) {
        Result result = new Result(new Status(code, message));
        return toJson(result);
    }

    public static String generateError(ErrorCode code) {
        Result result = new Result(new Status(code.getCode(), code.getDesc()));
        return toJson(result);
    }

    public static String generate() {
        return toJson(new Result());
    }

    public static String generate(Object data) {
        return toJson(new Result(data));
    }

    public static String generate(String key, Object data) {
        Map<String, Object> params = new HashMap<>();
        params.put(key, data);
        return toJson(new Result(params));
    }

    public static String generate(Page page, String key, Object data) {
        Map<String, Object> params = new HashMap<>();
        params.put(key, data);
        return toJson(new Result(page, params));
    }

    private static String toJson(Result result) {
        Gson gson = new Gson();
        return gson.toJson(result);
    }
}

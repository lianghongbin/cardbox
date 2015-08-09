package com.gamesky.card.core;

/**
 * LHB on 8/8/15.
 */
public class PushPayload {

    private final String token;
    private final String content;

    public PushPayload(String token, String content) {
        this.token = token;
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public String getContent() {
        return content;
    }
}

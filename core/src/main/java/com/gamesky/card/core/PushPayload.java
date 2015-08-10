package com.gamesky.card.core;

/**
 * LHB on 8/8/15.
 */
public class PushPayload {

    private final String token;
    private final String title;
    private final String content;

    public PushPayload(String token, String title, String content) {
        this.token = token;
        this.title = title;
        this.content = content;
    }

    public String getToken() {
        return token;
    }

    public String getTitle() {return title;}

    public String getContent() {
        return content;
    }
}

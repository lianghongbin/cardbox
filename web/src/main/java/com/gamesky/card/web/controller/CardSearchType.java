package com.gamesky.card.web.controller;

/**
 * Created on 6/12/15.
 *
 * @Author lianghongbin
 */
public enum CardSearchType {

    ALL(0),
    PAY(1),
    FREE(2),
    SCORE(3);

    private int type;

    public int getType() {
        return type;
    }

    private CardSearchType(int type) {
        this.type = type;
    }
}

package com.gamesky.card.core;

/**
 * 积分流水类别
 * Created on 2015/6/9.
 *
 * @Author lianghongbin
 */
public enum FlowType {

    IN(0),
    OUT(1);

    private int value;

    private FlowType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

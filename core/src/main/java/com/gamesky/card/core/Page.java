package com.gamesky.card.core;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class Page {

    private int total;
    private int size;
    private int num;

    public Page(int total, int size, int num) {
        this.total = total;
        this.size = size;
        this.num = num;
    }

    public int getTotal() {
        return total;
    }

    public int getSize() {
        return size;
    }

    public int getNum() {
        return num;
    }
}

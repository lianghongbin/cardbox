package com.gamesky.card.core;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class Page {

    private int total;
    private int size;
    private int num = 1;

    public Page() {
        this.total = 0;
        this.size = Integer.MAX_VALUE;
        this.num = 1;
    }

    public Page(int total, int size, int num) {
        this.total = total;
        this.size = size;
        if (num < 1) {
            this.num = 1;
        } else {
            this.num = num;
        }
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

    public int getOffset() {
        return (num -1) * size;
    }
}

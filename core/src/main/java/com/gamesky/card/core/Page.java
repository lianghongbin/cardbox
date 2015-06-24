package com.gamesky.card.core;

import java.beans.Transient;

/**
 * Created on 6/4/15.
 *
 * @Author lianghongbin
 */
public class Page {

    private int count = 0;
    private int pagesize;
    private int pagenum = 1;
    private int total = 1;

    public Page() {
        this.count = 0;
        this.pagesize = Integer.MAX_VALUE;
        this.pagenum = 1;
        this.total = 1;
    }

    public Page(int pagesize) {
        this.pagesize = pagesize;
    }

    public Page(int pagesize, int pagenum) {
        this.pagesize = pagesize;
        if (pagenum < 1) {
            this.pagenum = 1;
        } else {
            this.pagenum = pagenum;
        }

        this.total = (int) Math.ceil(count/(float)pagesize);
    }

    @Transient
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
        this.total = (int) Math.ceil(count/(float)pagesize);
    }

    public int getPagesize() {
        return pagesize;
    }

    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }

    public int getPagenum() {
        return pagenum;
    }

    public void setPagenum(int pagenum) {
        this.pagenum = pagenum;
    }

    public int getOffset() {
        return (pagenum -1) * pagesize;
    }

    public int getTotal() {
        return total;
    }
}

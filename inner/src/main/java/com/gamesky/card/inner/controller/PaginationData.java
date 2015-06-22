package com.gamesky.card.inner.controller;

import com.gamesky.card.core.Page;

/**
 * Created on 2015/6/20.
 *
 * @Author lianghongbin
 */
public class PaginationData {
    private int pageNumber;         // -> The current page number
    private int pageSize;           // -> The number of items in each page
    private int pagesAvailable;     // -> The total number of pages
    private String sortDirection;   // -> The sorting direction (ascending or descending)
    private String sortField;       // -> The field currently sorted by
    private Object pageItems;
    private Page page;

    public PaginationData(Page page, Object pageItems) {
        this.pageItems = pageItems;
        this.page = page;
    }

    public int getPageNumber() {
        return this.page.getPagenum();
    }

    public int getPageSize() {
        return this.page.getPagesize();
    }

    public int getPagesAvailable() {
        return this.page.getTotal();
    }

    public String getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(String sortDirection) {
        this.sortDirection = sortDirection;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public Object getPageItems() {
        return pageItems;
    }

    public Page getPage() {
        return page;
    }
}

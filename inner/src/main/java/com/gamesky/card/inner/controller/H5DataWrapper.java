package com.gamesky.card.inner.controller;

import com.gamesky.card.core.model.H5Game;

import java.util.List;

/**
 * lianghongbin on 15/8/5.
 */
public class H5DataWrapper {

    private Status status;
    private Page page;
    private Info data;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Info getData() {
        return data;
    }

    public void setData(Info data) {
        this.data = data;
    }

    class Status {
        private int code;
        private String message;
        private int isonline;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public int isonline() {
            return isonline;
        }

        public void setIsonline(int isonline) {
            this.isonline = isonline;
        }
    }

    class Page {
        private int pagetotal;
        private int pagecount;
        private int pagenum;

        public int getPagetotal() {
            return pagetotal;
        }

        public void setPagetotal(int pagetotal) {
            this.pagetotal = pagetotal;
        }

        public int getPagecount() {
            return pagecount;
        }

        public void setPagecount(int pagecount) {
            this.pagecount = pagecount;
        }

        public int getPagenum() {
            return pagenum;
        }

        public void setPagenum(int pagenum) {
            this.pagenum = pagenum;
        }
    }

    class Info {
        private int wxgameid;
        private int wxgamecount;
        private int newgameid;
        private int newgamecount;
        private int hotgamecount;
        private List<H5Game> wxgamelist;
        private List<H5Game> newgamelist;
        private List<H5Game> hotgamelist;
        private H5Game recommendgame;

        public int getWxgameid() {
            return wxgameid;
        }

        public void setWxgameid(int wxgameid) {
            this.wxgameid = wxgameid;
        }

        public int getWxgamecount() {
            return wxgamecount;
        }

        public void setWxgamecount(int wxgamecount) {
            this.wxgamecount = wxgamecount;
        }

        public int getNewgameid() {
            return newgameid;
        }

        public void setNewgameid(int newgameid) {
            this.newgameid = newgameid;
        }

        public int getNewgamecount() {
            return newgamecount;
        }

        public void setNewgamecount(int newgamecount) {
            this.newgamecount = newgamecount;
        }

        public int getHotgamecount() {
            return hotgamecount;
        }

        public void setHotgamecount(int hotgamecount) {
            this.hotgamecount = hotgamecount;
        }

        public List<H5Game> getWxgamelist() {
            return wxgamelist;
        }

        public void setWxgamelist(List<H5Game> wxgamelist) {
            this.wxgamelist = wxgamelist;
        }

        public List<H5Game> getNewgamelist() {
            return newgamelist;
        }

        public void setNewgamelist(List<H5Game> newgamelist) {
            this.newgamelist = newgamelist;
        }

        public List<H5Game> getHotgamelist() {
            return hotgamelist;
        }

        public void setHotgamelist(List<H5Game> hotgamelist) {
            this.hotgamelist = hotgamelist;
        }

        public H5Game getRecommendgame() {
            return recommendgame;
        }

        public void setRecommendgame(H5Game recommendgame) {
            this.recommendgame = recommendgame;
        }
    }
}

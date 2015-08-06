package com.gamesky.card.core.model;

public class H5Game {
    private Integer aid;

    private String title;

    private Integer sort;

    private String gamePic;

    private String gameIntro;

    private String gameUrl;

    private String gameOrientation;

    private Boolean gameIswx;

    private Boolean gameIshot;

    private String gameBanner;

    private String gamePopulation;

    private Long createTime;

    private Long updateTime;

    private Boolean recommend;

    private String platform;

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getGamePic() {
        return gamePic;
    }

    public void setGamePic(String gamePic) {
        this.gamePic = gamePic;
    }

    public String getGameIntro() {
        return gameIntro;
    }

    public void setGameIntro(String gameIntro) {
        this.gameIntro = gameIntro;
    }

    public String getGameUrl() {
        return gameUrl;
    }

    public void setGameUrl(String gameUrl) {
        this.gameUrl = gameUrl;
    }

    public String getGameOrientation() {
        return gameOrientation;
    }

    public void setGameOrientation(String gameOrientation) {
        this.gameOrientation = gameOrientation;
    }

    public Boolean getGameIswx() {
        return gameIswx;
    }

    public void setGameIswx(Boolean gameIswx) {
        this.gameIswx = gameIswx;
    }

    public Boolean getGameIshot() {
        return gameIshot;
    }

    public void setGameIshot(Boolean gameIshot) {
        this.gameIshot = gameIshot;
    }

    public String getGameBanner() {
        return gameBanner;
    }

    public void setGameBanner(String gameBanner) {
        this.gameBanner = gameBanner;
    }

    public String getGamePopulation() {
        return gamePopulation;
    }

    public void setGamePopulation(String gamePopulation) {
        this.gamePopulation = gamePopulation;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Boolean getRecommend() {
        return recommend;
    }

    public void setRecommend(Boolean recommend) {
        this.recommend = recommend;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        H5Game other = (H5Game) that;
        return (this.getAid() == null ? other.getAid() == null : this.getAid().equals(other.getAid()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getGamePic() == null ? other.getGamePic() == null : this.getGamePic().equals(other.getGamePic()))
            && (this.getGameIntro() == null ? other.getGameIntro() == null : this.getGameIntro().equals(other.getGameIntro()))
            && (this.getGameUrl() == null ? other.getGameUrl() == null : this.getGameUrl().equals(other.getGameUrl()))
            && (this.getGameOrientation() == null ? other.getGameOrientation() == null : this.getGameOrientation().equals(other.getGameOrientation()))
            && (this.getGameIswx() == null ? other.getGameIswx() == null : this.getGameIswx().equals(other.getGameIswx()))
            && (this.getGameIshot() == null ? other.getGameIshot() == null : this.getGameIshot().equals(other.getGameIshot()))
            && (this.getGameBanner() == null ? other.getGameBanner() == null : this.getGameBanner().equals(other.getGameBanner()))
            && (this.getGamePopulation() == null ? other.getGamePopulation() == null : this.getGamePopulation().equals(other.getGamePopulation()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getRecommend() == null ? other.getRecommend() == null : this.getRecommend().equals(other.getRecommend()))
            && (this.getPlatform() == null ? other.getPlatform() == null : this.getPlatform().equals(other.getPlatform()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getAid() == null) ? 0 : getAid().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getGamePic() == null) ? 0 : getGamePic().hashCode());
        result = prime * result + ((getGameIntro() == null) ? 0 : getGameIntro().hashCode());
        result = prime * result + ((getGameUrl() == null) ? 0 : getGameUrl().hashCode());
        result = prime * result + ((getGameOrientation() == null) ? 0 : getGameOrientation().hashCode());
        result = prime * result + ((getGameIswx() == null) ? 0 : getGameIswx().hashCode());
        result = prime * result + ((getGameIshot() == null) ? 0 : getGameIshot().hashCode());
        result = prime * result + ((getGameBanner() == null) ? 0 : getGameBanner().hashCode());
        result = prime * result + ((getGamePopulation() == null) ? 0 : getGamePopulation().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getRecommend() == null) ? 0 : getRecommend().hashCode());
        result = prime * result + ((getPlatform() == null) ? 0 : getPlatform().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aid=").append(aid);
        sb.append(", title=").append(title);
        sb.append(", sort=").append(sort);
        sb.append(", gamePic=").append(gamePic);
        sb.append(", gameIntro=").append(gameIntro);
        sb.append(", gameUrl=").append(gameUrl);
        sb.append(", gameOrientation=").append(gameOrientation);
        sb.append(", gameIswx=").append(gameIswx);
        sb.append(", gameIshot=").append(gameIshot);
        sb.append(", gameBanner=").append(gameBanner);
        sb.append(", gamePopulation=").append(gamePopulation);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", recommend=").append(recommend);
        sb.append(", platform=").append(platform);
        sb.append("]");
        return sb.toString();
    }
}
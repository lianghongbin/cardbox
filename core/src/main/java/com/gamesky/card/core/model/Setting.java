package com.gamesky.card.core.model;

public class Setting {
    private Integer id;

    private String us;

    private Integer weixin;

    private Integer qq;

    private Integer daily;

    private Integer registry;

    private String v;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUs() {
        return us;
    }

    public void setUs(String us) {
        this.us = us;
    }

    public Integer getWeixin() {
        return weixin;
    }

    public void setWeixin(Integer weixin) {
        this.weixin = weixin;
    }

    public Integer getQq() {
        return qq;
    }

    public void setQq(Integer qq) {
        this.qq = qq;
    }

    public Integer getDaily() {
        return daily;
    }

    public void setDaily(Integer daily) {
        this.daily = daily;
    }

    public Integer getRegistry() {
        return registry;
    }

    public void setRegistry(Integer registry) {
        this.registry = registry;
    }

    public String getV() {
        return v;
    }

    public void setV(String v) {
        this.v = v;
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
        Setting other = (Setting) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUs() == null ? other.getUs() == null : this.getUs().equals(other.getUs()))
            && (this.getWeixin() == null ? other.getWeixin() == null : this.getWeixin().equals(other.getWeixin()))
            && (this.getQq() == null ? other.getQq() == null : this.getQq().equals(other.getQq()))
            && (this.getDaily() == null ? other.getDaily() == null : this.getDaily().equals(other.getDaily()))
            && (this.getRegistry() == null ? other.getRegistry() == null : this.getRegistry().equals(other.getRegistry()))
            && (this.getV() == null ? other.getV() == null : this.getV().equals(other.getV()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUs() == null) ? 0 : getUs().hashCode());
        result = prime * result + ((getWeixin() == null) ? 0 : getWeixin().hashCode());
        result = prime * result + ((getQq() == null) ? 0 : getQq().hashCode());
        result = prime * result + ((getDaily() == null) ? 0 : getDaily().hashCode());
        result = prime * result + ((getRegistry() == null) ? 0 : getRegistry().hashCode());
        result = prime * result + ((getV() == null) ? 0 : getV().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", us=").append(us);
        sb.append(", weixin=").append(weixin);
        sb.append(", qq=").append(qq);
        sb.append(", daily=").append(daily);
        sb.append(", registry=").append(registry);
        sb.append(", v=").append(v);
        sb.append("]");
        return sb.toString();
    }
}
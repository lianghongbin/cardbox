package com.gamesky.card.core.model;

import java.util.Date;

public class Databasechangelog {
    private String id;

    private String author;

    private String filename;

    private Date dateexecuted;

    private Integer orderexecuted;

    private String exectype;

    private String md5sum;

    private String description;

    private String comments;

    private String tag;

    private String liquibase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public Date getDateexecuted() {
        return dateexecuted;
    }

    public void setDateexecuted(Date dateexecuted) {
        this.dateexecuted = dateexecuted;
    }

    public Integer getOrderexecuted() {
        return orderexecuted;
    }

    public void setOrderexecuted(Integer orderexecuted) {
        this.orderexecuted = orderexecuted;
    }

    public String getExectype() {
        return exectype;
    }

    public void setExectype(String exectype) {
        this.exectype = exectype;
    }

    public String getMd5sum() {
        return md5sum;
    }

    public void setMd5sum(String md5sum) {
        this.md5sum = md5sum;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getLiquibase() {
        return liquibase;
    }

    public void setLiquibase(String liquibase) {
        this.liquibase = liquibase;
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
        Databasechangelog other = (Databasechangelog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getFilename() == null ? other.getFilename() == null : this.getFilename().equals(other.getFilename()))
            && (this.getDateexecuted() == null ? other.getDateexecuted() == null : this.getDateexecuted().equals(other.getDateexecuted()))
            && (this.getOrderexecuted() == null ? other.getOrderexecuted() == null : this.getOrderexecuted().equals(other.getOrderexecuted()))
            && (this.getExectype() == null ? other.getExectype() == null : this.getExectype().equals(other.getExectype()))
            && (this.getMd5sum() == null ? other.getMd5sum() == null : this.getMd5sum().equals(other.getMd5sum()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getComments() == null ? other.getComments() == null : this.getComments().equals(other.getComments()))
            && (this.getTag() == null ? other.getTag() == null : this.getTag().equals(other.getTag()))
            && (this.getLiquibase() == null ? other.getLiquibase() == null : this.getLiquibase().equals(other.getLiquibase()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getFilename() == null) ? 0 : getFilename().hashCode());
        result = prime * result + ((getDateexecuted() == null) ? 0 : getDateexecuted().hashCode());
        result = prime * result + ((getOrderexecuted() == null) ? 0 : getOrderexecuted().hashCode());
        result = prime * result + ((getExectype() == null) ? 0 : getExectype().hashCode());
        result = prime * result + ((getMd5sum() == null) ? 0 : getMd5sum().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getComments() == null) ? 0 : getComments().hashCode());
        result = prime * result + ((getTag() == null) ? 0 : getTag().hashCode());
        result = prime * result + ((getLiquibase() == null) ? 0 : getLiquibase().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", author=").append(author);
        sb.append(", filename=").append(filename);
        sb.append(", dateexecuted=").append(dateexecuted);
        sb.append(", orderexecuted=").append(orderexecuted);
        sb.append(", exectype=").append(exectype);
        sb.append(", md5sum=").append(md5sum);
        sb.append(", description=").append(description);
        sb.append(", comments=").append(comments);
        sb.append(", tag=").append(tag);
        sb.append(", liquibase=").append(liquibase);
        sb.append("]");
        return sb.toString();
    }
}
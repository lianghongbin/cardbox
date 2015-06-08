package com.gamesky.card.core.model;

import java.util.Date;

public class Key {
    private Integer id;

    private Integer cardId;

    private Integer code;

    private Boolean assigned;

    private Boolean used;

    private Date assignTime;

    private Date useTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Boolean getAssigned() {
        return assigned;
    }

    public void setAssigned(Boolean assigned) {
        this.assigned = assigned;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public Date getAssignTime() {
        return assignTime;
    }

    public void setAssignTime(Date assignTime) {
        this.assignTime = assignTime;
    }

    public Date getUseTime() {
        return useTime;
    }

    public void setUseTime(Date useTime) {
        this.useTime = useTime;
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
        Key other = (Key) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCardId() == null ? other.getCardId() == null : this.getCardId().equals(other.getCardId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getAssigned() == null ? other.getAssigned() == null : this.getAssigned().equals(other.getAssigned()))
            && (this.getUsed() == null ? other.getUsed() == null : this.getUsed().equals(other.getUsed()))
            && (this.getAssignTime() == null ? other.getAssignTime() == null : this.getAssignTime().equals(other.getAssignTime()))
            && (this.getUseTime() == null ? other.getUseTime() == null : this.getUseTime().equals(other.getUseTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCardId() == null) ? 0 : getCardId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getAssigned() == null) ? 0 : getAssigned().hashCode());
        result = prime * result + ((getUsed() == null) ? 0 : getUsed().hashCode());
        result = prime * result + ((getAssignTime() == null) ? 0 : getAssignTime().hashCode());
        result = prime * result + ((getUseTime() == null) ? 0 : getUseTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", cardId=").append(cardId);
        sb.append(", code=").append(code);
        sb.append(", assigned=").append(assigned);
        sb.append(", used=").append(used);
        sb.append(", assignTime=").append(assignTime);
        sb.append(", useTime=").append(useTime);
        sb.append("]");
        return sb.toString();
    }
}
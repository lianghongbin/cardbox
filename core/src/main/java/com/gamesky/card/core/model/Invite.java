package com.gamesky.card.core.model;

public class Invite {
    private Integer id;

    private String phone;

    private String invitedPhone;

    private Long invitedTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getInvitedPhone() {
        return invitedPhone;
    }

    public void setInvitedPhone(String invitedPhone) {
        this.invitedPhone = invitedPhone;
    }

    public Long getInvitedTime() {
        return invitedTime;
    }

    public void setInvitedTime(Long invitedTime) {
        this.invitedTime = invitedTime;
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
        Invite other = (Invite) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getInvitedPhone() == null ? other.getInvitedPhone() == null : this.getInvitedPhone().equals(other.getInvitedPhone()))
            && (this.getInvitedTime() == null ? other.getInvitedTime() == null : this.getInvitedTime().equals(other.getInvitedTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getInvitedPhone() == null) ? 0 : getInvitedPhone().hashCode());
        result = prime * result + ((getInvitedTime() == null) ? 0 : getInvitedTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", phone=").append(phone);
        sb.append(", invitedPhone=").append(invitedPhone);
        sb.append(", invitedTime=").append(invitedTime);
        sb.append("]");
        return sb.toString();
    }
}
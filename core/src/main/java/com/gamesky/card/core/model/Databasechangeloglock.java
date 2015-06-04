package com.gamesky.card.core.model;

import java.util.Date;

public class Databasechangeloglock {
    private Integer id;

    private Boolean locked;

    private Date lockgranted;

    private String lockedby;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public Date getLockgranted() {
        return lockgranted;
    }

    public void setLockgranted(Date lockgranted) {
        this.lockgranted = lockgranted;
    }

    public String getLockedby() {
        return lockedby;
    }

    public void setLockedby(String lockedby) {
        this.lockedby = lockedby;
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
        Databasechangeloglock other = (Databasechangeloglock) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getLocked() == null ? other.getLocked() == null : this.getLocked().equals(other.getLocked()))
            && (this.getLockgranted() == null ? other.getLockgranted() == null : this.getLockgranted().equals(other.getLockgranted()))
            && (this.getLockedby() == null ? other.getLockedby() == null : this.getLockedby().equals(other.getLockedby()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getLocked() == null) ? 0 : getLocked().hashCode());
        result = prime * result + ((getLockgranted() == null) ? 0 : getLockgranted().hashCode());
        result = prime * result + ((getLockedby() == null) ? 0 : getLockedby().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", locked=").append(locked);
        sb.append(", lockgranted=").append(lockgranted);
        sb.append(", lockedby=").append(lockedby);
        sb.append("]");
        return sb.toString();
    }
}
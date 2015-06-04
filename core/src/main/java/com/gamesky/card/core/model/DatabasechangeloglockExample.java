package com.gamesky.card.core.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DatabasechangeloglockExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitOffset;

    protected Integer limit;

    public DatabasechangeloglockExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    public void setLimitOffset(Integer limitOffset) {
        this.limitOffset=limitOffset;
    }

    public Integer getLimitOffset() {
        return limitOffset;
    }

    public void setLimit(Integer limit) {
        this.limit=limit;
    }

    public Integer getLimit() {
        return limit;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLockedIsNull() {
            addCriterion("LOCKED is null");
            return (Criteria) this;
        }

        public Criteria andLockedIsNotNull() {
            addCriterion("LOCKED is not null");
            return (Criteria) this;
        }

        public Criteria andLockedEqualTo(Boolean value) {
            addCriterion("LOCKED =", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotEqualTo(Boolean value) {
            addCriterion("LOCKED <>", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThan(Boolean value) {
            addCriterion("LOCKED >", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedGreaterThanOrEqualTo(Boolean value) {
            addCriterion("LOCKED >=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThan(Boolean value) {
            addCriterion("LOCKED <", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedLessThanOrEqualTo(Boolean value) {
            addCriterion("LOCKED <=", value, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedIn(List<Boolean> values) {
            addCriterion("LOCKED in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotIn(List<Boolean> values) {
            addCriterion("LOCKED not in", values, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedBetween(Boolean value1, Boolean value2) {
            addCriterion("LOCKED between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockedNotBetween(Boolean value1, Boolean value2) {
            addCriterion("LOCKED not between", value1, value2, "locked");
            return (Criteria) this;
        }

        public Criteria andLockgrantedIsNull() {
            addCriterion("LOCKGRANTED is null");
            return (Criteria) this;
        }

        public Criteria andLockgrantedIsNotNull() {
            addCriterion("LOCKGRANTED is not null");
            return (Criteria) this;
        }

        public Criteria andLockgrantedEqualTo(Date value) {
            addCriterion("LOCKGRANTED =", value, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedNotEqualTo(Date value) {
            addCriterion("LOCKGRANTED <>", value, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedGreaterThan(Date value) {
            addCriterion("LOCKGRANTED >", value, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedGreaterThanOrEqualTo(Date value) {
            addCriterion("LOCKGRANTED >=", value, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedLessThan(Date value) {
            addCriterion("LOCKGRANTED <", value, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedLessThanOrEqualTo(Date value) {
            addCriterion("LOCKGRANTED <=", value, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedIn(List<Date> values) {
            addCriterion("LOCKGRANTED in", values, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedNotIn(List<Date> values) {
            addCriterion("LOCKGRANTED not in", values, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedBetween(Date value1, Date value2) {
            addCriterion("LOCKGRANTED between", value1, value2, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockgrantedNotBetween(Date value1, Date value2) {
            addCriterion("LOCKGRANTED not between", value1, value2, "lockgranted");
            return (Criteria) this;
        }

        public Criteria andLockedbyIsNull() {
            addCriterion("LOCKEDBY is null");
            return (Criteria) this;
        }

        public Criteria andLockedbyIsNotNull() {
            addCriterion("LOCKEDBY is not null");
            return (Criteria) this;
        }

        public Criteria andLockedbyEqualTo(String value) {
            addCriterion("LOCKEDBY =", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyNotEqualTo(String value) {
            addCriterion("LOCKEDBY <>", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyGreaterThan(String value) {
            addCriterion("LOCKEDBY >", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyGreaterThanOrEqualTo(String value) {
            addCriterion("LOCKEDBY >=", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyLessThan(String value) {
            addCriterion("LOCKEDBY <", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyLessThanOrEqualTo(String value) {
            addCriterion("LOCKEDBY <=", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyLike(String value) {
            addCriterion("LOCKEDBY like", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyNotLike(String value) {
            addCriterion("LOCKEDBY not like", value, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyIn(List<String> values) {
            addCriterion("LOCKEDBY in", values, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyNotIn(List<String> values) {
            addCriterion("LOCKEDBY not in", values, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyBetween(String value1, String value2) {
            addCriterion("LOCKEDBY between", value1, value2, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyNotBetween(String value1, String value2) {
            addCriterion("LOCKEDBY not between", value1, value2, "lockedby");
            return (Criteria) this;
        }

        public Criteria andLockedbyLikeInsensitive(String value) {
            addCriterion("upper(LOCKEDBY) like", value.toUpperCase(), "lockedby");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
package com.gamesky.card.core.model;

import java.util.ArrayList;
import java.util.List;

public class SettingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitOffset;

    protected Integer limit;

    public SettingExample() {
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
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andUsIsNull() {
            addCriterion("us is null");
            return (Criteria) this;
        }

        public Criteria andUsIsNotNull() {
            addCriterion("us is not null");
            return (Criteria) this;
        }

        public Criteria andUsEqualTo(String value) {
            addCriterion("us =", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsNotEqualTo(String value) {
            addCriterion("us <>", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsGreaterThan(String value) {
            addCriterion("us >", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsGreaterThanOrEqualTo(String value) {
            addCriterion("us >=", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsLessThan(String value) {
            addCriterion("us <", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsLessThanOrEqualTo(String value) {
            addCriterion("us <=", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsLike(String value) {
            addCriterion("us like", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsNotLike(String value) {
            addCriterion("us not like", value, "us");
            return (Criteria) this;
        }

        public Criteria andUsIn(List<String> values) {
            addCriterion("us in", values, "us");
            return (Criteria) this;
        }

        public Criteria andUsNotIn(List<String> values) {
            addCriterion("us not in", values, "us");
            return (Criteria) this;
        }

        public Criteria andUsBetween(String value1, String value2) {
            addCriterion("us between", value1, value2, "us");
            return (Criteria) this;
        }

        public Criteria andUsNotBetween(String value1, String value2) {
            addCriterion("us not between", value1, value2, "us");
            return (Criteria) this;
        }

        public Criteria andAnnounceIsNull() {
            addCriterion("announce is null");
            return (Criteria) this;
        }

        public Criteria andAnnounceIsNotNull() {
            addCriterion("announce is not null");
            return (Criteria) this;
        }

        public Criteria andAnnounceEqualTo(String value) {
            addCriterion("announce =", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceNotEqualTo(String value) {
            addCriterion("announce <>", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceGreaterThan(String value) {
            addCriterion("announce >", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceGreaterThanOrEqualTo(String value) {
            addCriterion("announce >=", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceLessThan(String value) {
            addCriterion("announce <", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceLessThanOrEqualTo(String value) {
            addCriterion("announce <=", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceLike(String value) {
            addCriterion("announce like", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceNotLike(String value) {
            addCriterion("announce not like", value, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceIn(List<String> values) {
            addCriterion("announce in", values, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceNotIn(List<String> values) {
            addCriterion("announce not in", values, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceBetween(String value1, String value2) {
            addCriterion("announce between", value1, value2, "announce");
            return (Criteria) this;
        }

        public Criteria andAnnounceNotBetween(String value1, String value2) {
            addCriterion("announce not between", value1, value2, "announce");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNull() {
            addCriterion("weixin is null");
            return (Criteria) this;
        }

        public Criteria andWeixinIsNotNull() {
            addCriterion("weixin is not null");
            return (Criteria) this;
        }

        public Criteria andWeixinEqualTo(Integer value) {
            addCriterion("weixin =", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotEqualTo(Integer value) {
            addCriterion("weixin <>", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThan(Integer value) {
            addCriterion("weixin >", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinGreaterThanOrEqualTo(Integer value) {
            addCriterion("weixin >=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThan(Integer value) {
            addCriterion("weixin <", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinLessThanOrEqualTo(Integer value) {
            addCriterion("weixin <=", value, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinIn(List<Integer> values) {
            addCriterion("weixin in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotIn(List<Integer> values) {
            addCriterion("weixin not in", values, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinBetween(Integer value1, Integer value2) {
            addCriterion("weixin between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andWeixinNotBetween(Integer value1, Integer value2) {
            addCriterion("weixin not between", value1, value2, "weixin");
            return (Criteria) this;
        }

        public Criteria andQqIsNull() {
            addCriterion("qq is null");
            return (Criteria) this;
        }

        public Criteria andQqIsNotNull() {
            addCriterion("qq is not null");
            return (Criteria) this;
        }

        public Criteria andQqEqualTo(Integer value) {
            addCriterion("qq =", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotEqualTo(Integer value) {
            addCriterion("qq <>", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThan(Integer value) {
            addCriterion("qq >", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqGreaterThanOrEqualTo(Integer value) {
            addCriterion("qq >=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThan(Integer value) {
            addCriterion("qq <", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqLessThanOrEqualTo(Integer value) {
            addCriterion("qq <=", value, "qq");
            return (Criteria) this;
        }

        public Criteria andQqIn(List<Integer> values) {
            addCriterion("qq in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotIn(List<Integer> values) {
            addCriterion("qq not in", values, "qq");
            return (Criteria) this;
        }

        public Criteria andQqBetween(Integer value1, Integer value2) {
            addCriterion("qq between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andQqNotBetween(Integer value1, Integer value2) {
            addCriterion("qq not between", value1, value2, "qq");
            return (Criteria) this;
        }

        public Criteria andDailyIsNull() {
            addCriterion("daily is null");
            return (Criteria) this;
        }

        public Criteria andDailyIsNotNull() {
            addCriterion("daily is not null");
            return (Criteria) this;
        }

        public Criteria andDailyEqualTo(Integer value) {
            addCriterion("daily =", value, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyNotEqualTo(Integer value) {
            addCriterion("daily <>", value, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyGreaterThan(Integer value) {
            addCriterion("daily >", value, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyGreaterThanOrEqualTo(Integer value) {
            addCriterion("daily >=", value, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyLessThan(Integer value) {
            addCriterion("daily <", value, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyLessThanOrEqualTo(Integer value) {
            addCriterion("daily <=", value, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyIn(List<Integer> values) {
            addCriterion("daily in", values, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyNotIn(List<Integer> values) {
            addCriterion("daily not in", values, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyBetween(Integer value1, Integer value2) {
            addCriterion("daily between", value1, value2, "daily");
            return (Criteria) this;
        }

        public Criteria andDailyNotBetween(Integer value1, Integer value2) {
            addCriterion("daily not between", value1, value2, "daily");
            return (Criteria) this;
        }

        public Criteria andRegistryIsNull() {
            addCriterion("registry is null");
            return (Criteria) this;
        }

        public Criteria andRegistryIsNotNull() {
            addCriterion("registry is not null");
            return (Criteria) this;
        }

        public Criteria andRegistryEqualTo(Integer value) {
            addCriterion("registry =", value, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryNotEqualTo(Integer value) {
            addCriterion("registry <>", value, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryGreaterThan(Integer value) {
            addCriterion("registry >", value, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryGreaterThanOrEqualTo(Integer value) {
            addCriterion("registry >=", value, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryLessThan(Integer value) {
            addCriterion("registry <", value, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryLessThanOrEqualTo(Integer value) {
            addCriterion("registry <=", value, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryIn(List<Integer> values) {
            addCriterion("registry in", values, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryNotIn(List<Integer> values) {
            addCriterion("registry not in", values, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryBetween(Integer value1, Integer value2) {
            addCriterion("registry between", value1, value2, "registry");
            return (Criteria) this;
        }

        public Criteria andRegistryNotBetween(Integer value1, Integer value2) {
            addCriterion("registry not between", value1, value2, "registry");
            return (Criteria) this;
        }

        public Criteria andVIsNull() {
            addCriterion("v is null");
            return (Criteria) this;
        }

        public Criteria andVIsNotNull() {
            addCriterion("v is not null");
            return (Criteria) this;
        }

        public Criteria andVEqualTo(String value) {
            addCriterion("v =", value, "v");
            return (Criteria) this;
        }

        public Criteria andVNotEqualTo(String value) {
            addCriterion("v <>", value, "v");
            return (Criteria) this;
        }

        public Criteria andVGreaterThan(String value) {
            addCriterion("v >", value, "v");
            return (Criteria) this;
        }

        public Criteria andVGreaterThanOrEqualTo(String value) {
            addCriterion("v >=", value, "v");
            return (Criteria) this;
        }

        public Criteria andVLessThan(String value) {
            addCriterion("v <", value, "v");
            return (Criteria) this;
        }

        public Criteria andVLessThanOrEqualTo(String value) {
            addCriterion("v <=", value, "v");
            return (Criteria) this;
        }

        public Criteria andVLike(String value) {
            addCriterion("v like", value, "v");
            return (Criteria) this;
        }

        public Criteria andVNotLike(String value) {
            addCriterion("v not like", value, "v");
            return (Criteria) this;
        }

        public Criteria andVIn(List<String> values) {
            addCriterion("v in", values, "v");
            return (Criteria) this;
        }

        public Criteria andVNotIn(List<String> values) {
            addCriterion("v not in", values, "v");
            return (Criteria) this;
        }

        public Criteria andVBetween(String value1, String value2) {
            addCriterion("v between", value1, value2, "v");
            return (Criteria) this;
        }

        public Criteria andVNotBetween(String value1, String value2) {
            addCriterion("v not between", value1, value2, "v");
            return (Criteria) this;
        }

        public Criteria andDownloadIsNull() {
            addCriterion("download is null");
            return (Criteria) this;
        }

        public Criteria andDownloadIsNotNull() {
            addCriterion("download is not null");
            return (Criteria) this;
        }

        public Criteria andDownloadEqualTo(Integer value) {
            addCriterion("download =", value, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadNotEqualTo(Integer value) {
            addCriterion("download <>", value, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadGreaterThan(Integer value) {
            addCriterion("download >", value, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadGreaterThanOrEqualTo(Integer value) {
            addCriterion("download >=", value, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadLessThan(Integer value) {
            addCriterion("download <", value, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadLessThanOrEqualTo(Integer value) {
            addCriterion("download <=", value, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadIn(List<Integer> values) {
            addCriterion("download in", values, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadNotIn(List<Integer> values) {
            addCriterion("download not in", values, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadBetween(Integer value1, Integer value2) {
            addCriterion("download between", value1, value2, "download");
            return (Criteria) this;
        }

        public Criteria andDownloadNotBetween(Integer value1, Integer value2) {
            addCriterion("download not between", value1, value2, "download");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Long value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Long value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Long value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Long value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Long value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Long> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Long> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Long value1, Long value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Long value1, Long value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andAndroidIsNull() {
            addCriterion("android is null");
            return (Criteria) this;
        }

        public Criteria andAndroidIsNotNull() {
            addCriterion("android is not null");
            return (Criteria) this;
        }

        public Criteria andAndroidEqualTo(String value) {
            addCriterion("android =", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidNotEqualTo(String value) {
            addCriterion("android <>", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidGreaterThan(String value) {
            addCriterion("android >", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidGreaterThanOrEqualTo(String value) {
            addCriterion("android >=", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidLessThan(String value) {
            addCriterion("android <", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidLessThanOrEqualTo(String value) {
            addCriterion("android <=", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidLike(String value) {
            addCriterion("android like", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidNotLike(String value) {
            addCriterion("android not like", value, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidIn(List<String> values) {
            addCriterion("android in", values, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidNotIn(List<String> values) {
            addCriterion("android not in", values, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidBetween(String value1, String value2) {
            addCriterion("android between", value1, value2, "android");
            return (Criteria) this;
        }

        public Criteria andAndroidNotBetween(String value1, String value2) {
            addCriterion("android not between", value1, value2, "android");
            return (Criteria) this;
        }

        public Criteria andIosIsNull() {
            addCriterion("ios is null");
            return (Criteria) this;
        }

        public Criteria andIosIsNotNull() {
            addCriterion("ios is not null");
            return (Criteria) this;
        }

        public Criteria andIosEqualTo(String value) {
            addCriterion("ios =", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosNotEqualTo(String value) {
            addCriterion("ios <>", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosGreaterThan(String value) {
            addCriterion("ios >", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosGreaterThanOrEqualTo(String value) {
            addCriterion("ios >=", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosLessThan(String value) {
            addCriterion("ios <", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosLessThanOrEqualTo(String value) {
            addCriterion("ios <=", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosLike(String value) {
            addCriterion("ios like", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosNotLike(String value) {
            addCriterion("ios not like", value, "ios");
            return (Criteria) this;
        }

        public Criteria andIosIn(List<String> values) {
            addCriterion("ios in", values, "ios");
            return (Criteria) this;
        }

        public Criteria andIosNotIn(List<String> values) {
            addCriterion("ios not in", values, "ios");
            return (Criteria) this;
        }

        public Criteria andIosBetween(String value1, String value2) {
            addCriterion("ios between", value1, value2, "ios");
            return (Criteria) this;
        }

        public Criteria andIosNotBetween(String value1, String value2) {
            addCriterion("ios not between", value1, value2, "ios");
            return (Criteria) this;
        }

        public Criteria andH5IsNull() {
            addCriterion("h5 is null");
            return (Criteria) this;
        }

        public Criteria andH5IsNotNull() {
            addCriterion("h5 is not null");
            return (Criteria) this;
        }

        public Criteria andH5EqualTo(Integer value) {
            addCriterion("h5 =", value, "h5");
            return (Criteria) this;
        }

        public Criteria andH5NotEqualTo(Integer value) {
            addCriterion("h5 <>", value, "h5");
            return (Criteria) this;
        }

        public Criteria andH5GreaterThan(Integer value) {
            addCriterion("h5 >", value, "h5");
            return (Criteria) this;
        }

        public Criteria andH5GreaterThanOrEqualTo(Integer value) {
            addCriterion("h5 >=", value, "h5");
            return (Criteria) this;
        }

        public Criteria andH5LessThan(Integer value) {
            addCriterion("h5 <", value, "h5");
            return (Criteria) this;
        }

        public Criteria andH5LessThanOrEqualTo(Integer value) {
            addCriterion("h5 <=", value, "h5");
            return (Criteria) this;
        }

        public Criteria andH5In(List<Integer> values) {
            addCriterion("h5 in", values, "h5");
            return (Criteria) this;
        }

        public Criteria andH5NotIn(List<Integer> values) {
            addCriterion("h5 not in", values, "h5");
            return (Criteria) this;
        }

        public Criteria andH5Between(Integer value1, Integer value2) {
            addCriterion("h5 between", value1, value2, "h5");
            return (Criteria) this;
        }

        public Criteria andH5NotBetween(Integer value1, Integer value2) {
            addCriterion("h5 not between", value1, value2, "h5");
            return (Criteria) this;
        }

        public Criteria andUsLikeInsensitive(String value) {
            addCriterion("upper(us) like", value.toUpperCase(), "us");
            return (Criteria) this;
        }

        public Criteria andAnnounceLikeInsensitive(String value) {
            addCriterion("upper(announce) like", value.toUpperCase(), "announce");
            return (Criteria) this;
        }

        public Criteria andVLikeInsensitive(String value) {
            addCriterion("upper(v) like", value.toUpperCase(), "v");
            return (Criteria) this;
        }

        public Criteria andAndroidLikeInsensitive(String value) {
            addCriterion("upper(android) like", value.toUpperCase(), "android");
            return (Criteria) this;
        }

        public Criteria andIosLikeInsensitive(String value) {
            addCriterion("upper(ios) like", value.toUpperCase(), "ios");
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
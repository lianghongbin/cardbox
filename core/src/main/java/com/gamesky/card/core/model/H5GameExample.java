package com.gamesky.card.core.model;

import java.util.ArrayList;
import java.util.List;

public class H5GameExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    protected Integer limitOffset;

    protected Integer limit;

    public H5GameExample() {
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

        public Criteria andAidIsNull() {
            addCriterion("aid is null");
            return (Criteria) this;
        }

        public Criteria andAidIsNotNull() {
            addCriterion("aid is not null");
            return (Criteria) this;
        }

        public Criteria andAidEqualTo(Integer value) {
            addCriterion("aid =", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotEqualTo(Integer value) {
            addCriterion("aid <>", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThan(Integer value) {
            addCriterion("aid >", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidGreaterThanOrEqualTo(Integer value) {
            addCriterion("aid >=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThan(Integer value) {
            addCriterion("aid <", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidLessThanOrEqualTo(Integer value) {
            addCriterion("aid <=", value, "aid");
            return (Criteria) this;
        }

        public Criteria andAidIn(List<Integer> values) {
            addCriterion("aid in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotIn(List<Integer> values) {
            addCriterion("aid not in", values, "aid");
            return (Criteria) this;
        }

        public Criteria andAidBetween(Integer value1, Integer value2) {
            addCriterion("aid between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andAidNotBetween(Integer value1, Integer value2) {
            addCriterion("aid not between", value1, value2, "aid");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andSortIsNull() {
            addCriterion("sort is null");
            return (Criteria) this;
        }

        public Criteria andSortIsNotNull() {
            addCriterion("sort is not null");
            return (Criteria) this;
        }

        public Criteria andSortEqualTo(Integer value) {
            addCriterion("sort =", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotEqualTo(Integer value) {
            addCriterion("sort <>", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThan(Integer value) {
            addCriterion("sort >", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortGreaterThanOrEqualTo(Integer value) {
            addCriterion("sort >=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThan(Integer value) {
            addCriterion("sort <", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortLessThanOrEqualTo(Integer value) {
            addCriterion("sort <=", value, "sort");
            return (Criteria) this;
        }

        public Criteria andSortIn(List<Integer> values) {
            addCriterion("sort in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotIn(List<Integer> values) {
            addCriterion("sort not in", values, "sort");
            return (Criteria) this;
        }

        public Criteria andSortBetween(Integer value1, Integer value2) {
            addCriterion("sort between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andSortNotBetween(Integer value1, Integer value2) {
            addCriterion("sort not between", value1, value2, "sort");
            return (Criteria) this;
        }

        public Criteria andGamePicIsNull() {
            addCriterion("game_pic is null");
            return (Criteria) this;
        }

        public Criteria andGamePicIsNotNull() {
            addCriterion("game_pic is not null");
            return (Criteria) this;
        }

        public Criteria andGamePicEqualTo(String value) {
            addCriterion("game_pic =", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicNotEqualTo(String value) {
            addCriterion("game_pic <>", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicGreaterThan(String value) {
            addCriterion("game_pic >", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicGreaterThanOrEqualTo(String value) {
            addCriterion("game_pic >=", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicLessThan(String value) {
            addCriterion("game_pic <", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicLessThanOrEqualTo(String value) {
            addCriterion("game_pic <=", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicLike(String value) {
            addCriterion("game_pic like", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicNotLike(String value) {
            addCriterion("game_pic not like", value, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicIn(List<String> values) {
            addCriterion("game_pic in", values, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicNotIn(List<String> values) {
            addCriterion("game_pic not in", values, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicBetween(String value1, String value2) {
            addCriterion("game_pic between", value1, value2, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGamePicNotBetween(String value1, String value2) {
            addCriterion("game_pic not between", value1, value2, "gamePic");
            return (Criteria) this;
        }

        public Criteria andGameIntroIsNull() {
            addCriterion("game_intro is null");
            return (Criteria) this;
        }

        public Criteria andGameIntroIsNotNull() {
            addCriterion("game_intro is not null");
            return (Criteria) this;
        }

        public Criteria andGameIntroEqualTo(String value) {
            addCriterion("game_intro =", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroNotEqualTo(String value) {
            addCriterion("game_intro <>", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroGreaterThan(String value) {
            addCriterion("game_intro >", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroGreaterThanOrEqualTo(String value) {
            addCriterion("game_intro >=", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroLessThan(String value) {
            addCriterion("game_intro <", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroLessThanOrEqualTo(String value) {
            addCriterion("game_intro <=", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroLike(String value) {
            addCriterion("game_intro like", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroNotLike(String value) {
            addCriterion("game_intro not like", value, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroIn(List<String> values) {
            addCriterion("game_intro in", values, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroNotIn(List<String> values) {
            addCriterion("game_intro not in", values, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroBetween(String value1, String value2) {
            addCriterion("game_intro between", value1, value2, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameIntroNotBetween(String value1, String value2) {
            addCriterion("game_intro not between", value1, value2, "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameUrlIsNull() {
            addCriterion("game_url is null");
            return (Criteria) this;
        }

        public Criteria andGameUrlIsNotNull() {
            addCriterion("game_url is not null");
            return (Criteria) this;
        }

        public Criteria andGameUrlEqualTo(String value) {
            addCriterion("game_url =", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlNotEqualTo(String value) {
            addCriterion("game_url <>", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlGreaterThan(String value) {
            addCriterion("game_url >", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlGreaterThanOrEqualTo(String value) {
            addCriterion("game_url >=", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlLessThan(String value) {
            addCriterion("game_url <", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlLessThanOrEqualTo(String value) {
            addCriterion("game_url <=", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlLike(String value) {
            addCriterion("game_url like", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlNotLike(String value) {
            addCriterion("game_url not like", value, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlIn(List<String> values) {
            addCriterion("game_url in", values, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlNotIn(List<String> values) {
            addCriterion("game_url not in", values, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlBetween(String value1, String value2) {
            addCriterion("game_url between", value1, value2, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameUrlNotBetween(String value1, String value2) {
            addCriterion("game_url not between", value1, value2, "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameOrientationIsNull() {
            addCriterion("game_orientation is null");
            return (Criteria) this;
        }

        public Criteria andGameOrientationIsNotNull() {
            addCriterion("game_orientation is not null");
            return (Criteria) this;
        }

        public Criteria andGameOrientationEqualTo(String value) {
            addCriterion("game_orientation =", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationNotEqualTo(String value) {
            addCriterion("game_orientation <>", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationGreaterThan(String value) {
            addCriterion("game_orientation >", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationGreaterThanOrEqualTo(String value) {
            addCriterion("game_orientation >=", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationLessThan(String value) {
            addCriterion("game_orientation <", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationLessThanOrEqualTo(String value) {
            addCriterion("game_orientation <=", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationLike(String value) {
            addCriterion("game_orientation like", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationNotLike(String value) {
            addCriterion("game_orientation not like", value, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationIn(List<String> values) {
            addCriterion("game_orientation in", values, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationNotIn(List<String> values) {
            addCriterion("game_orientation not in", values, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationBetween(String value1, String value2) {
            addCriterion("game_orientation between", value1, value2, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameOrientationNotBetween(String value1, String value2) {
            addCriterion("game_orientation not between", value1, value2, "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameIswxIsNull() {
            addCriterion("game_iswx is null");
            return (Criteria) this;
        }

        public Criteria andGameIswxIsNotNull() {
            addCriterion("game_iswx is not null");
            return (Criteria) this;
        }

        public Criteria andGameIswxEqualTo(Boolean value) {
            addCriterion("game_iswx =", value, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxNotEqualTo(Boolean value) {
            addCriterion("game_iswx <>", value, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxGreaterThan(Boolean value) {
            addCriterion("game_iswx >", value, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxGreaterThanOrEqualTo(Boolean value) {
            addCriterion("game_iswx >=", value, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxLessThan(Boolean value) {
            addCriterion("game_iswx <", value, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxLessThanOrEqualTo(Boolean value) {
            addCriterion("game_iswx <=", value, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxIn(List<Boolean> values) {
            addCriterion("game_iswx in", values, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxNotIn(List<Boolean> values) {
            addCriterion("game_iswx not in", values, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxBetween(Boolean value1, Boolean value2) {
            addCriterion("game_iswx between", value1, value2, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIswxNotBetween(Boolean value1, Boolean value2) {
            addCriterion("game_iswx not between", value1, value2, "gameIswx");
            return (Criteria) this;
        }

        public Criteria andGameIshotIsNull() {
            addCriterion("game_ishot is null");
            return (Criteria) this;
        }

        public Criteria andGameIshotIsNotNull() {
            addCriterion("game_ishot is not null");
            return (Criteria) this;
        }

        public Criteria andGameIshotEqualTo(Boolean value) {
            addCriterion("game_ishot =", value, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotNotEqualTo(Boolean value) {
            addCriterion("game_ishot <>", value, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotGreaterThan(Boolean value) {
            addCriterion("game_ishot >", value, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotGreaterThanOrEqualTo(Boolean value) {
            addCriterion("game_ishot >=", value, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotLessThan(Boolean value) {
            addCriterion("game_ishot <", value, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotLessThanOrEqualTo(Boolean value) {
            addCriterion("game_ishot <=", value, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotIn(List<Boolean> values) {
            addCriterion("game_ishot in", values, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotNotIn(List<Boolean> values) {
            addCriterion("game_ishot not in", values, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotBetween(Boolean value1, Boolean value2) {
            addCriterion("game_ishot between", value1, value2, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameIshotNotBetween(Boolean value1, Boolean value2) {
            addCriterion("game_ishot not between", value1, value2, "gameIshot");
            return (Criteria) this;
        }

        public Criteria andGameBannerIsNull() {
            addCriterion("game_banner is null");
            return (Criteria) this;
        }

        public Criteria andGameBannerIsNotNull() {
            addCriterion("game_banner is not null");
            return (Criteria) this;
        }

        public Criteria andGameBannerEqualTo(String value) {
            addCriterion("game_banner =", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerNotEqualTo(String value) {
            addCriterion("game_banner <>", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerGreaterThan(String value) {
            addCriterion("game_banner >", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerGreaterThanOrEqualTo(String value) {
            addCriterion("game_banner >=", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerLessThan(String value) {
            addCriterion("game_banner <", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerLessThanOrEqualTo(String value) {
            addCriterion("game_banner <=", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerLike(String value) {
            addCriterion("game_banner like", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerNotLike(String value) {
            addCriterion("game_banner not like", value, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerIn(List<String> values) {
            addCriterion("game_banner in", values, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerNotIn(List<String> values) {
            addCriterion("game_banner not in", values, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerBetween(String value1, String value2) {
            addCriterion("game_banner between", value1, value2, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGameBannerNotBetween(String value1, String value2) {
            addCriterion("game_banner not between", value1, value2, "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGamePopulationIsNull() {
            addCriterion("game_population is null");
            return (Criteria) this;
        }

        public Criteria andGamePopulationIsNotNull() {
            addCriterion("game_population is not null");
            return (Criteria) this;
        }

        public Criteria andGamePopulationEqualTo(String value) {
            addCriterion("game_population =", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationNotEqualTo(String value) {
            addCriterion("game_population <>", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationGreaterThan(String value) {
            addCriterion("game_population >", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationGreaterThanOrEqualTo(String value) {
            addCriterion("game_population >=", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationLessThan(String value) {
            addCriterion("game_population <", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationLessThanOrEqualTo(String value) {
            addCriterion("game_population <=", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationLike(String value) {
            addCriterion("game_population like", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationNotLike(String value) {
            addCriterion("game_population not like", value, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationIn(List<String> values) {
            addCriterion("game_population in", values, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationNotIn(List<String> values) {
            addCriterion("game_population not in", values, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationBetween(String value1, String value2) {
            addCriterion("game_population between", value1, value2, "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andGamePopulationNotBetween(String value1, String value2) {
            addCriterion("game_population not between", value1, value2, "gamePopulation");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Long value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Long value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Long value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Long value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Long value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Long value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Long> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Long> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Long value1, Long value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Long value1, Long value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNull() {
            addCriterion("recommend is null");
            return (Criteria) this;
        }

        public Criteria andRecommendIsNotNull() {
            addCriterion("recommend is not null");
            return (Criteria) this;
        }

        public Criteria andRecommendEqualTo(Boolean value) {
            addCriterion("recommend =", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotEqualTo(Boolean value) {
            addCriterion("recommend <>", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThan(Boolean value) {
            addCriterion("recommend >", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendGreaterThanOrEqualTo(Boolean value) {
            addCriterion("recommend >=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThan(Boolean value) {
            addCriterion("recommend <", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendLessThanOrEqualTo(Boolean value) {
            addCriterion("recommend <=", value, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendIn(List<Boolean> values) {
            addCriterion("recommend in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotIn(List<Boolean> values) {
            addCriterion("recommend not in", values, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendBetween(Boolean value1, Boolean value2) {
            addCriterion("recommend between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andRecommendNotBetween(Boolean value1, Boolean value2) {
            addCriterion("recommend not between", value1, value2, "recommend");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(String value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(String value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(String value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(String value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(String value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(String value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLike(String value) {
            addCriterion("platform like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotLike(String value) {
            addCriterion("platform not like", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<String> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<String> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(String value1, String value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(String value1, String value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andGamePicLikeInsensitive(String value) {
            addCriterion("upper(game_pic) like", value.toUpperCase(), "gamePic");
            return (Criteria) this;
        }

        public Criteria andGameIntroLikeInsensitive(String value) {
            addCriterion("upper(game_intro) like", value.toUpperCase(), "gameIntro");
            return (Criteria) this;
        }

        public Criteria andGameUrlLikeInsensitive(String value) {
            addCriterion("upper(game_url) like", value.toUpperCase(), "gameUrl");
            return (Criteria) this;
        }

        public Criteria andGameOrientationLikeInsensitive(String value) {
            addCriterion("upper(game_orientation) like", value.toUpperCase(), "gameOrientation");
            return (Criteria) this;
        }

        public Criteria andGameBannerLikeInsensitive(String value) {
            addCriterion("upper(game_banner) like", value.toUpperCase(), "gameBanner");
            return (Criteria) this;
        }

        public Criteria andGamePopulationLikeInsensitive(String value) {
            addCriterion("upper(game_population) like", value.toUpperCase(), "gamePopulation");
            return (Criteria) this;
        }

        public Criteria andPlatformLikeInsensitive(String value) {
            addCriterion("upper(platform) like", value.toUpperCase(), "platform");
            return (Criteria) this;
        }

        public Criteria andTypeLikeInsensitive(String value) {
            addCriterion("upper(type) like", value.toUpperCase(), "type");
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
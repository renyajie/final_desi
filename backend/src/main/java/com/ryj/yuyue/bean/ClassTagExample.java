package com.ryj.yuyue.bean;

import java.util.ArrayList;
import java.util.List;

public class ClassTagExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClassTagExample() {
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

        public Criteria andRelaxedIsNull() {
            addCriterion("relaxed is null");
            return (Criteria) this;
        }

        public Criteria andRelaxedIsNotNull() {
            addCriterion("relaxed is not null");
            return (Criteria) this;
        }

        public Criteria andRelaxedEqualTo(Integer value) {
            addCriterion("relaxed =", value, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedNotEqualTo(Integer value) {
            addCriterion("relaxed <>", value, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedGreaterThan(Integer value) {
            addCriterion("relaxed >", value, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedGreaterThanOrEqualTo(Integer value) {
            addCriterion("relaxed >=", value, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedLessThan(Integer value) {
            addCriterion("relaxed <", value, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedLessThanOrEqualTo(Integer value) {
            addCriterion("relaxed <=", value, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedIn(List<Integer> values) {
            addCriterion("relaxed in", values, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedNotIn(List<Integer> values) {
            addCriterion("relaxed not in", values, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedBetween(Integer value1, Integer value2) {
            addCriterion("relaxed between", value1, value2, "relaxed");
            return (Criteria) this;
        }

        public Criteria andRelaxedNotBetween(Integer value1, Integer value2) {
            addCriterion("relaxed not between", value1, value2, "relaxed");
            return (Criteria) this;
        }

        public Criteria andIntenseIsNull() {
            addCriterion("intense is null");
            return (Criteria) this;
        }

        public Criteria andIntenseIsNotNull() {
            addCriterion("intense is not null");
            return (Criteria) this;
        }

        public Criteria andIntenseEqualTo(Integer value) {
            addCriterion("intense =", value, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseNotEqualTo(Integer value) {
            addCriterion("intense <>", value, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseGreaterThan(Integer value) {
            addCriterion("intense >", value, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseGreaterThanOrEqualTo(Integer value) {
            addCriterion("intense >=", value, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseLessThan(Integer value) {
            addCriterion("intense <", value, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseLessThanOrEqualTo(Integer value) {
            addCriterion("intense <=", value, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseIn(List<Integer> values) {
            addCriterion("intense in", values, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseNotIn(List<Integer> values) {
            addCriterion("intense not in", values, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseBetween(Integer value1, Integer value2) {
            addCriterion("intense between", value1, value2, "intense");
            return (Criteria) this;
        }

        public Criteria andIntenseNotBetween(Integer value1, Integer value2) {
            addCriterion("intense not between", value1, value2, "intense");
            return (Criteria) this;
        }

        public Criteria andCommonIsNull() {
            addCriterion("common is null");
            return (Criteria) this;
        }

        public Criteria andCommonIsNotNull() {
            addCriterion("common is not null");
            return (Criteria) this;
        }

        public Criteria andCommonEqualTo(Integer value) {
            addCriterion("common =", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotEqualTo(Integer value) {
            addCriterion("common <>", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonGreaterThan(Integer value) {
            addCriterion("common >", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonGreaterThanOrEqualTo(Integer value) {
            addCriterion("common >=", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonLessThan(Integer value) {
            addCriterion("common <", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonLessThanOrEqualTo(Integer value) {
            addCriterion("common <=", value, "common");
            return (Criteria) this;
        }

        public Criteria andCommonIn(List<Integer> values) {
            addCriterion("common in", values, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotIn(List<Integer> values) {
            addCriterion("common not in", values, "common");
            return (Criteria) this;
        }

        public Criteria andCommonBetween(Integer value1, Integer value2) {
            addCriterion("common between", value1, value2, "common");
            return (Criteria) this;
        }

        public Criteria andCommonNotBetween(Integer value1, Integer value2) {
            addCriterion("common not between", value1, value2, "common");
            return (Criteria) this;
        }

        public Criteria andRecoveryIsNull() {
            addCriterion("recovery is null");
            return (Criteria) this;
        }

        public Criteria andRecoveryIsNotNull() {
            addCriterion("recovery is not null");
            return (Criteria) this;
        }

        public Criteria andRecoveryEqualTo(Integer value) {
            addCriterion("recovery =", value, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryNotEqualTo(Integer value) {
            addCriterion("recovery <>", value, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryGreaterThan(Integer value) {
            addCriterion("recovery >", value, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryGreaterThanOrEqualTo(Integer value) {
            addCriterion("recovery >=", value, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryLessThan(Integer value) {
            addCriterion("recovery <", value, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryLessThanOrEqualTo(Integer value) {
            addCriterion("recovery <=", value, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryIn(List<Integer> values) {
            addCriterion("recovery in", values, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryNotIn(List<Integer> values) {
            addCriterion("recovery not in", values, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryBetween(Integer value1, Integer value2) {
            addCriterion("recovery between", value1, value2, "recovery");
            return (Criteria) this;
        }

        public Criteria andRecoveryNotBetween(Integer value1, Integer value2) {
            addCriterion("recovery not between", value1, value2, "recovery");
            return (Criteria) this;
        }

        public Criteria andEnhanceIsNull() {
            addCriterion("enhance is null");
            return (Criteria) this;
        }

        public Criteria andEnhanceIsNotNull() {
            addCriterion("enhance is not null");
            return (Criteria) this;
        }

        public Criteria andEnhanceEqualTo(Integer value) {
            addCriterion("enhance =", value, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceNotEqualTo(Integer value) {
            addCriterion("enhance <>", value, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceGreaterThan(Integer value) {
            addCriterion("enhance >", value, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("enhance >=", value, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceLessThan(Integer value) {
            addCriterion("enhance <", value, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceLessThanOrEqualTo(Integer value) {
            addCriterion("enhance <=", value, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceIn(List<Integer> values) {
            addCriterion("enhance in", values, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceNotIn(List<Integer> values) {
            addCriterion("enhance not in", values, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceBetween(Integer value1, Integer value2) {
            addCriterion("enhance between", value1, value2, "enhance");
            return (Criteria) this;
        }

        public Criteria andEnhanceNotBetween(Integer value1, Integer value2) {
            addCriterion("enhance not between", value1, value2, "enhance");
            return (Criteria) this;
        }

        public Criteria andNurseIsNull() {
            addCriterion("nurse is null");
            return (Criteria) this;
        }

        public Criteria andNurseIsNotNull() {
            addCriterion("nurse is not null");
            return (Criteria) this;
        }

        public Criteria andNurseEqualTo(Integer value) {
            addCriterion("nurse =", value, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseNotEqualTo(Integer value) {
            addCriterion("nurse <>", value, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseGreaterThan(Integer value) {
            addCriterion("nurse >", value, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseGreaterThanOrEqualTo(Integer value) {
            addCriterion("nurse >=", value, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseLessThan(Integer value) {
            addCriterion("nurse <", value, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseLessThanOrEqualTo(Integer value) {
            addCriterion("nurse <=", value, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseIn(List<Integer> values) {
            addCriterion("nurse in", values, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseNotIn(List<Integer> values) {
            addCriterion("nurse not in", values, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseBetween(Integer value1, Integer value2) {
            addCriterion("nurse between", value1, value2, "nurse");
            return (Criteria) this;
        }

        public Criteria andNurseNotBetween(Integer value1, Integer value2) {
            addCriterion("nurse not between", value1, value2, "nurse");
            return (Criteria) this;
        }

        public Criteria andConsumeIsNull() {
            addCriterion("consume is null");
            return (Criteria) this;
        }

        public Criteria andConsumeIsNotNull() {
            addCriterion("consume is not null");
            return (Criteria) this;
        }

        public Criteria andConsumeEqualTo(Integer value) {
            addCriterion("consume =", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotEqualTo(Integer value) {
            addCriterion("consume <>", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeGreaterThan(Integer value) {
            addCriterion("consume >", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeGreaterThanOrEqualTo(Integer value) {
            addCriterion("consume >=", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLessThan(Integer value) {
            addCriterion("consume <", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeLessThanOrEqualTo(Integer value) {
            addCriterion("consume <=", value, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeIn(List<Integer> values) {
            addCriterion("consume in", values, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotIn(List<Integer> values) {
            addCriterion("consume not in", values, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeBetween(Integer value1, Integer value2) {
            addCriterion("consume between", value1, value2, "consume");
            return (Criteria) this;
        }

        public Criteria andConsumeNotBetween(Integer value1, Integer value2) {
            addCriterion("consume not between", value1, value2, "consume");
            return (Criteria) this;
        }

        public Criteria andClassKIdIsNull() {
            addCriterion("class_k_id is null");
            return (Criteria) this;
        }

        public Criteria andClassKIdIsNotNull() {
            addCriterion("class_k_id is not null");
            return (Criteria) this;
        }

        public Criteria andClassKIdEqualTo(Integer value) {
            addCriterion("class_k_id =", value, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdNotEqualTo(Integer value) {
            addCriterion("class_k_id <>", value, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdGreaterThan(Integer value) {
            addCriterion("class_k_id >", value, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_k_id >=", value, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdLessThan(Integer value) {
            addCriterion("class_k_id <", value, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdLessThanOrEqualTo(Integer value) {
            addCriterion("class_k_id <=", value, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdIn(List<Integer> values) {
            addCriterion("class_k_id in", values, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdNotIn(List<Integer> values) {
            addCriterion("class_k_id not in", values, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdBetween(Integer value1, Integer value2) {
            addCriterion("class_k_id between", value1, value2, "classKId");
            return (Criteria) this;
        }

        public Criteria andClassKIdNotBetween(Integer value1, Integer value2) {
            addCriterion("class_k_id not between", value1, value2, "classKId");
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
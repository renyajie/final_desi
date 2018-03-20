package com.ryj.yuyue.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardOrderExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CardOrderExample() {
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

        public Criteria andUIdIsNull() {
            addCriterion("u_id is null");
            return (Criteria) this;
        }

        public Criteria andUIdIsNotNull() {
            addCriterion("u_id is not null");
            return (Criteria) this;
        }

        public Criteria andUIdEqualTo(Integer value) {
            addCriterion("u_id =", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotEqualTo(Integer value) {
            addCriterion("u_id <>", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThan(Integer value) {
            addCriterion("u_id >", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("u_id >=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThan(Integer value) {
            addCriterion("u_id <", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdLessThanOrEqualTo(Integer value) {
            addCriterion("u_id <=", value, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdIn(List<Integer> values) {
            addCriterion("u_id in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotIn(List<Integer> values) {
            addCriterion("u_id not in", values, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdBetween(Integer value1, Integer value2) {
            addCriterion("u_id between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andUIdNotBetween(Integer value1, Integer value2) {
            addCriterion("u_id not between", value1, value2, "uId");
            return (Criteria) this;
        }

        public Criteria andCardKIdIsNull() {
            addCriterion("card_k_id is null");
            return (Criteria) this;
        }

        public Criteria andCardKIdIsNotNull() {
            addCriterion("card_k_id is not null");
            return (Criteria) this;
        }

        public Criteria andCardKIdEqualTo(Integer value) {
            addCriterion("card_k_id =", value, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdNotEqualTo(Integer value) {
            addCriterion("card_k_id <>", value, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdGreaterThan(Integer value) {
            addCriterion("card_k_id >", value, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("card_k_id >=", value, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdLessThan(Integer value) {
            addCriterion("card_k_id <", value, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdLessThanOrEqualTo(Integer value) {
            addCriterion("card_k_id <=", value, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdIn(List<Integer> values) {
            addCriterion("card_k_id in", values, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdNotIn(List<Integer> values) {
            addCriterion("card_k_id not in", values, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdBetween(Integer value1, Integer value2) {
            addCriterion("card_k_id between", value1, value2, "cardKId");
            return (Criteria) this;
        }

        public Criteria andCardKIdNotBetween(Integer value1, Integer value2) {
            addCriterion("card_k_id not between", value1, value2, "cardKId");
            return (Criteria) this;
        }

        public Criteria andOrdTimeIsNull() {
            addCriterion("ord_time is null");
            return (Criteria) this;
        }

        public Criteria andOrdTimeIsNotNull() {
            addCriterion("ord_time is not null");
            return (Criteria) this;
        }

        public Criteria andOrdTimeEqualTo(Date value) {
            addCriterion("ord_time =", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeNotEqualTo(Date value) {
            addCriterion("ord_time <>", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeGreaterThan(Date value) {
            addCriterion("ord_time >", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ord_time >=", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeLessThan(Date value) {
            addCriterion("ord_time <", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeLessThanOrEqualTo(Date value) {
            addCriterion("ord_time <=", value, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeIn(List<Date> values) {
            addCriterion("ord_time in", values, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeNotIn(List<Date> values) {
            addCriterion("ord_time not in", values, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeBetween(Date value1, Date value2) {
            addCriterion("ord_time between", value1, value2, "ordTime");
            return (Criteria) this;
        }

        public Criteria andOrdTimeNotBetween(Date value1, Date value2) {
            addCriterion("ord_time not between", value1, value2, "ordTime");
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
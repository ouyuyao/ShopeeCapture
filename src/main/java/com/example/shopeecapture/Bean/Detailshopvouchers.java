package com.example.shopeecapture.Bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

public class Detailshopvouchers implements Serializable {
    private static final long serialVersionUID = -53191028940238263L;
    private Integer id;
    /**
     * 优惠券抵扣金额
     * 最小优惠券抵扣金额
     * 最大优惠券抵扣金额
     */
    private Double discountValue;
    @JsonIgnore
    private Double min_discountValue;
    @JsonIgnore
    private Double max_discountValue;
    /**
     * 店铺ID
     */
    private String shopid;
    /**
     * 优惠券生效金额
     * 最小优惠券生效金额
     * 最大优惠券生效金额
     */
    private Double minSpend;
    @JsonIgnore
    private Double min_minSpend;
    @JsonIgnore
    private Double max_minSpend;
    /**
     * 爬取数据事件ID
     */
    private String eventid;
    /**
     * 插入日期yyyyMMdd
     * 最小插入日期yyyyMMdd
     * 最大插入日期yyyyMMdd
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date valueDate;
    @JsonIgnore
    private Date min_valueDate;
    @JsonIgnore
    private Date max_valueDate;
    /**
     * 插入时间yyyy-MM-dd HH:mm:ss.SSS
     * 最小插入时间yyyy-MM-dd HH:mm:ss.SSS
     * 最大插入时间yyyy-MM-dd HH:mm:ss.SSS
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date insertTime;
    @JsonIgnore
    private Date min_insertTime;
    @JsonIgnore
    private Date max_insertTime;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Double discountValue) {
        this.discountValue = discountValue;
    }

    public Double getMin_discountValue() {
        return min_discountValue;
    }

    public void setMin_discountValue(Double min_discountValue) {
        this.min_discountValue = min_discountValue;
    }

    public Double getMax_discountValue() {
        return max_discountValue;
    }

    public void setMax_discountValue(Double max_discountValue) {
        this.max_discountValue = max_discountValue;
    }

    public String getShopid() {
        return shopid;
    }

    public void setShopid(String shopid) {
        this.shopid = shopid;
    }

    public Double getMinSpend() {
        return minSpend;
    }

    public void setMinSpend(Double minSpend) {
        this.minSpend = minSpend;
    }

    public Double getMin_minSpend() {
        return min_minSpend;
    }

    public void setMin_minSpend(Double min_minSpend) {
        this.min_minSpend = min_minSpend;
    }

    public Double getMax_minSpend() {
        return max_minSpend;
    }

    public void setMax_minSpend(Double max_minSpend) {
        this.max_minSpend = max_minSpend;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public Date getValueDate() {
        return valueDate;
    }

    public void setValueDate(Date valueDate) {
        this.valueDate = valueDate;
    }

    public Date getMin_valueDate() {
        return min_valueDate;
    }

    public void setMin_valueDate(Date min_valueDate) {
        this.min_valueDate = min_valueDate;
    }

    public Date getMax_valueDate() {
        return max_valueDate;
    }

    public void setMax_valueDate(Date max_valueDate) {
        this.max_valueDate = max_valueDate;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getMin_insertTime() {
        return min_insertTime;
    }

    public void setMin_insertTime(Date min_insertTime) {
        this.min_insertTime = min_insertTime;
    }

    public Date getMax_insertTime() {
        return max_insertTime;
    }

    public void setMax_insertTime(Date max_insertTime) {
        this.max_insertTime = max_insertTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"id\":").append(id);
        sb.append("\"discountValue\":").append(discountValue);
        sb.append("\"min_discountValue\":").append(min_discountValue);
        sb.append("\"max_discountValue\":").append(max_discountValue);
        sb.append("\"shopid\":").append(shopid);
        sb.append("\"minSpend\":").append(minSpend);
        sb.append("\"min_minSpend\":").append(min_minSpend);
        sb.append("\"max_minSpend\":").append(max_minSpend);
        sb.append("\"eventid\":").append(eventid);
        sb.append("\"valueDate\":").append(valueDate);
        sb.append("\"min_valueDate\":").append(min_valueDate);
        sb.append("\"max_valueDate\":").append(max_valueDate);
        sb.append("\"insertTime\":").append(insertTime);
        sb.append("\"min_insertTime\":").append(min_insertTime);
        sb.append("\"max_insertTime\":").append(max_insertTime);
        sb.append('}');
        return sb.toString();
    }
}

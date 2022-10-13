package com.example.shopeecapture.Bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

public class Delivers implements Serializable {
    private static final long serialVersionUID = 212402421192047065L;
    private Integer id;
    /**
     * 爬取数据事件ID
     */
    private String eventid;
    /**
     * 商品ID
     */
    private String itemid;
    /**
     * 所在商铺ID
     */
    private String shopid;
    /**
     * 免运费金额
     * 最小免运费金额
     * 最大免运费金额
     */
    private Double minSpend;
    @JsonIgnore
    private Double min_minSpend;
    @JsonIgnore
    private Double max_minSpend;
    /**
     * 最低运费
     * 最小最低运费
     * 最大最低运费
     */
    private Double minPrice;
    @JsonIgnore
    private Double min_minPrice;
    @JsonIgnore
    private Double max_minPrice;
    /**
     * 最高运费
     * 最小最高运费
     * 最大最高运费
     */
    private Double maxPrice;
    @JsonIgnore
    private Double min_maxPrice;
    @JsonIgnore
    private Double max_maxPrice;
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
    /**
     * 记录支持的快递种类个数
     * 最小记录支持的快递种类个数
     * 最大记录支持的快递种类个数
     */
    private Double medianPrice;
    @JsonIgnore
    private Double min_medianPrice;
    @JsonIgnore
    private Double max_medianPrice;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEventid() {
        return eventid;
    }

    public void setEventid(String eventid) {
        this.eventid = eventid;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
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

    public Double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public Double getMin_minPrice() {
        return min_minPrice;
    }

    public void setMin_minPrice(Double min_minPrice) {
        this.min_minPrice = min_minPrice;
    }

    public Double getMax_minPrice() {
        return max_minPrice;
    }

    public void setMax_minPrice(Double max_minPrice) {
        this.max_minPrice = max_minPrice;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public Double getMin_maxPrice() {
        return min_maxPrice;
    }

    public void setMin_maxPrice(Double min_maxPrice) {
        this.min_maxPrice = min_maxPrice;
    }

    public Double getMax_maxPrice() {
        return max_maxPrice;
    }

    public void setMax_maxPrice(Double max_maxPrice) {
        this.max_maxPrice = max_maxPrice;
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

    public Double getMedianPrice() {
        return medianPrice;
    }

    public void setMedianPrice(Double medianPrice) {
        this.medianPrice = medianPrice;
    }

    public Double getMin_medianPrice() {
        return min_medianPrice;
    }

    public void setMin_medianPrice(Double min_medianPrice) {
        this.min_medianPrice = min_medianPrice;
    }

    public Double getMax_medianPrice() {
        return max_medianPrice;
    }

    public void setMax_medianPrice(Double max_medianPrice) {
        this.max_medianPrice = max_medianPrice;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append('{');
        sb.append("\"id\":").append(id);
        sb.append("\"eventid\":").append(eventid);
        sb.append("\"itemid\":").append(itemid);
        sb.append("\"shopid\":").append(shopid);
        sb.append("\"minSpend\":").append(minSpend);
        sb.append("\"min_minSpend\":").append(min_minSpend);
        sb.append("\"max_minSpend\":").append(max_minSpend);
        sb.append("\"minPrice\":").append(minPrice);
        sb.append("\"min_minPrice\":").append(min_minPrice);
        sb.append("\"max_minPrice\":").append(max_minPrice);
        sb.append("\"maxPrice\":").append(maxPrice);
        sb.append("\"min_maxPrice\":").append(min_maxPrice);
        sb.append("\"max_maxPrice\":").append(max_maxPrice);
        sb.append("\"valueDate\":").append(valueDate);
        sb.append("\"min_valueDate\":").append(min_valueDate);
        sb.append("\"max_valueDate\":").append(max_valueDate);
        sb.append("\"insertTime\":").append(insertTime);
        sb.append("\"min_insertTime\":").append(min_insertTime);
        sb.append("\"max_insertTime\":").append(max_insertTime);
        sb.append("\"medianPrice\":").append(medianPrice);
        sb.append("\"min_medianPrice\":").append(min_medianPrice);
        sb.append("\"max_medianPrice\":").append(max_medianPrice);
        sb.append('}');
        return sb.toString();
    }
}

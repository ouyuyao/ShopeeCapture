package com.example.shopeecapture.Bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

public class Detailmodels implements Serializable {
    private static final long serialVersionUID = 638064955743523433L;
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
     * 图片网址
     */
    private String image;
    /**
     * 子选项名
     */
    private String name;
    /**
     * 子选项现价
     * 最小子选项现价
     * 最大子选项现价
     */
    private Double price;
    @JsonIgnore
    private Double min_price;
    @JsonIgnore
    private Double max_price;
    /**
     * 子选项原价
     * 最小子选项原价
     * 最大子选项原价
     */
    private Double priceBeforeDiscount;
    @JsonIgnore
    private Double min_priceBeforeDiscount;
    @JsonIgnore
    private Double max_priceBeforeDiscount;
    /**
     * 库存
     * 最小库存
     * 最大库存
     */
    private Integer stock;
    @JsonIgnore
    private Integer min_stock;
    @JsonIgnore
    private Integer max_stock;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public Double getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(Double priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public Double getMin_priceBeforeDiscount() {
        return min_priceBeforeDiscount;
    }

    public void setMin_priceBeforeDiscount(Double min_priceBeforeDiscount) {
        this.min_priceBeforeDiscount = min_priceBeforeDiscount;
    }

    public Double getMax_priceBeforeDiscount() {
        return max_priceBeforeDiscount;
    }

    public void setMax_priceBeforeDiscount(Double max_priceBeforeDiscount) {
        this.max_priceBeforeDiscount = max_priceBeforeDiscount;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getMin_stock() {
        return min_stock;
    }

    public void setMin_stock(Integer min_stock) {
        this.min_stock = min_stock;
    }

    public Integer getMax_stock() {
        return max_stock;
    }

    public void setMax_stock(Integer max_stock) {
        this.max_stock = max_stock;
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
        sb.append("\"eventid\":").append(eventid);
        sb.append("\"itemid\":").append(itemid);
        sb.append("\"shopid\":").append(shopid);
        sb.append(",\"image\":\"").append(image).append('\"');
        sb.append(",\"name\":\"").append(name).append('\"');
        sb.append("\"price\":").append(price);
        sb.append("\"min_price\":").append(min_price);
        sb.append("\"max_price\":").append(max_price);
        sb.append("\"priceBeforeDiscount\":").append(priceBeforeDiscount);
        sb.append("\"min_priceBeforeDiscount\":").append(min_priceBeforeDiscount);
        sb.append("\"max_priceBeforeDiscount\":").append(max_priceBeforeDiscount);
        sb.append("\"stock\":").append(stock);
        sb.append("\"min_stock\":").append(min_stock);
        sb.append("\"max_stock\":").append(max_stock);
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

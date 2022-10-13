package com.example.shopeecapture.Bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

public class Products implements Serializable {
    private static final long serialVersionUID = -58819166931394454L;
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
     * 商品名
     */
    private String name;
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
     * 顾客like个数
     * 最小顾客like个数
     * 最大顾客like个数
     */
    private Integer likeCount;
    @JsonIgnore
    private Integer min_likeCount;
    @JsonIgnore
    private Integer max_likeCount;
    /**
     * 当月销量
     * 最小当月销量
     * 最大当月销量
     */
    private Integer sold;
    @JsonIgnore
    private Integer min_sold;
    @JsonIgnore
    private Integer max_sold;
    /**
     * 历史销量
     * 最小历史销量
     * 最大历史销量
     */
    private Integer historicalSold;
    @JsonIgnore
    private Integer min_historicalSold;
    @JsonIgnore
    private Integer max_historicalSold;
    /**
     * 币种
     */
    private String currency;
    /**
     * 现价
     * 最小现价
     * 最大现价
     */
    private Double price;
    @JsonIgnore
    private Double min_price;
    @JsonIgnore
    private Double max_price;
    /**
     * 最低现价
     * 最小最低现价
     * 最大最低现价
     */
    private Double priceMin;
    @JsonIgnore
    private Double min_priceMin;
    @JsonIgnore
    private Double max_priceMin;
    /**
     * 最高现价
     * 最小最高现价
     * 最大最高现价
     */
    private Double priceMax;
    @JsonIgnore
    private Double min_priceMax;
    @JsonIgnore
    private Double max_priceMax;
    /**
     * 折前价格
     * 最小折前价格
     * 最大折前价格
     */
    private Double priceBeforeDiscount;
    @JsonIgnore
    private Double min_priceBeforeDiscount;
    @JsonIgnore
    private Double max_priceBeforeDiscount;
    /**
     * 折前最低价
     * 最小折前最低价
     * 最大折前最低价
     */
    private Double priceMinBeforeDiscount;
    @JsonIgnore
    private Double min_priceMinBeforeDiscount;
    @JsonIgnore
    private Double max_priceMinBeforeDiscount;
    /**
     * 折前最高价
     * 最小折前最高价
     * 最大折前最高价
     */
    private Double priceMaxBeforeDiscount;
    @JsonIgnore
    private Double min_priceMaxBeforeDiscount;
    @JsonIgnore
    private Double max_priceMaxBeforeDiscount;
    /**
     * 折扣百分比
     */
    private String showDiscount;
    /**
     * 子选项名字列表
     */
    private String tierVariations;
    /**
     * shopee官方验证
     */
    private String shopeeVerified;
    /**
     * 是否官营店
     */
    private String isOfficialShop;
    /**
     * 店铺标记地区
     */
    private String shopLocation;
    /**
     * 搜索关键字
     */
    private String searchstr;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public Integer getMin_likeCount() {
        return min_likeCount;
    }

    public void setMin_likeCount(Integer min_likeCount) {
        this.min_likeCount = min_likeCount;
    }

    public Integer getMax_likeCount() {
        return max_likeCount;
    }

    public void setMax_likeCount(Integer max_likeCount) {
        this.max_likeCount = max_likeCount;
    }

    public Integer getSold() {
        return sold;
    }

    public void setSold(Integer sold) {
        this.sold = sold;
    }

    public Integer getMin_sold() {
        return min_sold;
    }

    public void setMin_sold(Integer min_sold) {
        this.min_sold = min_sold;
    }

    public Integer getMax_sold() {
        return max_sold;
    }

    public void setMax_sold(Integer max_sold) {
        this.max_sold = max_sold;
    }

    public Integer getHistoricalSold() {
        return historicalSold;
    }

    public void setHistoricalSold(Integer historicalSold) {
        this.historicalSold = historicalSold;
    }

    public Integer getMin_historicalSold() {
        return min_historicalSold;
    }

    public void setMin_historicalSold(Integer min_historicalSold) {
        this.min_historicalSold = min_historicalSold;
    }

    public Integer getMax_historicalSold() {
        return max_historicalSold;
    }

    public void setMax_historicalSold(Integer max_historicalSold) {
        this.max_historicalSold = max_historicalSold;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
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

    public Double getPriceMin() {
        return priceMin;
    }

    public void setPriceMin(Double priceMin) {
        this.priceMin = priceMin;
    }

    public Double getMin_priceMin() {
        return min_priceMin;
    }

    public void setMin_priceMin(Double min_priceMin) {
        this.min_priceMin = min_priceMin;
    }

    public Double getMax_priceMin() {
        return max_priceMin;
    }

    public void setMax_priceMin(Double max_priceMin) {
        this.max_priceMin = max_priceMin;
    }

    public Double getPriceMax() {
        return priceMax;
    }

    public void setPriceMax(Double priceMax) {
        this.priceMax = priceMax;
    }

    public Double getMin_priceMax() {
        return min_priceMax;
    }

    public void setMin_priceMax(Double min_priceMax) {
        this.min_priceMax = min_priceMax;
    }

    public Double getMax_priceMax() {
        return max_priceMax;
    }

    public void setMax_priceMax(Double max_priceMax) {
        this.max_priceMax = max_priceMax;
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

    public Double getPriceMinBeforeDiscount() {
        return priceMinBeforeDiscount;
    }

    public void setPriceMinBeforeDiscount(Double priceMinBeforeDiscount) {
        this.priceMinBeforeDiscount = priceMinBeforeDiscount;
    }

    public Double getMin_priceMinBeforeDiscount() {
        return min_priceMinBeforeDiscount;
    }

    public void setMin_priceMinBeforeDiscount(Double min_priceMinBeforeDiscount) {
        this.min_priceMinBeforeDiscount = min_priceMinBeforeDiscount;
    }

    public Double getMax_priceMinBeforeDiscount() {
        return max_priceMinBeforeDiscount;
    }

    public void setMax_priceMinBeforeDiscount(Double max_priceMinBeforeDiscount) {
        this.max_priceMinBeforeDiscount = max_priceMinBeforeDiscount;
    }

    public Double getPriceMaxBeforeDiscount() {
        return priceMaxBeforeDiscount;
    }

    public void setPriceMaxBeforeDiscount(Double priceMaxBeforeDiscount) {
        this.priceMaxBeforeDiscount = priceMaxBeforeDiscount;
    }

    public Double getMin_priceMaxBeforeDiscount() {
        return min_priceMaxBeforeDiscount;
    }

    public void setMin_priceMaxBeforeDiscount(Double min_priceMaxBeforeDiscount) {
        this.min_priceMaxBeforeDiscount = min_priceMaxBeforeDiscount;
    }

    public Double getMax_priceMaxBeforeDiscount() {
        return max_priceMaxBeforeDiscount;
    }

    public void setMax_priceMaxBeforeDiscount(Double max_priceMaxBeforeDiscount) {
        this.max_priceMaxBeforeDiscount = max_priceMaxBeforeDiscount;
    }

    public String getShowDiscount() {
        return showDiscount;
    }

    public void setShowDiscount(String showDiscount) {
        this.showDiscount = showDiscount;
    }

    public String getTierVariations() {
        return tierVariations;
    }

    public void setTierVariations(String tierVariations) {
        this.tierVariations = tierVariations;
    }

    public String getShopeeVerified() {
        return shopeeVerified;
    }

    public void setShopeeVerified(String shopeeVerified) {
        this.shopeeVerified = shopeeVerified;
    }

    public String getIsOfficialShop() {
        return isOfficialShop;
    }

    public void setIsOfficialShop(String isOfficialShop) {
        this.isOfficialShop = isOfficialShop;
    }

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getSearchstr() {
        return searchstr;
    }

    public void setSearchstr(String searchstr) {
        this.searchstr = searchstr;
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
        sb.append(",\"name\":\"").append(name).append('\"');
        sb.append("\"stock\":").append(stock);
        sb.append("\"min_stock\":").append(min_stock);
        sb.append("\"max_stock\":").append(max_stock);
        sb.append("\"likeCount\":").append(likeCount);
        sb.append("\"min_likeCount\":").append(min_likeCount);
        sb.append("\"max_likeCount\":").append(max_likeCount);
        sb.append("\"sold\":").append(sold);
        sb.append("\"min_sold\":").append(min_sold);
        sb.append("\"max_sold\":").append(max_sold);
        sb.append("\"historicalSold\":").append(historicalSold);
        sb.append("\"min_historicalSold\":").append(min_historicalSold);
        sb.append("\"max_historicalSold\":").append(max_historicalSold);
        sb.append(",\"currency\":\"").append(currency).append('\"');
        sb.append("\"price\":").append(price);
        sb.append("\"min_price\":").append(min_price);
        sb.append("\"max_price\":").append(max_price);
        sb.append("\"priceMin\":").append(priceMin);
        sb.append("\"min_priceMin\":").append(min_priceMin);
        sb.append("\"max_priceMin\":").append(max_priceMin);
        sb.append("\"priceMax\":").append(priceMax);
        sb.append("\"min_priceMax\":").append(min_priceMax);
        sb.append("\"max_priceMax\":").append(max_priceMax);
        sb.append("\"priceBeforeDiscount\":").append(priceBeforeDiscount);
        sb.append("\"min_priceBeforeDiscount\":").append(min_priceBeforeDiscount);
        sb.append("\"max_priceBeforeDiscount\":").append(max_priceBeforeDiscount);
        sb.append("\"priceMinBeforeDiscount\":").append(priceMinBeforeDiscount);
        sb.append("\"min_priceMinBeforeDiscount\":").append(min_priceMinBeforeDiscount);
        sb.append("\"max_priceMinBeforeDiscount\":").append(max_priceMinBeforeDiscount);
        sb.append("\"priceMaxBeforeDiscount\":").append(priceMaxBeforeDiscount);
        sb.append("\"min_priceMaxBeforeDiscount\":").append(min_priceMaxBeforeDiscount);
        sb.append("\"max_priceMaxBeforeDiscount\":").append(max_priceMaxBeforeDiscount);
        sb.append(",\"showDiscount\":\"").append(showDiscount).append('\"');
        sb.append(",\"tierVariations\":\"").append(tierVariations).append('\"');
        sb.append(",\"shopeeVerified\":\"").append(shopeeVerified).append('\"');
        sb.append(",\"isOfficialShop\":\"").append(isOfficialShop).append('\"');
        sb.append(",\"shopLocation\":\"").append(shopLocation).append('\"');
        sb.append(",\"searchstr\":\"").append(searchstr).append('\"');
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

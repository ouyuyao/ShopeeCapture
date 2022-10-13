package com.example.shopeecapture.Bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;

public class Shopinfo implements Serializable {
    private static final long serialVersionUID = -98884373303254311L;
    private Integer id;
    /**
     * 爬取数据事件ID
     */
    private String eventid;
    /**
     * 店铺ID
     */
    private String shopid;
    /**
     * 店铺名
     */
    private String name;
    /**
     * 店铺所在地区
     */
    private String shopLocation;
    /**
     * 店主最后登录时间
     */
    private String lastActiveTime;
    /**
     * 商品数量
     * 最小商品数量
     * 最大商品数量
     */
    private Integer itemCount;
    @JsonIgnore
    private Integer min_itemCount;
    @JsonIgnore
    private Integer max_itemCount;
    /**
     * 店铺评级
     * 最小店铺评级
     * 最大店铺评级
     */
    private Double ratingStar;
    @JsonIgnore
    private Double min_ratingStar;
    @JsonIgnore
    private Double max_ratingStar;
    /**
     * 店铺关注数
     * 最小店铺关注数
     * 最大店铺关注数
     */
    private Integer followerCount;
    @JsonIgnore
    private Integer min_followerCount;
    @JsonIgnore
    private Integer max_followerCount;
    /**
     * 回应概率
     */
    private String responseRate;
    /**
     * 平均回应时间
     */
    private String responseTime;
    /**
     * 差评比例
     * 最小差评比例
     * 最大差评比例
     */
    private Integer ratingBad;
    @JsonIgnore
    private Integer min_ratingBad;
    @JsonIgnore
    private Integer max_ratingBad;
    /**
     * 好评比例
     * 最小好评比例
     * 最大好评比例
     */
    private Integer ratingGood;
    @JsonIgnore
    private Integer min_ratingGood;
    @JsonIgnore
    private Integer max_ratingGood;
    /**
     * 中评比例
     * 最小中评比例
     * 最大中评比例
     */
    private Integer ratingNormal;
    @JsonIgnore
    private Integer min_ratingNormal;
    @JsonIgnore
    private Integer max_ratingNormal;
    /**
     * 是否shopee验证
     */
    private String isShopeeVerified;
    /**
     * 是否推荐店铺
     */
    private String isPreferredPlusSeller;
    /**
     * 是否官方直营店铺
     */
    private String isOfficialShop;
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

    public String getShopLocation() {
        return shopLocation;
    }

    public void setShopLocation(String shopLocation) {
        this.shopLocation = shopLocation;
    }

    public String getLastActiveTime() {
        return lastActiveTime;
    }

    public void setLastActiveTime(String lastActiveTime) {
        this.lastActiveTime = lastActiveTime;
    }

    public Integer getItemCount() {
        return itemCount;
    }

    public void setItemCount(Integer itemCount) {
        this.itemCount = itemCount;
    }

    public Integer getMin_itemCount() {
        return min_itemCount;
    }

    public void setMin_itemCount(Integer min_itemCount) {
        this.min_itemCount = min_itemCount;
    }

    public Integer getMax_itemCount() {
        return max_itemCount;
    }

    public void setMax_itemCount(Integer max_itemCount) {
        this.max_itemCount = max_itemCount;
    }

    public Double getRatingStar() {
        return ratingStar;
    }

    public void setRatingStar(Double ratingStar) {
        this.ratingStar = ratingStar;
    }

    public Double getMin_ratingStar() {
        return min_ratingStar;
    }

    public void setMin_ratingStar(Double min_ratingStar) {
        this.min_ratingStar = min_ratingStar;
    }

    public Double getMax_ratingStar() {
        return max_ratingStar;
    }

    public void setMax_ratingStar(Double max_ratingStar) {
        this.max_ratingStar = max_ratingStar;
    }

    public Integer getFollowerCount() {
        return followerCount;
    }

    public void setFollowerCount(Integer followerCount) {
        this.followerCount = followerCount;
    }

    public Integer getMin_followerCount() {
        return min_followerCount;
    }

    public void setMin_followerCount(Integer min_followerCount) {
        this.min_followerCount = min_followerCount;
    }

    public Integer getMax_followerCount() {
        return max_followerCount;
    }

    public void setMax_followerCount(Integer max_followerCount) {
        this.max_followerCount = max_followerCount;
    }

    public String getResponseRate() {
        return responseRate;
    }

    public void setResponseRate(String responseRate) {
        this.responseRate = responseRate;
    }

    public String getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(String responseTime) {
        this.responseTime = responseTime;
    }

    public Integer getRatingBad() {
        return ratingBad;
    }

    public void setRatingBad(Integer ratingBad) {
        this.ratingBad = ratingBad;
    }

    public Integer getMin_ratingBad() {
        return min_ratingBad;
    }

    public void setMin_ratingBad(Integer min_ratingBad) {
        this.min_ratingBad = min_ratingBad;
    }

    public Integer getMax_ratingBad() {
        return max_ratingBad;
    }

    public void setMax_ratingBad(Integer max_ratingBad) {
        this.max_ratingBad = max_ratingBad;
    }

    public Integer getRatingGood() {
        return ratingGood;
    }

    public void setRatingGood(Integer ratingGood) {
        this.ratingGood = ratingGood;
    }

    public Integer getMin_ratingGood() {
        return min_ratingGood;
    }

    public void setMin_ratingGood(Integer min_ratingGood) {
        this.min_ratingGood = min_ratingGood;
    }

    public Integer getMax_ratingGood() {
        return max_ratingGood;
    }

    public void setMax_ratingGood(Integer max_ratingGood) {
        this.max_ratingGood = max_ratingGood;
    }

    public Integer getRatingNormal() {
        return ratingNormal;
    }

    public void setRatingNormal(Integer ratingNormal) {
        this.ratingNormal = ratingNormal;
    }

    public Integer getMin_ratingNormal() {
        return min_ratingNormal;
    }

    public void setMin_ratingNormal(Integer min_ratingNormal) {
        this.min_ratingNormal = min_ratingNormal;
    }

    public Integer getMax_ratingNormal() {
        return max_ratingNormal;
    }

    public void setMax_ratingNormal(Integer max_ratingNormal) {
        this.max_ratingNormal = max_ratingNormal;
    }

    public String getIsShopeeVerified() {
        return isShopeeVerified;
    }

    public void setIsShopeeVerified(String isShopeeVerified) {
        this.isShopeeVerified = isShopeeVerified;
    }

    public String getIsPreferredPlusSeller() {
        return isPreferredPlusSeller;
    }

    public void setIsPreferredPlusSeller(String isPreferredPlusSeller) {
        this.isPreferredPlusSeller = isPreferredPlusSeller;
    }

    public String getIsOfficialShop() {
        return isOfficialShop;
    }

    public void setIsOfficialShop(String isOfficialShop) {
        this.isOfficialShop = isOfficialShop;
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
        sb.append("\"shopid\":").append(shopid);
        sb.append(",\"name\":\"").append(name).append('\"');
        sb.append(",\"shopLocation\":\"").append(shopLocation).append('\"');
        sb.append(",\"lastActiveTime\":\"").append(lastActiveTime).append('\"');
        sb.append("\"itemCount\":").append(itemCount);
        sb.append("\"min_itemCount\":").append(min_itemCount);
        sb.append("\"max_itemCount\":").append(max_itemCount);
        sb.append("\"ratingStar\":").append(ratingStar);
        sb.append("\"min_ratingStar\":").append(min_ratingStar);
        sb.append("\"max_ratingStar\":").append(max_ratingStar);
        sb.append("\"followerCount\":").append(followerCount);
        sb.append("\"min_followerCount\":").append(min_followerCount);
        sb.append("\"max_followerCount\":").append(max_followerCount);
        sb.append(",\"responseRate\":\"").append(responseRate).append('\"');
        sb.append(",\"responseTime\":\"").append(responseTime).append('\"');
        sb.append("\"ratingBad\":").append(ratingBad);
        sb.append("\"min_ratingBad\":").append(min_ratingBad);
        sb.append("\"max_ratingBad\":").append(max_ratingBad);
        sb.append("\"ratingGood\":").append(ratingGood);
        sb.append("\"min_ratingGood\":").append(min_ratingGood);
        sb.append("\"max_ratingGood\":").append(max_ratingGood);
        sb.append("\"ratingNormal\":").append(ratingNormal);
        sb.append("\"min_ratingNormal\":").append(min_ratingNormal);
        sb.append("\"max_ratingNormal\":").append(max_ratingNormal);
        sb.append(",\"isShopeeVerified\":\"").append(isShopeeVerified).append('\"');
        sb.append(",\"isPreferredPlusSeller\":\"").append(isPreferredPlusSeller).append('\"');
        sb.append(",\"isOfficialShop\":\"").append(isOfficialShop).append('\"');
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

package com.example.shopeecapture.Utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class InfoGetter {

    static final Logger logger = LoggerFactory.getLogger(InfoGetter.class);

    @Resource
    private static ProductsMapper productsMapper;

    public static Map<String, Object> getProduct(int pageCount, String searchStr, String eventId) {
    /*
    商品搜索结果
        https://xiapi.xiapibuy.com/api/v4/search/search_items?by=relevancy&keyword=%E5%BE%AE%E5%9E%8B%E7%A7%AF%E6%9C%A8&limit=60&newest=0&order=desc&page_type=search&scenario=PAGE_GLOBAL_SEARCH&version=2
        limit: 用来限制每次搜索返回多少结果
        newest: 用来设置数据个数，获取数据为newest-limit的那几条

        "adjust"."count":  从请求中的这个这个字段获取总的数据量，算出来页码，页码从0开始算
        "items".[i]."item_basic": 获取各个商品

        字段："items".[i]."item_basic"
        "itemid" , "shopid" , "name" , "currency" , "stock" , "liked_count" ,
        "sold" , "historical_sold" ,
        "price" , "price_min" , "price_max" , "price_before_discount" , "price_min_before_discount" , "price_max_before_discount" , (个位=price/100000)
        "show_discount"(以商品信息为准，这里的自己加%) ,
        "tier_variations".[0]."options" (型号选项名) ,
        "shopee_verified" , "is_official_shop"
        "shop_location"
    */
        logger.info("getProduct process start-------");
        Map<String, Object> resultMap = new HashMap<>(800000);
        List productsList = new ArrayList();
        List shopIdList = new ArrayList();
        List shopId_itemId_pairList = new ArrayList();
        try {
            for (int j = 0; j < pageCount; j++) {
                logger.info("getProduct start-------" + j);
                int newest = j * 60;
                String requestUrl = "https://xiapi.xiapibuy.com/api/v4/search/search_items?by=relevancy&keyword=" + searchStr + "&limit=60&newest=" + newest + "&order=desc&page_type=search&scenario=PAGE_GLOBAL_SEARCH&version=2";
                JSONObject callResult = Utils.callShoppe(requestUrl);
                if (callResult != null) {
                    String items = callResult.get("items") == null ? "" : callResult.get("items").toString();
                    if (StringUtils.isNotBlank(items)) {
                        JSONArray itemsJsonArray = JSONArray.parseArray(items);
                        for (int i = 0; i < itemsJsonArray.size(); i++) {
                            JSONObject itemJsonObject = itemsJsonArray.getJSONObject(i);
                            String itemBasic = itemJsonObject.get("item_basic") == null ? "" : itemJsonObject.get("item_basic").toString();
                            if (StringUtils.isNotBlank(itemBasic)) {
                                JSONObject itemBasicJsonObject = JSONObject.parseObject(itemBasic);
                                Products products = new Products();
                                products.setSearchstr(URLDecoder.decode(searchStr, "UTF-8"));
                                products.setEventid(eventId);
                                products.setItemid(itemBasicJsonObject.get("itemid") == null ? "" : itemBasicJsonObject.get("itemid").toString());
                                products.setShopid(itemBasicJsonObject.get("shopid") == null ? "" : itemBasicJsonObject.get("shopid").toString());
                                products.setName(itemBasicJsonObject.get("name") == null ? "" : itemBasicJsonObject.get("name").toString());
                                products.setStock(Utils.intValueCheck(itemBasicJsonObject.get("stock")));
                                products.setLikeCount(Utils.intValueCheck(itemBasicJsonObject.get("liked_count")));
                                products.setSold(Utils.intValueCheck(itemBasicJsonObject.get("sold")));
                                products.setHistoricalSold(Utils.intValueCheck(itemBasicJsonObject.get("historical_sold")));
                                products.setCurrency(itemBasicJsonObject.get("currency") == null ? "" : itemBasicJsonObject.get("currency").toString());
                                products.setPrice(Utils.doubleValueCheck(itemBasicJsonObject.get("price")));
                                products.setPriceMin(Utils.doubleValueCheck(itemBasicJsonObject.get("price_min")));
                                products.setPriceMax(Utils.doubleValueCheck(itemBasicJsonObject.get("price_max")));
                                products.setPriceBeforeDiscount(Utils.doubleValueCheck(itemBasicJsonObject.get("price_before_discount")));
                                products.setPriceMinBeforeDiscount(Utils.doubleValueCheck(itemBasicJsonObject.get("price_min_before_discount")));
                                products.setPriceMaxBeforeDiscount(Utils.doubleValueCheck(itemBasicJsonObject.get("price_max_before_discount")));
                                products.setShowDiscount(itemBasicJsonObject.get("show_discount") == null ? "0" : (itemBasicJsonObject.get("show_discount").toString() + "%"));
//                                products.setTierVariations(itemBasicJsonObject.get("tier_variations") == null ? "" : itemBasicJsonObject.get("tier_variations").toString());
                                products.setShopeeVerified(itemBasicJsonObject.get("shopee_verified") == null ? "false" : itemBasicJsonObject.get("shopee_verified").toString());
                                products.setIsOfficialShop(itemBasicJsonObject.get("is_official_shop") == null ? "false" : itemBasicJsonObject.get("is_official_shop").toString());
                                products.setShopLocation(itemBasicJsonObject.get("shop_location") == null ? "" : itemBasicJsonObject.get("shop_location").toString());
                                String itemId = itemBasicJsonObject.get("itemid") == null ? "" : itemBasicJsonObject.get("itemid").toString();
                                String shopId = itemBasicJsonObject.get("shopid") == null ? "" : itemBasicJsonObject.get("shopid").toString();
                                if (StringUtils.isNotBlank(itemId) && StringUtils.isNotBlank(shopId)) {
                                    products.setValueDate(new Date());
                                    products.setInsertTime(new Date());

                                    shopId_itemId_pairList.add(itemId + "-" + shopId);
                                    shopIdList.add(shopId);
                                    productsList.add(products);
                                } else {
                                    if (StringUtils.isBlank(itemId)) {
                                        logger.info("itemId is null:" + itemId);
                                    } else if (StringUtils.isBlank(shopId)) {
                                        logger.info("shopid is null:" + shopId);
                                    }
                                }
                            } else {
                                logger.info("item_basic-------------not exist");
                            }
                        }
                    } else {
                        logger.info("could not find items");
                    }
                    logger.info("get product list process:" + Utils.getPercent(j + 1, pageCount));
                } else {
                    logger.info("call time out");
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        resultMap.put("shopIdList", shopIdList);
        resultMap.put("shopId_itemId_pairList", shopId_itemId_pairList);
        resultMap.put("productsList", productsList);
        logger.info("getProduct process end-------");
        return resultMap;
    }

    public static Map<String, Object> getProductDetails(String itemId, String shopId, String eventId,DetailmodelsMapper detailmodelsMapper,DetailshopvouchersMapper detailshopvouchersMapper,ProductdetailsMapper productdetailsMapper) {
    /*
    商品信息
        https://xiapi.xiapibuy.com/api/v4/item/get?itemid=14282556366&shopid=5954263

        字段："data".
        "price_max_before_discount"  , "price_before_discount": 18300000 , "price_min_before_discount" , "has_lowest_price_guarantee",
		"price_min"  , "price_max"  , "price"  ,
		"stock"  , "discount" (直接就是百分数) , "min_purchase_limit"
		"historical_sold"  , "sold": 0,
		"name" ,
		"image","images" (https://s-cf-tw.shopeesz.com/file/#{image value})
		"models".[i] (每个选项对应的金额)
		    "name" , "price" , "price_before_discount"
		"shop_vouchers".[i]  (店铺优惠券)
		    "min_spend" , "discount_value"
    */
        logger.info("getProductDetails process start-------");
        Map<String, Object> resultMap = new HashMap<>(800000);
        try {
            Productdetails productdetails = new Productdetails();
            String requestUrl = "https://xiapi.xiapibuy.com/api/v4/item/get?itemid=" + itemId + "&shopid=" + shopId;
            JSONObject callResult = Utils.callShoppe(requestUrl);
            if (callResult != null) {
                String data = callResult.get("data") == null ? "" : callResult.get("data").toString();
                if (StringUtils.isNotBlank(data)) {
                    productdetails.setEventid(eventId);
                    productdetails.setShopid(shopId);
                    productdetails.setItemid(itemId);
                    JSONObject dataJsonObject = JSONObject.parseObject(data);
                    productdetails.setName(dataJsonObject.get("name") == null ? "" : dataJsonObject.get("name").toString());
                    productdetails.setPrice(Utils.doubleValueCheck(dataJsonObject.get("price")));
                    productdetails.setPriceMin(Utils.doubleValueCheck(dataJsonObject.get("price_min")));
                    productdetails.setPriceMax(Utils.doubleValueCheck(dataJsonObject.get("price_max")));
                    productdetails.setPriceBeforeDiscount(Utils.doubleValueCheck(dataJsonObject.get("price_before_discount")));
                    productdetails.setPriceMinBeforeDiscount(Utils.doubleValueCheck(dataJsonObject.get("price_min_before_discount")));
                    productdetails.setPriceMaxBeforeDiscount(Utils.doubleValueCheck(dataJsonObject.get("price_max_before_discount")));
                    productdetails.setHasLowestPriceGuarantee(dataJsonObject.get("has_lowest_price_guarantee") == null ? "false" : dataJsonObject.get("has_lowest_price_guarantee").toString());
                    String discount = dataJsonObject.get("discount") == null ? "0" : dataJsonObject.get("discount").toString();
                    double discountDouble = Double.parseDouble(discount) * 10;
                    BigDecimal bd = new BigDecimal(discountDouble);
                    discountDouble = bd.setScale(0, RoundingMode.DOWN).doubleValue();
                    discount = discountDouble + "%";
                    productdetails.setDiscount(discount);
                    String minPurchaseLimit = dataJsonObject.get("min_purchase_limit") == null ? "0" : dataJsonObject.get("min_purchase_limit").toString();
                    minPurchaseLimit = "0".equals(minPurchaseLimit) ? "false" : "true";
                    productdetails.setMinPurchaseLimit(minPurchaseLimit);
                    productdetails.setStock(Utils.intValueCheck(dataJsonObject.get("stock")));
                    productdetails.setHistoricalSold(Utils.intValueCheck(dataJsonObject.get("historical_sold")));
                    productdetails.setSold(Utils.intValueCheck(dataJsonObject.get("sold")));
                    productdetails.setImage(dataJsonObject.get("image") == null ? "" : ("https://s-cf-tw.shopeesz.com/file/" + dataJsonObject.get("image").toString()));
                    String tier_variations = dataJsonObject.get("tier_variations") == null ? "" : dataJsonObject.get("tier_variations").toString();
                    Map<String, String> imagesMap = new HashMap<>(800000);
                    if (StringUtils.isNotBlank(tier_variations)) {
                        JSONArray tierVariationJsonArray = JSONArray.parseArray(tier_variations);
                        if (tierVariationJsonArray.size() > 0) {
                            JSONObject tierVariationJsonObject = tierVariationJsonArray.getJSONObject(0);
                            String options = tierVariationJsonObject.get("options") == null ? "" : tierVariationJsonObject.get("options").toString();
                            String images = tierVariationJsonObject.get("images") == null ? "" : tierVariationJsonObject.get("images").toString();
                            JSONArray optionsJsonArray = JSONArray.parseArray(options);
                            JSONArray imagesJsonArray = JSONArray.parseArray(images);
                            for (int i = 0; i < optionsJsonArray.size(); i++) {
                                if ((imagesJsonArray != null) && (i < imagesJsonArray.size())) {
                                    String imageValue = "https://s-cf-tw.shopeesz.com/file/" + imagesJsonArray.get(i).toString();
                                    imagesMap.put(optionsJsonArray.get(i).toString(), imageValue);
                                }
                            }
                        }
                    }

                    String modelsArray = dataJsonObject.get("models") == null ? "" : dataJsonObject.get("models").toString();
                    if (StringUtils.isNotBlank(modelsArray)) {
                        List<Integer> detailModelsIdList = new ArrayList<>();
                        JSONArray modelsJsonArray = JSONArray.parseArray(modelsArray);
                        for (int i = 0; i < modelsJsonArray.size(); i++) {
                            JSONObject modelsJsonObject = modelsJsonArray.getJSONObject(i);
                            String name = modelsJsonObject.get("name") == null ? "" : modelsJsonObject.get("name").toString();
                            Detailmodels detailModels = new Detailmodels();
                            detailModels.setEventid(eventId);
                            detailModels.setShopid(shopId);
                            detailModels.setItemid(itemId);
                            detailModels.setName(name);
                            detailModels.setStock(Utils.intValueCheck(modelsJsonObject.get("stock")));
                            detailModels.setPrice(Utils.doubleValueCheck(modelsJsonObject.get("price")));
                            detailModels.setPriceBeforeDiscount(Utils.doubleValueCheck(modelsJsonObject.get("price_before_discount")));
                            if (!"".equals(name)) {
                                String image = imagesMap.get(name);
                                detailModels.setImage(image);
                            }
                            detailModels.setValueDate(new Date());
                            detailModels.setInsertTime(new Date());
                            detailmodelsMapper.insert(detailModels);
                            detailModelsIdList.add(detailModels.getId());
                        }
                        productdetails.setDetailmodelsid(detailModelsIdList.toString());
                    }
                    String shopVouchersArray = dataJsonObject.get("shop_vouchers") == null ? "" : dataJsonObject.get("shop_vouchers").toString();
                    if (StringUtils.isNotBlank(shopVouchersArray)) {
                        List<Integer> detailshopvouchersIdList = new ArrayList<>();
                        JSONArray shopVouchersJsonArray = JSONArray.parseArray(shopVouchersArray);
                        for (int i = 0; i < shopVouchersJsonArray.size(); i++) {
                            JSONObject shopVouchersJSONObject = shopVouchersJsonArray.getJSONObject(i);
                            Detailshopvouchers detailshopvouchers = new Detailshopvouchers();
                            detailshopvouchers.setEventid(eventId);
                            detailshopvouchers.setShopid(shopId);
                            detailshopvouchers.setDiscountValue(Utils.doubleValueCheck(shopVouchersJSONObject.get("discount_value")));
                            detailshopvouchers.setMinSpend(Utils.doubleValueCheck(shopVouchersJSONObject.get("min_spend")));
                            detailshopvouchers.setValueDate(new Date());
                            detailshopvouchers.setInsertTime(new Date());
                            detailshopvouchersMapper.insert(detailshopvouchers);
                            detailshopvouchersIdList.add(detailshopvouchers.getId());
                        }
                        productdetails.setShopVouchersid(detailshopvouchersIdList.toString());
                    }
                    String itemrUrl = productdetails.getName();
                    itemrUrl = itemrUrl.replaceAll("<", "-")
                            .replaceAll(">", "-")
                            .replaceAll(" ", "-")
                            .replaceAll("/", "-");
                    itemrUrl = "https://xiapi.xiapibuy.com/" + itemrUrl + "-i." + productdetails.getShopid() + "." + productdetails.getItemid() + "?sp_atk=1ed08a4f-b007-4658-83c0-ab0d1ceca4c2&xptdk=1ed08a4f-b007-4658-83c0-ab0d1ceca4c2";
                    productdetails.setUrl(itemrUrl);
                    productdetails.setValueDate(new Date());
                    productdetails.setInsertTime(new Date());
                    resultMap.put(itemId + "-" + shopId, productdetails);
                    int count = productdetailsMapper.insert(productdetails);
                    if (count > 0) {
                        logger.debug("insert itemId:" + itemId + " shopId:" + shopId + " SUCCESS====");
                    } else {
                        logger.debug("insert itemId:" + itemId + " shopId:" + shopId + " FAILED----");
                    }
                }else{
                    resultMap = null;
                    logger.info("not get data for itemId:" + itemId + " shopId:" + shopId);
                }
            } else {
                resultMap = null;
                logger.info("call time out");
            }
        } catch (Exception e) {
            resultMap = null;
            logger.error(e.getMessage());
        }
        logger.info("getProductDetails process end-------");
        return resultMap;
    }

    public static Map<String, Object> getShopInfo(String shopId, String eventId,ShopinfoMapper shopinfoMapper) {
    /*
    商店信息
        https://xiapi.xiapibuy.com/api/v4/product/get_shop_info?shopid=5954263

        字段："data".
        "shopid" , "name" , "shop_location" , "last_active_time" ,
        "item_count" , "rating_star" , "follower_count" ,
        "is_shopee_verified" , "is_preferred_plus_seller" , "is_official_shop" ,
        "response_rate" ,  "response_time" ,
        "rating_bad" , "rating_good" , "rating_normal"
    */
        logger.info("getShopInfo process start-------");
        Map<String, Object> resultMap = new HashMap<>(800000);
        try {
            String requestUrl = "https://xiapi.xiapibuy.com/api/v4/product/get_shop_info?shopid=" + shopId;
            JSONObject callResult = Utils.callShoppe(requestUrl);
            if (callResult != null) {
                String data = callResult.get("data") == null ? "" : callResult.get("data").toString();
                if (StringUtils.isNotBlank(data)) {
                    JSONObject dataJsonObject = JSONObject.parseObject(data);
                    Shopinfo shopInfo = new Shopinfo();
                    shopInfo.setEventid(eventId);
                    shopInfo.setShopid(dataJsonObject.get("shopid") == null ? "" : dataJsonObject.get("shopid").toString());
                    shopInfo.setName(dataJsonObject.get("name") == null ? "" : dataJsonObject.get("name").toString());
                    shopInfo.setShopLocation(dataJsonObject.get("shop_location") == null ? "" : dataJsonObject.get("shop_location").toString());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String lastActivieTime = dataJsonObject.get("last_active_time") == null ? "0" : dataJsonObject.get("last_active_time").toString();
                    long lastActivieTime_temp = Long.parseLong(lastActivieTime);
                    String lastActivieTime_string = sdf.format(new Date(lastActivieTime_temp * 1000L));
                    shopInfo.setLastActiveTime(lastActivieTime_string);
                    shopInfo.setItemCount(Utils.intValueCheck(dataJsonObject.get("item_count")));
                    double ratingStar = dataJsonObject.get("rating_star") == null ? (double) 0 : Double.parseDouble(dataJsonObject.get("rating_star").toString());
                    shopInfo.setRatingStar(ratingStar);
                    shopInfo.setFollowerCount(Utils.intValueCheck(dataJsonObject.get("follower_count")));
                    shopInfo.setResponseRate(dataJsonObject.get("response_rate") == null ? "0%" : (dataJsonObject.get("response_rate").toString() + "%"));
                    String responseTime = dataJsonObject.get("response_time") == null ? "0" : dataJsonObject.get("response_time").toString();
                    DecimalFormat decimalFormat = new DecimalFormat("##");
                    responseTime = decimalFormat.format(Integer.parseInt(responseTime) / 60.00 / 60.00);
                    shopInfo.setResponseTime(responseTime + " Hours");
                    shopInfo.setRatingBad(Utils.intValueCheck(dataJsonObject.get("rating_bad")));
                    shopInfo.setRatingGood(Utils.intValueCheck(dataJsonObject.get("rating_good")));
                    shopInfo.setRatingNormal(Utils.intValueCheck(dataJsonObject.get("rating_normal")));
                    shopInfo.setIsShopeeVerified(dataJsonObject.get("is_shopee_verified") == null ? "false" : dataJsonObject.get("is_shopee_verified").toString());
                    shopInfo.setIsPreferredPlusSeller(dataJsonObject.get("is_preferred_plus_seller") == null ? "false" : dataJsonObject.get("is_preferred_plus_seller").toString());
                    shopInfo.setIsOfficialShop(dataJsonObject.get("is_official_shop") == null ? "false" : dataJsonObject.get("is_official_shop").toString());
                    shopInfo.setValueDate(new Date());
                    shopInfo.setInsertTime(new Date());
                    resultMap.put(shopId, shopInfo);
                    int count = shopinfoMapper.insert(shopInfo);
                    if (count > 0) {
                        logger.debug("insert " + shopId + " shopinfo SUCCESS====");
                    } else {
                        logger.debug("insert " + shopId + " shopinfo FAILED----");
                    }
                } else {
                    resultMap = null;
                    logger.info("not get data for shopId:" + shopId);
                }
            } else {
                resultMap = null;
                logger.info("call time out");
            }
        } catch (Exception e) {
            resultMap = null;
            logger.error(e.getMessage());
        }
        logger.info("getShopInfo process end-------");
        return resultMap;
    }

    public static Map<String, Object> getDeliverFeeWithItemAndShopId(String itemId, String shopId, String eventId,DeliversMapper deliversMapper) {
    /*
     运费
        https://xiapi.xiapibuy.com/api/v4/pdp/get_shipping?buyer_zipcode=&city=%E4%B8%AD%E6%AD%A3%E5%8D%80&district=&itemid=14282556366&shopid=5954263&state=%E8%87%BA%E5%8C%97%E5%B8%82&town=

        字段："data".
        "product_info"."free_shipping"."min_spend" (免运费条件 个位=price/100000)
        "shipping_fee_info"."min_price"."max_price" (国内以到台北为单位  个位=price/100000)
        "ungrouped_channel_infos" (获取数组长度，以获取支持的快递种类个数)
    */
        logger.info("getDeliverFeeWithItemAndShopId process start-------");
        Map<String, Object> resultMap = new HashMap<>(800000);
        try {
            Delivers delivers = new Delivers();
            String requestUrl = "https://xiapi.xiapibuy.com/api/v4/pdp/get_shipping?buyer_zipcode=&city=%E4%B8%AD%E6%AD%A3%E5%8D%80&district=&itemid=" + itemId + "&shopid=" + shopId + "&state=%E8%87%BA%E5%8C%97%E5%B8%82&town=";
            JSONObject callResult = Utils.callShoppe(requestUrl);
            if (callResult != null) {
                String data = callResult.get("data") == null ? "" : callResult.get("data").toString();
                if (StringUtils.isNotBlank(data)) {
                    delivers.setEventid(eventId);
                    delivers.setShopid(shopId);
                    delivers.setItemid(itemId);
                    JSONObject dataJsonObject = JSONObject.parseObject(data);
                    String productInfo = dataJsonObject.get("product_info") == null ? "" : dataJsonObject.get("product_info").toString();
                    if (StringUtils.isNotBlank(productInfo)) {
                        JSONObject productInfoJsonObject = JSONObject.parseObject(productInfo);
                        String freeShipping = productInfoJsonObject.get("free_shipping") == null ? "" : productInfoJsonObject.get("free_shipping").toString();
                        if (StringUtils.isNotBlank(freeShipping)) {
                            JSONObject freeShippingJsonObject = JSONObject.parseObject(freeShipping);
                            delivers.setMinSpend(Utils.doubleValueCheck(freeShippingJsonObject.get("min_spend")));
                        } else {
                            delivers.setMinSpend((double) 0);
                            logger.info("could not find deliver free_shipping");
                        }
                        String shippingFeeInfo = productInfoJsonObject.get("shipping_fee_info") == null ? "" : productInfoJsonObject.get("shipping_fee_info").toString();
                        if (StringUtils.isNotBlank(shippingFeeInfo)) {
                            JSONObject shippingFeeInfoJsonObject = JSONObject.parseObject(shippingFeeInfo);
                            delivers.setMinPrice(Utils.doubleValueCheck(shippingFeeInfoJsonObject.get("min_price")));
                            delivers.setMaxPrice(Utils.doubleValueCheck(shippingFeeInfoJsonObject.get("max_price")));
                        } else {
                            logger.info("could not find deliver shipping_fee_info");
                        }
                    } else {
                        logger.info("could not find deliver product_info");
                    }
                    String ungroupedChannelInfos = dataJsonObject.get("ungrouped_channel_infos") == null ? "" : dataJsonObject.get("ungrouped_channel_infos").toString();
                    if (StringUtils.isNotBlank(ungroupedChannelInfos)) {
                        JSONArray ungroupedChannelInfosJsonArray = JSONArray.parseArray(ungroupedChannelInfos);
                        List<Integer> maxPriceList = new ArrayList<>();
                        for (int i = 0; i < ungroupedChannelInfosJsonArray.size(); i++) {
                            JSONObject deliverFee = ungroupedChannelInfosJsonArray.getJSONObject(i);
                            int prePrice = 0;
                            if (deliverFee.get("max_price") != null) {
                                prePrice = Integer.parseInt(deliverFee.get("max_price").toString());
                            }
                            maxPriceList.add(prePrice);
                        }
                        double sufPrice = Utils.median(maxPriceList) / 100000;
                        delivers.setMedianPrice(sufPrice);
                    }
                    delivers.setValueDate(new Date());
                    delivers.setInsertTime(new Date());
                    resultMap.put(itemId + "-" + shopId, delivers);
                    deliversMapper.insert(delivers);
                } else {
                    resultMap = null;
                    logger.info("could not find deliver info data");
                }
            } else {
                resultMap = null;
                logger.info("call time out");
            }
        } catch (Exception e) {
            resultMap = null;
            logger.error(e.getMessage());
        }
        logger.info("getDeliverFeeWithItemAndShopId process end-------");
        return resultMap;
    }

}

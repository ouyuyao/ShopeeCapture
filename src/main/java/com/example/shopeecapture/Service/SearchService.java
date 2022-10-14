package com.example.shopeecapture.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.shopeecapture.Bean.Products;
import com.example.shopeecapture.Mapper.*;
import com.example.shopeecapture.Utils.InfoGetter;
import com.example.shopeecapture.Utils.Utils;
import com.example.shopeecapture.config.Constants;
import com.example.shopeecapture.config.Email.EmailLog;
import com.example.shopeecapture.config.Email.EmailMessageBean;
import com.example.shopeecapture.config.ResponseInfo;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author ottoo
 */
@Service("searchService")
public class SearchService {

    static final Logger logger = LoggerFactory.getLogger(SearchService.class);

    @Resource
    private AsyncThreadTaskService asyncThreadTaskService;

    @Resource
    private DeliversMapper deliversMapper;
    @Resource
    private DetailmodelsMapper detailmodelsMapper;
    @Resource
    private DetailshopvouchersMapper detailshopvouchersMapper;
    @Resource
    private ProductdetailsMapper productdetailsMapper;
    @Resource
    private ShopinfoMapper shopinfoMapper;
    @Autowired
    EmailLog emailLog;

    public ResponseInfo queryBySearchStr(List<String> searchKeyList) throws Exception {
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("Process SearchService queryBySearchStr()......." + searchKeyList.toString())));
        logger.info("Process SearchService queryBySearchStr()......." + searchKeyList.toString());

        String eventId = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        Map<String, Object> getTotalProductsResult = startGetProductAndRalateInfo(searchKeyList, eventId);
        List<String> totalShopIdList = (List<String>) getTotalProductsResult.get("totalShopIdList");
        List<String> totalShopId_itemId_pairList = (List<String>) getTotalProductsResult.get("totalShopId_itemId_pairList");
        List<Products> totalProductsList = (List<Products>) getTotalProductsResult.get("totalProductsList");

        logger.info("totalShopIdList:" + totalShopIdList.size());
        logger.info("totalShopId_itemId_pairList:" + totalShopId_itemId_pairList.size());
        logger.info("totalProductsList:" + totalProductsList.size());

        List<Future<String>> futureList=new ArrayList<>();

        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("prepare insert Products records:"+totalProductsList.size())));
        CountDownLatch countDownLatchForProducts = new CountDownLatch(totalProductsList.size());
        Future<String> asyncResult = asyncThreadTaskService.insertProduct(totalProductsList, countDownLatchForProducts);
        String insertProductCount = asyncResult.get();
        futureList.add(asyncResult);
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("actually insert Products records:"+insertProductCount)));

        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("prepare handle ProductDetails records:"+totalShopId_itemId_pairList.size())));
        CountDownLatch countDownLatchForProductDetails = new CountDownLatch(totalShopId_itemId_pairList.size());
        Future<String> processProductDetailsResult = asyncThreadTaskService.processProductDetails(eventId,totalShopId_itemId_pairList,countDownLatchForProductDetails, detailmodelsMapper, detailshopvouchersMapper, productdetailsMapper);
        String processProductDetailsCount = processProductDetailsResult.get();
        futureList.add(processProductDetailsResult);
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("actually handle ProductDetails records:"+processProductDetailsCount)));

        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("prepare handle ShopInfos records:"+totalShopIdList.size())));
        CountDownLatch countDownLatchForShopInfos = new CountDownLatch(totalShopIdList.size());
        Future<String> processShopInfosResult = asyncThreadTaskService.processShopInfos(eventId,totalShopIdList,countDownLatchForShopInfos, shopinfoMapper);
        String processShopInfosCount = processShopInfosResult.get();
        futureList.add(processShopInfosResult);
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("actually handle ShopInfos records:"+processShopInfosCount)));

//        CountDownLatch countDownLatchForDeliverInfos = new CountDownLatch(totalShopId_itemId_pairList.size());
//        futureList.add(asyncThreadTaskService.processDeliverInfos(eventId,totalShopId_itemId_pairList,countDownLatchForDeliverInfos,deliversMapper));

        while (!futureList.isEmpty()) {
            //回调信息空了就结束**
            for (int i = 0; i < futureList.size(); i++) {
                if (futureList.get(i).isDone()) {
                    //判断线程结束，输出回调信息，并将该回调清除
                    logger.info(futureList.get(i).get());
                    futureList.remove(i);
                } else {
                    continue;
                }
            }
        }

        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        listResponseMsg.add(eventId);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(listResponseMsg);

        return responseInfo;
    }

    public Map<String, Object> startGetProductAndRalateInfo(List<String> searchKeyList, String eventId) {
        logger.info("startGetProductAndRalateInfo start-------");
        try {
            List totalShopIdList = new ArrayList();
            List totalShopId_itemId_pairList = new ArrayList();
            List<Products> totalProductsList = new ArrayList();
            int prevProductCount = 0;
            for (int i = 0; i < searchKeyList.size(); i++) {
                String searchStr = searchKeyList.get(i);
                logger.info("searchStr:"+searchStr);
                int newest = 0;
                int pageCount;
                String requestUrl = "https://xiapi.xiapibuy.com/api/v4/search/search_items?by=relevancy&keyword=" + searchStr + "&limit=60&newest=" + newest + "&order=desc&page_type=search&scenario=PAGE_GLOBAL_SEARCH&version=2";
                JSONObject callResult = Utils.callShoppe(requestUrl);
                if (callResult != null) {
                    String adjust = callResult.get("adjust") == null ? "" : callResult.get("adjust").toString();
                    if (StringUtils.isNotBlank(adjust)) {
                        JSONObject adjustJson = JSONObject.parseObject(adjust);
                        String count = adjustJson.get("count") == null ? "0" : adjustJson.get("count").toString();
                        if (StringUtils.isNotBlank(count)) {
                            //计算出总数
                            pageCount = Integer.parseInt(count) / 60;
                            Map<String, Object> result = InfoGetter.getProduct(pageCount, searchStr, eventId);
                            List shopIdList = (List) result.get("shopIdList");
                            List shopId_itemId_pairList = (List) result.get("shopId_itemId_pairList");
                            List productsList = (List) result.get("productsList");
                            totalShopIdList = (List) Stream.of(totalShopIdList, shopIdList).flatMap(Collection::stream).distinct().collect(Collectors.toList());
                            totalShopId_itemId_pairList = (List) Stream.of(totalShopId_itemId_pairList, shopId_itemId_pairList).flatMap(Collection::stream).distinct().collect(Collectors.toList());
                            totalProductsList = (List) Stream.of(totalProductsList, productsList).flatMap(Collection::stream).collect(Collectors.toList());
                            totalProductsList = totalProductsList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                                    new TreeSet<>(Comparator.comparing(t -> t.getItemid() + "#" + t.getShopid()))), ArrayList::new));

                            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("queryBySearchStr():" + searchStr+" got data")));
                        } else {
                            logger.info("could not find product count");
                        }
                    } else {
                        logger.info("could not find adjust");
                    }
                } else {
                    logger.info("could not find message with " + searchStr);
                    emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("could not find message with " + searchStr)));
                }
                logger.info("startGetProductAndRalateInfo process:" + Utils.getPercent((i + 1), (searchKeyList.size())));
                prevProductCount = totalProductsList.size() - prevProductCount;
                emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("queryBySearchStr():" + searchStr+" products count:"+prevProductCount)));
            }
            totalProductsList = totalProductsList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() ->
                    new TreeSet<>(Comparator.comparing(t -> t.getItemid() + "#" + t.getShopid()))), ArrayList::new));

            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("totalShopIdList", totalShopIdList);
            resultMap.put("totalShopId_itemId_pairList", totalShopId_itemId_pairList);
            resultMap.put("totalProductsList", totalProductsList);
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("total Shop counts:"+totalShopIdList.size())));
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("total product counts:"+totalProductsList.size())));
            return resultMap;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        logger.info("startGetProductAndRalateInfo end-------");
        return null;
    }

}

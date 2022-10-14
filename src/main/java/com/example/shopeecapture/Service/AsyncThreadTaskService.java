package com.example.shopeecapture.Service;

import com.example.shopeecapture.Bean.Products;
import com.example.shopeecapture.Mapper.*;
import com.example.shopeecapture.Utils.InfoGetter;
import com.example.shopeecapture.Utils.Utils;
import com.example.shopeecapture.config.Email.EmailLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

@Service("asyncThreadTaskService")
public class AsyncThreadTaskService {
    static final Logger logger = LoggerFactory.getLogger(AsyncThreadTaskService.class);

    @Resource
    private ProductsMapper productsMapper;
    @Autowired
    EmailLog emailLog;

    @Async("asyncTaskExecutor")
    public Future<String> insertProduct(List<Products> totalProductsList,CountDownLatch countDownLatchForProducts){
        int insertCount = 0;
        try{
            long startTime = System.currentTimeMillis();
            logger.info("insertProduct Async start-------");
            if(totalProductsList.size()>0){
                logger.error("check size before loop:"+totalProductsList.size());
                int loopcount = 0;
                for (Products products : totalProductsList) {
                    int insertMark = this.productsMapper.insert(products);
                    insertCount = insertMark<=0?(insertCount):(insertCount+1);
                    if (insertMark!=1){
                        logger.error("itemid:"+products.getItemid()+"-shopid:"+products.getShopid()+"---insert mark:"+insertMark+"===========");
                    }
                    loopcount = loopcount + 1;
                }
                logger.error("check loop count after loop finish:"+loopcount);
            }
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("insertProduct process time:" + Utils.formatTime(processTime));
            logger.info("insertProduct Async end-------");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            countDownLatchForProducts.countDown();
            return new AsyncResult<>(String.valueOf(insertCount));
        }
    }

    @Async("asyncTaskExecutor")
    public Future<String> processProductDetails(String eventId, List<String> totalShopId_itemId_pairList,CountDownLatch countDownLatchForProductDetails,DetailmodelsMapper detailmodelsMapper,DetailshopvouchersMapper detailshopvouchersMapper,ProductdetailsMapper productdetailsMapper){
        int processCount = 0;
        try{
            long startTime = System.currentTimeMillis();
            logger.info("processProductDetails Async start-------");
            if (totalShopId_itemId_pairList.size() > 0) {
                int loopCount = 0;
                //获取上面所有商品所关联的商品详细信息
                for (String shoIdItemId : totalShopId_itemId_pairList) {
                    String[] ids = shoIdItemId.split("-");
                    try {
                        String itemId = ids[0];
                        String shopId = ids[1];
                        Map<String, Object> result1 = InfoGetter.getProductDetails(itemId, shopId, eventId,detailmodelsMapper,detailshopvouchersMapper,productdetailsMapper);
                        if (result1 != null) {
                            loopCount = loopCount + 1;
                            processCount = processCount + result1.size();
                        }
                        logger.info("get product detail info process:" + Utils.getPercent(loopCount, totalShopId_itemId_pairList.size()));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("processProductDetails process time:" + Utils.formatTime(processTime));
            logger.info("processProductDetails Async end-------");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            countDownLatchForProductDetails.countDown();
            return new AsyncResult<>( String.valueOf(processCount));
        }
    }

    @Async("asyncTaskExecutor")
    public Future<String> processShopInfos(String eventId, List<String> totalShopIdList,CountDownLatch countDownLatchForShopInfos,ShopinfoMapper shopinfoMapper){
        int processCount = 0;
        try{
            long startTime = System.currentTimeMillis();
            logger.info("processShopInfos Async start-------");
            if (totalShopIdList.size() > 0) {
                int loopCount = 0;
                //获取上面所有商品所关联的商铺信息
                for (String shopid : totalShopIdList) {
                    try {
                        Map<String, Object> result1 = InfoGetter.getShopInfo(shopid, eventId,shopinfoMapper);
                        if (result1 != null) {
                            loopCount = loopCount + 1;
                            processCount = processCount + 1;
                        }
                        logger.info("get shop info process:" + Utils.getPercent(loopCount, totalShopIdList.size()));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("processShopInfos process time:" + Utils.formatTime(processTime));
            logger.info("processShopInfos Async end-------");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            countDownLatchForShopInfos.countDown();
            return new AsyncResult<>( String.valueOf(processCount));
        }
    }

    @Async("asyncTaskExecutor")
    public Future<String> processDeliverInfos(String eventId, List<String> totalShopId_itemId_pairList, CountDownLatch countDownLatchForDeliverInfos, DeliversMapper deliversMapper){
        try{
            long startTime = System.currentTimeMillis();
            logger.info("processDeliverInfos Async start-------");
            if (totalShopId_itemId_pairList.size() > 0) {
                int loopCount = 0;
                //获取上面所有商品所关联的快递信息
                for (String shoIdItemId : totalShopId_itemId_pairList) {
                    String[] ids = shoIdItemId.split("-");
                    try {
                        String itemId = ids[0];
                        String shopId = ids[1];
                        Map<String, Object> result1 = InfoGetter.getDeliverFeeWithItemAndShopId(itemId, shopId, eventId,deliversMapper);
                        if (result1 != null) {
                            loopCount = loopCount + 1;
                        }
                        logger.info("get deliver info process:" + Utils.getPercent(loopCount, totalShopId_itemId_pairList.size()));
                    } catch (Exception e) {
                        logger.error(e.getMessage());
                    }
                }
            }
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("processDeliverInfos process time:" + Utils.formatTime(processTime));
            logger.info("processDeliverInfos Async end-------");
        }catch (Exception e){
            logger.error(e.getMessage());
        }finally {
            countDownLatchForDeliverInfos.countDown();
            return new AsyncResult<>( "DONE");
        }
    }

    @Async("asyncTaskExecutor")
    public Future<String> checkMissedForShopInfos(String eventId, String shopid,CountDownLatch countDownLatchForShopInfos,ShopinfoMapper shopinfoMapper){
        try{
            long startTime = System.currentTimeMillis();
            logger.info("checkMissedForShopInfos Async start-------");
            Map<String, Object> result1 = InfoGetter.getShopInfo(shopid, eventId,shopinfoMapper);
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("checkMissedForShopInfos process time:" + Utils.formatTime(processTime));
            logger.info("checkMissedForShopInfos Async end-------");
            countDownLatchForShopInfos.countDown();
            if (result1 != null) {
                logger.info("checkMissedForShopInfos process success");
                return new AsyncResult<>( "1");
            } else {
                logger.info("checkMissedForShopInfos process failed");
                return new AsyncResult<>( "0");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            countDownLatchForShopInfos.countDown();
            return new AsyncResult<>( "0");
        }
    }

    @Async("asyncTaskExecutor")
    public Future<String> checkMissedForProductdetails(String eventId, String itemId, String shopId,DetailmodelsMapper detailmodelsMapper,DetailshopvouchersMapper detailshopvouchersMapper,ProductdetailsMapper productdetailsMapper,CountDownLatch countDownLatchForProductdetails){
        try{
            long startTime = System.currentTimeMillis();
            logger.info("checkMissedForProductdetails Async start-------");
            Map<String, Object> result1 = InfoGetter.getProductDetails(itemId, shopId, eventId,detailmodelsMapper,detailshopvouchersMapper,productdetailsMapper);
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("checkMissedForProductdetails process time:" + Utils.formatTime(processTime));
            logger.info("checkMissedForProductdetails Async end-------");
            countDownLatchForProductdetails.countDown();
            if (result1 != null) {
                logger.info("checkMissedForProductdetails process success");
                return new AsyncResult<>( "1");
            } else {
                logger.info("checkMissedForProductdetails process failed");
                return new AsyncResult<>( "0");
            }
        }catch (Exception e){
            logger.error(e.getMessage());
            countDownLatchForProductdetails.countDown();
            return new AsyncResult<>( "0");
        }
    }
}

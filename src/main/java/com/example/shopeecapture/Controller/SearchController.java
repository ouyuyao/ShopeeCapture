package com.example.shopeecapture.Controller;

import com.example.shopeecapture.Bean.Productdetails;
import com.example.shopeecapture.Bean.Searchkey;
import com.example.shopeecapture.Bean.Shopinfo;
import com.example.shopeecapture.Service.ProductdetailsService;
import com.example.shopeecapture.Service.SearchService;
import com.example.shopeecapture.Service.SearchkeyService;
import com.example.shopeecapture.Service.ShopinfoService;
import com.example.shopeecapture.Utils.Utils;
import com.example.shopeecapture.config.Constants;
import com.example.shopeecapture.config.Email.EmailLog;
import com.example.shopeecapture.config.Email.EmailMessageBean;
import com.example.shopeecapture.config.ResponseInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("search")
@Api(value = "SearchController", description = "触发爬虫")
public class SearchController {

    static final Logger logger = LoggerFactory.getLogger(SearchController.class);

    /**
     * 服务对象
     */
    @Resource
    private SearchService searchService;
    @Resource
    private SearchkeyService searchkeyService;
    @Resource
    private ShopinfoService shopinfoService;
    @Resource
    private ProductdetailsService productdetailsService;
    @Autowired
    EmailLog emailLog;

    @ApiOperation(notes = "触发爬虫", value="manualSearch")
    @RequestMapping(value = "manualSearch", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo manualSearch() throws Exception {
        ResponseInfo activtyResponse = new ResponseInfo();
        long startTime = System.currentTimeMillis();
        try {
            ResponseInfo responseInfo = searchkeyService.queryAll();
            List<Searchkey> keyList = (List<Searchkey>) responseInfo.getResponseMsg();
            if (keyList.size()>0){
                List<String> searchKeyList = new ArrayList<>();
                for (Searchkey searchkey : keyList) {
                    searchKeyList.add(searchkey.getKeyname());
                }
                activtyResponse = this.searchService.queryBySearchStr(searchKeyList);
            } else {
                activtyResponse.setStatus(Constants.FAILED);
                activtyResponse.setStatusCode(Constants.STATUS_FAILED);
                List<Object> listResponseMsg = new ArrayList<>();
                listResponseMsg.add("SearchController can't get search str");
                activtyResponse.setResponseMsg(listResponseMsg);
                logger.info("SearchController can't get search str");
            }
        } catch (Exception e) {
            activtyResponse.setStatus(Constants.FAILED);
            activtyResponse.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add("SearchController select By searchStr got exception:" + e.getMessage());
            activtyResponse.setResponseMsg(listResponseMsg);
            logger.error("SearchController select By searchStr got exception:" + e.getMessage());
        }finally {
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("total process time:" + Utils.formatTime(processTime));
            return activtyResponse;
        }
    }

    @Scheduled(cron = "${timeTask.cron}")
    public void autoSearch() throws Exception {
        try {
            ResponseInfo responseInfo = searchkeyService.queryAll();
            List<Searchkey> keyList = (List<Searchkey>) responseInfo.getResponseMsg();
            System.out.println("keyList:"+keyList.size());
            if (keyList.size()>0){
                List<String> searchKeyList = new ArrayList<>();
                for (Searchkey searchkey : keyList) {
                    searchKeyList.add(searchkey.getKeyname());
                }
                this.searchService.queryBySearchStr(searchKeyList);
            } else {
                logger.info("SearchController can't get search str");
            }
        } catch (Exception e) {
            logger.error("SearchController select By searchStr got exception:" + e.getMessage());
        }
    }

    @ApiOperation(notes = "检查未成功爬取的商品详细信息", value="manualCheckProductDetails")
    @RequestMapping(value = "manualCheckProductDetails", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo manualCheckProductDetails() {
        ResponseInfo activtyResponse = new ResponseInfo();
        long startTime = System.currentTimeMillis();
        try {
            logger.info("manualCheckProductDetails process start");
            Productdetails productdetails = new Productdetails();
            productdetails.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
            productdetails.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
            activtyResponse = this.productdetailsService.checkMissed(productdetails);
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("manual Check Product Details all finish")));
        } catch (Exception e) {
            logger.error("SearchController manual Check Product Details got exception:" + e.getMessage());
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("SearchController manual Check Product Details got exception:" + e.getCause())));
            List resultMessage = new ArrayList();
            resultMessage.add("SearchController manual Check Product Details got exception:" + e.getMessage());
            activtyResponse.setStatus(Constants.OPT_FAILED);
            activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            activtyResponse.setResponseMsg(resultMessage);
        } finally {
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("manualCheckProductDetails total process time:" + Utils.formatTime(processTime));
            emailLog.sendEmail();
            return activtyResponse;
        }
    }
    @Scheduled(cron = "${timeTask.cronProductDetails}")
    public void autoCheckProductDetails() throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            logger.info("autoCheckProductDetails process start");
            Productdetails productdetails = new Productdetails();
            productdetails.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
            productdetails.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
            this.productdetailsService.checkMissed(productdetails);
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("auto Check Product Details all finish")));
        } catch (Exception e) {
            logger.error("SearchController auto Check Product Details got exception:" + e.getMessage());
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("SearchController auto Check Product Details got exception:" + e.getCause())));
            List resultMessage = new ArrayList();
            resultMessage.add("SearchController auto Check Product Details got exception:" + e.getMessage());
        } finally {
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("autoCheckProductDetails total process time:" + Utils.formatTime(processTime));
            emailLog.sendEmail();
        }
    }

    @ApiOperation(notes = "检查未成功爬取的店铺详细信息", value="manualCheckShopInfo")
    @RequestMapping(value = "manualCheckShopInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo manualCheckShopInfo() throws Exception {
        ResponseInfo activtyResponse = new ResponseInfo();
        long startTime = System.currentTimeMillis();
        try {
            logger.info("manualCheckShopInfo process start");
            Shopinfo shopinfo = new Shopinfo();
            shopinfo.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
            shopinfo.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
            activtyResponse = this.shopinfoService.checkMissed(shopinfo);
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("manual Check Shop Info all finish")));
        } catch (Exception e) {
            logger.error("SearchController manual Check Shop Info got exception:" + e.getMessage());
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("SearchController manual Check Shop Info got exception:" + e.getCause())));
            List resultMessage = new ArrayList();
            resultMessage.add("SearchController manual Check Shop Info got exception:" + e.getMessage());
            activtyResponse.setStatus(Constants.OPT_FAILED);
            activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            activtyResponse.setResponseMsg(resultMessage);
        } finally {
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("manualCheckShopInfo total process time:" + Utils.formatTime(processTime));
            emailLog.sendEmail();
            return activtyResponse;
        }
    }
    @Scheduled(cron = "${timeTask.cronShopInfo}")
    public void autoCheckShopInfo() throws Exception {
        long startTime = System.currentTimeMillis();
        try {
            logger.info("autoCheckShopInfo process start");
            Shopinfo shopinfo = new Shopinfo();
            shopinfo.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
            shopinfo.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
            this.shopinfoService.checkMissed(shopinfo);
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("auto Check Shop Info all finish")));
        } catch (Exception e) {
            logger.error("SearchController auto Check Shop Info got exception:" + e.getMessage());
            emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("SearchController auto Check Shop Info got exception:" + e.getCause())));
            List resultMessage = new ArrayList();
            resultMessage.add("SearchController auto Check Shop Info got exception:" + e.getMessage());
        } finally {
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("autoCheckShopInfo total process time:" + Utils.formatTime(processTime));
            emailLog.sendEmail();
        }
    }
}

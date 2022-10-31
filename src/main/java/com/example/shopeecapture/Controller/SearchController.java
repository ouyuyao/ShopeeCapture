package com.example.shopeecapture.Controller;

import com.example.shopeecapture.Bean.Processlocks;
import com.example.shopeecapture.Bean.Productdetails;
import com.example.shopeecapture.Bean.Searchkey;
import com.example.shopeecapture.Bean.Shopinfo;
import com.example.shopeecapture.Mapper.ProcesslocksMapper;
import com.example.shopeecapture.Service.*;
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
    @Resource
    private ProcesslocksMapper processlocksMapper;
    @Autowired
    EmailLog emailLog;

    @ApiOperation(notes = "触发爬虫", value = "manualSearch")
    @RequestMapping(value = "manualSearch", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo manualSearch() throws Exception {
        ResponseInfo activtyResponse = new ResponseInfo();
        List<Object> listResponseMsg = new ArrayList<>();

        Processlocks processlocks = processlocksMapper.queryMainLockStatus();
        if ((null == processlocks) || ("F".equalsIgnoreCase(processlocks.getStatus()))) {
            emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("manualSearch start process::::::")));
            long startTime = System.currentTimeMillis();
            try {
                ResponseInfo responseInfo = searchkeyService.queryAll();
                List<Searchkey> keyList = (List<Searchkey>) responseInfo.getResponseMsg();
                if (keyList.size() > 0) {
                    List<String> searchKeyList = new ArrayList<>();
                    for (Searchkey searchkey : keyList) {
                        searchKeyList.add(searchkey.getKeyname());
                    }
                    activtyResponse = this.searchService.queryBySearchStr(searchKeyList);
                } else {
                    activtyResponse.setStatus(Constants.FAILED);
                    activtyResponse.setStatusCode(Constants.STATUS_FAILED);
                    listResponseMsg.add("SearchController can't get search str");
                    activtyResponse.setResponseMsg(listResponseMsg);
                    logger.info("SearchController can't get search str");
                }
            } catch (Exception e) {
                activtyResponse.setStatus(Constants.FAILED);
                activtyResponse.setStatusCode(Constants.STATUS_FAILED);
                listResponseMsg.add("SearchController select By searchStr got exception:" + e.getMessage());
                logger.error("SearchController select By searchStr got exception:" + e.getMessage());
            } finally {
                long endTime = System.currentTimeMillis();
                long processTime = endTime - startTime;
                listResponseMsg.add("processTime:" + processTime);
                activtyResponse.setResponseMsg(listResponseMsg);
                logger.info("manualSearch total process time:" + Utils.formatTime(processTime));
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("manualSearch total process time:" + Utils.formatTime(processTime))));
                emailLog.sendEmail("manualSearch-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                return activtyResponse;
            }
        } else {
            activtyResponse.setStatus(Constants.OPT_FAILED);
            activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            listResponseMsg.add("main flow still processing,can't start the manualSearch function:");
            activtyResponse.setResponseMsg(listResponseMsg);
            return activtyResponse;
        }
    }

    @Scheduled(cron = "${timeTask.cron}")
    public void autoSearch() throws Exception {
        Processlocks processlocks = processlocksMapper.queryMainLockStatus();
        int id = 0;
        if (null == processlocks) {
            Processlocks processlockInsert = new Processlocks();
            processlockInsert.setStatus("T");
            processlockInsert.setProcesslogic("main");
            processlockInsert.setLastupdatetimestamp(new Date());
            processlocksMapper.insert(processlockInsert);
            id = processlockInsert.getId();
        }else if ("F".equalsIgnoreCase(processlocks.getStatus())) {
            id = processlocks.getId();
            Processlocks processlockUpdate = new Processlocks();
            processlockUpdate.setId(processlocks.getId());
            processlockUpdate.setStatus("T");
            processlockUpdate.setProcesslogic("main");
            processlockUpdate.setLastupdatetimestamp(new Date());
            processlocksMapper.update(processlockUpdate);
        }else{
            id = processlocks.getId();
        }
        emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("autoSearch start process::::::")));
        long startTime = System.currentTimeMillis();
        logger.info("SearchController autoSearch start");
        try {
            ResponseInfo responseInfo = searchkeyService.queryAll();
            List<Searchkey> keyList = (List<Searchkey>) responseInfo.getResponseMsg();
            System.out.println("keyList:" + keyList.size());
            if (keyList.size() > 0) {
                List<String> searchKeyList = new ArrayList<>();
                for (Searchkey searchkey : keyList) {
                    searchKeyList.add(searchkey.getKeyname());
                }
                this.searchService.queryBySearchStr(searchKeyList);
                Thread.sleep(120000);
            } else {
                logger.info("SearchController can't get search str");
            }
        } catch (Exception e) {
            logger.error("SearchController select By searchStr got exception:" + e.getMessage());
        } finally {
            Processlocks processlockUpdate = new Processlocks();
            processlockUpdate.setId(id);
            processlockUpdate.setStatus("F");
            processlockUpdate.setProcesslogic("main");
            processlockUpdate.setLastupdatetimestamp(new Date());
            processlocksMapper.update(processlockUpdate);
            long endTime = System.currentTimeMillis();
            long processTime = endTime - startTime;
            logger.info("autoSearch total process time:" + Utils.formatTime(processTime));
            emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("autoSearch total process time:" + Utils.formatTime(processTime))));
            emailLog.sendEmail("autoSearch-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
        }
    }

    @ApiOperation(notes = "检查未成功爬取的商品详细信息", value = "manualCheckProductDetails")
    @RequestMapping(value = "manualCheckProductDetails", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo manualCheckProductDetails() {
        Processlocks processlocks = processlocksMapper.queryMainLockStatus();
        ResponseInfo activtyResponse = new ResponseInfo();
        List resultMessage = new ArrayList();
        if ((null == processlocks) || ("F".equalsIgnoreCase(processlocks.getStatus()))) {
            emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("manualCheckProductDetails start process::::::")));
            long startTime = System.currentTimeMillis();
            try {
                logger.info("manualCheckProductDetails process start");
                Productdetails productdetails = new Productdetails();
                productdetails.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
                productdetails.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
                activtyResponse = this.productdetailsService.checkMissed(productdetails);
                resultMessage.add("manual Check Product Details all finish");
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("manual Check Product Details all finish")));
            } catch (Exception e) {
                logger.error("SearchController manual Check Product Details got exception:" + e.getMessage());
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("SearchController manual Check Product Details got exception:" + e.getCause())));
                resultMessage.add("SearchController manual Check Product Details got exception:" + e.getMessage());
                activtyResponse.setStatus(Constants.OPT_FAILED);
                activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            } finally {
                long endTime = System.currentTimeMillis();
                long processTime = endTime - startTime;
                resultMessage.add("processTime:" + processTime);
                activtyResponse.setResponseMsg(resultMessage);
                logger.info("manualCheckProductDetails total process time:" + Utils.formatTime(processTime));
                emailLog.sendEmail("manualCheckProductDetails-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                return activtyResponse;
            }
        } else {
            activtyResponse.setStatus(Constants.OPT_FAILED);
            activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            resultMessage.add("main flow still processing,can't start the manualCheckProductDetails function:");
            activtyResponse.setResponseMsg(resultMessage);
            return activtyResponse;
        }
    }

    @Scheduled(cron = "${timeTask.cronProductDetails}")
    public void autoCheckProductDetails() throws Exception {
        logger.info("autoCheckProductDetails =-----------------");
        Processlocks processlocks = processlocksMapper.queryMainLockStatus();
        if ((null == processlocks) || ("F".equalsIgnoreCase(processlocks.getStatus()))) {
            emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("autoCheckProductDetails start process::::::")));
            long startTime = System.currentTimeMillis();
            try {
                logger.info("autoCheckProductDetails process start");
                Productdetails productdetails = new Productdetails();
                productdetails.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
                productdetails.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
                this.productdetailsService.checkMissed(productdetails);
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("auto Check Product Details all finish")));
            } catch (Exception e) {
                logger.error("SearchController auto Check Product Details got exception:" + e.getMessage());
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("SearchController auto Check Product Details got exception:" + e.getCause())));
            } finally {
                long endTime = System.currentTimeMillis();
                long processTime = endTime - startTime;
                logger.info("autoCheckProductDetails total process time:" + Utils.formatTime(processTime));
                emailLog.sendEmail("autoCheckProductDetails-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            }
        } else {
            logger.info("main flow still processing,can't start the autoCheckProductDetails function");
            System.out.println("main flow still processing,can't start the autoCheckProductDetails function");
        }
    }

    @ApiOperation(notes = "检查未成功爬取的店铺详细信息", value = "manualCheckShopInfo")
    @RequestMapping(value = "manualCheckShopInfo", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo manualCheckShopInfo() throws Exception {
        Processlocks processlocks = processlocksMapper.queryMainLockStatus();
        ResponseInfo activtyResponse = new ResponseInfo();
        List resultMessage = new ArrayList();
        if ((null == processlocks) || ("F".equalsIgnoreCase(processlocks.getStatus()))) {
            emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("manualCheckShopInfo start process::::::")));
            long startTime = System.currentTimeMillis();
            try {
                logger.info("manualCheckShopInfo process start");
                Shopinfo shopinfo = new Shopinfo();
                shopinfo.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
                shopinfo.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
                activtyResponse = this.shopinfoService.checkMissed(shopinfo);
                resultMessage.add("manual Check Shop Info all finish");
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("manual Check Shop Info all finish")));
            } catch (Exception e) {
                logger.error("SearchController manual Check Shop Info got exception:" + e.getMessage());
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("SearchController manual Check Shop Info got exception:" + e.getCause())));
                resultMessage.add("SearchController manual Check Shop Info got exception:" + e.getMessage());
                activtyResponse.setStatus(Constants.OPT_FAILED);
                activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            } finally {
                long endTime = System.currentTimeMillis();
                long processTime = endTime - startTime;
                resultMessage.add("processTime:" + processTime);
                activtyResponse.setResponseMsg(resultMessage);
                logger.info("manualCheckShopInfo total process time:" + Utils.formatTime(processTime));
                emailLog.sendEmail("manualCheckShopInfo-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                return activtyResponse;
            }
        } else {
            activtyResponse.setStatus(Constants.OPT_FAILED);
            activtyResponse.setStatusCode(Constants.STATUS_SUCCESS);
            resultMessage.add("main flow still processing,can't start the manualCheckShopInfo function:");
            activtyResponse.setResponseMsg(resultMessage);
            return activtyResponse;
        }
    }

    @Scheduled(cron = "${timeTask.cronShopInfo}")
    public void autoCheckShopInfo() throws Exception {
        logger.info("autoCheckShopInfo =-----------------");
        Processlocks processlocks = processlocksMapper.queryMainLockStatus();
        if ((null == processlocks) || ("F".equalsIgnoreCase(processlocks.getStatus()))) {
            emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("autoCheckShopInfo start process::::::")));
            long startTime = System.currentTimeMillis();
            try {
                logger.info("autoCheckShopInfo process start");
                Shopinfo shopinfo = new Shopinfo();
                shopinfo.setMin_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00")));
                shopinfo.setMax_insertTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(DateFormatUtils.format(new Date(), "yyyy-MM-dd 23:59:59")));
                this.shopinfoService.checkMissed(shopinfo);
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("auto Check Shop Info all finish")));
            } catch (Exception e) {
                logger.error("SearchController auto Check Shop Info got exception:" + e.getMessage());
                emailLog.log(new EmailMessageBean(this.getClass().getName(), Thread.currentThread().getName(), ("SearchController auto Check Shop Info got exception:" + e.getCause())));
                List resultMessage = new ArrayList();
                resultMessage.add("SearchController auto Check Shop Info got exception:" + e.getMessage());
            } finally {
                long endTime = System.currentTimeMillis();
                long processTime = endTime - startTime;
                logger.info("autoCheckShopInfo total process time:" + Utils.formatTime(processTime));
                emailLog.sendEmail("autoCheckShopInfo-" + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
            }
        } else {
            logger.info("main flow still processing,can't start the autoCheckShopInfo function");
            System.out.println("main flow still processing,can't start the autoCheckShopInfo function");
        }
    }
}

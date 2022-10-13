package com.example.shopeecapture.Service;

import com.example.shopeecapture.Utils.Utils;
import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import com.example.shopeecapture.config.Email.EmailLog;
import com.example.shopeecapture.config.Email.EmailMessageBean;
import org.slf4j.*;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Shopinfo)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 17:20:36
 */
@Service("shopinfoService")
public class ShopinfoService {
    @Resource
    private ShopinfoMapper shopinfoMapper;

    @Resource
    private AsyncThreadTaskService asyncThreadTaskService;

    @Autowired
    EmailLog emailLog;

    static final Logger logger = LoggerFactory.getLogger(ShopinfoService.class);

    /**
     * 查当天爬取时，有没有漏数据没插入数据库
     *
     * @param shopinfo 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo checkMissed(Shopinfo shopinfo)  throws Exception{
        long startTime = System.currentTimeMillis();
        int finishCount = 0;
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), "Process Shopinfo checkMissed()......."));
        logger.info("Process Shopinfo checkMissed().......");
        logger.info("Shopinfo......." + shopinfo.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        List<Shopinfo> list = this.shopinfoMapper.checkMissed(shopinfo);
        logger.info("shopinfo checkMissed size......."+list.size());
        List<Future<String>> futureList=new ArrayList<>();
        if (list.size()>0){
            for (Shopinfo info : list){
                CountDownLatch countDownLatchForShopInfos = new CountDownLatch(list.size());
                Future<String> asyncResult = asyncThreadTaskService.checkMissedForShopInfos(info.getEventid()+"-1",info.getShopid(),countDownLatchForShopInfos, shopinfoMapper);
                String runResult = asyncResult.get(10, TimeUnit.MINUTES);
                logger.info("runResult:"+runResult);
                if ("1".equals(runResult)){
                    finishCount = finishCount + 1;
                }
                futureList.add(asyncResult);
            }
        }
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
        long endTime = System.currentTimeMillis();
        long processTime = endTime - startTime;
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("checkMissedForShopInfos process time:" + Utils.formatTime(processTime))));
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("totalRecordCount:"+list.size())));
        emailLog.log(new EmailMessageBean(this.getClass().getName(),Thread.currentThread().getName(), ("totalfinishCount:"+finishCount)));
        List resultMessage = new ArrayList();
        resultMessage.add("totalRecordCount:"+list.size());
        resultMessage.add("totalfinishCount:"+finishCount);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(resultMessage);
        return responseInfo;
    }

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Shopinfo queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Shopinfo> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Shopinfo shopinfo = this.shopinfoMapper.queryById(id);
        listResponseMsg.add(shopinfo);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    public ResponseInfo queryAllByLimit(int offset, int rowCount) {
        logger.info("Process Shopinfo queryAllByLimit().......");
        logger.info("offset:" + offset + ",rowCount:" + rowCount);
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Shopinfo> list = this.shopinfoMapper.queryAllByLimit(offset, rowCount);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param shopinfo 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo queryByFilters(Shopinfo shopinfo) {
        logger.info("Process Shopinfo queryByFilters().......");
        logger.info("Shopinfo......." + shopinfo.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Shopinfo> list = this.shopinfoMapper.queryByFilters(shopinfo);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param shopinfo 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Shopinfo shopinfo) {
        logger.info("Process Shopinfo insert().......");
        logger.info("Shopinfo......." + shopinfo.toString());
        //修改数据库中的数据
        int count = this.shopinfoMapper.insert(shopinfo);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Shopinfo> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Shopinfo afterItem = this.shopinfoMapper.queryById(shopinfo.getId());
            listResponseMsg.add(afterItem);
            responseInfo.setStatus(Constants.OPT_SUCCESS);
            responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        }
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }

    /**
     * 修改数据
     *
     * @param shopinfo 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Shopinfo shopinfo) {
        logger.info("Process Shopinfo update().......");
        logger.info("shopinfo......." + shopinfo.toString());
        //修改数据库中的数据
        int count = this.shopinfoMapper.update(shopinfo);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Shopinfo> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Shopinfo afterItem = this.shopinfoMapper.queryById(shopinfo.getId());
            listResponseMsg.add(afterItem);
            responseInfo.setStatus(Constants.OPT_SUCCESS);
            responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        }
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    public ResponseInfo deleteById(Integer id) {
        logger.info("Process Shopinfo deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.shopinfoMapper.deleteById(id);
        if (count != 0) {
            responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
            listResponseMsg.add(Constants.DELETE_SUCCESS);
        } else {
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            listResponseMsg.add(Constants.DELETE_FAILED);
        }
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }
}

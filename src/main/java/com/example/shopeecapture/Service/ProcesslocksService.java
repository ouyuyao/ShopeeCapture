package com.example.shopeecapture.Service;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import org.slf4j.*;

import java.util.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Processlocks)表服务实现类
 *
 * @author makejava
 * @since 2022-10-29 10:33:52
 */
@Service("processlocksService")
public class ProcesslocksService {
    @Resource
    private ProcesslocksMapper processlocksMapper;

    static final Logger logger = LoggerFactory.getLogger(ProcesslocksService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Processlocks queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Processlocks> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Processlocks processlocks = this.processlocksMapper.queryById(id);
        listResponseMsg.add(processlocks);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }

    /**
     * 获取爬虫主流程的最新一条信息
     *
     * @return 实例对象
     */
    public ResponseInfo queryMainLockStatus() {
        logger.info("Process Processlocks queryMainLockStatus().......");
        ResponseInfo responseInfo = new ResponseInfo();
        List<Processlocks> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Processlocks processlocks = this.processlocksMapper.queryMainLockStatus();
        listResponseMsg.add(processlocks);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param processlocks 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Processlocks processlocks) {
        logger.info("Process Processlocks insert().......");
        logger.info("Processlocks......." + processlocks.toString());
        //修改数据库中的数据
        int count = this.processlocksMapper.insert(processlocks);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Processlocks> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Processlocks afterItem = this.processlocksMapper.queryById(processlocks.getId());
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
     * @param processlocks 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Processlocks processlocks) {
        logger.info("Process Processlocks update().......");
        logger.info("processlocks......." + processlocks.toString());
        //修改数据库中的数据
        int count = this.processlocksMapper.update(processlocks);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Processlocks> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Processlocks afterItem = this.processlocksMapper.queryById(processlocks.getId());
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
        logger.info("Process Processlocks deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.processlocksMapper.deleteById(id);
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

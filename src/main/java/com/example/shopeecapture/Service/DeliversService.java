package com.example.shopeecapture.Service;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import org.slf4j.*;

import java.util.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Delivers)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 19:19:31
 */
@Service("deliversService")
public class DeliversService {
    @Resource
    private DeliversMapper deliversMapper;

    static final Logger logger = LoggerFactory.getLogger(DeliversService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Delivers queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Delivers> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Delivers delivers = this.deliversMapper.queryById(id);
        listResponseMsg.add(delivers);
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
        logger.info("Process Delivers queryAllByLimit().......");
        logger.info("offset:" + offset + ",rowCount:" + rowCount);
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Delivers> list = this.deliversMapper.queryAllByLimit(offset, rowCount);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param delivers 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo queryByFilters(Delivers delivers) {
        logger.info("Process Delivers queryByFilters().......");
        logger.info("Delivers......." + delivers.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Delivers> list = this.deliversMapper.queryByFilters(delivers);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param delivers 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Delivers delivers) {
        logger.info("Process Delivers insert().......");
        logger.info("Delivers......." + delivers.toString());
        //修改数据库中的数据
        int count = this.deliversMapper.insert(delivers);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Delivers> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Delivers afterItem = this.deliversMapper.queryById(delivers.getId());
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
     * @param delivers 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Delivers delivers) {
        logger.info("Process Delivers update().......");
        logger.info("delivers......." + delivers.toString());
        //修改数据库中的数据
        int count = this.deliversMapper.update(delivers);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Delivers> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Delivers afterItem = this.deliversMapper.queryById(delivers.getId());
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
        logger.info("Process Delivers deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.deliversMapper.deleteById(id);
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

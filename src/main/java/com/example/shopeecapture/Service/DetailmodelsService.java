package com.example.shopeecapture.Service;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import org.slf4j.*;

import java.util.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Detailmodels)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 17:14:57
 */
@Service("detailmodelsService")
public class DetailmodelsService {
    @Resource
    private DetailmodelsMapper detailmodelsMapper;

    static final Logger logger = LoggerFactory.getLogger(DetailmodelsService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Detailmodels queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Detailmodels> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Detailmodels detailmodels = this.detailmodelsMapper.queryById(id);
        listResponseMsg.add(detailmodels);
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
        logger.info("Process Detailmodels queryAllByLimit().......");
        logger.info("offset:" + offset + ",rowCount:" + rowCount);
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Detailmodels> list = this.detailmodelsMapper.queryAllByLimit(offset, rowCount);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param detailmodels 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo queryByFilters(Detailmodels detailmodels) {
        logger.info("Process Detailmodels queryByFilters().......");
        logger.info("Detailmodels......." + detailmodels.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Detailmodels> list = this.detailmodelsMapper.queryByFilters(detailmodels);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param detailmodels 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Detailmodels detailmodels) {
        logger.info("Process Detailmodels insert().......");
        logger.info("Detailmodels......." + detailmodels.toString());
        //修改数据库中的数据
        int count = this.detailmodelsMapper.insert(detailmodels);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Detailmodels> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Detailmodels afterItem = this.detailmodelsMapper.queryById(detailmodels.getId());
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
     * @param detailmodels 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Detailmodels detailmodels) {
        logger.info("Process Detailmodels update().......");
        logger.info("detailmodels......." + detailmodels.toString());
        //修改数据库中的数据
        int count = this.detailmodelsMapper.update(detailmodels);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Detailmodels> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Detailmodels afterItem = this.detailmodelsMapper.queryById(detailmodels.getId());
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
        logger.info("Process Detailmodels deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.detailmodelsMapper.deleteById(id);
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

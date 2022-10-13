package com.example.shopeecapture.Service;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import org.slf4j.*;

import java.util.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Detailshopvouchers)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 17:15:16
 */
@Service("detailshopvouchersService")
public class DetailshopvouchersService {
    @Resource
    private DetailshopvouchersMapper detailshopvouchersMapper;

    static final Logger logger = LoggerFactory.getLogger(DetailshopvouchersService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Detailshopvouchers queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Detailshopvouchers> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Detailshopvouchers detailshopvouchers = this.detailshopvouchersMapper.queryById(id);
        listResponseMsg.add(detailshopvouchers);
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
        logger.info("Process Detailshopvouchers queryAllByLimit().......");
        logger.info("offset:" + offset + ",rowCount:" + rowCount);
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Detailshopvouchers> list = this.detailshopvouchersMapper.queryAllByLimit(offset, rowCount);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param detailshopvouchers 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo queryByFilters(Detailshopvouchers detailshopvouchers) {
        logger.info("Process Detailshopvouchers queryByFilters().......");
        logger.info("Detailshopvouchers......." + detailshopvouchers.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Detailshopvouchers> list = this.detailshopvouchersMapper.queryByFilters(detailshopvouchers);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param detailshopvouchers 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Detailshopvouchers detailshopvouchers) {
        logger.info("Process Detailshopvouchers insert().......");
        logger.info("Detailshopvouchers......." + detailshopvouchers.toString());
        //修改数据库中的数据
        int count = this.detailshopvouchersMapper.insert(detailshopvouchers);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Detailshopvouchers> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Detailshopvouchers afterItem = this.detailshopvouchersMapper.queryById(detailshopvouchers.getId());
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
     * @param detailshopvouchers 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Detailshopvouchers detailshopvouchers) {
        logger.info("Process Detailshopvouchers update().......");
        logger.info("detailshopvouchers......." + detailshopvouchers.toString());
        //修改数据库中的数据
        int count = this.detailshopvouchersMapper.update(detailshopvouchers);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Detailshopvouchers> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Detailshopvouchers afterItem = this.detailshopvouchersMapper.queryById(detailshopvouchers.getId());
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
        logger.info("Process Detailshopvouchers deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.detailshopvouchersMapper.deleteById(id);
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

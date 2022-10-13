package com.example.shopeecapture.Service;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import org.slf4j.*;

import java.util.*;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Products)表服务实现类
 *
 * @author makejava
 * @since 2022-09-23 17:15:43
 */
@Service("productsService")
public class ProductsService {
    @Resource
    private ProductsMapper productsMapper;

    static final Logger logger = LoggerFactory.getLogger(ProductsService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Products queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Products> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Products products = this.productsMapper.queryById(id);
        listResponseMsg.add(products);
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
        logger.info("Process Products queryAllByLimit().......");
        logger.info("offset:" + offset + ",rowCount:" + rowCount);
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Products> list = this.productsMapper.queryAllByLimit(offset, rowCount);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param products 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo queryByFilters(Products products) {
        logger.info("Process Products queryByFilters().......");
        logger.info("Products......." + products.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Products> list = this.productsMapper.queryByFilters(products);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param products 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Products products) {
        logger.info("Process Products insert().......");
        logger.info("Products......." + products.toString());
        //修改数据库中的数据
        int count = this.productsMapper.insert(products);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Products> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Products afterItem = this.productsMapper.queryById(products.getId());
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
     * @param products 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Products products) {
        logger.info("Process Products update().......");
        logger.info("products......." + products.toString());
        //修改数据库中的数据
        int count = this.productsMapper.update(products);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Products> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Products afterItem = this.productsMapper.queryById(products.getId());
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
        logger.info("Process Products deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.productsMapper.deleteById(id);
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

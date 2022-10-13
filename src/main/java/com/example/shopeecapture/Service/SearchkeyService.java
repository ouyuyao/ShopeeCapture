package com.example.shopeecapture.Service;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.Bean.*;
import com.example.shopeecapture.Mapper.*;
import com.example.shopeecapture.config.Email.EmailLog;
import com.example.shopeecapture.config.Email.EmailMessageBean;
import org.slf4j.*;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * (Searchkey)表服务实现类
 *
 * @author makejava
 * @since 2022-10-09 15:10:20
 */
@Service("searchkeyService")
public class SearchkeyService {
    @Resource
    private SearchkeyMapper searchkeyMapper;

    static final Logger logger = LoggerFactory.getLogger(SearchkeyService.class);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    public ResponseInfo queryById(Integer id) {
        logger.info("Process Searchkey queryById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Searchkey> listResponseMsg = new ArrayList<>();
        //得到对象
        logger.info("using database data.......");
        Searchkey searchkey = this.searchkeyMapper.queryById(id);
        listResponseMsg.add(searchkey);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(listResponseMsg);
        return responseInfo;
    }

    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    public ResponseInfo queryAll() {
        logger.info("Process Searchkey queryAll().......");
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Searchkey> list = this.searchkeyMapper.queryAll();
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
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
        logger.info("Process Searchkey queryAllByLimit().......");
        logger.info("offset:" + offset + ",rowCount:" + rowCount);
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Searchkey> list = this.searchkeyMapper.queryAllByLimit(offset, rowCount);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param searchkey 查询对象的值
     * @return 对象列表
     */
    public ResponseInfo queryByFilters(Searchkey searchkey) {
        logger.info("Process Searchkey queryByFilters().......");
        logger.info("Searchkey......." + searchkey.toString());
        ResponseInfo responseInfo = new ResponseInfo();
        //得到集合
        logger.info("using database data.......");
        List<Searchkey> list = this.searchkeyMapper.queryByFilters(searchkey);
        logger.info("list......." + list);
        responseInfo.setStatus(Constants.OPT_SUCCESS);
        responseInfo.setStatusCode(Constants.STATUS_SUCCESS);
        responseInfo.setResponseMsg(list);
        return responseInfo;
    }

    /**
     * 新增数据
     *
     * @param searchkey 实例对象
     * @return 实例对象
     */
    public ResponseInfo insert(Searchkey searchkey) {
        logger.info("Process Searchkey insert().......");
        logger.info("Searchkey......." + searchkey.toString());
        //修改数据库中的数据
        int count = this.searchkeyMapper.insert(searchkey);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Searchkey> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Searchkey afterItem = this.searchkeyMapper.queryById(searchkey.getId());
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
     * @param searchkey 实例对象
     * @return 实例对象
     */
    public ResponseInfo update(Searchkey searchkey) {
        logger.info("Process Searchkey update().......");
        logger.info("searchkey......." + searchkey.toString());
        //修改数据库中的数据
        int count = this.searchkeyMapper.update(searchkey);
        ResponseInfo responseInfo = new ResponseInfo();
        List<Searchkey> listResponseMsg = new ArrayList<>();
        if (count <= 0) {
            responseInfo.setStatus(Constants.OPT_FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
        } else {
            Searchkey afterItem = this.searchkeyMapper.queryById(searchkey.getId());
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
        logger.info("Process Searchkey deleteById().......");
        logger.info("id:" + id);
        ResponseInfo responseInfo = new ResponseInfo();
        List<String> listResponseMsg = new ArrayList<>();
        //删除数据库中的数据
        int count = this.searchkeyMapper.deleteById(id);
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

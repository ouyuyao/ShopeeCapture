package com.example.shopeecapture.Controller;

import com.example.shopeecapture.Bean.Searchkey;
import com.example.shopeecapture.Service.SearchkeyService;

import javax.annotation.Resource;

import com.example.shopeecapture.config.*;
import com.example.shopeecapture.config.Email.EmailLog;
import com.example.shopeecapture.config.Email.EmailMessageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import org.slf4j.*;

import java.util.*;
import java.text.*;

/**
 * (Searchkey)表控制层
 *
 * @author makejava
 * @since 2022-10-09 15:10:12
 */

@RestController
@RequestMapping("searchkey")
@Api(value = "SearchkeyController", description = "操作Searchkey表相关数据")
public class SearchkeyController {

    static final Logger logger = LoggerFactory.getLogger(SearchkeyController.class);

    /**
     * 服务对象
     */
    @Resource
    private SearchkeyService searchkeyService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(notes = "通过主键查询单条Searchkey数据", value = "Select Searchkey By Id")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "selectById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo selectById(Integer id) {
        try {
            logger.info("SearchkeyController select By Id:" + id);
            return this.searchkeyService.queryById(id);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

    /**
     * 查询全部数据
     *
     * @return 对象列表
     */
    @ApiOperation(notes = "查询Searchkey全部数据", value = "Select all from Searchkey")
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo queryAll() {
        try {
            return this.searchkeyService.queryAll();
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    @ApiOperation(notes = "分页查询Searchkey数据", value = "Select Searchkey with offset and rowCount")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "offset", value = "offset", required = true, dataType = "int"),
            @ApiImplicitParam(paramType = "query", name = "rowCount", value = "rowCount", required = true, dataType = "int")
    })
    @RequestMapping(value = "queryAllByLimit", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo queryAllByLimit(int offset, int rowCount) {
        try {
            logger.info("select Searchkey By offset:" + offset + ",rowCount:" + rowCount);
            return this.searchkeyService.queryAllByLimit(offset, rowCount);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

    /**
     * 根据传入值查询多条数据
     *
     * @param id
     * @param keyname
     * @return 对象列表
     */
    @ApiOperation(notes = "根据传入值查询Searchkey数据", value = "Select Searchkey with filters")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "", required = false, dataType = "Integer"),
            @ApiImplicitParam(paramType = "query", name = "keyname", value = "", required = false, dataType = "String")
    })
    @RequestMapping(value = "queryByFilters", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo queryByFilters(Integer id, String keyname) {
        try {
            Searchkey searchkey = new Searchkey();
            searchkey.setId(id);
            searchkey.setKeyname(keyname);
            logger.info("SearchkeyController select By Filters:" + searchkey);
            return this.searchkeyService.queryByFilters(searchkey);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

    /**
     * 新增数据
     *
     * @param keyname
     * @return 实例对象
     */
    @ApiOperation(notes = "根据传入值新增Searchkey数据", value = "Insert new Searchkey")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "keyname", value = "", required = false, dataType = "String")
    })
    @RequestMapping(value = "insert", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo insert(String keyname) throws ParseException {
        try {
            Searchkey searchkey = new Searchkey();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String updateDateString = simpleDateFormat.format(new Date());
            java.util.Date timeDate = simpleDateFormat.parse(updateDateString);
            searchkey.setKeyname(keyname);
            logger.info("SearchkeyController insert By searchkey:" + searchkey);
            return this.searchkeyService.insert(searchkey);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

    /**
     * 修改数据
     *
     * @param id
     * @param keyname
     * @return 实例对象
     */
    @ApiOperation(notes = "根据传入值修改Searchkey数据", value = "Update new Searchkey")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "", required = true, dataType = "Integer")
            , @ApiImplicitParam(paramType = "query", name = "keyname", value = "", required = false, dataType = "String")
    })
    @RequestMapping(value = "update", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo update(Integer id, String keyname) throws ParseException {
        try {
            Searchkey searchkey = new Searchkey();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String updateDateString = simpleDateFormat.format(new Date());
            java.util.Date timeDate = simpleDateFormat.parse(updateDateString);
            searchkey.setId(id);
            searchkey.setKeyname(keyname);
            logger.info("update SearchkeyController By searchkey:" + searchkey);
            return this.searchkeyService.update(searchkey);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

    /**
     * 通过主键删除单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @ApiOperation(notes = "通过主键删除单条Searchkey数据", value = "Delete Searchkey By Id")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "id", value = "", required = true, dataType = "Integer")
    })
    @RequestMapping(value = "deleteById", method = RequestMethod.GET)
    @ResponseBody
    public ResponseInfo deleteById(Integer id) {
        try {
            logger.info("SearchkeyController delete By Id:" + id);
            return this.searchkeyService.deleteById(id);
        } catch (Exception e) {
            ResponseInfo responseInfo = new ResponseInfo();
            responseInfo.setStatus(Constants.FAILED);
            responseInfo.setStatusCode(Constants.STATUS_FAILED);
            List<Object> listResponseMsg = new ArrayList<>();
            listResponseMsg.add(e.getClass());
            logger.error("exception", (Object) e.getStackTrace());
            responseInfo.setResponseMsg(listResponseMsg);
            return responseInfo;
        }
    }

}

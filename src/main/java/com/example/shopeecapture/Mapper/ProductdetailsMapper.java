package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Productdetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Productdetails)表服务接口
 *
 * @author makejava
 * @since 2022-09-23 17:15:29
 */
@Mapper
public interface ProductdetailsMapper {

    /**
     * 查当天爬取时，有没有漏数据没插入数据库
     *
     * @param productdetails 查询对象的值
     * @return 对象列表
     */
    List<Productdetails> checkMissed(Productdetails productdetails);

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Productdetails queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    List<Productdetails> queryAllByLimit(int offset, int rowCount);

    /**
     * 根据传入值查询多条数据
     *
     * @param productdetails 查询对象的值
     * @return 对象列表
     */
    List<Productdetails> queryByFilters(Productdetails productdetails);

    /**
     * 新增数据
     *
     * @param productdetails 实例对象
     * @return 实例对象
     */
    int insert(Productdetails productdetails);

    /**
     * 修改数据
     *
     * @param productdetails 实例对象
     * @return 实例对象
     */
    int update(Productdetails productdetails);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}

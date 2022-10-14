package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Products;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Products)表服务接口
 *
 * @author makejava
 * @since 2022-09-23 17:15:43
 */
@Mapper
public interface ProductsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Products queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    List<Products> queryAllByLimit(int offset, int rowCount);

    /**
     * 根据传入值查询多条数据
     *
     * @param products 查询对象的值
     * @return 对象列表
     */
    List<Products> queryByFilters(Products products);

    /**
     * 新增数据
     *
     * @param products 实例对象
     * @return 实例对象
     */
    int insert(Products products);

    /**
     * 修改数据
     *
     * @param products 实例对象
     * @return 实例对象
     */
    int update(Products products);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

    /**
     * 通过itemid shopid删除数据
     *
     * @param itemid itemid
     * @param shopid shopid
     * @return 是否成功
     */
    int deleteByItemAndShopId(String itemid, String shopid);

}

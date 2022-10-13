package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Searchkey;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * (Searchkey)表服务接口
 *
 * @author makejava
 * @since 2022-10-09 15:10:15
 */
@Mapper
public interface SearchkeyMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Searchkey queryById(Integer id);

    /**
     * 查询全部数据
     *
     * @return 实例对象
     */
    List<Searchkey> queryAll();

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    List<Searchkey> queryAllByLimit(int offset, int rowCount);

    /**
     * 根据传入值查询多条数据
     *
     * @param searchkey 查询对象的值
     * @return 对象列表
     */
    List<Searchkey> queryByFilters(Searchkey searchkey);

    /**
     * 新增数据
     *
     * @param searchkey 实例对象
     * @return 实例对象
     */
    int insert(Searchkey searchkey);

    /**
     * 修改数据
     *
     * @param searchkey 实例对象
     * @return 实例对象
     */
    int update(Searchkey searchkey);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}

package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Delivers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * (Delivers)表服务接口
 *
 * @author makejava
 * @since 2022-09-23 19:19:30
 */
@Mapper
public interface DeliversMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Delivers queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    List<Delivers> queryAllByLimit(int offset, int rowCount);

    /**
     * 根据传入值查询多条数据
     *
     * @param delivers 查询对象的值
     * @return 对象列表
     */
    List<Delivers> queryByFilters(Delivers delivers);

    /**
     * 新增数据
     *
     * @param delivers 实例对象
     * @return 实例对象
     */
    int insert(Delivers delivers);

    /**
     * 修改数据
     *
     * @param delivers 实例对象
     * @return 实例对象
     */
    int update(Delivers delivers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}

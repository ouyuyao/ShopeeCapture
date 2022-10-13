package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Detailmodels;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Detailmodels)表服务接口
 *
 * @author makejava
 * @since 2022-09-23 17:14:56
 */
@Mapper
public interface DetailmodelsMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Detailmodels queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    List<Detailmodels> queryAllByLimit(int offset, int rowCount);

    /**
     * 根据传入值查询多条数据
     *
     * @param detailmodels 查询对象的值
     * @return 对象列表
     */
    List<Detailmodels> queryByFilters(Detailmodels detailmodels);

    /**
     * 新增数据
     *
     * @param detailmodels 实例对象
     * @return 实例对象
     */
    int insert(Detailmodels detailmodels);

    /**
     * 修改数据
     *
     * @param detailmodels 实例对象
     * @return 实例对象
     */
    int update(Detailmodels detailmodels);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}

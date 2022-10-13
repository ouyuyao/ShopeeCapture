package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Detailshopvouchers;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * (Detailshopvouchers)表服务接口
 *
 * @author makejava
 * @since 2022-09-23 17:15:15
 */
@Mapper
public interface DetailshopvouchersMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Detailshopvouchers queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset   查询起始位置
     * @param rowCount 查询条数
     * @return 对象列表
     */
    List<Detailshopvouchers> queryAllByLimit(int offset, int rowCount);

    /**
     * 根据传入值查询多条数据
     *
     * @param detailshopvouchers 查询对象的值
     * @return 对象列表
     */
    List<Detailshopvouchers> queryByFilters(Detailshopvouchers detailshopvouchers);

    /**
     * 新增数据
     *
     * @param detailshopvouchers 实例对象
     * @return 实例对象
     */
    int insert(Detailshopvouchers detailshopvouchers);

    /**
     * 修改数据
     *
     * @param detailshopvouchers 实例对象
     * @return 实例对象
     */
    int update(Detailshopvouchers detailshopvouchers);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}

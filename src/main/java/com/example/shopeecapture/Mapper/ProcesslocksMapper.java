package com.example.shopeecapture.Mapper;

import com.example.shopeecapture.Bean.Processlocks;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

/**
 * (Processlocks)表服务接口
 *
 * @author makejava
 * @since 2022-10-29 10:33:49
 */
@Mapper
public interface ProcesslocksMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    Processlocks queryById(Integer id);

    /**
     * 获取爬虫主流程的最新一条信息
     *
     * @return 对象列表
     */
    Processlocks queryMainLockStatus();

    /**
     * 新增数据
     *
     * @param processlocks 实例对象
     * @return 实例对象
     */
    int insert(Processlocks processlocks);

    /**
     * 修改数据
     *
     * @param processlocks 实例对象
     * @return 实例对象
     */
    int update(Processlocks processlocks);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    int deleteById(Integer id);

}

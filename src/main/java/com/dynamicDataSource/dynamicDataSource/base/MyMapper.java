package com.dynamicDataSource.dynamicDataSource.base;

import tk.mybatis.mapper.common.BaseMapper;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 11:37
 * Modified By:
 */
public interface MyMapper<T> extends Mapper<T>, MySqlMapper<T>, BaseMapper<T> {
}

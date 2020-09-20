package com.dynamicDataSource.dynamicDataSource.service;

import com.dynamicDataSource.dynamicDataSource.entity.SysUserDO;

import java.util.List;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 10:48
 * Modified By:
 */
public interface DemoService {

    /**
     * 获取用户列表 走类的数据源
     *
     * @return 用户信息
     */
    List<SysUserDO> getSysUserList();

    /**
     * 获取用户列表 方法指定数据源
     *
     * @return 用户信息
     */
    List<SysUserDO> getSysUserList1();
}

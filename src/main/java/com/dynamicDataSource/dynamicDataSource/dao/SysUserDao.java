package com.dynamicDataSource.dynamicDataSource.dao;

import com.dynamicDataSource.dynamicDataSource.base.MyMapper;
import com.dynamicDataSource.dynamicDataSource.entity.SysUserDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 10:58
 * Modified By:
 */
@Repository
public interface SysUserDao extends MyMapper<SysUserDO> {

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    SysUserDO selectUserById(@Param("id") Integer id);
}

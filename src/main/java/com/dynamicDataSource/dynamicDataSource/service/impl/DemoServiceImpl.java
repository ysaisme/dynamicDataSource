package com.dynamicDataSource.dynamicDataSource.service.impl;

import com.dynamicDataSource.dynamicDataSource.annotations.DataSource;
import com.dynamicDataSource.dynamicDataSource.dao.SysUserDao;
import com.dynamicDataSource.dynamicDataSource.entity.SysUserDO;
import com.dynamicDataSource.dynamicDataSource.service.DemoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 10:49
 * Modified By:
 */
@Service
@Slf4j
@DataSource("slave1")
public class DemoServiceImpl implements DemoService {

    @Autowired
    private SysUserDao sysUserDao;

    @Override
    public List<SysUserDO> getSysUserList() {
        log.info("-> 获取用户列表");
        return sysUserDao.select(SysUserDO.builder()
                .removeFlag(Boolean.FALSE)
                .build());
    }

    @Override
    @DataSource()
    public List<SysUserDO> getSysUserList1() {
        log.info("-> 获取用户列表1");
        return sysUserDao.select(SysUserDO.builder()
                .removeFlag(Boolean.FALSE)
                .build());
    }
}

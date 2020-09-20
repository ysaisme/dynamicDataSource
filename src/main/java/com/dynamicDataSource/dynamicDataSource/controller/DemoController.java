package com.dynamicDataSource.dynamicDataSource.controller;

import com.dynamicDataSource.dynamicDataSource.service.DemoService;
import com.dynamicDataSource.dynamicDataSource.untils.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 10:40
 * Modified By:
 */
@RequestMapping("/demo")
@RestController
@Slf4j
public class DemoController {

    @Autowired
    private DemoService demoService;

    @PostMapping("/getSysUserList")
    public BaseResult getSysUserList() {
        return BaseResult.success(demoService.getSysUserList());
    }

    @PostMapping("/getSysUserList1")
    public BaseResult getSysUserList1() {
        return BaseResult.success(demoService.getSysUserList1());
    }
}

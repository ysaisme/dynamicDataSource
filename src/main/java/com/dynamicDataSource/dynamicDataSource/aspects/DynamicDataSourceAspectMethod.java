package com.dynamicDataSource.dynamicDataSource.aspects;

import com.dynamicDataSource.dynamicDataSource.annotations.DataSource;
import com.dynamicDataSource.dynamicDataSource.dynamic.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: ysd
 * @Description: 方法上使用注解处理
 * @Date: Created in 2020/9/20 11:47
 * Modified By:
 */
@Slf4j
@Aspect
@Order(2)
@Component
public class DynamicDataSourceAspectMethod {

    /**
     * 方法切面
     *
     * @param point
     */
    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint point, DataSource ds) throws Throwable {
        String dsId = ds.value();
        if (DynamicDataSourceContextHolder.dataSourceIds.contains(dsId)) {
            if (!dsId.equals(DynamicDataSourceContextHolder.getDataSourceRouterKey())) {
                DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
            }
//            log.debug("Use DataSource :{} >", dsId, point.getSignature());
        } else {
            log.info("数据源[{}]不存在，使用默认数据源 >{}", dsId, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
        }
    }

    @After("@annotation(ds)")
    public void restoreDataSource(DataSource ds) {
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
    }
}

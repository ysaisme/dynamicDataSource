package com.dynamicDataSource.dynamicDataSource.aspects;

import com.dynamicDataSource.dynamicDataSource.annotations.DataSource;
import com.dynamicDataSource.dynamicDataSource.dynamic.DynamicDataSourceContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @Author: ysd
 * @Description: 类上使用注解处理
 * @Date: Created in 2020/9/20 11:47
 * Modified By:
 */
@Slf4j
@Aspect
@Order(1)
@Component
public class DynamicDataSourceAspect {

    @Pointcut("@within(com.dynamicDataSource.dynamicDataSource.annotations.DataSource)")
    public void pointCut() {
    }

    /**
     * 类切面
     *
     * @param point
     */
    @Before("pointCut()")
    public void changeDataSource(JoinPoint point) {
        DataSource datasource = (DataSource) point.getSignature().getDeclaringType().getAnnotation(DataSource.class);
        String dsId = "master";
        if (null != datasource.value()) {
            dsId = datasource.value();
        }
        if (DynamicDataSourceContextHolder.containsDataSource(dsId)) {
//            log.info("使用数据源-- {} *** {}", dsId, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceRouterKey(dsId);
        } else {
            log.info("数据源[{}]不存在，使用默认数据源 > {}", dsId, point.getSignature());
            DynamicDataSourceContextHolder.setDataSourceRouterKey("master");
        }
    }

    @After("pointCut()")
    public void restoreDataSource() {
        DynamicDataSourceContextHolder.removeDataSourceRouterKey();
    }

}

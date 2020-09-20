package com.dynamicDataSource.dynamicDataSource.dynamic;

import com.dynamicDataSource.dynamicDataSource.enums.DataSourceEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @Author: ysd
 * @Description: 动态数据源路由
 * @Date: Created in 2020/9/20 11:54
 * Modified By:
 */
@Slf4j
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        String dataSourceName = DynamicDataSourceContextHolder.getDataSourceRouterKey();
        if (dataSourceName == null) {
            dataSourceName = DataSourceEnum.MASTER.getDataSource();
        }
        log.info("当前数据源是: {}", dataSourceName);
        return dataSourceName;
    }
}

package com.dynamicDataSource.dynamicDataSource.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

/**
 * @Author: ysd
 * @Description:
 * @Date: Created in 2020/9/20 11:55
 * Modified By:
 */
@AllArgsConstructor
@Getter
@ToString
public enum DataSourceEnum {


    MASTER("master"),

    SLAVEONE("slave1"),

    SLAVETWO("slave2"),
    ;

    /**
     * 数据源
     */
    private String dataSource;
}

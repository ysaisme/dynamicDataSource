package com.dynamicDataSource.dynamicDataSource.dynamic;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ysd
 * @Description: 数据源上下文
 * @Date: Created in 2020/9/20 11:52
 * Modified By:
 */
@Slf4j
public class DynamicDataSourceContextHolder {

    /**
     * 存储已经注册的数据源key
     */
    public static List<String> dataSourceIds = new ArrayList<>();

    /**
     * 线程内私有变量
     */
    private static final ThreadLocal<String> HOLDER = new ThreadLocal<>();

    public static String getDataSourceRouterKey(){
        return HOLDER.get();
    }

    public static void setDataSourceRouterKey (String dataSourceRouterKey) {
        HOLDER.set(dataSourceRouterKey);
//        log.info("切换至 {} 数据源", dataSourceRouterKey);
    }

    /**
     * 设置数据源之前一定要先移除
     */
    public static void removeDataSourceRouterKey () {
        HOLDER.remove();
    }

    /**
     * 判断指定DataSrouce当前是否存在
     *
     * @param dataSourceId
     * @return
     */
    public static boolean containsDataSource(String dataSourceId){
        return dataSourceIds.contains(dataSourceId);
    }
}

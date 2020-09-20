package com.dynamicDataSource.dynamicDataSource.dynamic;

import com.dynamicDataSource.dynamicDataSource.enums.DataSourceEnum;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.boot.context.properties.source.ConfigurationPropertyNameAliases;
import org.springframework.boot.context.properties.source.ConfigurationPropertySource;
import org.springframework.boot.context.properties.source.MapConfigurationPropertySource;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ysd
 * @Description: 动态注册数据源
 * @Date: Created in 2020/9/20 11:53
 * Modified By:
 */
@Slf4j
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    /**
     * 配置上下文 获取配置文件
     */
    private Environment evn;

    private final static ConfigurationPropertyNameAliases aliases = new ConfigurationPropertyNameAliases();

    /**
     * 由于部分数据源配置不同，所以在此处添加别名，避免切换数据源出现某些参数无法注入的情况
     */
    static {
        aliases.addAliases("url", new String[]{"url"});
        aliases.addAliases("username", new String[]{"username"});
    }

    /**
     * 存储注册的数据源
     */
    private Map<String, DataSource> dataSourceMap = new HashMap<>();

    /**
     * 参数绑定工具
     */
    private Binder binder;


    @Override
    public void setEnvironment(Environment environment) {
        log.info("开始注册数据源");
        this.evn = environment;
        //绑定配置器
        binder = Binder.get(evn);
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry beanDefinitionRegistry) {
        //获取所有的数据源
        Map config, defaultDataSourceProperties;
        defaultDataSourceProperties = binder.bind("spring.datasource.master", Map.class).get();
        //获取数据源类型
        String type = evn.getProperty("spring.datasource.master.type");
        Class<? extends DataSource> clazz = getDataSourceType(type);
        //绑定默认数据源参数 也就是主数据源
        DataSource dataSource, defaultDataSource = bind(clazz, defaultDataSourceProperties);
        DynamicDataSourceContextHolder.dataSourceIds.add(DataSourceEnum.MASTER.getDataSource());
        log.info("注册默认的数据源成功!");

        //获取其它数据源
        if (binder.bind("spring.datasource.cluster", Bindable.listOf(Map.class)).isBound()) {
            List<Map> configs = binder.bind("spring.datasource.cluster", Bindable.listOf(Map.class)).get();
            for (int i = 0; i < configs.size(); i++) {
                config = configs.get(i);
                clazz = getDataSourceType((String) config.get("type"));
                defaultDataSourceProperties = config;
                //绑定参数
                dataSource = bind(clazz, defaultDataSourceProperties);
                //获取数据源的key,判断是哪个数据源
                String key = (String) config.get("key");
                dataSourceMap.put(key, dataSource);
                //数据源上下文，用于管理数据源与记录已经注册的数据源的key
                DynamicDataSourceContextHolder.dataSourceIds.add(key);
                log.info("注册 {} 数据源成功!", key);
            }
        }

        GenericBeanDefinition define = new GenericBeanDefinition();
        // 设置bean的类型，此处DynamicRoutingDataSource是继承AbstractRoutingDataSource的实现类
        define.setBeanClass(DynamicRoutingDataSource.class);
        // 需要注入的参数
        MutablePropertyValues mpv = define.getPropertyValues();
        // 添加默认数据源
        mpv.add("defaultTargetDataSource", defaultDataSource);
        // 添加其他数据源
        mpv.add("targetDataSources", dataSourceMap);
        // 将该bean注册为datasource，不使用springboot自动生成的datasource
        beanDefinitionRegistry.registerBeanDefinition("datasource", define);
        log.info("注册数据源成功，一共注册{}个数据源", dataSourceMap.keySet().size() + 1);
    }

    /**
     * 通过字符串获取数据源class对象
     *
     * @param typeStr
     * @return
     */
    private Class<? extends DataSource> getDataSourceType(String typeStr) {
        Class<? extends DataSource> type;
        try {
            if (StringUtils.hasLength(typeStr)) {
                // 字符串不为空则通过反射获取class对象
                type = (Class<? extends DataSource>) Class.forName(typeStr);
            } else {
                // 默认为hikariCP数据源，与springboot默认数据源保持一致
                type = HikariDataSource.class;
            }
            return type;
        } catch (Exception e) {
            //无法通过反射获取class对象的情况则抛出异常，该情况一般是写错了，所以此次抛出一个runtimeexception
            throw new IllegalArgumentException("can not resolve class with type: " + typeStr);
        }
    }

    /**
     * 绑定参数，以下三个方法都是参考DataSourceBuilder的bind方法实现的，目的是尽量保证我们自己添加的数据源构造过程与springboot保持一致
     *
     * @param result
     * @param properties
     */
    private void bind(DataSource result, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        // 将参数绑定到对象
        binder.bind(ConfigurationPropertyName.EMPTY, Bindable.ofInstance(result));
    }

    private <T extends DataSource> T bind(Class<T> clazz, Map properties) {
        ConfigurationPropertySource source = new MapConfigurationPropertySource(properties);
        Binder binder = new Binder(new ConfigurationPropertySource[]{source.withAliases(aliases)});
        // 通过类型绑定参数并获得实例对象
        return binder.bind(ConfigurationPropertyName.EMPTY, Bindable.of(clazz)).get();
    }

    /**
     * @param clazz
     * @param sourcePath 参数路径，对应配置文件中的值，如: spring.datasource
     * @param <T>
     * @return
     */
    private <T extends DataSource> T bind(Class<T> clazz, String sourcePath) {
        Map properties = binder.bind(sourcePath, Map.class).get();
        return bind(clazz, properties);
    }
}

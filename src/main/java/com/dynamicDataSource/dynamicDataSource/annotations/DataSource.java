package com.dynamicDataSource.dynamicDataSource.annotations;

import java.lang.annotation.*;

/**
 * @Author: ysd
 * @Description: 切换数据源注解 可以用于类或者方法级别 方法级别优先级 > 类级别  注意:请不要再事务内使用此注解，会导致切换不生效
 * @Date: Created in 2020/9/20 11:46
 * Modified By:
 */
@Target({ElementType.METHOD,ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource{
    //默认主数据源
    String value() default "master";
}
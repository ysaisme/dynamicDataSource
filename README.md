# dynamicDataSource
springboot基于注解的动态切换数据源处理

通过自定义注解@DataSource支持类级别，方法级别数据源的动态切换

切换数据源注解 可以用于类或者方法级别 方法级别优先级 > 类级别  

注意:请不要再事务内使用此注解，会导致切换不生效

DynamicDataSourceRegister 动态注册数据源
DynamicDataSourceContextHolder 获取数据源上下文
DynamicRoutingDataSource 动态数据源路由

DynamicDataSourceAspect 类切面处理
DynamicDataSourceAspectMethod 方法切面处理

需在启动类加上次注解@Import(DynamicDataSourceRegister.class)

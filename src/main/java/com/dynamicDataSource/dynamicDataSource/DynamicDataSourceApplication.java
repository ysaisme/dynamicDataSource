package com.dynamicDataSource.dynamicDataSource;

import com.dynamicDataSource.dynamicDataSource.dynamic.DynamicDataSourceRegister;
import org.springframework.context.annotation.Import;
import tk.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "com.dynamicDataSource.dynamicDataSource"
})
@MapperScan(basePackages = {
        "com.dynamicDataSource.dynamicDataSource.dao"
})
@Import(DynamicDataSourceRegister.class)
public class DynamicDataSourceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DynamicDataSourceApplication.class, args);
    }

}

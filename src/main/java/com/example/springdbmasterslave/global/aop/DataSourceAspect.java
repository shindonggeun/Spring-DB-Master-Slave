package com.example.springdbmasterslave.global.aop;

import com.example.springdbmasterslave.global.datasource.DataSourceContextHolder;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {
    
    @Before("@annotation(ReadOnly)")
    public void useSlaveDataSource() {
        DataSourceContextHolder.setDataSourceType("slave");
    }

    @After("@annotation(ReadOnly)")
    public void clearDataSource() {
        DataSourceContextHolder.clearDataSourceType();
    }
}

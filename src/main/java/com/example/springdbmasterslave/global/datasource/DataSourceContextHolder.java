package com.example.springdbmasterslave.global.datasource;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DataSourceContextHolder {

    private static final ThreadLocal<String> CONTEXT = new ThreadLocal<>();

    public static String getDataSourceType() {
        return CONTEXT.get();
    }

    public static void setDataSourceType(String dataSourceType) {
        log.info("Switching to {} DataSource", dataSourceType);
        CONTEXT.set(dataSourceType);
    }

    public static void clearDataSourceType() {
        CONTEXT.remove();
    }
}
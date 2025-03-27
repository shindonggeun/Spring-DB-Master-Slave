//package com.example.backend.global.config;
//
//import com.zaxxer.hikari.HikariDataSource;
//import java.util.HashMap;
//import java.util.Map;
//import javax.sql.DataSource;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.jdbc.DataSourceBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.DependsOn;
//import org.springframework.context.annotation.Primary;
//import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
//
/// **
// * 마스터-슬레이브 구조에서 다중 DataSource 설정 클래스
// * - master: 쓰기 전용
// * - slave: 읽기 전용
// * 트랜잭션의 readOnly 여부에 따라 라우팅을 수행
// */
//@Configuration
//public class DataSourceConfig {
//
//    /**
//     * 마스터 DB 설정 (@Primary가 아님)
//     * - application.yml: spring.datasource.master.hikari.* 에서 설정 로드
//     */
//    @Bean
//    @ConfigurationProperties("spring.datasource.master.hikari")
//    public DataSource masterDataSource() {
//        return DataSourceBuilder.create()
//            .type(HikariDataSource.class)
//            .build();
//    }
//
//    /**
//     * 슬레이브 DB 설정
//     * - application.yml: spring.datasource.slave.hikari.* 에서 설정 로드
//     */
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave.hikari")
//    public DataSource slaveDataSource() {
//        return DataSourceBuilder.create()
//            .type(HikariDataSource.class)
//            .build();
//    }
//
//    /**
//     * 라우팅 데이터소스 설정
//     * - Transaction이 readOnly일 경우 슬레이브로, 아니면 마스터로 라우팅
//     * - 내부적으로 AbstractRoutingDataSource를 상속한 RoutingDataSource 사용
//     */
//    @Bean
//    @DependsOn({"masterDataSource", "slaveDataSource"})
//    public DataSource routingDataSource(
//        @Qualifier("masterDataSource") DataSource masterDataSource,
//        @Qualifier("slaveDataSource") DataSource slaveDataSource) {
//        RoutingDataSource routingDataSource = new RoutingDataSource();
//        Map<Object, Object> datasource = new HashMap<>();
//        datasource.put("master", masterDataSource);
//        datasource.put("slave", slaveDataSource);
//        routingDataSource.setTargetDataSources(datasource);
//        routingDataSource.setDefaultTargetDataSource(masterDataSource);
//        return routingDataSource;
//    }
//
//    /**
//     * LazyConnectionDataSourceProxy를 이용해 실제 커넥션을 사용하는 시점에 라우팅이 발생하도록 설정
//     * - @Primary: 다른 곳에서 @Autowired DataSource 할 경우 이 빈이 주입됨
//     */
//    @Bean
//    @Primary
//    @DependsOn("routingDataSource")
//    public LazyConnectionDataSourceProxy dataSource(DataSource routingDataSource) {
//        return new LazyConnectionDataSourceProxy(routingDataSource);
//    }
//}

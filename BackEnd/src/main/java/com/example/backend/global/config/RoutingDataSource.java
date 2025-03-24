package com.example.backend.global.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * 실제 커넥션을 가져오는 시점에 호출되어,
 * 현재 트랜잭션이 readOnly인지 여부에 따라 마스터 또는 슬레이브를 선택하는 라우팅 클래스
 */
@Slf4j
public class RoutingDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        // 현재 트랜잭션이 readOnly일 경우 → slave 데이터소스로 라우팅
        boolean isReadOnly = TransactionSynchronizationManager.isCurrentTransactionReadOnly();
        String target = isReadOnly ? "slave" : "master";
        log.info("Routing to DataSource: {}", target); // 추가
        return target;
    }
}

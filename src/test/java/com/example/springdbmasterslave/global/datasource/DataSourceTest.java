package com.example.springdbmasterslave.global.datasource;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.springdbmasterslave.domain.member.service.MemberService;
import java.sql.Connection;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DataSourceTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private MemberService memberService;

    @Test
    void testMasterSlaveSeparation() throws Exception {
        // Master DB 연결 확인
        try (Connection connection = dataSource.getConnection()) {
            String url = connection.getMetaData().getURL();
            assertThat(url).contains("jdbc:mysql://localhost:3306");
        }

        // Slave에서 실행되는지 확인
        String testEmail = "slave-tes123t@example.com";
        memberService.registerMember("Slave Test", testEmail);

        memberService.getMemberByEmail(testEmail); // @ReadOnly 붙었으므로 Slave에서 실행돼야 함
    }
}

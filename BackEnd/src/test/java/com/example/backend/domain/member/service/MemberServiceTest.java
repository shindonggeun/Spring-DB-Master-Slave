package com.example.backend.domain.member.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.backend.domain.member.dto.MemberCreateRequest;
import com.example.backend.domain.member.entity.Member;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Test
    void testRegisterMemberPerformance() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Given
        MemberCreateRequest createRequest = new MemberCreateRequest(
            "donggeun" + System.currentTimeMillis() + "@example.com", "ShinDonggeun");

        // When
        Member savedMember = memberService.createMember(createRequest);

        stopWatch.stop();
        System.out.println("회원 가입 실행 시간: " + stopWatch.getTotalTimeMillis() + "ms");

        // Then
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo(createRequest.email());
    }

    @Test
    void testGetAllMembersPerformance() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // When
        List<Member> members = memberService.getAllMembers();

        stopWatch.stop();
        System.out.println("회원 목록 조회 실행 시간: " + stopWatch.getTotalTimeMillis() + "ms");

        // Then
        assertThat(members).isNotNull();
    }
}

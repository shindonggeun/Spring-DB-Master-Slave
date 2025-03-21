package com.example.backend.domain.member.service;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import com.example.backend.domain.member.entity.Member;
import com.example.backend.domain.member.repository.MemberRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

@SpringBootTest
public class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testRegisterMemberPerformance() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // Given
        String name = "신동근";
        String email = "donggeun" + System.currentTimeMillis() + "@example.com"; // 중복 방지

        // When
        Member savedMember = memberService.createMember(name, email);

        stopWatch.stop();
        System.out.println("회원 가입 실행 시간: " + stopWatch.getTotalTimeMillis() + "ms");

        // Then
        assertThat(savedMember).isNotNull();
        assertThat(savedMember.getEmail()).isEqualTo(email);
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

    @Test
    void testGetMemberByEmailPerformance() {
        // Given
        String email = "test1@example.com";
        memberService.createMember("Test User", email);

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();

        // When
        Member member = memberService.getMemberByEmail(email);

        stopWatch.stop();
        System.out.println("이메일 조회 실행 시간: " + stopWatch.getTotalTimeMillis() + "ms");

        // Then
        assertThat(member).isNotNull();
        assertThat(member.getEmail()).isEqualTo(email);
    }
}

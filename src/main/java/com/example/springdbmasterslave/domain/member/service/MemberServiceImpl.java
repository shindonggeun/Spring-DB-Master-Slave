package com.example.springdbmasterslave.domain.member.service;

import com.example.springdbmasterslave.domain.member.entity.Member;
import com.example.springdbmasterslave.domain.member.repository.MemberRepository;
import com.example.springdbmasterslave.global.aop.ReadOnly;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member registerMember(String name, String email) {
        Member member = Member.builder()
            .name(name)
            .email(email)
            .build();
        return memberRepository.save(member);
    }

    @Override
    @ReadOnly
    @Transactional(readOnly = true)
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    @ReadOnly
    @Transactional(readOnly = true)
    public Member getMemberByEmail(String email) {
        return memberRepository.findByEmail(email).orElse(null);
    }
}

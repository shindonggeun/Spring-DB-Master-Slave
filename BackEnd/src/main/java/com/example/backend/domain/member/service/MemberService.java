package com.example.backend.domain.member.service;

import com.example.backend.domain.member.dto.MemberCreateRequest;
import com.example.backend.domain.member.entity.Member;
import java.util.List;

public interface MemberService {

    Member createMember(MemberCreateRequest createRequest);

    List<Member> getAllMembers();

    Member getMemberByEmail(String email);
}

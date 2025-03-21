package com.example.backend.domain.member.service;

import com.example.backend.domain.member.entity.Member;
import java.util.List;

public interface MemberService {

    Member createMember(String name, String email);

    List<Member> getAllMembers();

    Member getMemberByEmail(String email);
}

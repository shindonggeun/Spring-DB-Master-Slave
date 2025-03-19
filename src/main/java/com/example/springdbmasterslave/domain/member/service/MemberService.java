package com.example.springdbmasterslave.domain.member.service;

import com.example.springdbmasterslave.domain.member.entity.Member;
import java.util.List;

public interface MemberService {

    Member registerMember(String name, String email);

    List<Member> getAllMembers();

    Member getMemberByEmail(String email);
}

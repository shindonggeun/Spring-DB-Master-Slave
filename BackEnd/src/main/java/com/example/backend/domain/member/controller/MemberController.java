package com.example.backend.domain.member.controller;

import com.example.backend.domain.member.dto.MemberCreateRequest;
import com.example.backend.domain.member.entity.Member;
import com.example.backend.domain.member.service.MemberService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/members")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/slave")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/slave/{email}")
    public Member getMemberByEmail(@PathVariable String email) {
        return memberService.getMemberByEmail(email);
    }

    @PostMapping("/master")
    public Member createMember(@RequestBody MemberCreateRequest createRequest) {
        return memberService.createMember(createRequest);
    }
}

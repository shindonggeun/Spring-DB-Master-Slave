package com.example.backend.domain.member.dto;

public record MemberCreateRequest(
    String email,
    String name
) {

}

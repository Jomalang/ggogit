package io.ggogit.ggogit.domain.member.entity;


import lombok.Getter;

@Getter
public enum RoleType {
    USER("사용자"),
    ADMIN("관리자");

    private final String description;

    RoleType(String description) {
        this.description = description;
    }
}
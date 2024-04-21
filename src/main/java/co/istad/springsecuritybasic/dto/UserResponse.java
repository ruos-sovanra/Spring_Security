package co.istad.springsecuritybasic.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserResponse(String id, String email, String password, Set<String> roles) {
}

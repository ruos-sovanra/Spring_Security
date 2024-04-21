package co.istad.springsecuritybasic.dto;

import lombok.Builder;

import java.util.Set;

@Builder
public record UserRequest(String email, String password, Set<String> roles) {
}

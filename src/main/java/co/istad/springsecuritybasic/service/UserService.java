package co.istad.springsecuritybasic.service;

import co.istad.springsecuritybasic.dto.UserRequest;
import co.istad.springsecuritybasic.dto.UserResponse;

public interface UserService {
    UserResponse createUser(UserRequest userRequest);
}

package co.istad.springsecuritybasic.restcontrollers;


import co.istad.springsecuritybasic.dto.UserRequest;
import co.istad.springsecuritybasic.dto.UserResponse;
import co.istad.springsecuritybasic.service.UserService;
import co.istad.springsecuritybasic.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AdminRestController {

    private final UserService userService;

    @PostMapping("/register")
    public BaseResponse<UserResponse> createNewUser(@RequestBody UserRequest userRequest) {
        return BaseResponse.<UserResponse>createSuccess()
                .setPayload(userService.createUser(userRequest));

    }
}

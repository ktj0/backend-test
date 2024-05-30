package kr.co.polycube.backendtest.controller;

import jakarta.validation.Valid;
import kr.co.polycube.backendtest.dto.UserIdResponseDto;
import kr.co.polycube.backendtest.dto.UserRequestDto;
import kr.co.polycube.backendtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // user 등록
    @PostMapping("/users")
    public UserIdResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }
}

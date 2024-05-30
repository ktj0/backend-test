package kr.co.polycube.backendtest.controller;

import jakarta.validation.Valid;
import kr.co.polycube.backendtest.dto.UserIdResponseDto;
import kr.co.polycube.backendtest.dto.UserRequestDto;
import kr.co.polycube.backendtest.dto.UserResponseDto;
import kr.co.polycube.backendtest.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // user 등록
    @PostMapping("/users")
    public UserIdResponseDto createUser(@Valid @RequestBody UserRequestDto requestDto) {
        return userService.createUser(requestDto);
    }

    // user 조회
    @GetMapping("/users/{id}")
    public UserResponseDto getUser(@PathVariable Long id) {
        return userService.getUser(id);
    }
}

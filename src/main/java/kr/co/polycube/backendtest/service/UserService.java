package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.dto.UserIdResponseDto;
import kr.co.polycube.backendtest.dto.UserRequestDto;
import kr.co.polycube.backendtest.entity.User;
import kr.co.polycube.backendtest.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    // user 등록
    public UserIdResponseDto createUser(UserRequestDto requestDto) {
        User user = new User(requestDto.getName());
        User savedUser =  userRepository.save(user);

        return new UserIdResponseDto(savedUser.getId());
    }
}

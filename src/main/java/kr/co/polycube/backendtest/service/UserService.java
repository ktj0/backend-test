package kr.co.polycube.backendtest.service;

import jakarta.persistence.EntityNotFoundException;
import kr.co.polycube.backendtest.dto.UserIdResponseDto;
import kr.co.polycube.backendtest.dto.UserRequestDto;
import kr.co.polycube.backendtest.dto.UserResponseDto;
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

    // user 조회
    public UserResponseDto getUser(Long userId) {
        User user = findUser(userId);

        return new UserResponseDto(user);
    }

    // user 존재여부 확인
    private User findUser(Long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new EntityNotFoundException("해당 사용자가 존재하지 않습니다.")
        );
    }
}

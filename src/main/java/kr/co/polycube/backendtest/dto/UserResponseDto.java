package kr.co.polycube.backendtest.dto;

import kr.co.polycube.backendtest.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String name;

    public UserResponseDto(User user) {
        this.id = user.getId();
        this.name = user.getName();
    }
}

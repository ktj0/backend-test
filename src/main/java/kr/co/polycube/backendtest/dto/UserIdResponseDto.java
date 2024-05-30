package kr.co.polycube.backendtest.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserIdResponseDto {
    private Long id;

    public UserIdResponseDto(Long id) {
        this.id = id;
    }
}

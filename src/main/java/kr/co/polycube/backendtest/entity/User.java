package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.dto.UserRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    public User(String name) {
        this.name = name;
    }

    // name 수정
    public void update(UserRequestDto requestDto) {
        this.name = requestDto.getName();
    }
}

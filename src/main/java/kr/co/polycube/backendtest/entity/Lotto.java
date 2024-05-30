package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import kr.co.polycube.backendtest.dto.LottoRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "lottos")
@Getter
@Setter
@NoArgsConstructor
public class Lotto extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer number_1;

    @Column(nullable = false)
    private Integer number_2;

    @Column(nullable = false)
    private Integer number_3;

    @Column(nullable = false)
    private Integer number_4;

    @Column(nullable = false)
    private Integer number_5;

    @Column(nullable = false)
    private Integer number_6;

    public Lotto(LottoRequestDto requestDto) {
        this.number_1 = requestDto.getNumbers().get(0);
        this.number_2 = requestDto.getNumbers().get(1);
        this.number_3 = requestDto.getNumbers().get(2);
        this.number_4 = requestDto.getNumbers().get(3);
        this.number_5 = requestDto.getNumbers().get(4);
        this.number_6 = requestDto.getNumbers().get(5);
    }
}

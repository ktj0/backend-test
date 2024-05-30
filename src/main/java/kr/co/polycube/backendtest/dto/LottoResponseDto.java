package kr.co.polycube.backendtest.dto;

import kr.co.polycube.backendtest.entity.Lotto;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class LottoResponseDto {
    private List<Integer> numbers;

    public LottoResponseDto(Lotto lotto) {
        this.numbers = Arrays.asList(
                lotto.getNumber_1(),
                lotto.getNumber_2(),
                lotto.getNumber_3(),
                lotto.getNumber_4(),
                lotto.getNumber_5(),
                lotto.getNumber_6()
        );
    }
}

package kr.co.polycube.backendtest.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LottoRequestDto {
    private List<Integer> numbers;
}

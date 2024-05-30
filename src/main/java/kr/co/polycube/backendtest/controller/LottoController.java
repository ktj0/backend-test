package kr.co.polycube.backendtest.controller;

import kr.co.polycube.backendtest.dto.LottoRequestDto;
import kr.co.polycube.backendtest.dto.LottoResponseDto;
import kr.co.polycube.backendtest.service.LottoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lottos")
public class LottoController {
    private final LottoService lottoService;

    // lotto 번호 등록
    @PostMapping()
    public LottoResponseDto registerLottoNumbers(@RequestBody LottoRequestDto requestDto) {
        return lottoService.registerLottoNumbers(requestDto);
    }
}

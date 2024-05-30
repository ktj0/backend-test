package kr.co.polycube.backendtest.service;

import kr.co.polycube.backendtest.dto.LottoRequestDto;
import kr.co.polycube.backendtest.dto.LottoResponseDto;
import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.repository.LottoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LottoService {
    private final LottoRepository lottoRepository;

    // lotto 번호 등록
    public LottoResponseDto registerLottoNumbers(LottoRequestDto requestDto) {
        Lotto lotto = new Lotto(requestDto);
        Lotto saveLotto = lottoRepository.save(lotto);

        return new LottoResponseDto(saveLotto);
    }
}

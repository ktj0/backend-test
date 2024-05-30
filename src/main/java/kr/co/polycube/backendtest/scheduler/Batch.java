package kr.co.polycube.backendtest.scheduler;

import kr.co.polycube.backendtest.entity.Lotto;
import kr.co.polycube.backendtest.entity.Winner;
import kr.co.polycube.backendtest.repository.LottoRepository;
import kr.co.polycube.backendtest.repository.WinnerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Component
@Slf4j(topic = "로또 당첨 번호 확인")
@RequiredArgsConstructor
public class Batch {
    private final LottoRepository lottoRepository;
    private final WinnerRepository winnerRepository;

    @Scheduled(cron = "0 0 0 * * SUN")
    public void checkWinners() {
        LocalDateTime currentDate = LocalDateTime.now();
        LocalDateTime oneWeekAgo = currentDate.minusWeeks(1);

        List<Lotto> lottos = lottoRepository.findByCreatedAtBetween(
                oneWeekAgo.toLocalDate().atStartOfDay(),
                currentDate.toLocalDate().atStartOfDay()
        );

        List<Integer> winningNumbers = generateLottoNumbers();

        for (Lotto lotto : lottos) {
            int rank = calculateWinningRank(lotto, winningNumbers);

            if (rank > 0) {
                Winner winner = new Winner(lotto, rank);

                winnerRepository.save(winner);
            }
        }
    }

    // 1부터 45까지의 숫자 중 6개를 랜덤으로 선택해 당첨번호 생성
    private List<Integer> generateLottoNumbers() {
        Random random = new Random();
        List<Integer> numbers = new ArrayList<>();

        while (numbers.size() < 6) {
            int randomNumber = random.nextInt(45) + 1;

            if (!numbers.contains(randomNumber)) {
                numbers.add(randomNumber);
            }
        }

        return numbers;
    }

    // 당첨번호와 등록된 번호를 비교해서 등수 계산
    private int calculateWinningRank(Lotto lotto, List<Integer> winningNumbers) {
        List<Integer> lottoNumbers = Arrays.asList(
                lotto.getNumber_1(),
                lotto.getNumber_2(),
                lotto.getNumber_3(),
                lotto.getNumber_4(),
                lotto.getNumber_5(),
                lotto.getNumber_6()
        );

        int matchedNumbers = 0;

        for (int i = 0; i < lottoNumbers.size(); i++) {
            if (lottoNumbers.get(i).equals(winningNumbers.get(i))) {
                matchedNumbers++;
            }
        }

        switch (matchedNumbers) {
            case 6:
                return 1;
            case 5:
                return 2;
            case 4:
                return 3;
            case 3:
                return 4;
            case 2:
                return 5;
            default:
                return 0;
        }
    }
}

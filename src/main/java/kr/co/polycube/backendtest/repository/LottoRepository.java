package kr.co.polycube.backendtest.repository;

import kr.co.polycube.backendtest.entity.Lotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LottoRepository extends JpaRepository<Lotto, Long> {
    List<Lotto> findByCreatedAtBetween(LocalDateTime startDateTime, LocalDateTime endDateTime);
}

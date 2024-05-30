package kr.co.polycube.backendtest.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "winners")
@Getter
@Setter
@NoArgsConstructor
public class Winner extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lotto_id", nullable = false)
    private Lotto lotto;

    @Column(nullable = false)
    private Integer winningRank;

    public Winner(Lotto lotto, Integer winningRank) {
        this.lotto = lotto;
        this.winningRank = winningRank;
    }
}

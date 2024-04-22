package kz.din.transactions.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    private BigDecimal remainingMonthlyLimit;
    private BigDecimal transactionAmount;
    @CreationTimestamp
    private LocalDateTime transactionDatetime;
    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyShortName;
    @Enumerated(EnumType.STRING)
    private Expenses expenses;
    private Boolean limitExceeded;
}

package kz.din.transactions.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String name;

    private String bankAccount;

    @CreationTimestamp
    private LocalDateTime created;

    @UpdateTimestamp
    private LocalDateTime updated;

    @Enumerated(EnumType.STRING)
    private CurrencyShortName currencyShortName;



    @PostPersist
    private void generateAccountNumber() {
        if (this.bankAccount != null) return;

        StringBuilder accountNumberBuilder = new StringBuilder();
        accountNumberBuilder.append("0000000000");
        String formattedId = String.format("%010d", this.id);
        accountNumberBuilder.replace(10 - formattedId.length(), 10, formattedId);
        this.bankAccount =  accountNumberBuilder.toString();
    }

}

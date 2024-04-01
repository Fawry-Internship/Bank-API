package org.example.bankapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bankapi.entity.enums.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Transaction {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id ;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;


    @PositiveOrZero(message = "Amount must be positive or zero")
    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType transaction_type;

    private LocalDateTime created_at;
    private String payment_method = "credit card";
}

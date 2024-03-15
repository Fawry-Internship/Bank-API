package org.example.bankapi.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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


    @Positive(message = "balance must be positive")
    private double amount;

    @NotBlank(message = "Transaction type must not be blank")
    private String transaction_type;

    private LocalDateTime created_at;
    private String payment_method = "credit card";
}

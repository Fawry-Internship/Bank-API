package org.example.bankapi.model.transaction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.bankapi.entity.enums.TransactionType;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponseDTO {
    private Long id;
    private double amount;
    private TransactionType transaction_type;
    private LocalDateTime created_at;
    private String payment_method = "credit card";
}

package org.example.bankapi.model.transaction;

import jakarta.validation.constraints.DecimalMin;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionRequest {

    @CreditCardNumber(message = "Please provide a valid email card number")
    private String cardNumber;

    @DecimalMin(value = "0.01", message = "Amount must be greater than or equal to 0.01")
    private double amount;
}

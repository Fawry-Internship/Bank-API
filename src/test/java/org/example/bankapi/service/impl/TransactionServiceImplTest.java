package org.example.bankapi.service.impl;

import org.example.bankapi.entity.Account;
import org.example.bankapi.entity.Transaction;
import org.example.bankapi.exception.RecordNotFoundException;
import org.example.bankapi.model.transaction.TransactionRequest;
import org.example.bankapi.repository.AccountRepository;
import org.example.bankapi.repository.TransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private TransactionServiceImpl transactionService;

    private TransactionRequest transactionRequest;
    private Account mockAccount;

    @BeforeEach
    public void setup () {
        transactionRequest = new TransactionRequest("1234567891234567",
                15.00);

        mockAccount = new Account();
        mockAccount.setBalance(1500.0);
        mockAccount.setCardNumber("1234567891234567");
        mockAccount.setEmail("sameh.tarek@gmail.com");
    }

    @Test
    public void depositShouldSuccess () {
        // Arrange
        when(accountRepository.findByCardNumber(transactionRequest.getCardNumber()))
                .thenReturn(Optional.of(mockAccount));

        // Act
        String result = transactionService.deposit(transactionRequest);

        // Assert
        assertThat(result).isEqualTo("success");
    }

    @Test
    public void depositShouldThrowExceptionIfCardNumberIsValid () {
        // Arrange
        when(accountRepository.findByCardNumber(transactionRequest.getCardNumber()))
                .thenReturn(Optional.ofNullable(null));

        // Act & Assert
        assertThatThrownBy(() -> transactionService.deposit(transactionRequest))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("This Card Number Not Valid " + transactionRequest.getCardNumber());
    }

    @Test
    public void withdrawShouldSuccessIfCardNumberIsValidAndAmountIsSufficient () {
        // Arrange
        when(accountRepository.findByCardNumber(transactionRequest.getCardNumber()))
                .thenReturn(Optional.of(mockAccount));

        // Act
        String result = transactionService.withdraw(transactionRequest);

        // Assert
        assertThat("success").isEqualTo(result);
    }

    @Test
    public void withdrawShouldFailedIfCardNumberIsNotValidAndAmountIsInsufficient () {
        // Arrange
        TransactionRequest transactionRequest1 =
                new TransactionRequest("1234567891234567", 1600.00);
        when(accountRepository.findByCardNumber(transactionRequest1.getCardNumber()))
                .thenReturn(Optional.of(mockAccount));

        // Act & Assert
        assertThatThrownBy(() -> transactionService.withdraw(transactionRequest1))
                .isInstanceOf(RecordNotFoundException.class)
                .hasMessageContaining("Insufficient Balance, The current balance is: " + mockAccount.getBalance());

    }

}
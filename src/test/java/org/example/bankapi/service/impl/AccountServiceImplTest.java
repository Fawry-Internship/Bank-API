package org.example.bankapi.service.impl;

import org.example.bankapi.entity.Account;
import org.example.bankapi.entity.Transaction;
import org.example.bankapi.exception.RecordNotFoundException;
import org.example.bankapi.mapper.TransactionMapper;
import org.example.bankapi.model.transaction.TransactionResponseDTO;
import org.example.bankapi.repository.AccountRepository;
import org.example.bankapi.utils.SecurityUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    @Mock
    private AccountRepository accountRepository;
    @Mock
    private TransactionMapper transactionMapper;
    @InjectMocks
    private AccountServiceImpl accountService;

    private Account mockAccount;
    private MockedStatic<SecurityUtils> mockedStatic ;


    @BeforeEach
    public void setup () {

        mockAccount = new Account();
        mockAccount.setBalance(1500.0);
        mockAccount.setCardNumber("1234567891234567");
        mockAccount.setEmail("sameh.tarek@gmail.com");
        mockAccount.setTransactions(List.of(new Transaction()));

        mockedStatic = mockStatic(SecurityUtils.class);
    }

    @AfterEach
    public void tearDown() {
        mockedStatic.close();
    }

    @Test
    public void viewAuthenticatedAccountBalanceShouldReturnAccountBalance () {
        // Arrange
        mockedStatic.when(SecurityUtils::getCurrentUserEmail).thenReturn(mockAccount.getEmail());
        when(accountRepository.findByEmail(mockAccount.getEmail())).thenReturn(Optional.of(mockAccount));

        // Act
        Double balance = accountService.viewAuthenticatedAccountBalance();

        // Assert
        assertEquals(1500.0, balance);
    }

    @Test
    public void viewAuthenticatedAccountTransactionsShouldReturnListOfTransactionResponseDTO() {
        // Arrange
        mockedStatic.when(SecurityUtils::getCurrentUserEmail).thenReturn(mockAccount.getEmail());
        when(accountRepository.findByEmail(mockAccount.getEmail())).thenReturn(Optional.of(mockAccount));

        TransactionResponseDTO transactionResponseDTO = new TransactionResponseDTO();
        Transaction transaction = new Transaction();
        when(transactionMapper.toDTO(transaction)).thenReturn(transactionResponseDTO);

        // Act
        List<TransactionResponseDTO> result =
                accountService.viewAuthenticatedAccountTransactions();

        // Assert
        assertEquals(1, result.size());
    }

    @Test
    public void getCurrentAccountShouldReturnAuthAccountCardNumber () {
        // Arrange
        mockedStatic.when(SecurityUtils::getCurrentUserEmail).thenReturn(mockAccount.getEmail());
        when(accountRepository.findByEmail(mockAccount.getEmail())).thenReturn(Optional.of(mockAccount));

        // Act
        String result = accountService.getAuthenticatedAccountCardNumber();

        // Assert
        assertEquals("1234567891234567", result);
    }

    @Test
    public void getCurrentAccountShouldThrowExceptionIfAccountDoesNotExist () {
        // Arrange
        mockedStatic.when(SecurityUtils::getCurrentUserEmail).thenReturn(null);

        //Act & Assert
        assertThrows(RecordNotFoundException.class,
                () -> accountService.getAuthenticatedAccountCardNumber());
    }

}
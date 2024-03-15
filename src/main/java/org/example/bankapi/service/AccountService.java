package org.example.bankapi.service;

import org.example.bankapi.model.transaction.TransactionResponseDTO;

import java.util.List;

public interface AccountService {
    Double viewAccountBalanceById(Long accountId);

    List<TransactionResponseDTO> viewAccountTransactionsById(Long accountId);
}

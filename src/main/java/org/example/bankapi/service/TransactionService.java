package org.example.bankapi.service;

import org.example.bankapi.model.transaction.TransactionRequest;

public interface TransactionService {
    String deposit(TransactionRequest transactionRequest);

    String withdraw(TransactionRequest transactionRequest);
}

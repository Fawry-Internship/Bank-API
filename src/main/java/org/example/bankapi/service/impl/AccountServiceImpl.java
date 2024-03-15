package org.example.bankapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankapi.Repository.AccountRepository;
import org.example.bankapi.entity.Account;
import org.example.bankapi.exception.RecordNotFoundException;
import org.example.bankapi.mapper.TransactionMapper;
import org.example.bankapi.model.transaction.TransactionResponseDTO;
import org.example.bankapi.service.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final TransactionMapper transactionMapper;

    @Override
    public Double viewAccountBalanceById(Long accountId) {
        log.info("You want to view Balance for Account with id {}", accountId);
        Account currentAccount = getCurrentAccount(accountId);
        Double accountBalance = currentAccount.getBalance();
        log.info("The current Balance is {}", accountBalance);
        return accountBalance;
    }

    @Override
    public List<TransactionResponseDTO> viewAccountTransactionsById(Long accountId) {
        log.info("You want to view Transactions for Account with id {}", accountId);
        Account currentAccount = getCurrentAccount(accountId);
        List<TransactionResponseDTO> transactionResponseDTOS = currentAccount.getTransactions()
                .stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());
        log.info("This All Transactions Details for This Account {}", transactionResponseDTOS);
        return transactionResponseDTOS;
    }

    private Account getCurrentAccount(Long accountId){
        Account currentAccount =  accountRepository.findById(accountId)
                .orElseThrow(() -> new RecordNotFoundException("Account with Id " + accountId + "doesn't Exist"));
        log.info("This is current account {}", currentAccount);
        return currentAccount;
    }
}

package org.example.bankapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankapi.repository.AccountRepository;
import org.example.bankapi.entity.Account;
import org.example.bankapi.exception.RecordNotFoundException;
import org.example.bankapi.mapper.TransactionMapper;
import org.example.bankapi.model.transaction.TransactionResponseDTO;
import org.example.bankapi.service.AccountService;
import org.example.bankapi.utils.SecurityUtils;
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
    public Double viewAuthenticatedAccountBalance() {
        Account currentAccount = getCurrentAccount();
        Double accountBalance = currentAccount.getBalance();
        log.info("The current Balance is {}", accountBalance);
        return accountBalance;
    }

    @Override
    public List<TransactionResponseDTO> viewAuthenticatedAccountTransactions() {
        Account currentAccount = getCurrentAccount();
        List<TransactionResponseDTO> transactionResponseDTOS = currentAccount.getTransactions()
                .stream()
                .map(transactionMapper::toDTO)
                .collect(Collectors.toList());

        if(transactionResponseDTOS.isEmpty()){
            log.error("There is no Transactions for this user with Email {}", currentAccount);
        }

        log.info("This All Transactions Details for This Account {}", transactionResponseDTOS);
        return transactionResponseDTOS;
    }

    @Override
    public String getAuthenticatedAccountCardNumber() {
        Account currentAccount = getCurrentAccount();
        String accountCardNumber = currentAccount.getCardNumber();
        log.info("The current Account Card Number is {}", accountCardNumber);
        return accountCardNumber;
    }

    private Account getCurrentAccount(){
        String currentUserEmail = SecurityUtils.getCurrentUserEmail();
        log.info("user with Email {} want to view his Transactions ", currentUserEmail);
        Account currentAccount =  accountRepository.findByEmail(currentUserEmail)
                .orElseThrow(() -> {
                    log.error("Account with Email {}, doesn't Exist", currentUserEmail);
                    return new RecordNotFoundException("Account with Email " + currentUserEmail + "doesn't Exist");
                });
        log.info("This is current account {}", currentAccount);
        return currentAccount;
    }
}

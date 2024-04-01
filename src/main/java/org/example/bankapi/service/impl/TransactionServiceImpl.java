package org.example.bankapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankapi.entity.enums.TransactionType;
import org.example.bankapi.repository.AccountRepository;
import org.example.bankapi.repository.TransactionRepository;
import org.example.bankapi.entity.Account;
import org.example.bankapi.entity.Transaction;
import org.example.bankapi.exception.RecordNotFoundException;
import org.example.bankapi.model.transaction.TransactionRequest;
import org.example.bankapi.service.TransactionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public String deposit(TransactionRequest transactionRequest) {
        String cardNumber = transactionRequest.getCardNumber();
        double amount = transactionRequest.getAmount();
        log.info("you want to deposit Amount {}, for customer with card Number {}",
                amount, cardNumber);

        Account cardAccount = IsValidCard(cardNumber);
        double accountBalance = cardAccount.getBalance();
        log.info("current Balance for Account with card Number {}, is {}", cardNumber, accountBalance);
        cardAccount.setBalance(accountBalance + amount);
        accountRepository.save(cardAccount);
        log.info("Balance for Account with card Number {}, After update is {}", cardNumber, cardAccount.getBalance());

        saveTransaction(cardAccount, amount, TransactionType.DEPOSIT);
        return "success";
    }

    @Override
    public String withdraw(TransactionRequest transactionRequest) {
        String cardNumber = transactionRequest.getCardNumber();
        double amount = transactionRequest.getAmount();
        log.info("you want to withdraw Amount {}, from customer with card Number {}",
                amount, cardNumber);

        Account cardAccount= IsValidCard(cardNumber);
        double accountBalance = cardAccount.getBalance();
        log.info("current Balance for Account with card Number {}, is {}", cardNumber, accountBalance);

        if(accountBalance < amount){
            log.error("Insufficient Balance for withdraw the current balance is {}", accountBalance);
            throw new RecordNotFoundException("Insufficient Balance, The current balance is: "+ accountBalance);
        }

        cardAccount.setBalance(accountBalance - amount);
        accountRepository.save(cardAccount);
        log.info("Balance for Account with card Number {}, After update is {}", cardNumber, cardAccount.getBalance());

        saveTransaction(cardAccount, amount, TransactionType.WITHDRAW);
        return "success";
    }


    private Account IsValidCard(String cardNumber){
        Account account = accountRepository.findByCardNumber(cardNumber)
                .orElseThrow(() -> {
                    log.error("This Card Number Not Valid: {}", cardNumber);
                    return new RecordNotFoundException("This Card Number Not Valid "+ cardNumber);
                });
        log.info("This Card Number {}, is valid for this Account {}",cardNumber, account);
        return account;
    }

    private void saveTransaction(Account account, double amount, TransactionType transactionType){
        Transaction transaction = new Transaction();
        transaction.setTransaction_type(transactionType);
        transaction.setAccount(account);
        transaction.setCreated_at(LocalDateTime.now());
        transaction.setAmount(amount);
        transactionRepository.save(transaction);
        log.info("This Transaction Saved Successfully : {}", transaction);
    }

}

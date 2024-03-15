package org.example.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankapi.model.transaction.TransactionResponseDTO;
import org.example.bankapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("balance/{accountId}")
    public ResponseEntity<Double> viewAccountBalanceById(@PathVariable(name = "accountId") Long accountId){
        return new ResponseEntity<>(accountService.viewAccountBalanceById(accountId), HttpStatus.OK);
    }

    @GetMapping("transactions/{accountId}")
    public ResponseEntity<List<TransactionResponseDTO>>  viewAccountTransactionsById(@PathVariable(name = "accountId") Long accountId){
        return new ResponseEntity<>(accountService.viewAccountTransactionsById(accountId), HttpStatus.OK);
    }
}

package org.example.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankapi.model.transaction.TransactionRequest;
import org.example.bankapi.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/transaction")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("deposit")
    public ResponseEntity<String> deposit(@Validated @RequestBody TransactionRequest transactionRequest){
        return new ResponseEntity<>(transactionService.deposit(transactionRequest), HttpStatus.CREATED);
    }

    @PostMapping("withdraw")
    public ResponseEntity<String> withdraw(@Validated @RequestBody TransactionRequest transactionRequest){
        return new ResponseEntity<>(transactionService.withdraw(transactionRequest), HttpStatus.ACCEPTED);
    }
}

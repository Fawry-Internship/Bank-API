package org.example.bankapi.controller;

import lombok.RequiredArgsConstructor;
import org.example.bankapi.model.transaction.TransactionResponseDTO;
import org.example.bankapi.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:4300"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/bank/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping("balance")
    public ResponseEntity<Double> viewCurrentAccountBalance(){
        return new ResponseEntity<>(accountService.viewAuthenticatedAccountBalance(), HttpStatus.OK);
    }

    @GetMapping("transactions")
    public ResponseEntity<List<TransactionResponseDTO>> viewCurrentAccountTransactions(){
        return new ResponseEntity<>(accountService.viewAuthenticatedAccountTransactions(), HttpStatus.OK);
    }

    @GetMapping("cardNumber")
    public ResponseEntity<String> getCurrentAccountCardNumber(){
        return ResponseEntity.ok(accountService.getAuthenticatedAccountCardNumber());
    }
}

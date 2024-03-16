package org.example.bankapi.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.bankapi.model.account.AccountRequestDTO;
import org.example.bankapi.model.auth.AuthenticationRequest;
import org.example.bankapi.model.auth.AuthenticationResponse;
import org.example.bankapi.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bank/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@Valid @RequestBody AuthenticationRequest authenticationRequest){
        AuthenticationResponse response = authenticationService.authenticate(authenticationRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody AccountRequestDTO accountRequestDTO){
        AuthenticationResponse response = authenticationService.register(accountRequestDTO);
        return ResponseEntity.ok(response);
    }
}

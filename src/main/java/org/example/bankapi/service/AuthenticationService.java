package org.example.bankapi.service;

import org.example.bankapi.model.account.AccountRequestDTO;
import org.example.bankapi.model.auth.AuthenticationRequest;
import org.example.bankapi.model.auth.AuthenticationResponse;

public interface AuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest);

    AuthenticationResponse register(AccountRequestDTO accountRequestDTO);
}

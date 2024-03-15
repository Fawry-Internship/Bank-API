package org.example.bankapi.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.bankapi.repository.AccountRepository;
import org.example.bankapi.entity.Account;
import org.example.bankapi.exception.ConflictException;
import org.example.bankapi.mapper.AccountMapper;
import org.example.bankapi.model.account.AccountRequestDTO;
import org.example.bankapi.model.auth.AuthenticationRequest;
import org.example.bankapi.model.auth.AuthenticationResponse;
import org.example.bankapi.security.JWTService;
import org.example.bankapi.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final AccountRepository accountRepository;
    private final JWTService jwtService;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;
    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest authenticationRequest) {
        log.info("user wants to login with this credentials {}", authenticationRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );

        Account userAccount = accountRepository.findByEmail(authenticationRequest.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found"));

        var jwtToken = jwtService.generateToken(authentication);
        log.info("The user has login successfully");
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .id(userAccount.getId())
                .email(userAccount.getEmail())
                .name(userAccount.getName())
                .build();
    }

    @Override
    public AuthenticationResponse register(AccountRequestDTO accountRequestDTO) {
        Account userAccount = accountMapper.toEntity(accountRequestDTO);
        String cardNumber = generateCardNumber();
        userAccount.setCardNumber(cardNumber);
        log.info("This Card Number {}", cardNumber);
        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        log.info("This is UserAccount {}", userAccount);

        if(accountRepository.findByEmail(accountRequestDTO.getEmail()).isPresent()){
            log.error("this Account already exist");
            throw new ConflictException("this Account already exist");
        }

        accountRepository.save(userAccount);
        log.info("Account Added Successfully {}", userAccount);
        return authenticate(new AuthenticationRequest(accountRequestDTO.getEmail(), accountRequestDTO.getPassword()));
    }

    private static String generateCardNumber() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().replaceAll("-", "").substring(0, 16);
    }

}

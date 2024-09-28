package org.example.bankapi.repository;

import lombok.RequiredArgsConstructor;
import org.example.bankapi.entity.Account;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AccountRepositoryTest {

    @Autowired
    private AccountRepository accountRepository;
    private Account account;

    @AfterEach
    public void tearDown () {
        accountRepository.deleteAll();
    }
    @BeforeEach
    public void setup() {
        account = new Account();
        account.setId(1);
        account.setName("Sameh");
        account.setEmail("sameh.tarek.fci@gmail.com");
        account.setCardNumber("0101111111111111");
        account.setBalance(222);
        account.setPassword("12345678");

        accountRepository.save(account);
    }

    @Test
    public void testFindByCardNumber () {
        //Arrange and Act
        Account account1 = accountRepository.findByCardNumber(account.getCardNumber())
                .orElseThrow();

        //Assert
        assertNotNull(account1);
    }

    @Test
    public void testFindByEmail () {
        //Arrange and Act
        Account account1 = accountRepository.findByEmail(account.getEmail())
                .orElseThrow();

        //Assert
        assertNotNull(account1);
    }

}
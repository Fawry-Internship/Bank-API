package org.example.bankapi.Repository;

import org.example.bankapi.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends
        JpaRepository<Transaction, Long> {
}

package org.example.bankapi.mapper;

import org.example.bankapi.entity.Transaction;
import org.example.bankapi.model.transaction.TransactionResponseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    TransactionResponseDTO toDTO(Transaction transaction);
}

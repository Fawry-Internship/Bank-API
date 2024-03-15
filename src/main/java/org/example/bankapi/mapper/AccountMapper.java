package org.example.bankapi.mapper;

import org.example.bankapi.entity.Account;
import org.example.bankapi.model.account.AccountRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {
    @Mapping(target = "cardNumber", ignore = true)
    Account toEntity(AccountRequestDTO accountRequestDTO);
    AccountRequestDTO toDTO(Account account);
}

package com.myprojects.digital.account.controllers.dto.accounts;

import com.myprojects.digital.account.models.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDTOMapper {
    public Account mapDtoToAccount(AccountDTO accountDTO) {
        return new Account(accountDTO.id(), accountDTO.name(), accountDTO.document(), accountDTO.availableValue());
    }

    public AccountDTO mapAccountToDTO(Account account) {
        return new AccountDTO(account.getId(), account.getName(), account.getDocument(), account.getAvailableValue());
    }
}

package com.myprojects.digital.account.controllers.dto.accounts;

import com.myprojects.digital.account.models.entities.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountDTOMapper {
    public static Account mapCreateRequestToAccount(AccountCreatRequest accountCreatRequest) {
        return new Account(accountCreatRequest.id(), accountCreatRequest.name(), accountCreatRequest.document(), accountCreatRequest.availableValue());
    }

    public static AccountCreatResponse mapAccountToCreateResponse(Account account) {
        return new AccountCreatResponse(account.getId(), account.getName(), account.getDocument(), account.getAvailableValue());
    }
}

package com.myprojects.digital.account.controllers;

import com.myprojects.digital.account.controllers.dto.accounts.AccountCreatRequest;
import com.myprojects.digital.account.controllers.dto.accounts.AccountCreatResponse;
import com.myprojects.digital.account.controllers.dto.accounts.AccountDTOMapper;
import com.myprojects.digital.account.controllers.dto.transfer.TransferResponse;
import com.myprojects.digital.account.controllers.dto.transfer.TransferDTOMapper;
import com.myprojects.digital.account.models.entities.Account;
import com.myprojects.digital.account.models.entities.Transfer;
import com.myprojects.digital.account.services.AccountService;
import com.myprojects.digital.account.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransferService transferService;
    private final TransferDTOMapper transferDTOMapper;

    @Autowired
    public AccountController(AccountService accountService, TransferService transferService, TransferDTOMapper transferDTOMapper) {
        this.accountService = accountService;
        this.transferService = transferService;
        this.transferDTOMapper = transferDTOMapper;
    }

    @PostMapping
    public ResponseEntity<AccountCreatResponse> createAccount(@RequestBody @Valid AccountCreatRequest accountCreatRequest) {

        Account newAccount = accountService.createAccount(AccountDTOMapper.mapCreateRequestToAccount(accountCreatRequest));
        AccountCreatResponse newAccountResponse = AccountDTOMapper.mapAccountToCreateResponse(newAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccountResponse);
    }

    @GetMapping("/{id}/transaction-history")
    public ResponseEntity<List<TransferResponse>> findAllAccounts(@PathVariable Long accountId) {
        List<Transfer> transferHistory = transferService.getTransactionHistory(accountId);
        List<TransferResponse> transferHistoryDTO = transferHistory.stream()
                .map(transferDTOMapper::mapTransferToTransferResonse)
                .toList();
        return ResponseEntity.ok(transferHistoryDTO);
    }
    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts() {
        List<Account> allAccounts = accountService.findAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

}

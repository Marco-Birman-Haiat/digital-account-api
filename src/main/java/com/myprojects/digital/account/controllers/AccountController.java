package com.myprojects.digital.account.controllers;

import com.myprojects.digital.account.controllers.dto.accounts.AccountDTO;
import com.myprojects.digital.account.controllers.dto.accounts.AccountDTOMapper;
import com.myprojects.digital.account.controllers.dto.transfer.TransferDTO;
import com.myprojects.digital.account.controllers.dto.transfer.TransferDTOMapper;
import com.myprojects.digital.account.models.entities.Account;
import com.myprojects.digital.account.models.entities.Transfer;
import com.myprojects.digital.account.services.AccountService;
import com.myprojects.digital.account.services.TransferService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;
    private final TransferService transferService;
    private final AccountDTOMapper accountDTOMapper;
    private final TransferDTOMapper transferDTOMapper;

    @Autowired
    public AccountController(AccountService accountService, TransferService transferService,
                             AccountDTOMapper accountDTOMapper, TransferDTOMapper transferDTOMapper) {
        this.accountService = accountService;
        this.transferService = transferService;
        this.accountDTOMapper = accountDTOMapper;
        this.transferDTOMapper = transferDTOMapper;
    }

    @PostMapping
    public ResponseEntity<AccountDTO> createAccount(@RequestBody @Valid AccountDTO accountDTO) {

        Account newAccount = accountService.createAccount(accountDTOMapper.mapDtoToAccount(accountDTO));
        AccountDTO newAccountDTO = accountDTOMapper.mapAccountToDTO(newAccount);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccountDTO);
    }

    @GetMapping("/{id}/transaction-history")
    public ResponseEntity<List<TransferDTO>> findAllAccounts(@PathVariable Long id) {
        List<Transfer> transferHistory = transferService.getTransactionHistory(id);
        List<TransferDTO> transferHistoryDTO = transferHistory.stream()
                .map(transferDTOMapper::mapTransferToDTO)
                .toList();
        return ResponseEntity.ok(transferHistoryDTO);
    }
    @GetMapping
    public ResponseEntity<List<Account>> findAllAccounts() {
        List<Account> allAccounts = accountService.findAllAccounts();
        return ResponseEntity.ok(allAccounts);
    }

}

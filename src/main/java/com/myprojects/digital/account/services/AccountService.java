package com.myprojects.digital.account.services;

import com.myprojects.digital.account.controllers.dto.transfer.TransferRequest;
import com.myprojects.digital.account.exceptions.AccountNotFoundException;
import com.myprojects.digital.account.exceptions.DocumentInUseException;
import com.myprojects.digital.account.exceptions.InsufficientValueException;
import com.myprojects.digital.account.models.entities.Account;
import com.myprojects.digital.account.models.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account createAccount(Account account) {
        try {
            return accountRepository.save(account);
        } catch (Exception e) {
            throw new DocumentInUseException("document already in use");
        }
    }

    public Account findByDocument(String document) {
        Optional<Account> optionalFoundAccount = accountRepository.findByDocument(document);

        if (optionalFoundAccount.isEmpty()) {
            throw new AccountNotFoundException("document not found");
        }

        return optionalFoundAccount.get();
    }

    public BigDecimal credit(Long id, BigDecimal value) {
        Optional<Account> optionalFoundAccount = accountRepository.findById(id);

        if (optionalFoundAccount.isEmpty()) {
            throw new AccountNotFoundException("sender account not found");
        }

        Account accountToUpdate = optionalFoundAccount.get();
        accountToUpdate.setAvailableValue(accountToUpdate.getAvailableValue().add(value));
        Account updatedAccount = accountRepository.save(accountToUpdate);
        return updatedAccount.getAvailableValue();

    }

    public BigDecimal debit(Long id, BigDecimal value) {
        Optional<Account> optionalFoundAccount = accountRepository.findById(id);

        if (optionalFoundAccount.isEmpty()) {
            throw new AccountNotFoundException("sender account not found");
        }

        Account accountToUpdate = optionalFoundAccount.get();
        if (value.compareTo(accountToUpdate.getAvailableValue()) == 1) {
            throw new InsufficientValueException("unavailable funds");
        }

        accountToUpdate.setAvailableValue(accountToUpdate.getAvailableValue().subtract(value));
        Account updatedAccount = accountRepository.save(accountToUpdate);
        return updatedAccount.getAvailableValue();
    }

    public Optional<Account> findAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public List<Account> findAllAccounts() {
        return accountRepository.findAll();
    }

}

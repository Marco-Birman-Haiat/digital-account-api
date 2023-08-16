package com.myprojects.digital.account.models.repositories;

import com.myprojects.digital.account.models.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByDocument(String document);
}

package com.myprojects.digital.account.models.repositories;

import com.myprojects.digital.account.models.entities.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransfersRepository extends JpaRepository<Transfer, Long> {

    @Query(
            value = "SELECT t.* FROM transfers AS t " +
                    "WHERE t.sender_id = :accountId " +
                    "OR t.receiver_id = :accountId",
            nativeQuery = true
    )
    List<Transfer> findAllBySenderIdOrdReceiverId(Long accountId);
}

package com.myprojects.digital.account.services;

import com.myprojects.digital.account.controllers.dto.transfer.TransferDTOMapper;
import com.myprojects.digital.account.controllers.dto.transfer.TransferCreatedDTO;
import com.myprojects.digital.account.controllers.dto.transfer.TransferRequest;
import com.myprojects.digital.account.exceptions.DuplicatedTransferException;
import com.myprojects.digital.account.models.entities.Account;
import com.myprojects.digital.account.models.entities.Transfer;
import com.myprojects.digital.account.models.repositories.TransfersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.List;

@Service
public class TransferService {

    private final TransfersRepository transfersRepository;
    private final AccountService accountService;

    @Autowired
    public TransferService(TransfersRepository transfersRepository, AccountService accountService) {
        this.transfersRepository = transfersRepository;
        this.accountService = accountService;
    }

    public Transfer initiateTransfer(TransferRequest transferRequest) {
        Account senderAccount = accountService.findByDocument(transferRequest.senderDocument());
        Account receiverAccount = accountService.findByDocument(transferRequest.receiverDocument());

        validateTransfer(transferRequest, senderAccount, receiverAccount);
        Transfer createdTransfer = executeTransferOperations(senderAccount, receiverAccount, transferRequest);

        return createdTransfer;

    }

    private void validateTransfer(TransferRequest transferRequest, Account senderAccount, Account receiverAccount) {
        List<Transfer> transactionHistory = getTransactionHistory(senderAccount.getId());
        Instant now = Clock.systemDefaultZone().instant();

        List<Transfer> duplicateTransfers = transactionHistory.stream()
                .filter((t) -> t.getSenderAccount().getId().equals(senderAccount.getId())
                    &&  t.getReceiverAccount().getId().equals(receiverAccount.getId())
                    && t.getValue().compareTo(transferRequest.value()) == 0
                    && Duration.between(t.getCreatedOn(), now).toMillis() < 120000)
                .toList();

        if (!duplicateTransfers.isEmpty()) {
            throw new DuplicatedTransferException("transfer is possibly duplicated");
        }
    }

    public List<Transfer> getTransactionHistory(Long id) {
        return transfersRepository.findAllBySenderIdOrdReceiverId(id);
    }

    @Transactional
    public Transfer executeTransferOperations(Account senderAccount, Account receiverAccount, TransferRequest transferRequest) {
        accountService.credit(receiverAccount.getId(), transferRequest.value());
        accountService.debit(senderAccount.getId(), transferRequest.value());

        Transfer transferToExecute = new Transfer(transferRequest.id(), senderAccount,
                receiverAccount, Clock.systemDefaultZone().instant(), transferRequest.value());
        Transfer createdTransfer = transfersRepository.save(transferToExecute);
        return createdTransfer;
    }

}

package com.myprojects.digital.account.controllers;

import com.myprojects.digital.account.controllers.dto.transfer.TransferCreatedDTO;
import com.myprojects.digital.account.controllers.dto.transfer.TransferDTO;
import com.myprojects.digital.account.controllers.dto.transfer.TransferDTOMapper;
import com.myprojects.digital.account.controllers.dto.transfer.TransferRequest;
import com.myprojects.digital.account.models.entities.Transfer;
import com.myprojects.digital.account.services.TransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/transfers")
public class TransferController {

    private TransferService transferService;
    private TransferDTOMapper transferDTOMapper;

    @Autowired
    public TransferController(TransferService transferService, TransferDTOMapper transferDTOMapper) {
        this.transferService = transferService;
        this.transferDTOMapper = transferDTOMapper;
    }

    @PostMapping
    public ResponseEntity<TransferCreatedDTO> createTransfer(@RequestBody TransferRequest transferRequest) {
        Transfer createdTransfer = transferService.initiateTransfer(transferRequest);
        TransferCreatedDTO createdTransferDTO = transferDTOMapper.mapTransferToCreatedResponse(createdTransfer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTransferDTO);
    }
}

package com.myprojects.digital.account.controllers.dto.transfer;

import com.myprojects.digital.account.models.entities.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferDTOMapper {

    public TransferCreatedResponse mapTransferToCreatedResponse(Transfer transfer) {
        return new TransferCreatedResponse(transfer.getId(), transfer.getSenderAccount().getAvailableValue(),
                transfer.getSenderAccount().getDocument(), transfer.getReceiverAccount().getDocument(), transfer.getCreatedOn());
    }
    
    public TransferResponse mapTransferToTransferResonse(Transfer transfer) {
        return new TransferResponse(transfer.getSenderAccount().getDocument(),
                transfer.getSenderAccount().getDocument(), transfer.getValue(), transfer.getCreatedOn());
    }
}

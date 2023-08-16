package com.myprojects.digital.account.controllers.dto.transfer;

import com.myprojects.digital.account.models.entities.Account;
import com.myprojects.digital.account.models.entities.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferDTOMapper {

    public TransferCreatedDTO mapTransferToCreatedResponse(Transfer transfer) {
        return new TransferCreatedDTO(transfer.getId(), transfer.getSenderAccount().getAvailableValue(),
                transfer.getSenderAccount().getDocument(), transfer.getReceiverAccount().getDocument(), transfer.getCreatedOn());
    }
    
    public TransferDTO mapTransferToDTO(Transfer transfer) {
        return new TransferDTO(transfer.getSenderAccount().getDocument(),
                transfer.getSenderAccount().getDocument(), transfer.getValue(), transfer.getCreatedOn());
    }
}

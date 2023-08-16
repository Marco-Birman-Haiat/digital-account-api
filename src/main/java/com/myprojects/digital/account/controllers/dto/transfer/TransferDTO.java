package com.myprojects.digital.account.controllers.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

public record TransferDTO(

        @JsonProperty("sender-document")
        String senderDocument,
        @JsonProperty("receiver-document")
        String receiverDocument,
        BigDecimal value,
        Instant datetime
) {
}

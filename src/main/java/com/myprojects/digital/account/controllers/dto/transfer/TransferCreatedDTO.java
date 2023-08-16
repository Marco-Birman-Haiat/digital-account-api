package com.myprojects.digital.account.controllers.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.Instant;

public record TransferCreatedDTO(
        Long id,
        @JsonProperty("available-value")
        BigDecimal availableValue,
        @JsonProperty("sender-document")
        String senderDocument,
        @JsonProperty("receiver-document")
        String receiverDocument,
        Instant datetime
        ) {
}

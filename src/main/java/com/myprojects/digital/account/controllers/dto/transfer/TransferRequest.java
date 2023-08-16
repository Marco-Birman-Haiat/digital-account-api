package com.myprojects.digital.account.controllers.dto.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record TransferRequest(

        Long id,
        @JsonProperty("sender-document")
        String senderDocument,
        @JsonProperty("receiver-document")
        String receiverDocument,
        BigDecimal value) {
}

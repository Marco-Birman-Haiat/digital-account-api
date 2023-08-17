package com.myprojects.digital.account.controllers.dto.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record AccountCreatResponse(
        Long id,
        String name,
        String document,
        @JsonProperty("available-value")
        BigDecimal availableValue) {
}

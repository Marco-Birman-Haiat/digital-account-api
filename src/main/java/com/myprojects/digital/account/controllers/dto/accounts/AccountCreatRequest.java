package com.myprojects.digital.account.controllers.dto.accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.myprojects.digital.account.models.entities.Account;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;


public record AccountCreatRequest(
        Long id,
        @Size(min = 2)
        @NotNull(message = "name should not be null or blank")
        String name,
        @Size(min = 1)
        @NotNull(message = "document should not be null or blank")
        String document,
        @NotNull(message = "available-value should not be null")
        @JsonProperty("available-value")
        BigDecimal availableValue) {
}

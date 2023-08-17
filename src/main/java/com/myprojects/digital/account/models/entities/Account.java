package com.myprojects.digital.account.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myprojects.digital.account.utils.BrSocialSecurity;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;


@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(unique = true)
    private BrSocialSecurity document;

    @OneToMany(mappedBy = "senderAccount")
    @JsonIgnore
    private List<Transfer> transfersSent;

    @OneToMany(mappedBy = "receiverAccount")
    @JsonIgnore
    private List<Transfer> transfersReceived;

    @JsonProperty("available-value")
    @Column(name = "available_value")
    private BigDecimal availableValue;

    public Account() {
    }

    public Account(Long id, String name, String document, BigDecimal availableValue) {
        this.id = id;
        this.name = name;
        this.document = new BrSocialSecurity(document);
        this.availableValue = availableValue;
    }

    public List<Transfer> getTransfersSent() {
        return transfersSent;
    }

    public void setTransfersSent(List<Transfer> transfersSent) {
        this.transfersSent = transfersSent;
    }

    public List<Transfer> getTransfersReceived() {
        return transfersReceived;
    }

    public void setTransfersReceived(List<Transfer> transfersReceived) {
        this.transfersReceived = transfersReceived;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document.getValue();
    }

    public void setDocument(String document) {
        this.document.setValue(document);
    }

    public BigDecimal getAvailableValue() {
        return availableValue;
    }

    public void setAvailableValue(BigDecimal availableValue) {
        this.availableValue = availableValue;
    }
}

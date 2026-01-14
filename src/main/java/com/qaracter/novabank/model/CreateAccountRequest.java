package com.qaracter.novabank.model;

public class CreateAccountRequest {
    private String ownerName;
    private String iban;

    public CreateAccountRequest() {
    }

    public CreateAccountRequest(String ownerName, String iban) {
        this.ownerName = ownerName;
        this.iban = iban;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }
}

package com.qaracter.novabank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.qaracter.novabank.exception.InvalidAmountException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String type;
    @NotNull
    @Min(value = 1L, message = "Amount must be positive")
    private Double amount;

    @NotNull
    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "account_id")
    @JsonIgnore
    private Account account;

    public Transaction() {}

    public Transaction(String type, Double amount, Account account) {
        if (amount == null || amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }
        if (!"DEPOSIT".equals(type) && !"WITHDRAW".equals(type)) {
            throw new IllegalArgumentException("Invalid transaction type");
        }

        this.type = type;
        this.amount = amount;
        this.account = account;
        this.date = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
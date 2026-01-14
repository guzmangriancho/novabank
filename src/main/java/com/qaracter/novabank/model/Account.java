package com.qaracter.novabank.model;


import com.qaracter.novabank.exception.InsufficientBalanceException;
import com.qaracter.novabank.exception.InvalidAmountException;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name cannot be empty")
    private String ownerName;

    @Column(unique = true)
    @NotBlank
    @Pattern(regexp = "^ES\\d{22}$", message = "IBAN must start with ES")
    private String iban;

    @Min(value = 0L, message = "Balance cannot be negative")
    private Double balance;

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    private List<Transaction> transactions = new ArrayList<>();

    public Account() {}

    public Account(String ownerName, String iban) {
        this.ownerName = ownerName;
        this.iban = iban;
        this.balance = 0.0;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void deposit(Double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        Transaction transaction = new Transaction("DEPOSIT", amount, this);
        this.transactions.add(transaction);
        this.balance += amount;
    }

    public void withdraw(Double amount) {
        if (amount <= 0) {
            throw new InvalidAmountException("Amount must be positive");
        }

        if (this.balance < amount) {
            throw new InsufficientBalanceException("Insufficient balance");
        }

        Transaction transaction = new Transaction("WITHDRAW", amount, this);
        this.transactions.add(transaction);
        this.balance -= amount;
    }

}

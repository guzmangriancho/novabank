package com.qaracter.novabank.service;

import com.qaracter.novabank.exception.InvalidIbanException;
import com.qaracter.novabank.exception.AccountNotFoundException;
import com.qaracter.novabank.model.Account;
import com.qaracter.novabank.model.CreateAccountRequest;
import com.qaracter.novabank.model.Transaction;
import com.qaracter.novabank.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(CreateAccountRequest request) {
        if (request.getOwnerName() == null) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (!request.getIban().matches("^ES\\d{22}$")) {
            throw new InvalidIbanException("Invalid IBAN");
        }
        if(accountRepository.findByIban(request.getIban()) != null){
            throw new InvalidIbanException("Account with that IBAN already exists");
        }

        Account account = new Account(request.getOwnerName(), request.getIban());
        return accountRepository.save(account);
    }

    public Account getAccount(Long id) {
        Account account = accountRepository.findById(id).orElse(null);
        if (account == null) {
            throw new AccountNotFoundException("Account not found");
        }
        return account;
    }

    public Account deposit(Long id, Double amount) {
        Account account = getAccount(id);
        account.deposit(amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Long id, Double amount) {
        Account account = getAccount(id);
        account.withdraw(amount);
        return accountRepository.save(account);
    }

    public List<Transaction> getTransactions(Long id) {
        Account account = getAccount(id);
        return account.getTransactions();
    }
}
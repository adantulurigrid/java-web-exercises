package com.bobocode.web.controller;

import com.bobocode.dao.AccountDao;
import com.bobocode.model.Account;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountRestController {

    private final AccountDao accountDao;

    public AccountRestController(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @GetMapping
    public List<Account> findAll() {
        return accountDao.findAll();
    }

    @GetMapping("/{id}")
    public Account findById(@PathVariable long id) {
        return accountDao.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account create(@RequestBody Account account) {
        return accountDao.save(account);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable long id,
                       @RequestBody Account account) {

        if (!idEquals(id, account.getId())) {
            throw new IllegalStateException("Path id and account id do not match");
        }

        accountDao.save(account);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        Account account = accountDao.findById(id);
        accountDao.remove(account);
    }

    private boolean idEquals(long pathId, Long accountId) {
        return accountId != null && pathId == accountId;
    }
}
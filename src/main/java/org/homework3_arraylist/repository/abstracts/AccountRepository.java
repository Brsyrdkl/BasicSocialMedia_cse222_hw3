package org.homework3_arraylist.repository.abstracts;

import org.homework3_arraylist.entities.concretes.Account;

import java.util.List;

public interface AccountRepository {

    List<Account> getAccounts();
    Account getById(int id);
    Account add(Account account);
    Account update(int id,Account account);
    void delete(int id);

    boolean isLoggedIn(Account account);

    void setLoggedIn(Account account, boolean loggedIn);

    boolean isLoggedOnce();

    void setLoggedOnce(boolean loggedOnce);
}

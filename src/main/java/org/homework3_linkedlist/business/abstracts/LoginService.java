package org.homework3_linkedlist.business.abstracts;

import org.homework3_linkedlist.entities.concretes.Account;

public interface LoginService {


    void checkLoggedIn(Account account);

    void login(Account account);

    void logout(Account account);

    boolean isLoggedIn(Account account);
}

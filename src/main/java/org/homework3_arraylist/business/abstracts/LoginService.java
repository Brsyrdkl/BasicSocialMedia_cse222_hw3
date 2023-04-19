package org.homework3_arraylist.business.abstracts;

import org.homework3_arraylist.entities.concretes.Account;

public interface LoginService {


    void checkLoggedIn(Account account);

    void login(Account account);

    void logout(Account account);

    boolean isLoggedIn(Account account);
}

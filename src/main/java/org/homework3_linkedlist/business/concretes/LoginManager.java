package org.homework3_linkedlist.business.concretes;

import org.homework3_linkedlist.business.abstracts.LoginService;
import org.homework3_linkedlist.entities.concretes.Account;
import org.homework3_linkedlist.repository.abstracts.AccountRepository;

public class LoginManager implements LoginService {

    AccountRepository accountRepository;

    public LoginManager(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    /**
     * @param account
     * Account can log in with this method
     * Checks if another account is logged
     */
    @Override
    public void login(Account account){
        if(account.getLoggedIn() || accountRepository.isLoggedIn(account)) throw new RuntimeException("You can't login! Account is already logged");
        else {
            if(!accountRepository.isLoggedOnce()) {
                System.out.println("...Logging into an account (username: " + account.getUsername() + ")");
            }
            else{
                System.out.println("...Logging into another account (username: " + account.getUsername() + ")");
            }
            account.setLoggedIn(true);
            accountRepository.setLoggedIn(account,true);
            accountRepository.setLoggedOnce(true);
        }
    }
    /**
     * @param account
     * Account can logout with this method
     * Checks if account is logged
     */
    @Override
    public void logout(Account account){
        if(!account.getLoggedIn() || !accountRepository.isLoggedIn(account)) throw new RuntimeException("Account is not logged! Login first");
        else {
            System.out.println("\n...Logging out from account '" + account.getUsername() + "'");
            account.setLoggedIn(false);
            accountRepository.setLoggedIn(account,false);
        }
    }

    @Override
    public boolean isLoggedIn(Account account) {
        return account.getLoggedIn();
    }

    @Override
    public void checkLoggedIn(Account account) {
        if (!isLoggedIn(account)) {
            throw new IllegalStateException("Account is not currently logged in.");
        }
    }

}

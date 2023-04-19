package org.homework3_linkedlist.repository.concretes;

import org.homework3_linkedlist.entities.concretes.Account;
import org.homework3_linkedlist.repository.abstracts.AccountRepository;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAccountRepository implements AccountRepository {

    List<Account> accounts;

    private boolean isLoggedIn;
    private boolean isLoggedOnce;

    public InMemoryAccountRepository(){
        System.out.println("...Creating Account...");
        accounts = new ArrayList<Account>();
        accounts.add(new Account(1,"gizemsungu","10.03.1995","Istanbul"));
        accounts.add(new Account(2,"sibelgulmez","31.01.1990","Kocaeli"));
        accounts.add(new Account(3,"gokhankaya","15.07.1993","Kirklareli"));
        accounts.add(new Account(4,"barisyurdakul","11.03.1995","Hatay"));
        accounts.add(new Account(5,"cihansen","21.01.1994","Manisa"));
        accounts.add(new Account(6,"ahmetdogan","13.07.1993","Samsun"));
        accounts.add(new Account(7,"asrinumur","10.08.1995","Aðrý"));
        accounts.add(new Account(8,"barkingencal","13.01.1980","Izmir"));
        accounts.add(new Account(9,"cavitisler","10.07.1999","Antalya"));
        accounts.add(new Account(10,"barisyayla","19.11.1993","Balýkesir"));
    }

    @Override
    public List<Account> getAccounts() {
        return accounts;
    }

    @Override
    public Account getById(int id) {
        return accounts.get(id-1);
    }

    @Override
    public Account add(Account account) {
        accounts.add(account);
        return account;
    }

    @Override
    public Account update(int id, Account account) {
        return accounts.set(id - 1, account);
    }

    @Override
    public void delete(int id) {
        accounts.remove(id - 1);
    }

    @Override
    public boolean isLoggedIn(Account account) {
        return account.getLoggedIn();
    }
    @Override
    public void setLoggedIn(Account account, boolean loggedIn) {
        account.setLoggedIn(loggedIn);
    }

    @Override
    public boolean isLoggedOnce() {
        return isLoggedOnce;
    }
    @Override
    public void setLoggedOnce(boolean loggedOnce) {
        isLoggedOnce = loggedOnce;
    }
}

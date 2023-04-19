package org.homework3_arraylist.tests;

import org.homework3_arraylist.business.concretes.AccountManager;
import org.homework3_arraylist.business.concretes.LoginManager;
import org.homework3_arraylist.entities.concretes.Account;
import org.homework3_arraylist.repository.abstracts.AccountRepository;
import org.homework3_arraylist.repository.concretes.InMemoryAccountRepository;

import java.util.ArrayList;
import java.util.List;

public class TestClass2 {

    public static void main(String[] args){

        AccountRepository accountRepository = new InMemoryAccountRepository();

        LoginManager loginManager = new LoginManager(accountRepository);
        AccountManager accountManager = new AccountManager(accountRepository);

        Account account1 = accountRepository.getById(1);
        Account account2 = accountRepository.getById(2);
        Account account3 = accountRepository.getById(3);
        Account account4 = accountRepository.getById(4);
        Account account5 = accountRepository.getById(5);
        Account account6 = accountRepository.getById(6);
        Account account7 = accountRepository.getById(7);
        Account account8 = accountRepository.getById(8);
        Account account9 = accountRepository.getById(9);
        Account account10 = accountRepository.getById(10);

        loginManager.login(accountRepository.getById(2));
        List<String> contents = new ArrayList<>();
        contents.add("I like Java");
        contents.add("Java the coffee");
        accountManager.shareMultiplePost(account2,contents);
        accountManager.addFollower(account2,account1);
        accountManager.addFollower(account2,account3);
        loginManager.logout(account2);
        loginManager.login(account3);
        accountManager.viewProfile(account3,account2);
        accountManager.likePost(account3,1,account2);
        accountManager.commentPost(account3,1,account2,"me too");
        accountManager.addFollower(account3,account1);
        accountManager.addFollower(account3,account2);
        accountManager.sendMessage(account3,account1,"This homework is too easy");
        loginManager.logout(account3);
        loginManager.login(account1);
        accountManager.sendMessage(account1,account3,"I don't think so");
        accountManager.checkOutboxMessages(account1);
        accountManager.checkInboxMessages(account1);
        accountManager.viewInboxMessages(account1);
        accountManager.viewOutboxMessages(account1);
        accountManager.viewProfile(account1,account2);
        accountManager.viewPost(account1,account2);
        accountManager.viewInteraction(account1,account2);
        accountManager.likePost(account1,1,account2);
        accountManager.likePost(account1,2,account2);
        accountManager.viewInteraction(account1,account2);
        loginManager.logout(account1);

        /*TEST2*/

        loginManager.login(account1);
        accountManager.sharePost(account1,"I like Java");
        accountManager.sharePost(account1,"I gave up");
        loginManager.logout(account1);

        loginManager.login(account2);
        accountManager.viewProfile(account2,account1);
        accountManager.likePost(account2,1,account1);
        loginManager.logout(account2);

        loginManager.login(account3);
        accountManager.viewProfile(account3,account1);
        accountManager.commentPost(account3,2,account1,"Nice!");
        accountManager.sendMessage(account3,account1,"Hello");
        loginManager.logout(account3);

        loginManager.login(account1);
        accountManager.viewProfile(account1,account1);
        accountManager.viewInboxMessages(account1);
        loginManager.logout(account1);



    }
}

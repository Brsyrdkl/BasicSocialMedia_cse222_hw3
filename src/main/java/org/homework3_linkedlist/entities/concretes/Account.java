package org.homework3_linkedlist.entities.concretes;

import org.homework3_linkedlist.entities.concretes.Message;
import org.homework3_linkedlist.entities.concretes.Post;

import java.util.LinkedList;

public class Account {

    private int accountId;
    private String username;
    /* XX.YY.ZZZZ */
    private String birthdate;
    private String location;
    private boolean isLoggedIn;

    private LinkedList<Post> posts;

    private LinkedList<Message> messagesInbox;
    private LinkedList<Message> messagesOutbox;
    private LinkedList<Account> following;
    private LinkedList<Account> followers;

    private LinkedList<Account> blockedAccounts;

    public Account(){
        //Default Constructor
    }

    public Account(int accountId, String username, String birthdate, String location) {
        this.accountId = accountId;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
        this.isLoggedIn = false;

        this.posts = new LinkedList<Post>();
        this.messagesInbox = new LinkedList<Message>();//gelen
        this.messagesOutbox = new LinkedList<Message>();//giden
        this.following = new LinkedList<Account>();
        this.followers = new LinkedList<Account>();
        this.blockedAccounts = new LinkedList<Account>();

        System.out.println("An account with username " + username + " has been created");
    }

    public int getId() {
        return accountId;
    }

    public void setId(int accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean getLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.isLoggedIn = loggedIn;
    }

    public LinkedList<Post> getPosts() {
        return posts;
    }

    public void setPosts(LinkedList<Post> posts) {
        this.posts = posts;
    }

    public LinkedList<Message> getMessagesInbox() {
        return messagesInbox;
    }

    public void setMessagesInbox(LinkedList<Message> messagesInbox) {
        this.messagesInbox = messagesInbox;
    }

    public LinkedList<Message> getMessagesOutbox() {
        return messagesOutbox;
    }

    public void setMessagesOutbox(LinkedList<Message> messagesOutbox) {
        this.messagesOutbox = messagesOutbox;
    }

    public LinkedList<Account> getFollowing() {
        return following;
    }

    public void setFollowing(LinkedList<Account> following) {
        this.following = following;
    }

    public LinkedList<Account> getFollowers() {
        return followers;
    }

    public void setFollowers(LinkedList<Account> followers) {
        this.followers = followers;
    }

    public LinkedList<Account> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(LinkedList<Account> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }
}

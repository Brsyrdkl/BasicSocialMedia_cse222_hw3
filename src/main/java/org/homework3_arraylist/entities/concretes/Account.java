package org.homework3_arraylist.entities.concretes;

import java.util.ArrayList;

public class Account {

    private int accountId;
    private String username;
    /* XX.YY.ZZZZ */
    private String birthdate;
    private String location;
    private boolean isLoggedIn;

    private ArrayList<Post> posts;

    private ArrayList<Message> messagesInbox;
    private ArrayList<Message> messagesOutbox;
    private ArrayList<Account> following;
    private ArrayList<Account> followers;

    private ArrayList<Account> blockedAccounts;

    public Account(){
        //Default Constructor
    }

    public Account(int accountId, String username, String birthdate, String location) {
        this.accountId = accountId;
        this.username = username;
        this.birthdate = birthdate;
        this.location = location;
        this.isLoggedIn = false;

        this.posts = new ArrayList<Post>();
        this.messagesInbox = new ArrayList<Message>();//gelen
        this.messagesOutbox = new ArrayList<Message>();//giden
        this.following = new ArrayList<Account>();
        this.followers = new ArrayList<Account>();
        this.blockedAccounts = new ArrayList<Account>();

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

    public ArrayList<Post> getPosts() {
        return posts;
    }

    public void setPosts(ArrayList<Post> posts) {
        this.posts = posts;
    }

    public ArrayList<Message> getMessagesInbox() {
        return messagesInbox;
    }

    public void setMessagesInbox(ArrayList<Message> messagesInbox) {
        this.messagesInbox = messagesInbox;
    }

    public ArrayList<Message> getMessagesOutbox() {
        return messagesOutbox;
    }

    public void setMessagesOutbox(ArrayList<Message> messagesOutbox) {
        this.messagesOutbox = messagesOutbox;
    }

    public ArrayList<Account> getFollowing() {
        return following;
    }

    public void setFollowing(ArrayList<Account> following) {
        this.following = following;
    }

    public ArrayList<Account> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<Account> followers) {
        this.followers = followers;
    }

    public ArrayList<Account> getBlockedAccounts() {
        return blockedAccounts;
    }

    public void setBlockedAccounts(ArrayList<Account> blockedAccounts) {
        this.blockedAccounts = blockedAccounts;
    }
}

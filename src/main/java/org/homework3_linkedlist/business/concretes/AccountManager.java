package org.homework3_linkedlist.business.concretes;

import org.homework3_linkedlist.business.abstracts.AccountService;
import org.homework3_linkedlist.entities.concretes.Account;
import org.homework3_linkedlist.entities.concretes.Message;
import org.homework3_linkedlist.entities.concretes.Post;
import org.homework3_linkedlist.repository.abstracts.AccountRepository;

import java.util.LinkedList;
import java.util.List;

public class AccountManager implements AccountService {

    AccountRepository accountRepository;
    private final LinkedList<String> history;

    public AccountManager(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.history = new LinkedList<String>();
    }

    @Override
    public void addAccount(Account account) {
        accountRepository.add(account);
    }

    @Override
    public void addMultipleAccounts(List<Account> accounts) {
        for (Account account : accounts) {
            addAccount(account);
        }
    }

    @Override
    public void follow(Account followerAccount,Account followingAccount) {
        checkLoggedIn(followerAccount);
        checkBlocked(followerAccount,followingAccount);
        if (!isFollowing(followerAccount,followingAccount)) {
            addFollower(followerAccount,followingAccount);
            System.out.println("...You are now following " + followingAccount.getUsername());
            history.add("You followed " + followingAccount.getUsername());
        }
        else {
            System.out.println("...You are already following " + followingAccount.getUsername());
        }
    }

    /**
     * @param followingAccount,followerAccount;
     * Account can unfollow with this method
     * Checks if another account is logged
     */
    @Override
    public void unfollow(Account followerAccount,Account followingAccount) {
        checkLoggedIn(followerAccount);
        checkBlocked(followerAccount,followingAccount);
        if (isFollowing(followerAccount,followingAccount)) {
            removeFollower(followerAccount,followingAccount);
            System.out.println("You have unfollowed " + followingAccount.getUsername());
            history.add("You unfollowed " + followingAccount.getUsername());
        }
        else {
            System.out.println("You are not currently following " + followingAccount.getUsername());
        }
    }

    /**
     * @param followingAccount,followerAccount;
     * Boolean for follow methods
     */
    @Override
    public boolean isFollowing(Account followerAccount,Account followingAccount) {
        boolean result = false;
        for (int i = 0 ; i < followerAccount.getFollowing().size() ; i++) {
            if (followerAccount.getFollowing().get(i).equals(followingAccount)){
                result = true;
            }
        }
        return result;
    }

    /**
     * Adds follower to the accounts
     * @param followingAccount,followerAccount;
     */
    @Override
    public void addFollower(Account followerAccount, Account followingAccount) {
        checkLoggedIn(followerAccount);
        System.out.println("Following  "+ followingAccount.getUsername());
        followingAccount.getFollowers().add(followerAccount);
        followerAccount.getFollowing().add(followingAccount);
    }

    @Override
    public void addMultipleFollower(Account followerAccount,List<Account> accounts) {
        checkLoggedIn(followerAccount);
        //----------------------------CHECKS ACCOUNT ITSELF
        for(Account account : accounts){
            if(account.getId() == followerAccount.getId()){
                throw new RuntimeException("Account cannot follow itself");
            }
        }
        //-------------------------------CHECKS FOLLOWS TWICE
        for(int i = 1 ; i < accountRepository.getAccounts().size() ; i++){
            Account tempAccount;
            tempAccount = accountRepository.getAccounts().get(i);
            if(tempAccount.equals(accountRepository.getAccounts().get(i-1))) throw new RuntimeException("Account cannot follow twice");
        }
        //----------------------------------------FOLLOWING MULTIPLE ACCOUNT
        System.out.print("...Following ");
        for (int i = 0; i < accounts.size() - 1; i++) {
            System.out.print(accountRepository.getAccounts().get(i).getUsername() + " ");
        }
        System.out.print("and " + accounts.get(accounts.size() - 1).getUsername());
        for (Account account : accounts) {
            addFollower(followerAccount,account);
        }
    }

    @Override
    public void removeFollower(Account removerAccount,Account removingAccount) {

        removingAccount.getFollowers().remove(removerAccount);
        removerAccount.getFollowing().remove(removingAccount);
    }

    @Override
    public void removeMultipleFollower(Account removerAccount, List<Account> accounts){

        for (Account account : accounts) {
            removeFollower(removerAccount,account);
        }
    }


    @Override
    public void shareMultiplePost(Account account, List<String> contents) {
        checkLoggedIn(account);
        for (String content : contents) {
            Post post = new Post(account.getPosts().size() + 1, account.getId(), content);
            account.getPosts().add(post);
        }
        System.out.println("...Sharing "+ contents.size() + " posts");
        history.add("You posted " + contents.size() + " post(s)");
    }

    @Override
    public void blockAccount(Account blockerAccount, Account blockingAccount) {
        checkLoggedIn(blockerAccount);
        if(blockerAccount.getBlockedAccounts().contains(blockingAccount)){
            System.out.println("Account is already blocked");
        }
        else {
            System.out.println("\n...Blocking account '" + blockerAccount.getUsername() + "'");
            blockerAccount.getBlockedAccounts().add(blockingAccount);
            blockingAccount.getBlockedAccounts().add(blockerAccount);//other account cant also access this account
            history.add("You blocked " + blockingAccount.getUsername());
        }
    }

    @Override
    public void unBlockAccount(Account blockerAccount, Account blockingAccount) {
        checkLoggedIn(blockerAccount);
        if(!blockerAccount.getBlockedAccounts().contains(blockingAccount)) {
            System.out.println("Account is not blocked");
        }
        else {
            System.out.println("\n...Unblocking account '" + blockerAccount.getUsername() + "'");
            blockerAccount.getBlockedAccounts().remove(blockingAccount);
            blockingAccount.getBlockedAccounts().remove(blockerAccount);
            history.add("You unblocked " + blockingAccount.getUsername());
        }
    }

    public void checkLoggedIn(Account account){
        if(!account.getLoggedIn())  throw new IllegalStateException("Account is not currently logged in.");
    }

    public void checkBlocked(Account account1, Account account2){
        if(account1.getBlockedAccounts().contains(account2)){
            throw new RuntimeException("Account is blocked");
        }
    }


    /**
     * @return str
     * Prints follower string
     */
    @Override
    public String printFollower(Account account){
        int size = account.getFollowers().size();
        String str = "";
        for(int i=0; i<size; i++) {
            str += account.getFollowers().get(i).getUsername();
            str += ", ";
        }
        return str;
    }
    /**
     * @return String
     * Prints following string
     */
    @Override
    public String printFollowing(Account account){
        int size = account.getFollowing().size();
        String str = "";
        for(int i=0; i<size; i++) {
            str += account.getFollowing().get(i).getUsername();
            str += ", ";
        }
        return str;
    }

    /**
     * Views another account
     * Checks logged in
     * Checks blocked
     * @param viewerAccount,
     * @param anotherAccount
     */
    @Override
    public void viewProfile(Account viewerAccount, Account anotherAccount) {
        checkLoggedIn(viewerAccount);
        checkBlocked(viewerAccount,anotherAccount);
            if (viewerAccount.getId() == anotherAccount.getId()) {
                System.out.println("...Viewing your profile...");
            } else {
                System.out.println("...Viewing " + viewerAccount.getUsername() + "'s profile");
            }
            System.out.println("\nUser ID: " + anotherAccount.getId()
                    + "\nUsername: " + anotherAccount.getUsername()
                    + "\nLocation: " + anotherAccount.getLocation()
                    + "\nBirth Date: " + anotherAccount.getBirthdate()
                    + "\n" + anotherAccount.getUsername() + " is following " + anotherAccount.getFollowing().size() +
                    " account(s) and has " + anotherAccount.getFollowers().size() + " follower(s).");
            if (anotherAccount.getFollowing().size() == 0 && anotherAccount.getFollowers().size() != 0) {
                System.out.println("The followers of " + anotherAccount.getUsername() + " are: " + printFollower(anotherAccount));
            }
            if (anotherAccount.getFollowing().size() != 0 && anotherAccount.getFollowers().size() == 0) {
                System.out.println(anotherAccount.getUsername() + " is following: " + printFollowing(anotherAccount));
            }
            if (anotherAccount.getFollowing().size() != 0 && anotherAccount.getFollowers().size() != 0) {
                System.out.println("The followers of " + anotherAccount.getUsername() + " are: " + printFollower(anotherAccount));
                System.out.println(anotherAccount.getUsername() + " is following: " + printFollowing(anotherAccount));
            }
            System.out.println(anotherAccount.getUsername() + " has " + anotherAccount.getPosts().size() + " posts\n");
        history.add("You viewed " + anotherAccount.getUsername() + "'s profile");
    }

    /**
     * Sharing post with string parameter
     * @param postcomment,account;
     */
    @Override
    public void sharePost(Account account, String postcomment){
        checkLoggedIn(account);
        Post post = new Post(account.getPosts().size()+1,account.getId(), postcomment);
        System.out.println("...Sharing post...");
        account.getPosts().add(post);
        history.add("You shared post id: " + account.getPosts().size()+1);
    }

    /**
     * @param account
     * Displays all post of the account
     */
    @Override
    public void viewPost(Account viewerAccount, Account account) {
        checkLoggedIn(viewerAccount);
        checkBlocked(viewerAccount,account);
            System.out.println("...Viewing " + account.getUsername() + "' posts...");
            for (int i = 0; i < account.getPosts().size(); i++) {
                System.out.println("(PostID: " + account.getPosts().get(i).getPostId() + ")"
                        + " " + account.getUsername() + ": " + printPosts(account,i));
            }
        history.add("You viewed " + account.getUsername() + "'s posts");
    }

    /**
     * @return str
     * Returns string to print post
     */
    @Override
    public String printPosts(Account account, int i){
        int size = account.getPosts().size();
        String str = "";
        return str += account.getPosts().get(i).getContent();
    }

    /**
     * Like another accounts post if exists
     * @param postId
     * @param account
     */
    @Override
    public void likePost(Account likerAccount, int postId,Account account){
        checkLoggedIn(likerAccount);
        checkBlocked(likerAccount,account);
        System.out.println("...Liking a post of " + account.getUsername());
        if (postId > account.getPosts().size()) throw new RuntimeException("There is no post id");
        else {
            Post post = account.getPosts().get(postId - 1);
            PostManager postManager = new PostManager(post);
            postManager.addLike(postId, likerAccount);

            System.out.println("...You liked " + post.getContent());
            history.add("You liked " + account.getUsername() + "'s post id :" + postId);
        }

    }
    @Override
    public void unLikePost(Account likerAccount, int postId, Account account){
        checkLoggedIn(likerAccount);
        checkBlocked(likerAccount,account);
        System.out.println("...Unliking a post of " + account.getUsername());
        if (postId > account.getPosts().size()) throw new RuntimeException("There is no post id");
        else{
            Post post = account.getPosts().get(postId - 1);
            PostManager postManager = new PostManager(post);
            postManager.removeLike(postId,likerAccount);

            System.out.println("...You unliked " + post.getContent());
            history.add("You unliked " + account.getUsername() + "'s post id :" + postId);
        }

    }
    @Override
    public String printLikes(Post post){
        String str = "";
        for(int i=0 ; i < post.getLikes().size() ; i++){
            str += accountRepository.getById(post.getLikes().get(i).getAccountId()).getUsername();
            str += ", ";
        }
        return str;
    }

    /**
     * Comments post if exists,gets String parameter for this.
     * @param commenterAccount
     * @param postId
     * @param account
     * @param comment
     */
    @Override
    public void commentPost(Account commenterAccount, int postId,Account account,String comment){
        checkLoggedIn(commenterAccount);
        checkBlocked(commenterAccount,account);
        System.out.println("...Adding a comment on a post of " + account.getUsername());
        if (postId > account.getPosts().size()) throw new RuntimeException("There is no post id");
        else {
            Post post = account.getPosts().get(postId - 1);
            PostManager postManager = new PostManager(post);
            postManager.addComment(postId, comment, commenterAccount);
            System.out.println("...You commented on " + comment);
            history.add("You commented " + account.getUsername() + "'s post id : " + postId);
        }
    }

    public void unCommentPost(Account commenterAccount, int postId , Account account, String comment){
        checkLoggedIn(commenterAccount);
        checkBlocked(commenterAccount,account);
        if (postId > account.getPosts().size()) throw new RuntimeException("There is no post id");
        else{
            Post post = account.getPosts().get(postId - 1);
            PostManager postManager = new PostManager(post);
            postManager.removeComment(postId,commenterAccount,comment);
            System.out.println("...You uncommented " + comment);
            history.add("You uncommented " + account.getUsername() + "'s post id : " + postId);
            }

    }



    /**
     * @param receiverAccount
     * @param content
     * Sends message to another account
     * Gets string parameter
     * Checks if account is logged in
     * Checks if account is blocked
     */
    @Override
    public void sendMessage(Account senderAccount,Account receiverAccount, String content){
        checkLoggedIn(senderAccount);
        checkBlocked(senderAccount,receiverAccount);
            if(senderAccount.getFollowing().contains(receiverAccount)) {
                System.out.println("\n...Sending a message to " + receiverAccount.getUsername());
                Message message = new Message(senderAccount.getMessagesOutbox().size(), senderAccount.getId(), receiverAccount.getId(), content);
                senderAccount.getMessagesOutbox().add(message);
                receiverAccount.getMessagesInbox().add(message);
                history.add("You send message to " + receiverAccount.getUsername());
            }
            else {
                System.out.println("...You are not following " + receiverAccount.getUsername() + ".You can't send message");
            }

    }

    /**
     * Prints how many messages have been received
     */
    @Override
    public void checkInboxMessages(Account account){
        checkLoggedIn(account);
        System.out.println("...Checking inbox...");
        System.out.println("There is/are " + account.getMessagesInbox().size() + " message(s) in the inbox.");
        history.add("You checked inbox messages");
    }

    /**
     * Prints how many messages have been sent
     */
    @Override
    public void checkOutboxMessages(Account account){
        checkLoggedIn(account);
        System.out.println("...Checking outbox...");
        System.out.println("There is/are " + account.getMessagesOutbox().size() + " message(s) in the outbox.");
        history.add("You checked outbox messages");
    }
    /**
     * @param account
     * Prints all Inbox messages
     * Checks logged in
     */
    @Override
    public void viewInboxMessages(Account account) {
        checkLoggedIn(account);
        System.out.println("...Viewing inbox..." + "\n" +
                "---------------------");
        for (int i = 0; i < account.getMessagesInbox().size(); i++) {

            System.out.println("Message ID: " + account.getMessagesInbox().get(i).getMessageId() + "\n" +
                    "From: " + accountRepository.getAccounts().get(account.getMessagesInbox().get(i).getSenderId()-1).getUsername() + "\n" +
                    "To: " + accountRepository.getAccounts().get(account.getMessagesInbox().get(i).getReceiverId()-1).getUsername()+ "\n" +
                    "Message: " + account.getMessagesInbox().get(i).getContent() + "\n");
        }
        history.add("You viewed inbox messages");
    }
    /**
     * @param account
     * Prints all Outbox messages
     * Checks logged in
     */
    @Override
    public void viewOutboxMessages(Account account) {
        checkLoggedIn(account);
        System.out.println("...Viewing outbox..." + "\n" +
                "---------------------");
        for (int i = 0; i < account.getMessagesOutbox().size(); i++) {

            System.out.println("Message ID: " + account.getMessagesOutbox().get(i).getMessageId() + "\n" +
                    "From: " + accountRepository.getAccounts().get(account.getMessagesOutbox().get(i).getSenderId()).getUsername() + "\n" +
                    "To: " + accountRepository.getAccounts().get(account.getMessagesOutbox().get(i).getReceiverId()).getUsername()+ "\n" +
                    "Message: " + account.getMessagesOutbox().get(i).getContent() + "\n");
        }
        history.add("You viewed outbox messages");
    }

    /**
     * @param account
     * Prints all posts' interactions
     * Checks logged in
     * Checks blocked
     */
    @Override
    public void viewInteraction(Account viewerAccount, Account account){
        checkLoggedIn(viewerAccount);
        checkBlocked(viewerAccount,account);
            System.out.println("... Viewing " + account.getUsername() + "'s posts' interactions...");
            for (int i = 0; i < account.getPosts().size(); i++) {

                System.out.println("(PostID: " + account.getPosts().get(i).getPostId() + ")"
                        + " " + account.getUsername() + ": " + printPosts(account,i));
                if (account.getPosts().get(i).getLikes().size() > 0) {
                    System.out.println("The post was liked by the following account(s):" + printLikes(account.getPosts().get(i)));
                } else {
                    System.out.println("The post has no likes.");
                }
                if (account.getPosts().get(i).getComments().size() > 0) {
                    System.out.println("The post has " + account.getPosts().get(i).getComments().size() + " comment(s)...");
                    for (int j = 0; j < account.getPosts().get(i).getComments().size(); j++) {
                        System.out.println("Comment " + (j + 1) + ": '" + accountRepository.getById(account.getPosts().get(i).getComments().get(j).getAccountId()).getUsername() + "' said '"
                                + account.getPosts().get(i).getComments().get(j).getContent() + "'");
                    }
                } else {
                    System.out.println("The post has no comments.");
                }
            }
        history.add("You viewed " + account.getUsername() + "'s interaction");
    }

    public void viewHistory(Account account) {
        checkLoggedIn(account);
        System.out.println("\nVIEWING YOUR HISTORY");
        for (String s : history) {
            System.out.println(s);
        }
    }

}

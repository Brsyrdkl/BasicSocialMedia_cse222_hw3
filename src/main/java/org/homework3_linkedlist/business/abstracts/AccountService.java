package org.homework3_linkedlist.business.abstracts;

import org.homework3_linkedlist.entities.concretes.Account;
import org.homework3_linkedlist.entities.concretes.Post;

import java.util.List;

public interface AccountService {

    void addAccount(Account account);
    void addMultipleAccounts(List<Account> accounts);
    void follow(Account followerAccount,Account followingAccount);
    void unfollow(Account followerAccount,Account followingAccount);
    boolean isFollowing(Account followerAccount,Account followingAccount);
    void addFollower(Account followerAccount,Account followingAccount);
    void addMultipleFollower(Account followerAccount,List<Account> accounts);
    void removeFollower(Account removerAccount,Account removingAccount);
    void removeMultipleFollower(Account removerAccount,List<Account> accounts);

    void checkBlocked(Account blockerAccount, Account blockingAccount);

    String printFollower(Account account);
    String printFollowing(Account account);
    void viewProfile(Account viewerAccount, Account anotherAccount);
    void sendMessage(Account senderAccount, Account receiverAccount, String content);
    void sharePost(Account account, String content);
    void shareMultiplePost(Account account, List<String> contents);
    void viewPost(Account viewerAccount, Account account);

    String printPosts(Account account, int i);
    void likePost(Account likerAccount, int postId,Account account);

    void unLikePost(Account likerAccount, int postId, Account account);

    String printLikes(Post post);

    void commentPost(Account commenterAccount, int postId, Account account, String comment);
    void checkInboxMessages(Account account);
    void checkOutboxMessages(Account account);
    void viewInboxMessages(Account account);
    void viewOutboxMessages(Account account);
    void blockAccount(Account blockerAccount, Account blockingAccount);

    void unBlockAccount(Account blockerAccount, Account blockingAccount);

    void viewInteraction(Account viewerAccount, Account account);
}

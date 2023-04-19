package org.homework3_linkedlist.business.abstracts;

import org.homework3_linkedlist.entities.concretes.Account;

public interface PostService {

    void addLike(int postId , Account account);
    void addComment(int postId, String comment,Account account);


}

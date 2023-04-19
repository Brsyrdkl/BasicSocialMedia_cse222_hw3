package org.homework3_arraylist.business.abstracts;

import org.homework3_arraylist.entities.concretes.Account;

public interface PostService {

    void addLike(int postId , Account account);
    void addComment(int postId, String comment,Account account);


}

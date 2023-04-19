package org.homework3_linkedlist.entities.concretes;

import org.homework3_linkedlist.entities.concretes.Comment;
import org.homework3_linkedlist.entities.concretes.Like;

import java.util.ArrayList;
import java.util.LinkedList;

public class Post {

    private String content;

    int postId;

    int accountid;

    private LinkedList<Like> likes;

    private LinkedList<Comment> comments;

    public Post() {}

    public Post(int postId,int accountid,String content){
        this.postId = postId;
        this.accountid = accountid;
        this.content = content;
        this.likes = new LinkedList<>();
        this.comments = new LinkedList<>();
    }


    /*Setters and Getters*/

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public int getAccountid() {
        return accountid;
    }

    public void setAccountid(int accountid) {
        this.accountid = accountid;
    }

    public void setLikes(LinkedList<Like> likes) {
        this.likes = likes;
    }

    public void setComments(LinkedList<Comment> comments) {
        this.comments = comments;
    }

    public String getContent(){ return content; }
    public void setContent(String postString) {
        this.content = postString;
    }

    public LinkedList<Like> getLikes() {
        return likes;
    }
    public LinkedList<Comment> getComments() {
        return comments;
    }

}

package org.homework3_arraylist.entities.concretes;

import java.util.ArrayList;

public class Post {

    private String content;

    int postId;

    int accountid;

    private ArrayList<Like> likes;

    private ArrayList<Comment> comments;

    public Post() {}

    public Post(int postId,int accountid,String content){
        this.postId = postId;
        this.accountid = accountid;
        this.content = content;
        this.likes = new ArrayList<Like>();
        this.comments = new ArrayList<Comment>();
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

    public void setLikes(ArrayList<Like> likes) {
        this.likes = likes;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public String getContent(){ return content; }
    public void setContent(String postString) {
        this.content = postString;
    }

    public ArrayList<Like> getLikes() {
        return likes;
    }
    public ArrayList<Comment> getComments() {
        return comments;
    }

}

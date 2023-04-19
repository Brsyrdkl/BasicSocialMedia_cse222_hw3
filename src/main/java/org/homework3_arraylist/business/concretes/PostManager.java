package org.homework3_arraylist.business.concretes;

import org.homework3_arraylist.business.abstracts.PostService;
import org.homework3_arraylist.entities.concretes.Account;
import org.homework3_arraylist.entities.concretes.Comment;
import org.homework3_arraylist.entities.concretes.Like;
import org.homework3_arraylist.entities.concretes.Post;

public class PostManager implements PostService {

    Post post;

    public PostManager(Post post) {
        this.post = post;
    }

    public void addLike(int postId , Account account){
        post.getLikes().add(new Like(post.getLikes().size()+1, postId, account.getId()));
    }

    public void addComment(int postId, String comment,Account account){
        post.getComments().add(new Comment(1,postId,account.getId(),comment));
    }

    public void removeLike(int postId , Account account){
        for(int i = 0; i < post.getLikes().size() ; i++){
            if((post.getLikes().get(i).getAccountId()) == account.getId()){
                post.getLikes().remove(i);
            }
        }
    }

    public void removeComment(int postId , Account account,String comment) {
        for (int i = 0; i < post.getComments().size(); i++) {
            if (post.getComments().get(i).getContent().equals(comment)
             && post.getComments().get(i).getAccountId() == account.getId())
            {
                post.getComments().remove(i);
            }
        }
    }
}

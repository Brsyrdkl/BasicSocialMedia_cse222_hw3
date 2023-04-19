package org.homework3_linkedlist.entities.concretes;

import org.homework3_linkedlist.entities.concretes.Interaction;

public class Comment extends Interaction {

    private String content;

    public Comment() {
    }

    public Comment(int interactionId, int postId, int accountId, String content) {
        super(interactionId, postId, accountId);
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

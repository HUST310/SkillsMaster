package com.hust310.SkillsMaster.domain;

import lombok.Data;

import java.util.Date;

@Data
public class CommentResponse {
    private User commentorInfo;
    private User commenteeInfo;
    private Date time;
    private String title;
    private Integer likes;
    private Integer comment;
    private Integer uid;
    private String content;

    public void setCommenteeInfo(User commenteeInfo) {
        this.commenteeInfo = commenteeInfo;
    }

    public User getCommenteeInfo() {
        return commenteeInfo;
    }

    public void setCommentorInfo(User commentorInfo) {
        this.commentorInfo = commentorInfo;
    }

    public User getCommentorInfo() {
        return commentorInfo;
    }
}

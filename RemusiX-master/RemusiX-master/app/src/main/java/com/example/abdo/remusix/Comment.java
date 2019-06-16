package com.example.abdo.remusix;

public class Comment {
    String usrname ,comment_time,likes,comment;
    int user_img;

    public Comment(String usrname, String comment_time, String likes, String comment, int user_img) {
        this.usrname = usrname;
        this.comment_time = comment_time;
        this.likes = likes;
        this.comment = comment;
        this.user_img = user_img;
    }

    public String getUsrname() {
        return usrname;
    }

    public void setUsrname(String usrname) {
        this.usrname = usrname;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getUser_img() {
        return user_img;
    }

    public void setUser_img(int user_img) {
        this.user_img = user_img;
    }
}

package com.example.abdo.remusix;

public class Comment {
   private String usrname ,comment_time;
   private String user_img;
   private String comment;



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

    public String getUser_img() {
        return user_img;
    }

    public void setUser_img(String user_img) {
        this.user_img = user_img;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(String usrname, String comment_time, String user_img, String comment) {
        this.usrname = usrname;
        this.comment_time = comment_time;
        this.user_img = user_img;
        this.comment = comment;
    }
}

package com.example.abdo.remusix;

import java.io.Serializable;

public class PostData implements Serializable {

        private String id;
       private String name ,time;
             private int  like,comment;
      private   String img;
      private   String singer_img;
        private   String ArtistName;
       private    String SongName;

    public PostData(String id, String name, String time, int like, int comment, String img, String singer_img, String artistName, String songName) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.like = like;
        this.comment = comment;
        this.img = img;
        this.singer_img = singer_img;
        ArtistName = artistName;
        SongName = songName;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public String getArtistName() {
        return ArtistName;
    }

    public void setArtistName(String artistName) {
        ArtistName = artistName;
    }

    public String getSongName() {
        return SongName;
    }

    public void setSongName(String songName) {
        SongName = songName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getSinger_img() {
        return singer_img;
    }

    public void setSinger_img(String singer_img) {
        this.singer_img = singer_img;
    }


    public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }











}

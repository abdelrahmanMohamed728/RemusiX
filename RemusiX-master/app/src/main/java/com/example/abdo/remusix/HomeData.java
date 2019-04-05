package com.example.abdo.remusix;

public class HomeData {


        String name ,time,likes,comment;
        int img;
        int singer_img;

        public HomeData(String name, String time,
                            int img, int singer_img, String likes, String comment) {
            this.name = name;
            this.time = time;
            this.img = img;
            this.singer_img = singer_img;
            this.likes = likes;
            this.comment = comment;

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

        public int getImg() {
            return img;
        }

        public void setImg(int img) {
            this.img = img;
        }

        public int getSinger_img() {
            return singer_img;
        }

        public void setSinger_img(int singer_img) {
            this.singer_img = singer_img;
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







}

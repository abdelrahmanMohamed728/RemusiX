package com.example.abdo.remusix;

public class Artist {
    private String id;
    private String name;
    private String img;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public Artist(String id, String name, String img) {
        this.id = id;
        this.name = name;
        this.img = img;
    }
}

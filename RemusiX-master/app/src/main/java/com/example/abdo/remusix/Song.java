package com.example.abdo.remusix;

public class Song {
    private String img;
    private String name;
    private String id;
    private String link;
    private String ArtistId;

    public Song(String img, String name, String id, String link, String artistId) {
        this.img = img;
        this.name = name;
        this.id = id;
        this.link = link;
        ArtistId = artistId;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String  img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getArtistId() {
        return ArtistId;
    }

    public void setArtistId(String artistId) {
        ArtistId = artistId;
    }
}

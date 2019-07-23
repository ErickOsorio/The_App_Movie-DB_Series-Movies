package com.eos.numbers.to.appmovies.Item;

public class itemDetail {

    String genres_name;
    String key_video;

    public itemDetail(String genres_name, String key_video) {
        this.genres_name = genres_name;
        this.key_video = key_video;
    }

    public String getGenres_name() {
        return genres_name;
    }

    public void setGenres_name(String genres_name) {
        this.genres_name = genres_name;
    }

    public String getKey_video() {
        return key_video;
    }

    public void setKey_video(String key_video) {
        this.key_video = key_video;
    }
}

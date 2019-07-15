package com.eos.numbers.to.appmovies.Item;

public class itemMain {

    public int id;
    public String title;
    public String poster;
    public String votes;

    public itemMain(int id, String title, String poster, String votes) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.votes = votes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getVotes() {
        return votes;
    }

    public void setVotes(String votes) {
        this.votes = votes;
    }
}

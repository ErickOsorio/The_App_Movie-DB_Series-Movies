package com.eos.numbers.to.appmovies.Item;

public class itemMain {

    public int id;
    public String title;
    public String poster;
    public String votes;
    public String language;
    public String date;
    public String overview;

    public itemMain(int id, String title, String poster, String votes, String language, String date, String overview) {
        this.id = id;
        this.title = title;
        this.poster = poster;
        this.votes = votes;
        this.language = language;
        this.date = date;
        this.overview = overview;
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

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }
}

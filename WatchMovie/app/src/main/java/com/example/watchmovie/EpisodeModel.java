package com.example.watchmovie;


//____________________ THIS IS THE CLASS FOR EPISODE LIST

public class EpisodeModel {

    private String title;
    private String genre;
    private String year;
    private int video;
    // State of the item
    private boolean expanded;

    public EpisodeModel(String title, String genre, String year,int video) {
        this.title = title;
        this.genre = genre;
        this.year = year;
        this.video=video;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear() {
        return year;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public int getVideo() {
        return video;
    }

    public void setVideo(int video) {
        this.video = video;
    }
}
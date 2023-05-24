package com.jh.team2;

import android.util.Log;

// 자식 아이템
public class Team2B_SubItem {
    // api로 받아온 Json 데이터
    // 여기에서 원하는 정보를 받아와서 사용
    // 리퀘스로 받아 오는 Json 데이터를 참조해서 만들자.
    private String id;
    private String title;
    private String original_title;
    private String poster_path;
    private String overview;
    private String backdrop_path;
    private String release_date;
    private double vote_average;

    public Team2B_SubItem(String id, String title, String original_title, String poster_path, String overview, String backdrop_path, String release_date, double vote_average) {
        this.id = id;
        this.title = title;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.overview = overview;
        this.backdrop_path = backdrop_path;
        this.release_date = release_date;
        this.vote_average = vote_average;
        Log.d("포스터", poster_path);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public String getRelease_date() {
        return release_date;
    }

    public double getVote_average() { return vote_average; }

    public void setVoteAverage(double vote_average) {
        vote_average = vote_average;
    }

    @Override
    public String toString() {
        return "Team2B_SubItem{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", original_title='" + original_title + '\'' +
                ", poster_path='" + poster_path + '\'' +
                ", overview='" + overview + '\'' +
                ", backdrop_path='" + backdrop_path + '\'' +
                ", release_date='" + release_date + '\'' +
                ", vote_average=" + vote_average +
                '}';
    }
}
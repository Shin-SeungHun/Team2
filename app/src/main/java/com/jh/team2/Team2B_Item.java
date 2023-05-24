package com.jh.team2;

import java.util.ArrayList;

// 상위 리사이클러뷰 아이템클래스를 정의
public class Team2B_Item {
    private String itemTitle;
    // 하위 리사이클러뷰 아이템으로 정의한 subItemList를 전역변수로 선언한다.
    private ArrayList<Team2B_SubItem> movieList;


    public Team2B_Item(String itemTitle, ArrayList<Team2B_SubItem> movieList) {
        this.itemTitle = itemTitle;
        // 하위 리사이클러뷰
        this.movieList = movieList;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public ArrayList<Team2B_SubItem> getMovieList() {
//        Log.d("check", "333");
        return movieList;
    }

    public void setMovieItemList(ArrayList<Team2B_SubItem> movieList) {
        this.movieList = movieList;
    }
}
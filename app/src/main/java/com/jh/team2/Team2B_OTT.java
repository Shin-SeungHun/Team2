package com.jh.team2;

import android.util.Log;

// 자식 아이템
public class Team2B_OTT {
    // api로 받아온 Json 데이터
    // 여기에서 원하는 정보를 받아와서 사용
    // 리퀘스로 받아 오는 Json 데이터를 참조해서 만들자.
    private int provider_id;
    private String logo_path;
    private String provider_name;
    private String display_priority;

    public Team2B_OTT(int provider_id, String logo_path, String provider_name, String display_priority) {
        this.provider_id = provider_id;
        this.logo_path = logo_path;
        this.provider_name = provider_name;
        this.display_priority = display_priority;

        Log.d("logo", logo_path);
    }

    public int getProvider_id() {
        return provider_id;
    }

    public String getLogo_path() {
        return logo_path;
    }

    public String getProvider_name() {
        return provider_name;
    }

    public String getDisplay_priority() {
        return display_priority;
    }

}
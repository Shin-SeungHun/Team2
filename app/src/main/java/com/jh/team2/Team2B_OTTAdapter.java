package com.jh.team2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.jh.team2.Model.API;

import java.util.ArrayList;

// OTT 어뎁터
public class Team2B_OTTAdapter extends RecyclerView.Adapter<Team2B_OTTAdapter.RecyclerViewHolders> {

    //    리사이클러뷰 어뎁터

    private ArrayList<Team2B_OTT> OTTList;
    private LayoutInflater mInflate;
    private Context mContext;

    //constructor
    public Team2B_OTTAdapter(Context context, ArrayList<Team2B_OTT> OTTList) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.OTTList = OTTList;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.layout_ott, parent, false);
        RecyclerViewHolders viewHolder = new RecyclerViewHolders(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders holder, final int position) {
        // OTT아이콘 출력
        // 베이스 url에 logo_path 결합하면 해당 OTT아이콘이 출력됨
        String url = API.poster_url + OTTList.get(position).getLogo_path();
        Log.d("url", url);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .into(holder.iv_ott_icon);
    }

    @Override
    public int getItemCount() {
        return this.OTTList.size();
    }


    //뷰홀더 - 따로 클래스 파일로 만들어도 된다.
    public static class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public ImageView iv_ott_icon;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            iv_ott_icon = (ImageView) itemView.findViewById(R.id.iv_ott_icon);
        }
    }
}
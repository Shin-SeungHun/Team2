package com.jh.team2;

import android.annotation.SuppressLint;
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

// 자식 어답터
public class Team2B_SubItemAdapter extends RecyclerView.Adapter<Team2B_SubItemAdapter.RecyclerViewHolders> {

    //    리사이클러뷰 어뎁터

    private final ArrayList<Team2B_SubItem> mMovieList;
    private final LayoutInflater mInflate;
    private final Context mContext;

    //constructor
    public Team2B_SubItemAdapter(Context context, ArrayList<Team2B_SubItem> mMovieList) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mMovieList = mMovieList;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.layout_sub_item, parent, false);
        return new RecyclerViewHolders(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders holder, @SuppressLint("RecyclerView") final int position) {
        // 포스터 출력
        // 베이스 url에 Poster_path 결합하면 해당 영화 포스터가 출력됨
        String url = API.poster_url + mMovieList.get(position).getPoster_path();
        Log.d("url", url);
        Glide.with(mContext)
                .load(url)
                .centerCrop()
                .into(holder.imageView);

        //각 아이템 클릭 이벤트 (포스터 클릭시)
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 영화정보창으로 이동
                // 아래 5개 정보를 putExtra해서 정보창에 띄움 (다른 원하는 정보도 정보창에 띄울 수 있음)
                Intent intent = new Intent(mContext, DetailActivity.class);
                intent.putExtra("id", mMovieList.get(position).getId());
                intent.putExtra("title", mMovieList.get(position).getTitle());
                intent.putExtra("original_title", mMovieList.get(position).getOriginal_title());
                intent.putExtra("poster_path", mMovieList.get(position).getPoster_path());
                intent.putExtra("overview", mMovieList.get(position).getOverview());
                intent.putExtra("release_date", mMovieList.get(position).getRelease_date());
                intent.putExtra("vote_average", mMovieList.get(position).getVote_average());
                mContext.startActivity(intent);
                Log.d("Adapter", "Clcked: " + position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mMovieList.size();
    }


    //뷰홀더 - 따로 클래스 파일로 만들어도 된다.
    public static class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public ImageView imageView;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }
}
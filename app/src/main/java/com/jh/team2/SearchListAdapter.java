package com.jh.team2;


import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.jh.team2.Model.API;

import java.util.ArrayList;

public class SearchListAdapter extends RecyclerView.Adapter<SearchListAdapter.RecyclerViewHolders>{

//    리사이클러뷰 어뎁터

    private ArrayList<Team2B_SubItem> mMovieList;
    private LayoutInflater mInflate;
    private Context mContext;
    private double Rating;

    //constructor
    public SearchListAdapter(Context context, ArrayList<Team2B_SubItem> itemList) {
        this.mContext = context;
        this.mInflate = LayoutInflater.from(context);
        this.mMovieList = itemList;
    }

    @NonNull
    @Override
    public RecyclerViewHolders onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflate.inflate(R.layout.list_item_film, parent, false);
        RecyclerViewHolders viewHolder = new RecyclerViewHolders(view);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolders holder, @SuppressLint("RecyclerView") final int position) {
        // 포스터 출력
        // 베이스 url에 Poster_path 결합하면 해당 영화 포스터가 출력됨
        String url = API.poster_url + mMovieList.get(position).getPoster_path();
        Glide.with(mContext)
                .load(url)
                .transition(withCrossFade())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .error(R.drawable.baseline_movie_filter_24)
                .override(Target.SIZE_ORIGINAL)
                .centerCrop()
                //.crossFade()
                .into(holder.imageView);

        holder.tvTitle.setText(mMovieList.get(position).getTitle());
        holder.tvDesc.setText(mMovieList.get(position).getOverview());

        Rating = mMovieList.get(position).getVote_average();
        Log.d("Rating", "Rating: "+ Rating);
        float newValue = (float)Rating;
        holder.ratingBar.setNumStars(5);
        holder.ratingBar.setStepSize((float) 0.5);
        holder.ratingBar.setRating(newValue / 2);


        //각 아이템 클릭 이벤트 (포스터 클릭시)
        holder.itemView.setOnClickListener(view -> {
            // 영화정보창으로 이동
            // 아래 5개 정보를 putExtra해서 정보창에 띄움 (다른 원하는 정보도 정보창에 띄울 수 있음)
            Intent intent = new Intent(mContext, DetailActivity.class);
            intent.putExtra("title", mMovieList.get(position).getTitle());
            intent.putExtra("original_title", mMovieList.get(position).getOriginal_title());
            intent.putExtra("poster_path", mMovieList.get(position).getPoster_path());
            intent.putExtra("overview", mMovieList.get(position).getOverview());
            intent.putExtra("release_date", mMovieList.get(position).getRelease_date());
            mContext.startActivity(intent);
            Log.d("Adapter", "Clcked: " + position);
        });
    }

    @Override
    public int getItemCount() {
        return this.mMovieList.size();
    }


    //뷰홀더 - 따로 클래스 파일로 만들어도 된다.
    public static class RecyclerViewHolders extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView tvTitle, tvDesc;
        public RatingBar ratingBar;

        public RecyclerViewHolders(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
            tvTitle = (TextView) itemView.findViewById(R.id.tvTitle);
            tvDesc = (TextView) itemView.findViewById(R.id.tvDesc);
            ratingBar = itemView.findViewById(R.id.ratingBar);
        }
    }

}
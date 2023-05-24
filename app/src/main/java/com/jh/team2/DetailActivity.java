package com.jh.team2;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jh.team2.Model.API;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class DetailActivity extends AppCompatActivity {

    // 영화 정보창

    ArrayList<Team2B_OTT> ottList;
    String[] strings;
    Button btnFavoriteN, btnFavoriteY;
    private String tv_title;
    private ImageView ivBackdrop;
    private ProgressBar pbLoadBackdrop;
    private TextView tvMovieTitle;
    private TextView tvMovieReleaseDate;
    private TextView tvMovieRatings;
    private TextView tvOverview;
//    private CastAdapter castAdapter;
//    private List<Cast> castList;
    private ProgressBar pbLoadCast;
    private TextView tvHomepageValue;
    private TextView tvTaglineValue;
    private TextView tvRuntimeValue;

    private String movieName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_detail);
        setContentView(R.layout.activity_movie_details);

        initUI();
        initCollapsingToolbar();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            finish(); // Close the activity when back button is pressed
        });

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String original_title = intent.getStringExtra("original_title");
        String poster_path = intent.getStringExtra("poster_path");
        String overview = intent.getStringExtra("overview");
        String release_date = intent.getStringExtra("release_date");
        String vote_average = intent.getStringExtra("vote_average");

        // 한국에서 해당 영화 볼 수 있는 플랫폼 목록
        ottList = new ArrayList<Team2B_OTT>();

//       Log.d("id", id);

        // 한국에서 해당 영화 볼 수 있는 플랫폼 목록 API url 생성
        String Load_url = API.base_url + API.api_movie + id + API.providers + API.api_key;
        strings = new String[]{Load_url};
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(strings[0]);

//        btnFavoriteN = findViewById(R.id.btnFavoriteN);
//        btnFavoriteY = findViewById(R.id.btnFavoriteY);

//        findViewById(R.id.btnFavoriteN).setOnClickListener(mClick);
//        findViewById(R.id.btnFavoriteY).setOnClickListener(mClick);

        //////// putExtra로 받아온 데이터를 getStringExtra로 받아서 사용 ///////////
//        TextView textView_title = (TextView)findViewById(R.id.tv_title);
//        textView_title.setText(title);
//        TextView tv_vote_average = (TextView)findViewById(R.id.tv_vote_average);
//        tv_vote_average.setText(vote_average);
//        TextView textView_original_title = (TextView)findViewById(R.id.tv_original_title);
//        textView_original_title.setText(original_title);
//        ImageView imageView_poster = (ImageView) findViewById(R.id.iv_poster);

//        TextView textView_title = (TextView)findViewById(R.id.tv_movie_title);
//        textView_title.setText(title);
//        TextView tv_vote_average = (TextView)findViewById(R.id.tv_vote_average);
//        tv_vote_average.setText(vote_average);
//        TextView textView_original_title = (TextView)findViewById(R.id.tv_original_title);
//        textView_original_title.setText(original_title);
        ImageView imageView_poster = (ImageView) findViewById(R.id.iv_backdrop);

        Log.d("포스터", API.poster_url + poster_path);

        Glide.with(this)
                .load(API.poster_url + poster_path)
                .centerCrop()
                .into(imageView_poster);

//        TextView textView_overview = (TextView)findViewById(R.id.tv_overview);
//        textView_overview.setText(overview);
//        TextView textView_release_date = (TextView)findViewById(R.id.tv_release_date);
//        textView_release_date.setText(release_date);
        //////////////////////////////////////////

        // 플랫폼 목록 리사이클러뷰에 저장
        // API 통신하는 시간이 있으므로 Handler 사용
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                RecyclerView rv_ott = (RecyclerView) findViewById(R.id.rv_ott);
                LinearLayoutManager layoutManager = new LinearLayoutManager(DetailActivity.this, LinearLayoutManager.HORIZONTAL, false);
                Team2B_OTTAdapter itemAdapter = new Team2B_OTTAdapter(DetailActivity.this, ottList);
                rv_ott.setAdapter(itemAdapter);
                rv_ott.setLayoutManager(layoutManager);
            }
        }, 500);

    }

    private void initUI() {

        ivBackdrop = findViewById(R.id.iv_backdrop);
        //pbLoadBackdrop = findViewById(R.id.pb_load_backdrop);
        tvMovieTitle = findViewById(R.id.tv_movie_title);
        tvMovieReleaseDate = findViewById(R.id.tv_release_date);
        tvMovieRatings = findViewById(R.id.tv_movie_ratings);
        tvOverview = findViewById(R.id.tv_movie_overview);

        //castList = new ArrayList<>();
        RecyclerView rvCast = findViewById(R.id.rv_cast);
       // castAdapter = new CastAdapter(this, castList);
        //rvCast.setAdapter(castAdapter);
        pbLoadCast = findViewById(R.id.pb_cast_loading);

        tvHomepageValue = findViewById(R.id.tv_homepage_value);
        tvTaglineValue = findViewById(R.id.tv_tagline_value);
        tvRuntimeValue = findViewById(R.id.tv_runtime_value);
    }
    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");

        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(tv_title);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }
    View.OnClickListener mClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch(v.getId()) {
                case R.id.btnFavoriteN:
                    btnFavoriteN.setVisibility(View.INVISIBLE);
                    btnFavoriteY.setVisibility(View.VISIBLE);
                    break;
                case R.id.btnFavoriteY:
                    btnFavoriteN.setVisibility(View.VISIBLE);
                    btnFavoriteY.setVisibility(View.INVISIBLE);
                    break;
            }

        }
    };

    public class MyAsyncTask extends AsyncTask<String, Void, Team2B_OTT[]> {

        //로딩중 표시
        ProgressDialog progressDialog = new ProgressDialog(DetailActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            //show dialog
            progressDialog.show();
        }

        @Override
        protected Team2B_OTT[] doInBackground(String... strings) {
            // doInBackground(String... strings) << 여기에서 strings 를 받아와 url에 넣음

            // url 확인 Log
            Log.d("AsyncTask", "url : " + strings[0]);

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            // 리퀘스트 결과
            try {
                Response response = client.newCall(request).execute();
                Log.d("response", response+"");
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();

                // results값 추출
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");

                // JsonElement를 JsonObject로 변환
                JsonObject jsonObject = (JsonObject) rootObject.getAsJsonObject();
                System.out.println(rootObject);

                // results 안에 KR이 있는지 확인
                if(rootObject.getAsJsonObject().get("KR").isJsonNull()) return null;
                else{
                    // results중에 KR찾기
                    JsonObject KR = (JsonObject) jsonObject.getAsJsonObject("KR");

                    // KR flatrate 안에 ott정보들 JsonArray에 저장
                    JsonArray flatrate = (JsonArray) KR.getAsJsonArray("flatrate");
                    JsonArray buy = (JsonArray) KR.getAsJsonArray("buy");

                    // SubItem클래스에 데이터 저장
                    Team2B_OTT[] posts = gson.fromJson(flatrate, Team2B_OTT[].class);

                    Log.d("check", "1111");
                    System.out.println(posts);
                    return posts;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Team2B_OTT[] result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            // result가 null이 아니라면 ArrayList에 차례대로 집어 넣는다.
            if (result != null) {
                for (Team2B_OTT p : result) {
                    ottList.add(p);
                    Log.d("check", "2222");
                }
            } else return;
        }
    }
}
package com.jh.team2;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jh.team2.Model.API;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Search extends AppCompatActivity {

    private RecyclerView rvFilmRecommend;
    private SearchListAdapter adapter;
    private TextView resultTextView;

    ArrayList<Team2B_SubItem> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rvFilmRecommend = (RecyclerView) findViewById(R.id.rvFilmRecommend);
        movieList = new ArrayList<Team2B_SubItem>();

        //Asynctask - OKHttp
        String Load_url = "https://api.themoviedb.org/3/movie/upcoming?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&language=ko-KR&page=1";


        String[] strings = {Load_url};
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(strings[0]);

        //LayoutManager
        rvFilmRecommend.setLayoutManager(new LinearLayoutManager(Search.this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // 뒤로가기 버튼을 툴바에 추가
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(v -> {
            onBackPressed(); // 뒤로가기 버튼을 클릭하면 액티비티 종료
        });

        // 커스텀 서치뷰 레이아웃을 툴바에 설정
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View searchViewLayout = inflater.inflate(R.layout.custom_search_view, toolbar, false);
        toolbar.addView(searchViewLayout);

        SearchView searchView = findViewById(R.id.search_view);
        searchView.setQueryHint("프로그램, 영화, 배우 검색...");  // 원하는 힌트로 변경
        searchView.setIconified(false); // SearchView 확장

        resultTextView = findViewById(R.id.result_text_view); // 결과를 표시할 TextView

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(searchView, InputMethodManager.SHOW_IMPLICIT);

        searchView.setOnClickListener(v -> {
            searchView.setIconified(false); // SearchView 확장
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // 검색어 제출 시 처리할 내용을 작성합니다.
                String search_url = "https://api.themoviedb.org/3/search/movie?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&query=" + query + "&language=ko-KR&page=1";
                String[] strings = {search_url};
                MyAsyncTask mAsyncTask = new MyAsyncTask();
                mAsyncTask.execute(strings[0]);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // 검색어가 변경될 때마다 처리할 내용을 작성합니다.
                resultTextView.setText(newText); // 텍스트뷰에 검색어 표시
                String search_url = "https://api.themoviedb.org/3/search/movie?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&query=" + newText + "&language=ko-KR&page=1";
                String[] strings = {search_url};
                MyAsyncTask mAsyncTask = new MyAsyncTask();
                mAsyncTask.execute(strings[0]);
                return true;
            }
        });
    }

    public class MyAsyncTask extends AsyncTask<String, Void, Team2B_SubItem[]> {
        //로딩중 표시
        ProgressDialog progressDialog = new ProgressDialog(Search.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            //show dialog
            progressDialog.show();

            //목록 배열의 내용을 클리어 해 놓는다.
            movieList.clear();

        }

        @Override
        protected Team2B_SubItem[] doInBackground(String... strings) {
            // 처음에 영화를 불러올때랑 검색결과를 불러올때 둘 다 여기에서 진행
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
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");
                // Movie클래스에 데이터 저장
                Team2B_SubItem[] posts = gson.fromJson(rootObject, Team2B_SubItem[].class);
                return posts;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Team2B_SubItem[] result) {
            super.onPostExecute(result);
            progressDialog.dismiss();
            //ArrayList에 차례대로 집어 넣는다.
            if (result.length > 0) {
                for (Team2B_SubItem p : result) {
                    movieList.add(p);
                }
            }

            //어답터 설정
            adapter = new SearchListAdapter(Search.this, movieList);
            rvFilmRecommend.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }
}
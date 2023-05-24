package com.jh.team2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jh.team2.Model.API;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Team2D_Fragment_pop extends Fragment {

    // 사실 ott 중복선택 가능하게 하고싶어서 토글버튼 사용한건데 아직 방법을 모르겠어서 일단 단일선택으로 구현했습니다.

    private RecyclerView recyclerView;
    Button btnPrevious;
    private Team2B_SubItemAdapter adapter;
    ArrayList<Team2B_SubItem> movieListPop;
    int page = 1;
    String[] strings;

    // 5/22 추가 ott버튼
    ToggleButton btnNetflix, btnWatcha, btnDisneyPlus, btnWavve;
    boolean Netflix, Watcha, DisneyPlus, Wavve;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_team2d_fragment_pop, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        btnPrevious = (Button)v.findViewById(R.id.btnPrevious);

        // 5/22 추가 ott버튼
        btnNetflix = (ToggleButton)v.findViewById(R.id.btnNetflix);
        btnWatcha = (ToggleButton)v.findViewById(R.id.btnWatcha);
        btnDisneyPlus = (ToggleButton)v.findViewById(R.id.btnDisneyPlus);
        btnWavve = (ToggleButton)v.findViewById(R.id.btnWavve);

        movieListPop = new ArrayList<Team2B_SubItem>();
        btnPrevious.setVisibility(View.INVISIBLE);

        //Asynctask - OKHttp
        load_main(1);

        //LayoutManager
       // recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing); // Define the desired spacing in pixels

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);

// Create an instance of GridSpacingItemDecoration and set it as the item decoration for the RecyclerView
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, spacingInPixels, true);
        recyclerView.addItemDecoration(itemDecoration);
        // 페이지버튼
        v.findViewById(R.id.btnPrevious).setOnClickListener(mClickListener);
        v.findViewById(R.id.btnNext).setOnClickListener(mClickListener);

        // OTT버튼
        v.findViewById(R.id.btnNetflix).setOnClickListener(mClick);
        v.findViewById(R.id.btnWatcha).setOnClickListener(mClick);
        v.findViewById(R.id.btnDisneyPlus).setOnClickListener(mClick);
        v.findViewById(R.id.btnWavve).setOnClickListener(mClick);

        return v;
    }

    // 이전 다음 버튼
    Button.OnClickListener mClickListener = new Button.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnPrevious:
                    page--;
                    load_main(page);
                    Log.d("Page", page + "");
                    break;
                case R.id.btnNext:
                    page++;
                    Log.d("Page", page + "");
                    load_main(page);
                    break;
            }
        }
    };

    // 5/22 추가 ott버튼 선택
    ToggleButton.OnClickListener mClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnNetflix:
                    if (btnNetflix.isSelected()) {
                        NetflixSelectN();
                        if(!Netflix&&!Watcha&&!DisneyPlus&&!Wavve) load_main(page);
                    } else {
                        NetflixSelectY();
                        WatchaSelectN();
                        DisneyPlusSelectN();
                        WavveSelectN();
                        load_ott(8, 1);
                    }
                    break;
                case R.id.btnWatcha:
                    if (btnWatcha.isSelected()) {
                        WatchaSelectN();
                        if(!Netflix&&!Watcha&&!DisneyPlus&&!Wavve) load_main(page);
                    } else {
                        WatchaSelectY();
                        NetflixSelectN();
                        DisneyPlusSelectN();
                        WavveSelectN();
                        load_ott(97, 1);
                    }
                    break;
                case R.id.btnDisneyPlus:
                    if (btnDisneyPlus.isSelected()) {
                        DisneyPlusSelectN();
                        if(!Netflix&&!Watcha&&!DisneyPlus&&!Wavve) load_main(page);
                    } else {
                        DisneyPlusSelectY();
                        NetflixSelectN();
                        WatchaSelectN();
                        WavveSelectN();
                        load_ott(337, 1);
                    }
                    break;
                case R.id.btnWavve:
                    if (btnWavve.isSelected()) {
                        WavveSelectN();
                        if(!Netflix&&!Watcha&&!DisneyPlus&&!Wavve) load_main(page);
                    } else {
                        WavveSelectY();
                        NetflixSelectN();
                        WatchaSelectN();
                        DisneyPlusSelectN();
                        load_ott(356, 1);
                    }
                    break;
            }
        }
    };

    void WavveSelectN() {
        btnWavve.setBackgroundResource(R.drawable.clear);
        btnWavve.setSelected(false);
        Wavve = false;
    }
    void WavveSelectY() {
        btnWavve.setBackgroundResource(R.drawable.clear_60);
        btnWavve.setSelected(true);
        Wavve = true;
    }
    void DisneyPlusSelectN() {
        btnDisneyPlus.setBackgroundResource(R.drawable.clear);
        btnDisneyPlus.setSelected(false);
        DisneyPlus = false;
    }
    void DisneyPlusSelectY() {
        btnDisneyPlus.setBackgroundResource(R.drawable.clear_60);
        btnDisneyPlus.setSelected(true);
        DisneyPlus = true;
    }
    void WatchaSelectN() {
        btnWatcha.setBackgroundResource(R.drawable.clear);
        btnWatcha.setSelected(false);
        Watcha = false;
    }
    void WatchaSelectY() {
        btnWatcha.setBackgroundResource(R.drawable.clear_60);
        btnWatcha.setSelected(true);
        Watcha = true;
    }
    void NetflixSelectN() {
        btnNetflix.setBackgroundResource(R.drawable.clear);
        btnNetflix.setSelected(false);
        Netflix = false;
    }
    void NetflixSelectY() {
        btnNetflix.setBackgroundResource(R.drawable.clear_60);
        btnNetflix.setSelected(true);
        Netflix = true;
    }


    // 5/22 추가 ott 필터링
    void load_ott(int provider, int page) {
        if(page > 1) btnPrevious.setVisibility(View.VISIBLE);
        else btnPrevious.setVisibility(View.INVISIBLE);
        String Load_url = API.base_url + API.api_discover + API.api_key + API.with_provider + provider + API.LangSortPage + page;
        strings = new String[]{Load_url};
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(strings[0]);
    }

    void load_main(int page) {
        if(page > 1) btnPrevious.setVisibility(View.VISIBLE);
        else btnPrevious.setVisibility(View.INVISIBLE);
        String Load_url;
//        if(API.isPop)
        Load_url = API.base_url + API.pop + API.api_key + API.regionLang + API.page + page;
//        else Load_url = API.base_url + API.top + API.api_key + API.regionLang + API.page + page;

        strings = new String[]{Load_url};
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(strings[0]);
    }

    public class MyAsyncTask extends AsyncTask<String, Void, Team2B_SubItem[]> {
        //로딩중 표시
        ProgressDialog progressDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            //show dialog
            progressDialog.show();

            movieListPop.clear();

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
            if(result.length > 0){
                for(Team2B_SubItem p : result){
                    movieListPop.add(p);
                }
            }

            //어답터 설정
            adapter = new Team2B_SubItemAdapter(getActivity(), movieListPop);
            recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }

    }
}
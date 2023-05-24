package com.jh.team2;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
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

public class Team2E_Fragment_top extends Fragment {

    private RecyclerView recyclerView;
    Button btnPrevious;
    private Team2B_SubItemAdapter adapter;
    ArrayList<Team2B_SubItem> movieListPop;
    int page = 1;
    String[] strings;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_team2e_fragment_top, container, false);

        recyclerView = (RecyclerView)v.findViewById(R.id.recycler_view);
        btnPrevious = (Button)v.findViewById(R.id.btnPrevious);
        movieListPop = new ArrayList<Team2B_SubItem>();
        btnPrevious.setVisibility(View.INVISIBLE);

        //Asynctask - OKHttp
        load_main(1);

        //LayoutManager
//        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.spacing); // Define the desired spacing in pixels

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(layoutManager);
        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, spacingInPixels, true);
        recyclerView.addItemDecoration(itemDecoration);

        // 페이지버튼
        v.findViewById(R.id.btnPrevious).setOnClickListener(mClickListener);
        v.findViewById(R.id.btnNext).setOnClickListener(mClickListener);

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

    void load_main(int page) {
        if(page > 1) btnPrevious.setVisibility(View.VISIBLE);
        else btnPrevious.setVisibility(View.INVISIBLE);
        String Load_url;
//        if(API.isPop)
//        Load_url = API.base_url + API.pop + API.api_key + API.regionLang + API.page + page;
//        else .
        Load_url = API.base_url + API.top + API.api_key + API.regionLang + API.page + page;

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
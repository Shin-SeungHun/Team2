//package com.jh.team2;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.app.ProgressDialog;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.os.Handler;
//import android.util.Log;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonElement;
//import com.google.gson.JsonParser;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class MainActivity extends AppCompatActivity {
//
//    String[] strings;
//    ArrayList<Team2B_SubItem> movieListPop;
//    ArrayList<Team2B_SubItem> movieListTop;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        String base_url = "https://api.themoviedb.org/3/";
//        String api_key = "000ddb7fd6589cf82b163f9d79e7e8c1";
//
//        movieListPop = new ArrayList<Team2B_SubItem>();
//        movieListTop = new ArrayList<Team2B_SubItem>();
//
////        https://api.themoviedb.org/3/discover/movie?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&watch_region=KR&language=ko-KR&with_genres=28&sort_by=popularity.desc
//        String Load_url = base_url + "movie/popular?api_key=" + api_key + "&language=ko-KR&page=1";
//        strings = new String[]{Load_url};
//        MyAsyncTask mAsyncTask = new MyAsyncTask();
//        mAsyncTask.execute(strings[0]);
//
//        String Load_url1 = base_url + "movie/top_rated?api_key=" + api_key + "&language=ko-KR&page=1";
//        strings = new String[]{Load_url1};
//        MyAsyncTask mAsyncTask1 = new MyAsyncTask();
//        mAsyncTask1.execute(strings[0]);
//
//        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                // 상위 리사이클러뷰 설정
//                RecyclerView rvItem = (RecyclerView) findViewById(R.id.rv_item);
//                LinearLayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
//                Team2B_ItemAdapter itemAdapter = new Team2B_ItemAdapter(MainActivity.this, buildItemList());
//                rvItem.setAdapter(itemAdapter);
//                rvItem.setLayoutManager(layoutManager);
//            }
//        }, 500);
//
//    }
//
//    // 상위아이템 큰박스 아이템을 10개 만듭니다.
//    private List<Team2B_Item> buildItemList() {
//        List<Team2B_Item> itemList = new ArrayList<>();
//
//        // popular
//        Team2B_Item item = new Team2B_Item("popular", movieListPop);
//        itemList.add(item);
//
//        // top-rated
//        Team2B_Item item1 = new Team2B_Item("top-rated", movieListTop);
//        itemList.add(item1);
//
//        return itemList;
//    }
//
//    // 그안에 존재하는 하위 아이템 박스
//    private ArrayList<Team2B_SubItem> buildSubItemListPopular() {
//
//
//        return movieListPop;
//    }
//
//    private ArrayList<Team2B_SubItem> buildSubItemListTopRated() {
//
//        return movieListTop;
//    }
//
//    public class MyAsyncTask extends AsyncTask<String, Void, Team2B_SubItem[]> {
//
//        int i = 0;
//        //로딩중 표시
//        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
//            progressDialog.setMessage("\t로딩중...");
//            //show dialog
//            progressDialog.show();
//
//            //목록 배열의 내용을 클리어 해 놓는다.
////            movieListPop.clear();
//
//        }
//
//        @Override
//        protected Team2B_SubItem[] doInBackground(String... strings) {
//            // 처음에 영화를 불러올때랑 검색결과를 불러올때 둘 다 여기에서 진행
//            // doInBackground(String... strings) << 여기에서 strings 를 받아와 url에 넣음
//
//            // url 확인 Log
//            Log.d("AsyncTask", "url : " + strings[0]);
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder()
//                    .url(strings[0])
//                    .build();
//
//            if(strings[0].equals("https://api.themoviedb.org/3/movie/popular?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&language=ko-KR&page=1")) i = 1;
//            else if(strings[0].equals("https://api.themoviedb.org/3/movie/top_rated?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&language=ko-KR&page=1")) i = 2;
//
//            // 리퀘스트 결과
//            try {
//                Response response = client.newCall(request).execute();
//                Gson gson = new GsonBuilder().create();
//                JsonParser parser = new JsonParser();
//                JsonElement rootObject = parser.parse(response.body().charStream())
//                        .getAsJsonObject().get("results");
//                // SubItem클래스에 데이터 저장
//                Team2B_SubItem[] posts = gson.fromJson(rootObject, Team2B_SubItem[].class);
////                Log.d("check", "1111");
//                return posts;
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//
//
//        @Override
//        protected void onPostExecute(Team2B_SubItem[] result) {
//            super.onPostExecute(result);
//            progressDialog.dismiss();
//            //ArrayList에 차례대로 집어 넣는다.
//            if (result.length > 0) {
//                for (Team2B_SubItem p : result) {
//                    if(i == 1)  movieListPop.add(p);
//                    else if(i == 2)  movieListTop.add(p);
////                    Log.d("check", "2222");
//                }
//
//            }
//
//        }
//    }
//}
package com.jh.team2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jh.team2.Model.API;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Team2B_FragmentMain extends Fragment {

    String[] strings;
    ArrayList<Team2B_SubItem> movieListAction;
    ArrayList<Team2B_SubItem> movieListAdventure;
    ArrayList<Team2B_SubItem> movieListAnimation;
    ArrayList<Team2B_SubItem> movieListCrime;
    ArrayList<Team2B_SubItem> movieListDocumentary;
    ArrayList<Team2B_SubItem> movieListDrama;
    ArrayList<Team2B_SubItem> movieListFamily;
    ArrayList<Team2B_SubItem> movieListFantasy;
    ArrayList<Team2B_SubItem> movieListHistory;
    ArrayList<Team2B_SubItem> movieListMusic;
    ArrayList<Team2B_SubItem> movieListMystery;
    ArrayList<Team2B_SubItem> movieListScienceFiction;
    ArrayList<Team2B_SubItem> movieListTVMovie;
    ArrayList<Team2B_SubItem> movieListThriller;
    ArrayList<Team2B_SubItem> movieListWar;
    ArrayList<Team2B_SubItem> movieListWestern;
    ArrayList<Team2B_SubItem> movieListComedy;
    ArrayList<Team2B_SubItem> movieListHorror;
    ArrayList<Team2B_SubItem> movieListRomance;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_team2b_fragment_main, container, false);

// 12 모험 16 애니 80 범죄 99 다큐멘터리 18 드라마 10751 가족 14 판타지 36 역사 27 공포 10402 음악 9648 미스터리 878 sf 10770 tv영화 53스릴러 10752 전쟁
        int[] genre = {28, 12, 16, 80, 99,
                        18, 10751, 14, 36, 10402,
                        9648, 878, 10770, 53, 10752,
                        37, 35, 27, 10749}; // Action_28 / Comedy_35 / Horror_27 / Romance_10749

        movieListAction = new ArrayList<Team2B_SubItem>();
        movieListAdventure = new ArrayList<Team2B_SubItem>();
        movieListAnimation = new ArrayList<Team2B_SubItem>();
        movieListCrime = new ArrayList<Team2B_SubItem>();
        movieListDocumentary = new ArrayList<Team2B_SubItem>();
        movieListDrama = new ArrayList<Team2B_SubItem>();
        movieListFamily = new ArrayList<Team2B_SubItem>();
        movieListFantasy = new ArrayList<Team2B_SubItem>();
        movieListHistory = new ArrayList<Team2B_SubItem>();
        movieListMusic = new ArrayList<Team2B_SubItem>();
        movieListMystery = new ArrayList<Team2B_SubItem>();
        movieListScienceFiction = new ArrayList<Team2B_SubItem>();
        movieListTVMovie = new ArrayList<Team2B_SubItem>();
        movieListThriller = new ArrayList<Team2B_SubItem>();
        movieListWar = new ArrayList<Team2B_SubItem>();
        movieListWestern = new ArrayList<Team2B_SubItem>();
        movieListComedy = new ArrayList<Team2B_SubItem>();
        movieListHorror = new ArrayList<Team2B_SubItem>();
        movieListRomance = new ArrayList<Team2B_SubItem>();


        String Load_Action_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[0] + API.sort;
        strings = new String[]{Load_Action_url};
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(strings[0]);

        String Load_Adventure_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[1] + API.sort;
        strings = new String[]{Load_Adventure_url};
        MyAsyncTask mAsyncTask1 = new MyAsyncTask();
        mAsyncTask1.execute(strings[0]);

        String Load_Animation_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[2] + API.sort;
        strings = new String[]{Load_Animation_url};
        MyAsyncTask mAsyncTask2 = new MyAsyncTask();
        mAsyncTask2.execute(strings[0]);

        String Load_Crime_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[3] + API.sort;
        strings = new String[]{Load_Crime_url};
        MyAsyncTask mAsyncTask3 = new MyAsyncTask();
        mAsyncTask3.execute(strings[0]);

        String Load_Documentary_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[4] + API.sort;
        strings = new String[]{Load_Documentary_url};
        MyAsyncTask mAsyncTask4 = new MyAsyncTask();
        mAsyncTask4.execute(strings[0]);

        String Load_Drama_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[5] + API.sort;
        strings = new String[]{Load_Drama_url};
        MyAsyncTask mAsyncTask5 = new MyAsyncTask();
        mAsyncTask5.execute(strings[0]);

        String Load_Family_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[6] + API.sort;
        strings = new String[]{Load_Family_url};
        MyAsyncTask mAsyncTask6 = new MyAsyncTask();
        mAsyncTask6.execute(strings[0]);

        String Load_Fantasy_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[7] + API.sort;
        strings = new String[]{Load_Fantasy_url};
        MyAsyncTask mAsyncTask7 = new MyAsyncTask();
        mAsyncTask7.execute(strings[0]);

        String Load_History_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[8] + API.sort;
        strings = new String[]{Load_History_url};
        MyAsyncTask mAsyncTask8 = new MyAsyncTask();
        mAsyncTask8.execute(strings[0]);

        String Load_Music_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[9] + API.sort;
        strings = new String[]{Load_Music_url};
        MyAsyncTask mAsyncTask9 = new MyAsyncTask();
        mAsyncTask9.execute(strings[0]);

        String Load_Mystery_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[10] + API.sort;
        strings = new String[]{Load_Mystery_url};
        MyAsyncTask mAsyncTask10 = new MyAsyncTask();
        mAsyncTask10.execute(strings[0]);

        String Load_ScienceFiction_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[11] + API.sort;
        strings = new String[]{Load_ScienceFiction_url};
        MyAsyncTask mAsyncTask11 = new MyAsyncTask();
        mAsyncTask11.execute(strings[0]);

        String Load_TVMovie_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[12] + API.sort;
        strings = new String[]{Load_TVMovie_url};
        MyAsyncTask mAsyncTask12 = new MyAsyncTask();
        mAsyncTask12.execute(strings[0]);

        String Load_Thriller_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[13] + API.sort;
        strings = new String[]{Load_Thriller_url};
        MyAsyncTask mAsyncTask13 = new MyAsyncTask();
        mAsyncTask13.execute(strings[0]);

        String Load_War_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[14] + API.sort;
        strings = new String[]{Load_War_url};
        MyAsyncTask mAsyncTask14 = new MyAsyncTask();
        mAsyncTask14.execute(strings[0]);

        String Load_Western_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[15] + API.sort;
        strings = new String[]{Load_Western_url};
        MyAsyncTask mAsyncTask15 = new MyAsyncTask();
        mAsyncTask15.execute(strings[0]);

        String Load_Comedy_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[16] + API.sort;
        strings = new String[]{Load_Comedy_url};
        MyAsyncTask mAsyncTask16 = new MyAsyncTask();
        mAsyncTask16.execute(strings[0]);

        String Load_Horror_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[17] + API.sort;
        strings = new String[]{Load_Horror_url};
        MyAsyncTask mAsyncTask17 = new MyAsyncTask();
        mAsyncTask17.execute(strings[0]);

        String Load_Romance_url = API.base_url + API.api_discover + API.api_key + API.regionLang + API.api_genres + genre[18] + API.sort;
        strings = new String[]{Load_Romance_url};
        MyAsyncTask mAsyncTask18 = new MyAsyncTask();
        mAsyncTask18.execute(strings[0]);


        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // 상위 리사이클러뷰 설정
                RecyclerView rvItem = (RecyclerView) v.findViewById(R.id.rv_item);
                LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
                Team2B_ItemAdapter itemAdapter = new Team2B_ItemAdapter(getActivity(), buildItemList());
                rvItem.setAdapter(itemAdapter);
                rvItem.setLayoutManager(layoutManager);
            }
        }, 2000);

        return v;
    }

    // 상위아이템 큰박스 아이템을 10개 만듭니다.
    private List<Team2B_Item> buildItemList() {
        List<Team2B_Item> itemList = new ArrayList<>();

        // Shuffle the movie lists
        Collections.shuffle(movieListAction);
        Collections.shuffle(movieListAdventure);
        Collections.shuffle(movieListAnimation);
        Collections.shuffle(movieListCrime);
        Collections.shuffle(movieListDocumentary);
        Collections.shuffle(movieListDrama);
        Collections.shuffle(movieListFamily);
        Collections.shuffle(movieListFantasy);
        Collections.shuffle(movieListHistory);
        Collections.shuffle(movieListMusic);
        Collections.shuffle(movieListMystery);
        Collections.shuffle(movieListScienceFiction);
        Collections.shuffle(movieListTVMovie);
        Collections.shuffle(movieListThriller);
        Collections.shuffle(movieListWar);
        Collections.shuffle(movieListWestern);
        Collections.shuffle(movieListComedy);
        Collections.shuffle(movieListHorror);
        Collections.shuffle(movieListRomance);

        // Shuffle the remaining movie lists



        // Action
        Team2B_Item ActionItem = new Team2B_Item("액션", movieListAction);
        itemList.add(ActionItem);

        // Adventure
        Team2B_Item AdventureItem = new Team2B_Item("모험", movieListAdventure);
        itemList.add(AdventureItem);

        // Animation
        Team2B_Item AnimationItem = new Team2B_Item("애니메이션", movieListAnimation);
        itemList.add(AnimationItem);

        // Crime
        Team2B_Item CrimeItem = new Team2B_Item("범죄", movieListCrime);
        itemList.add(CrimeItem);

        // Documentary
        Team2B_Item DocumentaryItem = new Team2B_Item("다큐멘터리", movieListDocumentary);
        itemList.add(DocumentaryItem);

        // Drama
        Team2B_Item DramaItem = new Team2B_Item("드라마", movieListDrama);
        itemList.add(DramaItem);

        // Family
        Team2B_Item FamilyItem = new Team2B_Item("가족", movieListFamily);
        itemList.add(FamilyItem);

        // Fantasy
        Team2B_Item FantasyItem = new Team2B_Item("판타지", movieListFantasy);
        itemList.add(FantasyItem);

        // History
        Team2B_Item HistoryItem = new Team2B_Item("역사", movieListHistory);
        itemList.add(HistoryItem);

        // Music
        Team2B_Item MusicItem = new Team2B_Item("음악", movieListMusic);
        itemList.add(MusicItem);

        // Mystery
        Team2B_Item MysteryItem = new Team2B_Item("미스터리", movieListMystery);
        itemList.add(MysteryItem);

        // SF
        Team2B_Item ScienceFiction = new Team2B_Item("SF", movieListScienceFiction);
        itemList.add(ScienceFiction);

        // TV Movie
        Team2B_Item TVMovieItem = new Team2B_Item("TV 영화", movieListTVMovie);
        itemList.add(TVMovieItem);

        // Thriller
        Team2B_Item ThrillerItem = new Team2B_Item("스릴러", movieListThriller);
        itemList.add(ThrillerItem);

        // War
        Team2B_Item WarItem = new Team2B_Item("전쟁", movieListWar);
        itemList.add(WarItem);

        // Western
        Team2B_Item WesternItem = new Team2B_Item("서부", movieListWestern);
        itemList.add(WesternItem);

        // Comedy
        Team2B_Item ComedyItem = new Team2B_Item("코미디", movieListComedy);
        itemList.add(ComedyItem);

        // Horror
        Team2B_Item HorrorItem = new Team2B_Item("호러", movieListHorror);
        itemList.add(HorrorItem);

        // Romance
        Team2B_Item RomanceItem = new Team2B_Item("로맨스", movieListRomance);
        itemList.add(RomanceItem);


//        for(int i=0; i< 4; i++){
//            Collections.shuffle(itemList);
//        }
        return itemList;
    }

    public class MyAsyncTask extends AsyncTask<String, Void, Team2B_SubItem[]> {

        int i = 0;
        //로딩중 표시
        ProgressDialog progressDialog = new ProgressDialog(getActivity());

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("\t로딩중...");
            //show dialog
            progressDialog.show();

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

            // url 안에 해당 장르번호가 있는지 확인
            if (strings[0].contains("with_genres=28")) i = 1;
            else if (strings[0].contains("with_genres=12")) i = 2;
            else if (strings[0].contains("with_genres=16")) i = 3;
            else if (strings[0].contains("with_genres=80")) i = 4;
            else if (strings[0].contains("with_genres=99")) i = 5;
            else if (strings[0].contains("with_genres=18")) i = 6;
            else if (strings[0].contains("with_genres=10751")) i = 7;
            else if (strings[0].contains("with_genres=14")) i = 8;
            else if (strings[0].contains("with_genres=36")) i = 9;
            else if (strings[0].contains("with_genres=10402")) i = 10;
            else if (strings[0].contains("with_genres=9648")) i = 11;
            else if (strings[0].contains("with_genres=878")) i = 12;
            else if (strings[0].contains("with_genres=10770")) i = 13;
            else if (strings[0].contains("with_genres=53")) i = 14;
            else if (strings[0].contains("with_genres=10752")) i = 15;
            else if (strings[0].contains("with_genres=37")) i = 16;
            else if (strings[0].contains("with_genres=35")) i = 17;
            else if (strings[0].contains("with_genres=27")) i = 18;
            else if (strings[0].contains("with_genres=10749")) i = 19;

            // 리퀘스트 결과
            try {
                Response response = client.newCall(request).execute();
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");
//                Log.d("JsonElement", rootObject+"");
                // SubItem클래스에 데이터 저장
                Team2B_SubItem[] posts = gson.fromJson(rootObject, Team2B_SubItem[].class);
//                Log.d("check", "1111");
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
                    if (i == 1) movieListAction.add(p);
                    else if (i == 2) movieListAdventure.add(p);
                    else if (i == 3) movieListAnimation.add(p);
                    else if (i == 4) movieListCrime.add(p);
                    else if (i == 5) movieListDocumentary.add(p);
                    else if (i == 6) movieListDrama.add(p);
                    else if (i == 7) movieListFamily.add(p);
                    else if (i == 8) movieListFantasy.add(p);
                    else if (i == 9) movieListHistory.add(p);
                    else if (i == 10) movieListMusic.add(p);
                    else if (i == 11) movieListMystery.add(p);
                    else if (i == 12) movieListScienceFiction.add(p);
                    else if (i == 13) movieListTVMovie.add(p);
                    else if (i == 14) movieListThriller.add(p);
                    else if (i == 15) movieListWar.add(p);
                    else if (i == 16) movieListWestern.add(p);
                    else if (i == 17) movieListComedy.add(p);
                    else if (i == 18) movieListHorror.add(p);
                    else if (i == 19) movieListRomance.add(p);
//                    Log.d("check", "2222");
                }

            }

        }
    }

}

//        1. 프래그먼트 클래스 Team2B_FragmentMain은 Fragment 클래스를 상속하고 onCreateView 메서드를 재정의하여 레이아웃을 확장합니다.
//        2. 다양한 장르의 영화를 나타내는 것처럼 보이는 'Team2B_SubItem' 유형의 여러 ArrayList를 정의합니다.
//        3. onCreateView 메서드에서 MyAsyncTask 클래스의 여러 인스턴스를 생성하고 실행하여 장르에 따라 다른 URL에서 영화 데이터를 가져옵니다.
//        4. 2000밀리초의 지연 후 'Handler'를 사용하여 UI를 업데이트하고 가져온 동영상 데이터를 표시하도록 RecyclerView를 설정합니다.
//        5. buildItemList 메서드는 다양한 장르의 영화를 나타내는 Team2B_Item 개체 목록을 생성하고 해당 ArrayList로 채웁니다.
//        6. MyAsyncTask 클래스는 AsyncTask를 확장하는 내부 클래스로 API에서 비동기적으로 데이터를 가져오는 역할을 합니다.
//        7. MyAsyncTask의 doInBackground 메소드에서 제공된 URL을 사용하여 OkHttp 라이브러리를 사용하여 HTTP 요청을 합니다.
//        8. 가져온 영화 데이터는 처리되어 Team2B_SubItem 개체의 배열로 변환됩니다.
//        9. MyAsyncTask의 onPostExecute 메서드는 일반적으로 검색된 동영상 데이터를 처리하고 해당 ArrayList를 업데이트합니다.
//        10.데이터 로드 프로세스 중에 진행률 대화 상자가 표시됩니다.
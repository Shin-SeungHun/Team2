package com.jh.team2.ViewModel;



import android.os.AsyncTask;

import androidx.lifecycle.ViewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jh.team2.Team2B_SubItem;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class SearchViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Team2B_SubItem>> movieListLiveData = new MutableLiveData<>();

    public LiveData<ArrayList<Team2B_SubItem>> getMovieListLiveData() {
        return movieListLiveData;
    }

    public void searchMovies(String query) {
        String searchUrl = "https://api.themoviedb.org/3/search/movie?api_key=000ddb7fd6589cf82b163f9d79e7e8c1&query=" + query + "&language=ko-KR&page=1";

        // Perform the search operation and update the movie list LiveData with the results
        MyAsyncTask mAsyncTask = new MyAsyncTask();
        mAsyncTask.execute(searchUrl);
    }

    private class MyAsyncTask extends AsyncTask<String, Void, Team2B_SubItem[]> {
        @Override
        protected Team2B_SubItem[] doInBackground(String... strings) {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(strings[0])
                    .build();

            try {
                Response response = client.newCall(request).execute();
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");
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

            if (result != null) {
                ArrayList<Team2B_SubItem> movieList = new ArrayList<>();
                for (Team2B_SubItem p : result) {
                    movieList.add(p);
                }
                movieListLiveData.setValue(movieList);
            }
        }
    }
}

package com.jh.team2.Model;

public class API {
    public static boolean isPop = true;

    public static String base_url = "https://api.themoviedb.org/3/";
    public static String api_discover = "discover/movie?";
    public static String api_movie = "movie/";
    public static String pop = "movie/popular?";
    public static String top = "movie/top_rated?";
    public static String providers = "/watch/providers?";
    public static String with_provider = "&with_watch_providers=";
    public static String api_key = "api_key=000ddb7fd6589cf82b163f9d79e7e8c1";
    public static String regionLang = "&region=KR&language=ko-KR";
    public static String page = "&page=";
    public static String api_genres = "&with_genres=";
    public static String sort = "&sort_by=popularity.desc";
    public static String LangSortPage = "&watch_region=KR&language=ko-KR&sort_by=popularity.desc&page=";


    // image
    public static String poster_url = "https://image.tmdb.org/t/p/w500";
}

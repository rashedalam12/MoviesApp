package com.example.windcore.moviesapp.apis;

import com.example.windcore.moviesapp.model.MovieDB;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieDBAPI {
    @GET("/3/movie/popular")
    Call<MovieDB> getPopularMovie(@Query("api_key") String apiKey);

    @GET("/3/movie/top_rated")
    Call<MovieDB> getTopRatedMovie(@Query("api_key") String apiKey);


}

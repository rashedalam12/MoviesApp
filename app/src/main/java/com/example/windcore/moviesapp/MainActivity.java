package com.example.windcore.moviesapp;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.windcore.moviesapp.adapter.PosterAdapter;
import com.example.windcore.moviesapp.apis.MovieDBAPI;
import com.example.windcore.moviesapp.model.MovieDB;
import com.example.windcore.moviesapp.model.Result;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    PosterAdapter adapter;
    List<Result> resultList;
    MovieDBAPI movieDBAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        rvMovies.setLayoutManager(new GridLayoutManager(this,2));

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.movieDBBaseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        movieDBAPI = retrofit.create(MovieDBAPI.class);
        getPopularMovie();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int itemId = item.getItemId();

        if (itemId == R.id.popularitem){
            getPopularMovie();
            return true;
        }
        if (itemId == R.id.top_rated){
            getTopRatedMovie();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected void getPopularMovie(){
        movieDBAPI.getPopularMovie(Constants.apiKey).enqueue(new Callback<MovieDB>() {
            @Override
            public void onResponse(Call<MovieDB> call, Response<MovieDB> response) {
                Log.d("mess","call made");
                resultList= response.body().getResults();
                adapter = new PosterAdapter(MainActivity.this,resultList);
                rvMovies.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MovieDB> call, Throwable t) {

               Toast.makeText(MainActivity.this,"Call Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void getTopRatedMovie(){
        movieDBAPI.getTopRatedMovie(Constants.apiKey).enqueue(new Callback<MovieDB>() {
            @Override
            public void onResponse(Call<MovieDB> call, Response<MovieDB> response) {
                Log.d("mess","call made");
                resultList= response.body().getResults();
                adapter = new PosterAdapter(MainActivity.this,resultList);
                rvMovies.setAdapter(adapter);
                //Log.i("list", resultList.size()+" inside" + resultList.get(0).getTitle());
            }

            @Override
            public void onFailure(Call<MovieDB> call, Throwable t) {

                Toast.makeText(MainActivity.this,"Call Failed", Toast.LENGTH_SHORT).show();
            }
        });

    }


}

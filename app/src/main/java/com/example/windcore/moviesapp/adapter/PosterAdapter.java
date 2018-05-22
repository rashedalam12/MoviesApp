package com.example.windcore.moviesapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.windcore.moviesapp.DetailsMovie;
import com.example.windcore.moviesapp.R;
import com.example.windcore.moviesapp.model.Result;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PosterAdapter extends RecyclerView.Adapter<PosterAdapter.PosterViewHolder> {

    private Context context;
    private List<Result> posterList;
    private Intent intent;

    public PosterAdapter(Context context, List posterList){
        this.context = context;
        this.posterList = posterList;
        intent = new Intent(context, DetailsMovie.class);
    }

    @Override
    public PosterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View posterItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_poster, parent, false);
        return new PosterViewHolder(posterItem);
    }

    @Override
    public void onBindViewHolder(PosterViewHolder holder, int position) {
        Picasso.get().load("http://image.tmdb.org/t/p/w780/"+posterList.get(position).getPosterPath()).into(holder.ivMovieposter);
    }

    @Override
    public int getItemCount() {
        return posterList.size();
    }


    public class PosterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public ImageView ivMovieposter;

        public PosterViewHolder(View itemView) { // The view is pass by the onCreateViewHolder which is a
            // representation of the item xml

            super(itemView);
            ivMovieposter = (ImageView) itemView.findViewById(R.id.iv_movieposter);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int x = getAdapterPosition();
            //Intent Extra
            intent.putExtra("OriginalTitle", posterList.get(x).getTitle());
            intent.putExtra("ReleaseDate",posterList.get(x).getReleaseDate());
            intent.putExtra("VoteAverage",posterList.get(x).getVoteAverage()/2.0+"");
            intent.putExtra("Synopsis",posterList.get(x).getOverview());
            intent.putExtra("PosterPath","http://image.tmdb.org/t/p/w780/"+posterList.get(x).getPosterPath());
            context.startActivity(intent);
        }
    }
}

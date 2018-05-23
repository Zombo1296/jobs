package com.dashlabs.interview.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.dashlabs.interview.activities.PosterActivity;
import com.dashlabs.interview.models.Poster;
import com.dashlabs.interview.R;


import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private Context mContext;
    private List<Poster> mData;

    //Glide's option
    RequestOptions option;

    public RecyclerViewAdapter(Context context, List<Poster> data) {
        mContext = context;
        mData = data;

        //request option for Gliad
        option = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(mContext);
        view = inflater.inflate(R.layout.poster_row_item,parent,false);

        //added
        final MyViewHolder viewHolder = new MyViewHolder(view);
        viewHolder.view_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, PosterActivity.class);
                intent.putExtra("extra_title", mData.get(viewHolder.getAdapterPosition()).getMovieTitle());
                intent.putExtra("extra_releaseDate", mData.get(viewHolder.getAdapterPosition()).getReleaseDate());
                intent.putExtra("extra_average", mData.get(viewHolder.getAdapterPosition()).getVoteAverage());
                intent.putExtra("extra_image", mData.get(viewHolder.getAdapterPosition()).getPosterURL());
                intent.putExtra("extra_overview", mData.get(viewHolder.getAdapterPosition()).getMovieOverview());

                mContext.startActivity(intent);
            }
        });

//        return new MyViewHolder(view);
        return  viewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        holder.movie_title.setText(mData.get(position).getMovieTitle());
        holder.vote_average.setText(mData.get(position).getVoteAverage());
        holder.release_date.setText(mData.get(position).getReleaseDate());
        holder.movie_overview.setText(mData.get(position).getMovieOverview());

        //load image into miss image field using Glide
        Glide.with(mContext).load(mData.get(position).getPosterURL()).apply(option).into(holder.img_poster);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView movie_title;
        TextView vote_average;
        TextView release_date;
        TextView movie_overview;
        ImageView img_poster;
        LinearLayout view_container;


        public MyViewHolder(View itemView) {
            super(itemView);

            view_container = itemView.findViewById(R.id.itemContainer);

            movie_title = itemView.findViewById(R.id.movieTitle);
            vote_average = itemView.findViewById(R.id.voteAverage);
            release_date = itemView.findViewById(R.id.releaseDate);
            movie_overview = itemView.findViewById(R.id.overview);
            img_poster = itemView.findViewById(R.id.posterImg);
        }
    }
}

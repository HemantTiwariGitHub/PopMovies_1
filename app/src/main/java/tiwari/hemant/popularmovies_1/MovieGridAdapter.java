package tiwari.hemant.popularmovies_1;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import com.squareup.picasso.Picasso;

/**
 * Created by GoluMicku1 on 11-09-2016.
 */
public class MovieGridAdapter extends RecyclerView.Adapter<MovieGridAdapter.MovieViewHolder>{

    private ArrayList<MovieDetails> mMovieList;

    public static class MovieViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;


        public MovieViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }

    MovieGridAdapter(ArrayList<MovieDetails> iMovieList)
    {
        mMovieList = iMovieList;
    }

    @Override
    public MovieGridAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View movieView = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item_grid, parent,false);
        MovieViewHolder movieViewHolder = new MovieViewHolder(movieView);

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {

        String imagePath = mMovieList.get(position).getmPosterURI();
        Picasso.with(holder.mImageView.getContext()).load(imagePath).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return  mMovieList.size();
    }

}

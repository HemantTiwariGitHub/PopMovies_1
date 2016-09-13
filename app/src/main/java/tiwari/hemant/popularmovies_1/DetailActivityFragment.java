package tiwari.hemant.popularmovies_1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetailActivityFragment extends Fragment {

    public DetailActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View detailView =  inflater.inflate(R.layout.movie_detail_layout, container, false);

        MovieDetails movieDetails = ((DetailActivity)getActivity()).getMovieDetail();


        ImageView posterImage = (ImageView)detailView.findViewById(R.id.movie_poster_image);
        TextView title = (TextView)detailView.findViewById(R.id.movie_name_title);
        TextView releaseDate = (TextView)detailView.findViewById(R.id.releaseDate);
        TextView rating = (TextView)detailView.findViewById(R.id.rating);
        TextView plotSynopsis = (TextView)detailView.findViewById(R.id.plotsynopsis);


        Picasso.with(getActivity()).load(movieDetails.getmPosterURI()).into(posterImage);
        title.setText(movieDetails.getmName());
        releaseDate.setText(movieDetails.getmReleaseDate());
        rating.setText(movieDetails.getmRating());
        plotSynopsis.setText(movieDetails.getmPlotSynopsis());


        return detailView;

    }
}

package tiwari.hemant.popularmovies_1;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

/**
 * Created by GoluMicku1 on 13-09-2016.
 */
public class MovieDataParser {

    final static String TAG = "PopM_" + MovieDataParser.class.getSimpleName();
    final static String TAG_PAGE = "page";
    final static String TAG_RESULTS = "results";

    final static String TAG_TITLE = "title";
    final static String TAG_POSTER = "poster_path";

    final static String TAG_SYNOPSIS = "overview";
    final static String TAG_RELEASE = "release_date";

    final static String TAG_VOTE = "vote_average";

    final static String POSTER_URI_PREPEND =  "http://image.tmdb.org/t/p/w185/";
    static ArrayList<MovieDetails> getMovieDataFromString(String str) throws JSONException
    {
        ArrayList<MovieDetails> sMovieDetailsList = new ArrayList<MovieDetails>();
        if(null == str)
        {
            return sMovieDetailsList;
        }

        JSONObject response = new JSONObject(str);
        JSONArray resultArray = response.getJSONArray(TAG_RESULTS);

        for (int i = 0; i < resultArray.length(); i++)
        {
            JSONObject movieObj = resultArray.getJSONObject(i);

            String title  =  movieObj.getString(TAG_TITLE);
            String poster = movieObj.getString(TAG_POSTER);
            String synopsis = movieObj.getString(TAG_SYNOPSIS);
            String releaseDate =  movieObj.getString(TAG_RELEASE);
            String vote = movieObj.getString(TAG_VOTE);
            String DEF_RUNTIME = "120";

            MovieDetails movieDetails =  new MovieDetails(title,DEF_RUNTIME,POSTER_URI_PREPEND+poster, releaseDate, vote, synopsis);

            Log.d(TAG, "Adding  :" + movieDetails.toString() );

            sMovieDetailsList.add(movieDetails);


        }

        return sMovieDetailsList;
    }
}

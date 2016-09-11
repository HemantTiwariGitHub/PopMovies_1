package tiwari.hemant.popularmovies_1;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.util.concurrent.Exchanger;
import android.content.Context;

/**
 * Created by GoluMicku1 on 11-09-2016.
 */
public class MovieDataDownloaderTask extends AsyncTask<Void, Void, Void> {

    private final static String TAG = "PopM_DownloaderTask";
    private static ArrayList<MovieDetails> mMovieDetailsList = new ArrayList<MovieDetails>();

    private static boolean TEST = false;

    static ArrayList<MovieDetails>  getMovieDetailList(Context c)
    {
        if(TEST) {
            try {
                getMovieDataFromString(localTestStringReader(c));
            }catch (Exception e)
            {
                Log.d(TAG, e.toString());
            }
        }
        return mMovieDetailsList;
    }

    static String  localTestStringReader(Context c)
    {
        String data= c.getString(R.string.testData);

        return data;
    }

   static void getMovieDataFromString(String str) throws JSONException
    {
        mMovieDetailsList.clear();
        final String TAG_PAGE = "page";
        final String TAG_RESULTS = "results";

        final String TAG_TITLE = "title";
        final String TAG_POSTER = "poster_path";

        final String TAG_SYNOPSIS = "overview";
        final String TAG_RELEASE = "release_date";

        final String TAG_VOTE = "vote_average";

        final String URI_PREPEND =  "http://image.tmdb.org/t/p/w185";


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

            MovieDetails movieDetails =  new MovieDetails(title,DEF_RUNTIME,URI_PREPEND+poster, releaseDate, vote, synopsis);

            Log.d(TAG, "Adding  :" + movieDetails.toString() );

            mMovieDetailsList.add(movieDetails);


        }






    }

    @Override
    protected Void doInBackground(Void... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String foreCasts[]=null;

// Will contain the raw JSON response as a string.
        String forecastJsonStr = null;

        try {
            // Construct the URL for the OpenWeatherMap query
            // Possible parameters are available at OWM's forecast API page, at
            // http://openweathermap.org/API#forecast
            String urlStr = "https://api.themoviedb.org/3/movie/popular?api_key=";

            Log.d(TAG, urlStr);

            URL url = new URL(urlStr);

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                forecastJsonStr = null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                forecastJsonStr = null;
            }
            forecastJsonStr = buffer.toString();
            Log.d(TAG, forecastJsonStr);
            getMovieDataFromString(forecastJsonStr);

        } catch (Exception e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            forecastJsonStr = null;
        } finally{
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e("PlaceholderFragment", "Error closing stream", e);
                }
            }

        }
        return null;
    }



}

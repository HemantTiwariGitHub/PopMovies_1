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
public class MovieDataDownloaderTask extends AsyncTask<Void, Void, String> {

    private final static String TAG = "PopM_" + MovieDataDownloaderTask.class.getSimpleName();
    private static ArrayList<MovieDetails> mMovieDetailsList = new ArrayList<MovieDetails>();
    private AsyncCompletionCallback mCallBack;
    private String MovieURL= "";
    private Context mContext;
    private static int mPage= 0;

    public interface AsyncCompletionCallback
    {
        void onDownloadComplete();
    }

    MovieDataDownloaderTask(AsyncCompletionCallback iCallBack, Context iContext)
    {
        mCallBack = iCallBack;
        mContext = iContext;
        mPage = mPage+1;
    }
    static  ArrayList<MovieDetails> getMovieDetailList()
    {
        return  mMovieDetailsList;
    }


    @Override
    protected void onPreExecute()
    {
        super.onPreExecute();

    }


    @Override
    protected String doInBackground(Void... strings) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String movieStr = null;

        try {

            URL url = new URL(Utils.prepareMovieURL(mContext,mPage));
            Log.d(TAG, url.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                movieStr = null;
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
                movieStr = null;
            }
            movieStr = buffer.toString();
            Log.d(TAG, movieStr);
           // mMovieDetailsList = MovieDataParser.getMovieDataFromString(movieStr);
          // mCallBack.onDownloadComplete();

        } catch (Exception e) {
            Log.e("PlaceholderFragment", "Error ", e);
            // If the code didn't successfully get the weather data, there's no point in attempting
            // to parse it.
            movieStr = null;
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
        return movieStr;
    }

    @Override
    protected void onPostExecute(String movieData) {
        super.onPostExecute(movieData);

        try {
            ArrayList<MovieDetails>  tempList = MovieDataParser.getMovieDataFromString(movieData);
            for(MovieDetails tMD : tempList)
            {
                mMovieDetailsList.add(tMD);
            }


        }catch(JSONException e)
        {
            Log.e(TAG, e.toString());
        }

        mCallBack.onDownloadComplete();

        Log.d(TAG,"delta");
    }
}

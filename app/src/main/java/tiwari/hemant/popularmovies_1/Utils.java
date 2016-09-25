package tiwari.hemant.popularmovies_1;

/**
 * Created by GoluMicku1 on 11-09-2016.
 */

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
public class Utils {


    public static String prepareMovieURL(Context ctext, int page)
    {

        SharedPreferences sPref= PreferenceManager.getDefaultSharedPreferences(ctext);

        String type = sPref.getString("pref_movie_list_type","top_rated");

        return  "https://api.themoviedb.org/3/movie/"+ type +"?api_key="+APIKEY.KEY+"page="+page;
    }

}

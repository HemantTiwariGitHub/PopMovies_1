package tiwari.hemant.popularmovies_1;

/**
 * Created by GoluMicku1 on 11-09-2016.
 */
public class Utils {


    public static String prepareMovieURL()
    {

        return  "https://api.themoviedb.org/3/movie/popular?api_key="+APIKEY.KEY;
    }

}

package tiwari.hemant.popularmovies_1;


/**
 * Created by GoluMicku1 on 11-09-2016.
 */
public class MovieDetails {

    private String mName;
    private String mPlotSynopsis;
    private String mRating;
    private String mReleaseDate;
    private String mPosterURI;
    private String mRunTime;

    public MovieDetails(String mName, String mRunTime, String mPosterURI, String mReleaseDate, String mRating, String mPlotSynopsis) {
        this.mName = mName;
        this.mRunTime = mRunTime;
        this.mPosterURI = mPosterURI;
        this.mReleaseDate = mReleaseDate;
        this.mRating = mRating;
        this.mPlotSynopsis = mPlotSynopsis;
    }


    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPlotSynopsis() {
        return mPlotSynopsis;
    }

    public void setmPlotSynopsis(String mPlotSynopsis) {
        this.mPlotSynopsis = mPlotSynopsis;
    }

    public String getmRating() {
        return mRating;
    }

    public void setmRating(String mRating) {
        this.mRating = mRating;
    }

    public String getmReleaseDate() {
        return mReleaseDate;
    }

    public void setmReleaseDate(String mReleaseDate) {
        this.mReleaseDate = mReleaseDate;
    }

    public String getmPosterURI() {
        return mPosterURI;
    }

    public void setmPosterURI(String mPosterURI) {
        this.mPosterURI = mPosterURI;
    }

    public String getmRunTime() {
        return mRunTime;
    }

    public void setmRunTime(String mRunTime) {
        this.mRunTime = mRunTime;
    }

    @Override
    public String toString() {
        return "[" + mName + " " + mPosterURI + " " + mRating + " " + mReleaseDate + " " + mRunTime + " " + mPlotSynopsis + " ]";
    }
}

package tiwari.hemant.popularmovies_1;


import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.Preference;
import android.app.Activity;
import tiwari.hemant.popularmovies_1.MovieSettingFragment;

public class SettingsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content,new MovieSettingFragment())
                .commit();
    }
}
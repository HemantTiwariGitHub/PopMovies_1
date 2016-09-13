package tiwari.hemant.popularmovies_1;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class DetailActivity extends AppCompatActivity {

    private int mPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getIntent().hasExtra("KEY_POS"))
        {
            mPosition = getIntent().getIntExtra("KEY_POS", 0);

        }

        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }


    MovieDetails getMovieDetail()
    {
        return MovieDataDownloaderTask.getMovieDetailList().get(mPosition);

    }

}

package tiwari.hemant.popularmovies_1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity implements MovieGridFragment.OnMovieSelectedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onMovieSelected(int pos) {
        //Toast.makeText(this, "Clicked " + MovieDataDownloaderTask.getMovieDetailList().get(pos).getmName(), Toast.LENGTH_SHORT).show();
        Intent detailIntent = new Intent(this, DetailActivity.class);

        detailIntent.putExtra("KEY_POS", pos);
        startActivity(detailIntent);

    }
}

package hess.fabian.filmverwaltung.detailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.addActivity.Images.MovieBackdropTask;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesContent;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.SeriesResultsPage;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = findViewById(R.id.detail_overview_textview);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle.getParcelable("movies") != null) {
            MovieResultsPage item = bundle.getParcelable("movies");
            toolbar.setTitle(item.getTitle());
            setSupportActionBar(toolbar);

            MovieBackdropTask backdropTask = new MovieBackdropTask();

            ImageView poster = findViewById(R.id.poster_detail);
            try {
                item.setBackdrop(backdropTask.execute(item.getBackdrop_path()).get());
                poster.setImageBitmap(item.getBackdrop());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }

            textView.setText(item.getOverview());
        }
        else if(bundle.getParcelable("series") != null) {
            SeriesResultsPage item = bundle.getParcelable("series");
            toolbar.setTitle(item.getTitle());
            setSupportActionBar(toolbar);

            textView.setText(item.getOverview());
        }
        else {
            System.out.println("Error: False intent extra.");
            return;
        }


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}

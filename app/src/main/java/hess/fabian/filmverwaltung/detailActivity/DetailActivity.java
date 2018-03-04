package hess.fabian.filmverwaltung.detailActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.mainActivity.movies.MovieContent;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesContent;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = findViewById(R.id.detail_overview_textview);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        if(intent.getSerializableExtra("movies") != null) {
            MovieContent.MovieItem item = (MovieContent.MovieItem) intent.getSerializableExtra("movies");
            toolbar.setTitle(item.title);
            setSupportActionBar(toolbar);

            textView.setText(item.overview);
        }
        else if(intent.getSerializableExtra("series") != null) {
            SeriesContent.SeriesItem item = (SeriesContent.SeriesItem) intent.getSerializableExtra("series");
            toolbar.setTitle(item.title);
            setSupportActionBar(toolbar);

            textView.setText(item.overview);
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

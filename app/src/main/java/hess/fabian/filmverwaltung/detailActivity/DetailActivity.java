package hess.fabian.filmverwaltung.detailActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.addActivity.Images.BackdropTask;
import hess.fabian.filmverwaltung.mainActivity.MainActivity;
import hess.fabian.filmverwaltung.tmdbApi.ResultsPage;

public class DetailActivity extends AppCompatActivity {

    private String media;
    private ResultsPage resultsPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView textView = findViewById(R.id.detail_overview_textview);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if(bundle.getParcelable("movies") != null) {
            media = "movies";
        }
        else if(bundle.getParcelable("series") != null) {
            media = "series";
        }
        else {
            System.out.println("Error: False intent extra.");
            return;
        }

        resultsPage = bundle.getParcelable(media);
        toolbar.setTitle(resultsPage.getTitle());
        setSupportActionBar(toolbar);
        textView.setText(resultsPage.getOverview());

        ImageView backdrop = findViewById(R.id.poster_detail);
        BackdropTask backdropTask = new BackdropTask(backdrop);
        backdropTask.execute(resultsPage);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, MainActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable(media, resultsPage);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }
}

package hess.fabian.filmverwaltung.addActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;

import hess.fabian.filmverwaltung.R;

public class AddActivity extends AppCompatActivity {

    private String media;
    private final String series = "series";
    private final String movies = "movies";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar);

        Intent intent = getIntent();
        media = intent.getStringExtra("media");

        if(media.equals(movies)) {
            toolbar.setTitle("Neuen Film hinzufügen:");
            setSupportActionBar(toolbar);
        }
        else if (media.equals(series)) {
            toolbar.setTitle("Neue Serie hinzufügen:");
            setSupportActionBar(toolbar);
        }
        else {
            System.out.println("Error: False intent extra.");
            return;
        }

        SearchView searchView = findViewById(R.id.searchview_add);
        CharSequence query = searchView.getQuery();

        // Download info from query
        if (media == series) {

        }
        else if (media == movies) {

        }
    }

}

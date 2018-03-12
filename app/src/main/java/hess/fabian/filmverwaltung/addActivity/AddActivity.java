package hess.fabian.filmverwaltung.addActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.SearchView;
import android.widget.Toast;
import java.util.concurrent.ExecutionException;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.mainActivity.MainActivity;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;

public class AddActivity extends AppCompatActivity {

    private SearchView searchView;
    private String media;
    private final String series = "series";
    private final String movies = "movies";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AddContent items = new AddContent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar_add);
        searchView = findViewById(R.id.searchview_add);

        recyclerView = findViewById(R.id.list_add);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AddRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);


        Intent intent = getIntent();
        media = intent.getStringExtra("media");

        if(media.equals(movies)) {
            toolbar.setTitle("Neuen Film hinzufügen:");
            setSupportActionBar(toolbar);
        }
        else if(media.equals(series)) {
            toolbar.setTitle("Neue Serie hinzufügen:");
            setSupportActionBar(toolbar);
        }
        else {
            System.out.println("Error: False intent extra.");
            return;
        }

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return onQueryText(s);
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return onQueryText(s);
            }
        });
    }

    private boolean onQueryText(String query) {
        if(query.equals("")) {
            return false;
        }
        // Download info from query
        if(media.equals(series)) {

        } else if(media.equals( movies)) {
            MovieTask movieTask = new MovieTask();
            try {
                MovieResultsPage resultsPage = movieTask.execute(query).get();

                if(resultsPage != null) {
                    Toast.makeText(this, "Resultspage: " + resultsPage.getTitle(), Toast.LENGTH_LONG).show();

                    AddContent.AddItem item = new AddContent.AddItem(String.valueOf(resultsPage.getId()), resultsPage.getTitle(), resultsPage.getOverview());
                    items.addItem(item);
                    adapter.notifyDataSetChanged();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public void onListFragmentInteraction(AddContent.AddItem item) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("add", item);
        startActivity(intent);
    }
}
package hess.fabian.filmverwaltung.addActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.detailActivity.DetailActivity;
import hess.fabian.filmverwaltung.mainActivity.MainActivity;
import hess.fabian.filmverwaltung.mainActivity.movies.MovieContent;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesContent;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.SeriesResultsPage;

public class AddActivity extends AppCompatActivity {

    private SearchView searchView;
    private String media;
    private final String series = "series";
    private final String movies = "movies";
    private RecyclerView recyclerView;
    private AddRecyclerViewAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private AddContent items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar_add);
        searchView = findViewById(R.id.searchview_add);

        items = new AddContent();
        items.removeAllItems();
        recyclerView = findViewById(R.id.recyclerview_add);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AddRecyclerViewAdapter(items);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

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

        recyclerView.addOnItemTouchListener(new AddRecyclerTouchListener(AddActivity.this, new AddRecyclerTouchListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                showDetails(items.getItem(position));
                //Toast.makeText(AddActivity.this, position+ " is selected successfully", Toast.LENGTH_SHORT).show();
                //Toast.makeText(AddActivity.this, items.getItem(position) + " is selected successfully", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    // TODO: Leaves list empty if same results are shown
    private boolean onQueryText(String query) {
        items.removeAllItems();
        adapter.notifyDataSetChanged();
        AddContent.AddItem item = null;

        if(query.equals("")) {
            return false;
        }
        // Download info from query
        if(media.equals(series)) {
            SeriesTask seriesTask = new SeriesTask();
            try {
                List<SeriesResultsPage> resultsPageList = seriesTask.execute(query).get();

                if((resultsPageList !=null) && (!resultsPageList.isEmpty())) {
                    Toast.makeText(this, "Resultspage: " + resultsPageList.get(0).getTitle(), Toast.LENGTH_LONG).show();

                    for (SeriesResultsPage resultsPage: resultsPageList) {
                        item = new AddContent.AddItem(String.valueOf(resultsPage.getId()), resultsPage.getTitle(), resultsPage.getOverview());
                        items.addItem(item);
                    }

                    adapter.addItems(items);
                    adapter.notifyDataSetChanged();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return false;
            }
        } else if(media.equals( movies)) {
            MovieTask movieTask = new MovieTask();
            try {
                List<MovieResultsPage> resultsPageList = movieTask.execute(query).get();

                if((resultsPageList !=null) && (!resultsPageList.isEmpty())) {
                    Toast.makeText(this, "Resultspage: " + resultsPageList.get(0).getTitle(), Toast.LENGTH_LONG).show();

                    for (MovieResultsPage resultsPage: resultsPageList) {
                        item = new AddContent.AddItem(String.valueOf(resultsPage.getId()), resultsPage.getTitle(), resultsPage.getOverview());
                        items.addItem(item);
                    }

                    adapter.addItems(items);
                    adapter.notifyDataSetChanged();
                }
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
                return false;
            }
        }

        return true;
    }

    public void showDetails(AddContent.AddItem item) {
        Intent intent = new Intent(this, DetailActivity.class);

        switch (media) {
            case movies:
                MovieContent.MovieItem movieItem = new MovieContent.MovieItem(item.getId(), item.getTitle(), item.getOverview());
                intent.putExtra(media, movieItem);
                break;
            case series:
                SeriesContent.SeriesItem seriesItem = new SeriesContent.SeriesItem(item.getId(), item.getTitle(), item.getOverview());
                intent.putExtra(media, seriesItem);
                break;
            default:
                System.out.println("Error: Undefined media.");
                return;
        }

        startActivity(intent);
    }
}
package hess.fabian.filmverwaltung.addActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.addActivity.Images.MovieImageTask;
import hess.fabian.filmverwaltung.addActivity.Images.SeriesImageTask;
import hess.fabian.filmverwaltung.detailActivity.DetailActivity;
import hess.fabian.filmverwaltung.mainActivity.movies.MovieContent;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesContent;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.SeriesResultsPage;

public class AddActivity extends AppCompatActivity {

    private String media;
    private final String series = "series";
    private final String movies = "movies";
    private AddRecyclerViewAdapter adapter;
    private List<MovieResultsPage> movieResultsPageList;
    private List<SeriesResultsPage> seriesResultsPageList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        Toolbar toolbar = findViewById(R.id.toolbar_add);
        SearchView searchView = findViewById(R.id.searchview_add);




        RecyclerView recyclerView = findViewById(R.id.recyclerview_add);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        Intent intent = getIntent();
        media = intent.getStringExtra("media");

        switch (media) {
            case movies:
                movieResultsPageList = new ArrayList<>();
                movieResultsPageList.clear();
                seriesResultsPageList = null;
                toolbar.setTitle("Neuen Film hinzufügen:");
                setSupportActionBar(toolbar);
                break;
            case series:
                seriesResultsPageList = new ArrayList<>();
                seriesResultsPageList.clear();
                movieResultsPageList = null;
                toolbar.setTitle("Neue Serie hinzufügen:");
                setSupportActionBar(toolbar);
                break;
            default:
                System.out.println("Error: False intent extra.");
                return;
        }

        adapter = new AddRecyclerViewAdapter(movieResultsPageList, seriesResultsPageList);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


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
                if (media.equals(movies)) {
                    showDetails(movieResultsPageList.get(position), null);
                }
                else if (media.equals(series)) {
                    showDetails(null, seriesResultsPageList.get(position));
                }
                //Toast.makeText(AddActivity.this, position+ " is selected successfully", Toast.LENGTH_SHORT).show();
                //Toast.makeText(AddActivity.this, items.getItem(position) + " is selected successfully", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private boolean onQueryText(String query) {
        if(query.equals("")) {
            return false;
        }
        // Download info from query
        // TODO: Improve speed or async download
        switch (media) {
            case movies:
                movieResultsPageList.clear();
                adapter.notifyDataSetChanged();
                MovieTask movieTask = new MovieTask(adapter);
                try {
                    movieResultsPageList = movieTask.execute(query).get();

                    for(int index = 0; index < movieResultsPageList.size(); index++) {
                        MovieImageTask movieImageTask = new MovieImageTask(adapter);
                        movieImageTask.execute(movieResultsPageList.get(index));
                    }
                    return true;
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return false;
                }
            case series:
                seriesResultsPageList.clear();
                adapter.notifyDataSetChanged();
                SeriesTask seriesTask = new SeriesTask(adapter);

                try {
                    seriesResultsPageList = seriesTask.execute(query).get();

                    for(int index = 0; index < seriesResultsPageList.size(); index++) {
                        SeriesImageTask seriesImageTask = new SeriesImageTask(adapter);
                        seriesImageTask.execute(seriesResultsPageList.get(index));
                    }
                    return true;
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                    return false;
                }
            default:
                    return false;
        }
    }

    public void showDetails(MovieResultsPage movieItem, SeriesResultsPage seriesItem) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();

        switch (media) {
            case movies:
                bundle.putParcelable(media, movieItem);
                intent.putExtras(bundle);
                startActivity(intent);
                return;
            case series:
                bundle.putParcelable(media, seriesItem);
                intent.putExtras(bundle);
                startActivity(intent);
                return;
            default:
                return;
        }
    }
}
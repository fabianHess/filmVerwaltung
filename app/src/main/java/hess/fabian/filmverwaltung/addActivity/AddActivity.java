package hess.fabian.filmverwaltung.addActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.SearchView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.addActivity.Images.ImageTask;
import hess.fabian.filmverwaltung.detailActivity.DetailActivity;
import hess.fabian.filmverwaltung.tmdbApi.ResultsPage;

public class AddActivity extends AppCompatActivity {

    private String media;
    private final String series = "series";
    private final String movies = "movies";
    private AddRecyclerViewAdapter adapter;
    private List<ResultsPage> resultsPageList;

    private final int ADDCONTENT = 1;

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
        resultsPageList = new ArrayList<>();
        resultsPageList.clear();

        switch (media) {
            case movies:
                toolbar.setTitle("Neuen Film hinzufügen:");
                setSupportActionBar(toolbar);
                break;
            case series:
                toolbar.setTitle("Neue Serie hinzufügen:");
                setSupportActionBar(toolbar);
                break;
            default:
                System.out.println("Error: False intent extra.");
                return;
        }

        adapter = new AddRecyclerViewAdapter(resultsPageList);
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
                showDetails(resultsPageList.get(position));
                //Toast.makeText(AddActivity.this, position+ " is selected successfully", Toast.LENGTH_SHORT).show();
                //Toast.makeText(AddActivity.this, items.getItem(position) + " is selected successfully", Toast.LENGTH_SHORT).show();
            }
        }));
    }

    private boolean onQueryText(String query) {
        // Download info from query
        // TODO: Improve speed or async download
        resultsPageList.clear();
        adapter.notifyDataSetChanged();

        if(query.equals("")) {
            return false;
        }

        DownloadTask downloadTask = new DownloadTask(adapter, media);
        try {
            resultsPageList = downloadTask.execute(query).get();

            for(int index = 0; index < resultsPageList.size(); index++) {
                ImageTask movieImageTask = new ImageTask(adapter);
                ResultsPage resultsPage = resultsPageList.get(index);
                movieImageTask.execute(resultsPage);
            }
            return true;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showDetails(ResultsPage item) {
        Intent intent = new Intent(this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(media, item);
        intent.putExtras(bundle);
        startActivityForResult(intent, ADDCONTENT);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && data != null) {
            setResult(ADDCONTENT, data);
            finish();
        }
    }
}
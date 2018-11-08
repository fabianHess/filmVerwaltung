package hess.fabian.filmverwaltung.mainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.Serializable;

import hess.fabian.filmverwaltung.addActivity.AddActivity;
import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.detailActivity.DetailActivity;
import hess.fabian.filmverwaltung.mainActivity.movies.MovieContent;
import hess.fabian.filmverwaltung.mainActivity.movies.MoviesFragment;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesContent;
import hess.fabian.filmverwaltung.mainActivity.series.SeriesFragment;


/**
 * Created by Fabian on 01.03.2018.
 */

public class MainActivity extends AppCompatActivity implements MoviesFragment.OnListFragmentInteractionListener,
                                                               SeriesFragment.OnListFragmentInteractionListener {
    private TabLayout tabLayout;
    private ViewPager viewPager = null;

    private final String movies = "movies";
    private final String series = "series";
    private String media = movies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Filme")); //R.string.movies_page_title
        tabLayout.addTab(tabLayout.newTab().setText("Serien")); //R.string.series_page_title
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        initMovies();
        initSeries();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                if(tab.getText() == "Filme") {
                    media = movies;
                }
                if(tab.getText() == "Serien") {
                    media = series;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    // Only for testing purpose
    // Please replace this with loading function
    public void initMovies() {
        MovieContent movieContent = new MovieContent();
        MovieContent.MovieItem item = new MovieContent.MovieItem("1", "Film1", "Some text for the detail activity.");

        movieContent.addItem(item);

        MovieContent.MovieItem item2 = new MovieContent.MovieItem("2", "Film2", "Some text for the detail activity.");

        movieContent.addItem(item2);
    }

    public void initSeries() {
        SeriesContent seriesContent = new SeriesContent();
        SeriesContent.SeriesItem item = new SeriesContent.SeriesItem("1", "Serie1", "Some text for the detail activity.");

        seriesContent.addItem(item);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if(id == R.id.add_settings) {
            Intent intent = new Intent(this, AddActivity.class);
            intent.putExtra("media", this.media);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onListFragmentInteraction(MovieContent.MovieItem item) {
        Toast toast = Toast.makeText(this, "Item clicked: " + item.title, Toast.LENGTH_SHORT);
        toast.show();

        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("movies", (Serializable) item);
        startActivity(intent);


    }

    @Override
    public void onListFragmentInteraction(SeriesContent.SeriesItem item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("series", item);
        startActivity(intent);
    }
}
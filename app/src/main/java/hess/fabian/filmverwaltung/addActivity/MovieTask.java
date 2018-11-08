package hess.fabian.filmverwaltung.addActivity;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.TmdbSearch;

/**
 * Created by Fabian on 05.03.2018.
 */

public class MovieTask extends AsyncTask<String, Integer, List<MovieResultsPage>>{

    private TmdbSearch tmdbSearch;
    private MovieResultsPage movieResultsPage;
    private ProgressBar progressBar;
    private String movieName;
    private List<MovieResultsPage> movieResultsPageList;

    protected void onPreExecuted() {
    }

    @Override
    protected List<MovieResultsPage> doInBackground(String[] query) {
        movieResultsPageList = new ArrayList<>();
        tmdbSearch = new TmdbSearch();
        movieName = query[0];

        movieResultsPageList = tmdbSearch.searchMovie(movieName);

        if((null != movieResultsPageList) && (!movieResultsPageList.isEmpty())) {
            return movieResultsPageList;
        } else {
            return null;
        }
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(List<MovieResultsPage> movieResultsPageList) {
        super.onPostExecute(movieResultsPageList);
    }
}

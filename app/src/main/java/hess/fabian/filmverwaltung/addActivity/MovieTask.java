package hess.fabian.filmverwaltung.addActivity;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.TmdbSearch;

/**
 * Created by Fabian on 05.03.2018.
 */

public class MovieTask extends AsyncTask<String, Integer, MovieResultsPage>{

    private TmdbSearch tmdbSearch;
    private MovieResultsPage movieResultsPage;
    private ProgressBar progressBar;
    private String movieName;

    protected void onPreExecuted() {
    }

    @Override
    protected MovieResultsPage doInBackground(String[] query) {
        tmdbSearch = new TmdbSearch();
        movieName = query[0];

        movieResultsPage = tmdbSearch.searchMovie(movieName);

        if(movieResultsPage != null) {
            return movieResultsPage;
        } else {
            return null;
        }
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(MovieResultsPage movieResultsPage) {
        super.onPostExecute(movieResultsPage);
    }
}

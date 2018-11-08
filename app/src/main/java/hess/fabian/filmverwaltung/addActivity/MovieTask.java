package hess.fabian.filmverwaltung.addActivity;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.TmdbSearch;

/**
 * Created by Fabian on 05.03.2018.
 */
public class MovieTask extends AsyncTask<String, Integer, List<MovieResultsPage>>{

    private AddRecyclerViewAdapter adapter;
    private TmdbSearch tmdbSearch;
    private List<MovieResultsPage> movieResultsPageList;

    public MovieTask(AddRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        movieResultsPageList = new ArrayList<>();
        tmdbSearch = new TmdbSearch();
    }

    @Override
    protected List<MovieResultsPage> doInBackground(String[] query) {
        String movieName = query[0];
        movieResultsPageList = tmdbSearch.searchMovie(movieName);

        if((null != movieResultsPageList) && (!movieResultsPageList.isEmpty())) {
            return movieResultsPageList;
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(List<MovieResultsPage> movieResultsPageList) {
        super.onPostExecute(movieResultsPageList);
        adapter.addMovieItem(movieResultsPageList);
        adapter.notifyDataSetChanged();
    }
}
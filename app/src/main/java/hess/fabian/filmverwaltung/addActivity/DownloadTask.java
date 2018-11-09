package hess.fabian.filmverwaltung.addActivity;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

import hess.fabian.filmverwaltung.tmdbApi.ResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.TmdbSearch;

/**
 * Created by Fabian on 05.03.2018.
 */
public class DownloadTask extends AsyncTask<String, Integer, List<ResultsPage>>{

    private AddRecyclerViewAdapter adapter;
    private TmdbSearch tmdbSearch;
    private List<ResultsPage> resultsPageList;
    private String media;

    private static final String movies = "movies";
    private static final String series = "series";

    public DownloadTask(AddRecyclerViewAdapter adapter, String mediaType) {
        this.adapter = adapter;
        resultsPageList = new ArrayList<>();
        tmdbSearch = new TmdbSearch();
        media = mediaType;
    }

    @Override
    protected List<ResultsPage> doInBackground(String[] query) {
        String movieName = query[0];
        switch (media) {
            case movies:
                resultsPageList = tmdbSearch.searchMovie(movieName);
                break;
            case series:
                resultsPageList = tmdbSearch.searchSeries(movieName);
                break;
            default:
                return null;
        }

        if((null != resultsPageList) && (!resultsPageList.isEmpty())) {
            return resultsPageList;
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(List<ResultsPage> rsultsPageList) {
        super.onPostExecute(resultsPageList);
        adapter.addItem(resultsPageList);
        adapter.notifyDataSetChanged();
    }
}
package hess.fabian.filmverwaltung.addActivity;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;

import hess.fabian.filmverwaltung.tmdbApi.SeriesResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.TmdbSearch;

public class SeriesTask extends AsyncTask<String, Integer, List<SeriesResultsPage>> {

    private TmdbSearch tmdbSearch;
    private SeriesResultsPage seriesResultsPage;
    private ProgressBar progressBar;
    private String seriesName;
    private List<SeriesResultsPage> seriesResultsPageList;

    protected void onPreExecuted() {
    }

    @Override
    protected List<SeriesResultsPage> doInBackground(String[] query) {
        seriesResultsPageList = new ArrayList<>();
        tmdbSearch = new TmdbSearch();
        seriesName = query[0];

        seriesResultsPageList = tmdbSearch.searchSeries(seriesName);

        if((null != seriesResultsPageList) && (!seriesResultsPageList.isEmpty())) {
            return seriesResultsPageList;
        } else {
            return null;
        }
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(List<SeriesResultsPage> movieResultsPageList) {
        super.onPostExecute(seriesResultsPageList);
    }
}

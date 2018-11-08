package hess.fabian.filmverwaltung.addActivity;

import android.os.AsyncTask;
import java.util.ArrayList;
import java.util.List;

import hess.fabian.filmverwaltung.tmdbApi.SeriesResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.TmdbSearch;

/**
 * Created by Fabian on 08.11.2018.
 */
public class SeriesTask extends AsyncTask<String, Integer, List<SeriesResultsPage>> {

    private AddRecyclerViewAdapter adapter;
    private TmdbSearch tmdbSearch;
    private List<SeriesResultsPage> seriesResultsPageList;

    public SeriesTask(AddRecyclerViewAdapter adapter) {
        this.adapter = adapter;
        seriesResultsPageList = new ArrayList<>();
        tmdbSearch = new TmdbSearch();
    }

    @Override
    protected List<SeriesResultsPage> doInBackground(String[] query) {
        String seriesName = query[0];
        seriesResultsPageList = tmdbSearch.searchSeries(seriesName);

        if((null != seriesResultsPageList) && (!seriesResultsPageList.isEmpty())) {
            return seriesResultsPageList;
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(List<SeriesResultsPage> seriesResultsPageList) {
        super.onPostExecute(seriesResultsPageList);
        adapter.addSeriesItem(seriesResultsPageList);
        adapter.notifyDataSetChanged();
    }
}
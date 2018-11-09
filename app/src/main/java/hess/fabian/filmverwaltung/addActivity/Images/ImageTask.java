package hess.fabian.filmverwaltung.addActivity.Images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import hess.fabian.filmverwaltung.addActivity.AddRecyclerViewAdapter;
import hess.fabian.filmverwaltung.tmdbApi.ResultsPage;


// Images
// https://image.tmdb.org/t/p/w500/<poster_path>
    /* https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400
    "backdrop_sizes": ["w300", "w780", "w1280", "original"],
    "poster_sizes": ["w92", "w154", "w185", "w342", "w500", "w780", "original"],
    */
public class ImageTask extends AsyncTask<ResultsPage, Integer, ResultsPage> {

    private static final String TMDB_POSTER_BASIC = "https://image.tmdb.org/t/p/";
    private static final String TMDB_POSTER_WIDTH = "w154";

    private AddRecyclerViewAdapter adapter;


    public ImageTask(AddRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected ResultsPage doInBackground(ResultsPage[] resultsPages) {
        ResultsPage resultsPage = resultsPages[0];

        String imageUrl = TMDB_POSTER_BASIC + TMDB_POSTER_WIDTH + resultsPage.getPoster_path();

        try {
            InputStream inputStream = (InputStream) new URL(imageUrl).getContent();
            Bitmap poster = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

            if (poster != null) {
                resultsPage.setPoster(poster);
                return resultsPage;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(ResultsPage resultsPage) {
        super.onPostExecute(resultsPage);
        adapter.notifyDataSetChanged();
    }
}
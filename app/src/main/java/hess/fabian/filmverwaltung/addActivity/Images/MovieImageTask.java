package hess.fabian.filmverwaltung.addActivity.Images;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import hess.fabian.filmverwaltung.addActivity.AddRecyclerViewAdapter;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;


// Images
// https://image.tmdb.org/t/p/w500/<poster_path>
    /* https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400
    "backdrop_sizes": ["w300", "w780", "w1280", "original"],
    "poster_sizes": ["w92", "w154", "w185", "w342", "w500", "w780", "original"],
    */
public class MovieImageTask extends AsyncTask<MovieResultsPage, Integer, Bitmap> {

    private static final String TMDB_POSTER_BASIC = "https://image.tmdb.org/t/p/";
    private static final String TMDB_POSTER_WIDTH = "w154";

    private MovieResultsPage item;
    private AddRecyclerViewAdapter adapter;


    public MovieImageTask(AddRecyclerViewAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    protected Bitmap doInBackground(MovieResultsPage[] items) {
        item = items[0];

        String imageUrl = TMDB_POSTER_BASIC + TMDB_POSTER_WIDTH + item.getPoster_path();

        try {
            InputStream inputStream = (InputStream) new URL(imageUrl).getContent();
            Bitmap poster = BitmapFactory.decodeStream(inputStream);
            inputStream.close();

            if (poster != null) {
                return poster;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected void onProgressUpdate(Integer... progress) {
    }

    @Override
    protected void onPostExecute(Bitmap poster) {
        super.onPostExecute(poster);
        item.setPoster(poster);
        adapter.notifyDataSetChanged();
    }
}
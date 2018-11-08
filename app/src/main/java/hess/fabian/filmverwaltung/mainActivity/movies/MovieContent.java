package hess.fabian.filmverwaltung.mainActivity.movies;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabian on 03.03.2018.
 */

public class MovieContent {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<MovieContent.MovieItem> ITEMS = new ArrayList<MovieItem>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, MovieContent.MovieItem> ITEM_MAP = new HashMap<String, MovieItem>();

    public static void addItem(MovieItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }


    public static class MovieItem implements Serializable {
        public final String id;
        public final String title;
        //public final Image poster;
        public final String overview;

        public MovieItem(String id, String title, /*Image poster*/ String overview) {
            this.id = id;
            this.title = title;
            //this.poster = poster;
            this.overview = overview;
        }

        @Override
        public String toString() {
            return title;
        }
    }
}

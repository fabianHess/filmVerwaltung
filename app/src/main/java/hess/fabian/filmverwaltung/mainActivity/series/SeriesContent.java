package hess.fabian.filmverwaltung.mainActivity.series;

import android.media.Image;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabian on 03.03.2018.
 */

public class SeriesContent {
    public static final List<SeriesContent.SeriesItem> ITEMS = new ArrayList<>();
    public static final Map<String, SeriesContent.SeriesItem> ITEM_MAP = new HashMap<>();

    public static void addItem(SeriesItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    public static class SeriesItem implements Serializable {
        public final String id;
        public final  String title;
        //public final Image poster;
        public final String overview;

        public SeriesItem(String id, String title/*, Image poster*/, String overview) {
            this.id = id;
            this.title = title;
            //this.poster = poster;
            this.overview = overview;
        }
    }
}
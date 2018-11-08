package hess.fabian.filmverwaltung.addActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Fabian on 03.03.2018.
 */

public class AddContent {
    public static final List<AddContent.AddItem> ITEMS = new ArrayList<>();
    public static final Map<String, AddContent.AddItem> ITEM_MAP = new HashMap<>();

    public static void addItem(AddItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    public int getLength() {
        return ITEMS.size();
    }

    public AddItem getItem(int position) {
        AddContent.AddItem item = ITEMS.get(position);

        return item;
    }

    public void removeItem(int position) {
        ITEM_MAP.remove(ITEMS.get(position));
        ITEMS.remove(position);
    }

    public void removeAllItems() {
        ITEM_MAP.clear();
        ITEMS.clear();
    }


    public static class AddItem implements Serializable {
        private final String id;
        private final  String title;
        //public final Image poster;
        private final String overview;

        public AddItem(String id, String title/*, Image poster*/, String overview) {
            this.id = id;
            this.title = title;
            //this.poster = poster;
            this.overview = overview;
        }

        @Override
        public String toString() { return getTitle(); }

        public String getId() { return id; }

        public String getTitle() { return title; }

        public String getOverview() { return overview; }
    }
}
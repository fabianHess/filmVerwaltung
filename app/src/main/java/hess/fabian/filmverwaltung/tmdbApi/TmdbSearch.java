package hess.fabian.filmverwaltung.tmdbApi;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fabian on 04.03.2018.
 */

public class TmdbSearch {

    private static final String TMDB_BASIC = "https://api.themoviedb.org/3/search/";
    private static final String TMDB_MOVIES = "movie?";
    private static final String TMDB_SERIES = "tv?";
    private static final String TMDB_LANGUAGE_DE = "&language=de";
    private static final String TMDB_LANGUAGE_EN_US = "&language=en-us";
    private static final String TMDB_API_KEY = "&api_key=5acd7d46d1ddb6fd586c056da65c86cc";
    private static final String TMDB_PAGE = "&page=";
    private String tmdb_page = "1"; // About 20 results per page (if you need more?)
    private static final String TMDB_QUERY = "&query=";
    //private String query;   // searched item

    // https://api.themoviedb.org/3/search/movie?api_key={api_key}&query=Jack+Reacher

    // Images
    // https://image.tmdb.org/t/p/w500/<poster_path>
    /* https://www.themoviedb.org/talk/53c11d4ec3a3684cf4006400
    "backdrop_sizes": ["w300", "w780", "w1280", "original"],
    "poster_sizes": ["w92", "w154", "w185", "w342", "w500", "w780", "original"],
    */





    public TmdbSearch(){
    }

    public List<MovieResultsPage> searchMovie(String query) {
        String searchURL = TMDB_BASIC + TMDB_MOVIES + TMDB_API_KEY + TMDB_QUERY + query + TMDB_LANGUAGE_DE;

        try {
            JsonReader jsonReader = new JsonReader(searchURL);
            JSONObject jsonObject = jsonReader.getJsonObject();
            List<MovieResultsPage> movieResultsPageList = new ArrayList<>();

            if (jsonObject != null) {
                JSONArray results = (JSONArray) jsonObject.get("results");

                for (int index = 0; index < results.length(); index++) {
                    JSONObject resultsJSONObject = results.getJSONObject(index);
                    MovieResultsPage movieResultsPage = new MovieResultsPage(resultsJSONObject);
                    movieResultsPageList.add(movieResultsPage);
                }
                return movieResultsPageList;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<SeriesResultsPage> searchSeries(String query) {
        String searchURL = TMDB_BASIC + TMDB_SERIES + TMDB_API_KEY + TMDB_QUERY + query + TMDB_LANGUAGE_DE;

        try {
            JsonReader jsonReader = new JsonReader(searchURL);
            JSONObject jsonObject = jsonReader.getJsonObject();
            List<SeriesResultsPage> seriesResultsPageList = new ArrayList<>();

            if (jsonObject != null) {
                JSONArray results = (JSONArray) jsonObject.get("results");

                for (int index = 0; index < results.length(); index++) {
                    JSONObject resultsJSONObject = results.getJSONObject(index);
                    SeriesResultsPage seriesResultsPage = new SeriesResultsPage(resultsJSONObject);
                    seriesResultsPageList.add(seriesResultsPage);
                }
                return seriesResultsPageList;
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
package hess.fabian.filmverwaltung.tmdbApi;

import android.graphics.Movie;

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
    public TmdbSearch(){

    }

    public List<MovieResultsPage> searchMovie(String query) {
        String searchURL = TMDB_BASIC + TMDB_MOVIES + TMDB_API_KEY + TMDB_QUERY + query + TMDB_LANGUAGE_DE;
        JsonReader jsonReader = null;

        try {
            jsonReader = new JsonReader(searchURL);
            JSONObject jsonObject = jsonReader.getJsonObject();
            List<MovieResultsPage> movieResultsPageList = new ArrayList<>();

            for(int index = 0; index < jsonObject.length(); index++) {
                MovieResultsPage movieResultsPage = new MovieResultsPage(jsonObject, index);
                movieResultsPageList.add(movieResultsPage);
            }

            return movieResultsPageList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<SeriesResultsPage> searchSeries(String query){
        String searchURL = TMDB_BASIC + TMDB_SERIES + TMDB_API_KEY + TMDB_QUERY + query + TMDB_LANGUAGE_DE;
        JsonReader jsonReader = null;

        try {
            jsonReader = new JsonReader(searchURL);
            JSONObject jsonObject = jsonReader.getJsonObject();
            List<SeriesResultsPage> seriesResultsPageList = new ArrayList<>();
            int resultsLength = ((JSONArray) jsonObject.get("results")).length();

            for(int index = 0; index < resultsLength; index++) {
                SeriesResultsPage seriesResultsPage = new SeriesResultsPage(jsonObject, index);
                seriesResultsPageList.add(seriesResultsPage);
            }

            return seriesResultsPageList;
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}

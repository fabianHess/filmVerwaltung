package hess.fabian.filmverwaltung.tmdbApi;

/**
 * Created by Fabian on 04.03.2018.
 */

public class TmdbSearch {

    private static final String TMDB_BASIC = "https://api.themoviedb.org/3/search";
    private static final String TMDB_MOVIES = "movie/";
    private static final String TMDB_SERIES = "tv/";
    private static final String TMDB_LANGUAGE_DE = "?language=de";
    private static final String TMDB_LANGUAGE_EN_US = "?language=en-us";
    private static final String TMDB_API_KEY = "&api_key=5acd7d46d1ddb6fd586c056da65c86cc";
    private static final String TMDB_PAGE = "&page=";
    private String tmdb_page = "1"; // About 20 results per page (if you need more?)
    private static final String TMDB_QUERY = "&query=";
    //private String query;   // searched item

    public MovieResultPage searchMovie(String query){
        String searchURL = TMDB_BASIC + TMDB_MOVIES + TMDB_API_KEY + TMDB_QUERY + query;
    }

    public TvResultPage searchMovie(String query){
        String searchURL = TMDB_BASIC + TMDB_SERIES + TMDB_API_KEY + TMDB_QUERY + query;

    }
}

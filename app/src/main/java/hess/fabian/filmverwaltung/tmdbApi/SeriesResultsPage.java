package hess.fabian.filmverwaltung.tmdbApi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SeriesResultsPage {


    private String original_name;
    private int id;
    private String title;
    private int vote_count;
    private double vote_average;
    private String poster_path;
    private String first_air_date;
    private double popularity;
    private int[] genre_ids;
    private String original_language;
    private String backdrop_path;
    private String overview;

    public SeriesResultsPage(JSONObject jsonObject, int resultNo) throws JSONException {
        if(jsonObject != null) {
            JSONArray results = (JSONArray) jsonObject.get("results");
            JSONObject object = results.getJSONObject(resultNo);
            extractValues(object);
        }
    }

    private void extractValues(JSONObject jsonObject) {
        original_name       = jsonObject.optString("original_name");
        id                  = jsonObject.optInt("id");
        title               = jsonObject.optString("name");
        vote_count          = jsonObject.optInt("vote_count");
        vote_average        = jsonObject.optInt("vote_average");
        poster_path         = jsonObject.optString("poster_path");
        first_air_date      = jsonObject.optString("first_air_date");
        popularity          = jsonObject.optDouble("popularity");
        //genre_ids           = jsonObject.opt("genre_ids");
        original_language   = jsonObject.optString("original_language");
        backdrop_path       = jsonObject.optString("backdrop_path");
        overview            = jsonObject.optString("overview");
    }



    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

}

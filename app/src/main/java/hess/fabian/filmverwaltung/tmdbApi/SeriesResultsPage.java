package hess.fabian.filmverwaltung.tmdbApi;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by Fabian on 08.11.2018
 */
public class SeriesResultsPage implements Parcelable {

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
    private Bitmap poster;
    private Bitmap backdrop;

    public SeriesResultsPage(JSONObject jsonObject) {
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

    protected SeriesResultsPage(Parcel in) {
        original_name = in.readString();
        id = in.readInt();
        title = in.readString();
        vote_count = in.readInt();
        vote_average = in.readDouble();
        poster_path = in.readString();
        first_air_date = in.readString();
        popularity = in.readDouble();
        genre_ids = in.createIntArray();
        original_language = in.readString();
        backdrop_path = in.readString();
        overview = in.readString();
        poster = in.readParcelable(Bitmap.class.getClassLoader());
        backdrop = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<SeriesResultsPage> CREATOR = new Creator<SeriesResultsPage>() {
        @Override
        public SeriesResultsPage createFromParcel(Parcel in) {
            return new SeriesResultsPage(in);
        }

        @Override
        public SeriesResultsPage[] newArray(int size) {
            return new SeriesResultsPage[size];
        }
    };

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

    public Bitmap getPoster() {
        return poster;
    }

    public void setPoster(Bitmap poster) {
        this.poster = poster;
    }

    public Bitmap getBackdrop() {
        return backdrop;
    }

    public void setBackdrop(Bitmap backdrop) {
        this.backdrop = backdrop;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(original_name);
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeInt(vote_count);
        parcel.writeDouble(vote_average);
        parcel.writeString(poster_path);
        parcel.writeString(first_air_date);
        parcel.writeDouble(popularity);
        parcel.writeIntArray(genre_ids);
        parcel.writeString(original_language);
        parcel.writeString(backdrop_path);
        parcel.writeString(overview);
        parcel.writeParcelable(poster, i);
        parcel.writeParcelable(backdrop, i);
    }
}
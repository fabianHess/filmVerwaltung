package hess.fabian.filmverwaltung.tmdbApi;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import org.json.JSONObject;

/**
 * Created by Fabian on 04.03.2018.
 */

public class MovieResultsPage implements Parcelable {

    private int vote_count;
    private int id;
    private boolean video;
    private double vote_average;
    private String title;
    private double popularity;
    private String poster_path;
    private String original_language;
    private String original_title;
    //genre_ids: 0	12; 1	14; 2	10751;
    private String backdrop_path;
    private boolean adult;
    private String overview;
    private String release_date;
    private Bitmap poster;
    private Bitmap backdrop;

    public MovieResultsPage(JSONObject jsonObject) {
        vote_count          = jsonObject.optInt("vote_count");
        id                  = jsonObject.optInt("id");
        video               = jsonObject.optBoolean("video");
        vote_average        = jsonObject.optDouble("vote_average");
        title               = jsonObject.optString("title");
        popularity          = jsonObject.optDouble("popularity");
        poster_path         = jsonObject.optString("poster_path");
        original_language   = jsonObject.optString("original_language");
        original_title      = jsonObject.optString("original_title");
        backdrop_path       = jsonObject.optString("backdrop_path");
        adult               = jsonObject.optBoolean("adult");
        overview            = jsonObject.optString("overview");
        release_date        = jsonObject.optString("release_date");
    }

    protected MovieResultsPage(Parcel in) {
        vote_count = in.readInt();
        id = in.readInt();
        video = in.readByte() != 0;
        vote_average = in.readDouble();
        title = in.readString();
        popularity = in.readDouble();
        poster_path = in.readString();
        original_language = in.readString();
        original_title = in.readString();
        backdrop_path = in.readString();
        adult = in.readByte() != 0;
        overview = in.readString();
        release_date = in.readString();
        poster = in.readParcelable(Bitmap.class.getClassLoader());
        backdrop = in.readParcelable(Bitmap.class.getClassLoader());
    }

    public static final Creator<MovieResultsPage> CREATOR = new Creator<MovieResultsPage>() {
        @Override
        public MovieResultsPage createFromParcel(Parcel in) {
            return new MovieResultsPage(in);
        }

        @Override
        public MovieResultsPage[] newArray(int size) {
            return new MovieResultsPage[size];
        }
    };

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public double getVote_average() {
        return vote_average;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
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
        parcel.writeInt(vote_count);
        parcel.writeInt(id);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeDouble(vote_average);
        parcel.writeString(title);
        parcel.writeDouble(popularity);
        parcel.writeString(poster_path);
        parcel.writeString(original_language);
        parcel.writeString(original_title);
        parcel.writeString(backdrop_path);
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeParcelable(poster, i);
        parcel.writeParcelable(backdrop, i);
    }
}
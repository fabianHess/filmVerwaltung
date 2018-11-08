package hess.fabian.filmverwaltung.addActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.tmdbApi.MovieResultsPage;
import hess.fabian.filmverwaltung.tmdbApi.SeriesResultsPage;

public class AddRecyclerViewAdapter extends RecyclerView.Adapter<AddRecyclerViewAdapter.ViewHolder> {

    private List<MovieResultsPage> movieResultsPageList;
    private List<SeriesResultsPage> seriesResultsPageList;


    public AddRecyclerViewAdapter (List<MovieResultsPage> movieItems, List<SeriesResultsPage> seriesItems) {
        movieResultsPageList = movieItems;
        seriesResultsPageList = seriesItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_add, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (null != movieResultsPageList) {
            MovieResultsPage movieResultsPage = movieResultsPageList.get(position);
            holder.mTitleView.setText(movieResultsPage.getTitle());
            holder.mPosterView.setImageBitmap(movieResultsPage.getPoster());
        }
        if (null != seriesResultsPageList) {
            SeriesResultsPage seriesResultsPage = seriesResultsPageList.get(position);
            holder.mTitleView.setText(seriesResultsPage.getTitle());
            holder.mPosterView.setImageBitmap(seriesResultsPage.getPoster());
        }

    }

    public void clear() {
        seriesResultsPageList.clear();
        movieResultsPageList.clear();
    }

    @Override
    public int getItemCount() {
        if (null != movieResultsPageList) {
            return movieResultsPageList.size();
        }
        else if (null != seriesResultsPageList) {
            return seriesResultsPageList.size();
        }
        else {
            return -1;
        }
    }

    public void addMovieItem(List<MovieResultsPage> items) {
        movieResultsPageList = items;
    }

    public void addSeriesItem(List<SeriesResultsPage> items) {
        seriesResultsPageList = items;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final View mView;
        private final TextView mTitleView;
        private ImageView mPosterView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mTitleView = view.findViewById(R.id.title_add);
            mPosterView = view.findViewById(R.id.poster_add);
        }
    }
}
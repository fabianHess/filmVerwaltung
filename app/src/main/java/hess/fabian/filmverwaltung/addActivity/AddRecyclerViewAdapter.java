package hess.fabian.filmverwaltung.addActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import hess.fabian.filmverwaltung.R;
import hess.fabian.filmverwaltung.tmdbApi.ResultsPage;

public class AddRecyclerViewAdapter extends RecyclerView.Adapter<AddRecyclerViewAdapter.ViewHolder> {

    private List<ResultsPage> resultsPageList;


    public AddRecyclerViewAdapter (List<ResultsPage> items) {
        resultsPageList = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_add, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (null != resultsPageList) {
            ResultsPage resultsPage = resultsPageList.get(position);
            holder.mTitleView.setText(resultsPage.getTitle());
            holder.mPosterView.setImageBitmap(resultsPage.getPoster());
        }
    }

    public void clear() {
        resultsPageList.clear();
    }

    @Override
    public int getItemCount() {
        if (null != resultsPageList) {
            return resultsPageList.size();
        }
        return 0;
    }

    public void addItem(List<ResultsPage> items) {
        resultsPageList = items;
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
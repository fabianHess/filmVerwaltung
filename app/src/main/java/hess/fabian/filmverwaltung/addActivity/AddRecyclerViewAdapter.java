package hess.fabian.filmverwaltung.addActivity;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import hess.fabian.filmverwaltung.R;

public class AddRecyclerViewAdapter extends RecyclerView.Adapter<AddRecyclerViewAdapter.ViewHolder> {

    private AddContent items;


    public AddRecyclerViewAdapter (AddContent value){
        this.items = value;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_add, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        System.out.print(items);

        AddContent.AddItem item = items.getItem(position);
        holder.mIdView.setText(item.getTitle());
    }

    public void clear(AddContent resetVal) {
        this.items = resetVal;
    }

    @Override
    public int getItemCount() {
        return items.getLength();
    }

    public void addItems(AddContent item)
    {
        this.items = item;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public TextView mContentView;
        public AddContent.AddItem mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.id_add);
            mContentView = view.findViewById(R.id.content_add);
        }
    }
}
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
        this.items= value;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_add, parent, false);
        return new ViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.textView.setText(items.getItem(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return items.getLength();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.id_add);
        }
    }
}
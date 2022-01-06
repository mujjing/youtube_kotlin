package co.jh.popupsample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.MyViewHolder> {

    private ArrayList<Number> numberList;

    public recyclerAdapter(ArrayList<Number> numberList) {
        this.numberList = numberList;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView label;

        public MyViewHolder(final View view) {
            super(view);
            label = view.findViewById(R.id.label);
        }
    }

    @NonNull
    @Override
    public recyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull recyclerAdapter.MyViewHolder holder, int position) {
        String label = numberList.get(position).getNumber();
        holder.label.setText(label);
    }

    @Override
    public int getItemCount() {
        return numberList.size();
    }
}

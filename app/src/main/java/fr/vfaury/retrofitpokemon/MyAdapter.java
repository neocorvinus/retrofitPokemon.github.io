package fr.vfaury.retrofitpokemon;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Pokemon> values;
    private final OnItemClickListener listener;

    public class ViewHolder extends RecyclerView.ViewHolder{
        // each data item is just a string in this case
        public TextView txtHeader;
        public TextView txtFooter;
        public ImageView img;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            txtHeader = (TextView) v.findViewById(R.id.firstLine);
            txtFooter = (TextView) v.findViewById(R.id.secondLine);
            img = v.findViewById(R.id.icon);
        }
    }

    public void add (int position, Pokemon item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove (int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public MyAdapter(List<Pokemon> values, OnItemClickListener listener){
        this.values = values;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewtype) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v =inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final Pokemon pokemon = values.get(position);
        holder.txtHeader.setText(pokemon.getName());
        holder.txtHeader.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(pokemon);
            }
        });
        holder.txtFooter.setText("Footer: "+ pokemon);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}

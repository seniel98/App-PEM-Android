package com.jdpadron98.biolabapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.CardViewHolder> implements Filterable {
    private ArrayList<CardViewItem> mcardViewItems;
    private ArrayList<CardViewItem> mcardViewItemsfull;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public static class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView cell_linetv, gL50tv;

        public CardViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            cell_linetv = itemView.findViewById(R.id.cell_line_tv);
            gL50tv = itemView.findViewById(R.id.gL50_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public CardViewAdapter(ArrayList<CardViewItem> cardViewItems) {
        mcardViewItems = cardViewItems;
        //Create a copy to use it for filtering
        mcardViewItemsfull = new ArrayList<>(cardViewItems);
    }

    @NonNull
    @Override
    public CardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_table_item, parent, false);
        CardViewHolder cvh = new CardViewHolder(view, mListener);
        return cvh;
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewHolder holder, int position) {
        CardViewItem currentItem = mcardViewItems.get(position);

        holder.cell_linetv.setText(currentItem.getCell_line_tv());
        holder.gL50tv.setText(currentItem.getgL50_tv());
    }

    @Override
    public int getItemCount() {
        return mcardViewItems.size();
    }

    @Override
    public Filter getFilter() {
        return cardFilter;
    }

    private Filter cardFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<CardViewItem> filteredList = new ArrayList<>();

            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(mcardViewItemsfull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (CardViewItem item : mcardViewItemsfull) {
                    if (item.getCell_line_tv().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                mcardViewItems.clear();
                mcardViewItems.addAll((ArrayList)results.values);
                notifyDataSetChanged();
        }
    };
}

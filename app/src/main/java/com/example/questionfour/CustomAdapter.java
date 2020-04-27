package com.example.questionfour;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> implements Filterable {
    private List<String> itemList;
    private List<String> completeList;

    public CustomAdapter(List<String> itemList) {
        this.itemList = itemList;
        completeList = new ArrayList<String>(itemList);
    }

    @Override
    public Filter getFilter() {
        return customFilter;
    }

    private Filter customFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<String> listFilter = new ArrayList<String>();

            if (constraint == null || constraint.length() == 0) {
                listFilter.addAll(completeList);
            } else {
                String pattern = constraint.toString().toLowerCase().trim();
                for (String s : completeList) {
                    if (s.contains((pattern))) {
                        listFilter.add(s);
                    }
                }
            }

            FilterResults filterResults = new FilterResults();
            filterResults.values = listFilter;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            itemList.clear();
            itemList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
        String itemText = itemList.get(position);
        holder.textView.setText(itemText);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    static class CustomViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        CustomViewHolder(View view) {
            super(view);
            textView = view.findViewById(R.id.text_view_1);
        }
    }

    private boolean isPartialPermutation(String firstString, String secondString) {
        char firstCharOne = firstString.charAt(0);
        char firstCharTwo = secondString.charAt(0);
        char lastCharOne = firstString.charAt(firstString.length() - 1);
        char lastCharTwo = secondString.charAt(secondString.length() - 1);

        if (firstCharOne != firstCharTwo) return false;

        if (firstString.length() != secondString.length()) return false;

        if (firstString.length() < 3 && secondString.length() < 3) return false;

        if (firstString.length() == 3 && secondString.length() == 3) {
            if (lastCharOne != lastCharTwo) {
                return true;
            } else {
                return false;
            }
        }

        if (firstString.length() > 3 && secondString.length() > 3) {
            return !firstString.substring(1, firstString.length() - 2).equals(secondString.substring(1, secondString.length() - 2));
        }

        return false;
    }
}

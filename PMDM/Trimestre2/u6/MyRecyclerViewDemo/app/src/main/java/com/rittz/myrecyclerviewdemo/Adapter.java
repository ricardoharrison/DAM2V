package com.rittz.myrecyclerviewdemo;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    public Adapter(ArrayList<EmailItem> items, Context context) {
        this.items = items;
        this.context = context;
    }

    private ArrayList<EmailItem> items;
    Context context;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName, textViewEmail;
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textViewName = (TextView) view.findViewById(R.id.textViewName);
            textViewEmail = (TextView) view.findViewById(R.id.textViewEmail);
            imageView = (ImageView) view.findViewById(R.id.imageView);
        }

    }


    // Create new views (invoked by the layout manager)
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_view, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.textViewName.setText(items.get(position).getName());
        viewHolder.textViewEmail.setText(items.get(position).getEmail());
        viewHolder.imageView.setImageResource(items.get(position).getImg());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return items.size();
    }

    //Other methods that let the adapter interact with buttons, etc.
}

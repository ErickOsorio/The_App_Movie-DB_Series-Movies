/*
 * Developed by Erick Osorio Sánchez
 * Last modified 5/12/19 2:21 PM.
 * Copyright (c) 2019. All rights reserved.
 */

package com.eos.numbers.to.appmovies.Adapter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.eos.numbers.to.appmovies.Helper.config;
import com.eos.numbers.to.appmovies.Item.itemMain;
import com.eos.numbers.to.appmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class itemAdapter extends RecyclerView.Adapter<itemAdapter.ViewHolder> {

    private List<itemMain> list;
    private OnItemClickListener itemClickListener;
    private Context context;

    public itemAdapter(List<itemMain> list, OnItemClickListener itemClickListener) {
        this.list = list;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView textViewTitle, textViewVotes;
        ImageView image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

           textViewTitle = itemView.findViewById(R.id.title);
           textViewVotes = itemView.findViewById(R.id.votes);
           image = itemView.findViewById(R.id.image);

        }

        public void bind (final itemMain item){

            textViewTitle.setText(String.valueOf(item.getTitle()));
            textViewVotes.setText(String.valueOf(item.getVotes()));

            Picasso.get()
                    .load(config.getUrlImages()+item.getPoster())
                    .into(image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickListener.OnItemClickListener(getAdapterPosition(), list.get(getAdapterPosition()));
                }
            });

        }
    }

    @Override
    public itemAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull itemAdapter.ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface OnItemClickListener{
        void OnItemClickListener(int position, itemMain item);
    }

    public void deleteItem(int index) {
        list.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, list.size());
    }

    public void deleteAllItem(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<itemMain> productsItems){
        list.clear();
        list.addAll(productsItems);
        notifyDataSetChanged();
    }
}

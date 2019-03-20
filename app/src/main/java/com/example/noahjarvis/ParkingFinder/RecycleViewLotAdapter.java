package com.example.noahjarvis.ParkingFinder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewLotAdapter extends RecyclerView.Adapter<RecycleViewLotAdapter.ViewHolder>{
    private ArrayList<ParkingLot> lots;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    public RecycleViewLotAdapter(ArrayList<ParkingLot> lots){
        super();
        this.lots = lots;
    }
    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lot_text, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String lot = lots.get(position).toString();
        holder.myTextView.setText(lot);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return lots.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.lot_list);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }
    ParkingLot getItem(int id) {
        return lots.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}

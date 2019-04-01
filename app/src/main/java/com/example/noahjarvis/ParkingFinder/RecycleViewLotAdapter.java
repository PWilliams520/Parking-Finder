package com.example.noahjarvis.ParkingFinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecycleViewLotAdapter extends RecyclerView.Adapter<RecycleViewLotAdapter.LotViewHolder>{
    private ArrayList<ParkingLot> lots;
    private LayoutInflater mInflater;
    private View.OnClickListener mClickListener;

    public RecycleViewLotAdapter(Context context, ArrayList<ParkingLot> lots){
        super();
        mInflater = LayoutInflater.from(context);
        this.lots = lots;
    }
    // inflates the row layout from xml when needed
    @Override
    public LotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.lot_text, parent, false);
        return new RecycleViewLotAdapter.LotViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(LotViewHolder holder, int position) {
        //Set texts of lots
        ParkingLot lot = lots.get(position);
        holder.lotTitle.setText(lot.getName());
        holder.lotText.setText(lot.toString());
        String percent = lot.getPercent() + "% of spots taken";
        holder.lotPercent.setText(percent);

        //sets color of even indices to cyan
        if(position%2 == 0) {
            holder.lotTitle.setBackgroundColor(0xFF00FFFF);
            holder.lotText.setBackgroundColor(0xFF00FFFF);
            holder.lotPercent.setBackgroundColor(0xFF00FFFF);

        }

        //sets color of odd indices to aquamarine
        else{
            holder.lotTitle.setBackgroundColor(0xFF7FFFD4);
            holder.lotText.setBackgroundColor(0xFF7FFFD4);
            holder.lotPercent.setBackgroundColor(0xFF7FFFD4);
        }
    }


    // total number of rows
    @Override
    public int getItemCount() {
        return lots.size();
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mClickListener = itemClickListener;
    }


    public class LotViewHolder extends RecyclerView.ViewHolder {
        TextView lotTitle;      //Name of Lot
        TextView lotText;       //Capacity of lot
        TextView lotPercent;    //Percent of spaces used

        LotViewHolder(View itemView) {
            super(itemView);
            lotTitle = itemView.findViewById(R.id.lot_title);
            lotText  = itemView.findViewById(R.id.lot_text);
            lotPercent = itemView.findViewById(R.id.lot_percent);
            itemView.setTag(this);
            itemView.setOnClickListener(mClickListener);
        }
    }
}

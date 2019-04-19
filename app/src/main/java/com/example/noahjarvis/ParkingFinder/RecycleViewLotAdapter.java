package com.example.noahjarvis.ParkingFinder;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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




        ////sets color of even indices to cyan
        int ColorOne = 0xFFAFEEEE; //Pale Turquiose
        int ColorTwo = 0xFF00FFFF; //Light Cyan

        int EmptyColor = 0x75C6EFCE;   //Light Green
        int MedColor = 0x75FFEB9C;   //Light Yellow
        int FullColor = 0x75FFC7CE;   //Light Red

        int EmptyText = 0xFF006100;   //Dark Green
        int MedText = 0xFF9C5700;   //Dark Yellow
        int FullText = 0xFF9C0006;   //Dark Red

        //sets color based on capacity

        //Color if capacity unknown
        if(lot.getCurrent() < 0){
            String capacity = "?/" + lot.getCapacity();
            holder.lotText.setText(capacity);
            percent = "lot capacity unknown";
            holder.lotPercent.setText(percent);
            holder.lotTitle.setBackgroundColor(0xFFE0E0E0);
            holder.lotText.setBackgroundColor(0xFFE0E0E0);
            holder.lotPercent.setBackgroundColor(0xFFE0E0E0);
            holder.lotTitle.setTextColor(0xFF777777);
            holder.lotText.setTextColor(0xFF777777);
            holder.lotPercent.setTextColor(0xFF777777);

        }
        //color if mostly empty
        else if (lot.getPercent() < 33) {
            holder.lotTitle.setBackgroundColor(EmptyColor);
            holder.lotText.setBackgroundColor(EmptyColor);
            holder.lotPercent.setBackgroundColor(EmptyColor);

            holder.lotTitle.setTextColor(EmptyText);
            holder.lotText.setTextColor(EmptyText);
            holder.lotPercent.setTextColor(EmptyText);

        }
        //color if partially filled
        else if (lot.getPercent() < 67 && lot.getPercent() >= 33) {
            holder.lotTitle.setBackgroundColor(MedColor);
            holder.lotText.setBackgroundColor(MedColor);
            holder.lotPercent.setBackgroundColor(MedColor);

            holder.lotTitle.setTextColor(MedText);
            holder.lotText.setTextColor(MedText);
            holder.lotPercent.setTextColor(MedText);
        }
        //color if full or almost full
        else {
            holder.lotTitle.setBackgroundColor(ColorTwo);
            holder.lotText.setBackgroundColor(ColorTwo);
            holder.lotPercent.setBackgroundColor(ColorTwo);
            holder.lotTitle.setBackgroundColor(FullColor);
            holder.lotText.setBackgroundColor(FullColor);
            holder.lotPercent.setBackgroundColor(FullColor);

            holder.lotTitle.setTextColor(FullText);
            holder.lotText.setTextColor(FullText);
            holder.lotPercent.setTextColor(FullText);

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

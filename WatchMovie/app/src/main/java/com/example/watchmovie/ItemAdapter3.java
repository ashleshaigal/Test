package com.example.watchmovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


//____________________ ADAPTER FOR SERIES RECYCLEVIEW

public class ItemAdapter3 extends RecyclerView.Adapter<ItemAdapter3.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<ItemModel> imageModelArrayList;
    private OnSeriesListner mOnItemListner;

    public ItemAdapter3(Context ctx, ArrayList<ItemModel> imageModelArrayList, OnSeriesListner onItemListner){

        inflater = LayoutInflater.from(ctx);
        this.imageModelArrayList = imageModelArrayList;
        this.mOnItemListner=onItemListner;

    }

    @Override
    public ItemAdapter3.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        MyViewHolder holder = new MyViewHolder(view,mOnItemListner);

        return holder;
    }

    @Override
    public void onBindViewHolder(ItemAdapter3.MyViewHolder holder, int position) {

        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.time.setText(imageModelArrayList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView time;
        ImageView iv;
        OnSeriesListner onItemListner;

        public MyViewHolder(View itemView,OnSeriesListner onItemListner) {
            super(itemView);

            time = (TextView) itemView.findViewById(R.id.tv);
            iv = (ImageView) itemView.findViewById(R.id.iv);
            this.onItemListner=onItemListner;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListner.onSeriesClick(getAdapterPosition());
        }
    }
    public interface OnSeriesListner{
        void onSeriesClick(int position);
    }
}
